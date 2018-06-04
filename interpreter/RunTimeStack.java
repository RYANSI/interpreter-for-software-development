package interpreter;

import java.util.ArrayList;
import java.util.Stack;

// The RunTimeStack has a runTimeStack and a framePointer stack.
// runTimeStack: holds integer values used throughout the duration of the program execution
// framePointer stack: holds the indicies of the start of each frame
public class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        //Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    public void dump() {
        Stack<Integer> tempFrames = new Stack<>();
        int lastFramePointer = this.framePointer.size() - 1;
        
        // set up a copy of framePointer
        for(int i = lastFramePointer; i > 0; i--) {
            tempFrames.add(this.framePointer.get(i));
        }
        
        // print each frame of the runTimeStack using sublists
        // keep popping the tempFramePointer stack to get the end of the current sublist
        int i = 0;
        
        // prints all frames except the last frame
        if (tempFrames.size() > 0) {
            
            while( !(tempFrames.empty()) ) {
                int lastFrame = tempFrames.pop();
                System.out.print(this.runTimeStack.subList(i, lastFrame));
                System.out.print(" ");
                i = lastFrame;
            }
        }
        
        // prints the last frame
        // OR
        // prints the only frame if there's only one frame
        if (i < this.runTimeStack.size()) {
            System.out.print(this.runTimeStack.subList(i, this.runTimeStack.size()));
        }
        
        // special case if the runTimeStack is empty
        if (i == 0 && this.runTimeStack.isEmpty())
            System.out.print("[]");

        System.out.println();
    } // end dump()
    
    public boolean isEmpty() {
        return runTimeStack.isEmpty();
    }
    
    public int peek() {
        if ( !(runTimeStack.isEmpty()) ) {
            int lastIndex = runTimeStack.size() - 1;
            return runTimeStack.get(lastIndex);
        }
        // if runTimeStack is empty, return -1 for error
        // FUTURE FIX: -1 might be a possible value in the stack,
        //              so returning -1 might not provide to expected behavior
        return -1;
    }
    
    public int pop() {
        if ( !(runTimeStack.isEmpty()) ) {
            int lastIndex = runTimeStack.size() - 1;
            int topValue = runTimeStack.get(lastIndex);
            runTimeStack.remove(lastIndex);
            return topValue;
        }
        // if runTimeStack is empty, return -1 for error
        // FUTURE FIX: -1 might be a possible value in the stack,
        //              so returning -1 might not provide to expected behavior
        return -1;
    }
    
    public void popAll() {
        runTimeStack.clear();
    }
    
    public int push(int i) {
        runTimeStack.add(i);
        return i;
    }
    
    public void newFrameAt(int offset) {
        // the 0 index of the new frame will be
        // the index of the top of the stack minus the offset
        // i.e. newFrameIndex = topOfStackIndex - offset
        int currentSize = runTimeStack.size();
        
        // debug text
        if (currentSize < offset) {
            System.out.println("New frame cannot be created with the specifed offset.");
            System.out.println("runTimeStack size: " + currentSize);
            System.out.println("offset size: " + offset);
        // execute if no problems
        } else {
            framePointer.add(currentSize - offset);
        }
    } // end newFrameAt()
    
    public void popFrame() {
        
        int topValue;
        
        int lastIndex = runTimeStack.size() - 1;
        int currentFramePointer = framePointer.peek();
        
        // temporarily store the value at the top of the stack
        if( !(runTimeStack.isEmpty()) ) {
            topValue = runTimeStack.get(lastIndex);
            
            // remove items from the top of the stack
            // until the bottom of the active frame is removed
            while(lastIndex >= currentFramePointer) {
                runTimeStack.remove(lastIndex);
                lastIndex--;
            }
            
            // push the temporarily stored value to the top of the stack
            runTimeStack.add(topValue);
            framePointer.pop();
            
        } else {
            System.out.println("runTimeStack is empty");
        }
    } // end popFrame()
    
    public int store(int offset) {
        
        if ( !(runTimeStack.isEmpty()) ) {
            int currentSize = runTimeStack.size();
            int currentFramePointer = framePointer.peek();
            
            // check if the requested index is within bounds of the current stack
            if ( (offset + currentFramePointer >= 0)
                    && (offset + currentFramePointer  <= currentSize - 1) ) {
                
                int poppedValue = runTimeStack.get(currentSize - 1);
                runTimeStack.remove(currentSize - 1);
                currentSize--;
            
                if (offset == currentSize) {
                    runTimeStack.add(currentFramePointer + offset);
                } else {
                    runTimeStack.set(currentFramePointer + offset, poppedValue);
                }
                
                return poppedValue;
            } // end boundary check
            
        } // end store() body
        
        // FUTURE FIX: -1 might be a possible value in the stack,
        //              so returning -1 might not provide to expected behavior
        return -1;
    } // end store()
    
    public int load(int offset) {
        // push value from requested index to the top of the stack
        
        if ( !(runTimeStack.isEmpty()) ) {
            int lastIndex = runTimeStack.size() - 1;
            int currentFramePointer = framePointer.peek();
            int loadValue = -1;
            
            // check if the requested index is within the bounds of the stack
            if ( (offset + currentFramePointer >= 0 )
                    && (offset + currentFramePointer <= lastIndex) ) {
                
                loadValue = runTimeStack.get(currentFramePointer + offset);
                runTimeStack.add(loadValue);
            }
            
            return loadValue;
        } // end load() body
        
        // FUTURE FIX: -1 might be a possible value in the stack,
        //              so returning -1 might not provide to expected behavior
        return -1;
    } // end load()
    
    public Integer push(Integer i) {
        return i;
    }
}
