import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Processor {

    static int [] instructionMemory = new int [2048];

    static int noInst = 0;
//    static int clockCycles = 0;
    static int clk = 1;

    static int [] regFile = new int[32];
    static int PC = 0;

    static ArrayList<Integer> currentInstruction = new ArrayList<>();
    static ArrayList<Instruction> currentDecInstruction1 = new ArrayList<Instruction>();
    static ArrayList<Instruction> currentExecInstruction = new ArrayList<Instruction>();
    static ArrayList<Instruction> currentMemInstruction = new ArrayList<Instruction>();
    static ArrayList<Integer> valueR1 = new ArrayList<>();
    static ArrayList<Integer> valueR1F = new ArrayList<>();


    public static void assembler(String program) throws Exception {
        program = "src/" + program + ".txt";
        File file =new File(program);
        Scanner sc = new Scanner(file);
        String s = "";
        while (sc.hasNextLine()){
            String instruction = "";

            while (sc.hasNext()){
                s =sc.next();

                String r1;
                String r2;
                String r3;
                String t;
                int reg1;
                int reg2;
                int reg3;

                switch(s){

                    case "ADD":
                        instruction = "0000";
                        reg1 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R1
                        r1 = String.format("%05d", reg1);
                        reg2 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R2
                        r2 = String.format("%05d", reg2);
                        reg3 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R3
                        r3 = String.format("%05d", reg3);
                        instruction+=r1+r2+r3+"0000000000000";
                        break;

                    case "SUB":
                        instruction = "0001";
                        reg1 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R1
                        r1 = String.format("%05d", reg1);
                        reg2 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R2
                        r2 = String.format("%05d", reg2);
                        reg3 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R3
                        r3 = String.format("%05d", reg3);
                        instruction+=r1+r2+r3+"0000000000000";
                        break;

                    case "MULI":
                        instruction = "0010";
                        reg1 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R1
                        r1 = String.format("%05d", reg1);
                        reg2 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R2
                        r2 = String.format("%05d", reg2);
                        t = Integer.toBinaryString(Integer.parseInt(sc.next())); //IMM
                        r3 = String.format("%18s", t).replace(' ', '0');
//                        r3 = String.format("%018d", reg3);
                        instruction+=r1+r2+r3.substring(r3.length()-18);
                        break;

                    case "ADDI":
                        instruction = "0011";
                        reg1 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R1
                        r1 = String.format("%05d", reg1);
                        reg2 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R2
                        r2 = String.format("%05d", reg2);
                        t = Integer.toBinaryString(Integer.parseInt(sc.next())); //IMM
                        r3 = String.format("%18s", t).replace(' ', '0');
//                        r3 = String.format("%018d", reg3);
                        instruction+=r1+r2+r3.substring(r3.length()-18);
                        break;

                    case "BNE":
                        instruction = "0100";
                        reg1 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R1
                        r1 = String.format("%05d", reg1);
                        reg2 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R2
                        r2 = String.format("%05d", reg2);
                        t = Integer.toBinaryString(Integer.parseInt(sc.next())); //IMM
                        r3 = String.format("%18s", t).replace(' ', '0');
//                        r3 = String.format("%018d", reg3);
                        instruction+=r1+r2+r3.substring(r3.length()-18);
                        break;

                    case "ANDI":
                        instruction = "0101";
                        reg1 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R1
                        r1 = String.format("%05d", reg1);
                        reg2 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R2
                        r2 = String.format("%05d", reg2);
                        t = Integer.toBinaryString(Integer.parseInt(sc.next())); //IMM
                        r3 = String.format("%18s", t).replace(' ', '0');
//                        r3 = String.format("%018d", reg3);
                        instruction+=r1+r2+r3.substring(r3.length()-18);
                        break;

                    case "ORI":
                        instruction = "0110";
                        reg1 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R1
                        r1 = String.format("%05d", reg1);
                        reg2 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R2
                        r2 = String.format("%05d", reg2);
                        t = Integer.toBinaryString(Integer.parseInt(sc.next())); //IMM
                        r3 = String.format("%18s", t).replace(' ', '0');
//                        r3 = String.format("%018d", reg3);
                        instruction+=r1+r2+r3.substring(r3.length()-18);
                        break;

                    case "J":
                        instruction = "0111";
                        t = Integer.toBinaryString(Integer.parseInt(sc.next())); //ADD
                        r1 = String.format("%28s", t).replace(' ', '0');
//                        r1 = String.format("%028d", reg1);
                        instruction+=r1;
                        break;

                    case "SLL":
                        instruction = "1000";
                        reg1 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R1
                        r1 = String.format("%05d", reg1);
                        reg2 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R2
                        r2 = String.format("%05d", reg2);
                        t = Integer.toBinaryString(Integer.parseInt(sc.next())); //SHAMT
                        r3 = String.format("%13s", t).replace(' ', '0');
//                        r3 = String.format("%013d", reg3);
                        instruction+=r1+r2+"00000"+r3;
                        break;

                    case "SRL":
                        instruction = "1001";
                        reg1 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R1
                        r1 = String.format("%05d", reg1);
                        reg2 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R2
                        r2 = String.format("%05d", reg2);
                        t = Integer.toBinaryString(Integer.parseInt(sc.next())); //SHAMT
                        r3 = String.format("%13s", t).replace(' ', '0');
//                        r3 = String.format("%013d", reg3);
                        instruction+=r1+r2+"00000"+r3;
                        break;

                    case "LW":
                        instruction = "1010";
                        reg1 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R1
                        r1 = String.format("%05d", reg1);
                        reg2 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R2
                        r2 = String.format("%05d", reg2);
                        t = Integer.toBinaryString(Integer.parseInt(sc.next())); //IMM
                        r3 = String.format("%18s", t).replace(' ', '0');
//                        r3 = String.format("%018d", reg3);
                        instruction+=r1+r2+r3.substring(r3.length()-18);
                        break;

                    case "SW":
                        instruction = "1011";
                        reg1 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R1
                        r1 = String.format("%05d", reg1);
                        reg2 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt((sc.next()).substring(1)))); //R2
                        r2 = String.format("%05d", reg2);
                        t = Integer.toBinaryString(Integer.parseInt(sc.next())); //IMM
                        r3 = String.format("%18s", t).replace(' ', '0');
//                        r3 = String.format("%018d", reg3);
                        instruction+=r1+r2+r3.substring(r3.length()-18);
                        break;
                }

                //ADD INST TO MEMORY
                Long x = (Long.parseLong(instruction, 2));
                instructionMemory[noInst] = x.intValue();

                //INCREMENT INST
                noInst++;

            }

        }

//        clockCycles = 7 + ((noInst - 1) * 2);
    }


    public static void process() throws Exception {

        int count = 0;

        while(PC < noInst || count < 7){

            if(clk%2==1 && !valueR1F.isEmpty()) { //7 WB
                writeBack();
            }

            if(clk%2==1 && !currentExecInstruction.isEmpty()) { //5 EXEC2
                int n = currentExecInstruction.get(0).opcode;
                execute();
                if(n == 7 || (n == 4 && (currentExecInstruction.get(0).valueR1 != currentExecInstruction.get(0).valueR2))) {
                    currentInstruction.clear();
                    currentDecInstruction1.clear();
                    currentMemInstruction.add(currentExecInstruction.remove(0));
                    clk++;
                    System.out.println("------------------------");
                    System.out.println();
                    memory();
                    valueR1F.add(valueR1.remove(0));
                    clk++;
                    System.out.println("------------------------");
                    System.out.println();
                    writeBack();
                    continue;
                }
                currentMemInstruction.add(currentExecInstruction.remove(0));
            }

            if(clk%2==1 && !currentInstruction.isEmpty()) { //3 DEC2
                decode();
                currentInstruction.clear(); //CLEAR INS
                currentExecInstruction.add(currentDecInstruction1.remove(0));
            }

            if(clk%2==1 && PC < noInst) { //1 FETCH
                fetch();
            }


            if(clk%2==0 && !currentMemInstruction.isEmpty()) { //6 MEM
                memory();
                valueR1F.add(valueR1.remove(0));
            }

            if(clk%2==0 && !currentExecInstruction.isEmpty()) { //4 EXEC1
                execute();
            }

            if(clk%2==0 && !currentInstruction.isEmpty()) { //2 DEC1
                decode();
            }

            //FOR JUMPS
            if(PC >= noInst)
                count++;


            clk ++;
            System.out.println("------------------------");
            System.out.println();
        }


    }


    public static void fetch(){

        int ins = instructionMemory[PC++];
        currentInstruction.add(ins);
        currentInstruction.add(PC - 1);

        System.out.println("In clock cycle " + clk + ", Fetch stage for instruction " + ins);
        System.out.println();


    }


    public static void decode(){

        int instruction = currentInstruction.get(0);
        int pc = currentInstruction.get(1);

        int opcode = (instruction & 0b11110000000000000000000000000000) >> 28; //31:28
        int r1 = (instruction & 0b00001111100000000000000000000000) >> 23; //27:23
        int r2 = (instruction & 0b00000000011111000000000000000000) >> 18; //22:18
        int r3 = (instruction & 0b00000000000000111110000000000000) >> 13; //17:13
        int shamt = (instruction & 0b00000000000000000001111111111111); //12:0
        int imm = ((instruction & 0b00000000000000111111111111111111) << 14 ) >> 14; //17:0
        int add = (instruction & 0b00001111111111111111111111111111); //27:0

        Instruction i = new Instruction(pc, instruction, opcode, r1, r2, r3, regFile[r1], regFile[r2], regFile[r3], shamt, imm, add);

        if(clk%2==1)
            currentDecInstruction1.add(i);

        System.out.println("In clock cycle " + clk + ", Decode stage for instruction " + instruction);
        System.out.println(i);
        System.out.println();

    }


    public static void execute() throws Exception {

        Instruction i = currentExecInstruction.get(0);

        if(clk%2==1) {

            if (i.opcode == 0)
                valueR1.add(i.valueR2 + i.valueR3);

            else if (i.opcode == 1)
                valueR1.add(i.valueR2 - i.valueR3);

            else if (i.opcode == 2)
                valueR1.add(i.valueR2 * i.imm);

            else if (i.opcode == 3)
                valueR1.add(i.valueR2 + i.imm);

            else if (i.opcode == 4) { //BNE
                if (i.valueR1 != i.valueR2) {
                    PC = i.pc + 1 + i.imm;
                    if (PC <= i.pc) throw new Exception("Infinite Loop");
                }
                valueR1.add(-1);
            }

            else if (i.opcode == 5)
                valueR1.add(i.valueR2 & i.imm);

            else if (i.opcode == 6)
                valueR1.add(i.valueR2 | i.imm);

            else if (i.opcode == 7) { //J
                String x = ((i.pc) & 0b11110000000000000000000000000000) + "" + i.add;
                PC = Integer.parseInt(x);
                if (PC <= i.pc) throw new Exception("Infinite Loop");
                valueR1.add(-1);
            }

            else if (i.opcode == -8) //SLL
                valueR1.add(i.valueR2 << i.shamt);

            else if (i.opcode == -7) //SRL
                valueR1.add(i.valueR2 >>> i.shamt);
        }

        System.out.println("In clock cycle " + clk + ", Execute stage for instruction " + i.instruction);
        System.out.println(i);
        System.out.println();


    }


    public static void memory(){

        Instruction i = currentMemInstruction.get(0);
        boolean flag = false;

        if(i.opcode == -6) //LW
            valueR1.add(instructionMemory[i.valueR2 + i.imm]);


        else if(i.opcode == -5) { //SW
            instructionMemory[i.valueR2 + i.imm] = regFile[i.r1];
            valueR1.add(-1);
            flag = true;
        }


        System.out.println("In clock cycle " + clk + ", Memory stage for instruction " + i.instruction);
        if(flag) System.out.println("M[" + (i.valueR2 + i.imm) + "] = " + regFile[i.r1]);
        System.out.println(i);
        System.out.println();

    }


    public static void writeBack(){

        Instruction i = currentMemInstruction.remove(0);
        boolean flag = false;

        if(i.r1!= 0 && !(i.opcode == 4 || i.opcode == 7 || i.opcode == -5)) {
            regFile[i.r1] = valueR1F.remove(0);
            flag = true;
        }

        else
            valueR1F.remove(0);

        System.out.println("In clock cycle " + clk + ", WriteBack stage for instruction " + i.instruction);
        if(flag) System.out.println("R" + i.r1 + ": " + regFile[i.r1]);
        System.out.println(i);
        System.out.println();

    }


    public static void main(String[] args) throws Exception {

        assembler("Test");
        process();

        System.out.println();
        System.out.println("Memory:");
        System.out.println(Arrays.toString(instructionMemory));
        System.out.println();
        System.out.println("Registers:");
        System.out.println(Arrays.toString(regFile));


    }

}
