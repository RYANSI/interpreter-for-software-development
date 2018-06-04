package interpreter.bytecode;

import interpreter.VirtualMachine;

// Each ByteCode class has its own fields.
    public abstract class ByteCode {
    
    public abstract void init(String arg1, String arg2);
    public abstract void execute(VirtualMachine vm);
}
