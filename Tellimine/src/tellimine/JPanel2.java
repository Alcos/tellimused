package tellimine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class JPanel2 extends JPanel{
    
    public JPanel2(){
        super();
     panel2();
    }
    
    private void panel2(){
          
        this.setLayout(new GridLayout(3,4));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(new JLabel("Tellimuse ID"));
        this.add(new JLabel("3005"));
        this.add(new JLabel("Tellija:"));
        this.add(new JFormattedTextField("Proov"));
        this.add(new JLabel("Sissekandekuupäev"));
        this.add(new JFormattedTextField(new Date()));
        this.add(new JLabel("Töökäsu nr."));
        this.add(new JFormattedTextField("1000"));
        this.add(new JLabel("Tellimise Detailid"));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
    }
    
}