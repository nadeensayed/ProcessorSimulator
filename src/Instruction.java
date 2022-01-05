public class Instruction {

    int pc;
    int instruction;
    int opcode;
    int r1;
    int r2;
    int r3;
    int valueR1;
    int valueR2;
    int valueR3;
    int shamt;
    int imm;
    int add;

    public Instruction(int p, int ins, int o, int r1, int r2, int r3, int vR1, int vR2, int vR3,int s, int i, int a){

        pc = p;
        instruction = ins;
        opcode = o;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        valueR1 = vR1;
        valueR2 = vR2;
        valueR3 = vR3;
        shamt = s;
        imm = i;
        add = a;

    }

    public String toString(){
        return "Instruction: " + instruction + " opcode: " + opcode + "   R1: " + r1 + "   R2: " + r2 + "   R3: " + r3 + "   Value R1: " + valueR1 +
                "   Value R2: " + valueR2 + "   Value R3: " + valueR3 + "   shamt: " + shamt + "   imm: " + imm + "   add " + add ;
    }

}
