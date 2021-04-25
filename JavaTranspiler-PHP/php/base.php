<?php
function loadbaseclasses($v) {
    $v->classes["java/lang/Object"] = [
        "name" => "java/lang/Object",
        "super" => "java/lang/Object",
        "flags" => [
            "public" => true,
            "private" => false,
            "protected" => false,
            "static" => false,
            "native" => false
        ],
        "methods" => [
            "<init>" => [
                "()V" => [
                    "name" => "<init>",
                    "descriptor" => "()V",
                    "code" => function($v,$l,$s) {},
                    "flags" => [
                        "public" => true,
                        "private" => false,
                        "protected" => false,
                        "static" => false,
                        "native" => false
                    ]
                ]
            ]
        ]
    ];
}
