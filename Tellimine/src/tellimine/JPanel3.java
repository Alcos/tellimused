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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class JPanel3 extends JPanel{
    
   private static String[] tPais={"ID","ArtikliID","Kogus","Märkus","Tellimiskuupäev","Tellitud"};
   private static Object andmed[][]=null;
   private Connection conn;
   JPopupMenu popup;
   JMenuItem menuItem;
   JTable tabel;
   TabMudel dtm;
   Integer rowNumber;
   Integer suurus;
   
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
        
        tabel_andmed();
        
        this.setLayout(new BorderLayout());
        
        ActionListener actionListener = new PopupActionListener();
        dtm=new TabMudel(andmed,tPais);
        tabel=new JTable(dtm);
        JScrollPane jsp=new JScrollPane(tabel);
        this.add(jsp,BorderLayout.CENTER);
        popup = new JPopupMenu();
        menuItem = new JMenuItem("New Record");
        menuItem.addActionListener(actionListener);
        popup.add(menuItem);
        menuItem = new JMenuItem("Delete Record");
        menuItem.addActionListener(actionListener);
        popup.add(menuItem);
        
        
        menuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){}
        });

        tabel.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent Me){
                if(Me.isPopupTrigger()){
                    popup.show(Me.getComponent(), Me.getX(), Me.getY());
                       
			Point p = Me.getPoint();
			rowNumber = tabel.rowAtPoint( p );
			ListSelectionModel model = tabel.getSelectionModel();
			model.setSelectionInterval( rowNumber, rowNumber );
                        
                   }
            }
        });
        
    
        dtm.addTableModelListener(new TableModelListener() {

              public void tableChanged(TableModelEvent e) {
                try {
                    
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    
                    if (column != -1){
                        TableModel model = (TableModel)e.getSource();
                        Object value = model.getValueAt(row, column);
                        andmed[row][column] = value;
                    }
                    
                    if (column == 1 || column == 2 || column == 3 || column == 4){
                            PreparedStatement pstmt2 = conn.prepareStatement("UPDATE andmed SET artikkel=?, kogus=?, markus=?, kuupaev=? WHERE id=?");
                            pstmt2.setObject(1, andmed[row][1]);
                            pstmt2.setObject(2, andmed[row][2]);
                            pstmt2.setObject(3, andmed[row][3]);
                            pstmt2.setObject(4, andmed[row][4]);
                            pstmt2.setObject(5, andmed[row][0]);
                            pstmt2.executeUpdate();
                    }
                        
                } catch (SQLException ex) {
                    Logger.getLogger(JPanel3.class.getName()).log(Level.SEVERE, null, ex);
                }
              }
         });
        
   }    
    
        class PopupActionListener implements ActionListener {
            public void actionPerformed(ActionEvent actionEvent) {
                
                if(actionEvent.getActionCommand().equals("New Record")){
                    try {
                         System.out.println("New");
                         PreparedStatement pstmt1 = conn.prepareStatement("INSERT INTO andmed"+ " (artikkel, kogus, markus, kuupaev)"+ " VALUES ('','','','')");
                         pstmt1.executeUpdate();
                         tabel_andmed();
                         Object [] uusRida={andmed[suurus][0],"",0,"","",true};
                         dtm.addRow(uusRida);
                         //dtm.fireTableDataChanged();
                    } catch (SQLException ex) {
                         Logger.getLogger(JPanel3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if(actionEvent.getActionCommand().equals("Delete Record")){
                    try {
                        System.out.println("Delete");
                        PreparedStatement pstmt1 = conn.prepareStatement("DELETE FROM Andmed WHERE id=?");
                        pstmt1.setObject(1, dtm.getValueAt(rowNumber,0));
                        pstmt1.executeUpdate();
                        dtm.removeRow(rowNumber);
                        //dtm.fireTableDataChanged();
                    } catch (SQLException ex) {
                        Logger.getLogger(JPanel3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        
        private void tabel_andmed() throws SQLException{
            
        Statement stmt = null;
        ResultSet rs = null;
        int tulpi = 6;
        suurus=0;
        Boolean cb = true;
        conn=connect1();
        
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM Andmed");
      
        while(rs.next()) {
            suurus++;
        }
        rs.close();
        
        andmed=new Object[suurus][tulpi];
        PreparedStatement pstmt = conn.prepareStatement("SELECT id,artikkel,kogus,markus,kuupaev FROM Andmed");
        ResultSet rs3 = pstmt.executeQuery();
        
        for(int a=0;a<suurus;a++){
                
                rs3.next();
                Integer id = rs3.getInt(1);
                String artikkel = rs3.getString(2);
                String kogus = rs3.getString(3);
                String markus = rs3.getString(4);
                String kuupaev = rs3.getString(5);
                    andmed[a][0]=id;
                    andmed[a][1]=artikkel;
                    andmed[a][2]=kogus;
                    andmed[a][3]=markus;
                    andmed[a][4]=kuupaev;
                    andmed[a][5]=cb;
                
        } //for lõpp
        rs3.close();
        suurus-=1;
     }
 }