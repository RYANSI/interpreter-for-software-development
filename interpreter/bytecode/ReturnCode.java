package interpreter.bytecode;

import interpreter.VirtualMachine;

// ByteCode: RETURN <funcname>
// return from the current function;
// <funcname> is used as a comment to indicate the current function
// RETURN is generated for intrinsic functions
public class ReturnCode extends ByteCode {
    
    private String funcname; // comment
    private int returnVal; // funcname
    
    @Override
    public void init(String arg1, String arg2) {
        this.funcname = arg1; // comment
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        this.returnVal = vm.peekRunStack();
        vm.popAllCurrentFrame();
        vm.popReturnAddrs();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RETURN ");
        sb.append(this.funcname);
        sb.append("\texit: ");
        sb.append(this.funcname);
        if (this.returnVal >= 0)
            sb.append(this.returnVal);
        return sb.toString();
    }
}
