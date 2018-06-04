package interpreter.bytecode.jumpcode;

import interpreter.VirtualMachine;

// FALSEBRANCH <label>
// pop the top of the stack;
// if it's false (0) then branch to <label>
// else execute the next bytecode
public class FalseBranchCode extends JumpCode {
    
    @Override
    public void execute(VirtualMachine vm) {
        int value = vm.popRunStack();
        
        if (value == 0) {
            vm.setpc(jumpIndex - 1);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FALSEBRANCH ");
        sb.append(this.label);
        return sb.toString();
    }
}
