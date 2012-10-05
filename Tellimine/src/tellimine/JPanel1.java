package tellimine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class JPanel1 extends JPanel{
   //private 
    private JButton nupp;
    
    
    
  public JPanel1(){
      super();
  initKomp();
  }
    
   
   private void initKomp(){
     this.setPreferredSize(new Dimension(700,25));
     this.setLayout(new BorderLayout());
       this.setBorder(BorderFactory.createLineBorder(Color.black));
       Font f = new Font("Dialog", Font.PLAIN, 20);
       JLabel label1 = new JLabel("Tellimine");
       label1.setFont(f);
       this.add(label1,BorderLayout.SOUTH);
       //this.setVisible(true);
   }
}