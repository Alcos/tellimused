package tellimine;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class JPanel1 extends JPanel{
    
   public void panel1(){
       
       JPanel panel1 = new JPanel();
       panel1.setSize(700,50);
       panel1.setBorder(BorderFactory.createLineBorder(Color.black));
       JLabel label1 = new JLabel("Tellimine");
       add(panel1);
       panel1.add(label1);
   }
}
