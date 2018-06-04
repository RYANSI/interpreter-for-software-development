package interpreter.bytecode.jumpcode;

import interpreter.VirtualMachine;

// ByteCode: LABEL <label>
// target for branches;
// (see FALSEBRANCH, GOTO)
public class LabelCode extends JumpCode {
    
    @Override
    public void init(String arg1, String arg2) {
        this.label = arg1;
    }
    
    // do nothing
    @Override
    public void execute(VirtualMachine vm) {} 
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LABEL ");
        sb.append(this.label);
        return sb.toString();
    }
}
