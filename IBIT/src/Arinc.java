/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author dan
 */
@SuppressWarnings("serial")
public class Arinc extends javax.swing.JFrame {

	/**
	 * Creates new form Arinc
	 */
	@SuppressWarnings("unchecked")
	public Arinc() {
		desktopPath="";
		try{
			desktopPath = System.getProperty("user.home") + "\\Desktop";
		}catch (Exception e){
		}

		initComponents();
		Scanner fileScanner = null;
		try{
			FileInputStream file=new FileInputStream(new File(desktopPath+"\\IBIT\\allSites.txt"));
			fileScanner=new Scanner(file);
			while(fileScanner.hasNextLine()){
				String command=fileScanner.nextLine();	
				splitString = (command.split(","));
				for(int i=0;i<splitString.length;i++){
					FinalData.add(splitString[i]);
				}
			}
		}catch(Exception e){

		}
		String str="";
		boolean grab=true;
		for(int i=0;i<FinalData.size();i++){
			if(grab){
				str=str+FinalData.get(i)+",";
				grab=false;
			}
			if(FinalData.get(i).toLowerCase().equals("end")){
				grab=true;
			}
		}
		String[] splitString=str.split(",");
		jList1.setListData(splitString);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
	private void initComponents() {

		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jButton5 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jCheckBox1 = new javax.swing.JCheckBox();
		jLabel4 = new javax.swing.JLabel();
		jCheckBox2 = new javax.swing.JCheckBox();
		jCheckBox3 = new javax.swing.JCheckBox();
		jCheckBox4 = new javax.swing.JCheckBox();
		jCheckBox5 = new javax.swing.JCheckBox();
		jCheckBox6 = new javax.swing.JCheckBox();
		jCheckBox7 = new javax.swing.JCheckBox();
		jCheckBox8 = new javax.swing.JCheckBox();
		jCheckBox9 = new javax.swing.JCheckBox();
		jCheckBox10 = new javax.swing.JCheckBox();
		jLabel5 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();
		jScrollPane2 = new javax.swing.JScrollPane();
		jList2 = new javax.swing.JList();
		jList1.addMouseListener(mouseListener);
		jList2.addMouseListener(mouseListeners);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jButton1.setText("Change Site");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("Emai List ");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("Test Site that is not on the list ");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jButton4.setText("Run Test");
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		jLabel1.setText("Sites to be tested");

		jLabel2.setText("All Sites");

		jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\dan\\Desktop\\IBIT\\src\\arinc_logo.jpg")); // NOI18N

		jButton5.setText("Add");
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});

		jButton6.setText("Remove");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		jCheckBox1.setText("Foward");
		jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox1ActionPerformed(evt);
			}
		});

		jLabel4.setText("What do you want the program to do? ");

		jCheckBox2.setText("IBIT");
		jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox2ActionPerformed(evt);
			}
		});

		jCheckBox3.setText("Reset");
		jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox3ActionPerformed(evt);
			}
		});

		jCheckBox4.setText("Health Test");
		jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox4ActionPerformed(evt);
			}
		});

		jCheckBox5.setText("IBIT Boolean 1");
		jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox5ActionPerformed(evt);
			}
		});

		jCheckBox6.setText("Reflected");
		jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox6ActionPerformed(evt);
			}
		});

		jCheckBox7.setText("VSWR");
		jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox7ActionPerformed(evt);
			}
		});

		jCheckBox8.setText("Modulation ");
		jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox8ActionPerformed(evt);
			}
		});

		jCheckBox9.setText("IBIT Boolean 2");
		jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox9ActionPerformed(evt);
			}
		});

		jCheckBox10.setText("Sensitivity");
		jCheckBox10.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox10ActionPerformed(evt);
			}
		});

		jLabel5.setText("What do you want the program to return ");

		jList1.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
			public int getSize() { return strings.length; }
			public Object getElementAt(int i) { return strings[i]; }
		});
		jScrollPane1.setViewportView(jList1);

		jList2.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "Enter Test sites" };
			public int getSize() { return strings.length; }
			public Object getElementAt(int i) { return strings[i]; }
		});
		jScrollPane2.setViewportView(jList2);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jLabel3)
						.addGap(498, 498, 498))
						.addGroup(layout.createSequentialGroup()
								.addGap(40, 40, 40)
								.addComponent(jLabel4)
								.addGap(0, 0, Short.MAX_VALUE))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addGap(98, 98, 98)
										.addComponent(jCheckBox7)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jButton3)
										.addGap(170, 170, 170))
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
																.addGap(23, 23, 23)
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(layout.createSequentialGroup()
																				.addComponent(jCheckBox2)
																				.addGap(36, 36, 36)
																				.addComponent(jCheckBox3)
																				.addGap(42, 42, 42)
																				.addComponent(jCheckBox4)
																				.addGap(0, 0, Short.MAX_VALUE))
																				.addGroup(layout.createSequentialGroup()
																						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																								.addComponent(jLabel5)
																								.addGroup(layout.createSequentialGroup()
																										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																												.addGroup(layout.createSequentialGroup()
																														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																																.addComponent(jCheckBox5)
																																.addComponent(jCheckBox10))
																																.addGap(37, 37, 37))
																																.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																																		.addComponent(jCheckBox1)
																																		.addGap(77, 77, 77)))
																																		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																																				.addComponent(jCheckBox8)
																																				.addComponent(jCheckBox6)
																																				.addComponent(jCheckBox9))))
																																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																																						.addComponent(jButton1)
																																						.addComponent(jButton2)))))
																																						.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
																																								.addGap(101, 101, 101)
																																								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																																										.addGroup(layout.createSequentialGroup()
																																												.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																												.addGap(425, 425, 425)
																																												.addComponent(jButton5)
																																												.addGap(248, 438, Short.MAX_VALUE))
																																												.addGroup(layout.createSequentialGroup()
																																														.addComponent(jLabel2)
																																														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																														.addComponent(jLabel1)))))
																																														.addGap(225, 225, 225))
																																														.addGroup(layout.createSequentialGroup()
																																																.addGap(555, 555, 555)
																																																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																																																		.addComponent(jButton4)
																																																		.addComponent(jButton6))
																																																		.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																																																		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																																																				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																																																						.addContainerGap(1006, Short.MAX_VALUE)
																																																						.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																																						.addGap(238, 238, 238)))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGap(41, 41, 41)
						.addComponent(jLabel3)
						.addGap(50, 50, 50)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel1)
								.addComponent(jLabel2))
								.addGap(45, 45, 45)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(jButton5)
												.addGap(128, 128, 128)
												.addComponent(jButton6)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jButton2)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton1)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton3))
												.addGroup(layout.createSequentialGroup()
														.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
														.addComponent(jLabel5)
														.addGap(18, 18, 18)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																		.addComponent(jCheckBox10)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(jCheckBox5))
																		.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																				.addComponent(jCheckBox8)
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																				.addComponent(jCheckBox9)))
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(jCheckBox1)
																						.addComponent(jCheckBox6))
																						.addGap(13, 13, 13)
																						.addComponent(jCheckBox7)))
																						.addGap(18, 18, 18)
																						.addComponent(jLabel4)
																						.addGap(27, 27, 27)
																						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																								.addComponent(jCheckBox2)
																								.addComponent(jCheckBox3)
																								.addComponent(jCheckBox4)
																								.addComponent(jButton4))
																								.addGap(66, 66, 66))
																								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																										.addGroup(layout.createSequentialGroup()
																												.addGap(221, 221, 221)
																												.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addContainerGap(416, Short.MAX_VALUE)))
				);

		pack();
	}// </editor-fold>                        

	private  String desktopPath;
	private boolean REF=false;
	private boolean Bool1=false;
	private boolean SWR=false;
	private boolean Sens=false;
	private boolean Mod=false;
	private boolean FWR=false;
	private boolean Bool2=false;
	private boolean IBIT=false;
	private boolean reset=false;
	private boolean health=false;
	private boolean run=false;
	public boolean getRun(){
		return run;
	}    public boolean getIBIT(){
		return IBIT;
	}
	private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
		IBIT=true;
	}                                          
	public boolean getReset(){
		return reset;
	}
	private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {                                           
		reset=true;
	}                                          
	public boolean getHealth(){
		return health;
	}
	private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {                                           
		health=true;
	}                                          
	public boolean getFWR(){
		return FWR;
	}
	private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
		FWR=true;
	}                                          
	public boolean getBool1(){
		return Bool1;
	}
	private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {                                           
		Bool1=true;
	}                                          
	public boolean getREF(){
		return REF;
	}
	private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {                                           
		REF=true;
	}                                          
	public boolean getSWR(){
		return SWR;
	}
	private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {                                           
		SWR=true;
	}                                          
	public boolean getMod(){
		return Mod;
	}
	private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {                                           
		Mod=true;
	}                                          
	public boolean getBool2(){
		return Bool2;
	}
	private void jCheckBox9ActionPerformed(java.awt.event.ActionEvent evt) {                                           
		Bool2=true;
	}                                          
	public boolean getSens(){
		return Sens;
	}
	private void jCheckBox10ActionPerformed(java.awt.event.ActionEvent evt) {                                            
		Sens=true;
	}                                           

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		try{
			String newFile=desktopPath+"\\IBIT\\emailList.txt";
			Desktop newDesk=Desktop.getDesktop();
			newDesk.edit(new File(newFile));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}                                        

	MouseAdapter mouseListener = new MouseAdapter() {
		@SuppressWarnings("deprecation")
		public void mouseClicked(MouseEvent e) {
			itemSelected= jList1.getSelectedValues();
		}
	};
	MouseAdapter mouseListeners = new MouseAdapter() {
		@SuppressWarnings("deprecation")
		public void mouseClicked(MouseEvent e) {
			itemSelected2= jList2.getSelectedValues();
		}
	};

	@SuppressWarnings("unchecked")
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) { 
		FinalData.removeAll(FinalData);
		try{
			String newFile=desktopPath+"\\IBIT\\allSites.txt";
			Desktop newDesk=Desktop.getDesktop();
			boolean locked=true;
			newDesk.edit(new File(newFile));
			while(locked){
				File file = new File(newFile);
				if(file.renameTo(file)){
					locked=false;
					Scanner fileScanner = null;
					try{
						FileInputStream file2=new FileInputStream(new File(desktopPath+"\\IBIT\\allSites.txt"));
						fileScanner=new Scanner(file2);
						while(fileScanner.hasNextLine()){
							String command=fileScanner.nextLine();	
							splitString = (command.split(","));
							for(int i=0;i<splitString.length;i++){
								FinalData.add(splitString[i]);
							}
						}
					}catch(Exception e){

					}
					String str="";
					boolean grab=true;
					for(int i=0;i<FinalData.size();i++){
						if(grab){
							str=str+FinalData.get(i)+",";
							grab=false;
						}
						if(FinalData.get(i).toLowerCase().equals("end")){
							grab=true;
						}
					}
					String[] splitString=str.split(",");
					jList1.setListData(splitString);
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}                                        

	@SuppressWarnings("unchecked")
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		FinalData.removeAll(FinalData);
		try{
			String newFile=desktopPath+"\\IBIT\\allSites.txt";
			Desktop newDesk=Desktop.getDesktop();
			boolean locked=true;
			newDesk.edit(new File(newFile));
			while(locked){
				File file = new File(newFile);
				if(file.renameTo(file)){
					locked=false;
					Scanner fileScanner = null;
					try{
						FileInputStream file2=new FileInputStream(new File(desktopPath+"\\IBIT\\allSites.txt"));
						fileScanner=new Scanner(file2);
						while(fileScanner.hasNextLine()){
							String command=fileScanner.nextLine();	
							splitString = (command.split(","));
							for(int i=0;i<splitString.length;i++){
								FinalData.add(splitString[i]);
							}
						}
					}catch(Exception e){

					}
					String str="";
					boolean grab=true;
					for(int i=0;i<FinalData.size();i++){
						if(grab){
							str=str+FinalData.get(i)+",";
							grab=false;
						}
						if(FinalData.get(i).toLowerCase().equals("end")){
							grab=true;
						}
					}
					String[] splitString=str.split(",");
					jList1.setListData(splitString);
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}                                                                               

	@SuppressWarnings("unchecked")
	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) { 
		String[] items;
		for(int i=0;i<itemSelected.length;i++){
			for(int j=0;j<FinalData.size();j++){
				try{
					if(FinalData.get(j).equals(itemSelected[i])&&!FinalData.get(j+5).toLowerCase().equals("end")){
						while(!FinalData.get(j).toLowerCase().equals("end")){
							testData.add(FinalData.get(j));
							j++;
						}
						testData.add(FinalData.get(j));
						break;
					}
				}catch(Exception e){
					if(testData.get(i).equals(itemSelected2[i])){
						while(!FinalData.get(j).toLowerCase().equals("end")){
							testData.add(FinalData.get(j));
							j++;
						}
						testData.add(FinalData.get(j));
					}
				}
			}
		}
		String str="";
		boolean grab=true;
		for(int i=0;i<testData.size();i++){
			if(grab){
				str=str+testData.get(i)+",";
				grab=false;
			}
			if(testData.get(i).toLowerCase().equals("end")){
				grab=true;
			}
		}
		items=str.split(",");
		jList2.setListData(items);
	}                                        

	@SuppressWarnings("unchecked")
	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {   
		String[] items;
		for(int j=0;j<itemSelected2.length;j++){
			for(int i=0;i<testData.size();i++){
				try{
					if(testData.get(i).equals(itemSelected2[j])&&!testData.get(i+5).toLowerCase().equals("end")){
						while(!testData.get(i).toLowerCase().equals("end")){
							testData.remove(i);
						}
						testData.remove(i);
						break;
					}
				}catch(Exception e){
					if(testData.get(i).equals(itemSelected2[j])){
						while(!testData.get(i).toLowerCase().equals("end")){
							testData.remove(i);
						}
						testData.remove(i);
						break;
					}
				}
			}
		}
		if(testData.size()!=0){
			String str="";
			boolean grab=true;
			for(int i=0;i<testData.size();i++){
				if(grab){
					str=str+testData.get(i)+",";
					grab=false;
				}
				if(testData.get(i).toLowerCase().equals("end")){
					grab=true;
				}
			}
			items=str.split(",");
			jList2.setListData(items);
		}else{
			String[] strings = { "Enter Test sites" };
			jList2.setListData(strings);

		}
	}                                 


	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		run=true;
	}                                        

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Arinc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Arinc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Arinc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Arinc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Arinc().setVisible(true);
			}
		});
	}
	// Variables declaration - do not modify                     
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JCheckBox jCheckBox1;
	private javax.swing.JCheckBox jCheckBox10;
	private javax.swing.JCheckBox jCheckBox2;
	private javax.swing.JCheckBox jCheckBox3;
	private javax.swing.JCheckBox jCheckBox4;
	private javax.swing.JCheckBox jCheckBox5;
	private javax.swing.JCheckBox jCheckBox6;
	private javax.swing.JCheckBox jCheckBox7;
	private javax.swing.JCheckBox jCheckBox8;
	private javax.swing.JCheckBox jCheckBox9;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private String[] splitString=null;
	@SuppressWarnings("unused")
	private String[] splitStrings={""};
	private ArrayList<String> FinalData=new ArrayList<String>();
	private ArrayList<String> testData=new ArrayList<String>();
	private javax.swing.JLabel jLabel5;
	@SuppressWarnings("rawtypes")
	private javax.swing.JList jList1;
	@SuppressWarnings("rawtypes")
	private javax.swing.JList jList2;
	private Object[] itemSelected;
	private Object[] itemSelected2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	// End of variables declaration  
	// End of variables declaration                   
	public ArrayList<String> getTestSites() {
		return testData;
	}
}