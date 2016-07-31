
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

public class Clientes extends javax.swing.JInternalFrame {
    DefaultTableModel model;
    String varcedula, varnombre, varapellido, vardireccion, vartelefono;
    JFrame ventanaclientes = new JFrame();
    MetodosAyudantes Metodo = new MetodosAyudantes();
    /** Creates new form Clientes */
    public Clientes() {
        initComponents();
        //ventanaclientes.setLocation(200, 100);
        CargaTablaDatosClientes("");
        btnLimpiezaBusqueda.setToolTipText("Limpiar Busqueda");
        txtCedulaAct.setEnabled(false);
        
    }
    public void Limpiar()
    {
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");

    }
    public void InsertarClientes()
    {
        Clientes cli = new Clientes();
        conexion cc=new conexion();
        Connection cn=cc.conectar();
        if(txtCedula.getText().isEmpty() && txtNombre.getText().isEmpty() && txtApellido.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Existen Campos Obligatorios");
        }
        else
        {
            if(txtCedula.getText().isEmpty())
            {
               JOptionPane.showMessageDialog(null, "Cedula Obligatoria"); 
            }
            else
            {
               if(txtNombre.getText().isEmpty())
            {
               JOptionPane.showMessageDialog(null, "Nombre Obligatoria"); 
            }
               else
               {
                   if(txtApellido.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Cedula Obligatoria"); 
                    }
                   else
                   {
                       String sql="";
        String CI_CLI, NOM_CLI, APE_CLI, DIR_CLI,FONO_CLI;
        CI_CLI=txtCedula.getText();
        NOM_CLI=txtNombre.getText().trim().toUpperCase();
        APE_CLI=txtApellido.getText().trim().toUpperCase();

        sql="insert into cliente values(?,?,?)";
        
        PreparedStatement psd;
        try {
            psd=cn.prepareStatement(sql);
            psd.setString(1, CI_CLI);
            psd.setString(2, NOM_CLI);
            psd.setString(3, APE_CLI);
            int n=psd.executeUpdate();
            if(n>0)
            {
                JOptionPane.showMessageDialog(null, "SE INSERTO CORRECTAMENTE EL CLIENTE");
                CargaTablaDatosClientes("");
                Limpiar();
                DIALOGOCLIENTESNUEVO.dispose();
            }
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "EL NUMERO DE CEDULA DEL CLIENTE YA EXISTE ");
            }
        
                
                   }
               }
               
            }
        }
  
    }
    public void CargaTablaDatosClientes(String Dato1)
    {
        String [] titulos={"CEDULA","NOMBRE","APELLIDO"};
        String [] registros= new String[3];
        String sql="";
        conexion cc=new conexion();
        Connection cn=cc.conectar();
//        sql="select * from cliente where CI_CLI like '%"+Dato1+"%' OR NOM_CLI like '%"+Dato1+"%' OR APE_CLI like '%"+Dato1+"%' ORDER BY APE_CLI";
                sql="select * from cliente where NOM_CLI like '%"+Dato1+"%' ORDER BY APE_CLI";

        model = new DefaultTableModel(null,titulos);
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                registros[0]=rs.getString("CI_CLI");
                registros[1]=rs.getString("NOM_CLI");
                registros[2]=rs.getString("APE_CLI");
                model.addRow(registros);
                
            }
            TablaClientes.setModel(model);
            int registro=TablaClientes.getRowCount();
            lblRegistrosClientes.setText(String.valueOf(registro));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void PasarDatosClientes()
    {
        if(TablaClientes.getSelectedRow()!=-1)
        {
            int fila=TablaClientes.getSelectedRow();
            txtCedulaAct.setText(TablaClientes.getValueAt(fila, 0).toString());
            txtNombreAct.setText(TablaClientes.getValueAt(fila, 1).toString());
            txtApellidoAct.setText(TablaClientes.getValueAt(fila, 2).toString());
        }
    
        
    DIALOGOCLIENTEACTUALIZAR.setSize(320, 320);
    DIALOGOCLIENTEACTUALIZAR.setLocation(500, 200);
    DIALOGOCLIENTEACTUALIZAR.setVisible(true);
    
        
    }
    public void ActualizarClientes()
    {
        conexion cc=new conexion();
        Connection cn=cc.conectar();
        String sql="";
        sql="update cliente set NOM_CLI='"+txtNombreAct.getText().trim().toUpperCase()+"',APE_CLI='"+txtApellidoAct.getText().trim().toUpperCase()+"' where CI_CLI='"+txtCedulaAct.getText()+"'";
        try {
            PreparedStatement psd=cn.prepareStatement(sql);
            int n=psd.executeUpdate();
            if(n>0)
            {
                JOptionPane.showMessageDialog(null, "SE ACTUALIZO CORRECTAMENTE EL CLIENTE");
                CargaTablaDatosClientes("");
                DIALOGOCLIENTEACTUALIZAR.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " NO SE SE ACTUALIZO CORRECTAMENTE EL CLIENTE");
        }
        
    }
    public void ActualizarTablaDatos()
    {
        String sql="";
        conexion cc=new conexion();
        Connection cn=cc.conectar();
        TablaClientes.getSelectedRow();
        if(TablaClientes.getSelectedRow()!=-1)
        {
            int fila=TablaClientes.getSelectedRow();
            sql="update cliente"
                    + "set NOM_CLI='"+TablaClientes.getValueAt(fila, 1).toString() +"',"
                    + "APE_CLI='"+TablaClientes.getValueAt(fila, 2).toString()+"',"
                    + "where CI_CLI='"+TablaClientes.getValueAt(fila, 0).toString()+"'";
        try {
            PreparedStatement psd=cn.prepareStatement(sql);
            int n = psd.executeUpdate();
            if(n>0)
            {
                JOptionPane.showMessageDialog(null, "SE ACTUALIZAO CORRECTAMENTE EL CLIENTE");
                CargaTablaDatosClientes("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
    }
    public void EliminarClientes()
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        if(JOptionPane.showConfirmDialog(new JInternalFrame(),"ESTA SEGURO QUE DESEA ELIMINAR","BORRAR REGISTRO",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION )
        {
        int fila=TablaClientes.getSelectedRow();
        String sql="";
        sql="delete from cliente where CI_CLI='"+TablaClientes.getValueAt(fila, 0).toString()+"'";
        PreparedStatement psd;
        try {
            psd = cn.prepareStatement(sql);
            int n = psd.executeUpdate();
            if(n>0)
            {
              JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE");
              CargaTablaDatosClientes("");
            }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SEPQ ELIMINO CORRECTAMENTE");
        }
                    
       } 
        
        
    }
    ///////////////LIBERAR PARA EL REPORTE
    /*public void Reportes_Clientes()
    {
        try {
            // TODO add your handling code here:
            conexion cc = new conexion();
            Connection cn = cc.conectar();
            Map a = new HashMap();
            //a.put("Cedula", txtAluCedula.getText());           
            //JasperReport reporte = JasperCompileManager.compileReport("C:/Users/LUISANTONIO/Desktop/Universidad/src/Reportes/ListadoAlumnos.jrxml");
//            JasperReport reporte = JasperCompileManager.compileReport("C:/Users/LUISANTONIO/Documents/NetBeansProjects/Proyecto_Farmacia/src/Reportes/ReporteClientes.jrxml");
            JasperReport reporte = JasperCompileManager.compileReport(" C:/Users/Usuario/Desktop/Proyecto_Farmacia/src/Reportes/ReporteClientes.jrxml");
//            C:\Users\Usuario\Desktop\Proyecto_Farmacia\src\Reportes
            
            JasperPrint print = JasperFillManager.fillReport(reporte, a,cc.conectar());
            JasperViewer.viewReport(print,false);
                        
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
            
        }
    }*/

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DIALOGOCLIENTESNUEVO = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtNombre = new letras.soloLetras();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        DIALOGOCLIENTEACTUALIZAR = new javax.swing.JDialog();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCedulaAct = new javax.swing.JTextField();
        txtNombreAct = new javax.swing.JTextField();
        txtApellidoAct = new javax.swing.JTextField();
        btnAceptarAct = new javax.swing.JButton();
        btnCAncelarAct = new javax.swing.JButton();
        mayusculas1 = new mayusculas.Mayusculas();
        jPanel2 = new javax.swing.JPanel();
        btnCliNuevo = new javax.swing.JButton();
        btnCliActualizar = new javax.swing.JButton();
        btnCliEliminar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bsCedula = new javax.swing.JTextField();
        lblRegistrosClientes = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnLimpiezaBusqueda = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaClientes = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        DIALOGOCLIENTESNUEVO.setTitle("NUEVO CLIENTE");
        DIALOGOCLIENTESNUEVO.setModal(true);
        DIALOGOCLIENTESNUEVO.setResizable(false);

        jLabel6.setText("Cedula");

        jLabel7.setText("Nombre");

        jLabel8.setText("Apellido");

        txtCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCedulaFocusLost(evt);
            }
        });
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCedula)
                    .addComponent(txtApellido)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar_Icono.jpg"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Aceptar_Icono.jpg"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DIALOGOCLIENTESNUEVOLayout = new javax.swing.GroupLayout(DIALOGOCLIENTESNUEVO.getContentPane());
        DIALOGOCLIENTESNUEVO.getContentPane().setLayout(DIALOGOCLIENTESNUEVOLayout);
        DIALOGOCLIENTESNUEVOLayout.setHorizontalGroup(
            DIALOGOCLIENTESNUEVOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOCLIENTESNUEVOLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DIALOGOCLIENTESNUEVOLayout.setVerticalGroup(
            DIALOGOCLIENTESNUEVOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOCLIENTESNUEVOLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(DIALOGOCLIENTESNUEVOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        DIALOGOCLIENTEACTUALIZAR.setTitle("ACTUALIZAR CLIENTE");
        DIALOGOCLIENTEACTUALIZAR.setModal(true);

        jLabel11.setText("Cedula");

        jLabel12.setText("Nombre");

        jLabel13.setText("Apellido");

        txtCedulaAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActActionPerformed(evt);
            }
        });

        txtNombreAct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreActKeyTyped(evt);
            }
        });

        txtApellidoAct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoActKeyTyped(evt);
            }
        });

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

        javax.swing.GroupLayout DIALOGOCLIENTEACTUALIZARLayout = new javax.swing.GroupLayout(DIALOGOCLIENTEACTUALIZAR.getContentPane());
        DIALOGOCLIENTEACTUALIZAR.getContentPane().setLayout(DIALOGOCLIENTEACTUALIZARLayout);
        DIALOGOCLIENTEACTUALIZARLayout.setHorizontalGroup(
            DIALOGOCLIENTEACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOCLIENTEACTUALIZARLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DIALOGOCLIENTEACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DIALOGOCLIENTEACTUALIZARLayout.createSequentialGroup()
                        .addGroup(DIALOGOCLIENTEACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                        .addGap(14, 14, 14)
                        .addGroup(DIALOGOCLIENTEACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtApellidoAct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(txtNombreAct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(txtCedulaAct, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)))
                    .addGroup(DIALOGOCLIENTEACTUALIZARLayout.createSequentialGroup()
                        .addComponent(btnAceptarAct)
                        .addGap(18, 18, 18)
                        .addComponent(btnCAncelarAct)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        DIALOGOCLIENTEACTUALIZARLayout.setVerticalGroup(
            DIALOGOCLIENTEACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOCLIENTEACTUALIZARLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DIALOGOCLIENTEACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedulaAct, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(DIALOGOCLIENTEACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreAct, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DIALOGOCLIENTEACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidoAct, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(DIALOGOCLIENTEACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptarAct)
                    .addComponent(btnCAncelarAct))
                .addGap(48, 48, 48))
        );

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("CLIENTES");
        setAutoscrolls(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        btnCliNuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCliNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Nuevo_Icono.png"))); // NOI18N
        btnCliNuevo.setText("Nuevo");
        btnCliNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliNuevoActionPerformed(evt);
            }
        });

        btnCliActualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCliActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar_Icono.png"))); // NOI18N
        btnCliActualizar.setText("Actualizar");
        btnCliActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliActualizarActionPerformed(evt);
            }
        });

        btnCliEliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCliEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar_Icono2.png"))); // NOI18N
        btnCliEliminar.setText("Eliminar");
        btnCliEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCliNuevo)
                .addGap(18, 18, 18)
                .addComponent(btnCliActualizar)
                .addGap(18, 18, 18)
                .addComponent(btnCliEliminar)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCliNuevo)
                    .addComponent(btnCliActualizar)
                    .addComponent(btnCliEliminar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busqueda", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Buscar_Cliente");

        bsCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bsCedulaKeyReleased(evt);
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
                        .addComponent(lblRegistrosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addComponent(bsCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
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
                    .addComponent(lblRegistrosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLimpiezaBusqueda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(bsCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        TablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TablaClientesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TablaClientes);

        jLabel4.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jLabel4.setText("CLIENTES");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FYBECA-AZUL_VENTANAS.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(269, 269, 269)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 283, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(431, 431, 431)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1328, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnCliActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliActualizarActionPerformed
// TODO add your handling code here:
    //ActualizarClientes();
    PasarDatosClientes();
    
}//GEN-LAST:event_btnCliActualizarActionPerformed

private void btnCliEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliEliminarActionPerformed
// TODO add your handling code here:
    EliminarClientes();
    
}//GEN-LAST:event_btnCliEliminarActionPerformed

private void TablaClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaClientesKeyReleased
// TODO add your handling code here:
    //if(evt.getKeyChar()==KeyEvent.VK_ENTER)
    //{
    //    ActualizarTablaDatos();
    //}
}//GEN-LAST:event_TablaClientesKeyReleased

private void btnCliNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliNuevoActionPerformed
// TODO add your handling code here: 
    //ClientesDatos CLI = new ClientesDatos();
    //CLI.setVisible(true);
    //CLI.btnActualizar.setVisible(false);
    //ClientesDatos cliente= new ClientesDatos();
    //cliente.setVisible(true);
    DIALOGOCLIENTESNUEVO.setSize(320, 320);
    DIALOGOCLIENTESNUEVO.setLocation(500, 200);
    DIALOGOCLIENTESNUEVO.setVisible(true);
    
    //model.addRow("CLICEDULA","CLINOMBRE","CLIAPELLIDO","CLIDIRECCION","CLITELEFONO");
    
    
    
    
    
}//GEN-LAST:event_btnCliNuevoActionPerformed

private void bsCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bsCedulaKeyReleased
// TODO add your handling code here:
    
    CargaTablaDatosClientes(bsCedula.getText().trim().toUpperCase());

}//GEN-LAST:event_bsCedulaKeyReleased

private void txtCedulaActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtCedulaActActionPerformed

private void btnCAncelarActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCAncelarActActionPerformed
// TODO add your handling code here:
    DIALOGOCLIENTEACTUALIZAR.dispose();
}//GEN-LAST:event_btnCAncelarActActionPerformed

private void btnAceptarActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActActionPerformed
// TODO add your handling code here:
    ActualizarClientes();
    
}//GEN-LAST:event_btnAceptarActActionPerformed

private void btnLimpiezaBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiezaBusquedaActionPerformed
// TODO add your handling code here:
    bsCedula.setText("");
    CargaTablaDatosClientes("");        
}//GEN-LAST:event_btnLimpiezaBusquedaActionPerformed

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
// TODO add your handling code here:
    Limpiar();
    DIALOGOCLIENTESNUEVO.dispose();
    
}//GEN-LAST:event_btnCancelarActionPerformed

private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
// TODO add your handling code here:
    InsertarClientes();
    //Limpiar();
}//GEN-LAST:event_btnAceptarActionPerformed

private void txtCedulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCedulaFocusLost
// TODO add your handling code here:
    Metodo.validadorDeCedula(txtCedula.getText());
}//GEN-LAST:event_txtCedulaFocusLost

private void txtCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyReleased
// TODO add your handling code here:
    
}//GEN-LAST:event_txtCedulaKeyReleased

private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
// TODO add your handling code here:
    Metodo.Numeros(txtCedula);
    
    
}//GEN-LAST:event_txtCedulaKeyTyped

private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
// TODO add your handling code here:
    Metodo.Letras(txtApellido);
    
}//GEN-LAST:event_txtApellidoKeyTyped

private void txtNombreActKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreActKeyTyped
// TODO add your handling code here:
    Metodo.Letras(txtNombreAct);
    
}//GEN-LAST:event_txtNombreActKeyTyped

private void txtApellidoActKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoActKeyTyped
// TODO add your handling code here:
    Metodo.Letras(txtApellidoAct);
    
}//GEN-LAST:event_txtApellidoActKeyTyped

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
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Clientes().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DIALOGOCLIENTEACTUALIZAR;
    private javax.swing.JDialog DIALOGOCLIENTESNUEVO;
    private javax.swing.JTable TablaClientes;
    private javax.swing.JTextField bsCedula;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAceptarAct;
    private javax.swing.JButton btnCAncelarAct;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCliActualizar;
    private javax.swing.JButton btnCliEliminar;
    private javax.swing.JButton btnCliNuevo;
    private javax.swing.JButton btnLimpiezaBusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRegistrosClientes;
    private mayusculas.Mayusculas mayusculas1;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtApellidoAct;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCedulaAct;
    private letras.soloLetras txtNombre;
    private javax.swing.JTextField txtNombreAct;
    // End of variables declaration//GEN-END:variables
}
