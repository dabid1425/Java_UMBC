/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author dan
 */
@SuppressWarnings("serial")
public class NetWork extends javax.swing.JFrame {

	/**
	 * Creates new form NetWork
	 */
	public NetWork() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	public NetWork(ArrayList<String> arrayList) {
		initComponents();
		try{
			jList1.addMouseListener(mouseListener);
			jList2.addMouseListener(mouseListeners);
			String str="";
			boolean grab=true;
			for(int i=0;i<arrayList.size();i++){
				if(grab){
					str=str+arrayList.get(i)+",";
					grab=false;
				}
				if(arrayList.get(i).toLowerCase().equals("end")){
					grab=true;
				}
				sites.add(arrayList.get(i));
			}
			String[] splitString=str.split(",");
			jList1.setListData(splitString);
			str="Enter Test Sites";
			splitString=str.split(" ");
			jList2.setListData(splitString);
		}catch(Exception e){

		}
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
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();
		jScrollPane2 = new javax.swing.JScrollPane();
		jList2 = new javax.swing.JList();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jButton1.setText("Go");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jList1.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
			public int getSize() { return strings.length; }
			public Object getElementAt(int i) { return strings[i]; }
		});
		jScrollPane1.setViewportView(jList1);

		jList2.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
			public int getSize() { return strings.length; }
			public Object getElementAt(int i) { return strings[i]; }
		});
		jScrollPane2.setViewportView(jList2);

		jButton2.setText("Add");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("Remove");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jLabel1.setText("Network");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(162, 162, 162)
						.addComponent(jButton1)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
								.addGap(33, 33, 33)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(69, 69, 69)
												.addComponent(jButton3))
												.addGroup(layout.createSequentialGroup()
														.addGap(79, 79, 79)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(jLabel1)
																.addComponent(jButton2))))
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
																.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(49, 49, 49))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGap(47, 47, 47)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE))
												.addGroup(layout.createSequentialGroup()
														.addContainerGap()
														.addComponent(jLabel1)
														.addGap(31, 31, 31)
														.addComponent(jButton2)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jButton3)
														.addGap(59, 59, 59)))
														.addComponent(jButton1)
														.addGap(35, 35, 35))
				);

		pack();
	}// </editor-fold>                        

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		go=true;
	}                                        

	@SuppressWarnings("unchecked")
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		String[] items;
		try{
			for(int i=0;i<itemSelected.length;i++){
				for(int j=0;j<sites.size();j++){
					try{
						if(sites.get(j).equals(itemSelected[i])){
							while(!sites.get(j).toLowerCase().equals("end")){
								testData.add(sites.get(j));
								j++;
							}
							testData.add(sites.get(j));
							break;
						}
					}catch(Exception e){
						if(sites.get(j).equals(itemSelected[i])){
							while(!sites.get(j).toLowerCase().equals("end")){
								testData.add(sites.get(j));
								j++;
							}
							testData.add(sites.get(j));
						}
					}
				}
			}
		}catch(Exception e){

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
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		String[] items;
		try{
			for(int j=0;j<itemSelected2.length;j++){
				for(int i=0;i<testData.size();i++){
					try{
						if(testData.get(i).equals(itemSelected2[j])){
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
		}catch(Exception e){

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
			java.util.logging.Logger.getLogger(NetWork.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(NetWork.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(NetWork.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(NetWork.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new NetWork().setVisible(true);
			}
		});
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
	public boolean getGo(){
		return go;
	}
	public ArrayList<String> getTestData(){
		return testData;
	}
	private boolean go=false;
	private ArrayList<String> testData=new ArrayList<String>();
	private ArrayList<String> sites=new ArrayList<String>();
	private Object[] itemSelected;
	private Object[] itemSelected2;
	@SuppressWarnings("unused")
	private String[] splitStrings={""};
	// Variables declaration - do not modify                     
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JLabel jLabel1;
	@SuppressWarnings("rawtypes")
	private javax.swing.JList jList1;
	@SuppressWarnings("rawtypes")
	private javax.swing.JList jList2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	// End of variables declaration                   
}
