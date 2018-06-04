package interpreter.bytecode.bopcode;

import interpreter.VirtualMachine;

public class LogicalAnd extends BopCode {
    
    @Override
    public void execute(VirtualMachine vm) {
        int op2 = vm.popRunStack();
        int op1 = vm.popRunStack();
        
        boolean op2b = (op2 >= 1);
        boolean op1b = (op1 >= 1);
        
        boolean result = op2b & op1b;
        int resultInt = result ? 1 : 0;
        
        vm.pushToRunStack(resultInt);
    }
}
