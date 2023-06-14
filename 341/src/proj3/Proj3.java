package proj3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Proj3{
	private Map<Character,Integer> counts;
	private Map<Character, String> codes;
	private static FileInputStream din;
	private static FileOutputStream dout;
	public BinaryNode buildTree(){
		BinaryNode newNodes=buildPrioity();
		buildTable(newNodes);
		return newNodes;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BinaryNode buildPrioity(){
		PriorityQueue<BinaryNode> priority = new PriorityQueue<BinaryNode>();
		for(Character character:counts.keySet()){
			priority.add(new BinaryNode(character,counts.get(character)));
		}
		while(priority.size()>1){
			@SuppressWarnings("rawtypes")
			BinaryNode newNode=new BinaryNode();
			newNode.setLeft(priority.remove());
			newNode.setRight(priority.remove());
			newNode.setFreqs(newNode.getLeft().getFreqs()+newNode.getRight().getFreqs());
			priority.add(newNode);
		}
		return priority.remove();
	}
	public void buildTable(BinaryNode newNodes){
		codes=new LinkedHashMap<Character,String>();
		travese(newNodes,"");
	}
	private void travese(BinaryNode newNodes,String val){
		if(newNodes.getLeft()==null&& newNodes.getRight()==null){
			codes.put(newNodes.getChar(), val);
		}if(newNodes.getLeft()!=null){
			travese(newNodes.getLeft(),val+"0");
		}if(newNodes.getRight()!=null){
			travese(newNodes.getRight(),val+"1");
		}

	}
	public BinaryNode encode(File compressedOutputFileName, String input) throws IOException{
		dout=new FileOutputStream(compressedOutputFileName);
		String ecodedText="";
		Scanner inputFile=new Scanner(new File(input));
		BinaryNode newnodes=buildTree();
		while(inputFile.hasNext()){
			inputFile.useDelimiter("");
			Character character=inputFile.next().charAt(0);
			for(Character chae:codes.keySet()){
				if(chae==character){
					ecodedText=ecodedText+codes.get(chae);
					break;
				}
			}
		}
		inputFile.close();
		ecodedText=ecodedText+codes.get('^');
		while(ecodedText.length()%8!=0){
			ecodedText=ecodedText+"0";
		}

		for(int i=0;i<ecodedText.length();i+=8){
			String str=ecodedText.substring(i, i+8);
			dout.write(stringToBinary(str));
		}
		dout.close();
		return newnodes;
	}
	public byte stringToBinary(String str){
		byte b=0;
		int power=7;
		for(int i=0;i<str.length();i++){
			int val=Integer.parseInt(str.charAt(i)+"");
			b=(byte) (b+val*Math.pow(2, power));
			power--;
		}
		return b;

	}
	public String byteToString(byte readIn){
		String str="";
		return str;
	}
	public void decode(File uncompressedOutputFileName,BinaryNode tree,String compressedOutputFileName) throws IOException{
		din=new FileInputStream(new File(compressedOutputFileName));
		dout=new FileOutputStream(uncompressedOutputFileName);
		int data=din.read();
		ArrayList<Character> encodedData=new ArrayList<Character>();
		while(data!=-1){
			String encodedData2="";
			String binary=Integer.toBinaryString(data);
			if(binary.length()%8!=0){
				if(binary.length()==7){
					encodedData2="0"+binary;
				}else if(binary.length()==6){
					encodedData2="00"+binary;
				}else if(binary.length()==5){
					encodedData2="000"+binary;
				}else if(binary.length()==4){
					encodedData2="0000"+binary;
				}else if(binary.length()==3){
					encodedData2="00000"+binary;
				}else if(binary.length()==2){
					encodedData2="000000"+binary;
				}else if(binary.length()==1){
					encodedData2="0000000"+binary;
				}
			}else {
				encodedData2=encodedData2+binary;
			}
			for(int i=0;i<encodedData2.length();i++){
				encodedData.add(encodedData2.charAt(i));
			}

			data=din.read();
		}
		String decodedData="";
		while(encodedData.size()!=0){
			BinaryNode trees=tree;
			while(trees.getChar()=='\u0000'){
				if(encodedData.get(0).equals('0')){
					trees=trees.getLeft();
				}if(encodedData.get(0).equals('1')){
					trees=trees.getRight();
				}
				encodedData.remove(0);
			}
			if(trees.getChar()!='^'){
				decodedData=decodedData+trees.getChar();
			}else if(trees.getChar()=='^'){
				break;
			}

		}
		din.close();
		byte [] decodedDatas=decodedData.getBytes();
		dout.write(decodedDatas);
		dout.close();
	}
	public void mapit(Scanner in){
		counts=new LinkedHashMap<Character,Integer>();
		while(in.hasNext()){
			in.useDelimiter("");
			Character character=in.next().charAt(0);
			if(counts.containsKey(character)){
				counts.put(character, 1+counts.get(character));
			}else{
				counts.put(character, 1);
			}
		}
		counts.put('^', 1);

	}
	public String charFreq() {
		String str="Character Frequencies \n";
		str=str+"---------------------\n";
		int CharacterTotal=0;
		for(Character character:counts.keySet()){
			if(character.equals(' ')){
				str=str+"Space"+ " "+counts.get(character)+"\n";
				CharacterTotal+=counts.get(character);
			}else if(character.equals('^')){

			}else if(character.equals('\n')){
				str=str+"New Line"+ " "+counts.get(character)+"\n";
				CharacterTotal+=counts.get(character);
			}else if (character.equals('\r')){
				str=str+"Return Line"+ " "+counts.get(character)+"\n";
				CharacterTotal+=counts.get(character);
			}else{
				str=str+character+ " "+counts.get(character)+"\n";
				CharacterTotal+=counts.get(character);
			}
		}
		return str+"Total Characters: "+CharacterTotal;
	}
	public String charEncodings() {
		String str="Character Encodings \n";
		str=str+"---------------------\n";
		for(Character character:codes.keySet()){
			if(character.equals(' ')){
				str=str+"Space"+ " "+codes.get(character)+"\n";
			}else if(character.equals('^')){

			}else if(character.equals('\n')){
				str=str+"New Line"+ " "+codes.get(character)+"\n";
			}else if (character.equals('\r')){
				str=str+"Return Line"+ " "+codes.get(character)+"\n";
			}else{
				str=str+character+ " "+codes.get(character)+"\n";
			}
		}
		return str;
	}
	public static void main(String args[]) throws FileNotFoundException{	
		Proj3 newProj=new Proj3();
		String inputFileName = args[0], 
				compressedOutputFileName = args[1], 
				uncompressedOutputFileName = args[2];
		Scanner in=new Scanner(new File(inputFileName));
		newProj.mapit(in);
		System.out.println("Compressing "+inputFileName +" into "+ compressedOutputFileName);
		File newFile=new File(compressedOutputFileName);
		in.close();
		BinaryNode newNode=null;
		try {
			newNode = newProj.encode(newFile,inputFileName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Compressed into "+ compressedOutputFileName);
		System.out.println("Uncompressing "+compressedOutputFileName +" into "+ uncompressedOutputFileName);
		newFile=new File(uncompressedOutputFileName);
		try {
			newProj.decode(newFile,newNode,compressedOutputFileName);
		} catch (IOException e) {
			System.out.println("Can't find the file");
		}
		System.out.println("Decompressed into "+uncompressedOutputFileName);
		System.out.println(newProj.charFreq());
		System.out.println(newProj.charEncodings());
	}
}