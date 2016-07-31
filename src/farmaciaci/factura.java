/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * factura.java
 *
 * Created on 30-ene-2015, 14:45:08
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
/*import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;*/


/**
 *
 * @author usuario
 */
public class factura extends javax.swing.JInternalFrame {
//VerificarCedula c=new VerificarCedula();
    /** Creates new form factura */
    DefaultTableModel model;
    public conexion cc=new conexion();
    public Connection cn=cc.conectar();
    Double precio;
    String nombre;
    Double total=0.00;
    int n =0;
    int j=0;
    int cantidadEsistente=0;
    double totalCompra=0,iva=0.0,subT=0.0;
    public String exis,caja;
    public factura() {
        initComponents();
//AQUI        numFc();
        cargarTablaMedicamnetos("");
        //jButton1.setVisible(false);
        txtNumFac.setEnabled(false);
        txtIva.setEditable(false);
        txtsub.setEditable(false);
        txtTotal.setEditable(false);
        Calendar cal=Calendar.getInstance(); 
        txtUsuId.setEditable(true);
        btnEliminar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnRealizarCompra.setEnabled(false);
        btnImprimir.setEnabled(false);
        TablaMedicamentso.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(TablaMedicamentso.getSelectedRow()!=-1)
                {
                    int fila = TablaMedicamentso.getSelectedRow();
                    btnRealizarCompra.setEnabled(true);

                    if(txtCedula.getText().length()==10&&txtNombre.getText().length()>=1&&txtApellido.getText().length()>=1&&txtDireccion.getText().length()>=1)
                    {
                       txtFacturaCantidad.setEnabled(true); 
                    }
                }
            }
        });
        
    }    
    public void limpiar(){
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtFacturaCantidad.setText("");
        txtNumFac.setText("");
        txtIva.setText("");
        txtsub.setText("");
        txtTotal.setText("");        
        Tlista.removeAll();
//         try{
//             DefaultTableModel temp;
//           temp = (DefaultTableModel) Tlista.getModel();
//           int a =temp.getRowCount();
//           for(int i=0; i<a; i++)
//               temp.removeRow(i);
//       }catch(Exception e){
//           System.out.println(e);
//       }
        
    }
    public void controlSoloLetras(java.awt.event.KeyEvent evt) {                                         
        char c = evt.getKeyChar();
        if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z'))evt.consume();
    }
     public void controlDireccion(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')&&(c < '0' || c > '9')&&c!='-') {
            evt.consume();
        }
    }
    
    public factura(String cedula,String nombre,String apellido,String direccion,String telefono) {
        initComponents();
        
        txtCedula.setText(cedula);
        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        txtDireccion.setText(direccion);  
        txtTelefono.setText(telefono);
    }
//    public void numFc(){
//        
//        String sql="SELECT * FROM FACTURAS ";
//        int numFac=1;
//        try {
//            Statement psw = cn.createStatement();
//            ResultSet rs = psw.executeQuery(sql);
//            while(rs.next()){
//                         numFac++;                
//                  }
//            } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "error en el numerode factura");
//        }
//        txtNumFac.setText(String.valueOf(numFac)); 
//    }
    public  Date convertirFecha(String fech) throws ParseException     {
      java.sql.Date sqlDate=null;
    try {
        
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
      java.util.Date utilDate = formatter.parse(fech);
      sqlDate = new java.sql.Date(utilDate.getTime());
      return sqlDate;
    } catch (ParseException e) {
      e.printStackTrace();
      return sqlDate; 
    }
    }
    public void guardarFactura(){
        if(txtNumFac.getText().trim().isEmpty())
        {
           JOptionPane.showMessageDialog(null, "No puede ingresar una factura sin numero");
        } 
        else{
            if(txtUsuId.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Usted No se a Identificado como Usuario");
             txtUsuId.requestFocus();
             txtUsuId.setEnabled(true);
        }
        else{
                 if(txtCedula.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "No puede ingresar una facutura sin cedula");
            txtCedula.setEnabled(true); 
            txtCedula.requestFocus();
        }
        else{
               if(txtFecha.getDate()==null){
                   JOptionPane.showMessageDialog(null, "No puede ingresar una facutura sin Fecha");
                    txtFecha.setEnabled(true); 
                    txtFecha.requestFocus();
               }
               else{
                String fecha,Usu_Id;
                int Fac_Id;
                Double sub,iva,tot;
                    String  Fac_Cedula;
                    String sql = "";
                    Fac_Id = Integer.parseInt(txtNumFac.getText());
                    fecha = new SimpleDateFormat("yyyy/MM/dd").format(txtFecha.getDate());
                    Fac_Cedula= txtCedula.getText();
                    Usu_Id=txtUsuId.getText();
                    sub=Double.valueOf(txtsub.getText());
                    iva=Double.valueOf(txtIva.getText());
                    tot=Double.valueOf(txtTotal.getText());
                try {
                    sql = "insert into facturas(num_fac, fec_fac, ci_cli_fac,ci_emp_fac,sub_tot_fac,iva_fac,total_pagar)values(?,?,?,?,?,?,?)";
                            PreparedStatement psw =cn.prepareStatement(sql);
                            psw.setInt(1, Fac_Id);                           
                            psw.setDate(2, convertirFecha(fecha));
                            psw.setString(3, Fac_Cedula);
                            psw.setString(4, Usu_Id);
                            psw.setDouble(5, sub);
                            psw.setDouble(6, iva);
                            psw.setDouble(7, tot);
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

    public void guardarCliente() {
        
       
        if(txtCedula.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "No puede ingresar un cliente sin cedula");
             txtCedula.requestFocus();
        }
        else{
        if(txtNombre.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "No puede ingresar un cliente sin nombre");
             txtNombre.requestFocus();
        }
        else{
        if(txtApellido.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "No puede ingresar un cliente sin apellido");
             txtApellido.requestFocus();
        }
        else{
            if(txtDireccion.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "No puede ingresar un cliente sin direccion");
             txtDireccion.requestFocus();
             
        }
        else{

        try {
                    String  Fac_Nombre, Fac_Apellido,Fac_Direccion ,Fac_Cedula,Fac_Telefono;
                    String sql = "";
                    Fac_Nombre = txtNombre.getText();
                    Fac_Apellido = txtApellido.getText();
                  
                    Fac_Direccion =txtDireccion.getText();
                    Fac_Cedula= txtCedula.getText();

                    sql = "insert into clientes(ci_cli, nom_cli, ape_cli, dir_cli )values(?,?,?,?)";
                        
                            PreparedStatement psw =cn.prepareStatement(sql);
                           
                            psw.setString(1, Fac_Cedula);
                            psw.setString(2, Fac_Nombre);
                            psw.setString(3, Fac_Apellido);
                            psw.setString(4, Fac_Direccion);
                            int n = psw.executeUpdate();
                            
                            if(n>0)
                            {
                                JOptionPane.showMessageDialog(null, "Se agrego un nuevo cliente");
                                TablaMedicamentso.setEnabled(true);
                                txtFacturaCantidad.setEnabled(true);
                                txtCedula.setEnabled(false);
                                txtNombre.setEnabled(false);
                                txtApellido.setEnabled(false);
                                txtDireccion.setEnabled(false);
                                //btnGuardarCliente.setEnabled(false);
                                txtBuscarNombrePro.setEnabled(true);

                            }
                       
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "El cliente ya existe"+ex);
           } 
        }
        }
        }
        } 
        
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
               TablaMedicamentso.setModel(model);
                }
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "La tabla productos tiene problemas al cargarse");
        }
    }
         
    public void agregarCompra(){
          
            DefaultTableModel modelo = new DefaultTableModel(){
                        @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
            return false;
            }};

        int aux = TablaMedicamentso.getSelectedRow();
         cantidadEsistente = 0;
        if (aux==-1){
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto","Advertencia",JOptionPane.WARNING_MESSAGE);
        }   
        else{
            if (txtFacturaCantidad.getText().trim().isEmpty()){
                     JOptionPane.showMessageDialog(null, "ingrese una cantidad"); 
                     txtFacturaCantidad.requestFocus();
               }else{ 
                     String Id = TablaMedicamentso.getValueAt(aux, 0).toString();
                     String nombre = TablaMedicamentso.getValueAt(aux, 1).toString();
                     String precio = TablaMedicamentso.getValueAt(aux, 3).toString();
                            
                     String DatoId=Id;
                             
                     int cantidad = Integer.parseInt(txtFacturaCantidad.getText());
                     String sql="select existencia from productos where cod_prod = "+DatoId+"";
                    try {
                            Statement psw = cn.createStatement();
                            ResultSet rs = psw.executeQuery(sql);
                            while(rs.next()){
                            cantidadEsistente=rs.getInt("existencia");
                            }
                         } catch (Exception ex) {
                               JOptionPane.showMessageDialog(null, "La cantidad no existe");
                            }  
                    if((cantidad > cantidadEsistente)||(cantidad==0)){
                           JOptionPane.showMessageDialog(null, "La cantidad maxima existente es "+cantidadEsistente,"Alerta",JOptionPane.WARNING_MESSAGE); 
                           txtFacturaCantidad.setText("");
                           txtFacturaCantidad.requestFocus();
                    }else {
                            int contLista =Tlista.getRowCount();
                            int auxiliarLista=0;
                            if (contLista!=0){
                                int datoBuscar=Integer.parseInt(DatoId);
                                for (int l =0; l < contLista; l++){
                                    String Idlista = Tlista.getValueAt(l, 0).toString();
                                    int datoBuscar2 = Integer.parseInt(Idlista);
                                    if(datoBuscar==datoBuscar2){
                                        auxiliarLista=1;
                                    }
                                }
                            }
                            if(auxiliarLista==1){
                                JOptionPane.showMessageDialog(null, "El producto ya esta en la lista");
                                txtFacturaCantidad.setText("");
                            } else {
                                n=n+1;
                                String aux2 = precio;
                                float tot = Float.parseFloat(aux2);                       
                                tot=tot*cantidad;
                                total=tot+total;                        
                                double newTotal= Math.rint(total*1000)/1000;
                                String aux3 = String.valueOf(newTotal);     
                                txtTotal.setText(aux3);
                                iva=newTotal*0.12;
                                double newIva=Math.rint(iva*1000)/1000;
                                txtIva.setText(String.valueOf(newIva));
                                subT=newTotal-iva;
                                double newSub=Math.rint(subT*1000)/1000;
                                txtsub.setText(String.valueOf(newSub));
                                DefaultTableModel temp = (DefaultTableModel) 
                                Tlista.getModel();
                                Object nuevo[]= {temp.getRowCount()+1,"","","",""};
                                temp.addRow(nuevo);
                                Tlista.setValueAt(Id, j, 0);
                                Tlista.setValueAt(nombre, j, 1);
                                Tlista.setValueAt(cantidad, j, 2);
                                Tlista.setValueAt(precio, j, 3);
                                Tlista.setValueAt(tot, j, 4);
                                j++;//Aumenta el contador
                                cargarTablaMedicamnetos("");
//                                disminuir();
                              }
                            totalCompra= Double.parseDouble(txtTotal.getText());               
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
            int fila= Tlista.getRowCount();
            JOptionPane.showMessageDialog(null, String.valueOf(fila));
            String sql = "insert into det_facturas (num_fac_p,cod_prod_p,can_prod ) values (?,?,?)";
            PreparedStatement psw =cn.prepareStatement(sql);
            for(int i=0;i<=fila;i++){
            String codigoProduto= Tlista.getValueAt(i, 0).toString();
            String cantidad=Tlista.getValueAt(i, 2).toString();
            String factura = txtNumFac.getText();
            JOptionPane.showMessageDialog(null, "factura: "+factura+" cantidad: "+cantidad+"  codigo pro:  "+codigoProduto);
            psw.setInt(1,Integer.parseInt(factura));//num_factura
            psw.setInt(2,Integer.parseInt(codigoProduto)); //codigo_producto
            psw.setDouble(3,Double.parseDouble(cantidad)); //cantidad
            psw.executeUpdate();
            }
           } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null,"no insertado");
            }
        }catch (Exception ex){
            
        }
    }
    
    public void detalle(){
        if(txtNumFac.getText().trim().isEmpty()){
           JOptionPane.showMessageDialog(null, "No puede ingresar una factura sin numero");
        } else{
            if(txtUsuId.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Usted No se a Identificado como Usuario");
                txtUsuId.requestFocus();
                txtUsuId.setEditable(true);
            }else{
                 if(txtCedula.getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(null, "No puede ingresar una facutura sin cedula");
                        txtCedula.setEnabled(true); 
                        txtCedula.requestFocus();
                 } else{
                     if(txtFecha.getDate()==null){
                         JOptionPane.showMessageDialog(null, "No puede ingresar una factura sin fecha");
                         txtFecha.requestFocus();
                     }else{
                        GuardarDetalle();
                        }
                  }
                }
            }
    }
    public void eliminarProdDetalle(){
        String sql="";
        DefaultTableModel modelo=(DefaultTableModel) Tlista.getModel();
        int fila=Tlista.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }else {
        String canTlista=Tlista.getValueAt(fila, 2).toString(); //Selecciona la cantidad del producto del detalle
        String codTlista=Tlista.getValueAt(fila, 0).toString(); // Selecciona el codigo de la tabla detalle el codigo del produc
//        int precio1=Integer.parseInt(Tlista.getValueAt(fila, 3).toString());
        double valorcalc=Double.parseDouble(Tlista.getValueAt(fila, 4).toString());
        double a=totalCompra-valorcalc;
        double b=a*0.12;
        double c= a-b;
        modelo.removeRow(fila); //limina la fila seleccionada   
        txtTotal.setText(String.valueOf(a));
        txtIva.setText(String.valueOf(b));
        txtsub.setText(String.valueOf(c));
        try {
            Statement st=cn.createStatement();
            String sql1="";
            sql1="SELECT EXISTENCIA FROM PRODUCTOS WHERE COD_PROD='"+codTlista+"'";
            ResultSet rs=st.executeQuery(sql1);
            while(rs.next()){
                 exis = rs.getString("EXISTENCIA");
//                 JOptionPane.showMessageDialog(null, "La existencia es "+a);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR EXISTENCIA ");
        }
        int aumenta=Integer.valueOf(exis)+Integer.valueOf(canTlista);
        try {
            sql="UPDATE PRODUCTOS SET EXISTENCIA="+aumenta+" where COD_PROD='"+codTlista+"'";
            PreparedStatement pst=cn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if(n>0){
//                JOptionPane.showMessageDialog(null, "Existencia Aumento");
                cargarTablaMedicamnetos("");
            }       
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "ERROR No se aumento"+aumenta);
        }
        }
    }
    
    public void resta(){
        try {
            String sql="";
            PreparedStatement pst = cn.prepareStatement(sql);
            int resta;
            int fila=TablaMedicamentso.getSelectedRow();
            resta=Integer.parseInt(TablaMedicamentso.getValueAt(fila, 2).toString())-Integer.parseInt(txtFacturaCantidad.getText());
            System.out.println(resta);
            sql="UPDATE PRODUCTOS SET EXISTENCIA="+resta+" where COD_PROD='"+TablaMedicamentso.getValueAt(fila, 0) +"'";
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Existencia Disminuyo");
                cargarTablaMedicamnetos("");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void disminuir(){
        try{
        String sql="";
        int fila=TablaMedicamentso.getSelectedRow();
        String canTprod=txtFacturaCantidad.getText(); //Selecciona la cantidad del producto del detalle
        String codTprod=TablaMedicamentso.getValueAt(fila, 0).toString(); // Selecciona el codigo de la tabla detalle el codigo del produc 
        try {
            Statement st=cn.createStatement();
            String sql1="";
            sql1="SELECT EXISTENCIA FROM PRODUCTOS WHERE COD_PROD='"+codTprod+"'";
            ResultSet rs=st.executeQuery(sql1);
            while(rs.next()){
                 exis = rs.getString("EXISTENCIA");
                 JOptionPane.showMessageDialog(null, "La existencia es "+exis);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR EXISTENCIA ");
        }
        int disminuye=Integer.valueOf(exis)-Integer.valueOf(canTprod);
        try {
            sql="UPDATE PRODUCTOS SET EXISTENCIA="+disminuye+" where COD_PROD='"+codTprod+"'";
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
    public void cajachicha(){
        try{
        try {
            String sql1="SELECT * FROM CAJA";
        int codigo=1;
        try {
            Statement psw = cn.createStatement();
            ResultSet rs = psw.executeQuery(sql1);
            while(rs.next()){
                         codigo++;                
                  }
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "error en el codigo caja");
        }
        
        caja=String.valueOf(codigo);
        
            String sql="";
//AQUI            numFc();
            int num=Integer.parseInt(txtNumFac.getText());
            double saldo=Double.parseDouble(txtTotal.getText());
            sql="INSERT INTO caja(cod_caj,num_fac_caj,saldo_caj)values(?,?,?)";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(caja));
            pst.setInt(2, num);
            pst.setDouble(3, saldo);
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Se agrego a la caja chicha");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:"+ex);
        }
        }catch(Exception ex) {
            
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        txtFecha = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        btnRealizarCompra = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNumFac = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaMedicamentso = new javax.swing.JTable();
        txtFacturaCantidad = new javax.swing.JTextField();
        panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tlista = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtsub = new javax.swing.JTextField();
        txtIva = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtBuscarNombrePro = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cientes"));

        jLabel1.setText("Cedula");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");

        jLabel4.setText("Direccion");

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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaKeyReleased(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTelefono)
                    .addComponent(txtDireccion)
                    .addComponent(txtApellido)
                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Fecha");

        jLabel7.setText("Farmaceutico");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FYBECA-AZUL_VENTANAS.jpg"))); // NOI18N
        jLabel8.setText("jLabel8");

        btnEliminar.setText("Eliminar Detalle");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnRealizarCompra.setText("Realizar compra");
        btnRealizarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarCompraActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jButton2.setText("Nuevo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(btnRealizarCompra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRealizarCompra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Sitka Small", 1, 16)); // NOI18N
        jLabel9.setText("FARMACIA FYBECA");

        jLabel10.setText("AMBATO - ECUADOR");

        jLabel11.setText("Telefono 0981089745");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("RUC: 1804983102001");

        jLabel13.setText("Num");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("FACTURA DE VENTA");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNumFac))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel14))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtNumFac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TablaMedicamentso.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        TablaMedicamentso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(TablaMedicamentso);

        txtFacturaCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFacturaCantidadKeyTyped(evt);
            }
        });

        panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Tlista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codido", "Nombre", "Cantidad", "Precio Unidad", "Total"
            }
        ));
        jScrollPane1.setViewportView(Tlista);

        jLabel15.setText("SubTotal");

        jLabel16.setText("IVA");

        jLabel17.setText("Total a Pagar");

        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtsub, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIva, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addGap(213, 213, 213)
                        .addComponent(jLabel17)
                        .addGap(31, 31, 31)
                        .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txtsub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel19.setText("Cantidad");

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Medicamento"));

        txtBuscarNombrePro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarNombreProActionPerformed(evt);
            }
        });
        txtBuscarNombrePro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarNombreProKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(txtBuscarNombrePro, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBuscarNombrePro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnAgregar.setText("Seleccionar  Producto");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        btnAgregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAgregarKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("Productos");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFacturaCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnAgregar)
                        .addGap(311, 311, 311)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtFacturaCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregar)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(181, 181, 181)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(37, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    
}//GEN-LAST:event_btnCancelarActionPerformed

private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
// TODO add your handling code here:
    eliminarProdDetalle();   
    
}//GEN-LAST:event_btnEliminarActionPerformed

private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
    agregarCompra();
    disminuir();
//    resta();
    cargarTablaMedicamnetos("");
    btnEliminar.setEnabled(true);
    btnCancelar.setEnabled(true);
}//GEN-LAST:event_btnAgregarActionPerformed

private void btnAgregarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAgregarKeyPressed
// TODO add your handling code here:
//    agregarCompra();
}//GEN-LAST:event_btnAgregarKeyPressed

private void txtCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyReleased
// TODO add your handling code here:
    if(txtCedula.getText().length()<=9)
    {
        txtNombre.setText("");
                txtApellido.setText("");
                txtDireccion.setText("");
    }
        else{
    
    if(txtCedula.getText().length()==10)
    {
    
    String Dato =txtCedula.getText();
        String sql="select * from clientes  where ci_cli like'%"+Dato+"%'";
        try {
            Statement psw = cn.createStatement();
            ResultSet rs = psw.executeQuery(sql);
            while(rs.next()){
                txtNombre.setText(rs.getString("nom_cli"));
                txtApellido.setText(rs.getString("ape_cli"));
                txtDireccion.setText(rs.getString("dir_cli"));
//                 txtBuscarNombrePro.setEnabled(true);  
//                 Tproductos.setEnabled(true);

                }
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No existe el cliente");
        }
    }
    }
}//GEN-LAST:event_txtCedulaKeyReleased

private void txtBuscarNombreProKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarNombreProKeyReleased
// TODO add your handling code here:
    cargarTablaMedicamnetos(txtBuscarNombrePro.getText());
    txtBuscarNombrePro.setText((txtBuscarNombrePro.getText().toUpperCase()));
}//GEN-LAST:event_txtBuscarNombreProKeyReleased

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
                 TablaMedicamentso.setEnabled(true);
            }
            else{
                txtNombre.setEnabled(true);
//                txtNombre.requestFocus();
            }
        }
}//GEN-LAST:event_txtCedulaActionPerformed

private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
// TODO add your handling code here:
    //c.controlCaracteres(evt);
}//GEN-LAST:event_txtCedulaKeyTyped

private void txtCedulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCedulaFocusLost
// TODO add your handling code here:
    //c.validacion(evt);
}//GEN-LAST:event_txtCedulaFocusLost

private void btnRealizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarCompraActionPerformed
    detalle();
    btnCancelar.setEnabled(false);
    btnEliminar.setEnabled(false);
    btnImprimir.setEnabled(true);
//    cajachicha();
    
}//GEN-LAST:event_btnRealizarCompraActionPerformed
private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
    System.exit(0);
}//GEN-LAST:event_btnSalirActionPerformed

private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtTotalActionPerformed

private void txtFacturaCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFacturaCantidadKeyTyped
// TODO add your handling code here:
    //c.controlCaracteres(evt);
}//GEN-LAST:event_txtFacturaCantidadKeyTyped

private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
// TODO add your handling code here:
    txtNombre.setText((txtNombre.getText().toUpperCase()));
}//GEN-LAST:event_txtNombreKeyReleased

private void txtApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyReleased
// TODO add your handling code here:
    txtApellido.setText((txtApellido.getText().toUpperCase()));
}//GEN-LAST:event_txtApellidoKeyReleased

private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
// TODO add your handling code here:
    controlSoloLetras(evt);
}//GEN-LAST:event_txtNombreKeyTyped

private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
    controlSoloLetras(evt);
}//GEN-LAST:event_txtApellidoKeyTyped

private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
// TODO add your handling code here:
    controlDireccion(evt);
}//GEN-LAST:event_txtDireccionKeyTyped

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    limpiar();
//AQUI    numFc();
}//GEN-LAST:event_jButton2ActionPerformed

private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
// TODO add your handling code here:
//        try {
//                Map a=new HashMap();   
//                a.put("numero", Integer.parseInt(txtNumFac.getText()));
//                JasperReport reporte=JasperCompileManager.compileReport("C:/Users/USUARIO/Documents/NetBeansProjects/Facturacion/Facturacion/src/reportes/imprimir.jrxml");
//                JasperPrint  print=JasperFillManager.fillReport(reporte, a,cc.conectar());
//                JasperViewer.viewReport(print,false);
//        } catch (JRException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
}//GEN-LAST:event_btnImprimirActionPerformed

private void txtBuscarNombreProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarNombreProActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtBuscarNombreProActionPerformed

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
            java.util.logging.Logger.getLogger(factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new factura().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaMedicamentso;
    private javax.swing.JTable Tlista;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnRealizarCompra;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscarNombrePro;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFacturaCantidad;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtNombre;
    public static javax.swing.JTextField txtNumFac;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTotal;
    public static javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtsub;
    // End of variables declaration//GEN-END:variables
}
