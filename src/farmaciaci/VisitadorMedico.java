/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VisitadorMedico.java
 *
 * Created on 17-jul-2015, 21:59:05
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
public class VisitadorMedico extends javax.swing.JInternalFrame {

    /** Creates new form VisitadorMedico */
     DefaultTableModel model;
    public VisitadorMedico() {
        initComponents();
        CargaTablaDatosVisitador("");
    }
    
    public void InsertarVisitador() {
        //Clientes cli = new Clientes();
        conexion cc = new conexion();
        Connection cn = cc.conectar();
//        if (txtCiBodegero.getText().isEmpty() && txtNombreB.getText().isEmpty() && txtApellidoB.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Existen Campos Obligatorios");
//        } else {
//            if (txtCiBodegero.getText().isEmpty()) {
//                JOptionPane.showMessageDialog(null, "Cedula Obligatoria");
//            } else {
//                if (txtNombreB.getText().isEmpty()) {
//                    JOptionPane.showMessageDialog(null, "Nombre Obligatoria");
//                } else {
//                    if (txtApellidoB.getText().isEmpty()) {
//                        JOptionPane.showMessageDialog(null, "Cedula Obligatoria");
//                    } else {
                        String sql = "";
                        String CI_VIS, NOM_VIS, APE_VIS,DIR_VIS,FONO_VIS;
                        CI_VIS = txtCiVisitador.getText();
                        NOM_VIS = txtNombreV.getText().trim().toUpperCase();
                        APE_VIS = txtApellidoV.getText().trim().toUpperCase();
                        DIR_VIS=txtDireccionV.getText().trim().toUpperCase();
                        
                        FONO_VIS=txtFonoV.getText().trim();
                        

                        sql = "insert into VISITADOR_MEDICO values(?,?,?,?,?)";

                        PreparedStatement psd;
                        try {
                            psd = cn.prepareStatement(sql);
                            psd.setString(1, CI_VIS);
                            psd.setString(2, NOM_VIS);
                            psd.setString(3, APE_VIS);
                            psd.setString(4, DIR_VIS);
                            psd.setString(5,FONO_VIS);
            
//            
                            int n = psd.executeUpdate();
                            if (n > 0) {
                                JOptionPane.showMessageDialog(null, "SE INSERTO CORRECTAMENTE EL VISITADOR MEDICO");
                                CargaTablaDatosVisitador("");
//                Limpiar();
//                DIALOGOCLIENTESNUEVO.dispose();
                            }

                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "EL NUMERO DE CEDULA DEL CLIENTE YA EXISTE ");
                        }


//                    }
//                }
//
//            }
//        }
    }
    
     public void CargaTablaDatosVisitador(String Dato1)
    {
        String [] titulos={"CEDULA","NOMBRE","APELLIDO","DIRECCION","TELEFONO"};
        String [] registros= new String[5];
        String sql="";
        conexion cc=new conexion();
        Connection cn=cc.conectar();
//        sql="select * from BODEGEROS where CI_BOD like '%"+Dato1+"%' OR NOM_BOD like '%"+Dato1+"%' OR APE_BOD like '%"+Dato1+"%' ORDER BY APE_BOD";
       sql="select * from VISITADOR_MEDICO where NOM_VIS like '%"+Dato1+"%' ORDER BY APE_VIS";
        model = new DefaultTableModel(null,titulos);
          
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                registros[0]=rs.getString("CI_VIS");
                registros[1]=rs.getString("NOM_VIS");
                registros[2]=rs.getString("APE_VIS");
                registros[3]=rs.getString("DIR_VIS");
                registros[4]=rs.getString("FONO_VIS");
                
//                
                model.addRow(registros);
                
            }
            TablaVisitador.setModel(model);
            int registro=TablaVisitador.getRowCount();
            lblRegistrosVisitador.setText(String.valueOf(registro));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
     public void ActualizarBodeguero()
    {
        conexion cc=new conexion();
        Connection cn=cc.conectar();
        String sql="";
        sql="update VISITADOR_MEDICO set NOM_VIS='"+txtNombreAct.getText().trim().toUpperCase()+"',APE_VIS='"+txtApellidoAct.getText().trim().toUpperCase()+"',DIR_VIS='"+txtDireccionAct.getText().trim().toUpperCase()+"',FONO_VIS='"+txtFonoAct.getText().trim()+"' where CI_VIS='"+txtCiVisitadorAct.getText()+"'";
        try {
            PreparedStatement psd=cn.prepareStatement(sql);
            int n=psd.executeUpdate();
            if(n>0)
            {
                JOptionPane.showMessageDialog(null, "SE ACTUALIZO CORRECTAMENTE EL VISITADOR");
                CargaTablaDatosVisitador("");
                DIALOGOVISITADORACTUALIZAR.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " NO SE SE ACTUALIZO CORRECTAMENTE EL BODEGUERO");
        }
        
    }
     
     public void PasarDatoVisitador()
    {
        if(TablaVisitador.getSelectedRow()!=-1)
        {
            int fila=TablaVisitador.getSelectedRow();
            txtCiVisitadorAct.setText(TablaVisitador.getValueAt(fila, 0).toString());
            txtNombreAct.setText(TablaVisitador.getValueAt(fila, 1).toString());
            txtApellidoAct.setText(TablaVisitador.getValueAt(fila, 2).toString());
            txtDireccionAct.setText(TablaVisitador.getValueAt(fila, 3).toString());
            txtFonoAct.setText(TablaVisitador.getValueAt(fila, 4).toString());
           
        }
    
        
    DIALOGOVISITADORACTUALIZAR.setSize(320, 415);
    DIALOGOVISITADORACTUALIZAR.setLocation(500, 200);
    DIALOGOVISITADORACTUALIZAR.setVisible(true);
   
    }
     
     public void EliminarVisitador()
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        if(JOptionPane.showConfirmDialog(new JInternalFrame(),"ESTA SEGURO QUE DESEA ELIMINAR","BORRAR REGISTRO",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION )
        {
        int fila=TablaVisitador.getSelectedRow();
        String sql="";
        sql="delete from VISITADOR_MEDICO where CI_VIS='"+TablaVisitador.getValueAt(fila, 0).toString()+"'";
        PreparedStatement psd;
        try {
            psd = cn.prepareStatement(sql);
            int n = psd.executeUpdate();
            if(n>0)
            {
              JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE");
              CargaTablaDatosVisitador("");
            }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SEPUDQ ELIMINAR CORRECTAMENTE");
        }
                    
       } 
 
    }
   
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DIALOGOVISITADORINSERTAR = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCiVisitador = new javax.swing.JTextField();
        txtNombreV = new javax.swing.JTextField();
        txtApellidoV = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDireccionV = new javax.swing.JTextField();
        txtFonoV = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        DIALOGOVISITADORACTUALIZAR = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtCiVisitadorAct = new javax.swing.JTextField();
        txtNombreAct = new javax.swing.JTextField();
        txtApellidoAct = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtDireccionAct = new javax.swing.JTextField();
        txtFonoAct = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnGuardar1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bsNombreV = new javax.swing.JTextField();
        lblRegistrosVisitador = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnLimpiezaBusqueda = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaVisitador = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnCliNuevo1 = new javax.swing.JButton();
        btnCliActualizar1 = new javax.swing.JButton();
        btnCliEliminar1 = new javax.swing.JButton();

        jLabel3.setText("CEDULA:");

        jLabel6.setText("NOMBRE:");

        jLabel7.setText("APELLIDO");

        jLabel9.setText("DIRECCION:");

        jLabel10.setText("TELEFONO:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCiVisitador, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(txtNombreV)
                            .addComponent(txtApellidoV)
                            .addComponent(txtDireccionV, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(txtFonoV, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))
                    .addComponent(jLabel9))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCiVisitador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNombreV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtDireccionV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFonoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        btnGuardar.setText("INSERTAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jButton1.setText("CANCELAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DIALOGOVISITADORINSERTARLayout = new javax.swing.GroupLayout(DIALOGOVISITADORINSERTAR.getContentPane());
        DIALOGOVISITADORINSERTAR.getContentPane().setLayout(DIALOGOVISITADORINSERTARLayout);
        DIALOGOVISITADORINSERTARLayout.setHorizontalGroup(
            DIALOGOVISITADORINSERTARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOVISITADORINSERTARLayout.createSequentialGroup()
                .addGroup(DIALOGOVISITADORINSERTARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(DIALOGOVISITADORINSERTARLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DIALOGOVISITADORINSERTARLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        DIALOGOVISITADORINSERTARLayout.setVerticalGroup(
            DIALOGOVISITADORINSERTARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOVISITADORINSERTARLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(DIALOGOVISITADORINSERTARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(jButton1))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jLabel14.setText("CEDULA:");

        jLabel15.setText("NOMBRE:");

        jLabel16.setText("APELLIDO");

        jLabel17.setText("DIRECCION:");

        jLabel18.setText("TELEFONO:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCiVisitadorAct, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(txtNombreAct)
                            .addComponent(txtApellidoAct)
                            .addComponent(txtDireccionAct, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(txtFonoAct, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))
                    .addComponent(jLabel17))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtCiVisitadorAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtNombreAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidoAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(txtDireccionAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFonoAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        btnGuardar1.setText("INSERTAR");
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FYBECA-AZUL_VENTANAS.jpg"))); // NOI18N

        javax.swing.GroupLayout DIALOGOVISITADORACTUALIZARLayout = new javax.swing.GroupLayout(DIALOGOVISITADORACTUALIZAR.getContentPane());
        DIALOGOVISITADORACTUALIZAR.getContentPane().setLayout(DIALOGOVISITADORACTUALIZARLayout);
        DIALOGOVISITADORACTUALIZARLayout.setHorizontalGroup(
            DIALOGOVISITADORACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOVISITADORACTUALIZARLayout.createSequentialGroup()
                .addGroup(DIALOGOVISITADORACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DIALOGOVISITADORACTUALIZARLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(DIALOGOVISITADORACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(DIALOGOVISITADORACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2))))
                    .addGroup(DIALOGOVISITADORACTUALIZARLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnGuardar1)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        DIALOGOVISITADORACTUALIZARLayout.setVerticalGroup(
            DIALOGOVISITADORACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOVISITADORACTUALIZARLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(DIALOGOVISITADORACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar1)
                    .addComponent(jButton2))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("VISITADOR MEDICO");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busqueda", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Buscar_Medico");

        bsNombreV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bsNombreVKeyReleased(evt);
            }
        });

        lblRegistrosVisitador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lblRegistrosVisitadorKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("#Registros:");

        btnLimpiezaBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Limpiar_Busqueda1.png"))); // NOI18N
        btnLimpiezaBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiezaBusquedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(lblRegistrosVisitador, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bsNombreV, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(btnLimpiezaBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblRegistrosVisitador, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(bsNombreV, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLimpiezaBusqueda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Algerian", 1, 36));
        jLabel4.setText("VISITADOR MEDICO");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FYBECA-AZUL_VENTANAS.jpg"))); // NOI18N

        TablaVisitador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaVisitador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TablaVisitadorKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TablaVisitador);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        btnCliNuevo1.setFont(new java.awt.Font("Tahoma", 1, 11));
        btnCliNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Nuevo_Icono.png"))); // NOI18N
        btnCliNuevo1.setText("Nuevo");
        btnCliNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliNuevo1ActionPerformed(evt);
            }
        });

        btnCliActualizar1.setFont(new java.awt.Font("Tahoma", 1, 11));
        btnCliActualizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar_Icono.png"))); // NOI18N
        btnCliActualizar1.setText("Actualizar");
        btnCliActualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliActualizar1ActionPerformed(evt);
            }
        });

        btnCliEliminar1.setFont(new java.awt.Font("Tahoma", 1, 11));
        btnCliEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar_Icono2.png"))); // NOI18N
        btnCliEliminar1.setText("Eliminar");
        btnCliEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliEliminar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCliNuevo1)
                .addGap(18, 18, 18)
                .addComponent(btnCliActualizar1)
                .addGap(18, 18, 18)
                .addComponent(btnCliEliminar1)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCliNuevo1)
                    .addComponent(btnCliActualizar1)
                    .addComponent(btnCliEliminar1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void bsNombreVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bsNombreVKeyReleased
// TODO add your handling code here:
    CargaTablaDatosVisitador(bsNombreV.getText().trim().toUpperCase());
    
//    CargaTablaDatosClientes(bsCedula.getText().trim().toUpperCase());
}//GEN-LAST:event_bsNombreVKeyReleased

private void btnLimpiezaBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiezaBusquedaActionPerformed
// TODO add your handling code here:
//    bsCedula.setText("");
//    CargaTablaDatosClientes("");        
}//GEN-LAST:event_btnLimpiezaBusquedaActionPerformed

private void TablaVisitadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaVisitadorKeyReleased
// TODO add your handling code here:
    //if(evt.getKeyChar()==KeyEvent.VK_ENTER)
    //{
    //    ActualizarTablaDatos();
    //}
}//GEN-LAST:event_TablaVisitadorKeyReleased

private void btnCliNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliNuevo1ActionPerformed
// TODO add your handling code here:
    DIALOGOVISITADORINSERTAR.setSize(320, 415);//320 340
    DIALOGOVISITADORINSERTAR.setLocation(500, 200);
    DIALOGOVISITADORINSERTAR.setVisible(true);
}//GEN-LAST:event_btnCliNuevo1ActionPerformed

private void btnCliActualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliActualizar1ActionPerformed
// TODO add your handling code here:
PasarDatoVisitador();
}//GEN-LAST:event_btnCliActualizar1ActionPerformed

private void btnCliEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliEliminar1ActionPerformed
// TODO add your handling code here:
    EliminarVisitador();
}//GEN-LAST:event_btnCliEliminar1ActionPerformed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
// TODO add your handling code here:
   //InsertarBodeguero();
    InsertarVisitador();
}//GEN-LAST:event_btnGuardarActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    //Limpiar();
    DIALOGOVISITADORINSERTAR.dispose();
}//GEN-LAST:event_jButton1ActionPerformed

private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
// TODO add your handling code here:
    ActualizarBodeguero();
}//GEN-LAST:event_btnGuardar1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jButton2ActionPerformed

private void lblRegistrosVisitadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblRegistrosVisitadorKeyReleased
// TODO add your handling code here:
    
     
}//GEN-LAST:event_lblRegistrosVisitadorKeyReleased

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
            java.util.logging.Logger.getLogger(VisitadorMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisitadorMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisitadorMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisitadorMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new VisitadorMedico().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DIALOGOVISITADORACTUALIZAR;
    private javax.swing.JDialog DIALOGOVISITADORINSERTAR;
    private javax.swing.JTable TablaVisitador;
    private javax.swing.JTextField bsNombreV;
    private javax.swing.JButton btnCliActualizar1;
    private javax.swing.JButton btnCliEliminar1;
    private javax.swing.JButton btnCliNuevo1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnLimpiezaBusqueda;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRegistrosVisitador;
    private javax.swing.JTextField txtApellidoAct;
    private javax.swing.JTextField txtApellidoV;
    private javax.swing.JTextField txtCiVisitador;
    private javax.swing.JTextField txtCiVisitadorAct;
    private javax.swing.JTextField txtDireccionAct;
    private javax.swing.JTextField txtDireccionV;
    private javax.swing.JTextField txtFonoAct;
    private javax.swing.JTextField txtFonoV;
    private javax.swing.JTextField txtNombreAct;
    private javax.swing.JTextField txtNombreV;
    // End of variables declaration//GEN-END:variables
}
