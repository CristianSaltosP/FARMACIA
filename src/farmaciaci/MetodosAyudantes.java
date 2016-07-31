/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmaciaci;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class MetodosAyudantes {
    
    
    public boolean validadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;

        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
// Coeficientes de validación cédula
// El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            System.out.println("Una excepcion ocurrio en el proceso de validadcion");
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
            JOptionPane.showMessageDialog(null, "La Cédula ingresada es Incorrecta vuelva a escribirla");
        }
        return cedulaCorrecta;
    }
    public void Numeros(final JTextField a)
    {
       a.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                int limite=10;
                char B= evt.getKeyChar();
                if(!Character.isDigit(B))
                {
                evt.consume();
                }
                if(a.getText().length()==limite)
                {
                    
                  evt.setKeyChar((char)KeyEvent.VK_CLEAR);
                  //validadorDeCedula(txtAluCedula.getText());
                    
                }
            }
        });
    }
    
    public void Letras(JTextField a)
    {
       a.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                       char B= evt.getKeyChar();
                if(Character.isDigit(B))
                {
                
                evt.consume();
               }
                
                
                
            }
        });
    }
}
