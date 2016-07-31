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
public class DetallePedido extends javax.swing.JInternalFrame {

    /** Creates new form DetalleVenta2 */
     DefaultTableModel model;
    public DetallePedido() {
        initComponents();
        //CargaTablaDetalle();
        NumeroFactura();
        CargarTablaDetalle();
    }
    public void Insertar()
    {
    
    conexion cc = new conexion();
    Connection cn = cc.conectar();
    String NUM_DET, COD_MED_P,CAN_PED,NUM_PED_P ;
    String sql;
    //double PRE_MED;
    NUM_DET=txtNumero.getText();
    COD_MED_P=txtCodigoMed.getText();//.toUpperCase();
    //PRE_MED=Double.parseDouble(txtPreMed.getText());
    CAN_PED=txtCantidad.getText();
    NUM_PED_P=txtNumeroVenta.getText();
    
    //java.util.Date fecha1 = txtFecCadMed.getDate();
    //String fecha = df.format(fecha1);
    //FEC_CAD=fecha; 
    
    sql= "insert into detalle_pedido(NUM_DET,COD_MED_P,CAN_PED,NUM_PED_P )values(?,?,?,?)";   
    PreparedStatement psd;
    
        try {
            psd = cn.prepareStatement(sql);
            psd.setString(1, NUM_DET);
            psd.setString(2, COD_MED_P);
            psd.setString(3, CAN_PED);
            psd.setString(4, NUM_PED_P);
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
//   
      public void NumeroFactura()
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        int num=1;
       
        String sql ="";
        int NUM_DET=1;
        //sql="select * from MEDICAMENTOS";
        sql="select * from detalle_pedido ";
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
      public void EliminarUnDetalle()
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        if(JOptionPane.showConfirmDialog(new JInternalFrame(),"ESTA SEGURO QUE DESEA ELIMINAR","BORRAR REGISTRO",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION )
        {
        int fila=TablaDetallePedido.getSelectedRow();
        String sql="";
        sql="delete from detalle_pedido where num_det='"+TablaDetallePedido.getValueAt(fila, 0).toString()+"'";
        PreparedStatement psd;
        try {
            psd = cn.prepareStatement(sql);
            int n = psd.executeUpdate();
            if(n>0)
            {
              JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE");
              CargarTablaDetalle();
              
            }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SEPUDQ ELIMINAR CORRECTAMENTE");
        }
                    
       } 
 
    }
      
      public void CargarTablaDetalle()//String Dato1)
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        String [] titulos = {"N° DETALLE.", "CODIGO M.", "CANTIDAD M.", "N° PEDIDO"};
        String [] registros = new String[4];
     
        model = new DefaultTableModel(null, titulos);
        String sql="";
       // sql="select * from DETALLE_VENTA where NOM_MED like '%"+Dato1+"%' order by NOM_MED ";
        sql="select * from DETALLE_PEDIDO";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                registros[0]= rs.getString("NUM_DET");
                registros[1]= rs.getString("COD_MED_P");
                registros[2]= rs.getString("CAN_PED");
                registros[3]= rs.getString("NUM_PED_P");
                //registros[4]= rs.getString("FEC_CAD");
             
                model.addRow(registros);
              }
            TablaDetallePedido.setModel(model);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigoMed = new javax.swing.JTextField();
        txtNumero = new componentes.Numeros();
        txtNumeroVenta = new componentes.Numeros();
        txtCantidad = new componentes.Numeros();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaDetallePedido = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("DETALLE PEDIDO");

        jLabel4.setText("Cantidad Medicamento");

        jLabel3.setText("Numero  Venta");

        jLabel1.setText("Numero");

        jLabel2.setText("Codigo Medicamento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigoMed, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNumeroVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigoMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNumeroVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        TablaDetallePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaDetallePedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TablaDetallePedidoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TablaDetallePedido);

        jButton1.setText("INSERTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FYBECA-AZUL_VENTANAS.jpg"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jLabel9.setText("DETALLE PEDIDO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminar)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel9)))
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(14, 14, 14)
                        .addComponent(btnEliminar)
                        .addGap(118, 118, 118))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(341, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void TablaDetallePedidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaDetallePedidoKeyReleased
// TODO add your handling code here:
    //if(evt.getKeyChar()==KeyEvent.VK_ENTER)
    //{
    //    ActualizarTablaDatos();
    //}
}//GEN-LAST:event_TablaDetallePedidoKeyReleased

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
//    InsertarDetalle();
    Insertar();
    CargarTablaDetalle();
}//GEN-LAST:event_jButton1ActionPerformed

private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
// TODO add your handling code here:
    EliminarUnDetalle();
}//GEN-LAST:event_btnEliminarActionPerformed

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
                new DetallePedido().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaDetallePedido;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private componentes.Numeros txtCantidad;
    private javax.swing.JTextField txtCodigoMed;
    private componentes.Numeros txtNumero;
    private componentes.Numeros txtNumeroVenta;
    // End of variables declaration//GEN-END:variables
}
