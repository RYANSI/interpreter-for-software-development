package interpreter.bytecode.bopcode;

import interpreter.VirtualMachine;

public class BopLessThan extends BopCode {
    
    @Override
    public void execute(VirtualMachine vm) {
        int op2 = vm.popRunStack();
        int op1 = vm.popRunStack();
        int isLess = 0;
        
        if (op1 < op2)
            isLess = 1;
        
        vm.pushToRunStack(isLess);
    }
}
