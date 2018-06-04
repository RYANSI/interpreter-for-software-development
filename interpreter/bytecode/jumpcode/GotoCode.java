package interpreter.bytecode.jumpcode;

import interpreter.VirtualMachine;

// ByteCode: GOTO <label>
public class GotoCode extends JumpCode {
    
    @Override
    public void execute(VirtualMachine vm) {
        vm.setpc(jumpIndex - 1);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GOTO ");
        sb.append(this.label);
        return sb.toString();
    }
}
