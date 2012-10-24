package tellimine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;


public class JPanel4 extends JPanel{
    
    JPanel2 paneel = new JPanel2();
    
        public JPanel4() throws Exception{
        super();
     panel4();
    }
        
        private void panel4() throws SQLException{
                        
            JButton uuenda = new JButton("<");
            this.add(uuenda);
            uuendanupp uuendus = new uuendanupp();
            uuenda.addActionListener(uuendus);

            JButton lisa = new JButton(">");
            this.add(lisa);
            lisanupp lisamine = new lisanupp();
            lisa.addActionListener(lisamine);       
        }
        
            
        
        private class uuendanupp implements ActionListener{
            
            public void actionPerformed(ActionEvent e){
                          
                System.out.println("<");

            }
             
    }
        private class lisanupp implements ActionListener{
            
            public void actionPerformed(ActionEvent e){
                try {   
                    paneel.rs.next();
                    paneel.yldandmed();
                    System.out.println(">");    
                } catch (SQLException ex) {
                    Logger.getLogger(JPanel4.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }        
    }
}