package interpreter.bytecode;

import interpreter.VirtualMachine;

// ByteCode: DUMP <ON/OFF>
// turns dump mode on or off
public class DumpCode extends ByteCode {
    private String currDumpCommand;
    private String newDumpCommand;
    
    // if the argument passed is "ON", is make "change" true
    @Override
    public void init(String arg1, String arg2) {
        this.newDumpCommand = arg1;
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        this.currDumpCommand = vm.getDumpMode();
        if ( !(currDumpCommand.equals(newDumpCommand)) )
            vm.switchDumpMode();
    }
    
}
