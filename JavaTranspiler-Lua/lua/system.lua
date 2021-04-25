return function(v)
v.classes["java/io/FilterOutputStream"] = {
super = "java/io/OutputStream",
methods = {
["<init>"] = {
["()V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,v.invoke(t,"java/io/OutputStream","<init>","()V",{table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins8::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "()V"
}
}
},
name = "java/io/FilterOutputStream",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
}
}
v.classes["java/io/OutputStream"] = {
super = "java/lang/Object",
methods = {
["<init>"] = {
["()V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,v.invoke(t,"java/lang/Object","<init>","()V",{table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins8::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "()V"
}
}
},
name = "java/io/OutputStream",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
}
}
v.classes["java/io/PrintStream"] = {
super = "java/io/FilterOutputStream",
methods = {
println = {
["(Ljava/lang/Object;)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,v.invoke(t,"java/io/PrintStream","print","(Ljava/lang/Object;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins9::
table.insert(s,1,l[1])
::ins10::
table.insert(s,1,{
type = "Ljava/lang/String;",
value = "\n"
})
::ins12::
table.insert(s,1,v.invoke(t,"java/io/PrintStream","print","(Ljava/lang/String;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins15::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "println",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(Ljava/lang/Object;)V"
},
["(Ljava/lang/String;)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,v.invoke(t,"java/io/PrintStream","print","(Ljava/lang/String;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins9::
table.insert(s,1,l[1])
::ins10::
table.insert(s,1,{
type = "Ljava/lang/String;",
value = "\n"
})
::ins12::
table.insert(s,1,v.invoke(t,"java/io/PrintStream","print","(Ljava/lang/String;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins15::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "println",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(Ljava/lang/String;)V"
},
["(I)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,v.invoke(t,"java/io/PrintStream","print","(I)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins9::
table.insert(s,1,l[1])
::ins10::
table.insert(s,1,{
type = "Ljava/lang/String;",
value = "\n"
})
::ins12::
table.insert(s,1,v.invoke(t,"java/io/PrintStream","print","(Ljava/lang/String;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins15::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "println",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(I)V"
}
},
print = {
["(Ljava/lang/Object;)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,v.invoke(t,"java/lang/String","valueOf","(Ljava/lang/Object;)Ljava/lang/String;",{table.remove(s,2)}))
if t.exception ~= nil then
goto ret
end
::ins9::
table.insert(s,1,v.invoke(t,"java/io/PrintStream","print","(Ljava/lang/String;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins12::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "print",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(Ljava/lang/Object;)V"
},
["(F)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,v.invoke(t,"java/lang/String","valueOf","(F)Ljava/lang/String;",{table.remove(s,2)}))
if t.exception ~= nil then
goto ret
end
::ins9::
table.insert(s,1,v.invoke(t,"java/io/PrintStream","print","(Ljava/lang/String;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins12::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "print",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(F)V"
},
["(D)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,v.invoke(t,"java/lang/String","valueOf","(D)Ljava/lang/String;",{table.remove(s,2)}))
if t.exception ~= nil then
goto ret
end
::ins9::
table.insert(s,1,v.invoke(t,"java/io/PrintStream","print","(Ljava/lang/String;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins12::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "print",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(D)V"
},
["(C)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,v.invoke(t,"java/lang/String","valueOf","(C)Ljava/lang/String;",{table.remove(s,2)}))
if t.exception ~= nil then
goto ret
end
::ins9::
table.insert(s,1,v.invoke(t,"java/io/PrintStream","print","(Ljava/lang/String;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins12::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "print",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(C)V"
},
["(J)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,v.invoke(t,"java/lang/String","valueOf","(J)Ljava/lang/String;",{table.remove(s,2)}))
if t.exception ~= nil then
goto ret
end
::ins9::
table.insert(s,1,v.invoke(t,"java/io/PrintStream","print","(Ljava/lang/String;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins12::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "print",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(J)V"
},
["(Z)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,v.invoke(t,"java/lang/String","valueOf","(Z)Ljava/lang/String;",{table.remove(s,2)}))
if t.exception ~= nil then
goto ret
end
::ins9::
table.insert(s,1,v.invoke(t,"java/io/PrintStream","print","(Ljava/lang/String;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins12::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "print",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(Z)V"
},
["(Ljava/lang/String;)V"] = {
code = function(v,t,l,s)
io.write(l[2].value)
end,
name = "print",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = true
},
descriptor = "(Ljava/lang/String;)V"
},
["(I)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,v.invoke(t,"java/lang/String","valueOf","(I)Ljava/lang/String;",{table.remove(s,2)}))
if t.exception ~= nil then
goto ret
end
::ins9::
table.insert(s,1,v.invoke(t,"java/io/PrintStream","print","(Ljava/lang/String;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins12::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "print",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(I)V"
}
},
["<init>"] = {
["()V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,v.invoke(t,"java/io/FilterOutputStream","<init>","()V",{table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins8::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "()V"
}
}
},
name = "java/io/PrintStream",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
}
}
v.classes["java/lang/Exception"] = {
super = "java/lang/Throwable",
methods = {
["<init>"] = {
["(Ljava/lang/Throwable;)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,v.invoke(t,"java/lang/Throwable","<init>","(Ljava/lang/Throwable;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins9::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(Ljava/lang/Throwable;)V"
},
["()V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,v.invoke(t,"java/lang/Throwable","<init>","()V",{table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins8::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "()V"
},
["(Ljava/lang/String;Ljava/lang/Throwable;)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,l[3])
::ins7::
table.insert(s,1,v.invoke(t,"java/lang/Throwable","<init>","(Ljava/lang/String;Ljava/lang/Throwable;)V",{table.remove(s,3),table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins10::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(Ljava/lang/String;Ljava/lang/Throwable;)V"
},
["(Ljava/lang/String;)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,v.invoke(t,"java/lang/Throwable","<init>","(Ljava/lang/String;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins9::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(Ljava/lang/String;)V"
}
}
},
name = "java/lang/Exception",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
}
}
v.classes["java/lang/RuntimeException"] = {
super = "java/lang/Exception",
methods = {
["<init>"] = {
["(Ljava/lang/Throwable;)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,v.invoke(t,"java/lang/Exception","<init>","(Ljava/lang/Throwable;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins9::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(Ljava/lang/Throwable;)V"
},
["()V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,v.invoke(t,"java/lang/Exception","<init>","()V",{table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins8::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "()V"
},
["(Ljava/lang/String;Ljava/lang/Throwable;)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[3])
::ins6::
table.insert(s,1,v.invoke(t,"java/lang/Exception","<init>","(Ljava/lang/Throwable;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins9::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(Ljava/lang/String;Ljava/lang/Throwable;)V"
},
["(Ljava/lang/String;)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,v.invoke(t,"java/lang/Exception","<init>","(Ljava/lang/String;)V",{table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins9::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(Ljava/lang/String;)V"
}
}
},
name = "java/lang/RuntimeException",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
}
}
v.classes["java/lang/String"] = {
super = "java/lang/Object",
methods = {
valueOf = {
["(I)Ljava/lang/String;"] = {
code = function(v,t,l,s)
return tostring(l[1].value)
end,
name = "valueOf",
flags = {
private = false,
static = true,
protected = false,
public = true,
native = true
},
descriptor = "(I)Ljava/lang/String;"
},
["(J)Ljava/lang/String;"] = {
code = function(v,t,l,s)
return tostring(l[1].value)
end,
name = "valueOf",
flags = {
private = false,
static = true,
protected = false,
public = true,
native = true
},
descriptor = "(J)Ljava/lang/String;"
},
["(F)Ljava/lang/String;"] = {
code = function(v,t,l,s)
return tostring(l[1].value)
end,
name = "valueOf",
flags = {
private = false,
static = true,
protected = false,
public = true,
native = true
},
descriptor = "(F)Ljava/lang/String;"
},
["(D)Ljava/lang/String;"] = {
code = function(v,t,l,s)
return tostring(l[1].value)
end,
name = "valueOf",
flags = {
private = false,
static = true,
protected = false,
public = true,
native = true
},
descriptor = "(D)Ljava/lang/String;"
}
},
["<init>"] = {
["()V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,v.invoke(t,"java/lang/Object","<init>","()V",{table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins8::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "()V"
}
}
},
name = "java/lang/String",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
}
}
v.classes["java/lang/System"] = {
super = "java/lang/Object",
methods = {
["<init>"] = {
["()V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,v.invoke(t,"java/lang/Object","<init>","()V",{table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins8::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "()V"
}
}
},
name = "java/lang/System",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
}
}
v.classes["java/lang/Throwable"] = {
super = "java/lang/Object",
methods = {
getMessage = {
["()Ljava/lang/String;"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,v.getfield(t,"java/lang/Throwable","message",table.remove(s,1)))
::ins8::
goto ret
::ret::
return table.remove(s,1)
end,
name = "getMessage",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "()Ljava/lang/String;"
}
},
["<init>"] = {
["(Ljava/lang/Throwable;)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,nil)
::ins6::
table.insert(s,1,l[2])
::ins7::
table.insert(s,1,v.invoke(t,"java/lang/Throwable","<init>","(Ljava/lang/String;Ljava/lang/Throwable;)V",{table.remove(s,3),table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins10::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(Ljava/lang/Throwable;)V"
},
["()V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,v.invoke(t,"java/lang/Object","<init>","()V",{table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins8::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "()V"
},
["(Ljava/lang/String;Ljava/lang/Throwable;)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,v.invoke(t,"java/lang/Object","<init>","()V",{table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins8::
table.insert(s,1,l[1])
::ins9::
table.insert(s,1,l[2])
::ins10::
v.putfield(t,"java/lang/Throwable","message",table.remove(s,2),table.remove(s,1))
::ins13::
table.insert(s,1,l[1])
::ins14::
table.insert(s,1,l[3])
::ins15::
v.putfield(t,"java/lang/Throwable","cause",table.remove(s,2),table.remove(s,1))
::ins18::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(Ljava/lang/String;Ljava/lang/Throwable;)V"
},
["(Ljava/lang/String;)V"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,l[2])
::ins6::
table.insert(s,1,nil)
::ins7::
table.insert(s,1,v.invoke(t,"java/lang/Throwable","<init>","(Ljava/lang/String;Ljava/lang/Throwable;)V",{table.remove(s,3),table.remove(s,2),table.remove(s,1)}))
if t.exception ~= nil then
goto ret
end
table.remove(s,1)
::ins10::
table.insert(s,1,nil)
goto ret
::ret::
return table.remove(s,1)
end,
name = "<init>",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "(Ljava/lang/String;)V"
}
},
getCause = {
["()Ljava/lang/Throwable;"] = {
code = function(v,t,l,s)
::ins4::
table.insert(s,1,l[1])
::ins5::
table.insert(s,1,v.getfield(t,"java/lang/Throwable","cause",table.remove(s,1)))
::ins8::
goto ret
::ret::
return table.remove(s,1)
end,
name = "getCause",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
},
descriptor = "()Ljava/lang/Throwable;"
}
}
},
name = "java/lang/Throwable",
flags = {
private = false,
static = false,
protected = false,
public = true,
native = false
}
}
end