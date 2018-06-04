package interpreter.bytecode;

import interpreter.VirtualMachine;

// ByteCode: LIT n - load the literal value n
// ByteCode: LIT 0 i - this form of the Lit was generated to load 0
//                      on the stack in order to initialize the variable
//                      i to 0 and reserve space on the runtime stack for i
public class LitCode extends ByteCode {
    
    private int n;
    private String var;
    
    @Override
    public void init(String arg1, String arg2) {
        this.n = Integer.parseInt(arg1);
        this.var = arg2;
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        vm.pushToRunStack(this.n);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LIT ");
        sb.append(this.n);
        sb.append(" ");
        sb.append(this.var);
        return sb.toString();
    }
}
