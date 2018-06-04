package interpreter.bytecode.bopcode;

import interpreter.VirtualMachine;

public class BopGreaterThanEquals extends BopCode {
    
    @Override
    public void execute(VirtualMachine vm) {
        int op2 = vm.popRunStack();
        int op1 = vm.popRunStack();
        int greaterOrEqual = 0;
        
        if (op1 >= op2)
            greaterOrEqual = 1;
        
        vm.pushToRunStack(greaterOrEqual);
    }
}
