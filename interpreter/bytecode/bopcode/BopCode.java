package interpreter.bytecode.bopcode;

import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import java.util.HashMap;

// BOP <binary op>
// pop top 2 levels of the stack and perform the indicated operation
// operations are "+", "-", "/", "*",
//                "==", "!=", "<=", ">", ">=", "<", "|", "&"
// "|" and "&" are logical operators, not bit operators
// lower level is the first operand: e.g. <second-level> + <top-level>
public abstract class BopCode extends ByteCode {
    
    protected String binaryOp;
    private static final HashMap<String, BopCode> BINARYOPS;
    
    static {
        BINARYOPS = new HashMap<>();
        BINARYOPS.put("+", new BopAdd());
        BINARYOPS.put("-", new BopSubtract());
        BINARYOPS.put("*", new BopMultiply());
        BINARYOPS.put("/", new BopDivide());
        BINARYOPS.put("==", new BopEquals());
        BINARYOPS.put("!=", new BopNotEqual());
        BINARYOPS.put("<", new BopLessThan());
        BINARYOPS.put("<=", new BopLessThanEquals());
        BINARYOPS.put(">", new BopGreaterThan());
        BINARYOPS.put(">=", new BopGreaterThanEquals());
        BINARYOPS.put("|", new LogicalOr());
        BINARYOPS.put("&", new LogicalAnd());
    }
    
    // interface used to instantiate BopCode sublcass in ByteCodeLoader
    public interface GetBop {
        default public BopCode getBop(String token) {
            BopCode newOp = BINARYOPS.get( token );
            return newOp;
        }
    }
    
    @Override
    public void init(String arg1, String arg2) {
        this.binaryOp = arg1;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BOP ");
        sb.append(binaryOp);
        return sb.toString();
    }
    
    @Override
    public abstract void execute(VirtualMachine vm);
    
}
