
public class AllINST {
	private static String[] posibleInst=new String[23];
	public AllINST(){
		posibleInst[0]="lw";posibleInst[1]="sw";posibleInst[2]="li";posibleInst[3]="add";posibleInst[4]="addi";
		posibleInst[5]="mult";posibleInst[6]="multi";posibleInst[7]="sub";posibleInst[8]="subi";posibleInst[9]="and";
		posibleInst[10]="andi";posibleInst[11]="or";posibleInst[12]="ori";posibleInst[13]="sll";posibleInst[14]="srl";
		posibleInst[15]="sra";posibleInst[16]="slli";posibleInst[17]="srli";posibleInst[18]="srai";posibleInst[19]="beq";
		posibleInst[20]="bne";posibleInst[21]="j";posibleInst[22]="hlt";
	}
	public boolean labelOrInst(String inst){
		String[] first=inst.split(" ");
		for(int i=0;i<23;i++){
			if(first[0].toLowerCase().equals(posibleInst[i])){
				return true;
			}
		}
		return false;
	}
	public int getCycles(String inst){
		if(inst.equals("add")||inst.equals("addi")||inst.equals("sub")||inst.equals("subi")){
			return 2;
		}else if(inst.equals("mult")||inst.equals("multi")){
			return 4;
		}
		return 0;
	}
}
