


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
public class FARMACEUTICO extends javax.swing.JInternalFrame {

    /** Creates new form FARMACEUTICO */
    
     DefaultTableModel model;
    public FARMACEUTICO() {
        initComponents();
        CargaTablaDatosFarmaceutico("");
    }
    
    public void InsertarFarmaceutico()
    {
        //Clientes cli = new Clientes();
        conexion cc=new conexion();
        Connection cn=cc.conectar();
        if(txtCedulaF.getText().isEmpty() && txtNombreF.getText().isEmpty() && txtApellidoF.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Existen Campos Obligatorios");
        }
        else
        {
            if(txtCedulaF.getText().isEmpty())
            {
               JOptionPane.showMessageDialog(null, "Cedula Obligatoria"); 
            }
            else
            {
               if(txtNombreF.getText().isEmpty())
            {
               JOptionPane.showMessageDialog(null, "Nombre Obligatoria"); 
            }
               else
               {
                   if(txtApellidoF.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Apellido Obligatoria"); 
                    }
                   else
                   {
                       String sql="";
        String CI_FAR, NOM_FAR, APE_FAR, SUE_FAR;
        CI_FAR=txtCedulaF.getText();
        NOM_FAR=txtNombreF.getText().trim().toUpperCase();
        APE_FAR=txtApellidoF.getText().trim().toUpperCase();
        SUE_FAR=txtSueldoF.getText().trim().toUpperCase();
        
        sql="insert into FARMACEUTICO values(?,?,?,?)";
        
        PreparedStatement psd;
        try {
            psd=cn.prepareStatement(sql);
            psd.setString(1, CI_FAR);
            psd.setString(2, NOM_FAR);
            psd.setString(3, APE_FAR);
            psd.setString(4, SUE_FAR);
            
            int n=psd.executeUpdate();
            if(n>0)
            {
                JOptionPane.showMessageDialog(null, "SE INSERTO CORRECTAMENTE EL CLIENTE");
                CargaTablaDatosFarmaceutico("");
                Limpiar();
                DIALOGOFARMACEUTICONUEVO.dispose();
            }
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "EL NUMERO DE CEDULA DEL CLIENTE YA EXISTE ");
            }
        
                
                   }
               }
               
            }
        }
       
    }
    
     public void Limpiar()
    {
        txtCedulaF.setText("");
        txtNombreF.setText("");
        txtApellidoF.setText("");
        txtSueldoF.setText("");
     }
     
     public void CargaTablaDatosFarmaceutico(String Dato1)
    {
        String [] titulos={"CEDULA","NOMBRE","APELLIDO","SUELDO"};
        String [] registros= new String[4];
        String sql="";
        conexion cc=new conexion();
        Connection cn=cc.conectar();
                sql="select * from FARMACEUTICO where NOM_FAR like '%"+Dato1+"%' ORDER BY APE_FAR";

        model = new DefaultTableModel(null,titulos);
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                registros[0]=rs.getString("CI_FAR");
                registros[1]=rs.getString("NOM_FAR");
                registros[2]=rs.getString("APE_FAR");
                registros[3]=rs.getString("SUE_FAR");
                
                model.addRow(registros);
                
            }
            TablaFarmaceutico.setModel(model);
            int registro=TablaFarmaceutico.getRowCount();
            lblRegistrosFarmaceutico.setText(String.valueOf(registro));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
     
     public void ActualizarFarmaceutico()
    {
        conexion cc=new conexion();
        Connection cn=cc.conectar();
        String sql="";
        sql="update FARMACEUTICO set NOM_FAR='"+txtNombreAct.getText().trim().toUpperCase()+"',APE_FAR='"+txtApellidoAct.getText().trim().toUpperCase()+"',SUE_FAR='"+txtSueldoAct.getText()+"'"+" where CI_FAR='"+txtCedulaAct.getText()+"'";
        try {
            PreparedStatement psd=cn.prepareStatement(sql);
            int n=psd.executeUpdate();
            if(n>0)
            {
                JOptionPane.showMessageDialog(null, "SE ACTUALIZO CORRECTAMENTE EL FARMACEUTICO");
                CargaTablaDatosFarmaceutico("");
                DIALOGOFARMACEUTICOACTUALIZAR.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " NO SE SE ACTUALIZO CORRECTAMENTE EL FARMACEUTICO");
        }
        
    }
     
     public void PasarDatosFarmaceutico()
    {
        if(TablaFarmaceutico.getSelectedRow()!=-1)
        {
            int fila=TablaFarmaceutico.getSelectedRow();
            txtCedulaAct.setText(TablaFarmaceutico.getValueAt(fila, 0).toString());
            txtNombreAct.setText(TablaFarmaceutico.getValueAt(fila, 1).toString());
            txtApellidoAct.setText(TablaFarmaceutico.getValueAt(fila, 2).toString());
            txtSueldoAct.setText(TablaFarmaceutico.getValueAt(fila, 3).toString());
          
        }
    
        
    DIALOGOFARMACEUTICOACTUALIZAR.setSize(320, 320);
    DIALOGOFARMACEUTICOACTUALIZAR.setLocation(500, 200);
    DIALOGOFARMACEUTICOACTUALIZAR.setVisible(true);
    
        
    }
     
     public void Eliminarfarmaceutico()
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        if(JOptionPane.showConfirmDialog(new JInternalFrame(),"ESTA SEGURO QUE DESEA ELIMINAR","BORRAR REGISTRO",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION )
        {
        int fila=TablaFarmaceutico.getSelectedRow();
        String sql="";
        sql="delete from FARMACEUTICO where CI_FAR='"+TablaFarmaceutico.getValueAt(fila, 0).toString()+"'";
        PreparedStatement psd;
        try {
            psd = cn.prepareStatement(sql);
            int n = psd.executeUpdate();
            if(n>0)
            {
              JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE");
              CargaTablaDatosFarmaceutico("");
            }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR CORRECTAMENTE");
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

        DIALOGOFARMACEUTICONUEVO = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCedulaF = new javax.swing.JTextField();
        txtNombreF = new letras.soloLetras();
        txtApellidoF = new letras.soloLetras();
        txtSueldoF = new componentes.Numeros();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        DIALOGOFARMACEUTICOACTUALIZAR = new javax.swing.JDialog();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCedulaAct = new javax.swing.JTextField();
        txtNombreAct = new javax.swing.JTextField();
        txtApellidoAct = new javax.swing.JTextField();
        txtSueldoAct = new javax.swing.JTextField();
        btnAceptarAct = new javax.swing.JButton();
        btnCAncelarAct = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bsNombre = new javax.swing.JTextField();
        lblRegistrosFarmaceutico = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnLimpiezaBusqueda = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaFarmaceutico = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnIngresoFarmaceutico = new javax.swing.JButton();
        btnActuFarmaceutico = new javax.swing.JButton();
        btnEliminarfarmaceutico = new javax.swing.JButton();

        DIALOGOFARMACEUTICONUEVO.setTitle("NUEVO CLIENTE");
        DIALOGOFARMACEUTICONUEVO.setModal(true);
        DIALOGOFARMACEUTICONUEVO.setResizable(false);

        jLabel6.setText("Cedula");

        jLabel7.setText("Nombre");

        jLabel8.setText("Apellido");

        jLabel9.setText("Sueldo");

        txtCedulaF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCedulaFFocusLost(evt);
            }
        });
        txtCedulaF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaFKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaFKeyTyped(evt);
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
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCedulaF, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(txtNombreF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtApellidoF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSueldoF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(txtCedulaF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreF, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel7)))
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtApellidoF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSueldoF, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel9)))
                .addGap(19, 19, 19))
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

        javax.swing.GroupLayout DIALOGOFARMACEUTICONUEVOLayout = new javax.swing.GroupLayout(DIALOGOFARMACEUTICONUEVO.getContentPane());
        DIALOGOFARMACEUTICONUEVO.getContentPane().setLayout(DIALOGOFARMACEUTICONUEVOLayout);
        DIALOGOFARMACEUTICONUEVOLayout.setHorizontalGroup(
            DIALOGOFARMACEUTICONUEVOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOFARMACEUTICONUEVOLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnAceptar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addContainerGap(16, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DIALOGOFARMACEUTICONUEVOLayout.setVerticalGroup(
            DIALOGOFARMACEUTICONUEVOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOFARMACEUTICONUEVOLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(DIALOGOFARMACEUTICONUEVOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        DIALOGOFARMACEUTICOACTUALIZAR.setTitle("ACTUALIZAR CLIENTE");
        DIALOGOFARMACEUTICOACTUALIZAR.setModal(true);

        jLabel11.setText("Cedula");

        jLabel12.setText("Nombre");

        jLabel13.setText("Apellido");

        jLabel14.setText("Sueldo");

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

        txtSueldoAct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSueldoActKeyTyped(evt);
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

        javax.swing.GroupLayout DIALOGOFARMACEUTICOACTUALIZARLayout = new javax.swing.GroupLayout(DIALOGOFARMACEUTICOACTUALIZAR.getContentPane());
        DIALOGOFARMACEUTICOACTUALIZAR.getContentPane().setLayout(DIALOGOFARMACEUTICOACTUALIZARLayout);
        DIALOGOFARMACEUTICOACTUALIZARLayout.setHorizontalGroup(
            DIALOGOFARMACEUTICOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOFARMACEUTICOACTUALIZARLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DIALOGOFARMACEUTICOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DIALOGOFARMACEUTICOACTUALIZARLayout.createSequentialGroup()
                        .addGroup(DIALOGOFARMACEUTICOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                        .addGap(14, 14, 14)
                        .addGroup(DIALOGOFARMACEUTICOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtApellidoAct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(txtNombreAct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(txtCedulaAct, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(txtSueldoAct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)))
                    .addGroup(DIALOGOFARMACEUTICOACTUALIZARLayout.createSequentialGroup()
                        .addComponent(btnAceptarAct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(btnCAncelarAct)))
                .addContainerGap())
        );
        DIALOGOFARMACEUTICOACTUALIZARLayout.setVerticalGroup(
            DIALOGOFARMACEUTICOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DIALOGOFARMACEUTICOACTUALIZARLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DIALOGOFARMACEUTICOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedulaAct, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(DIALOGOFARMACEUTICOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreAct, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DIALOGOFARMACEUTICOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidoAct, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DIALOGOFARMACEUTICOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSueldoAct, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(DIALOGOFARMACEUTICOACTUALIZARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptarAct)
                    .addComponent(btnCAncelarAct))
                .addContainerGap())
        );

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("FAMACEUTICO");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FYBECA-AZUL_VENTANAS.jpg"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busqueda", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Buscar_Farmaceutico");

        bsNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bsNombreKeyReleased(evt);
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblRegistrosFarmaceutico, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(bsNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(btnLimpiezaBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(lblRegistrosFarmaceutico, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(btnLimpiezaBusqueda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(bsNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jLabel4.setText("FARMACEUTICO");

        TablaFarmaceutico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaFarmaceutico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TablaFarmaceuticoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TablaFarmaceutico);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        btnIngresoFarmaceutico.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnIngresoFarmaceutico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Nuevo_Icono.png"))); // NOI18N
        btnIngresoFarmaceutico.setText("Nuevo");
        btnIngresoFarmaceutico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresoFarmaceuticoActionPerformed(evt);
            }
        });

        btnActuFarmaceutico.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnActuFarmaceutico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar_Icono.png"))); // NOI18N
        btnActuFarmaceutico.setText("Actualizar");
        btnActuFarmaceutico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActuFarmaceuticoActionPerformed(evt);
            }
        });

        btnEliminarfarmaceutico.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEliminarfarmaceutico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar_Icono3.jpg"))); // NOI18N
        btnEliminarfarmaceutico.setText("Eliminar");
        btnEliminarfarmaceutico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarfarmaceuticoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIngresoFarmaceutico)
                .addGap(18, 18, 18)
                .addComponent(btnActuFarmaceutico)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarfarmaceutico)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIngresoFarmaceutico)
                    .addComponent(btnActuFarmaceutico)
                    .addComponent(btnEliminarfarmaceutico))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void bsNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bsNombreKeyReleased
// TODO add your handling code here:
    
    CargaTablaDatosFarmaceutico(bsNombre.getText().trim().toUpperCase());
}//GEN-LAST:event_bsNombreKeyReleased

private void btnLimpiezaBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiezaBusquedaActionPerformed
// TODO add your handling code here:
   // bsCedula.setText("");
    //CargaTablaDatosClientes("");        
}//GEN-LAST:event_btnLimpiezaBusquedaActionPerformed

private void TablaFarmaceuticoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaFarmaceuticoKeyReleased
// TODO add your handling code here:
    //if(evt.getKeyChar()==KeyEvent.VK_ENTER)
    //{
    //    ActualizarTablaDatos();
    //}
}//GEN-LAST:event_TablaFarmaceuticoKeyReleased

private void btnIngresoFarmaceuticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresoFarmaceuticoActionPerformed
// TODO add your handling code here:
    DIALOGOFARMACEUTICONUEVO.setSize(320, 320);
    DIALOGOFARMACEUTICONUEVO.setLocation(500, 200);
    DIALOGOFARMACEUTICONUEVO.setVisible(true);
}//GEN-LAST:event_btnIngresoFarmaceuticoActionPerformed

private void btnActuFarmaceuticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActuFarmaceuticoActionPerformed
// TODO add your handling code here:
    PasarDatosFarmaceutico();
}//GEN-LAST:event_btnActuFarmaceuticoActionPerformed

private void btnEliminarfarmaceuticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarfarmaceuticoActionPerformed
// TODO add your handling code here:
    Eliminarfarmaceutico();
}//GEN-LAST:event_btnEliminarfarmaceuticoActionPerformed

private void txtCedulaFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCedulaFFocusLost
// TODO add your handling code here:
//    Metodo.validadorDeCedula(txtCedula.getText());
}//GEN-LAST:event_txtCedulaFFocusLost

private void txtCedulaFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaFKeyReleased
// TODO add your handling code here:
    
}//GEN-LAST:event_txtCedulaFKeyReleased

private void txtCedulaFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaFKeyTyped
// TODO add your handling code here:
    //Metodo.Numeros(txtCedula);
    
    
}//GEN-LAST:event_txtCedulaFKeyTyped

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
// TODO add your handling code here:
    Limpiar();
    DIALOGOFARMACEUTICONUEVO.dispose();
    
}//GEN-LAST:event_btnCancelarActionPerformed

private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
// TODO add your handling code here:
    InsertarFarmaceutico();
    //Limpiar();
}//GEN-LAST:event_btnAceptarActionPerformed

private void txtCedulaActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtCedulaActActionPerformed

private void txtNombreActKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreActKeyTyped
// TODO add your handling code here:
    //Metodo.Letras(txtNombreAct);
    
}//GEN-LAST:event_txtNombreActKeyTyped

private void txtApellidoActKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoActKeyTyped
// TODO add your handling code here:
    //Metodo.Letras(txtApellidoAct);
    
}//GEN-LAST:event_txtApellidoActKeyTyped

private void txtSueldoActKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSueldoActKeyTyped
// TODO add your handling code here:
    //Metodo.Letras(txtDireccionAct);
    
}//GEN-LAST:event_txtSueldoActKeyTyped

private void btnAceptarActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActActionPerformed
// TODO add your handling code here:
    ActualizarFarmaceutico();
    
}//GEN-LAST:event_btnAceptarActActionPerformed

private void btnCAncelarActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCAncelarActActionPerformed
// TODO add your handling code here:
    DIALOGOFARMACEUTICOACTUALIZAR.dispose();
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
            java.util.logging.Logger.getLogger(FARMACEUTICO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FARMACEUTICO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FARMACEUTICO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FARMACEUTICO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FARMACEUTICO().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DIALOGOFARMACEUTICOACTUALIZAR;
    private javax.swing.JDialog DIALOGOFARMACEUTICONUEVO;
    private javax.swing.JTable TablaFarmaceutico;
    private javax.swing.JTextField bsNombre;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAceptarAct;
    public static javax.swing.JButton btnActuFarmaceutico;
    private javax.swing.JButton btnCAncelarAct;
    private javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnEliminarfarmaceutico;
    public static javax.swing.JButton btnIngresoFarmaceutico;
    private javax.swing.JButton btnLimpiezaBusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRegistrosFarmaceutico;
    private javax.swing.JTextField txtApellidoAct;
    private letras.soloLetras txtApellidoF;
    private javax.swing.JTextField txtCedulaAct;
    private javax.swing.JTextField txtCedulaF;
    private javax.swing.JTextField txtNombreAct;
    private letras.soloLetras txtNombreF;
    private javax.swing.JTextField txtSueldoAct;
    private componentes.Numeros txtSueldoF;
    // End of variables declaration//GEN-END:variables
}
