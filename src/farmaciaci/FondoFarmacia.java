/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmaciaci;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

/**
 *
 * @author Usuario
 */
public class FondoFarmacia implements Border{
    
    public BufferedImage back;
 
        public FondoFarmacia()
        {
            
        try {
//            URL imagePath = new URL(getClass().getResource("/Imagenes/Farmacia_SanaSana.jpg").toString());
             URL imagePath = new URL(getClass().getResource("/Imagenes/FYBECA-AZUL1.jpg").toString());
            back = ImageIO.read(imagePath);
        } catch (Exception ex) {            
        }
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawImage(back, (x + (width - back.getWidth())/2),(y + (height - back.getHeight())/2), null);
    }
 
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }
 
    public boolean isBorderOpaque() {
        return false;
    }
    
    
}
