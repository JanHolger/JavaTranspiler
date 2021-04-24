local loadbaseclasses = require('base')
local loadsystemclasses = require('system')

return function()
    local vm = {
        classes = {}
    }
    loadbaseclasses(vm)
    loadsystemclasses(vm)
    vm.getclass = function(name)
        c = vm.classes[name]
        if c == nil then
            return nil
        end
        if c.static == nil then
            c.static = {}
            vm.invoke(name, '<clinit>', '()V', {})
        end
        return c
    end
    vm.getstatic = function(class, field)
        return vm.getclass(class).static[field]
    end
    vm.setstatic = function(class, field, value)
        vm.getclass(class).static[field] = value
    end
    vm.main = function(class, ...)
        local args = {...}
        local ca = {{values = {}}}
        for _,a in ipairs(args) do
            table.insert(ca[1].values, { class="java/lang/String", value=a })
        end
        vm.invoke(class,'main','([Ljava/lang/String;)V', ca)
    end
    vm.invoke = function(class, method, descriptor, args)
        local stack = {}
        local locals = {}
        for i,v in pairs(args) do
            locals[i] = v
        end
        local c = vm.getclass(class)
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
        local rval = m.code(vm, locals, stack)
        return rval
    end
    return vm
end