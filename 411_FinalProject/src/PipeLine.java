import java.io.PrintWriter;
import java.util.ArrayList;
public class PipeLine {
	private static ArrayList<Register> register=new ArrayList<Register>();
	private static int dataReady;
	private boolean stall=false;
	private int stallTime=0;
	private static int whichInstruction=0;
	//prints the header in the file
	public void printHeader(PrintWriter printWriter){
		printWriter.println("Cycle Number for Each Stage           IF       ID         EX4       MEM          WB");
	}
	//sets the stall 
	public boolean stall(int PC,String reg2, String reg3){
		Register newReg1=null,newReg2=null;
		if(reg2!=null){
			if(reg2.contains("r")){
				newReg1=new Register(reg2,0,0,0);
			}
		}
		if(reg3!=null){
			if(reg3.contains("r")){
				newReg2=new Register(reg3,0,0,0);
			}
		}
		if(newReg1!=null){
			for(int i=0;i<register.size();i++){
				if(newReg1.getReg().equals(register.get(i).getReg())){
					newReg1=register.get(i);
					break;
				}
			}
		}
		if(newReg2!=null){
			for(int i=0;i<register.size();i++){
				if(newReg2.getReg().equals(register.get(i).getReg())){
					newReg2=register.get(i);
					break;
				}
			}
		}
		if(newReg2!=null&&newReg1!=null){
			if(PC<=newReg2.getTime()||PC<=newReg1.getTime()){
				while(stallTime<=newReg2.getTime()-(PC)){
					stallTime++;
				}
				while(stallTime<=newReg1.getTime()-(PC)){
					stallTime++;
				}
				return true;
			}
		}
		return false;
	}
	//returns the stall time
	public int getStallTime(){
		return stallTime;
	}
	//gets the register
	public Register getReg(String reg1){
		Register newReg=null;
		@SuppressWarnings("unused")
		int offset=0;
		if(reg1.contains("(")&&reg1.contains("r")){
			String reg="";
			String off="";
			int i=0;
			while(!reg1.substring(i,i+1).equals("(")){
				off=off+reg1.substring(i,i+1);
				i++;
			}
			offset=Integer.parseInt(off);
			i++;
			while(!reg1.substring(i,i+1).equals(")")){
				reg=reg+reg1.substring(i,i+1);
				i++;
			}
			newReg=new Register(reg,0,0,0);
		}else{
			newReg=new Register(reg1,0,0,0);
		}
		for(int k=0;k<register.size();k++){
			if(register.get(k).getReg().equals(newReg.getReg())){
				return register.get(k);
			}
		}
		return null;
	}
	//returns the list of registers 
	public ArrayList<Register> getReg(){
		return register;
	}
	//tells if there will be a stall 
	public boolean returnStall(){
		return stall;
	}
	//returns which instruction the code is looking at 
	public int whichIC(){
		return whichInstruction;
	}
	//prints the label 
	public void printLabel(String str,PrintWriter printWriter){
		printWriter.print(str);
	}
	//prints the instruction
	public void printInstruction(String str, String reg1, String reg2, String reg3,PrintWriter printWriter){
		if(!str.equals("hlt")&&!str.equals("j")){
			if(reg3!=null){
				if(!reg3.equals("#")){
					//System.out.print(str+" "+reg1+" "+reg2+" "+reg3+"                   ");
					printWriter.print(str+" "+reg1+" "+reg2+" "+reg3+"                   ");
				}else{
					//System.out.print(str+" "+reg1+" "+reg2+"                        ");
					printWriter.print(str+" "+reg1+" "+reg2+"                        ");
				}
			}else{
				//System.out.print(str+" "+reg1+" "+reg2+"                        ");
				printWriter.print(str+" "+reg1+" "+reg2+"                        ");
			}
		}else if(str.equals("j")){
			//System.out.print(str+" "+reg1);
			printWriter.print(str+" "+reg1);
		}else{
			//System.out.print(str);
			printWriter.print(str);
		}

	}	
	//prints the time of EX4 MEM and WB
	public void printDiffTimes(int IC, PrintWriter printWriter){
		//System.out.print(IC+"           ");
		printWriter.print(IC+"            ");
	}
	//sets when the data will be ready 
	public void setDataReady(int dR){
		dataReady=dR;
	}
	//runs the instruction 
	public void instruction(String command,String reg1,String reg2,String reg3, ArrayList<Mem> data,ArrayList<String[]>commandList,int PC){
		try{
			Register newReg=null,newReg1=null,newReg2=null;
			int offset=0;
			if(reg1.contains("r")){
				newReg=new Register(reg1,0,0,0);
				if(reg2!=null){
					if(reg2.contains("(")&&reg2.contains("r")){
						String reg="";
						String off="";
						int i=0;
						while(!reg2.substring(i,i+1).equals("(")){
							off=off+reg2.substring(i,i+1);
							i++;
						}
						offset=Integer.parseInt(off);
						i++;
						while(!reg2.substring(i,i+1).equals(")")){
							reg=reg+reg2.substring(i,i+1);
							i++;
						}
						newReg1=new Register(reg,0,0,0);
					}else if(reg2.contains("r")){
						newReg1=new Register(reg2,0,0,0);
					}if(reg3!=null){
						if(reg3.contains("r")){
							newReg2=new Register(reg3,0,0,0);
						}
					}
				}
			}
			if(command.equals("li")){
				int value=0;
				String dumm="";
				if(reg2.contains("-")){
					if(reg2.contains("h")){
						dumm=reg2.substring(1, reg2.length()-1);
						value=Integer.parseInt(dumm, 16);
					}else if(reg2.contains("b")){
						dumm=reg2.substring(1, reg2.length()-1);
						value=Integer.parseInt(dumm, 2);
					}else{
						dumm=reg2.substring(1, reg2.length());
						value=Integer.parseInt(dumm);
					}
					value=value*-1;
				}else{
					if(reg2.contains("h")){
						dumm=reg2.substring(0, reg2.length()-1);
						value=Integer.parseInt(dumm, 16);
					}else if(reg2.contains("b")){
						dumm=reg2.substring(0, reg2.length()-1);
						value=Integer.parseInt(dumm, 2);
					}else{
						value=Integer.parseInt(reg2);
					}
				}
				newReg.set(value);
				newReg.setTime(dataReady+PC);
				int i=0;
				boolean found=false;
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}else if(command.equals("lw")){
				for(int i=0;i<register.size();i++){
					if(newReg1.getReg().equals(register.get(i).getReg())){
						newReg1=register.get(i);
						break;
					}
				}
				int i=0;
				newReg1.setMemLocation(newReg1.getValue());
				while(data.get(i).address!=newReg1.getValue()){
					i++;
				}
				byte[] dataVal=data.get(i).value;
				String word=new String(dataVal);
				long value=Long.parseLong(word,2);
				value=value+offset;
				newReg.set(value);
				boolean found=false;
				i=0;
				newReg.setTime(dataReady+PC+2);
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}else if(command.equals("j")){
				for(int i=0;i<commandList.size();i++){
					if(commandList.get(i)[0].equals(reg1+":")){
						whichInstruction=i;
						break;
					}
				}
			}else if(command.equals("mult")){
				for(int i=0;i<register.size();i++){
					if(newReg1.getReg().equals(register.get(i).getReg())){
						newReg1=register.get(i);
						break;
					}
				}
				for(int i=0;i<register.size();i++){
					if(newReg2.getReg().equals(register.get(i).getReg())){
						newReg2=register.get(i);
						break;
					}
				}
				newReg.multReg(newReg1, newReg2);
				newReg.setTime(dataReady+PC);
				boolean found=false;
				int i=0;
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}else if(command.equals("add")){
				for(int i=0;i<register.size();i++){
					if(newReg1.getReg().equals(register.get(i).getReg())){
						newReg1=register.get(i);
						break;
					}
				}
				for(int i=0;i<register.size();i++){
					if(newReg2.getReg().equals(register.get(i).getReg())){
						newReg2=register.get(i);
						break;
					}
				}
				newReg.addReg(newReg1, newReg2);
				newReg.setTime(dataReady+PC);
				boolean found=false;
				int i=0;
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}else if(command.equals("addi")){
				for(int i=0;i<register.size();i++){
					if(newReg1.getReg().equals(register.get(i).getReg())){
						newReg1=register.get(i);
						break;
					}
				}
				long value;
				String dumm="";
				if(reg3.contains("h")){
					dumm=reg3.substring(0, reg3.length()-1);
					value=Integer.parseInt(dumm, 16);
				}else if(reg3.contains("b")){
					dumm=reg3.substring(0, reg3.length()-1);
					value=Integer.parseInt(dumm, 2);
				}else{
					value=Integer.parseInt(reg3);
				}

				newReg.addIm(newReg1, value);
				newReg.setTime(dataReady+PC);
				boolean found=false;
				int i=0;
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}else if(command.equals("multi")){
				for(int i=0;i<register.size();i++){
					if(newReg1.getReg().equals(register.get(i).getReg())){
						newReg1=register.get(i);
						break;
					}
				}
				long imVal=Long.parseLong(reg3);
				newReg.mulIm(newReg1, imVal);
				newReg.setTime(dataReady+PC);
				boolean found=false;
				int i=0;
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}else if(command.equals("sub")){
				for(int i=0;i<register.size();i++){
					if(newReg1.getReg().equals(register.get(i).getReg())){
						newReg1=register.get(i);
						break;
					}
				}
				for(int i=0;i<register.size();i++){
					if(newReg2.getReg().equals(register.get(i).getReg())){
						newReg2=register.get(i);
						break;
					}
				}
				newReg.subReg(newReg1, newReg2);
				newReg.setTime(dataReady+PC);
				boolean found=false;
				int i=0;
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}else if(command.equals("subi")){
				for(int i=0;i<register.size();i++){
					if(newReg1.getReg().equals(register.get(i).getReg())){
						newReg1=register.get(i);
						break;
					}
				}
				long imVal=Long.parseLong(reg3);
				newReg.subIm(newReg1, imVal);
				newReg.setTime(dataReady+PC);
				boolean found=false;
				int i=0;
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}else if(command.equals("bne")){
				if(reg2.contains("r")){
					for(int i=0;i<register.size();i++){
						if(newReg1.getReg().equals(register.get(i).getReg())){
							newReg1=register.get(i);
							break;
						}
					}
				}
				for(int i=0;i<register.size();i++){
					if(newReg.getReg().equals(register.get(i).getReg())){
						newReg=register.get(i);
						break;
					}
				}
				if(newReg1!=null){
					if(!newReg.compReg(newReg1)){
						for(int i=0;i<commandList.size();i++){
							if(commandList.get(i)[0].equals(reg3+":")){
								whichInstruction=i;
								break;
							}
						}
					}else{
						whichInstruction=-1;
					}
				}else{
					long val=Long.parseLong(reg2);
					if(!newReg.compIm(val)){
						for(int i=0;i<commandList.size();i++){
							if(commandList.get(i)[0].equals(reg3+":")){
								whichInstruction=i;
								break;
							}
						}
					}else{
						whichInstruction=-1;
					}
				}
			}else if(command.equals("beq")){
				if(reg2.contains("r")){
					for(int i=0;i<register.size();i++){
						if(newReg1.getReg().equals(register.get(i).getReg())){
							newReg1=register.get(i);
							break;
						}
					}
				}
				for(int i=0;i<register.size();i++){
					if(newReg.getReg().equals(register.get(i).getReg())){
						newReg=register.get(i);
						break;
					}
				}
				if(newReg1!=null){
					if(newReg.compReg(newReg1)){
						for(int i=0;i<commandList.size();i++){
							if(commandList.get(i)[0].equals(reg3+":")){
								whichInstruction=i;
								break;
							}
						}
					}else{
						whichInstruction=-1;
					}
				}else{
					long val=Long.parseLong(reg2);
					if(newReg.compIm(val)){
						for(int i=0;i<commandList.size();i++){
							if(commandList.get(i)[0].equals(reg3+":")){
								whichInstruction=i;
								break;
							}
						}
					}else{
						whichInstruction=-1;
					}
				}
			}else if(command.equals("and")){
				for(int i=0;i<register.size();i++){
					if(newReg1.getReg().equals(register.get(i).getReg())){
						newReg1=register.get(i);
						break;
					}
				}
				for(int i=0;i<register.size();i++){
					if(newReg2.getReg().equals(register.get(i).getReg())){
						newReg2=register.get(i);
						break;
					}
				}
				newReg.and(newReg1, newReg2);
				newReg.setTime(dataReady+PC);
				boolean found=false;
				int i=0;
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}else if(command.equals("andi")){
				for(int i=0;i<register.size();i++){
					if(newReg.getReg().equals(register.get(i).getReg())){
						newReg=register.get(i);
						break;
					}
				}
				long imVal=Long.parseLong(reg2);
				newReg.andIm(newReg, imVal);
				newReg.setTime(dataReady+PC);
				boolean found=false;
				int i=0;
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}else if(command.equals("or")){
				for(int i=0;i<register.size();i++){
					if(newReg1.getReg().equals(register.get(i).getReg())){
						newReg1=register.get(i);
						break;
					}
				}
				for(int i=0;i<register.size();i++){
					if(newReg2.getReg().equals(register.get(i).getReg())){
						newReg2=register.get(i);
						break;
					}
				}
				newReg.or(newReg1, newReg2);
				newReg.setTime(dataReady+PC);
				boolean found=false;
				int i=0;
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}else if(command.equals("ori")){
				for(int i=0;i<register.size();i++){
					if(newReg.getReg().equals(register.get(i).getReg())){
						newReg=register.get(i);
						break;
					}
				}
				long imVal=Long.parseLong(reg2);
				newReg.orIm(newReg, imVal);
				newReg.setTime(dataReady+PC);
				boolean found=false;
				int i=0;
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}else if(command.equals("sra")||command.equals("srl")){
				for(int i=0;i<register.size();i++){
					if(newReg1.getReg().equals(register.get(i).getReg())){
						newReg1=register.get(i);
						break;
					}
				}
				for(int i=0;i<register.size();i++){
					if(newReg2.getReg().equals(register.get(i).getReg())){
						newReg2=register.get(i);
						break;
					}
				}
				newReg.shiftRight(newReg1, newReg2);
				newReg.setTime(dataReady+PC);
				boolean found=false;
				int i=0;
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}else if(command.equals("slli")){
				for(int i=0;i<register.size();i++){
					if(newReg1.getReg().equals(register.get(i).getReg())){
						newReg1=register.get(i);
						break;
					}
				}
				long imVal=Long.parseLong(reg3);
				newReg.shiftLeftIm(newReg1, imVal);
				newReg.setTime(dataReady+PC);
				boolean found=false;
				int i=0;
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}else if(command.equals("srli")){
				for(int i=0;i<register.size();i++){
					if(newReg1.getReg().equals(register.get(i).getReg())){
						newReg1=register.get(i);
						break;
					}
				}
				long imVal=Long.parseLong(reg3);
				newReg.shiftRightIm(newReg1, imVal);
				newReg.setTime(dataReady+PC);
				boolean found=false;
				int i=0;
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}else if(command.equals("sw")){
				for(int i=0;i<register.size();i++){
					if(newReg1.getReg().equals(register.get(i).getReg())){
						newReg1=register.get(i);
						break;
					}
				}
				for(int i=0;i<register.size();i++){
					if(newReg.getReg().equals(register.get(i).getReg())){
						newReg=register.get(i);
						break;
					}
				}
				int i=0;
				newReg1.setMemLocation(newReg1.getValue());
				offset=offset/4;
				while(data.get(i).address!=newReg1.getValue()+offset){
					i++;
				}
				String val=Long.toBinaryString(newReg.getValue());
				byte []b=val.getBytes();
				data.get(i).value=b;
				boolean found=false;
				i=0;
				newReg.setTime(dataReady+PC);
				while(i<register.size()){
					if(register.get(i).getReg().equals(newReg.getReg())){
						register.get(i).set(newReg.getValue());
						register.get(i).setTime(newReg.getTime());
						found=true;
						break;
					}
					i++;
				}
				if(!found){
					register.add(newReg);
				}
			}			
		}catch (Exception e){

		}
	}
	//resets the stall time
	public void setStall() {
		stallTime=0;

	}

}
