package interpreter.bytecode;

import interpreter.VirtualMachine;

// ByteCode: LOAD n <id>
// push the value in the slot which is offset n from
//   the start of the frame onto the top of the stack;
// <id> is used as a comment, it's the
//   variable name from which the data is loaded
public class LoadCode extends ByteCode {
    
    private int n;
    private String id;
    
    @Override
    public void init(String arg1, String arg2) {
        this.n = Integer.parseInt(arg1);
        this.id = arg2;
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        vm.loadFromOffset(this.n);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LOAD ");
        sb.append(this.n);
        sb.append(" ");
        sb.append(this.id);
        return sb.toString();
    }
}
