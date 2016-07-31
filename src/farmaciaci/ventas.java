/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ventas.java
 *
 * Created on 15/07/2015, 12:09:32 AM
 */
package farmaciaci;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
//import  java.util.Date;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.DocFlavor.STRING;
import javax.swing.JInternalFrame;
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

/**
 *
 * @author ACER
 */
public class ventas extends javax.swing.JFrame {

    /** Creates new form ventas */
    
    DefaultTableModel model;
    public conexion cc=new conexion();
    public Connection cn=cc.conectar();
      int cantidadEsistente=0;
      int j=0;
      public String exis;
      SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    
    public ventas() {
        initComponents();
        cargarTablaMedicamnetos("");
        NumProductos();
    }
    //ventas v=new ventas();
    public ventas(String cedula,String nombre,String apellido,String direccion,String telefono) {
        initComponents();
        
        txtCedula.setText(cedula);
        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        txtDireccion.setText(direccion);     
    }
    
    public  void cargarTablaMedicamnetos(String Dato){
        String [] titulos = {"CODIGO","NOMBRE","PRECIO UNITARIO","STOCK"," CADUCIDAD"};
        String [] registros= new String [5];
        String sql="SELECT * FROM MEDICAMENTOS WHERE NOM_MED LIKE'%"+Dato+"%' ORDER BY COD_MED";
        model = new DefaultTableModel(null,titulos);
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                registros[0]=rs.getString("COD_MED");
                registros[1]=rs.getString("NOM_MED");
                registros[2]=rs.getString("PRE_UNI");
                registros[3]=rs.getString("STOCK");
                registros[4]=rs.getString("FEC_CAD");
                
                model.addRow(registros); 
               TablaMedicamentos.setModel(model);
                }
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "La tabla productos tiene problemas al cargarse");
        }
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
        DIR_CLI=txtDireccion.getText().trim().toUpperCase();
        FONO_CLI=txtTelefono.getText();
        sql="insert into cliente values(?,?,?,?,?)";
        
        PreparedStatement psd;
        try {
            psd=cn.prepareStatement(sql);
            psd.setString(1, CI_CLI);
            psd.setString(2, NOM_CLI);
            psd.setString(3, APE_CLI);
            psd.setString(4, DIR_CLI);
            psd.setString(5, FONO_CLI);
            int n=psd.executeUpdate();
            if(n>0)
            {
                JOptionPane.showMessageDialog(null, "SE INSERTO CORRECTAMENTE EL CLIENTE");
               // CargaTablaDatosClientes("");
               // Limpiar();
                //DIALOGOCLIENTESNUEVO.dispose();
            }
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "EL NUMERO DE CEDULA DEL CLIENTE YA EXISTE ");
            }
        
                
                   }
               }
               
            }
        }
  
    }
     public void NumProductos()
    {
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        int num=1;
        String a;
       
        String sql ="";
        //sql="select * from MEDICAMENTOS";
        sql="select SECUENCIA_VENTA.NEXTVAL from DUAL ";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                //JOptionPane.showMessageDialog(null, "NO");
               // num = rs.getInt("SECUENCIA_MEDICAMENTOS.NEXTVAL");
               // a=rs.getString("SELECT SECUENCIA_VENTA.NEXTVAL from DUAL");
               
                num++;
                //txtCodMed.setText(String.valueOf("PRO00"+num));
               
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE PUEDO GENERAR EL CODIGO");
            
        }
       txtNumeroFactura.setText(String.valueOf(num));
        
    }
    
    public void agregarCompra(){
          
            DefaultTableModel modelo = new DefaultTableModel(){
                        @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
            return false;
            }};

        int aux = TablaMedicamentos.getSelectedRow();
         cantidadEsistente = 0;
        if (aux==-1){
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto","Advertencia",JOptionPane.WARNING_MESSAGE);
        }   
        else{
            if (txtFacturaCantidad.getText().trim().isEmpty()){
                     JOptionPane.showMessageDialog(null, "ingrese una cantidad"); 
                     txtFacturaCantidad.requestFocus();
               }else{ 
                     String Id = TablaMedicamentos.getValueAt(aux, 0).toString();
                     String nombre = TablaMedicamentos.getValueAt(aux, 1).toString();
                     String precio = TablaMedicamentos.getValueAt(aux, 2).toString();
                            
                     String DatoId=Id;
                             
                     int cantidad = Integer.parseInt(txtFacturaCantidad.getText());
                     String sql="select STOCK from MEDICAMENTOS where COD_MED ='"+Id+"'";
                    try {
                            Statement psw = cn.createStatement();
                            ResultSet rs = psw.executeQuery(sql);
                            while(rs.next()){
                            cantidadEsistente=rs.getInt("STOCK");
                            //JOptionPane.showMessageDialog(null, "SI EJECUTO");
                            }
                         } catch (Exception ex) {
                               JOptionPane.showMessageDialog(null, "La cantidad no existe");
                               System.out.println(cantidadEsistente);
                            }  
                    if((cantidad > cantidadEsistente)||(cantidad==0)){
                           JOptionPane.showMessageDialog(null, "La cantidad maxima existente es "+cantidadEsistente,"Alerta",JOptionPane.WARNING_MESSAGE); 
                           txtFacturaCantidad.setText("");
                           txtFacturaCantidad.requestFocus();
                    }else {
                            int contLista =TablaListaMedicamentos.getRowCount();
                            int auxiliarLista=0;
                            JOptionPane.showMessageDialog(null, "S");
//                            if (contLista!=0){
//                                //int datoBuscar=Integer.parseInt(DatoId);
//                                for (int l =0; l < contLista; l++){
//                                    String Idlista = TablaListaMedicamentos.getValueAt(l, 0).toString();
//                                    //int datoBuscar2 = Integer.parseInt(Idlista);
//                                    if(DatoId.equals(Idlista)/*datoBuscar==datoBuscar2*/){
//                                        auxiliarLista=1;
//                                    }
//                                }
//                            }
                            //if(TablaListaMedicamentos==null)/*{
                                JOptionPane.showMessageDialog(null, "El producto ya esta en la lista");
                                txtFacturaCantidad.setText("");
                            //} else */{
                                JOptionPane.showMessageDialog(null, "AASAAS");
                                //n=n+1;
                                String aux2 = precio;
                                float tot = Float.parseFloat(aux2);                       
                                tot=tot*cantidad;
//                                //total=tot+total;                        
//                                double newTotal= Math.rint(total*1000)/1000;
//                                String aux3 = String.valueOf(newTotal);     
//                                txtTotal.setText(aux3);
//                                iva=newTotal*0.12;
//                                double newIva=Math.rint(iva*1000)/1000;
//                                txtIva.setText(String.valueOf(newIva));
//                                subT=newTotal-iva;
//                                double newSub=Math.rint(subT*1000)/1000;
//                                txtsub.setText(String.valueOf(newSub));
                                DefaultTableModel temp = (DefaultTableModel) 
                                TablaListaMedicamentos.getModel();
                                Object nuevo[]= {temp.getRowCount()+1,"","","",""};
                                temp.addRow(nuevo);
                                TablaListaMedicamentos.setValueAt(Id, j, 0);
                                TablaListaMedicamentos.setValueAt(nombre, j, 1);
                                TablaListaMedicamentos.setValueAt(cantidad, j, 3);//
                                TablaListaMedicamentos.setValueAt(precio, j, 2);//
                                TablaListaMedicamentos.setValueAt(tot, j, 4);
                                j++;//Aumenta el contador
                                cargarTablaMedicamnetos("");
                               //disminuir();
                              }
//                           totalCompra= Double.parseDouble(txtTotal.getText());               
                        //}
                    }
            }
        
    }
    
    public void detalle(){
        if(txtNumeroFactura.getText().trim().isEmpty()){
           JOptionPane.showMessageDialog(null, "No puede ingresar una factura sin numero");
        } else{
            if(txtFamaceutico.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Usted No se a Identificado como Usuario");
                //txtUsuId.requestFocus();
                //txtUsuId.setEditable(true);
            }else{
                 if(txtCedula.getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(null, "No puede ingresar una facutura sin cedula");
                        txtCedula.setEnabled(true); 
                        txtCedula.requestFocus();
                 } else{
                     if(txtFechaCompra.getDate()==null){
                         JOptionPane.showMessageDialog(null, "No puede ingresar una factura sin fecha");
                         txtFechaCompra.requestFocus();
                     }else{
                        GuardarDetalle();
                        }
                  }
                }
            }
    }
    
    public void GuardarDetalle(){
        try{
        DefaultTableModel modelo = new DefaultTableModel(){
                        @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
            return false;
            }};
        try {
            guardarFactura();
            int fila= TablaListaMedicamentos.getRowCount();
            JOptionPane.showMessageDialog(null, String.valueOf(fila));
            String sql = "insert into DETALLE_VENTA (NUM_DET,COD_MED_P,CAN_MED_V,NUM_VEN_P ) values (?,?,?,?)";
            PreparedStatement psw =cn.prepareStatement(sql);
            for(int i=0;i<=fila;i++){
                String numDetalle= TablaListaMedicamentos.getValueAt(i, 0).toString();
            String codigoProduto= TablaListaMedicamentos.getValueAt(i, 1).toString();
            String cantidad=TablaListaMedicamentos.getValueAt(i, 2).toString();
            String factura = txtNumeroFactura.getText();
            //JOptionPane.showMessageDialog(null, "factura: "+factura+" cantidad: "+cantidad+"  codigo pro:  "+codigoProduto);
           psw.setInt(1,Integer.parseInt(numDetalle));
            psw.setInt(2,Integer.parseInt(factura));//num_factura
            psw.setInt(3,Integer.parseInt(codigoProduto)); //codigo_producto
            psw.setDouble(4,Double.parseDouble(cantidad)); //cantidad
            psw.executeUpdate();
            }
           } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null,"no insertado");
            }
        }catch (Exception ex){
            
        }
    }
    
    public void disminuir(){
        try{
        String sql="";
        int fila=TablaMedicamentos.getSelectedRow();
        String canTprod=txtFacturaCantidad.getText(); //Selecciona la cantidad del producto del detalle
        String codTprod=TablaMedicamentos.getValueAt(fila, 0).toString(); // Selecciona el codigo de la tabla detalle el codigo del produc 
        try {
            Statement st=cn.createStatement();
            String sql1="";
            sql1="SELECT STOCK FROM MEDICAMENTOS WHERE COD_MED='"+codTprod+"'";
            ResultSet rs=st.executeQuery(sql1);
            while(rs.next()){
                 exis = rs.getString("STOCK");
                 JOptionPane.showMessageDialog(null, "EL STOCK DEL MEDICAMENTO ES "+exis);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR EXISTENCIA ");
        }
        int disminuye=Integer.valueOf(exis)-Integer.valueOf(canTprod);
        try {
            sql="UPDATE MEDICAMENTOS SET STOCK="+disminuye+" where COD_MED='"+codTprod+"'";
            PreparedStatement pst=cn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Existencia Disminuyo");
                cargarTablaMedicamnetos("");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR No se aumento");
        }
        } catch (Exception ex)  {
            
        }
    }
    
    public void guardarFactura(){
        if(txtNumeroFactura.getText().trim().isEmpty())
        {
           JOptionPane.showMessageDialog(null, "No puede ingresar una factura sin numero");
        } 
        else{
            if(txtFamaceutico.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Usted No se a Identificado como Usuario");
            // txtUsuId.requestFocus();
             //txtUsuId.setEnabled(true);
        }
        else{
                 if(txtCedula.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "No puede ingresar una facutura sin cedula");
            txtCedula.setEnabled(true); 
            txtCedula.requestFocus();
        }
        else{
               if(txtFechaCompra.getDate()==null){
                   JOptionPane.showMessageDialog(null, "No puede ingresar una facutura sin Fecha");
                   // txtFecha.setEnabled(true); 
                    //txtFecha.requestFocus();
               }
               else{
                String fecha,Usu_Id;
                int Fac_Id;
                Double sub,iva,total;
                    String  Cli_Cedula;
                    String sql = "";
                    Fac_Id = Integer.parseInt(txtNumeroFactura.getText());
                    //fecha = new SimpleDateFormat("dd/MM/yyyy").format(txtFechaCompra.getDate());
                    ///////////////////
                    String FEC_COM;
                    java.util.Date fecha1 = txtFechaCompra.getDate();
                    fecha = df.format(fecha1);
                    FEC_COM=fecha; 
                    /////////////////
                    Cli_Cedula= txtCedula.getText();
                    Usu_Id=txtFamaceutico.getText();
                    //sub=Double.valueOf(txtsub.getText());
                    //iva=Double.valueOf(txtIva.getText());
                    //total=Double.valueOf(txtTotalPagar.getText());
                    String total1=txtTotalPagar.getText();
                try {
                    sql = "insert into VENTAS(NUM_VEN, F_H_VEN, TOTAL,CI_FAR_P,CI_CLI_P)values(?,?,?,?,?)";
                            PreparedStatement psw =cn.prepareStatement(sql);
                            psw.setInt(1, Fac_Id);                           
                            psw.setString(2,FEC_COM );//convertirFecha(fecha));
                            psw.setString(3, total1);
                            psw.setString(4, Usu_Id);
                            //psw.setDouble(5, sub);
                            //psw.setDouble(6, iva);
                            //psw.setString(5, Usu_Id);
                            psw.setString(5, Cli_Cedula);
                            int n = psw.executeUpdate();
                            
                            if(n>0)
                            {
                                JOptionPane.showMessageDialog(null, "Se inserto correctamente la factura");
                            }
                       
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "No se inserto corretamente la factura"+ex);
                   } 
               }      
        }
        }
        }
    }
    
    

 
    
//    public void CargaTablaDatosClientes(String Dato1)
//    {
//        String [] titulos={"CEDULA","NOMBRE","APELLIDO","DIRECCION","TELEFONO"};
//        String [] registros= new String[5];
//        String sql="";
//        conexion cc=new conexion();
//        Connection cn=cc.conectar();
////        sql="select * from cliente where CI_CLI like '%"+Dato1+"%' OR NOM_CLI like '%"+Dato1+"%' OR APE_CLI like '%"+Dato1+"%' ORDER BY APE_CLI";
//                sql="select * from cliente where NOM_CLI like '%"+Dato1+"%' ORDER BY APE_CLI";
//
//        model = new DefaultTableModel(null,titulos);
//        
//        try {
//            Statement st = cn.createStatement();
//            ResultSet rs=st.executeQuery(sql);
//            while(rs.next())
//            {
//                registros[0]=rs.getString("CI_CLI");
//                registros[1]=rs.getString("NOM_CLI");
//                registros[2]=rs.getString("APE_CLI");
//                registros[3]=rs.getString("DIR_CLI");
//                registros[4]=rs.getString("FONO_CLI");
//                model.addRow(registros);
//                
//            }
//            TablaClientes.setModel(model);
//            //int registro=TablaClientes.getRowCount();
//           // lblRegistrosClientes.setText(String.valueOf(registro));
//            
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
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

        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaMedicamentos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaListaMedicamentos = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNumeroFactura = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtFacturaCantidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTotalPagar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFamaceutico = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtFechaCompra = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FYBECA-AZUL_VENTANAS.jpg"))); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        TablaMedicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaMedicamentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TablaMedicamentosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TablaMedicamentos);

        TablaListaMedicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NUM DET", "COD MED", "NOMBRE", "PRECIO", "CANTIDAD", "SUBTOTAL"
            }
        ));
        TablaListaMedicamentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TablaListaMedicamentosKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(TablaListaMedicamentos);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Algerian", 1, 36));
        jLabel9.setText("VENTAS");

        jLabel1.setText("RUC:1093526745");

        jLabel3.setText("Nº FACTURA:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jButton4.setText("Guardar Compra");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("Nueva Compra");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setText("Reporte mas vendido");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton5))
                .addContainerGap(243, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cientes"));

        jLabel4.setText("Cedula");

        jLabel5.setText("Nombre");

        jLabel6.setText("Apellido");

        jLabel7.setText("Direccion");

        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });
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

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoKeyReleased(evt);
            }
        });

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        jLabel20.setText("Telefono");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTelefono)
                    .addComponent(txtDireccion)
                    .addComponent(txtApellido)
                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText("GUARDAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("SELECCIONAR MEDICAMENTO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setText("TOTAL A PAGAR:");

        jLabel10.setText("Farmaceutico");

        jLabel11.setText("Fecha de Compra");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFamaceutico, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel9)
                        .addGap(202, 202, 202)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(412, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtFacturaCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(291, 291, 291))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFamaceutico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(txtFacturaCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void TablaMedicamentosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaMedicamentosKeyReleased
// TODO add your handling code here:
    //if(evt.getKeyChar()==KeyEvent.VK_ENTER)
    //{
    //    ActualizarTablaDatos();
    //}
}//GEN-LAST:event_TablaMedicamentosKeyReleased

private void TablaListaMedicamentosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaListaMedicamentosKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_TablaListaMedicamentosKeyReleased

private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
// TODO add your handling code here:
        if(txtCedula.getText().length()==10&&txtNombre.getText().length()>=1&&txtApellido.getText().length()>=1&&txtDireccion.getText().length()>=1)
                    {
                       //txtFacturaCantidad.setEnabled(true); 
                        cargarTablaMedicamnetos("");
                        txtNombre.setEnabled(false);
                    }
        else{
            if(txtNombre.getText().trim().length()>=1)
            {
                 txtNombre.setEnabled(false);
                 TablaMedicamentos.setEnabled(true);
            }
            else{
                txtNombre.setEnabled(true);
//                txtNombre.requestFocus();
            }
        }
}//GEN-LAST:event_txtCedulaActionPerformed

private void txtCedulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCedulaFocusLost
// TODO add your handling code here:
    //c.validacion(evt);
}//GEN-LAST:event_txtCedulaFocusLost

private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
// TODO add your handling code here:
    //c.controlCaracteres(evt);
}//GEN-LAST:event_txtCedulaKeyTyped

private void txtCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyReleased
// TODO add your handling code here:
    if(txtCedula.getText().length()<=9)
    {
        txtNombre.setText("");
                txtApellido.setText("");
                txtDireccion.setText("");
                txtTelefono.setText("");
    }
        else{
    
    if(txtCedula.getText().length()==10)
    {
    
    String Dato =txtCedula.getText();
        String sql="select * from cliente  where ci_cli like'%"+Dato+"%'";
        try {
            Statement psw = cn.createStatement();
            ResultSet rs = psw.executeQuery(sql);
            while(rs.next()){
                txtNombre.setText(rs.getString("nom_cli"));
                txtApellido.setText(rs.getString("ape_cli"));
                txtDireccion.setText(rs.getString("dir_cli"));
                txtTelefono.setText( rs.getString("fono_cli"));
//                 txtBuscarNombrePro.setEnabled(true);  
//                 Tproductos.setEnabled(true);

                }
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No existe el cliente");
        }
    }
    }
}//GEN-LAST:event_txtCedulaKeyReleased

private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
// TODO add your handling code here:
//    controlSoloLetras(evt);
}//GEN-LAST:event_txtNombreKeyTyped

private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
// TODO add your handling code here:
    //txtNombre.setText((txtNombre.getText().toUpperCase()));
}//GEN-LAST:event_txtNombreKeyReleased

private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
    //controlSoloLetras(evt);
}//GEN-LAST:event_txtApellidoKeyTyped

private void txtApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyReleased
// TODO add your handling code here:
 //   txtApellido.setText((txtApellido.getText().toUpperCase()));
}//GEN-LAST:event_txtApellidoKeyReleased

private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
// TODO add your handling code here:
//    controlDireccion(evt);
}//GEN-LAST:event_txtDireccionKeyTyped

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    //InsertarClientes();
    
    
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
    agregarCompra();
    //disminuir();
    
    cargarTablaMedicamnetos("");
    
}//GEN-LAST:event_jButton3ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
// TODO add your handling code here:
    detalle();
}//GEN-LAST:event_jButton4ActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
// TODO add your handling code here:
    
    try {
            // TODO add your handling code here:
                conexion cc = new conexion();
                        Connection cn = cc.conectar();
                        Map a = new HashMap();
                        //a.put("direccion",txtdireccion.getText());
//                        a.put("estadocivil",txtEstCivil.getText());
                        //JasperReport  reporte = JasperCompileManager.compileReport("JasperReport  reporte = JasperCompileManager.compileReport("C:/Users/Usuario/Documents/NetBeansProjects/FarmaciaCI/src/farmaciaci/reMasVendidort1.jrxml");;
                        JasperReport  reporte = JasperCompileManager.compileReport("C:/Users/Usuario/Documents/NetBeansProjects/FarmaciaCI/src/farmaciaci/reMasVendidort1.jasper");
                        JasperPrint print = JasperFillManager.fillReport(reporte, a,cc.conectar());
                        JasperViewer.viewReport(print,false);
        } catch (JRException ex) {
           JOptionPane.showMessageDialog(null, ex);
        }
}//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ventas().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaListaMedicamentos;
    private javax.swing.JTable TablaMedicamentos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFacturaCantidad;
    private javax.swing.JTextField txtFamaceutico;
    private com.toedter.calendar.JDateChooser txtFechaCompra;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumeroFactura;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTotalPagar;
    // End of variables declaration//GEN-END:variables
}
