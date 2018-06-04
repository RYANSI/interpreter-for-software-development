package interpreter.bytecode.bopcode;

import interpreter.VirtualMachine;

public class BopEquals extends BopCode {
    
    @Override
    public void execute(VirtualMachine vm) {
        int op2 = vm.popRunStack();
        int op1 = vm.popRunStack();
        int equalOrNot = 0;
        
        if (op1 == op2)
            equalOrNot = 1;
        
        vm.pushToRunStack(equalOrNot);
    }
}
