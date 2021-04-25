<?php
function loaduser() { return function($v){
$v->classes["Test"] = [
"super" => "java/lang/Object",
"methods" => [
"test" => [
"([Ljava/lang/String;)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[1]);
ins5:
array_unshift($s,count(array_shift($s)["values"]));
ins6:
if (array_shift($s) != 0) goto ins19;
ins9:
array_unshift($s,[
"fields" => [

],
"class" => "java/lang/RuntimeException"
]);
ins12:
array_unshift($s, $s[0]);
ins13:
array_unshift($s,[
"class" => "java/lang/String",
"value" => "Fehler"
]);
ins15:
array_unshift($s,$v->invoke($t, "java/lang/RuntimeException","<init>","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins18:
$t["exception"] = array_shift($s);
array_unshift($s, NULL);
goto ret;
ins19:
array_unshift($s,$v->getstatic($t, "java/lang/System","out"));
ins22:
array_unshift($s,$l[1]);
ins23:
array_unshift($s,[
"class" => "int",
"value" => 0
]);
ins24:
array_unshift($s,array_splice($s,1,1)[0]["value"][array_shift($s)["value"]]);
ins25:
array_unshift($s,$v->invoke($t, "java/io/PrintStream","println","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins28:
array_unshift($s, NULL);
goto ret;
ret:
return array_unshift($s);
},
"name" => "test",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
],
"descriptor" => "([Ljava/lang/String;)V"
]
],
"main" => [
"([Ljava/lang/String;)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$v->getstatic($t, "Test","test"));
ins7:
array_unshift($s,$l[0]);
ins8:
array_unshift($s,$v->invoke($t, "Test","test","([Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
if ($v->exception_type_check("java/lang/RuntimeException",$t["exception"])) {
array_shift($s);
array_unshift($s, $t["exception"]);
$t["exception"] = NULL;
goto ins14;
}
goto ret;
}
array_shift($s);
ins11:
// GOTO
ins14:
$l[1] = array_shift($s);
ins15:
array_unshift($s,$v->getstatic($t, "java/lang/System","out"));
ins18:
array_unshift($s,[
"class" => "java/lang/String",
"value" => "Ein Fehler"
]);
ins20:
array_unshift($s,$v->invoke($t, "java/io/PrintStream","println","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins23:
array_unshift($s, NULL);
goto ret;
ret:
return array_unshift($s);
},
"name" => "main",
"flags" => [
"private" => false,
"static" => true,
"protected" => false,
"public" => true,
"native" => false
],
"descriptor" => "([Ljava/lang/String;)V"
]
],
"<init>" => [
"()V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$v->invoke($t, "java/lang/Object","<init>","()V",[array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins8:
array_unshift($s, NULL);
goto ret;
ret:
return array_unshift($s);
},
"name" => "<init>",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
],
"descriptor" => "()V"
]
],
"<clinit>" => [
"()V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,[
"fields" => [

],
"class" => "Test"
]);
ins7:
array_unshift($s, $s[0]);
ins8:
array_unshift($s,$v->invoke($t, "Test","<init>","()V",[array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins11:
$v->setstatic($t, "Test","test",array_unshift($s));
ins14:
array_unshift($s, NULL);
goto ret;
ret:
return array_unshift($s);
},
"name" => "<clinit>",
"flags" => [
"private" => false,
"static" => true,
"protected" => false,
"public" => false,
"native" => false
],
"descriptor" => "()V"
]
]
],
"name" => "Test",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
]
];
};}