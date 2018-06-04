package interpreter;

import interpreter.bytecode.*;

import java.util.Stack;

// The VirtualMachine holds the program itself, holds the program's state,
// and updates the program's state when requested. It executes each ByteCode
// via the Interpreter.
public class VirtualMachine {

    private RunTimeStack runStack;
    private Stack<Integer> returnAddrs;
    private Program program;
    private int pc;
    private boolean isRunning;
    private boolean dumpMode;

    protected VirtualMachine(Program program) {
        this.program = program;
    }

    public void setpc(int newpc) {this.pc = newpc;}
    public void switchIsRunning() {this.isRunning = !this.isRunning;}
    public void switchDumpMode() {this.dumpMode = !this.dumpMode;}
    public String getDumpMode() {
        String currDumpMode = "OFF";
        if (dumpMode)
            currDumpMode = "ON";
        return currDumpMode;
    }
    
    // methods for returnAddrs
    public void pushReturnAddrs() {this.returnAddrs.push(this.pc);}
    public void popReturnAddrs() {this.pc = this.returnAddrs.pop();}
    
    // methods for RunTimeStack
    public int  peekRunStack() {return this.runStack.peek();}
    public void pushToRunStack(int n) {runStack.push(n);}
    public int  popRunStack() {return this.runStack.pop();}
    public void popAllRunStack() { this.runStack.popAll();}
    public void pushNewFrame(int offset) {runStack.newFrameAt(offset);}
    public void popAllCurrentFrame() {this.runStack.popFrame();}
    
    public void storeAtOffset(int offset) {runStack.store(offset);}
    public void loadFromOffset(int offset) {runStack.load(offset);}
    
    // FIXME: tab out and print function along with parameter/s
    // - if the function is exiting, print "exit <function name>: <return value>"
    public void printDump(ByteCode code) {
        if( !(code instanceof DumpCode) ) {
            System.out.println(code.toString());
            runStack.dump();
        }
    }

    public void executeProgram() {
        
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<>();
        isRunning = true;
        dumpMode = false;
        
        while(isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            
            if (dumpMode)
                printDump(code);
            
            pc++;
        } // end run loop
    } // end executeProgram()
}
