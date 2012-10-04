package tellimine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;


public class Tellimine  extends JFrame{

    public Tellimine(){
          super("Tellimine");
         
    }
    
    
    public  void looFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setPreferredSize(new Dimension(800, 300));
       this.setLayout(new BorderLayout());
       
        JPanel1 panel = new JPanel1();
        //panel.panel1();
        this.add(panel, BorderLayout.NORTH);
        //lisa teine paneel
        
        this.setVisible(true);
        this.pack();
       
    
    }
    
    public static void main(String[] args) throws Exception{
        
        
       
       final Tellimine raam = new Tellimine();
        EventQueue.invokeLater(new Runnable() {
            @Override
        public void run() {
                 raam.looFrame();
                 
                 }
             });
        
        
        /*JPanel2 paneel = new JPanel2();
        paneel.panel2();
        frame.add(paneel);*/
    }
}
