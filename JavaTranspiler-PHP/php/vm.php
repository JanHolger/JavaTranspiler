<?php
require './base.php';
require './system.php';

class VM {
    public $classes = [];
    public $next_thread_id = 1;

    public function __construct () {
        loadbaseclasses($this);
        loadsystemclasses()($this); // the double invoke is intended
    }

    public function newthread ($name) {
        $this->next_thread_id++;
        if ($name == NULL) {
            $name = "Thread #" . strval($this->next_thread_id - 1);
        }
        return [
            'vm' => $this,
            'name' => $name,
            'id' => $this->next_thread_id - 1,
            'callstack' => [],
            'exception' => NULL
        ];
    }

    public function getclass(&$thread, $name) {
        if (!array_key_exists($name,$this->classes)) {
            echo "CLASS NOT FOUND! ".$name;
            return NULL;
        }
        $c = &$this->classes[$name];
        if (!array_key_exists('static', $c)) {
            $c['static'] = [];
            $this->invoke($thread, $name, '<clinit>', '()V', []);
        }
        return $c;
    }

    public function getstatic(&$thread, $class, $field) {
        $c = $this->getclass($thread, $class);
        if ($c == NULL) {
            echo "CNF";
            return NULL;
        }
        if(!array_key_exists('static', $c)) {
            return NULL;
        }
        if(!array_key_exists($field, $c['static'])) {
            return NULL;
        }
        return $c['static'][$field];
    }

    public function setstatic(&$thread, $class, $field, $value) {
         $c = $this->getclass($thread, $class);
         if ($c == NULL) {
            //Exception
            return NULL;
         }
         $this->classes[$class]['static'][$field] = $value;
    }

    public function getfield(&$thread, $class, $field, $instance) {
        if ($instance == NULL) {
            echo "NPE\n";
            return NULL;
        }
        if(!array_key_exists('fields', $instance)) {
            echo "NON OBJECT\n";
            return NULL;
        }
        return $instance['fields'][$field];
    }

    public function setfield(&$thread, $class, $field, $instance, $value) {
         if ($instance == NULL) {
             echo "NPE\n";
             return NULL;
         }
         if(!array_key_exists('fields', $instance)) {
             echo "NON OBJECT\n";
             return NULL;
         }
         $instance['fields'][$field] = $value;
    }

    public function instanceof(&$thread, $value, $type) {
        $isi = 0;
        if($value != NULL) {
            if($value['type'] == $type) {
                $isi = 1;
            }
        }
        return [
            'type' => 'Z',
            'value' => $isi
        ];
    }

    public function main($class, ...$args) {
        $thread = $this->newthread("Main");
        $ca = [["values" => []]];
        foreach ($args as $_ => $a) {
            array_unshift($ca[0]["values"], [
                "type" => "Ljava/lang/String;",
                "value" => $a
            ]);
        }
        $this->invoke($thread, $class, 'main', '([Ljava/lang/String;)V', $ca);
    }

    public function invoke(&$thread, $class, $method, $descriptor, $args) {
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
        $rval = $m["code"]($this, $thread, $locals, $stack);
        return $rval;
    }
}