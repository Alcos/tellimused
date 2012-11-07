package tellimine;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class jpNupud extends JPanel{
    
    jpTellijaYld paneel2;
    jpTellimusteTab paneel3;
    JFormattedTextField tel_id;
    
        public jpNupud(jpTellijaYld panel2, jpTellimusteTab panel3) throws Exception{
        super();
        paneel2 = panel2;
        paneel3 = panel3;
     panel4();
    }
        
        private void panel4() throws SQLException{
                      
            tel_id = new JFormattedTextField();
            tel_id.setPreferredSize(new Dimension(40, 27));
            tel_id.setText(paneel2.tellimus.getText());
            
            JButton tagasi = new JButton("<");
            this.add(tagasi);
            tagasinupp eelmine = new tagasinupp();
            tagasi.addActionListener(eelmine);
            
            this.add(tel_id);
            
            JButton edasi = new JButton(">");
            this.add(edasi);
            edasinupp jargmine = new edasinupp();
            edasi.addActionListener(jargmine); 
            
            JButton lisa = new JButton("Lisa Artikkel");
            this.add(lisa);
            lisaartikkel lisamine = new lisaartikkel();
            lisa.addActionListener(lisamine);
            
      ActionListener actionListener = new ActionListener() {
          
          public void actionPerformed(ActionEvent actionEvent) {
              try {
                    int arv = 0;
                    String str=null;
                    String id=tel_id.getText();
                    ArrayList al=new ArrayList();
                    Paring sql=new Paring();
                    Object [][] tulem=sql.SelectParing("SELECT * FROM yldandmed WHERE tel_id="+id, al);
                    
                    PreparedStatement pstmt = paneel3.conn.prepareStatement("SELECT * FROM yldandmed");
                    ResultSet rs = pstmt.executeQuery();
                    
                    int number = Integer.parseInt(id);
                    
                    while(rs.next()){
                        str = rs.getString("tel_id");
                        int row = Integer.parseInt(str);
                            if(row != number){
                                arv++;
                            }
                            if(row == number){
                                break;
                            }
                    }
                    int suurus = paneel2.suurus+1;
                    paneel2.rida=arv;
                    
                    if(number > suurus || number <= 0){
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "ID'd ei leitud!","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    
                    else{
                        paneel2.tellimus.setText(tulem[0][0].toString());
                        paneel2.tellija.setText(tulem[0][1].toString());
                        paneel2.kuupaev.setText(tulem[0][2].toString());
                        paneel2.tk_nr.setText(tulem[0][3].toString());
                    
                        paneel3.tabel_uuenda();
                    }
              } catch (Exception ex) {
                  Logger.getLogger(jpNupud.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
     };
            tel_id.addActionListener(actionListener);
        }
        
            
        
        private class tagasinupp implements ActionListener{
            
            public void actionPerformed(ActionEvent e){
                try {
                    paneel2.rida--;
                    
                    if(paneel2.rida < 0){
                        paneel2.rida=paneel2.suurus;
                        paneel2.yldandmed();
                        tel_id.setText(paneel2.tellimus.getText());
                        paneel3.tabel_uuenda();
                    }
                    else{
                        paneel2.yldandmed();
                        tel_id.setText(paneel2.tellimus.getText());
                        paneel3.tabel_uuenda();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(jpNupud.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
        private class edasinupp implements ActionListener{
            
            public void actionPerformed(ActionEvent e){
                try {
                    paneel2.rida++;
                    
                    if(paneel2.rida > paneel2.suurus){
                        paneel2.rida=0;
                        paneel2.yldandmed();
                        tel_id.setText(paneel2.tellimus.getText());
                        paneel3.tabel_uuenda();
                    }
                    else{
                        paneel2.yldandmed();
                        tel_id.setText(paneel2.tellimus.getText());
                        paneel3.tabel_uuenda();
                    }
                }
                catch (Exception ex) {
                    Logger.getLogger(jpNupud.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
        
        private class lisaartikkel implements ActionListener{
            
            public void actionPerformed(ActionEvent e){
                try {
                 PreparedStatement pstmt1 = paneel3.conn.prepareStatement("INSERT INTO tabelandmed"+ " (tel_id, mat_id, kogus, markus, tel_kuupaev, tellitud)"+ " VALUES (?,'','','','','true')");
                 pstmt1.setObject(1, paneel3.paneel.tellimus.getText());
                 pstmt1.executeUpdate();
                 paneel3.tabel_andmed();
                 Object [] uusRida={paneel3.andmed[paneel3.suurus][0],"",0,"","",true};
                 paneel3.dtm.addRow(uusRida);
                }
                catch (Exception ex) {
                    Logger.getLogger(jpNupud.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}