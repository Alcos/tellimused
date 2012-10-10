package tellimine;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;


public class JPanel3 extends JPanel{
    
   private static String[] tPais={"ArtikliID","Kogus","Märkus","Tellimiskuupäev","Tellitud"};
   private static Object andmed[][]=null;
   JPopupMenu popup;
   JMenuItem menuItem;
   JTable tabel;
   
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
        String kogus = rs3.getString(1);
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
        
        TabMudel dtm=new TabMudel(andmed,tPais);
        tabel=new JTable(dtm);
        JScrollPane jsp=new JScrollPane(tabel);
        this.add(jsp,BorderLayout.CENTER);
        popup = new JPopupMenu();
        menuItem = new JMenuItem("New Record");
        popup.add(menuItem);
        menuItem = new JMenuItem("Delete Record");
        popup.add(menuItem);
        
        menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){}
  });
  
        tabel.addMouseListener(new MouseAdapter(){
        public void mouseReleased(MouseEvent Me){
        if(Me.isPopupTrigger()){
        popup.show(Me.getComponent(), Me.getX(), Me.getY());
                        
                        // get the coordinates of the mouse click
			Point p = Me.getPoint();
 
			// get the row index that contains that coordinate
			int rowNumber = tabel.rowAtPoint( p );
 
			// Get the ListSelectionModel of the JTable
			ListSelectionModel model = tabel.getSelectionModel();
 
			// set the selected interval of rows. Using the "rowNumber"
			// variable for the beginning and end selects only that one row.
			model.setSelectionInterval( rowNumber, rowNumber );
        }
        }
  });
        
   }  
}