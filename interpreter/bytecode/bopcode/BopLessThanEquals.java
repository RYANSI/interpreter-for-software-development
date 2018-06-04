package interpreter.bytecode.bopcode;

import interpreter.VirtualMachine;

public class BopLessThanEquals extends BopCode {
    
    @Override
    public void execute(VirtualMachine vm) {
        int op2 = vm.popRunStack();
        int op1 = vm.popRunStack();
        int lessOrEqual = 0;
        
        if (op1 <= op2)
            lessOrEqual = 1;
        
        vm.pushToRunStack(lessOrEqual);
    }
}
