
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author atura
 */
public class TransactionHistor extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement st = null;
    String accountID;
    String IBAN;
    public TransactionHistor()
    {
        initComponents();
    }
    public TransactionHistor(ResultSet rs) throws SQLException {
        initComponents();
        this.rs = rs;
        accountID = rs.getString("ID");      
        IBAN = rs.getString("IBAN");
        conn = BankDBConnection.connectBankDB();
        try {
            show_transactions();
        } catch (SQLException ex) {
            Logger.getLogger(TransactionHistor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public ArrayList <Transactions> Transactionlist() throws SQLException{
        ArrayList <Transactions> Transactionlist = new ArrayList<>();
        try{
            rs.close();
            String query = "SELECT * FROM transactions WHERE IBAN = ? OR \"Account ID\" = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, IBAN);
            pst.setString(2, accountID);
            rs = pst.executeQuery();
            Transactions transactions;
            while (rs.next())
            {
                transactions = new Transactions(rs.getString("Transaction ID"), rs.getString("First_Name") , rs.getString("Last_Name"), TDate(), Damount());
                Transactionlist.add(transactions);
            }
        }catch(SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e)
        {
            
        }
        return Transactionlist;
    }
    
    public void show_transactions() throws SQLException
    {
        ArrayList <Transactions> Transactionlist = Transactionlist();
        DefaultTableModel model = (DefaultTableModel)Table.getModel();
        Object[] row = new Object[5];
        for(int i = 0; i < Transactionlist.size(); i++){
            row[0] = Transactionlist.get(i).getTID();
            row[1] = Transactionlist.get(i).getFName();
            row[2] = Transactionlist.get(i).getLName();
            row[3] = Transactionlist.get(i).getamount();
            row[4] = Transactionlist.get(i).getDate();
            model.addRow(row);
        }     
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        BackButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction ID", "First Name", "Last Name", "Amount", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        jScrollPane1.setViewportView(Table);

        BackButton.setLabel("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Transactions");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(681, 397));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        try {                                           
            String updateQuery = "SELECT * FROM accounts WHERE ID = ?";
            pst = conn.prepareStatement(updateQuery);
            pst.setString(1, accountID);
            rs = pst.executeQuery();
            setVisible(false);
            Home ob = null;
            try {
                ob = new Home(rs);
            } catch (SQLException ex) {
                Logger.getLogger(TransactionHistor.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.print("Homepage\n");
            ob.setVisible(true);
            dispose();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(TransactionHistor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BackButtonActionPerformed

    public String Damount() throws SQLException
    {
        String TraAccount = rs.getString("Account ID");
        if (TraAccount.equals(accountID))
        {           
            return ("+" + rs.getString("Amount"));
        }
        else
        {
            return ("-" + rs.getString("Amount"));
        }
            
    }
    
    public String TDate() throws SQLException
    {
        String Tdate = rs.getString("TDate");
        String Date = Tdate.substring(0,10);
        String Time = Tdate.substring(11,19);
        System.out.println(Time);
        System.out.println(Date);
        return (Date + " " + Time);
    }
    
    


    public static class Transactions {
        private String FName , LName , Date,TID , amount;

        public Transactions(String TID , String FName , String LName , String Date,String amount) {
            this.TID = TID;
            this.FName = FName;
            this.LName = LName;
            this.amount = amount;
            this.Date = Date;
        }
        public String getTID()
        {
                return TID;             
        }
        public String getamount()
        {
                return amount;             
        }
        public String getFName()
        {
                return FName;             
        }
        public String getLName()
        {
                return LName;             
        }
        public String getDate()
        {
                return Date;             
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
            java.util.logging.Logger.getLogger(TransactionHistor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransactionHistor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransactionHistor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransactionHistor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionHistor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JTable Table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
