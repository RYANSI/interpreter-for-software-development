package interpreter.bytecode;

import interpreter.VirtualMachine;

// ByteCode: POP n
// pop top n levels of runtime stack
public class PopCode extends ByteCode {
    
    private int n;
    
    @Override
    public void init(String arg1, String arg2) {
        this.n = Integer.parseInt(arg1);
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        while(this.n > 0) {
            vm.popRunStack();
            n--;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("POP ");
        sb.append(this.n);
        return sb.toString();
    }
}
