package tellimine;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class jpTellijaYld extends JPanel{
    
    private Connection conn;
    Integer suurus;
    JLabel tellimus, tk_nr;
    JFormattedTextField tellija, kuupaev;
    Statement stmt = null;
    ResultSet rs = null;
    Integer rida;
    
    public jpTellijaYld() throws SQLException{
        super();
     panel2();
    }
    
    
        public static Connection connect() { // Ühenduse loomine andmebaasiga
        
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
            
        conn=connect();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
        rs = stmt.executeQuery("SELECT * FROM yldandmed");
        rs.next();
        tellimus = new JLabel();
        tk_nr = new JLabel();
        tellija = new JFormattedTextField();
        kuupaev = new JFormattedTextField();
        rida = 0;
        
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
         
         ActionListener actionListener = new ActionListener() { // Kliendi andmete muudatuste listener.
          
             public void actionPerformed(ActionEvent actionEvent) {
                try {
                        PreparedStatement pstmt = conn.prepareStatement("UPDATE yldandmed SET tellija=?, kuupaev=? WHERE tel_id=?");
                        pstmt.setObject(1, tellija.getText());
                        pstmt.setObject(2, kuupaev.getText());
                        pstmt.setObject(3, tellimus.getText());
                        pstmt.executeUpdate();
                        //Tellimuse id järgi muudetakse ära tellija nimi ja kuupäev.
                } catch (SQLException ex) {
                    Logger.getLogger(jpTellijaYld.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }; // Actionlisteneri lõpp.
            kuupaev.addActionListener(actionListener);
            tellija.addActionListener(actionListener);
    }
    
    public void yldandmed() throws SQLException{
              
        ArrayList al=new ArrayList();
        Paring sql=new Paring();
        Object [][] tulem=sql.SelectParing("SELECT * FROM yldandmed", al);
        suurus=tulem.length-1;
        
        //Andmebaasist andmete lugemine objekt massiivi.
        
        tellimus.setText(tulem[rida][0].toString());
        tellija.setText(tulem[rida][1].toString());
        kuupaev.setText(tulem[rida][2].toString());
        tk_nr.setText(tulem[rida][3].toString());
        
        //Andmete sisestamine tekstikastidesse.
    }   
}