package tellimine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class JPanel1 extends JPanel{
   //private 
    
    
    
  public JPanel1(){
      super();
  initKomp();
  }
    
   
   private void initKomp(){
     this.setLayout(new BorderLayout());
       this.setBorder(BorderFactory.createLineBorder(Color.black));
       Font f = new Font("Dialog", Font.PLAIN, 20);
       JLabel label1 = new JLabel("Tellimine");
       label1.setFont(f);
       this.add(label1,BorderLayout.SOUTH);

   }
}