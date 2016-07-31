package farmaciaci;

import java.io.BufferedReader;
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
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

public class Medicamentos extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    public Medicamentos() {
        initComponents();
        CargarTablaMedicamentos("");
        tablaMedicamentos1.setVisible(false);
//        txtCaducidadAct.enable(false);
    }
    
     public void limpiar() {
        txtCodMed.setText("");
        txtNombMed.setText("");
        txtPreMed.setText("");
        txtCanMed.setText("");
        txtFecCadMed.setDate(null);
        
    }
    
    public void Insertar()
    {
    
    conexion cc = new conexion();
    Connection cn = cc.conectar();
    String COD_MED, NOM_MED, STOCK,FEC_CAD ;
    String sql;
    double PRE_MED;
    COD_MED=txtCodMed.getText();
    NOM_MED=txtNombMed.getText();//.toUpperCase();
    PRE_MED=Double.parseDouble(txtPreMed.getText());
    STOCK=txtCanMed.getText();
    
    java.util.Date fecha1 = txtFecCadMed.getDate();
    String fecha = df.format(fecha1);
    FEC_CAD=fecha; 
    
    sql= "insert into MEDICAMENTOS(COD_MED,NOM_MED,PRE_UNI,STOCK,FEC_CAD)values(?,?,?,?,?)";   
    PreparedStatement psd;
    
        try {
            psd = cn.prepareStatement(sql);
            psd.setString(1, COD_MED);
            psd.setString(2, NOM_MED);
            psd.setDouble(3, PRE_MED);
            psd.setString(4, STOCK);
            psd.setString(5, FEC_CAD);
         
                     int n = psd.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "INSERTADO"); 
               limpiar();
                CargarTablaMedicamentos("");
                
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            }
    } 
    
    
    public void ActualizarMedicamento()
    {
        conexion cc=new conexion();
        Connection cn=cc.conectar();
        String sql="";
        sql="update MEDICAMENTOS set NOM_MED='"+txtNombreAct.getText().trim().toUpperCase()+"',PRE_UNI='"+txtPrecioAct.getText().trim().toUpperCase()+"',STOCK='"+txtStockAct.getText().trim().toUpperCase()+"' where COD_MED='"+txtCodigoAct.getText()+"'";
        try {
            PreparedStatement psd=cn.prepareStatement(sql);
            int n=psd.executeUpdate();
            if(n>0)
            {
                JOptionPane.showMessageDialog(null, "SE ACTUALIZO CORRECTAMENTE EL MEDICAMENTO");
                CargarTablaMedicamentos("");
                DIALOGOMEDICAMENTOACTUALIZAR.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " NO SE SE ACTUALIZO CORRECTAMENTE EL MEDICAMENTO");
        }
        
    }
    
    public void PasarDatosMedicamentos()
    {
        if(tablaMedicamentos.getSelectedRow()!=-1)
        {
            int fila=tablaMedicamentos.getSelectedRow();
            txtCodigoAct.setText(tablaMedicamentos.getValueAt(fila, 0).toString());
            txtNombreAct.setText(tablaMedicamentos.getValueAt(fila, 1).toString());
            txtPrecioAct.setText(tablaMedicamentos.getValueAt(fila, 2).toString());
            txtStockAct.setText(tablaMedicamentos.getValueAt(fila, 3).toString());

          jLabel9.setText(tablaMedicamentos.getValueAt(fila, 4).toString());

        }
    
        
    DIALOGOMEDICAMENTOACTUALIZAR.setSize(320, 320);
    DIALOGOMEDICAMENTOACTUALIZAR.setLocation(500, 200);
    DIALOGOMEDICAMENTOACTUALIZAR.setVisible(true);
    
        
    }
    
     public void CargarTablaMedicamentos(String Dato1)
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        String [] titulos = {"CODIGO", "NOMBRE", "PRECIO UNITARIO", "STOCK","CADUCIDAD" };
        String [] registros = new String[5];
     
        model = new DefaultTableModel(null, titulos);
        String sql="";
        sql="select * from MEDICAMENTOS where COD_MED like '%"+Dato1+"%' order by NOM_MED ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                registros[0]= rs.getString("COD_MED");
                registros[1]= rs.getString("NOM_MED");
                registros[2]= rs.getString("PRE_UNI");
                registros[3]= rs.getString("STOCK");
                registros[4]= rs.getString("FEC_CAD");
             
                model.addRow(registros);
              }
            tablaMedicamentos.setModel(model);
          } catch (SQLException ex) {
            Logger.getLogger(Medicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String registro = String.valueOf(tablaMedicamentos.getRowCount());
        lblRegistroMedicamentos.setText(registro);
    }
     
    public void Cargarclaveproducto(String Dato1)
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        String [] titulos = {"CODIGO"};
        String [] registros = new String[1];
     
        model = new DefaultTableModel(null, titulos);
        String sql="";
        sql="select * from MEDICAMENTOS where COD_MED like '%"+Dato1+"%' order by COD_MED ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                registros[0]= rs.getString("COD_MED");
             
                model.addRow(registros);
              }
            tablaMedicamentos1.setModel(model);
          } catch (SQLException ex) {
            Logger.getLogger(Medicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String registro = String.valueOf(tablaMedicamentos1.getRowCount());
        lblRegistroMedicamentos.setText(registro);
    }
    
     public void NumProductos()
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        int num=1;
       
        String sql ="";
        //sql="select * from MEDICAMENTOS";
        sql="select SECUENCIA_MEDICAMENTO.NEXTVAL from DUAL ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                
               // num = rs.getInt("SECUENCIA_MEDICAMENTOS.NEXTVAL");
                num++;
                //txtCodMed.setText(String.valueOf("PRO00"+num));
               
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE PUEDO GENERAR EL CODIGO");
            
        }
       txtCodMed.setText(String.valueOf("PRO00"+num));
        
    }
     
     void Codigo(){
     int j;
     int cont=1;
     conexion cc = new conexion();
     Connection cn = cc.conectar();
     String num="";
     String c="";
     String sql="SELECT MAX(COD_MED) as COD_MED FROM MEDICAMENTOS";
         try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next())
            {
              //c=rs.getString(1);
                c=rs.getString("COD_MED");
                      
            }
            if(c==null){
            txtCodMed.setText("ME0001");
                System.out.println(c);
            }
            
            else{
            char r1=c.charAt(2);
            char r2=c.charAt(3);
            char r3=c.charAt(4);
            char r4=c.charAt(5);
            System.out.println(""+r1+r2+r3+r4);
            String juntar=""+r1+r2+r3+r4;
            
            int var=Integer.parseInt(juntar);
            System.out.println(""+var);
            GenerarNumeros gn=new GenerarNumeros();
            gn.generar(var);
            txtCodMed.setText(gn.serie());
            }
//            System.out.println(c);
//            j=Integer.parseInt(c);
//            GenerarNumeros gn=new GenerarNumeros();
//            gn.generar(j);
//            txtCodMed.setText(gn.serie());
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,"NO SE PUDO CARGAR EL CODIGO"+e);
         }
   
     }
     
     public void EliminarMedicamento()
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        if(JOptionPane.showConfirmDialog(new JInternalFrame(),"ESTA SEGURO QUE DESEA ELIMINAR","BORRAR REGISTRO",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION )
        {
        int fila=tablaMedicamentos.getSelectedRow();
        String sql="";
        sql="delete from MEDICAMENTOS where COD_MED='"+tablaMedicamentos.getValueAt(fila, 0).toString()+"'";
        PreparedStatement psd;
        try {
            psd = cn.prepareStatement(sql);
            int n = psd.executeUpdate();
            if(n>0)
            {
              JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE");
              CargarTablaMedicamentos("");
            }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SEPUDQ ELIMINAR CORRECTAMENTE");
        }
                    
       } 
 
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        DIALOGOMEDICAMENTOINSERTAR = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCodMed = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtFecCadMed = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        txtNombMed = new componentes.Mayusculas();
        txtPreMed = new componentes.Numeros();
        txtCanMed = new componentes.Numeros();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaMedicamentos1 = new javax.swing.JTable();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        DIALOGOMEDICAMENTOACTUALIZAR = new javax.swing.JDialog();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCodigoAct = new javax.swing.JTextField();
        btnAceptarAct = new javax.swing.JButton();
        btnCAncelarAct = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtNombreAct = new componentes.Mayusculas();
        txtPrecioAct = new componentes.Numeros();
        txtStockAct = new componentes.Numeros();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMedicamentos = new javax.swing.JTable();
        btnNuevoMedicamento = new javax.swing.JButton();
        btnActualizarMedicamentos = new javax.swing.JButton();
        btnEliminarMedicamentos = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblRegistroMedicamentos = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bsMedNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel3.setText("CODIGO:");

        txtCodMed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCodMedMouseClicked(evt);
            }
        });
        txtCodMed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodMedKeyReleased(evt);
            }
        });

        jLabel4.setText("MEDICAMENTO:");

        jLabel6.setText("CANTIDAD:");

        jLabel7.setText("CADUCIDAD:");

        jLabel16.setText("PRE. UNITARIO");

        tablaMedicamentos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaMedicamentos1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel16)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodMed, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(txtFecCadMed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombMed, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(txtPreMed, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(txtCanMed, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                .addContainerGap(42, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCodMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel16)
                    .addComponent(txtPreMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCanMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtFecCadMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
        );

        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DIALOGOMEDICAMENTOINSERTARLayout = new javax.swing.GroupLayout(DIALOGOMEDICAMENTOINSERTAR.getContentPane());
        DIALOGOMEDICAMENTOINSERTAR.getContentPane().setLayout(DIALOGOMEDICAMENTOINSERTARLayout);
        DIALOGOMEDICAMENTOINSERTARLayout.setHorizontalGroup(
            DIALOGOMEDICAMENTOINSERTARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOMEDICAMENTOINSERTARLayout.createSequentialGroup()
                .addGroup(DIALOGOMEDICAMENTOINSERTARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DIALOGOMEDICAMENTOINSERTARLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(btnAceptar)
                        .addGap(46, 46, 46)
                        .addComponent(btnCancelar))
                    .addGroup(DIALOGOMEDICAMENTOINSERTARLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        DIALOGOMEDICAMENTOINSERTARLayout.setVerticalGroup(
            DIALOGOMEDICAMENTOINSERTARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOMEDICAMENTOINSERTARLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(DIALOGOMEDICAMENTOINSERTARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DIALOGOMEDICAMENTOACTUALIZAR.setTitle("ACTUALIZAR MEDICAMENTO");
        DIALOGOMEDICAMENTOACTUALIZAR.setModal(true);

        jLabel11.setText("CODIGO:");

        jLabel12.setText("NOMBRE:");

        jLabel13.setText("PRECIO:");

        jLabel14.setText("CANTIDAD");

        jLabel15.setText("CADUCIDAD:");

        txtCodigoAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActActionPerformed(evt);
            }
        });

        btnAceptarAct.setFont(new java.awt.Font("Tahoma", 1, 11));
        btnAceptarAct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Aceptar_Icono.jpg"))); // NOI18N
        btnAceptarAct.setText("Aceptar");
        btnAceptarAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActActionPerformed(evt);
            }
        });

        btnCAncelarAct.setFont(new java.awt.Font("Tahoma", 1, 11));
        btnCAncelarAct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar_Icono.jpg"))); // NOI18N
        btnCAncelarAct.setText("Cancelar");
        btnCAncelarAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCAncelarActActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DIALOGOMEDICAMENTOACTUALIZARLayout = new javax.swing.GroupLayout(DIALOGOMEDICAMENTOACTUALIZAR.getContentPane());
        DIALOGOMEDICAMENTOACTUALIZAR.getContentPane().setLayout(DIALOGOMEDICAMENTOACTUALIZARLayout);
        DIALOGOMEDICAMENTOACTUALIZARLayout.setHorizontalGroup(
            DIALOGOMEDICAMENTOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createSequentialGroup()
                        .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                        .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecioAct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNombreAct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCodigoAct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(txtStockAct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createSequentialGroup()
                        .addComponent(btnAceptarAct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(btnCAncelarAct)))
                .addContainerGap())
        );
        DIALOGOMEDICAMENTOACTUALIZARLayout.setVerticalGroup(
            DIALOGOMEDICAMENTOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoAct, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtNombreAct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtPrecioAct, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtStockAct, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(DIALOGOMEDICAMENTOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptarAct)
                    .addComponent(btnCAncelarAct))
                .addContainerGap())
        );

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("MEDICAMENTOS");

        tablaMedicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaMedicamentos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnNuevoMedicamento.setText("NUEVO");
        btnNuevoMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoMedicamentoActionPerformed(evt);
            }
        });

        btnActualizarMedicamentos.setText("ACTUALIZAR");
        btnActualizarMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarMedicamentosActionPerformed(evt);
            }
        });

        btnEliminarMedicamentos.setText("ELIMINAR");
        btnEliminarMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMedicamentosActionPerformed(evt);
            }
        });

        jLabel1.setText("# REGISTRO:");

        jLabel2.setText("BUSCAR POR CODIGO:");

        bsMedNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bsMedNombreKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(lblRegistroMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bsMedNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegistroMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(bsMedNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FYBECA-AZUL_VENTANAS.jpg"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("MEDICAMENTOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(btnNuevoMedicamento)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizarMedicamentos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarMedicamentos))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoMedicamento)
                    .addComponent(btnActualizarMedicamentos)
                    .addComponent(btnEliminarMedicamentos))
                .addGap(57, 57, 57))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnNuevoMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoMedicamentoActionPerformed

    
    DIALOGOMEDICAMENTOINSERTAR.setSize(380, 400);
    DIALOGOMEDICAMENTOINSERTAR.setLocation(450, 50);
    DIALOGOMEDICAMENTOINSERTAR.setVisible(true);
    
}//GEN-LAST:event_btnNuevoMedicamentoActionPerformed

private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

    if (txtCodMed.getText().equals(tablaMedicamentos1.getValueAt(0,0))){
        conexion cc=new conexion();
        Connection cn=cc.conectar();
        String sql="";
        sql="update MEDICAMENTOS set STOCK=STOCK+'"+txtCanMed.getText().trim()+"' where COD_MED='"+tablaMedicamentos1.getValueAt(0,0)+"'";
        try {
            PreparedStatement psd=cn.prepareStatement(sql);
            int n=psd.executeUpdate();
            if(n>0)
            {
                JOptionPane.showMessageDialog(null, "SE ACumento el stock");
                CargarTablaMedicamentos("");
                DIALOGOMEDICAMENTOACTUALIZAR.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " NO SE SE pudo aumentar el stock");
        }
    }else{
    Insertar();
    }
}//GEN-LAST:event_btnAceptarActionPerformed

private void txtCodigoActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtCodigoActActionPerformed

private void btnAceptarActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActActionPerformed
// TODO add your handling code here:
    //PasarDatosMedicamentos();
    ActualizarMedicamento();
    bsMedNombre.setText("");
    
}//GEN-LAST:event_btnAceptarActActionPerformed

private void btnCAncelarActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCAncelarActActionPerformed
// TODO add your handling code here:
    DIALOGOMEDICAMENTOACTUALIZAR.dispose();
}//GEN-LAST:event_btnCAncelarActActionPerformed

private void btnActualizarMedicamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarMedicamentosActionPerformed
// TODO add your handling code here:
    txtCodigoAct.setEditable(false);
    PasarDatosMedicamentos();
}//GEN-LAST:event_btnActualizarMedicamentosActionPerformed

private void bsMedNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bsMedNombreKeyReleased
// TODO add your handling code here:
    CargarTablaMedicamentos(bsMedNombre.getText().trim().toUpperCase());
}//GEN-LAST:event_bsMedNombreKeyReleased

private void btnEliminarMedicamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMedicamentosActionPerformed
// TODO add your handling code here:
    EliminarMedicamento();
}//GEN-LAST:event_btnEliminarMedicamentosActionPerformed

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
// TODO add your handling code here:
    DIALOGOMEDICAMENTOINSERTAR.dispose();
}//GEN-LAST:event_btnCancelarActionPerformed

private void txtCodMedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodMedMouseClicked
    
}//GEN-LAST:event_txtCodMedMouseClicked

private void txtCodMedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodMedKeyReleased
// TODO add your handling code here:
    Cargarclaveproducto(txtCodMed.getText());
}//GEN-LAST:event_txtCodMedKeyReleased

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
            java.util.logging.Logger.getLogger(Medicamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Medicamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Medicamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Medicamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Medicamentos().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DIALOGOMEDICAMENTOACTUALIZAR;
    private javax.swing.JDialog DIALOGOMEDICAMENTOINSERTAR;
    private javax.swing.JTextField bsMedNombre;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAceptarAct;
    public static javax.swing.JButton btnActualizarMedicamentos;
    private javax.swing.JButton btnCAncelarAct;
    private javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnEliminarMedicamentos;
    public static javax.swing.JButton btnNuevoMedicamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblRegistroMedicamentos;
    private javax.swing.JTable tablaMedicamentos;
    private javax.swing.JTable tablaMedicamentos1;
    private componentes.Numeros txtCanMed;
    private javax.swing.JTextField txtCodMed;
    private javax.swing.JTextField txtCodigoAct;
    private com.toedter.calendar.JDateChooser txtFecCadMed;
    private componentes.Mayusculas txtNombMed;
    private componentes.Mayusculas txtNombreAct;
    private componentes.Numeros txtPreMed;
    private componentes.Numeros txtPrecioAct;
    private componentes.Numeros txtStockAct;
    // End of variables declaration//GEN-END:variables
}
