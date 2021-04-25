<?php
require './base.php';
require './system.php';

class VM {
    public $vm = [
        'classes' => [],
        'next_thread_id' => 1
    ];

    public function __construct () {
        loadbaseclasses($this);
        loadsystemclasses()($this); // the double invoke is intended
    }

    public function newthread ($name) {
        $this->vm['next_thread_id'] = $this->vm['next_thread_id'] + 1;
        if ($name == NULL) {
            $name = "Thread #" . strval($this->vm['next_thread_id'] - 1);
        }
        return [
            'vm' => $this,
            'name' => $name,
            'id' => $this->vm['next_thread_id'] - 1,
            'callstack' => []
        ];
    }

    public function getclass($thread, $name) {
        $c = &$this->vm['classes'][$name];
        if ($c == NULL) {
            echo "CLASS NOT FOUND!";
            return NULL;
        }
        if (!array_key_exists('static', $c)) {
            $c['static'] = [];
            $this->invoke($thread, $name, '<clinit>', '()V', []);
        }
        return $c;
    }

    public function getstatic($thread, $class, $field) {
        $c = $this->getclass($thread, $class);
        if ($c == NULL) {
            //Exception
            return NULL;
        }
        return $c['static'][$field];
    }

    public function setstatic($thread, $class, $field, $value) {
         $c = $this->getclass($thread, $class);
         if ($c == NULL) {
            //Exception
            return NULL;
         }
         $this->vm['classes'][$class]['static'][$field] = $value;
    }

    public function main($class, ...$args) {
        $thread = $this->newthread("Main");
        $ca = [["values" => []]];
        foreach ($args as $_ => $a) {
            array_unshift($ca[0]["values"], [
                "class" => "java/lang/String",
                "value" => $a
            ]);
        }
        $this->invoke($thread, $class, 'main', '([Ljava/lang/String;)V', $ca);
    }

    public function invoke($thread, $class, $method, $descriptor, $args) {
        $stack = [];
        $locals = [];
        foreach ($args as $i => $v) {
            $locals[$i] = $v;
        }

        $c = $this->getclass($thread, $class);
        if ($c == NULL) {
            // Exception
            return NULL;
        }
        if (!array_key_exists($method, $c["methods"])) {
            // Exception
            return NULL;
        }
        $om = $c["methods"][$method];
        $m = $om[$descriptor];
        if ($m == NULL) {
            // Exception
            return NULL;
        }
        if ($class == "Test")
            echo var_dump($locals);
        $rval = $m["code"]($this, $thread, $locals, $stack);
        return $rval;
    }
}