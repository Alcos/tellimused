package tellimine;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class JPanel2 extends JPanel{
    
    public void panel2(){
        
        GridLayout grid = new GridLayout(4,6);
        JPanel content = new JPanel();
        content.setLayout(grid);
        content.setSize(700, 100);
        content.add(new JLabel("Tellimuse ID"));
        content.add(new JLabel("3005"));
        content.add(new JLabel("Tellija:"));
        content.add(new JLabel("Proov"));
    }
    
}
