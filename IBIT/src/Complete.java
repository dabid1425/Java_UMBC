/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dan
 */
@SuppressWarnings("serial")
public class Complete extends javax.swing.JFrame {
	private boolean enter=false;
	private static boolean email=false;
	/**
	 * Creates new form Complete
	 * @param emailsent 
	 */
	public Complete(boolean emailsent) {
		email=emailsent;
		initComponents(emailsent);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 * @param emailsent 
	 */

	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents(boolean emailsent) {
		 jLabel1 = new javax.swing.JLabel();
	        jButton1 = new javax.swing.JButton();
	        jLabel2 = new javax.swing.JLabel();

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
	        if(emailsent){
	        	jLabel1.setText("Program Completed email sent");
	        }else{
	        	jLabel1.setText("Program Completed ");
	        }
	        

	        jButton1.setText("OK");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton1ActionPerformed(evt);
	            }
	        });

	        jLabel2.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("arinc_logo.jpg"))); // NOI18N
		      jLabel2.setMaximumSize(new java.awt.Dimension(180, 55));

		      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(81, 81, 81)
		                        .addComponent(jLabel2))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(135, 135, 135)
		                        .addComponent(jLabel1))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(207, 207, 207)
		                        .addComponent(jButton1)))
		                .addContainerGap(130, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                .addContainerGap(59, Short.MAX_VALUE)
		                .addComponent(jLabel2)
		                .addGap(59, 59, 59)
		                .addComponent(jLabel1)
		                .addGap(70, 70, 70)
		                .addComponent(jButton1)
		                .addGap(49, 49, 49))
		        );

		        pack();
		    }// </editor-fold>


	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		enter=true;

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/*
		 * Set the Nimbus look and feel
		 */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Complete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Complete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Complete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Complete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/*
		 * Create and display the form
		 */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new Complete(email).setVisible(true);
			}
		});
	}
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	// End of variables declaration
	public boolean thingsEntered() {
		return enter;
	}
}
