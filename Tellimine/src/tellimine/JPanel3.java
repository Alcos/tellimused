package tellimine;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class JPanel3 extends JPanel{
    
   private static String[] tPais={"ArtikliID","Kogus","Märkus","Tellimiskuupäev","Tellitud"};
   private static Object andmed[][]=null;
   
    public JPanel3() throws Exception{
        super();
     panel3();
    }
    
    public static Connection connect1() {
        
        Connection conn = null;
        
        try{    
            conn = DriverManager.getConnection("jdbc:cubrid:localhost:33000:Access::","dba","qwerty");
        }
        catch ( Exception e ) {

           System.err.println("SQLException : " + e.getMessage());
        }
        return conn;
    }
    
    private void panel3() throws Exception{
        
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList nl = new ArrayList();
        String nimi = null;
        Integer suurus = null;
        int tulpi = 5;
        Boolean cb = true;
        Connection conn=connect1();
        
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM Andmed");
      
        while(rs.next()) {
          nimi = rs.getString("ID");
          nl.add(nimi);
        }
        rs.close();
        
        Object[] array = nl.toArray();
        suurus=array.length;
        andmed=new Object[suurus][tulpi];
        PreparedStatement pstmt = conn.prepareStatement("SELECT kogus,markus,kuupaev FROM Andmed WHERE ID=?");
        
        for(int a=0;a<suurus;a++){
    
        pstmt.setObject(1, array[a]);
        ResultSet rs3 = pstmt.executeQuery();

        rs3.next();
        Double kogus = rs3.getDouble(1);
        String markus = rs3.getString(2);
        String kuupaev = rs3.getString(3);
            andmed[a][0]=array[a];
            andmed[a][1]=kogus;
            andmed[a][2]=markus;
            andmed[a][3]=kuupaev;
            andmed[a][4]=cb;
           rs3.close();
        } //for lõpp
        
        
        this.setLayout(new BorderLayout());
        //this.setBorder(BorderFactory.createLineBorder(Color.black));
        
        TabMudel dtm=new TabMudel(andmed,tPais);
        JTable tabel=new JTable(dtm);
        JScrollPane jsp=new JScrollPane(tabel);
        this.add(jsp,BorderLayout.CENTER);
        }
}