
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

import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.text.View;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Usuario
 */
public class ventas2 extends javax.swing.JInternalFrame {

    /** Creates new form ventas2 */
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    DefaultTableModel model;
    public ventas2() {
        initComponents();
        //NumeroFactura();
        CargarTablaVentas();
    }
    
     public void Insertar(){
    conexion cc = new conexion();
    Connection cn = cc.conectar();
    String NUM_VEN,F_H_VEN,TOTAL,CI_FAR_P,CI_CLI_P ;
    String sql;
    NUM_VEN=txtNumeroVenta.getText();
    java.util.Date fecha1 = txtFechaCompra.getDate();
    String fecha = df.format(fecha1);
    F_H_VEN=fecha;
    CI_FAR_P= txtCedulaFarmaceutico.getText();
    CI_CLI_P=txtCedulaCliente.getText();    
    sql= "insert into ventaS(NUM_VEN,F_H_VEN,CI_FAR_P,CI_CLI_P )values(?,?,?,?)";   
    PreparedStatement psd;
        try {
            psd = cn.prepareStatement(sql);
            psd.setString(1, NUM_VEN);
            psd.setString(2, F_H_VEN);
            psd.setString(3, CI_FAR_P);
            psd.setString(4, CI_CLI_P);
                     int n = psd.executeUpdate();
            //executeUpdate(Pertence al PreparedStatement) == Me devuelve un valor entero si se afecta a la BDD
            if(n>0){
                JOptionPane.showMessageDialog(null, "INSERTADO"); 
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            }
    }
      public void NumeroFactura()
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        int num=1;
       
        String sql ="";
        int NUM_DET=1;
        //sql="select * from MEDICAMENTOS";
        sql="select * from ventas ";
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
       txtNumeroVenta.setText(String.valueOf(NUM_DET));
        
    }
      
       public void CargarTablaVentas()//String Dato1)
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        String [] titulos = {"N° VENTA","FECHA VENTA","FARMACEUTICO","CLIENTE","TOTAL"};
        String [] registros = new String[5];
     
        model = new DefaultTableModel(null, titulos);
        String sql="";
       // sql="select * from DETALLE_VENTA where NOM_MED like '%"+Dato1+"%' order by NOM_MED ";
        sql="select * from VENTAS";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                registros[0]= rs.getString("NUM_VEN");
                registros[1]= rs.getString("F_H_VEN");
                registros[2]= rs.getString("CI_FAR_P");
                registros[3]= rs.getString("CI_CLI_P");
                registros[4]= rs.getString("TOTAL");
             
                model.addRow(registros);
              }
            TablaDetalle.setModel(model);
          } catch (SQLException ex) {
            Logger.getLogger(Medicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //String registro = String.valueOf(tablaMedicamentos.getRowCount());
        //lblRegistroMedicamentos.setText(registro);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNumeroVenta = new javax.swing.JTextField();
        txtCedulaCliente = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCedulaFarmaceutico = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFechaCompra = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaDetalle = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setForeground(java.awt.Color.white);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("VENTAS");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setText("Numero Venta");

        jLabel5.setText("Cedula cliente");

        txtNumeroVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroVentaActionPerformed(evt);
            }
        });
        txtNumeroVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumeroVentaFocusLost(evt);
            }
        });
        txtNumeroVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroVentaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroVentaKeyTyped(evt);
            }
        });

        txtCedulaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaClienteKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaClienteKeyReleased(evt);
            }
        });

        jLabel11.setText("Fecha venta");

        jLabel10.setText("Ci Farmaceutico");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtCedulaCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtNumeroVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                    .addComponent(txtCedulaFarmaceutico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(txtFechaCompra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel11))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtCedulaFarmaceutico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jButton1.setText("INGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("NUEVO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(69, Short.MAX_VALUE))
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

        jLabel9.setFont(new java.awt.Font("Algerian", 1, 36));
        jLabel9.setText("VENTAS");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FYBECA-AZUL_VENTANAS.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(703, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 234, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(61, 61, 61))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(707, 707, 707))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(52, 52, 52)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtNumeroVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroVentaActionPerformed
// TODO add your handling code here:
//        if(txtNumeroVenta.getText().length()==10&&txtCedulaCliente.getText().length()>=1&&txtApellido.getText().length()>=1&&txtDireccion.getText().length()>=1)
//                    {
//                       //txtFacturaCantidad.setEnabled(true); 
//                        cargarTablaMedicamnetos("");
//                        txtCedulaCliente.setEnabled(false);
//                    }
//        else{
//            if(txtCedulaCliente.getText().trim().length()>=1)
//            {
//                 txtCedulaCliente.setEnabled(false);
//                 TablaMedicamentos.setEnabled(true);
//            }
//            else{
//                txtCedulaCliente.setEnabled(true);
////                txtNombre.requestFocus();
//            }
//        }
}//GEN-LAST:event_txtNumeroVentaActionPerformed

private void txtNumeroVentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroVentaFocusLost
// TODO add your handling code here:
    //c.validacion(evt);
}//GEN-LAST:event_txtNumeroVentaFocusLost

private void txtNumeroVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroVentaKeyReleased
// TODO add your handling code here:
//    if(txtNumeroVenta.getText().length()<=9)
//    {
//        txtCedulaCliente.setText("");
//                txtApellido.setText("");
//                txtDireccion.setText("");
//                txtTotal.setText("");
//    }
//        else{
//    
//    if(txtNumeroVenta.getText().length()==10)
//    {
//    
//    String Dato =txtNumeroVenta.getText();
//        String sql="select * from cliente  where ci_cli like'%"+Dato+"%'";
//        try {
//            Statement psw = cn.createStatement();
//            ResultSet rs = psw.executeQuery(sql);
//            while(rs.next()){
//                txtCedulaCliente.setText(rs.getString("nom_cli"));
//                txtApellido.setText(rs.getString("ape_cli"));
//                txtDireccion.setText(rs.getString("dir_cli"));
//                txtTotal.setText( rs.getString("fono_cli"));
////                 txtBuscarNombrePro.setEnabled(true);  
////                 Tproductos.setEnabled(true);
//
//                }
//            } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "No existe el cliente");
//        }
//    }
//    }
}//GEN-LAST:event_txtNumeroVentaKeyReleased
//public void reporteMasVvendido(){
//     conexion cc = new conexion();
//    Connection cn = cc.conectar();
//        try {
//                Map a=new HashMap();                                   
//                //JasperReport reporte=JasperCompileManager.compileReport("C:/Users/USUARIO/Documents/NetBeansProjects/Facturacion/Facturacion/src/reportes/empleados.jrxml");
//                
//                JasperReport reporte=JasperCompileManager.compileReport("C:/Users/Usuario/Documents/NetBeansProjects/FarmaciaCI/src/farmaciaci/reMasVendidort1.jrxml");
//                JasperPrint  print=JasperFillManager.fillReport(reporte, a,cc.conectar());
//                JasperViewer.viewReport(print,false);
//               
//        } catch (JRException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
//    
//    }


private void txtNumeroVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroVentaKeyTyped
// TODO add your handling code here:
    //c.controlCaracteres(evt);
}//GEN-LAST:event_txtNumeroVentaKeyTyped

private void txtCedulaClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaClienteKeyTyped
// TODO add your handling code here:
//    controlSoloLetras(evt);
}//GEN-LAST:event_txtCedulaClienteKeyTyped

private void txtCedulaClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaClienteKeyReleased
// TODO add your handling code here:
    //txtNombre.setText((txtNombre.getText().toUpperCase()));
}//GEN-LAST:event_txtCedulaClienteKeyReleased

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    Insertar();
    CargarTablaVentas();
}//GEN-LAST:event_jButton1ActionPerformed

private void TablaDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaDetalleKeyReleased
// TODO add your handling code here:
    //if(evt.getKeyChar()==KeyEvent.VK_ENTER)
    //{
    //    ActualizarTablaDatos();
    //}
}//GEN-LAST:event_TablaDetalleKeyReleased

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
    NumeroFactura();
}//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ventas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ventas2().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaDetalle;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCedulaCliente;
    private javax.swing.JTextField txtCedulaFarmaceutico;
    private com.toedter.calendar.JDateChooser txtFechaCompra;
    private javax.swing.JTextField txtNumeroVenta;
    // End of variables declaration//GEN-END:variables
}
