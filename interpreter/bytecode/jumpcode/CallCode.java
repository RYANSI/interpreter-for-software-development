package interpreter.bytecode.jumpcode;

import interpreter.VirtualMachine;

// ByteCode: CALL <funcname>
// transfer control to the indicated function
public class CallCode extends JumpCode {
    
    private int paramVal;
    
    @Override
    public void execute(VirtualMachine vm) {
        this.paramVal = vm.peekRunStack();
        vm.pushReturnAddrs();
        vm.setpc(jumpIndex - 1);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CALL ");
        sb.append(this.label); // funcname
        sb.append("\t");
        sb.append(this.label);
        sb.append("(");
        sb.append(this.paramVal);
        sb.append(")");
        return sb.toString();
    }
}
