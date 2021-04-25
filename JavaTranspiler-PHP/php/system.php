<?php
function loadsystemclasses() { return function($v){
$v->classes["java/io/FilterOutputStream"] = [
"super" => "java/io/OutputStream",
"methods" => [
"<init>" => [
"()V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$v->invoke($t, "java/io/OutputStream","<init>","()V",[array_unshift($s,0)]));
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
]
],
"name" => "java/io/FilterOutputStream",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
]
];
$v->classes["java/io/OutputStream"] = [
"super" => "java/lang/Object",
"methods" => [
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
]
],
"name" => "java/io/OutputStream",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
]
];
$v->classes["java/io/PrintStream"] = [
"super" => "java/io/FilterOutputStream",
"methods" => [
"println" => [
"(Ljava/lang/Object;)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s,$v->invoke($t, "java/io/PrintStream","print","(Ljava/lang/Object;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins9:
array_unshift($s,$l[0]);
ins10:
array_unshift($s,[
"class" => "java/lang/String",
"value" => "\n"
]);
ins12:
array_unshift($s,$v->invoke($t, "java/io/PrintStream","print","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins15:
array_unshift($s, NULL);
goto ret;
ret:
return array_unshift($s);
},
"name" => "println",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
],
"descriptor" => "(Ljava/lang/Object;)V"
],
"(Ljava/lang/String;)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s,$v->invoke($t, "java/io/PrintStream","print","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins9:
array_unshift($s,$l[0]);
ins10:
array_unshift($s,[
"class" => "java/lang/String",
"value" => "\n"
]);
ins12:
array_unshift($s,$v->invoke($t, "java/io/PrintStream","print","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins15:
array_unshift($s, NULL);
goto ret;
ret:
return array_unshift($s);
},
"name" => "println",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
],
"descriptor" => "(Ljava/lang/String;)V"
],
"(I)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s,$v->invoke($t, "java/io/PrintStream","print","(I)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins9:
array_unshift($s,$l[0]);
ins10:
array_unshift($s,[
"class" => "java/lang/String",
"value" => "\n"
]);
ins12:
array_unshift($s,$v->invoke($t, "java/io/PrintStream","print","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins15:
array_unshift($s, NULL);
goto ret;
ret:
return array_unshift($s);
},
"name" => "println",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
],
"descriptor" => "(I)V"
]
],
"print" => [
"(Ljava/lang/Object;)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s,$v->invoke($t, "java/lang/String","valueOf","(Ljava/lang/Object;)Ljava/lang/String;",[array_unshift($s,1)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
ins9:
array_unshift($s,$v->invoke($t, "java/io/PrintStream","print","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins12:
array_unshift($s, NULL);
goto ret;
ret:
return array_unshift($s);
},
"name" => "print",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
],
"descriptor" => "(Ljava/lang/Object;)V"
],
"(F)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s,$v->invoke($t, "java/lang/String","valueOf","(F)Ljava/lang/String;",[array_unshift($s,1)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
ins9:
array_unshift($s,$v->invoke($t, "java/io/PrintStream","print","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins12:
array_unshift($s, NULL);
goto ret;
ret:
return array_unshift($s);
},
"name" => "print",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
],
"descriptor" => "(F)V"
],
"(D)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s,$v->invoke($t, "java/lang/String","valueOf","(D)Ljava/lang/String;",[array_unshift($s,1)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
ins9:
array_unshift($s,$v->invoke($t, "java/io/PrintStream","print","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins12:
array_unshift($s, NULL);
goto ret;
ret:
return array_unshift($s);
},
"name" => "print",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
],
"descriptor" => "(D)V"
],
"(C)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s,$v->invoke($t, "java/lang/String","valueOf","(C)Ljava/lang/String;",[array_unshift($s,1)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
ins9:
array_unshift($s,$v->invoke($t, "java/io/PrintStream","print","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins12:
array_unshift($s, NULL);
goto ret;
ret:
return array_unshift($s);
},
"name" => "print",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
],
"descriptor" => "(C)V"
],
"(J)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s,$v->invoke($t, "java/lang/String","valueOf","(J)Ljava/lang/String;",[array_unshift($s,1)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
ins9:
array_unshift($s,$v->invoke($t, "java/io/PrintStream","print","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins12:
array_unshift($s, NULL);
goto ret;
ret:
return array_unshift($s);
},
"name" => "print",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
],
"descriptor" => "(J)V"
],
"(Z)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s,$v->invoke($t, "java/lang/String","valueOf","(Z)Ljava/lang/String;",[array_unshift($s,1)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
ins9:
array_unshift($s,$v->invoke($t, "java/io/PrintStream","print","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins12:
array_unshift($s, NULL);
goto ret;
ret:
return array_unshift($s);
},
"name" => "print",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
],
"descriptor" => "(Z)V"
],
"(Ljava/lang/String;)V" => [
"code" => function($v,$t,$l,$s){
echo $l[1]["value"];
},
"name" => "print",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => true
],
"descriptor" => "(Ljava/lang/String;)V"
],
"(I)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s,$v->invoke($t, "java/lang/String","valueOf","(I)Ljava/lang/String;",[array_unshift($s,1)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
ins9:
array_unshift($s,$v->invoke($t, "java/io/PrintStream","print","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins12:
array_unshift($s, NULL);
goto ret;
ret:
return array_unshift($s);
},
"name" => "print",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
],
"descriptor" => "(I)V"
]
],
"<init>" => [
"()V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$v->invoke($t, "java/io/FilterOutputStream","<init>","()V",[array_unshift($s,0)]));
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
]
],
"name" => "java/io/PrintStream",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
]
];
$v->classes["java/lang/Exception"] = [
"super" => "java/lang/Throwable",
"methods" => [
"<init>" => [
"(Ljava/lang/Throwable;)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s,$v->invoke($t, "java/lang/Throwable","<init>","(Ljava/lang/Throwable;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins9:
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
"descriptor" => "(Ljava/lang/Throwable;)V"
],
"()V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$v->invoke($t, "java/lang/Throwable","<init>","()V",[array_unshift($s,0)]));
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
],
"(Ljava/lang/String;Ljava/lang/Throwable;)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s,$l[2]);
ins7:
array_unshift($s,$v->invoke($t, "java/lang/Throwable","<init>","(Ljava/lang/String;Ljava/lang/Throwable;)V",[array_unshift($s,2),array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins10:
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
"descriptor" => "(Ljava/lang/String;Ljava/lang/Throwable;)V"
],
"(Ljava/lang/String;)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s,$v->invoke($t, "java/lang/Throwable","<init>","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins9:
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
"descriptor" => "(Ljava/lang/String;)V"
]
]
],
"name" => "java/lang/Exception",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
]
];
$v->classes["java/lang/RuntimeException"] = [
"super" => "java/lang/Exception",
"methods" => [
"<init>" => [
"(Ljava/lang/Throwable;)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s,$v->invoke($t, "java/lang/Exception","<init>","(Ljava/lang/Throwable;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins9:
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
"descriptor" => "(Ljava/lang/Throwable;)V"
],
"()V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$v->invoke($t, "java/lang/Exception","<init>","()V",[array_unshift($s,0)]));
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
],
"(Ljava/lang/String;Ljava/lang/Throwable;)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[2]);
ins6:
array_unshift($s,$v->invoke($t, "java/lang/Exception","<init>","(Ljava/lang/Throwable;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins9:
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
"descriptor" => "(Ljava/lang/String;Ljava/lang/Throwable;)V"
],
"(Ljava/lang/String;)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s,$v->invoke($t, "java/lang/Exception","<init>","(Ljava/lang/String;)V",[array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins9:
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
"descriptor" => "(Ljava/lang/String;)V"
]
]
],
"name" => "java/lang/RuntimeException",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
]
];
$v->classes["java/lang/String"] = [
"super" => "java/lang/Object",
"methods" => [
"valueOf" => [
"(I)Ljava/lang/String;" => [
"code" => function($v,$t,$l,$s){
return strval($l[0]["value"]);
},
"name" => "valueOf",
"flags" => [
"private" => false,
"static" => true,
"protected" => false,
"public" => true,
"native" => true
],
"descriptor" => "(I)Ljava/lang/String;"
],
"(J)Ljava/lang/String;" => [
"code" => function($v,$t,$l,$s){
return strval($l[0]["value"]);
},
"name" => "valueOf",
"flags" => [
"private" => false,
"static" => true,
"protected" => false,
"public" => true,
"native" => true
],
"descriptor" => "(J)Ljava/lang/String;"
],
"(F)Ljava/lang/String;" => [
"code" => function($v,$t,$l,$s){
return strval($l[0]["value"]);
},
"name" => "valueOf",
"flags" => [
"private" => false,
"static" => true,
"protected" => false,
"public" => true,
"native" => true
],
"descriptor" => "(F)Ljava/lang/String;"
],
"(D)Ljava/lang/String;" => [
"code" => function($v,$t,$l,$s){
return strval($l[0]["value"]);
},
"name" => "valueOf",
"flags" => [
"private" => false,
"static" => true,
"protected" => false,
"public" => true,
"native" => true
],
"descriptor" => "(D)Ljava/lang/String;"
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
]
],
"name" => "java/lang/String",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
]
];
$v->classes["java/lang/System"] = [
"super" => "java/lang/Object",
"methods" => [
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
]
],
"name" => "java/lang/System",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
]
];
$v->classes["java/lang/Throwable"] = [
"super" => "java/lang/Object",
"methods" => [
"getMessage" => [
"()Ljava/lang/String;" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
// GETFIELD
ins8:
goto ret;
ret:
return array_unshift($s);
},
"name" => "getMessage",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
],
"descriptor" => "()Ljava/lang/String;"
]
],
"<init>" => [
"(Ljava/lang/Throwable;)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s, NULL);
ins6:
array_unshift($s,$l[1]);
ins7:
array_unshift($s,$v->invoke($t, "java/lang/Throwable","<init>","(Ljava/lang/String;Ljava/lang/Throwable;)V",[array_unshift($s,2),array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins10:
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
"descriptor" => "(Ljava/lang/Throwable;)V"
],
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
],
"(Ljava/lang/String;Ljava/lang/Throwable;)V" => [
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
array_unshift($s,$l[0]);
ins9:
array_unshift($s,$l[1]);
ins10:
// PUTFIELD
ins13:
array_unshift($s,$l[0]);
ins14:
array_unshift($s,$l[2]);
ins15:
// PUTFIELD
ins18:
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
"descriptor" => "(Ljava/lang/String;Ljava/lang/Throwable;)V"
],
"(Ljava/lang/String;)V" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
array_unshift($s,$l[1]);
ins6:
array_unshift($s, NULL);
ins7:
array_unshift($s,$v->invoke($t, "java/lang/Throwable","<init>","(Ljava/lang/String;Ljava/lang/Throwable;)V",[array_unshift($s,2),array_unshift($s,1),array_unshift($s,0)]));
if (array_key_exists("exception", $t)) {
goto ret;
}
array_shift($s);
ins10:
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
"descriptor" => "(Ljava/lang/String;)V"
]
],
"getCause" => [
"()Ljava/lang/Throwable;" => [
"code" => function($v,$t,$l,$s){
ins4:
array_unshift($s,$l[0]);
ins5:
// GETFIELD
ins8:
goto ret;
ret:
return array_unshift($s);
},
"name" => "getCause",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
],
"descriptor" => "()Ljava/lang/Throwable;"
]
]
],
"name" => "java/lang/Throwable",
"flags" => [
"private" => false,
"static" => false,
"protected" => false,
"public" => true,
"native" => false
]
];
};}