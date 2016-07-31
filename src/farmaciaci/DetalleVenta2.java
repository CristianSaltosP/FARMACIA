/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DetalleVenta2.java
 *
 * Created on 19-jul-2015, 18:17:00
 */
package farmaciaci;


import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class DetalleVenta2 extends javax.swing.JInternalFrame {

    /** Creates new form DetalleVenta2 */
     DefaultTableModel model;
    public DetalleVenta2() {
        initComponents();
        //CargaTablaDetalle();
        NumeroFactura();
        CargarTablaDetalle();
    }
    public void Insertar()
    {
    
    conexion cc = new conexion();
    Connection cn = cc.conectar();
    String NUM_DET, COD_MED_P,CAN_MED_V,NUM_VEN_P ;
    String sql;
    //double PRE_MED;
    NUM_DET=txtNumero.getText();
    COD_MED_P=txtCodigoMed.getText();//.toUpperCase();
    //PRE_MED=Double.parseDouble(txtPreMed.getText());
    CAN_MED_V=txtCantidad.getText();
    NUM_VEN_P=txtNumeroVenta.getText();
    
    //java.util.Date fecha1 = txtFecCadMed.getDate();
    //String fecha = df.format(fecha1);
    //FEC_CAD=fecha; 
    
    sql= "insert into detalle_venta(NUM_DET,COD_MED_P,CAN_MED_V,NUM_VEN_P )values(?,?,?,?)";   
    PreparedStatement psd;
    
        try {
            psd = cn.prepareStatement(sql);
            psd.setString(1, NUM_DET);
            psd.setString(2, COD_MED_P);
            psd.setString(3, CAN_MED_V);
            psd.setString(4, NUM_VEN_P);
           // psd.setString(5, FEC_CAD);
         
         
                     int n = psd.executeUpdate();
            //executeUpdate(Pertence al PreparedStatement) == Me devuelve un valor entero si se afecta a la BDD
            if(n>0){
                //CopiarImagen(txtRuta.getText(), dirdestino);
                JOptionPane.showMessageDialog(null, "INSERTADO"); 
               //limpiar();
               /* Bloquear();
                BloquearBotones();
                //PonerBorde();
                cargarTablaAlumnos("", "", "");*/
                //CargarTablaMedicamentos("");
                
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            }
    }
////   
      public void NumeroFactura()
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        int num=1;
       
        String sql ="";
        int NUM_DET=1;
        //sql="select * from MEDICAMENTOS";
        sql="select * from detalle_venta ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                
               // num = rs.getInt("SECUENCIA_MEDICAMENTOS.NEXTVAL");
                NUM_DET++;
                //txtCodMed.setText(String.valueOf("PRO00"+num));
               
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE PUEDO GENERAR EL CODIGO");
            
        }
       txtNumero.setText(String.valueOf(NUM_DET));
        
    }
//      
      public void CargarTablaDetalle()//String Dato1)
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        String [] titulos = {"N° DETALLE.", "CODIGO M.", "CANTIDAD M.", "N° VENTA"};
        String [] registros = new String[4];
     
        model = new DefaultTableModel(null, titulos);
        String sql="";
       // sql="select * from DETALLE_VENTA where NOM_MED like '%"+Dato1+"%' order by NOM_MED ";
        sql="select * from DETALLE_VENTA";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                registros[0]= rs.getString("NUM_DET");
                registros[1]= rs.getString("COD_MED_P");
                registros[2]= rs.getString("CAN_MED_V");
                registros[3]= rs.getString("NUM_VEN_P");
                //registros[4]= rs.getString("FEC_CAD");
             
                model.addRow(registros);
              }
            TablaDetalle.setModel(model);
          } catch (SQLException ex) {
            Logger.getLogger(Medicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //String registro = String.valueOf(tablaMedicamentos.getRowCount());
        //lblRegistroMedicamentos.setText(registro);
    }
    
//     public void NumProductos()
//    {
//        conexion cc = new conexion();
//        Connection cn = cc.conectar();
//        int num=1;
//       
//        String sql ="";
//        //sql="select * from MEDICAMENTOS";
//        sql="select SECUENCIA_MEDICAMENTO.NEXTVAL from DUAL ";
//        try {
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while(rs.next())
//            {
//                
//               // num = rs.getInt("SECUENCIA_MEDICAMENTOS.NEXTVAL");
//                num++;
//                //txtCodMed.setText(String.valueOf("PRO00"+num));
//               
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "NO SE PUEDO GENERAR EL CODIGO");
//            
//        }
//       txtCodMed.setText(String.valueOf("PRO00"+num));
//        
//    }
      
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigoMed = new javax.swing.JTextField();
        txtNumeroVenta = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaDetalle = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("DETALLE VENTA");

        jLabel4.setText("Cantidad Medicamento");

        jLabel1.setText("Numero");

        jLabel2.setText("Codigo Medicamento");

        jLabel6.setText("N° Venta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumeroVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(txtCodigoMed, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        TablaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TablaDetalleKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TablaDetalle);

        jButton1.setText("INSERTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Algerian", 1, 36));
        jLabel9.setText("DETALLE DE VENTAS");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FYBECA-AZUL_VENTANAS.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(53, 53, 53)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void TablaDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaDetalleKeyReleased
// TODO add your handling code here:
    //if(evt.getKeyChar()==KeyEvent.VK_ENTER)
    //{
    //    ActualizarTablaDatos();
    //}
}//GEN-LAST:event_TablaDetalleKeyReleased

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
//    InsertarDetalle();
    Insertar();
    CargarTablaDetalle();
}//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(DetalleVenta2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetalleVenta2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetalleVenta2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetalleVenta2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DetalleVenta2().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaDetalle;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigoMed;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtNumeroVenta;
    // End of variables declaration//GEN-END:variables
}
