package interpreter;

import interpreter.bytecode.jumpcode.LabelCode;
import interpreter.bytecode.*;
import interpreter.bytecode.jumpcode.*;

import java.util.ArrayList;

// The Program class stores an ArrayList of ByteCodes. 
public class Program {

    private ArrayList<ByteCode> program;

    public Program() {
        this.program = new ArrayList<>();
    }

    protected ByteCode getCode(int pc) {
        return this.program.get(pc);
    }

    public void addByteCodeInstance(ByteCode bytecode) {
        this.program.add(bytecode);
    }
    
    public void replaceByteCodeInstance(ByteCode bytecode, int pc) {
        this.program.set(pc, bytecode);
    }
    
    public int getSize() {
        return this.program.size();
    }
    
    // LabelCodes need their index (from the ArrayList) store in a HashMap
    public boolean isLabel(ByteCode bytecode) {
        return (bytecode instanceof LabelCode);
    }
    
    // JumpCodes need their jump indices replaced
    public boolean needsJumpIndex(ByteCode bytecode) {
        return (bytecode instanceof JumpCode);
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     * HINT: make note what type of data-stucture bytecodes are stored in.
     *
     * @param program Program object that holds a list of ByteCodes
     */
    public void resolveAddrs(Program program) {
        int index = 0;
        int programSize = program.getSize();
        
        while(index < programSize) {
            ByteCode outerbc = program.getCode(index);
            
            // check if the bytecode has a label
            if ( needsJumpIndex(outerbc) ) {
                
                // iterate through the program until the corresponding label is found
                int i = 0;
                String labelCopy = ((JumpCode)outerbc).getLabel();
                
                while(i < programSize) {
                    ByteCode innerbc = program.getCode(i);
                    
                    if ( isLabel(innerbc) ) {
                        String currentLabel = ((JumpCode)innerbc).getLabel();
                        
                        if (currentLabel.equals(labelCopy)) {
                            ((JumpCode)outerbc).setJumpIndex(i);
                            break;
                        }
                    }
                    
                    i++;
                } // end inner iteration
                
            } // end label check
            
            index++;
        } // full program iteration
    } // end resolveAddrs()

}
