<?php
require 'vm.php';
require 'user.php';

$vm = new VM();
loaduser()($vm);
try {
    $thread = $vm->newthread("Main");
    $vm->invoke($thread,'Test','main','([Ljava/lang/String;)V', [["values" => [["type" => "Ljava/lang/String;", "value" => "Hallo"]]]]);
} catch(Exception $ex) {
    echo "Catch exception: " . $ex->getMessage();
}