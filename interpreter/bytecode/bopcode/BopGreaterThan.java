package interpreter.bytecode.bopcode;

import interpreter.VirtualMachine;

public class BopGreaterThan extends BopCode {
    
    @Override
    public void execute(VirtualMachine vm) {
        int op2 = vm.popRunStack();
        int op1 = vm.popRunStack();
        int isGreater = 0;
        
        if (op1 > op2)
            isGreater = 1;
        
        vm.pushToRunStack(isGreater);
    }
}
