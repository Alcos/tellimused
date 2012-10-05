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
       
        JPanel1 panel1 = new JPanel1();
        this.add(panel1, BorderLayout.NORTH);

        JPanel2 panel2 = new JPanel2();
        this.add(panel2, BorderLayout.CENTER);
        
        JPanel3 panel3 = new JPanel3();
        this.add(panel3, BorderLayout.SOUTH);
        
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