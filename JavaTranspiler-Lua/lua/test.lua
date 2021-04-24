createvm = require('vm')
loaduser = require('user')

local vm = createvm()
loaduser(vm)

vm.main('Test', 'Hallo')