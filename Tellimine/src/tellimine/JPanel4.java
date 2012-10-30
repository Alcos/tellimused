package tellimine;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;


public class JPanel4 extends JPanel{
    
    JPanel2 paneel;
    JPanel3 paneel3;
    JFormattedTextField tel_id;
    
        public JPanel4(JPanel2 panel2, JPanel3 panel3) throws Exception{
        super();
        paneel = panel2;
        paneel3 = panel3;
     panel4();
    }
        
        private void panel4() throws SQLException{    
                      
            tel_id = new JFormattedTextField();
            tel_id.setPreferredSize( new Dimension( 40, 27 ) );
            tel_id.setText(paneel.rs.getString("tel_id"));
            
            JButton uuenda = new JButton("<");
            this.add(uuenda);
            uuendanupp uuendus = new uuendanupp();
            uuenda.addActionListener(uuendus);
            this.add(tel_id);
            JButton lisa = new JButton(">");
            this.add(lisa);
            lisanupp lisamine = new lisanupp();
            lisa.addActionListener(lisamine);       
        }
        
            
        
        private class uuendanupp implements ActionListener{
            
            public void actionPerformed(ActionEvent e){
                try {
                    paneel.rs.previous();
                    paneel.yldandmed();
                    tel_id.setText(paneel.rs.getString("tel_id"));
                    paneel3.tabel_andmed();
                } catch (SQLException ex) {
                    Logger.getLogger(JPanel4.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             
    }
        private class lisanupp implements ActionListener{
            
            public void actionPerformed(ActionEvent e){
                try {   
                    paneel.rs.next();
                    paneel.yldandmed();
                    tel_id.setText(paneel.rs.getString("tel_id"));
                    paneel3.tabel_andmed();  
                } catch (SQLException ex) {
                    Logger.getLogger(JPanel4.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }        
    }
}