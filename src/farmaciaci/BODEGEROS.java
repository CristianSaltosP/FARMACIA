/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BODEGEROS.java
 *
 * Created on 15/07/2015, 12:00:59 AM
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
 * @author ACER
 */
public class BODEGEROS extends javax.swing.JInternalFrame {

    /** Creates new form BODEGEROS */
    DefaultTableModel model;
    
    public BODEGEROS() {
        initComponents();
        CargaTablaDatosBodeguero("");
    }
    
    public void InsertarBodeguero() {
        //Clientes cli = new Clientes();
        conexion cc = new conexion();
        Connection cn = cc.conectar();

                        String sql = "";
                        String CI_BOD, NOM_BOD, APE_BOD;
                        CI_BOD = txtCiBodegero.getText();
                        NOM_BOD = txtNombreB.getText().trim().toUpperCase();
                        APE_BOD = letras1.getText().trim().toUpperCase();
                        

                        sql = "insert into BODEGEROS values(?,?,?)";

                        PreparedStatement psd;
                        try {
                            psd = cn.prepareStatement(sql);
                            psd.setString(1, CI_BOD);
                            psd.setString(2, NOM_BOD);
                            psd.setString(3, APE_BOD);
                            
                            int n = psd.executeUpdate();
                            if (n > 0) {
                                JOptionPane.showMessageDialog(null, "SE INSERTO CORRECTAMENTE EL BODEGUERO");
                                CargaTablaDatosBodeguero("");
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
    
    
    public void CargaTablaDatosBodeguero(String Dato1)
    {
        String [] titulos={"CEDULA","NOMBRE","APELLIDO"};
        String [] registros= new String[3];
        String sql="";
        conexion cc=new conexion();
        Connection cn=cc.conectar();
//        sql="select * from BODEGEROS where CI_BOD like '%"+Dato1+"%' OR NOM_BOD like '%"+Dato1+"%' OR APE_BOD like '%"+Dato1+"%' ORDER BY APE_BOD";
       sql="select * from BODEGEROS where NOM_BOD like '%"+Dato1+"%' ORDER BY NOM_BOD";
        model = new DefaultTableModel(null,titulos);
          
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                registros[0]=rs.getString("CI_BOD");
                registros[1]=rs.getString("NOM_BOD");
                registros[2]=rs.getString("APE_BOD");
                model.addRow(registros);
                
            }
            TablaBodeguero.setModel(model);
            int registro=TablaBodeguero.getRowCount();
            lblRegistrosBodeguero.setText(String.valueOf(registro));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
    
   
    
    
    public void ActualizarBodeguero()
    {
        conexion cc=new conexion();
        Connection cn=cc.conectar();
        String sql="";
        sql="update BODEGEROS set NOM_BOD='"+txtNombreAct.getText().trim().toUpperCase()+"',APE_BOD='"+txtApellidoAct.getText().trim().toUpperCase()+"'where CI_BOD='"+txtCedulaAct.getText()+"'";
        try {
            PreparedStatement psd=cn.prepareStatement(sql);
            int n=psd.executeUpdate();
            if(n>0)
            {
                JOptionPane.showMessageDialog(null, "SE ACTUALIZO CORRECTAMENTE EL BODEGUERO");
                CargaTablaDatosBodeguero("");
                DIALOGOBODEGUEROACTUALIZAR.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " NO SE SE ACTUALIZO CORRECTAMENTE EL BODEGUERO");
        }
        
    }
    public void PasarDatosBodeguero()
    {
        if(TablaBodeguero.getSelectedRow()!=-1)
        {
            int fila=TablaBodeguero.getSelectedRow();
            txtCedulaAct.setText(TablaBodeguero.getValueAt(fila, 0).toString());
            txtNombreAct.setText(TablaBodeguero.getValueAt(fila, 1).toString());
            txtApellidoAct.setText(TablaBodeguero.getValueAt(fila, 2).toString());
            
        }
    
        
    DIALOGOBODEGUEROACTUALIZAR.setSize(320, 340);
    DIALOGOBODEGUEROACTUALIZAR.setLocation(500, 200);
    DIALOGOBODEGUEROACTUALIZAR.setVisible(true);
   
    }
    
    public void EliminarBodeguero()
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        if(JOptionPane.showConfirmDialog(new JInternalFrame(),"ESTA SEGURO QUE DESEA ELIMINAR","BORRAR REGISTRO",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION )
        {
        int fila=TablaBodeguero.getSelectedRow();
        String sql="";
        sql="delete from BODEGEROS where CI_BOD='"+TablaBodeguero.getValueAt(fila, 0).toString()+"'";
        PreparedStatement psd;
        try {
            psd = cn.prepareStatement(sql);
            int n = psd.executeUpdate();
            if(n>0)
            {
              JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE");
              CargaTablaDatosBodeguero("");
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

        DIALOGOBODEGUEROINSERTAR = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCiBodegero = new componentes.Numeros();
        txtNombreB = new componentes.Mayusculas();
        letras1 = new componentes.Mayusculas();
        btnGuardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        DIALOGOBODEGUEROACTUALIZAR = new javax.swing.JDialog();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnAceptarAct = new javax.swing.JButton();
        btnCAncelarAct = new javax.swing.JButton();
        txtCedulaAct = new componentes.Numeros();
        txtNombreAct = new componentes.Mayusculas();
        txtApellidoAct = new componentes.Mayusculas();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bsNombreB = new javax.swing.JTextField();
        lblRegistrosBodeguero = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnLimpiezaBusqueda = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaBodeguero = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnCliNuevo1 = new javax.swing.JButton();
        btnCliActualizar1 = new javax.swing.JButton();
        btnCliEliminar1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jLabel3.setText("CEDULA:");

        jLabel6.setText("NOMBRE:");

        jLabel7.setText("APELLIDO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtCiBodegero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(txtNombreB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(letras1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCiBodegero, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(txtNombreB, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(letras1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
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

        javax.swing.GroupLayout DIALOGOBODEGUEROINSERTARLayout = new javax.swing.GroupLayout(DIALOGOBODEGUEROINSERTAR.getContentPane());
        DIALOGOBODEGUEROINSERTAR.getContentPane().setLayout(DIALOGOBODEGUEROINSERTARLayout);
        DIALOGOBODEGUEROINSERTARLayout.setHorizontalGroup(
            DIALOGOBODEGUEROINSERTARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOBODEGUEROINSERTARLayout.createSequentialGroup()
                .addGroup(DIALOGOBODEGUEROINSERTARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DIALOGOBODEGUEROINSERTARLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(DIALOGOBODEGUEROINSERTARLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        DIALOGOBODEGUEROINSERTARLayout.setVerticalGroup(
            DIALOGOBODEGUEROINSERTARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOBODEGUEROINSERTARLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(DIALOGOBODEGUEROINSERTARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(jButton1))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        DIALOGOBODEGUEROACTUALIZAR.setTitle("ACTUALIZAR CLIENTE");
        DIALOGOBODEGUEROACTUALIZAR.setModal(true);

        jLabel11.setText("Cedula");

        jLabel12.setText("Nombre");

        jLabel13.setText("Apellido");

        btnAceptarAct.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAceptarAct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Aceptar_Icono.jpg"))); // NOI18N
        btnAceptarAct.setText("Aceptar");
        btnAceptarAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActActionPerformed(evt);
            }
        });

        btnCAncelarAct.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCAncelarAct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar_Icono.jpg"))); // NOI18N
        btnCAncelarAct.setText("Cancelar");
        btnCAncelarAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCAncelarActActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DIALOGOBODEGUEROACTUALIZARLayout = new javax.swing.GroupLayout(DIALOGOBODEGUEROACTUALIZAR.getContentPane());
        DIALOGOBODEGUEROACTUALIZAR.getContentPane().setLayout(DIALOGOBODEGUEROACTUALIZARLayout);
        DIALOGOBODEGUEROACTUALIZARLayout.setHorizontalGroup(
            DIALOGOBODEGUEROACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOBODEGUEROACTUALIZARLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DIALOGOBODEGUEROACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DIALOGOBODEGUEROACTUALIZARLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAceptarAct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCAncelarAct))
                    .addGroup(DIALOGOBODEGUEROACTUALIZARLayout.createSequentialGroup()
                        .addGroup(DIALOGOBODEGUEROACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(DIALOGOBODEGUEROACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCedulaAct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombreAct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtApellidoAct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))))
                .addContainerGap())
        );
        DIALOGOBODEGUEROACTUALIZARLayout.setVerticalGroup(
            DIALOGOBODEGUEROACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOBODEGUEROACTUALIZARLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DIALOGOBODEGUEROACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedulaAct, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(DIALOGOBODEGUEROACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtNombreAct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(DIALOGOBODEGUEROACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoAct, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(DIALOGOBODEGUEROACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCAncelarAct)
                    .addComponent(btnAceptarAct))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("BODEGUEROS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busqueda", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Buscar_Bodegero");

        bsNombreB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bsNombreBKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblRegistrosBodeguero, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addComponent(bsNombreB, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiezaBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(lblRegistrosBodeguero, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLimpiezaBusqueda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(bsNombreB, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jLabel4.setText("BODEGEROS");

        TablaBodeguero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaBodeguero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TablaBodegueroKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TablaBodeguero);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        btnCliNuevo1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCliNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Nuevo_Icono.png"))); // NOI18N
        btnCliNuevo1.setText("Nuevo");
        btnCliNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliNuevo1ActionPerformed(evt);
            }
        });

        btnCliActualizar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCliActualizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar_Icono.png"))); // NOI18N
        btnCliActualizar1.setText("Actualizar");
        btnCliActualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliActualizar1ActionPerformed(evt);
            }
        });

        btnCliEliminar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FYBECA-AZUL_VENTANAS.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(145, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(318, 318, 318))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(27, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void bsNombreBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bsNombreBKeyReleased
// TODO add your handling code here:
    
    CargaTablaDatosBodeguero(bsNombreB.getText().trim().toUpperCase());
}//GEN-LAST:event_bsNombreBKeyReleased

private void btnLimpiezaBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiezaBusquedaActionPerformed
// TODO add your handling code here:
    bsNombreB.setText("");
    CargaTablaDatosBodeguero("");        
}//GEN-LAST:event_btnLimpiezaBusquedaActionPerformed

private void TablaBodegueroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaBodegueroKeyReleased
// TODO add your handling code here:
    //if(evt.getKeyChar()==KeyEvent.VK_ENTER)
    //{
    //    ActualizarTablaDatos();
    //}
}//GEN-LAST:event_TablaBodegueroKeyReleased
//
private void btnCliNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliNuevo1ActionPerformed
// TODO add your handling code here:
    DIALOGOBODEGUEROINSERTAR.setSize(320, 370);
    DIALOGOBODEGUEROINSERTAR.setLocation(500, 200);
    DIALOGOBODEGUEROINSERTAR.setVisible(true);
    /*.setSize(320, 340);
    DIALOGOBODEGUEROACTUALIZAR.setLocation(500, 200);
    DIALOGOBODEGUEROACTUALIZAR.setVisible(true);*/
}//GEN-LAST:event_btnCliNuevo1ActionPerformed

private void btnCliActualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliActualizar1ActionPerformed
    txtCedulaAct.setEnabled(false);
    PasarDatosBodeguero();
//    ActualizarBodeguero();
//    CargaTablaDatosBodeguero("");
    
}//GEN-LAST:event_btnCliActualizar1ActionPerformed

private void btnCliEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliEliminar1ActionPerformed
// TODO add your handling code here:
    EliminarBodeguero();
}//GEN-LAST:event_btnCliEliminar1ActionPerformed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

   InsertarBodeguero();
//   txtCiBodegero.setText("");
//   txtNombreB.setText("");
//   letras1.setText("");
   DIALOGOBODEGUEROINSERTAR.dispose();
    
}//GEN-LAST:event_btnGuardarActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    //Limpiar();
    DIALOGOBODEGUEROINSERTAR.dispose();
}//GEN-LAST:event_jButton1ActionPerformed

    private void btnAceptarActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActActionPerformed
        // TODO add your handling code here:
        ActualizarBodeguero();

    }//GEN-LAST:event_btnAceptarActActionPerformed

    private void btnCAncelarActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCAncelarActActionPerformed
        // TODO add your handling code here:
        DIALOGOBODEGUEROACTUALIZAR.dispose();
    }//GEN-LAST:event_btnCAncelarActActionPerformed

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
            java.util.logging.Logger.getLogger(BODEGEROS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BODEGEROS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BODEGEROS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BODEGEROS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new BODEGEROS().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DIALOGOBODEGUEROACTUALIZAR;
    private javax.swing.JDialog DIALOGOBODEGUEROINSERTAR;
    private javax.swing.JTable TablaBodeguero;
    private javax.swing.JTextField bsNombreB;
    private javax.swing.JButton btnAceptarAct;
    private javax.swing.JButton btnCAncelarAct;
    private javax.swing.JButton btnCliActualizar1;
    private javax.swing.JButton btnCliEliminar1;
    private javax.swing.JButton btnCliNuevo1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiezaBusqueda;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRegistrosBodeguero;
    private componentes.Mayusculas letras1;
    private componentes.Mayusculas txtApellidoAct;
    private componentes.Numeros txtCedulaAct;
    private componentes.Numeros txtCiBodegero;
    private componentes.Mayusculas txtNombreAct;
    private componentes.Mayusculas txtNombreB;
    // End of variables declaration//GEN-END:variables
}
