package interpreter.bytecode;

import interpreter.VirtualMachine;

// ByteCode: STORE n <id>
// pop the top of the stack;
// store value into the offset n from the start of the frame;
// <id> is used as a comment, it's the variable name where the data is stored
public class StoreCode extends ByteCode {
    
    private int n;
    private String id;
    
    @Override
    public void init(String arg1, String arg2) {
        this.n = Integer.parseInt(arg1);
        this.id = arg2;
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        vm.storeAtOffset(this.n);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("STORE ");
        sb.append(this.n);
        sb.append(" ");
        sb.append(this.id);
        sb.append("\t");
        sb.append(id);
        sb.append(" = ");
        sb.append(this.n);
        return sb.toString();
    }
}
