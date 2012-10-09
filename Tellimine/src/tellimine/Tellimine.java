package tellimine;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public class Tellimine  extends JFrame{

    public Tellimine(){
          super("Tellimine");       
    }
    
    public  void looFrame() throws Exception{
        
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setPreferredSize(new Dimension(800, 310));
       this.setLayout(new GridBagLayout());
       GridBagConstraints c = new GridBagConstraints();
       
        JPanel1 panel1 = new JPanel1();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,10,10);
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.add(panel1, c);

        JPanel2 panel2 = new JPanel2();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,0,0,0);
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 1;
        this.add(panel2, c);
        
        JPanel3 panel3 = new JPanel3();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 100;
        c.weightx = 1.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        this.add(panel3, c);
        
        JPanel4 panel4 = new JPanel4();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 3;
        this.add(panel4, c);
        
        this.setVisible(true);
        this.pack();
       
    
    }
    
    public static void main(String[] args) throws Exception{
        
        Class.forName("cubrid.jdbc.driver.CUBRIDDriver");
       
       final Tellimine raam = new Tellimine();
        EventQueue.invokeLater(new Runnable() {
            @Override
        public void run() {
                try {
                    raam.looFrame();
                } catch (Exception ex) {
                    Logger.getLogger(Tellimine.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
             });
    }
}