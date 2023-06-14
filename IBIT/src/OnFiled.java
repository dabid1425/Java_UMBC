import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class OnFiled {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		ArincReturns newArincReturns=new ArincReturns();
		boolean done=false;
		while(true){
			newArincReturns.setVisible(true);
			if(!done){
				if(!newArincReturns.getRun()){
					if(newArincReturns.getAdd()){
						NetWork newNetWork=new NetWork(newArincReturns.getsites());
						while(!newNetWork.getGo()){
							newNetWork.setVisible(true);
						}
						newNetWork.setVisible(false);
						newArincReturns.setAdd();
						newArincReturns.addToList(newNetWork.getTestData());	
					}

				}else{
					String str="Running\n";
					newArincReturns.setText(str);
					ArrayList<String> allTestData=newArincReturns.getTestData();
					for(int i=0;i<allTestData.size();i=i+5){
						String Site=allTestData.get(i);
						String IP=allTestData.get(i+1);
						int Port=Integer.parseInt(allTestData.get(i+2));
						String password=allTestData.get(i+3);
						AutomatedTelnetClient newAutomatedTelnetClient=new AutomatedTelnetClient(IP,Port,password);
						ArrayList<String> getboxdata=newArincReturns.getData();
						try{
							if(newAutomatedTelnetClient.getResponse().equals("\r\nPassword OK")){
								str=str+"Connected to "+Site+"\n";
								newArincReturns.setText(str);
								if(newArincReturns.getBox1()){
									str=str+" "+newAutomatedTelnetClient.sendCommand(getboxdata.get(1))+"\n";
									newArincReturns.setText(str);
								}if(newArincReturns.getBox2()){
									str=str+" "+newAutomatedTelnetClient.sendCommand(getboxdata.get(3))+"\n";
									newArincReturns.setText(str);
								}if(newArincReturns.getBox3()){
									str=str+" "+newAutomatedTelnetClient.sendCommand(getboxdata.get(5))+"\n";
									newArincReturns.setText(str);
								}if(newArincReturns.getBox4()){
									str=str+" "+newAutomatedTelnetClient.sendCommand(getboxdata.get(7))+"\n";
									newArincReturns.setText(str);
								}if(newArincReturns.getBox5()){
									str=str+" "+newAutomatedTelnetClient.sendCommand(getboxdata.get(9))+"\n";
									newArincReturns.setText(str);
								}if(newArincReturns.getBox6()){
									str=str+" "+newAutomatedTelnetClient.sendCommand(getboxdata.get(11))+"\n";
									newArincReturns.setText(str);
								}if(newArincReturns.getBox7()){
									str=str+" "+newAutomatedTelnetClient.sendCommand(getboxdata.get(13))+"\n";
									newArincReturns.setText(str);
								}if(newArincReturns.getBox8()){
									str=str+" "+newAutomatedTelnetClient.sendCommand(getboxdata.get(15))+"\n";
									newArincReturns.setText(str);
								}
							}else{
								throw new Exception();
							}
							newAutomatedTelnetClient.disconnect();
						}catch(Exception e){						
							str=str+" Could not connect to "+Site+"\n";
							newArincReturns.setText(str);
						}
					}
					if(newArincReturns.getSave()){
						String path="";
						try{
						path=newArincReturns.getPath();
						PrintWriter out = 
								new PrintWriter(path);
						out.println(str);
						out.close();
						}catch(Exception e){
							Excpetion newException=new Excpetion();
							newException.setText("Could not find "+path);
							while(!newException.getDone()){
								newException.setVisible(true);
							}
							System.exit(0);
						}
					}
					done=true;

				}
			}
		}

	}

}
