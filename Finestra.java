import java.awt.Canvas;
import java.awt.Color; /*importa il nome dei colore*/
import java.awt.image.BufferStrategy;
import java.awt.Graphics; /*importa il disegnare le cose su una finestra*/
import java.awt.image.BufferStrategy;

import javax.swing.JFrame; /*importa la finestra*/




public class Finestra extends JFrame
{
    private Canvas canvas = new Canvas();



    public Finestra()
    {
        /*spegni il gioco quando si preme sulla x*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
        /*metto la finestra a schermo intero*/
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        /*centra la scheda*/
        //setLocationRelativeTo(null);
        /*aggiunge le componenti grafiche */
        add(canvas);
        /*rendo la finestra visibile*/
        setVisible(true);

        /*settiamo il numero di buffer */
        canvas.createBufferStrategy(3);
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
      
        int i=0;
        int x = 0;
        int y=0;
        while(true){
            i=i+1;
            x=x+10;
            y=y+100;
            if(i >= getWidth()){
                i=0;
            }
            if(x>=getWidth()){x=0;}
            if(y>=getWidth()){y=0;}
           
            
            bufferStrategy = canvas.getBufferStrategy();
            Graphics graphics = bufferStrategy.getDrawGraphics();
           
            //questa funzione disegna a schermo quello che gli diciamo di disegnare
            //prima si sceglie il colore poi si fa il disegno
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, getWidth(), getHeight());
            graphics.setColor(Color.GRAY);
            graphics.fillRect(0, 0, getWidth()/3, getHeight()/3);
            graphics.fillRect((getWidth()/3)*2, 0, getWidth()/3, getHeight()/3);
            graphics.setColor(Color.lightGray);
            graphics.fillRect(0,getHeight()/3, getWidth(), (getHeight()/3)*2);
            graphics.setColor(Color.red);
            //dico di riempire un triangolo che parte da in alto a sx grande quanto tutta la finestra
            graphics.fillOval(i,200, 50, 100);
             graphics.fillOval(x,400, 50, 100);
             graphics.fillOval(y,500, 50, 100);
          
            graphics.dispose();
            bufferStrategy.show();
            
            
        }

     


        
    }

    /*public void paint(Graphics graphics)
    {
        //questa funzione disegna a schermo quello che gli diciamo di disegnare
        //prima si sceglie il colore poi si fa il disegno
        graphics.setColor(Color.GRAY);
        //dico di riempire un triangolo che parte da in alto a sx grande quanto tutta la finestra
        graphics.fillRect(0, 0,width, height);
    }*/

    public static void main(String[] args)
    {
        new Finestra();
        
    }
}
