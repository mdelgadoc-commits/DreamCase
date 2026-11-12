
package juego;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;


public class Prota {
    public static int x=10, y=400;
    public void paint(Graphics g){
        ImageIcon puerta=new ImageIcon(getClass().getResource("../images/puerta.png"));
        g.drawImage(puerta.getImage(),800,250,200,400,null);
        ImageIcon personaje=new ImageIcon(getClass().getResource("../images/Personaje.png"));
        g.drawImage(personaje.getImage(),x,y,100,100,null);
    }
    public void KeyPressed(KeyEvent e){
        if(e.getKeyCode()==37){
            if(x>0){
                x=x-20;
            }
        }
        if(e.getKeyCode()==39){
            if(x<900){
                x=x+20;
            }
        }
        if(e.getKeyCode()==38){
            if(y>0){
                y=y-20;
            }
        }
        if(e.getKeyCode()==40){
            if(y<550){
                y=y+20;
            }
        }
        
    }
    public Ellipse2D getBoundsYo(){
            return new Ellipse2D.Double(x+10,y+30,50,50);
        }
    public boolean llegaFinal(){
            Rectangle cuadrado=new Rectangle(850,350,110,400);
            Area cuadradoArea=new Area(cuadrado);
            return cuadradoArea.contains(getBoundsYo().getBounds());
        }
}
