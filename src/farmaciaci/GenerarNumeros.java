/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmaciaci;

/**
 *
 * @author Usuario
 */
public class GenerarNumeros {
    
    private int dato;
    private int cont=1;
    private String num="";
    public void generar(int dato){
        this.dato=dato;
        if((this.dato>=1000) ||(this.dato<10000) ){
            int can=cont+this.dato;
            num="ME"+can;
        }
        if((this.dato>=100) ||(this.dato<1000) ){
            int can=cont+this.dato;
            num="ME0"+can;
        }
        if((this.dato>=9) &&(this.dato<100) ){
            int can=cont+this.dato;
            num="ME00"+can;
        }
        if((this.dato>=1) ||(this.dato<9) ){
            int can=cont+this.dato;
            num="ME000"+can;
        }
        if((this.dato==0) ){
            int can=cont+this.dato;
            num="ME000"+can;
        }
 
    }
     
    public String serie(){
    return this.num;
    }
    
}
