package interpreter;

import interpreter.bytecode.*;
import interpreter.bytecode.bopcode.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

// ByteCodeLoader reads compiled x.cod files and translates each line
// into ByteCodes. Each ByteCode is loaded into an instance of a Program
// which is returned using ByteCodeLoader.loadCodes()
public class ByteCodeLoader extends Object implements interpreter.bytecode.bopcode.BopCode.GetBop {

    private BufferedReader byteSource;
    private Program program;
    private StringTokenizer tokenizer;
    private final String DELIMS = " \t";

    public ByteCodeLoader(String file) throws IOException {
            this.byteSource = new BufferedReader(new FileReader(file));
    }

    // BopCode is an abstract class and uses a HashMap to instantiate
    // each of its subclasses. An instantiation method is defined here
    // in order to create a new instance of a specified BopCode subclass.
    @Override
    public BopCode getBop(String token) {
        return BopCode.GetBop.super.getBop(token);
    }

    public boolean hasLabel(String classname) {
        return classname.equals("CallCode")
                || classname.equals("FalseBranchCode")
                || classname.equals("GotoCode")
                || classname.equals("LabelCode");
    }
    
    /**
     * This function should read one line of source code at a time. For each
     * line it should: Tokenize string to break it into parts. Grab correct
     * class name for the given ByteCode from CodeTable Create an instance of
     * the ByteCode class name returned from code table. Parse any additional
     * arguments for the given ByteCode and send them to the newly created
     * ByteCode instance via the init function.
     *
     * @return Program, which contains ByteCode class instances read from
     * byteSource
     */
    public Program loadCodes() {
        program = new Program();
        String line;

        try {
            while ((line = this.byteSource.readLine()) != null) {

                String token, className;
                String[] args = {"", ""};
                ByteCode bytecode;

                // tokenize the current string
                this.tokenizer = new StringTokenizer(line, DELIMS);
                token = this.tokenizer.nextToken();
                className = CodeTable.getClassName(token);

                // BopCode is abstract, so instantiate differently
                if (className.equals("BopCode")) {
                    token = this.tokenizer.nextToken();
                    bytecode = getBop(token);
                    args[0] = token;
                } else {
                    // create instance of ByteCode class returned from code table
                    StringBuilder sb = new StringBuilder();
                    sb.append("interpreter.bytecode.");
                    
                    if (hasLabel(className))
                        sb.append("jumpcode.");
                    
                    sb.append(className);
                    bytecode = (ByteCode) (Class.forName(sb.toString()).newInstance());

                    // parse any additional tokens and send them to the ByteCode instance
                    int argIndex = 0;
                    while (this.tokenizer.hasMoreTokens()) {
                        token = this.tokenizer.nextToken();
                        args[argIndex] = token;
                        argIndex++;
                    }
                }
                bytecode.init(args[0], args[1]);

                // load the bytecode class instance into program
                this.program.addByteCodeInstance(bytecode);

            } // finish processing file
            this.byteSource.close();

            // store jump indices in ByteCode instances with labels
            this.program.resolveAddrs(program);

            return this.program;
            // throws IOException, ClassNotFoundException,
        //    InstantiationException, IllegalAccessException
        } catch (IOException | ClassNotFoundException
                | InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        return program;
    }
}
