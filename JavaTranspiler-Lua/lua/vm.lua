local loadbaseclasses = require('base')
local loadsystemclasses = require('system')

return function()
    local vm = {
        classes = {},
        next_thread_id = 1
    }
    loadbaseclasses(vm)
    loadsystemclasses(vm)
    vm.newthread = function(name)
        vm.next_thread_id = vm.next_thread_id + 1
        if name == nil then
            name = "Thread #"..(vm.next_thread_id - 1)
        end
        return {
            vm = vm,
            name = name,
            id = vm.next_thread_id - 1,
            callstack = {}
        }
    end
    vm.getclass = function(thread, name)
        c = vm.classes[name]
        if c == nil then
            return nil
        end
        if c.static == nil then
            c.static = {}
            vm.invoke(thread, name, '<clinit>', '()V', {})
        end
        return c
    end
    vm.getstatic = function(thread, class, field)
        local c = vm.getclass(thread, class)
        if c == nil then
            -- Exception
            return
        end
        return c.static[field]
    end
    vm.setstatic = function(thread, class, field, value)
        local c = vm.getclass(thread, class)
        if c == nil then
            -- Exception
            return
        end
        c.static[field] = value
    end
    vm.getfield = function(thread, class, field, instance)
        return instance.fields[field]
    end
    vm.putfield = function(thread, class, field, instance, value)
        instance.fields[field] = value
    end
    vm.instanceof = function(thread, value, type)
        local isi = 0
        if value ~= nil then
            if value.type == type then
                isi = 1
            else
            end
        end
        return {
            type = "Z",
            value = isi
        }
    end
    vm.newstring = function(value)
        return {
            type = 'Ljava/lang/String;',
            value = value
        }
    end
    vm.main = function(class, ...)
        local thread = vm.newthread("Main")
        local args = {...}
        local ca = { vm.newarray(thread,'Ljava/lang/String;',#args) }
        for _,a in ipairs(args) do
            table.insert(ca[1].values, { type="Ljava/lang/String;", value=a })
        end
        vm.invoke(thread,class,'main','([Ljava/lang/String;)V', ca)
        if thread.exception ~= nil then
            print(thread.exception.type)
            print(thread.exception.fields.message.value)
        end
    end
    vm.newarray = function(thread, class, length)
        return { type="["..class, length = length, values = {}}
    end
    vm.invoke = function(thread, class, method, descriptor, args)
        local stack = {}
        local locals = {}
        for i,v in pairs(args) do
            locals[i] = v
        end
        local c = vm.getclass(thread, class)
        if c == nil then
            -- Exception
            return nil
        end
        local om = c.methods[method]
        if om == nil then
            -- Exception
            return nil
        end
        local m = om[descriptor]
        if m == nil then
            -- Exception
            return nil
        end
        local rval = m.code(vm, thread, locals, stack)
        return rval
    end
    return vm
end