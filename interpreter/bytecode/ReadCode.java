package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.Scanner;

// ByteCode: READ
// read an iteger;
// prompt the user for input;
// put the value just read on top of the stack
public class ReadCode extends ByteCode {
    
    // nothing to initialize
    @Override
    public void init(String arg1, String arg2) {}
    
    @Override
    public void execute(VirtualMachine vm) {
        Scanner input = new Scanner(System.in);
        System.out.print("Input int: ");
        int readInt = input.nextInt();
        input.nextLine(); // consume newline
        
        vm.pushToRunStack(readInt);
    }
    
    @Override
    public String toString() {
        return "READ";
    }
}
