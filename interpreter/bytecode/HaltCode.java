package interpreter.bytecode;

import interpreter.VirtualMachine;

// ByteCode: HALT
// halt execution
public class HaltCode extends ByteCode {
    
    // nothing to initialize
    @Override
    public void init(String arg1, String arg2) {}
    
    @Override
    public void execute(VirtualMachine vm) {
        vm.popAllRunStack(); // pop all stacks
        vm.switchIsRunning(); // switch isRunning flag to off
    }
    
    @Override
    public String toString() {
        return "HALT";
    }
}
