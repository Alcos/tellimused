package tellimine;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class JPanel2 extends JPanel{
    
    private Connection conn;
    Integer suurus;
    JLabel tellimus, tk_nr;
    JFormattedTextField tellija, kuupaev;
    Statement stmt = null;
    ResultSet rs = null;
    
    public JPanel2() throws SQLException{
        super();
     panel2();
    }
    
    
        public static Connection connect() {
        
        Connection conn = null;
        
        try{    
            conn = DriverManager.getConnection("jdbc:cubrid:localhost:33000:Access::","dba","qwerty");
        }
        catch ( Exception e ) {

           System.err.println("SQLException : " + e.getMessage());
        }
        return conn;
    }
        
    public void panel2() throws SQLException{
        
            JButton uuenda = new JButton(">");
            
            uuendanupp uuendus = new uuendanupp();
            uuenda.addActionListener(uuendus);
            
        conn=connect();
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM yldandmed");
        rs.next();
        tellimus = new JLabel();
        tk_nr = new JLabel();
        tellija = new JFormattedTextField();
        kuupaev = new JFormattedTextField();
        
        yldandmed();
        
        this.setLayout(new GridLayout(3,5));
        this.add(new JLabel("Tellimuse ID"));
        this.add(tellimus);
        this.add(new JLabel("Tellija:"));
        this.add(tellija);
        this.add(new JLabel(""));
        this.add(new JLabel("Sissekandekuupäev"));
        this.add(kuupaev);
        this.add(new JLabel("Töökäsu nr:"));
        this.add(tk_nr);
        this.add(new JLabel(""));
        this.add(new JLabel("Tellimise Detailid"));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(uuenda);
        
    }
    
    public void yldandmed() throws SQLException{
                
        tellimus.setText(rs.getString("tel_id"));
        System.out.println(rs.getString("tellija"));
        tellija.setText(rs.getString("tellija"));
        kuupaev.setText(rs.getString("kuupaev"));
        tk_nr.setText(rs.getString("tk_nr"));
    }
    
    
            private class uuendanupp implements ActionListener{
            
            public void actionPerformed(ActionEvent e){
                    try {
                        rs.next();
                        yldandmed();
                    } catch (SQLException ex) {
                        Logger.getLogger(JPanel2.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
             
    }
}