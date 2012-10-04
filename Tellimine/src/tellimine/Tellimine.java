package tellimine;

import java.awt.BorderLayout;
import javax.swing.JFrame;


public class Tellimine  extends JFrame{

    public Tellimine(){
        
       super("Tellimine");
       super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       super.setSize(800, 300);
       super.setLayout(new BorderLayout());
       super.setVisible(true);
        
    }
    
    public static void main(String[] args) throws Exception{
        
        Tellimine frame = new Tellimine();
        JPanel1 panel = new JPanel1();
        panel.panel1();
        frame.add(panel);
        /*JPanel2 paneel = new JPanel2();
        paneel.panel2();
        frame.add(paneel);*/
    }
}
