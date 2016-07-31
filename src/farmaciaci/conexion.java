
package farmaciaci;

import java.sql.*;
import java.io.*;
import javax.swing.JOptionPane;

public class conexion {
    
    Connection connect = null;
    
      public Connection conectar(){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
       String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:XE";  //ESQUEMA
        
            connect=DriverManager.getConnection(BaseDeDatos, "FARMACIA_I_A_C","FARMACIA_I_A_C");

           //JOptionPane.showMessageDialog(null, "Felicitaciones" );////che);
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "Error"+ex);
        }
        return connect;
      } 
        
    
}
