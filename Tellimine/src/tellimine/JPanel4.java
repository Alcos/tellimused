package tellimine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class JPanel4 extends JPanel{

        public JPanel4(){
        super();
     panel4();
    }
        
        private void panel4(){
            
            JButton uuenda = new JButton("Uuenda");
            this.add(uuenda);
            uuendanupp uuendus = new uuendanupp();
            uuenda.addActionListener(uuendus);
            //this.add(new JButton("Uuenda"));
            JButton lisa = new JButton("Lisa Artikkel");
            this.add(lisa);
            lisanupp lisamine = new lisanupp();
            lisa.addActionListener(lisamine);
            //this.add(new JButton("Lisa Artikkel"));
            
        }
        
            
        
        private class uuendanupp implements ActionListener{
            
            public void actionPerformed(ActionEvent e){
                
                System.out.println("Uuenda");

            }
             
    }
        private class lisanupp implements ActionListener{
            
            public void actionPerformed(ActionEvent e){
                
                System.out.println("Lisa");
                
            }
             
    }
}
