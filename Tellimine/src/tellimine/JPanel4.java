package tellimine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class JPanel4 extends JPanel{

    JPanel3 tabuuendus = new JPanel3();
    JPanel2 anduuendus = new JPanel2();
    
        public JPanel4() throws Exception{
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
                
                /*tabuuendus.revalidate();
                tabuuendus.repaint();
                tabuuendus.updateUI();*/
                                
                /*anduuendus.revalidate();
                anduuendus.repaint();
                anduuendus.updateUI();*/
                
                System.out.println("Uuenda");

            }
             
    }
        private class lisanupp implements ActionListener{
            
            public void actionPerformed(ActionEvent e){
                
                System.out.println("Lisa");
                
            }
             
    }
}
