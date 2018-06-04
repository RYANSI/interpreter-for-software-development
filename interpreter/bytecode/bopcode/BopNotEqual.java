package interpreter.bytecode.bopcode;

import interpreter.VirtualMachine;

public class BopNotEqual extends BopCode {
    
    @Override
    public void execute(VirtualMachine vm) {
        int op2 = vm.popRunStack();
        int op1 = vm.popRunStack();
        int notEqual = 0;
        
        if (op1 != op2)
            notEqual = 1;
        
        vm.pushToRunStack(notEqual);
    }
}
