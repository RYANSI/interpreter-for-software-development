package interpreter.bytecode;

import interpreter.VirtualMachine;

// ByteCode: ARGS n
// used prior to calling a function:
// n = #args
//          this instruction is immediately followed by the CALL
//          instruction; the function has n args so ARGS n instructs the
//          interpreter to set up a new frame n down from the top, so it
//          will include the arguments
public class ArgsCode extends ByteCode {
    
    private int n;
    
    @Override
    public void init(String arg1, String arg2) {
        this.n = Integer.parseInt(arg1);
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        vm.pushNewFrame(this.n);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ARGS ");
        sb.append(this.n);
        return sb.toString();
    }
}
