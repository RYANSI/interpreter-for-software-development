package interpreter.bytecode;

import interpreter.VirtualMachine;

// ByteCode: WRITE
// write the value on top of the stack to output;
// leave the value on top of the stack
public class WriteCode extends ByteCode {
    
    // nothing to initialize
    @Override
    public void init(String arg1, String arg2) {}
    
    @Override
    public void execute(VirtualMachine vm) {
        System.out.println(vm.peekRunStack());
    }
    
    @Override
    public String toString() {
        return "WRITE";
    }
}
