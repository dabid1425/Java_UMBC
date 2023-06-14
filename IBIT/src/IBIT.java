import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.mail.internet.MimeMultipart;
public class IBIT {
	/**
	 * IBIT.java - This class will run a IBIT test on sites predefined in a text file called Test.txt
	 * @version 1
	 * @Dan Abid
	 * @ARINC
	 *Still need to:  
	 *run tests on multiple sites
	 *change from and host-good for gmail need outlook
	 */
	private static DataOutputStream dout; // to write on the socket
	private static Map<Integer, Object[]> passedData = new HashMap<Integer, Object[]>();
	private static int rowPassedCount=1;
	private static Map<Integer, Object[]> failedData = new HashMap<Integer, Object[]>();
	private static int rowFailedCount=1;
	private static Socket socket;
	private static boolean testingStateA=false;
	private static boolean testingStateB=false;
	private static int cantConnect=0;
	private static String CommCenter;
	private static boolean checkedInPrevious=false;
	@SuppressWarnings("unused")
	private static boolean errorOnce=false;
	private static boolean duelSite=false;
	private static int errorOe=0;
	private static boolean skip=false;
	private static statusBar bar;
	private static boolean ninthBit=false;
	private static boolean tenthBit=false;
	private static String destination;
	private static String sendPassFile;
	private static int Sequelch=0;
	private static ArrayList<String> emailSender=new ArrayList<String>();
	private static ArrayList<String> savedPassedData=new ArrayList<String>();
	private static ArrayList<String> savedFailedData=new ArrayList<String>();
	private static DataInputStream din;//to read from the socket
	private static ArrayList<String> emailList=new ArrayList<String>();
	private static ArrayList<String> limits=new ArrayList<String>();
	private static ArrayList<String> errorSites=new ArrayList<String>();
	private static ArrayList<String> recivedData=new ArrayList<String>();
	private static Date newDate=new Date();//gets todays date 
	private static File compareFile;//the previous days pass file
	private static boolean failFileheader=false;//boolean to see if header is needed in the fail file
	private static boolean PassFileheader=false;//boolean to see if header is needed in the pass file
	private static File okFile;//todays pass file
	@SuppressWarnings("unused")
	private static FileWriter failedFwriter;//write to the fail file
	//private static PrintWriter failedFile;//printer to the fail file
	private static String Site;//current site
	private static String IpAddress;//current ip address
	private static int Port;//current port
	private static String IpAddress2;//current ip address
	private static String State;//current ip address
	private static int Port2;//current port
	private static String IpAddress3;//current ip address
	private static String State2;//current ip address
	private static int Port3;//current port
	private static String Site2;//current site
	private static File failFile;//today's fail file
	private static boolean failed;//boolean to see if any radios are not within the respectable limits
	private static boolean eightBit=false;
	private static boolean seventhBit=false;
	private static String newtodayPassed;//String for the current pass file needed to help send email 
	private static String newtodayfailed;//String for the current fail file needed to help send email
	private static boolean error=false;//boolean to see if there has been a radio the program cannot connect to
	private static boolean sensitivity=false;
	private static boolean srw=false;
	private static boolean modualtionDepth=false;
	private static boolean errorIndication=false;
	private static boolean foward=false;
	private static boolean refelcted=false;
	private static boolean sensitv=false;
	private static boolean modula=false;
	private static boolean writtenTo=false;
	private static boolean write=false;
	private static boolean testedA=false;
	private static boolean testedB=false;
	private static boolean firstTest=true;
	private static boolean addError=false;
	private static Retest newRetest;
	/**
	 * helps write to fail file 
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 */
	public static String whatFailedInCompareFile(){
		String str="";
		if(foward){
			str=str+"foward ";
		}if(refelcted){
			str=str+"refelcted ";
		}if(sensitv){
			str=str+"sensitivity ";
		}if(modula){
			str=str+"modulation ";
		}
		foward=false;
		refelcted=false;
		sensitv=false;
		modula=false;
		return str;
	}
	/**
	 * helps write to fail file 
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 */
	public static String whatFailed(){
		String str="";
		String data=Integer.toBinaryString(Integer.parseInt(recivedData.get(7),16));
		for(int i=0;i<data.length();i++){
			if(i==1){
				if(data.subSequence(data.length()-2, data.length()-1).equals("1")){
					str=str+"Sensitivity ";
				}
			}if(i==2){
				if(data.subSequence(data.length()-3,data.length()-2).equals("1")){
					str=str+"Modulation Depth ";
				}
			}if(i==3){
				if(data.subSequence(data.length()-4,data.length()-3).equals("1")){
					str=str+"PA Output ";
				}
			}if(i==4){
				if(data.subSequence(data.length()-5,data.length()-4).equals("1")){
					str=str+"PA Loop ";
				}
			}if(i==5){
				if(data.subSequence(data.length()-6,data.length()-5).equals("1")){
					str=str+"RF Drive ";
				}
			}if(i==6){
				if(data.subSequence(data.length()-7,data.length()-6).equals("1")){
					str=str+"EEPROM ";
				}
			}
		}
		seventhBit=false;
		return str;
	}
	/**
	 * helps write to fail file 
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 */
	public static String whatFailedEightBit(){
		String str="";
		String data=Integer.toBinaryString(Integer.parseInt(recivedData.get(8),16));
		for(int i=0;i<data.length();i++){
			if(i==2){
				if(data.subSequence(data.length()-3, data.length()-2).equals("1")){
					str=str+"Base band ";
				}
			}if(i==3){
				if(data.subSequence(data.length()-4,data.length()-3).equals("1")){
					str=str+"Audio ";
				}
			}if(i==4){
				if(data.subSequence(data.length()-5,data.length()-4).equals("1")){
					str=str+"IF Filters ";
				}
			}if(i==5){
				if(data.subSequence(data.length()-6,data.length()-5).equals("1")){
					str=str+"RX RF Filters ";
				}
			}if(i==6){
				if(data.subSequence(data.length()-7,data.length()-6).equals("1")){
					str=str+"TX RF Filters ";
				}
			}
		}

		eightBit=false;
		return str;
	}
	/**
	 * helps write to fail file 
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 */
	public static String whatFailedninthBit(){
		String str="";
		String data=Integer.toBinaryString(Integer.parseInt(recivedData.get(12),16));
		for(int i=0;i<data.length();i++){
			if(i==1){
				if(data.subSequence(data.length()-2, data.length()-1).equals("1")){
					str=str+"RF Loopback Failure ";
				}
			}if(i==2){
				if(data.subSequence(data.length()-3,data.length()-2).equals("1")){
					str=str+"Over Temperature ";
				}
			}if(i==3){
				if(data.subSequence(data.length()-4,data.length()-3).equals("1")){
					str=str+"Synth Lock Error ";
				}
			}if(i==4){
				if(data.subSequence(data.length()-5,data.length()-4).equals("1")){
					str=str+"PA Loop Error ";
				}
			}if(i==5){
				if(data.subSequence(data.length()-6,data.length()-5).equals("1")){
					str=str+"No Software Fills Loaded ";
				}
			}if(i==6){
				if(data.subSequence(data.length()-7,data.length()-6).equals("1")){
					str=str+"EEPROM Error ";
				}
			}if(i==7){
				if(data.subSequence(data.length()-8,data.length()-7).equals("1")){
					str=str+"Non-Specified MDR Error ";
				}
			}
		}

		ninthBit=false;
		return str;
	}
	/**
	 * helps write to fail file 
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 */
	public static String whatFailedtenthBit(){
		String str="";
		String data=Integer.toBinaryString(Integer.parseInt(recivedData.get(13),16));
		for(int i=0;i<data.length();i++){
			if(i==2){
				if(data.subSequence(data.length()-3, data.length()-2).equals("1")){
					str=str+"High Temp ";
				}
			}if(i==6){
				if(data.subSequence(data.length()-7,data.length()-6).equals("1")){
					str=str+"High VSWR ";
				}
			}if(i==7){
				if(data.subSequence(data.length()-8,data.length()-7).equals("1")){
					str=str+"Non-Spec MDR Warning ";
				}
			}
		}
		tenthBit=false;
		return str;
	}
	/**
	 * helps write to fail file 
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 */
	@SuppressWarnings("deprecation")
	public static void writeFailExcel(int i) throws Exception{
		if(!duelSite){
			writtenTo=true;
			if(!failFileheader){
				int year=newDate.getYear()+1900;
				failedData.put(rowFailedCount, new Object[]{newDate.getMonth()+1+"/"+newDate.getDate()+"/"+year});
				rowFailedCount++;
				failFileheader=true;
				failedData.put(rowFailedCount, new Object[]{"Site","IP Address","Error","FWD","REF","MOD","SENS"});
				savedFailedData.add("Site");savedFailedData.add("IP Address");savedFailedData.add("Error");savedFailedData.add("FWD");savedFailedData.add("MOD");
				savedFailedData.add("SENS");savedFailedData.add("End");
				rowFailedCount++;
			}
			if(i==8){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,"Error when running health test"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Error when running health test");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==7){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,"Error Indication"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Error Indication");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==6){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,"Data Input/Output stream failed"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Data Input/Output stream failed");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==-1){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,"Radio Busy"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Radio Busy");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==0){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,"High VSWR"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("High VSWR");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==1){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,whatFailedInCompareFile(),
						Integer.parseInt(recivedData.get(3),16), Integer.parseInt(recivedData.get(4),16)
						,Integer.parseInt(recivedData.get(5),16),hexToDec(recivedData.get(6))+" dBm"
						,Integer.parseInt(recivedData.get(7),16),Integer.parseInt(recivedData.get(8),16)});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add(whatFailedInCompareFile());
				savedFailedData.add(Integer.parseInt(recivedData.get(3),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(4),16)+"");
				savedFailedData.add(Integer.parseInt(recivedData.get(5),16)+"");savedFailedData.add(hexToDec(recivedData.get(6))+" dBm");
				savedFailedData.add(Integer.parseInt(recivedData.get(7),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(8),16)+"");
				savedFailedData.add("End");
				rowFailedCount++;			
			}if(i==2){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,whatFailedInLimits(),
						Integer.parseInt(recivedData.get(3),16), Integer.parseInt(recivedData.get(4),16)
						,Integer.parseInt(recivedData.get(5),16),hexToDec(recivedData.get(6))+" dBm"
						,Integer.parseInt(recivedData.get(7),16),Integer.parseInt(recivedData.get(8),16)});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add(whatFailedInLimits());
				savedFailedData.add(Integer.parseInt(recivedData.get(3),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(4),16)+"");
				savedFailedData.add(Integer.parseInt(recivedData.get(5),16)+"");savedFailedData.add(hexToDec(recivedData.get(6))+" dBm");
				savedFailedData.add(Integer.parseInt(recivedData.get(7),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(8),16)+"");
				savedFailedData.add("End");
				rowFailedCount++;		
			}if(seventhBit){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,whatFailed(),
						Integer.parseInt(recivedData.get(3),16), Integer.parseInt(recivedData.get(4),16)
						,Integer.parseInt(recivedData.get(5),16),hexToDec(recivedData.get(6))+" dBm"
						,Integer.parseInt(recivedData.get(7),16),Integer.parseInt(recivedData.get(8),16)});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add(whatFailed());
				savedFailedData.add(Integer.parseInt(recivedData.get(3),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(4),16)+"");
				savedFailedData.add(Integer.parseInt(recivedData.get(5),16)+"");savedFailedData.add(hexToDec(recivedData.get(6))+" dBm");
				savedFailedData.add(Integer.parseInt(recivedData.get(7),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(8),16)+"");
				savedFailedData.add("End");
				rowFailedCount++;	
			}if(eightBit){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,whatFailedEightBit(),
						Integer.parseInt(recivedData.get(3),16), Integer.parseInt(recivedData.get(4),16)
						,Integer.parseInt(recivedData.get(5),16),hexToDec(recivedData.get(6))+" dBm"
						,Integer.parseInt(recivedData.get(7),16),Integer.parseInt(recivedData.get(8),16)});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add(whatFailedEightBit());
				savedFailedData.add(Integer.parseInt(recivedData.get(3),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(4),16)+"");
				savedFailedData.add(Integer.parseInt(recivedData.get(5),16)+"");savedFailedData.add(hexToDec(recivedData.get(6))+" dBm");
				savedFailedData.add(Integer.parseInt(recivedData.get(7),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(8),16)+"");
				savedFailedData.add("End");
				rowFailedCount++;	
			}if(ninthBit){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,whatFailedninthBit(),hexToDec(recivedData.get(12)),hexToDec(recivedData.get(13))});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add(whatFailedninthBit());
				savedFailedData.add("End");
				rowFailedCount++;	
			}if(tenthBit){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,whatFailedtenthBit(),hexToDec(recivedData.get(12)),hexToDec(recivedData.get(13))});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add(whatFailedtenthBit());
				savedFailedData.add("End");
				rowFailedCount++;	
			}if(i==3){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,"Could not connect"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Could not connect");savedFailedData.add("End");
				rowFailedCount++;	
			}if(i==4){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,"Error Indication when trying to get Reset Indication"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Error Indication when trying to get Reset Indication");savedFailedData.add("End");
				rowFailedCount++;	
			}if(i==5){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,"Error Indication when running IBIT"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Error Indication when running IBIT");savedFailedData.add("End");
				rowFailedCount++;
			}
		}else if(testingStateA&&duelSite){
			if(!failFileheader){
				writtenTo=true;
				int year=newDate.getYear()+1900;
				failedData.put(rowFailedCount, new Object[]{newDate.getMonth()+1+"/"+newDate.getDate()+"/"+year});
				rowFailedCount++;
				failFileheader=true;
				failedData.put(rowFailedCount, new Object[]{"Site","IP Address","Error","FWD","REF","MOD","SENS"});
				rowFailedCount++;
				savedFailedData.add("Site");savedFailedData.add("IP Address");savedFailedData.add("Error");savedFailedData.add("FWD");savedFailedData.add("MOD");
				savedFailedData.add("SENS");savedFailedData.add("End");

			}if(i==8){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,"Error when running health test"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Error when running health test ");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==7){
				failedData.put(rowFailedCount, new Object[]{Site ,IpAddress,"Error Indication"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Error Indication");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==6){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress ,"Data Input/Output stream failed"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Data Input/Output stream failed");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==-5){
				failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3,"Can't Connect to Radio because Radio A has the antenna and is busy"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Can't Connect to Radio because Radio A has the antenna and is busy");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==-4){
				failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3," Can't Connect to Radio"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Can't Connect to Radio");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==-3){
				if(!testedB){
					failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3," Can't Connect to Radio"});
					savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Can't Connect to Radio");savedFailedData.add("End");
					rowFailedCount++;
				}else{
					failedData.put(rowFailedCount, new Object[]{Site,IpAddress,"Can't Connect to Radio"});
					savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Can't Connect to Radio");savedFailedData.add("End");
					rowFailedCount++;
				}
			}if(i==-2){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,Site2,IpAddress3,IpAddress2,"Can't Connect to DTI"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Can't Connect to DTI");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==-1){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress," Radio Busy"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Radio Busy");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==0){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress," High VSWR"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("High VSWR");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==1){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,whatFailedInCompareFile(),
						Integer.parseInt(recivedData.get(3),16), Integer.parseInt(recivedData.get(4),16)
						, Integer.parseInt(recivedData.get(5),16),hexToDec(recivedData.get(6))+" dBm"
						, Integer.parseInt(recivedData.get(7),16),Integer.parseInt(recivedData.get(8),16)});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add(whatFailedInCompareFile());
				savedFailedData.add(Integer.parseInt(recivedData.get(3),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(4),16)+"");
				savedFailedData.add(Integer.parseInt(recivedData.get(5),16)+"");savedFailedData.add(hexToDec(recivedData.get(6))+" dBm");
				savedFailedData.add(Integer.parseInt(recivedData.get(7),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(8),16)+"");
				savedFailedData.add("End");
				rowFailedCount++;
			}if(i==2){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,whatFailedInLimits()
						,Integer.parseInt(recivedData.get(3),16), Integer.parseInt(recivedData.get(4),16), Integer.parseInt(recivedData.get(5),16)
						,hexToDec(recivedData.get(6))+" dBm"
						, Integer.parseInt(recivedData.get(7),16),Integer.parseInt(recivedData.get(8),16)});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add(whatFailedInLimits());
				savedFailedData.add(Integer.parseInt(recivedData.get(3),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(4),16)+"");
				savedFailedData.add(Integer.parseInt(recivedData.get(5),16)+"");savedFailedData.add(hexToDec(recivedData.get(6))+" dBm");
				savedFailedData.add(Integer.parseInt(recivedData.get(7),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(8),16)+"");
				savedFailedData.add("End");
				rowFailedCount++;
			}if(seventhBit){	
				failedData.put(rowFailedCount, new Object[]{Site , IpAddress ,whatFailed(),
						Integer.parseInt(recivedData.get(3),16), Integer.parseInt(recivedData.get(4),16)
						,Integer.parseInt(recivedData.get(5),16),hexToDec(recivedData.get(6))+" dBm"
						,Integer.parseInt(recivedData.get(7),16),Integer.parseInt(recivedData.get(8),16)});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add(whatFailed());
				savedFailedData.add(Integer.parseInt(recivedData.get(3),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(4),16)+"");
				savedFailedData.add(Integer.parseInt(recivedData.get(5),16)+"");savedFailedData.add(hexToDec(recivedData.get(6))+" dBm");
				savedFailedData.add(Integer.parseInt(recivedData.get(7),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(8),16)+"");
				savedFailedData.add("End");
				rowFailedCount++;
			}if(eightBit){
				failedData.put(rowFailedCount, new Object[]{Site ,IpAddress,whatFailedEightBit(),
						Integer.parseInt(recivedData.get(3),16),Integer.parseInt(recivedData.get(4),16)
						,Integer.parseInt(recivedData.get(5),16),hexToDec(recivedData.get(6))+" dBm"
						,Integer.parseInt(recivedData.get(7),16),Integer.parseInt(recivedData.get(8),16)});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add(whatFailedEightBit());
				savedFailedData.add(Integer.parseInt(recivedData.get(3),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(4),16)+"");
				savedFailedData.add(Integer.parseInt(recivedData.get(5),16)+"");savedFailedData.add(hexToDec(recivedData.get(6))+" dBm");
				savedFailedData.add(Integer.parseInt(recivedData.get(7),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(8),16)+"");
				savedFailedData.add("End");
				rowFailedCount++;
			}if(ninthBit){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,whatFailedninthBit(),hexToDec(recivedData.get(12)),hexToDec(recivedData.get(13))});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add(whatFailedninthBit());
				savedFailedData.add("End");
				rowFailedCount++;
			}if(tenthBit){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,whatFailedtenthBit(),
						hexToDec(recivedData.get(12)),hexToDec(recivedData.get(13))});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add(whatFailedtenthBit());
				savedFailedData.add("End");
				rowFailedCount++;
			}if(i==3){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,"Could not connect"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Can't Connect");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==4){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,"Error Indication when trying to get Reset Indication"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Error Indication when trying to get Reset Indication");savedFailedData.add("End");
			}if(i==5){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,"Error Indication when running IBIT"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Error Indication when running IBIT");savedFailedData.add("End");
				rowFailedCount++;
			}
		}else if(testingStateB&&duelSite){
			if(!failFileheader){
				writtenTo=true;
				int year=newDate.getYear()+1900;
				failedData.put(rowFailedCount, new Object[]{newDate.getMonth()+1+"/"+newDate.getDate()+"/"+year});
				rowFailedCount++;
				failFileheader=true;
				failedData.put(rowFailedCount, new Object[]{"Site","IP Address","Error","FWD","REF","MOD","SENS"});
				rowFailedCount++;
				savedFailedData.add("Site");savedFailedData.add("IP Address");savedFailedData.add("Error");savedFailedData.add("FWD");savedFailedData.add("MOD");
				savedFailedData.add("SENS");savedFailedData.add("End");
			}
			if(i==8){
				failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3,"Error when running health test "});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add("Error when running health test");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==7){
				failedData.put(rowFailedCount, new Object[]{Site2 ,IpAddress3,"Error Indication"});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add("Error Indication");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==6){
				failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3 ,"Data Input/Output stream failed"});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add("Data Input/Output stream failed");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==-5){
				failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3,"Can't Connect to Radio because Radio B has the antenna and is busy"});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add("Can't Connect to Radio because Radio B has the antenna and is busy");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==-4){
				if(!testedA){
					failedData.put(rowFailedCount, new Object[]{Site,IpAddress,"Can't Connect to Radio"});
					savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Can't Connect to Radio");savedFailedData.add("End");
					rowFailedCount++;
				}else{
					failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3,"Can't Connect to Radio"});
					savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add("Can't Connect to Radio");savedFailedData.add("End");
					rowFailedCount++;
				}
			}if(i==-3){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress," Can't Connect to Radio"});
				savedFailedData.add(Site);savedFailedData.add(IpAddress);savedFailedData.add("Can't Connect to Radio");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==-2){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,Site2,IpAddress3,IpAddress2,"Can't Connect to DTI"});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add("Can't Connect to DIT");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==-1){
				failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3,"Radio Busy"});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add("Radio Busy");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==0){
				failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3,"High VSWR"});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add("High VSWR");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==1){
				failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3,whatFailedInCompareFile(),
						Integer.parseInt(recivedData.get(3),16), Integer.parseInt(recivedData.get(4),16)
						, Integer.parseInt(recivedData.get(5),16),hexToDec(recivedData.get(6))+" dBm"
						, Integer.parseInt(recivedData.get(7),16),Integer.parseInt(recivedData.get(8),16)});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add(whatFailedInCompareFile());
				savedFailedData.add(Integer.parseInt(recivedData.get(3),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(4),16)+"");
				savedFailedData.add(Integer.parseInt(recivedData.get(5),16)+"");savedFailedData.add(hexToDec(recivedData.get(6))+" dBm");
				savedFailedData.add(Integer.parseInt(recivedData.get(7),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(8),16)+"");
				savedFailedData.add("End");
				rowFailedCount++;
			}if(i==2){
				failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3,whatFailedInLimits()
						,Integer.parseInt(recivedData.get(3),16), Integer.parseInt(recivedData.get(4),16),
						Integer.parseInt(recivedData.get(5),16)
						,hexToDec(recivedData.get(6))+" dBm"
						, Integer.parseInt(recivedData.get(7),16),Integer.parseInt(recivedData.get(8),16)});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add(whatFailedInLimits());
				savedFailedData.add(Integer.parseInt(recivedData.get(3),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(4),16)+"");
				savedFailedData.add(Integer.parseInt(recivedData.get(5),16)+"");savedFailedData.add(hexToDec(recivedData.get(6))+" dBm");
				savedFailedData.add(Integer.parseInt(recivedData.get(7),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(8),16)+"");
				savedFailedData.add("End");
				rowFailedCount++;
			}if(seventhBit){	
				failedData.put(rowFailedCount, new Object[]{Site2 , IpAddress3 ,whatFailed(),
						Integer.parseInt(recivedData.get(3),16), Integer.parseInt(recivedData.get(4),16)
						,Integer.parseInt(recivedData.get(5),16),hexToDec(recivedData.get(6))+" dBm"
						,Integer.parseInt(recivedData.get(7),16),Integer.parseInt(recivedData.get(8),16)});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add(whatFailed());
				savedFailedData.add(Integer.parseInt(recivedData.get(3),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(4),16)+"");
				savedFailedData.add(Integer.parseInt(recivedData.get(5),16)+"");savedFailedData.add(hexToDec(recivedData.get(6))+" dBm");
				savedFailedData.add(Integer.parseInt(recivedData.get(7),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(8),16)+"");
				savedFailedData.add("End");
				rowFailedCount++;
			}if(eightBit){
				failedData.put(rowFailedCount, new Object[]{Site2 ,IpAddress3,whatFailedEightBit(),
						Integer.parseInt(recivedData.get(3),16),Integer.parseInt(recivedData.get(4),16)
						,Integer.parseInt(recivedData.get(5),16),hexToDec(recivedData.get(6))+" dBm"
						,Integer.parseInt(recivedData.get(7),16),Integer.parseInt(recivedData.get(8),16)});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add(whatFailedEightBit());
				savedFailedData.add(Integer.parseInt(recivedData.get(3),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(4),16)+"");
				savedFailedData.add(Integer.parseInt(recivedData.get(5),16)+"");savedFailedData.add(hexToDec(recivedData.get(6))+" dBm");
				savedFailedData.add(Integer.parseInt(recivedData.get(7),16)+"");savedFailedData.add(Integer.parseInt(recivedData.get(8),16)+"");
				savedFailedData.add("End");
				rowFailedCount++;
			}if(ninthBit){
				failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3,whatFailedninthBit(),hexToDec(recivedData.get(12)),hexToDec(recivedData.get(13))});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add(whatFailedninthBit());
				savedFailedData.add("End");
				rowFailedCount++;
			}if(tenthBit){
				failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3,whatFailedtenthBit(),
						hexToDec(recivedData.get(12)),hexToDec(recivedData.get(13))});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add(whatFailedtenthBit());
				savedFailedData.add("End");
				rowFailedCount++;
			}if(i==3){
				failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3,"Could not connect"});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add("Could not connect");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==4){
				failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3,"Error Indication when trying to get Reset Indication"});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add("Error Indication when trying to get Reset Indication");savedFailedData.add("End");
				rowFailedCount++;
			}if(i==5){
				failedData.put(rowFailedCount, new Object[]{Site2,IpAddress3,"Error Indication when running IBIT"});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add("Error Indication when running IBIT");savedFailedData.add("End");
				rowFailedCount++;
			}			
		}if(duelSite&&!testingStateA&&!testingStateB){
			if(!failFileheader){
				writtenTo=true;
				int year=newDate.getYear()+1900;
				failedData.put(rowFailedCount, new Object[]{newDate.getMonth()+1+"/"+newDate.getDate()+"/"+year});
				rowFailedCount++;
				failFileheader=true;
				failedData.put(rowFailedCount, new Object[]{"Site","IP Address","Site","IP Address","DTI Address","Error"});
				rowFailedCount++;
				savedFailedData.add("Site");savedFailedData.add("IP Address");savedFailedData.add("Error");savedFailedData.add("FWD");savedFailedData.add("MOD");
				savedFailedData.add("SENS");savedFailedData.add("End");
			}
			if(i==-2){
				failedData.put(rowFailedCount, new Object[]{Site,IpAddress,Site2,IpAddress3,IpAddress2,"Can't Connect to DTI"});
				savedFailedData.add(Site2);savedFailedData.add(IpAddress3);savedFailedData.add("Can't Connect to DTI");savedFailedData.add("End");
				rowFailedCount++;
			}
		}
		failed=false;
		error=false;
		recivedData.removeAll(recivedData);
	}
	/**
	 * helps write to fail file 
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 */	
	public static String whatFailedInLimits() {
		String str="";
		double newSWR=0;
		if(errorIndication){
			str=str+"Error Indication ";
		}if(srw){
			double swr=0;
			try{
				float refle=Integer.parseInt(recivedData.get(4),16);
				float forw=Integer.parseInt(recivedData.get(3),16);
				float ratio=(float) Math.sqrt(refle/forw);
				swr=(1+ratio)/(1-ratio);
				DecimalFormat df1=new DecimalFormat("0.##");
				newSWR=Double.parseDouble(df1.format(swr).toString());
			}catch(ArithmeticException e){

			}
			str=str+"SWR "+newSWR+" ";
		}if(modualtionDepth){
			DecimalFormat df1=new DecimalFormat("0.##");
			str=str+"Modualtion Depth "+df1.format(Integer.parseInt(recivedData.get(5),16))+" ";
		}if(sensitivity){
			DecimalFormat df1=new DecimalFormat("0.##");
			str=str+"sensitivity "+df1.format((Integer) hexToDec(recivedData.get(6)))+" ";
		}
		sensitivity=false;
		srw=false;
		modualtionDepth=false;
		errorIndication=false;
		return str;
	}
	/**
	 * changes the 7th octet which is sensitivity to dBMs 
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 */
	public static Number hexToDec(String hex)  {
		if (hex == null) {
			throw new NullPointerException("hexToDec: hex String is null.");
		}

		// You may want to do something different with the empty string.
		if (hex.equals("")) { return Byte.valueOf("0"); }

		// If you want to pad "FFF" to "0FFF" do it here.

		hex = hex.toUpperCase();

		// Check if high bit is set.
		boolean isNegative =
				hex.startsWith("8") || hex.startsWith("9") ||
				hex.startsWith("A") || hex.startsWith("B") ||
				hex.startsWith("C") || hex.startsWith("D") ||
				hex.startsWith("E") || hex.startsWith("F");

		BigInteger temp;

		if (isNegative) {
			// Negative number
			temp = new BigInteger(hex, 16);
			BigInteger subtrahend = BigInteger.ONE.shiftLeft(hex.length() * 4);
			temp = temp.subtract(subtrahend);
		} else {
			// Positive number
			temp = new BigInteger(hex, 16);
		}

		// Cut BigInteger down to size.
		if (hex.length() <= 2) { return (Byte)temp.byteValue(); }
		if (hex.length() <= 4) { return (Short)temp.shortValue(); }
		if (hex.length() <= 8) { return (Integer)temp.intValue(); }
		if (hex.length() <= 16) { return (Long)temp.longValue(); }
		return temp;
	}
	public static void createFailedExcel() throws MessagingException{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Failed Sites");
		Set<Integer> keyset = failedData.keySet();
		int rownum = 0;
		int numofCols=0;
		for (Integer key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object [] objArr = failedData.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				sheet.autoSizeColumn(cellnum);
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof Integer) {
					cell.setCellValue((Integer)obj);
					sheet.autoSizeColumn(cellnum);
				}else if(obj instanceof Float){
					cell.setCellValue((Float)obj);
					sheet.autoSizeColumn(cellnum);
				}else if(obj instanceof String){
					cell.setCellValue((String)obj);
					sheet.autoSizeColumn(cellnum);
				}else if(obj instanceof Double){
					cell.setCellValue((Double)obj);
					sheet.autoSizeColumn(cellnum);
				}
				if(cellnum>numofCols){
					numofCols=cellnum;
				}
			}
		}

		for(int i=0;i<numofCols;i++){
			sheet.autoSizeColumn(i);
		}
		try {
			FileOutputStream out = 
					new FileOutputStream(newtodayfailed);
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			sendEmail();
			System.exit(0);
		} catch (IOException e) {
			sendEmail();
			System.exit(0);
		}
	}
	public static void createPassesExcel() throws MessagingException{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Passed Sites");
		Set<Integer> keyset = passedData.keySet();
		int numofCols=0;
		int rownum = 0;
		for (Integer key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object [] objArr = passedData.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				sheet.autoSizeColumn(cellnum);
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof Integer) {
					cell.setCellValue((Integer)obj);
				}else if(obj instanceof Float){
					cell.setCellValue((Float)obj);
				}else if(obj instanceof String){
					cell.setCellValue((String)obj);
				}else if(obj instanceof Double){
					cell.setCellValue((Double)obj);
				}
				if(cellnum>numofCols){
					numofCols=cellnum;
				}

			}
		}
		for(int i=0;i<numofCols;i++){
			sheet.autoSizeColumn(i);
		}
		try {
			FileOutputStream out = 
					new FileOutputStream(newtodayPassed);
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			sendEmail();
			System.exit(0);
		} catch (IOException e) {
			sendEmail();
			System.exit(0);
		}
	}
	@SuppressWarnings("deprecation")
	public static void writetoMap() throws IOException, MessagingException{
		if(!PassFileheader){
			int year=newDate.getYear()+1900;
			passedData.put(rowPassedCount, new Object[]{newDate.getMonth()+1+"/"+newDate.getDate()+"/"+year});
			rowPassedCount++;
			PassFileheader=true;
			passedData.put(rowPassedCount, new Object[]{"Site","IP Address","FWD","REF","VSWR","MOD","SENS"});
			rowPassedCount++;
		}
		float refle=Integer.parseInt(recivedData.get(4),16);
		float forw=Integer.parseInt(recivedData.get(3),16);
		float ratio=(float) Math.sqrt(refle/forw);
		float swr=(1+ratio)/(1-ratio);
		DecimalFormat df1=new DecimalFormat("0.##");
		double newSWR=Double.parseDouble(df1.format(swr).toString());
		if(duelSite){
			try{
				socket = new Socket(IpAddress2,Port2);
			}catch(Exception e) {
				//System.out.println("Can't connect");
			}
			try{
				dout = new DataOutputStream(socket.getOutputStream());
				din = new DataInputStream(socket.getInputStream());
				//System.out.println("Connected streams to "+Site);
			}catch (IOException e) {
				//System.out.println("Can't connect");
			}
			int saveState=getState();
			socket.close();
			dout.close();
			din.close();
			if(saveState==1){
				passedData.put(rowPassedCount,new Object[]{Site2,IpAddress3,Integer.parseInt(recivedData.get(3),16),Integer.parseInt(recivedData.get(4),16),newSWR,
						Integer.parseInt(recivedData.get(5),16),hexToDec(recivedData.get(6))+" dBm"});
				savedPassedData.add(Site2);savedPassedData.add(IpAddress3);savedPassedData.add(Integer.parseInt(recivedData.get(3),16)+"")
				;savedPassedData.add(Integer.parseInt(recivedData.get(4),16)+"");savedPassedData.add(newSWR+"");
				savedPassedData.add(Integer.parseInt(recivedData.get(5),16)+"");savedPassedData.add(hexToDec(recivedData.get(6))+" dBm");

			}else{
				passedData.put(rowPassedCount,new Object[]{Site,IpAddress,Integer.parseInt(recivedData.get(3),16),Integer.parseInt(recivedData.get(4),16),newSWR,
						Integer.parseInt(recivedData.get(5),16),hexToDec(recivedData.get(6))+" dBm"});
				savedPassedData.add(Site);savedPassedData.add(IpAddress);savedPassedData.add(Integer.parseInt(recivedData.get(3),16)+"")
				;savedPassedData.add(Integer.parseInt(recivedData.get(4),16)+"");savedPassedData.add(newSWR+"");
				savedPassedData.add(Integer.parseInt(recivedData.get(5),16)+"");savedPassedData.add(hexToDec(recivedData.get(6))+" dBm");

			}
		}else{
			passedData.put(rowPassedCount,new Object[]{Site,IpAddress,Integer.parseInt(recivedData.get(3),16),Integer.parseInt(recivedData.get(4),16),newSWR,
					Integer.parseInt(recivedData.get(5),16),hexToDec(recivedData.get(6))+" dBm"});
			savedPassedData.add(Site);savedPassedData.add(IpAddress);savedPassedData.add(Integer.parseInt(recivedData.get(3),16)+"")
			;savedPassedData.add(Integer.parseInt(recivedData.get(4),16)+"");savedPassedData.add(newSWR+"");
			savedPassedData.add(Integer.parseInt(recivedData.get(5),16)+"");savedPassedData.add(hexToDec(recivedData.get(6))+" dBm");
		}

		rowPassedCount++;
	}
	/**
	 * writes passed sites to an excel sheet
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 * @throws MessagingException 
	 */
	public static void writePassExcel() throws IOException, MessagingException{
		writetoMap();
		write=true;
		recivedData.removeAll(recivedData);
	}

	/**
	 * Re-tests sites that could not be connected to 
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 */
	public static void reTestSites() throws Exception{
		int count=0;
		for(int i=1;i<errorSites.size();i=i+2){
			count++;
			if(count==1){

			}else{
				i=1;
			}
			if(i<errorSites.size()&&count<=(errorSites.size()/3)){
				Site=errorSites.get(i-1);
				//System.out.println(Site);
				Port=Integer.parseInt(errorSites.get(i+1));
				IpAddress=errorSites.get(i);
				if(errorSites.size()>3){
					if(errorSites.get(i+2).equals("duel")){
						duelSite=true;
						IpAddress2=errorSites.get(i+3);
						Port2=Integer.parseInt(errorSites.get(i+4));
						State=errorSites.get(i+5);
						Site2=errorSites.get(i+6);
						IpAddress3=errorSites.get(i+7);
						Port3=Integer.parseInt(errorSites.get(i+8));
						State=errorSites.get(i+9);
						if(errorSites.get(i+10).equals("yes")){
							testedA=true;
						}else if(errorSites.get(i+10).equals("yes1")){
							testedB=true;
						}else{
							testedA=false;
							testedB=false;
						}
						errorSites.remove(i+2);
						errorSites.remove(i+2);
						errorSites.remove(i+2);	
						errorSites.remove(i+2);
						errorSites.remove(i+2);
						errorSites.remove(i+2);
						errorSites.remove(i+2);	
						errorSites.remove(i+2);
						errorSites.remove(i+2);
					}
				}
				errorSites.remove(i);
				errorSites.remove(i-1);
				errorSites.remove(i-1);
				i=1;
				newRetest=new Retest(Site);
				newRetest.setVisible(true);
				error=false;
				ibit();
				newRetest.setVisible(false);
			}else{
				return;
			}
		}


	}
	public static int getState() throws IOException{
		try{
			Thread.sleep(6000);
		}catch(InterruptedException ie){

		}
		String encoding="GET /state.xml? HTTP/1.1 \r\n\r\n";
		dout.write(encoding.getBytes("ASCII"));
		String response=""+Integer.toHexString(din.readInt());
		for(int i=0;i<16;i++){
			response=response+""+Integer.toHexString(din.readInt());
		}
		if(Integer.parseInt(response.substring(131,133))==31){
			return 1;
		}else if(Integer.parseInt(response.substring(131,133))==30){
			return 0;
		}
		socket.close();
		din.close();
		dout.close();
		return 2;
	}
	public static void changeState() throws Exception{
		boolean changed=false;
		try{
			socket = new Socket(IpAddress2,Port2);
			if(error){
				changed=true;
				error=false;
			}
		}
		catch(Exception e) {
			error=true;
			skip=true;
			errorOe++;
			if(errorOe==1){
				errorOnce=true;
			}
			cantConnect++;
			//System.out.println(cantConnect);
			if(cantConnect<=3){
				addToError();
			}else{
				writeFailExcel(-2);
				cantConnect--;
				checkedInPrevious=true;
			}
			return;
		}
		if(!error){
			try{
				dout = new DataOutputStream(socket.getOutputStream());
				din = new DataInputStream(socket.getInputStream());
				//System.out.println("Connected streams to "+Site);
			}catch (IOException e) {
				error=true;
				skip=true;
				errorOe++;
				if(errorOe==1){
					errorOnce=true;
				}
				addToError();
				error=false;
				return;
			}
		}
		int saveState=getState();
		socket.close();
		dout.close();
		din.close();

		int newState=0;
		if(saveState==0){
			newState=1;
		}
		try{
			socket = new Socket(IpAddress2,Port2);
		}
		catch(Exception e) {
			error=true;
			skip=true;
			errorOe++;
			if(errorOe==1){
				errorOnce=true;
			}
			cantConnect++;
			//System.out.println(cantConnect);
			if(cantConnect<=3){
				addToError();
			}else{
				writeFailExcel(-2);
				cantConnect--;
				checkedInPrevious=true;
			}
			return;
		}
		if(!error){
			try{
				dout = new DataOutputStream(socket.getOutputStream());
				din = new DataInputStream(socket.getInputStream());
				//System.out.println("Connected streams to "+Site);
			}catch (IOException e) {
				error=true;
				skip=true;
				errorOe++;
				if(errorOe==1){
					errorOnce=true;
				}
				addToError();
				error=false;
				return;
			}
		}
		String encoding="GET /state.xml?relayState="+newState+" HTTP/1.1 \r\n\r\n";
		dout.write(encoding.getBytes("ASCII"));
		String response=""+Integer.toHexString(din.readInt());
		for(int i=0;i<16;i++){
			response=response+""+Integer.toHexString(din.readInt());
		}
		socket.close();
		dout.close();
		din.close();
		if(changed){
			error=true;
		}
		//			if(Integer.parseInt(response.substring(131,133))==31){
		//
		//			}else if(Integer.parseInt(response.substring(131,133))==30){
		//				
		//			}
	}

	public static void 	ibitDuel(int j) throws Exception{
		if(j==0){
			try{
				socket = new Socket(IpAddress,Port);
			}
			catch(Exception e) {
				if(testedA){
					return;
				}
				errorOe++;
				if(errorOe==1){
					errorOnce=true;
				}
				cantConnect++;
				//System.out.println(cantConnect);
				if(cantConnect<=3){
					if(firstTest){
						addError=true;
					}else{
						addToError();
						socket.close();
					}
				}else{
					writeFailExcel(-3);
					cantConnect--;
					checkedInPrevious=true;
				}
				return;
			}
		}else{
			try{
				socket = new Socket(IpAddress3,Port3);
			}
			catch(Exception e) {
				if(testedB){
					return;
				}
				errorOe++;
				if(errorOe==1){
					errorOnce=true;
				}
				cantConnect++;
				//System.out.println(cantConnect);
				if(cantConnect<=3){
					if(firstTest){
						addError=true;
					}else{
						addToError();
						socket.close();
					}
				}else{
					writeFailExcel(-4);
					cantConnect--;
					checkedInPrevious=true;
				}
				return;
			}
		}
		if(!error){
			try{
				dout = new DataOutputStream(socket.getOutputStream());
				din = new DataInputStream(socket.getInputStream());
				//System.out.println("Connected streams to "+Site);
			}catch (IOException e) {
				error=true;
				errorOe++;
				if(errorOe==1){
					errorOnce=true;
				}
				writeFailExcel(6);
				error=false;
				socket.close();
				try{
					dout.close();
					din.close();
				}catch (NullPointerException d){

				}
				return;
			}
		}
		String response;
		if(!error){
			try{
				response=""+Integer.toHexString(din.readInt());
				//System.out.println(response);
				if(Integer.parseInt(response.substring(0,2))==51){
					errorOe++;
					if(errorOe==1){
						errorOnce=true;
					}
					writeFailExcel(4);
					error=true;
				}
			}catch(Exception e){

			}
			if(!error){
				try{
					//try to run an IBIT test
					byte[] send;
					send=new byte[3];
					send[0]=69;
					send[1]=00;
					send[2]=00;
					dout.write(send);
					//System.out.println("Sending IBIT REQ to "+Site);
				}catch(IOException e){
					error=true;
					errorOe++;
					if(errorOe==1){
						errorOnce=true;
					}
					writeFailExcel(6);
					error=false;
					socket.close();
					try{
						dout.close();
						din.close();
					}catch (NullPointerException d){

					}
					return;
				}
				//will try to add the returning information to an array list 
				try{
					socket.setSoTimeout(20000);
					//System.out.println("reciving IBIT IND from "+Site);
					int i=0;
					while (i<9){
						i++;
						recivedData.add(Integer.toHexString(din.readByte()));
					}
					//						for(int k=0;k<recivedData.size();k++){
					//							System.out.print(recivedData.get(k));
					//						}
					//						System.out.println("");

					if(!(recivedData.get(0).equals("6d"))){
						error=true;
						errorOe++;
						if(errorOe==1){
							errorOnce=true;
						}
						writeFailExcel(7);
						error=false;
						socket.close();
						try{
							dout.close();
							din.close();
						}catch (NullPointerException d){

						}
						return;
					}
				}catch(IOException  e){
					try{
						//System.out.println(Integer.parseInt(recivedData.get(0),16));
						if(Integer.parseInt(recivedData.get(0),16)!=109){
							if(Integer.parseInt(recivedData.get(0),16)==81){
								writeFailExcel(5);
								error=true;
							}
						}
					}catch(IndexOutOfBoundsException a){
						errorOe++;
						error=true;
						skip=true;
						if(errorOe==1){
							errorOnce=true;
						}
						Sequelch++;
						if(Sequelch==3){
							if(!testedA&&!testedB){
								writeFailExcel(-1);
							}else if(testedA||testedB){
								writeFailExcel(-5);
							}
							skip=true;
							Sequelch--;
							error=true;

						}else{
							error=true;
							skip=true;
							addToError();
						}
						recivedData.removeAll(recivedData);
						error=true;
						skip=true;
					}
				}
				if(!error&&(!testedA&&j==0||!testedB&&j==1)){
					//System.out.println(recivedData.size());
					if(recivedData.size()!=9||Integer.parseInt(recivedData.get(3),16)==Integer.parseInt(recivedData.get(4),16)){
						if(recivedData.size()!=0){
							recivedData.removeAll(recivedData);
							socket.close();
							dout.close();
							din.close();
							error=true;
							errorOe++;
							if(errorOe==1){
								errorOnce=true;
							}
							writeFailExcel(0);

							recivedData.removeAll(recivedData);
							error=true;
						}else{
							skip=true;
							errorOe++;
							error=true;
							if(errorOe==1){
								errorOnce=true;
							}
							Sequelch++;
							if(Sequelch==3){
								writeFailExcel(-1);
								Sequelch--;

								error=true;
							}else{
								error=true;

								addToError();
							}
							recivedData.removeAll(recivedData);
							error=true;

						}
					}if(!error&&(!testedA&&j==0||!testedB&&j==1)){
						if(j==0){
							try{
								socket.close();
								socket = new Socket(IpAddress,Port);
								dout = new DataOutputStream(socket.getOutputStream());
								din = new DataInputStream(socket.getInputStream());
							}catch(Exception e){
								error=true;
								errorOe++;
								if(errorOe==1){
									errorOnce=true;
								}
								addToError();

							}
							try{
								//Health Request
								byte[] send;
								send=new byte[3];
								send[0]=35;
								send[1]=00;
								send[2]=00;
								dout.write(send);
							}catch(Exception e){
								error=true;
								errorOe++;
								if(errorOe==1){
									errorOnce=true;
								}
								writeFailExcel(8);
								error=false;
								socket.close();
								try{
									dout.close();
									din.close();
								}catch (NullPointerException d){

								}
								return;
							}
							//will try to add the returning information to an array list 
							response="";
							try{
								int i=0;
								while (i<9){
									i++;
									recivedData.add(Integer.toHexString(din.readByte()));
								}
							}catch(IOException e){
								error=true;
								errorOe++;
								if(errorOe==1){
									errorOnce=true;
								}
								writeFailExcel(8);
								error=false;
								socket.close();
								try{
									dout.close();
									din.close();
								}catch (NullPointerException d){

								}
								return;
							}
						}else{
							try{
								socket.close();
								socket = new Socket(IpAddress3,Port3);
								dout = new DataOutputStream(socket.getOutputStream());
								din = new DataInputStream(socket.getInputStream());
							}catch(Exception e){
								error=true;
								errorOe++;
								if(errorOe==1){
									errorOnce=true;
								}
								writeFailExcel(8);
								error=false;
								socket.close();
								try{
									dout.close();
									din.close();
								}catch (NullPointerException d){

								}
								return;
							}
							try{
								//Health Request
								byte[] send;
								send=new byte[3];
								send[0]=35;
								send[1]=00;
								send[2]=00;
								dout.write(send);
							}catch(Exception e){
								error=true;
								errorOe++;
								if(errorOe==1){
									errorOnce=true;
								}
								writeFailExcel(8);
								error=false;
								socket.close();
								try{
									dout.close();
									din.close();
								}catch (NullPointerException d){

								}
								return;
							}
							//will try to add the returning information to an array list 
							response="";
							try{
								int i=0;
								while (i<9){
									i++;
									recivedData.add(Integer.toHexString(din.readByte()));
								}
							}catch(IOException e){
								error=true;
								errorOe++;
								if(errorOe==1){
									errorOnce=true;
								}
								writeFailExcel(8);
								error=false;
								socket.close();
								try{
									dout.close();
									din.close();
								}catch (NullPointerException d){

								}
								return;
							}
						}
					}
				}
				checkedInPrevious=true;
				if(!skip){
					if(error){
						error=false;
						dout.close();
						din.close();
						socket.close();
					}else{
						dout.close();
						din.close();
						socket.close();
						if((!testedA&&j==0||!testedB&&j==1)){
							checkLimits();
						}
						recivedData.removeAll(recivedData);
					}
				}else{
					error=false;
					dout.close();
					din.close();
					socket.close();
				}
			}
		}


	}
	public static void ibitForDuel() throws Exception{
		try{
			socket = new Socket(IpAddress2,Port2);
		}
		catch(Exception e) {
			error=true;
			errorOe++;
			if(errorOe==1){
				errorOnce=true;
			}
			cantConnect++;
			//	System.out.println(cantConnect);
			if(cantConnect<=3){
				addToError();
			}else{
				writeFailExcel(-2);
				cantConnect--;
				checkedInPrevious=true;
			}
			return;
		}
		if(!error){
			try{
				dout = new DataOutputStream(socket.getOutputStream());
				din = new DataInputStream(socket.getInputStream());
				//System.out.println("Connected streams to "+Site);
			}catch (IOException e) {
				error=true;
				errorOe++;
				if(errorOe==1){
					errorOnce=true;
				}
				addToError();
				error=false;
				return;
			}
		}
		if(getState()==0){
			socket.close();
			din.close();
			dout.close();
			testingStateA=true;
			firstTest=true;
			ibitDuel(0);
			firstTest=false;
			testingStateA=false;
			if(!skip){
				changeState();
				if(!skip){
					testingStateB=true;
					ibitDuel(1);
					testingStateB=false;
					changeState();
					if(addError){
						addToError();
						addError=false;
					}
				}else{

				}
			}skip=false;
		}else{
			socket.close();
			din.close();
			dout.close();
			testingStateB=true;
			firstTest=true;
			ibitDuel(1);
			firstTest=false;
			testingStateB=false;
			if(!skip){
				changeState();
				if(!skip){
					testingStateA=true;
					ibitDuel(0);
					testingStateA=false;
					changeState();
					if(addError){
						addToError();
						addError=false;
					}
				}
			}skip=false;
		}
	}
	/**
	 * adds the site to the array list that contains sites that could not be connected to
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 * @throws IOException 
	 */
	public static void addToError() throws IOException{
		errorSites.add(Site);
		String port=""+Port;
		errorSites.add(IpAddress);
		errorSites.add(port);
		if(duelSite){
			errorSites.add("duel");
			errorSites.add(IpAddress2);
			String port2=""+Port2;
			errorSites.add(port2);
			errorSites.add(State);
			errorSites.add(Site2);
			errorSites.add(IpAddress3);
			String port3=""+Port3;
			errorSites.add(port3);
			errorSites.add(State2);
			if(testedA){
				errorSites.add("yes");
			}if(testedB){
				errorSites.add("yes1");
			}if(!testedA&&!testedB){
				errorSites.add("none");
			}
			testedA=false;
			testedB=false;
		}
		return;
	}
	/**
	 * connects to and runs IBIT and health tests on radio
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 */
	public static void ibit() throws Exception {
		try{
			Thread.sleep(4000);
		}catch(InterruptedException ie){

		}
		error=false;
		skip=false;
		checkedInPrevious=false;
		if(duelSite){
			ibitForDuel();
		}else{
			try{
				socket = new Socket(IpAddress,Port);
			}
			catch(Exception e){
				error=true;
				errorOe++;
				if(errorOe==1){
					errorOnce=true;
				}
				cantConnect++;
				if(cantConnect<=3){
					addToError();
				}else{
					writeFailExcel(3);
					cantConnect--;
					error=true;
				}
			}
			if(!error){
				try{
					dout = new DataOutputStream(socket.getOutputStream());
					din = new DataInputStream(socket.getInputStream());
					//System.out.println("Connected streams to "+Site);
				}catch (IOException e) {
					error=true;
					errorOe++;
					if(errorOe==1){
						errorOnce=true;
					}
					writeFailExcel(6);
					error=false;
					socket.close();
					try{
						dout.close();
						din.close();
					}catch (NullPointerException d){

					}
					return;
				}
				String response;
				if(!error){
					try{
						response=""+Integer.toHexString(din.readInt());
						//System.out.println(response);
						if(Integer.parseInt(response.substring(0,2))==51){
							errorOe++;
							if(errorOe==1){
								errorOnce=true;
							}
							writeFailExcel(4);
							error=true;
						}
					}catch(Exception e){
						error=true;
						errorOe++;
						if(errorOe==1){
							errorOnce=true;
						}
						addToError();
					}
					if(!error){
						try{
							//try to run an IBIT test
							byte[] send;
							send=new byte[3];
							send[0]=69;
							send[1]=00;
							send[2]=00;
							dout.write(send);
							//System.out.println("Sending IBIT REQ to "+Site);
						}catch(IOException e){
							error=true;
							errorOe++;
							if(errorOe==1){
								errorOnce=true;
							}
							writeFailExcel(6);
							error=false;
							socket.close();
							try{
								dout.close();
								din.close();
							}catch (NullPointerException d){

							}
							return;
						}
						//will try to add the returning information to an array list 
						try{
							socket.setSoTimeout(20000);
							//System.out.println("reciving IBIT IND from "+Site);
							int i=0;
							while (i<9){
								i++;
								recivedData.add(Integer.toHexString(din.readByte()));
							}
							if(!(recivedData.get(0).equals("6d"))){
								error=true;
								errorOe++;
								if(errorOe==1){
									errorOnce=true;
								}
								writeFailExcel(7);
								error=false;
								socket.close();
								try{
									dout.close();
									din.close();
								}catch (NullPointerException d){

								}
								return;
							}
						}catch(IOException  e){
							try{
								//System.out.println(Integer.parseInt(recivedData.get(0),16));
								if(Integer.parseInt(recivedData.get(0),16)!=109){
									if(Integer.parseInt(recivedData.get(0),16)==81){
										writeFailExcel(5);
										return;
									}
								}
							}catch(IndexOutOfBoundsException a){
								errorOe++;
								error=true;
								if(errorOe==1){
									errorOnce=true;
								}
								Sequelch++;
								if(Sequelch==3){
									writeFailExcel(-1);
									Sequelch--;
								}else{
									addToError();
								}
								recivedData.removeAll(recivedData);
								error=true;
							}
						}
						if(!error){
							//System.out.println(recivedData.size());
							if(recivedData.size()!=9||Integer.parseInt(recivedData.get(3),16)==Integer.parseInt(recivedData.get(4),16)){
								if(recivedData.size()!=0){
									recivedData.removeAll(recivedData);
									socket.close();
									socket = new Socket(IpAddress,Port);
									dout = new DataOutputStream(socket.getOutputStream());
									din = new DataInputStream(socket.getInputStream());
									try{
										//Health Request
										byte[] send;
										send=new byte[3];
										send[0]=35;
										send[1]=00;
										send[2]=00;
										dout.write(send);
									}catch(Exception e){
										error=true;
										errorOe++;
										if(errorOe==1){
											errorOnce=true;
										}
										writeFailExcel(8);
										error=false;
										socket.close();
										try{
											dout.close();
											din.close();
										}catch (NullPointerException d){

										}
										return;
									}
									//will try to add the returning information to an array list 
									response="";
									try{
										int i=0;
										while (i<9){
											i++;
											recivedData.add(Integer.toHexString(din.readByte()));
										}
									}catch(IOException e){
										error=true;
										errorOe++;
										if(errorOe==1){
											errorOnce=true;
										}
										writeFailExcel(8);
										error=false;
										socket.close();
										try{
											dout.close();
											din.close();
										}catch (NullPointerException d){

										}
										return;
									}
									errorOe++;
									if(errorOe==1){
										errorOnce=true;
									}
									writeFailExcel(0);
									recivedData.removeAll(recivedData);
									return;
								}else{
									errorOe++;
									error=true;
									if(errorOe==1){
										errorOnce=true;
									}
									Sequelch++;
									if(Sequelch==3){
										writeFailExcel(-1);
										Sequelch--;

									}else{
										error=true;
										addToError();
									}
									recivedData.removeAll(recivedData);
									error=true;
								}
							}if(!error){
								try{
									socket.close();
									socket = new Socket(IpAddress,Port);
									dout = new DataOutputStream(socket.getOutputStream());
									din = new DataInputStream(socket.getInputStream());
								}catch(Exception e){
									error=true;
									errorOe++;
									if(errorOe==1){
										errorOnce=true;
									}
									addToError();
								}
								try{
									//Health Request
									byte[] send;
									send=new byte[3];
									send[0]=35;
									send[1]=00;
									send[2]=00;
									dout.write(send);
								}catch(Exception e){
									error=true;
									errorOe++;
									if(errorOe==1){
										errorOnce=true;
									}
									writeFailExcel(8);
									error=false;
									socket.close();
									try{
										dout.close();
										din.close();
									}catch (NullPointerException d){

									}
									return;
								}
								//will try to add the returning information to an array list 
								response="";
								try{
									int i=0;
									while (i<9){
										i++;
										recivedData.add(Integer.toHexString(din.readByte()));
									}
								}catch(IOException e){
									error=true;
									errorOe++;
									if(errorOe==1){
										errorOnce=true;
									}
									writeFailExcel(8);
									error=false;
									socket.close();
									try{
										dout.close();
										din.close();
									}catch (NullPointerException d){

									}
									return;
								}
							}
						}
					}
				}
			}
		}
		duelSite=false;
		testedA=false;
		testedB=false;
		if(!checkedInPrevious){
			if(error){
				error=false;
				try{
					socket.close();
					dout.close();
					din.close();
				}catch (NullPointerException e){

				}
			}else{
				dout.close();
				din.close();
				socket.close();
				checkLimits();
			}
		}else{
			checkedInPrevious=false;
		}
	} 
	/**
	 * sets the program up in order to run IBIT and health test
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 */
	public static void testSite(String command) throws Exception {
		duelSite=false;
		if(!command.isEmpty()){
			try{
				StringTokenizer st = new StringTokenizer(command, ",");
				Site=st.nextToken();
				//System.out.println(Site);
				IpAddress=st.nextToken();
				try{
					Port=Integer.parseInt(st.nextToken());
				}catch (NumberFormatException e){
					Port=10555;
				}
				if(st.nextToken().equals("yes")){
					duelSite=true;
					State=st.nextToken();
					IpAddress2=st.nextToken();
					try{
						Port2=Integer.parseInt(st.nextToken());
					}catch (NumberFormatException e){
						Port2=80;
					}
					Site2=st.nextToken();
					IpAddress3=st.nextToken();
					try{
						Port3=Integer.parseInt(st.nextToken());
					}catch (NumberFormatException e){
						Port3=10555;
					}
					State2=st.nextToken();
				}
				bar=new statusBar(Site);
				bar.setVisible(true);
				ibit();
				bar.setVisible(false);
			}catch(NoSuchElementException e){

			}
		}
	}
	/**
	 * Sends email the user letting them know that not all excel sheets are closed
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 */
	public static void sendEmail() throws MessagingException {
		String host = emailSender.get(1);
		String from = emailSender.get(0);
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		Session session = Session.getInstance(props);
		//		props.put("mail.smtp.host", host);
		//		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		//message.setFrom(new InternetAddress(from));
		message.setFrom(new InternetAddress(from));
		InternetAddress[] toAddress = new InternetAddress[emailList.size()];

		// To get the array of addresses
		for( int i=0; i < emailList.size(); i++ ) { // changed from a while loop
			toAddress[i] = new InternetAddress(emailList.get(i));
		}


		for( int u=0; u < toAddress.length; u++) { // changed from a while loop
			message.addRecipient(Message.RecipientType.TO, toAddress[u]);
		}
		// Set Subject: header field
		message.setSubject("Excel Sheet could not be found");

		// Create the message part 
		BodyPart messageBodyPart = new MimeBodyPart();

		// Fill the message
		messageBodyPart.setText("Please make sure all excel sheets are closed and run the program again");

		// Create a multipar message
		Multipart multipart = new MimeMultipart();

		// Set text message part
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment

		// Send the complete message parts
		message.setContent(multipart );

		// Send message
		ErrorMessage newmessage=new ErrorMessage("sending");
		newmessage.setVisible(true);
		boolean sent=false;
		int numtimes = 0;
		while(!sent){
			try{
				if(numtimes>10){
					emailNotWorking newFrame=new emailNotWorking();
					boolean entered=false;
					while(!entered){
						newFrame.setVisible(true);
						entered=newFrame.thingsEntered();
					}
					from=newFrame.getEmail();
					host=newFrame.getHost();
					newFrame.setVisible(false);
					newFrame.reset();
					numtimes=0;
					props = System.getProperties();
					props.put("mail.smtp.host", host);
					session = Session.getDefaultInstance(props, null);
					message = new MimeMessage(session);
					//message.setFrom(new InternetAddress(from));
					message.setFrom(new InternetAddress(from));
					toAddress = new InternetAddress[emailList.size()];

					// To get the array of addresses
					for( int i=0; i < emailList.size(); i++ ) { // changed from a while loop
						toAddress[i] = new InternetAddress(emailList.get(i));
					}


					for( int u=0; u < toAddress.length; u++) { // changed from a while loop
						message.addRecipient(Message.RecipientType.TO, toAddress[u]);
					}
					// Set Subject: header field
					message.setSubject("Excel Sheet could not be found");

					// Create the message part 
					messageBodyPart = new MimeBodyPart();

					// Fill the message
					messageBodyPart.setText("Please make sure all excel sheets are closed and run the program again");


					// Create a multipar message
					multipart = new MimeMultipart();

					// Set text message part
					multipart.addBodyPart(messageBodyPart);

					// Part two is attachment

					// Send the complete message parts
					message.setContent(multipart );
					numtimes=0;
				}
				Transport.send(message);
				sent=true;
				newmessage.setVisible(false);
			}catch(Exception e){
				numtimes++;
				newmessage.setVisible(false);
				String save=e.toString();
				newmessage=new ErrorMessage("error in excelsheets"+save);
				while(!newmessage.thingsEntered()){
					newmessage.setVisible(true);
				}
				newmessage.setVisible(false);
			}
			System.exit(0);
		}

	}
	/**
	 * Sends email to the proper people who can fix radios that has failed 
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 */
	public static void SendFileEmail() throws AddressException, MessagingException, IOException{
		createFailedExcel();
		String host = emailSender.get(1);
		String from = emailSender.get(0);
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		Session session = Session.getInstance(props);
		MimeMessage message = new MimeMessage(session);
		//message.setFrom(new InternetAddress(from));
		message.setFrom(new InternetAddress(from));
		InternetAddress[] toAddress = new InternetAddress[emailList.size()];

		// To get the array of addresses
		for( int i=0; i < emailList.size(); i++ ) { // changed from a while loop
			toAddress[i] = new InternetAddress(emailList.get(i));
		}


		for( int u=0; u < toAddress.length; u++) { // changed from a while loop
			message.addRecipient(Message.RecipientType.TO, toAddress[u]);
		}
		// Set Subject: header field
		message.setSubject(CommCenter+": VHF failed sites");

		// Create the message part 
		BodyPart messageBodyPart = new MimeBodyPart();

		// Fill the message
		String str="<html><body><table><table border=4><tr>";
		for(int i=0;i<savedFailedData.size();i++){
			if(savedFailedData.get(i).equals("End")){
				str=str+"</tr>";
				if(i+1<savedFailedData.size()){
					str=str+"<tr>";
				}
			}else{
				str=str+"<td>"+savedFailedData.get(i)+"</td>";
			}
		}
		str=str+"</table></body></html>";
		messageBodyPart.setContent(str,"text/html");

		// Create a multipar message
		Multipart multipart = new MimeMultipart();

		// Set text message part
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		messageBodyPart = new MimeBodyPart();
		String filename = newtodayfailed;
		DataSource source = new FileDataSource(filename);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		multipart.addBodyPart(messageBodyPart);

		// Send the complete message parts
		message.setContent(multipart );

		// Send message
		ErrorMessage newmessage=new ErrorMessage("sending");
		boolean sent=false;
		int numtimes = 0;
		while(!sent){
			try{
				if(numtimes>10){
					emailNotWorking newFrame=new emailNotWorking();
					boolean entered=false;
					while(!entered){
						newFrame.setVisible(true);
						entered=newFrame.thingsEntered();
					}
					from=newFrame.getEmail();
					host=newFrame.getHost();
					newFrame.setVisible(false);
					newFrame.reset();
					numtimes=0;
					props = System.getProperties();
					props.put("mail.smtp.host", host);
					session = Session.getInstance(props);
					//session = Session.getDefaultInstance(props, null);
					message = new MimeMessage(session);
					//message.setFrom(new InternetAddress(from));
					message.setFrom(new InternetAddress(from));
					toAddress = new InternetAddress[emailList.size()];

					// To get the array of addresses
					for( int i=0; i < emailList.size(); i++ ) { // changed from a while loop
						toAddress[i] = new InternetAddress(emailList.get(i));
					}


					for( int u=0; u < toAddress.length; u++) { // changed from a while loop
						message.addRecipient(Message.RecipientType.TO, toAddress[u]);
					}
					// Set Subject: header field
					message.setSubject(CommCenter+": VHF failed sites");

					// Create the message part 
					messageBodyPart = new MimeBodyPart();

					// Fill the message
					 str="<html><body><table><table border=4><tr>";
					for(int i=0;i<savedFailedData.size();i++){
						if(savedFailedData.get(i).equals("End")){
							str=str+"</tr>";
							if(i+1<savedFailedData.size()){
								str=str+"<tr>";
							}
						}else{
							str=str+"<td>"+savedFailedData.get(i)+"</td>";
						}
					}
					str=str+"</table></body></html>";
					messageBodyPart.setContent(str,"text/html");


					// Create a multipar message
					multipart = new MimeMultipart();

					// Set text message part
					multipart.addBodyPart(messageBodyPart);

					// Part two is attachment
					messageBodyPart = new MimeBodyPart();
					filename = newtodayfailed;
					source = new FileDataSource(filename);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(filename);
					multipart.addBodyPart(messageBodyPart);

					// Send the complete message parts
					message.setContent(multipart );
					numtimes=0;
				}
				Transport.send(message);
				sent=true;
				newmessage.setVisible(false);
			}catch(Exception e){
				numtimes++;
				newmessage.setVisible(false);
				String save=e.toString();
				newmessage=new ErrorMessage("Trying to send email"+save);
				while(!newmessage.thingsEntered()){
					newmessage.setVisible(true);
				}
				newmessage.setVisible(false);
			}
		}
	}
	/**
	 * Will get all three excel files needed for todays test
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none 
	 */	
	@SuppressWarnings("deprecation")
	public static void setFiles(){
		int date=newDate.getDate();
		int date2=date-1;
		boolean changeyear=false;
		int year=newDate.getYear()+1900-2000;
		int month=newDate.getMonth()+1;
		if(date==1){
			if(month==4||month==6||month==9||month==11){
				date2=31;
			}else if(month==1||month==5||month==7||month==8||month==10||month==12){
				date2=30;				
			}else{
				date2=29;
			}
		}
		if(changeyear){
			year=year-1;
		}
		int month2=month-1;
		String yesterdayPassed="IBIT-"+month+date2;
		FileStructure newfile= new FileStructure(""," "," ");
		String todayPassed="IBIT-"+month2+date;
		newfile= new FileStructure(destination,todayPassed,"Passed");
		okFile=new File(newfile.getFile().toString());
		if(okFile==null){
			month2=month2-1;
			todayPassed="IBIT-"+month2+date;
			newfile= new FileStructure(destination,todayPassed,"Passed");
			okFile=new File(newfile.getFile().toString());
		}
		if(okFile==null){
			month2=month2-1;
			todayPassed="IBIT-"+month2+date;
			newfile= new FileStructure(destination,todayPassed,"Passed");
			okFile=new File(newfile.getFile().toString());
		}
		newfile=new FileStructure(destination,todayPassed,"Failed");
		failFile=new File(newfile.getFile().toString());
		newtodayPassed="IBIT-"+month+"-"+date+"-"+year+"_Passed.xls";
		newtodayfailed="IBIT-"+month+"-"+date+"-"+year+"_Failed.xls";
		okFile.renameTo(new File(newtodayPassed));
		failFile.renameTo(new File(newtodayfailed));
		FileStructure newfiles= new FileStructure(destination,yesterdayPassed,"Passed");
		compareFile=new File(newfiles.getFile().toString());
	}
	/**
	 *Compares the findings of the tests on the current site to limits set in the configuration file
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none 
	 */
	public static void checkLimits() throws Exception {
		if(testingStateA){
			testedA=true;
		}else if(testingStateB){
			testedB=true;
		}
		if(Integer.parseInt(recivedData.get(0),16)==109){
			try{
				if(Integer.parseInt(recivedData.get(0),16)==81){
					failed=true;
					errorIndication=true;
				}if(Integer.parseInt(recivedData.get(5),16)<Integer.parseInt(limits.get(0))){
					failed=true;
					modualtionDepth=true;
				}int sens=(Integer) hexToDec(recivedData.get(6));
				String dada=Integer.toBinaryString(Integer.parseInt(recivedData.get(7),16));
				for(int i=0;i<dada.length();i++){
					if(Integer.parseInt(dada.substring(i,i+1))==1){
						seventhBit=true;
						failed=true;
					}
				}
				if(sens<Integer.parseInt(limits.get(1))){
					failed=true;
					sensitivity=true;			
				}String data=Integer.toBinaryString(Integer.parseInt(recivedData.get(8),16));
				for(int i=0;i<data.length();i++){
					if(Integer.parseInt(data.substring(i,i+1))==1){
						eightBit=true;
						failed=true;
					}
				}try{
					float refle=Integer.parseInt(recivedData.get(4),16);
					float forw=Integer.parseInt(recivedData.get(3),16);
					float ratio=(float) Math.sqrt(refle/forw);
					double swr=(1+ratio)/(1-ratio);
					DecimalFormat df1=new DecimalFormat("0.##");
					double newSWR=Double.parseDouble(df1.format(swr).toString());
					if(newSWR>=Integer.parseInt(limits.get(2))){
						failed=true;
						srw=true;
					}

				}catch(ArithmeticException e){
					failed=true;
				}
				if(Integer.parseInt(recivedData.get(9),16)==83){
					String dade="";
					try{
						dade=Integer.toBinaryString(Integer.parseInt(recivedData.get(12),16));
					}catch(NumberFormatException e){
						errorOe++;
						if(errorOe==1){
							errorOnce=true;
						}
						writeFailExcel(0);
						return;
					}
					//System.out.println(Integer.parseInt(recivedData.get(12)));

					for(int i=0;i<dade.length();i++){
						if(Integer.parseInt(dade.substring(i,i+1))==1){
							ninthBit=true;
							failed=true;
						}
					} 
					try{
						dade=Integer.toBinaryString(Integer.parseInt(recivedData.get(13),16));
					}catch(NumberFormatException e){
						errorOe++;
						if(errorOe==1){
							errorOnce=true;
						}
						writeFailExcel(0);
						return;
					}
					for(int i=0;i<dade.length();i++){
						if(Integer.parseInt(dade.substring(i,i+1))==1){
							tenthBit=true;
							failed=true;
						}
					}
				}
				if(failed&&(srw||sensitivity||modualtionDepth||errorIndication)){
					errorOe++;
					if(errorOe==1){
						errorOnce=true;
					}
					writeFailExcel(2);
					failed=false;
					recivedData.removeAll(recivedData);
				}else if(tenthBit||ninthBit||seventhBit||eightBit){
					errorOe++;
					if(errorOe==1){
						errorOnce=true;
					}
					writeFailExcel(-2);
				}else{
					checkPreviousDays();
				}
			}catch(IndexOutOfBoundsException e){
				errorOe++;
				if(errorOe==1){
					errorOnce=true;
				}
				addToError();
			}
		}
	}
	/**
	 * Compares the findings of the tests on the current site to the passed excel sheet from yesterday
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none 
	 */	
	public static void checkPreviousDays() throws Exception {
		try{                
			//create BufferedReader to read csv file
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader( new FileReader(compareFile));
			String strLine = "";
			StringTokenizer st = null;
			//read comma separated file line by line
			while( (strLine = br.readLine()) != null){
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ",");
				while(st.hasMoreTokens()){
					//display csv values
					if(st.nextToken().equals(Site)){
						st.nextToken();
						st.nextToken();
						st.nextToken();
						String previous=st.nextToken();
						if((Math.abs((Integer.parseInt(previous)-
								Integer.parseInt(recivedData.get(3),16))/(Integer.parseInt(previous)))*100)>=Integer.parseInt(limits.get(3))){
							failed=true;
							foward=true;
						}previous=st.nextToken();
						try{
							if((Math.abs((Integer.parseInt(previous)-
									Integer.parseInt(recivedData.get(4),16))/(Integer.parseInt(previous)))*100)>=Integer.parseInt(limits.get(3))){
								failed=true;
								refelcted=true;
							}
						}catch(ArithmeticException e){
							if(Integer.parseInt(previous)==0&&Integer.parseInt(recivedData.get(4),16)==0){

							}else{
								failed=true;
								refelcted=true;
							}
						}
						st.nextToken();
						previous=st.nextToken();
						if((Math.abs((Integer.parseInt(previous)-
								Integer.parseInt(recivedData.get(5),16)))/(Integer.parseInt(previous))*100)>=Integer.parseInt(limits.get(3))){
							failed=true;
							sensitv=true;
						}previous=st.nextToken();
						int test=(Integer) hexToDec(recivedData.get(6));
						String privious=previous;
						String prib="";
						for(int i=0;i<privious.length();i++){
							if(privious.substring(i,i+1).equals(" ")){
								break;
							}else{
								prib=prib+privious.substring(i,i+1);
							}
						}
						if((Math.abs((Integer.parseInt(prib)-test))/((Integer.parseInt(prib)))*100)>=Integer.parseInt(limits.get(3))){
							failed=true;
							modula=true;
						}
					}
				}
			}			
		}catch(Exception e){          
		}
		if(failed&&(foward||refelcted||sensitv||modula)){
			errorOe++;
			addToError();
			if(errorOe==1){
				errorOnce=true;
			}
			writeFailExcel(1);
		}else{
			writePassExcel();
		}
	}
	public String getSITE() {
		return Site;
	}
	public static void main(String[] arg) throws Exception {
		String desktopPath="";
		try{
			desktopPath = System.getProperty("user.home") + "\\Desktop";
		}catch (Exception e){
		}
		FileStructure newFileStructure=new FileStructure(desktopPath+"\\IBIT","testFile",".txt");
		boolean filenotFound=true;
		File file=null;
		Scanner fileScanner = null;
		while(filenotFound){
			String testfile="";
			try{
				if(testfile.equals("")){
					String des=newFileStructure.getFile().toString();
					file=new File(des);
					fileScanner=new Scanner(file);
					filenotFound=false;
				}else{
					fileScanner=new Scanner(file);
					filenotFound=false;
				}
			}catch(Exception e){
				errorPop newPop=new errorPop();
				while(!newPop.thingsEntered()){
					newPop.setVisible(true);
				}
				newPop.setVisible(false);
				destinationofConfigFile newConfig=new destinationofConfigFile();
				while(!newConfig.thingsEntered()){
					newConfig.setVisible(true);
				}
				testfile=newConfig.destination()+"\\testFile.txt";
				newConfig.setVisible(false);
			}
		}
		String command=fileScanner.nextLine();
		while(command.isEmpty()){
			command=fileScanner.nextLine();
		}
		String emailarray[] = command.split(",");
		for(int i=0;i<emailarray.length;i++){
			emailSender.add(emailarray[i]);
		}
		destination=desktopPath = System.getProperty("user.home") + "\\Desktop";
		setFiles();
		command=fileScanner.nextLine();
		while(command.isEmpty()){
			command=fileScanner.nextLine();
		}
		String array[] = command.split(",");
		for(int i=0;i<array.length;i++){
			emailList.add(array[i]);
		}
		sendPassFile=fileScanner.nextLine();
		while(sendPassFile.isEmpty()){
			sendPassFile=fileScanner.nextLine();
		}
		command=fileScanner.nextLine();
		while(command.isEmpty()){
			command=fileScanner.nextLine();
		}
		String limitsarray[] = command.split(",");
		for(int i=0;i<limitsarray.length;i++){
			limits.add(limitsarray[i]);
		}
		command=fileScanner.nextLine();
		while(command.isEmpty()){
			command=fileScanner.nextLine();
		}
		CommCenter=command;
		//System.out.println(CommCenter);
		boolean firstTime=true;
		boolean stoptesting=false;
		while(fileScanner.hasNextLine()){
			command=fileScanner.nextLine();
			while(command.isEmpty()){
				try{
					command=fileScanner.nextLine();
				}catch(NoSuchElementException e){
					stoptesting=true;
					break;
				}
			}if(!stoptesting){
				if(firstTime){
					firstTime=false;
					boolean testing =true;
					while(testing){

						testSite(command);
						testing=false;
					}
				}else{
					boolean testing =true;
					while(testing){
						testSite(command);
						testing=false;
					}
				}
			}else{
				break;
			}

		}
		boolean sent=false;
		boolean emailsent=false;
		while(errorSites.size()>0){
			reTestSites();
		}
		if(write){

			if(sendPassFile.equals("yes")){
				sendPass();
			}
		}

		if(writtenTo&&!sent){
			SendFileEmail();
			emailsent=true;
		}
		Complete newComplete=new Complete(emailsent);
		while(!newComplete.thingsEntered()){
			newComplete.setVisible(true);
		}		
		System.exit(0);
	}
	public static void sendPass() throws IOException, AddressException, MessagingException {
		createPassesExcel();
		String host = emailSender.get(1);
		String from = emailSender.get(0);
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message3 = new MimeMessage(session);
		//message.setFrom(new InternetAddress(from));
		message3.setFrom(new InternetAddress(from));
		InternetAddress[] toAddress = new InternetAddress[emailList.size()];

		// To get the array of addresses
		for( int i=0; i < emailList.size(); i++ ) { // changed from a while loop
			toAddress[i] = new InternetAddress(emailList.get(i));
		}
		for( int u=0; u < toAddress.length; u++) { // changed from a while loop
			message3.addRecipient(Message.RecipientType.TO, toAddress[u]);
		}
		// Set Subject: header field
		message3.setSubject(CommCenter+": VHF passed sites");

		// Create the message part 
		BodyPart messageBodyPart = new MimeBodyPart();

		// Fill the message
		String str="<html><body><table><table border=4><tr><td>Site</td><td>IP Address</td><td>FWD</td><td>REF</td><td>VSWR</td><td>MOD</td><td>SENS</td></tr>";
		str=str+"<tr>";
		for(int i=0;i<savedPassedData.size();i++){
			if(savedPassedData.get(i).contains("dBm")){
				str=str+"<td>"+savedPassedData.get(i)+"</td></tr>";
				if(i+1<savedPassedData.size()){
					str=str+"<tr>";
				}
			}else{
				str=str+"<td>"+savedPassedData.get(i)+"</td>";
			}
		}
		str=str+"</table></body></html>";
		messageBodyPart.setContent(str,"text/html");
		// Create a multipart message
		Multipart multipart = new MimeMultipart();

		// Set text message part
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		messageBodyPart = new MimeBodyPart();
		String filename = newtodayPassed;
		DataSource source = new FileDataSource(filename);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		multipart.addBodyPart(messageBodyPart);

		// Send the complete message parts
		message3.setContent(multipart);

		// Send message
		ErrorMessage newmessage=new ErrorMessage("sending");
		newmessage.setVisible(true);
		boolean sent=false;
		int numtimes = 0;
		while(!sent){
			try{
				if(numtimes>10){
					emailNotWorking newFrame=new emailNotWorking();
					boolean entered=false;
					while(!entered){
						newFrame.setVisible(true);
						entered=newFrame.thingsEntered();
					}
					from=newFrame.getEmail();
					host=newFrame.getHost();
					newFrame.setVisible(false);
					newFrame.reset();
					numtimes=0;
					props = System.getProperties();
					props.put("mail.smtp.host", host);
					session = Session.getDefaultInstance(props, null);
					message3 = new MimeMessage(session);
					//message.setFrom(new InternetAddress(from));
					message3.setFrom(new InternetAddress(from));
					toAddress = new InternetAddress[emailList.size()];

					// To get the array of addresses
					for( int i=0; i < emailList.size(); i++ ) { // changed from a while loop
						toAddress[i] = new InternetAddress(emailList.get(i));
					}


					for( int u=0; u < toAddress.length; u++) { // changed from a while loop
						message3.addRecipient(Message.RecipientType.TO, toAddress[u]);
					}
					// Set Subject: header field
					message3.setSubject(CommCenter+": VHF passed sites");

					// Create the message part 
					messageBodyPart = new MimeBodyPart();

					// Fill the message
					str="<html><body><table><table border=4><tr><td>Site</td><td>IP Address</td><td>FWD</td><td>REF</td><td>VSWR</td><td>MOD</td><td>SENS</td></tr>";
					str=str+"<tr>";
					for(int i=0;i<savedPassedData.size();i++){
						if(savedPassedData.get(i).contains("dBm")){
							str=str+"<td>"+savedPassedData.get(i)+"</td></tr>";
							if(i+1<savedPassedData.size()){
								str=str+"<tr>";
							}
						}else{
							str=str+"<td>"+savedPassedData.get(i)+"</td>";
						}
					}
					str=str+"</table></body></html>";
					messageBodyPart.setContent(str,"text/html");

					// Create a multipar message
					multipart = new MimeMultipart();

					// Set text message part
					multipart.addBodyPart(messageBodyPart);
					// Part two is attachment
					messageBodyPart = new MimeBodyPart();
					filename = newtodayPassed;
					source = new FileDataSource(filename);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(filename);
					multipart.addBodyPart(messageBodyPart);
					message3.setContent(multipart );
					numtimes=0;
				}
				Transport.send(message3);
				sent=true;
				newmessage.setVisible(false);
			}catch(Exception e){
				numtimes++;
				newmessage.setVisible(false);
				String save=e.toString();
				newmessage=new ErrorMessage("Trying to send email"+save);
				while(!newmessage.thingsEntered()){
					newmessage.setVisible(true);
				}
				newmessage.setVisible(false);
			}
		}
	}
}