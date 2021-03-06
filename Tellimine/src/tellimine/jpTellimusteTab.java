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


public class jpTellimusteTab extends JPanel{

   private static String[] tPais={"ID","ArtikliID","Kogus","Märkus","Tellimiskuupäev","Tellitud"};
   public static Object andmed[][]=null;
   public Connection conn;
   JPopupMenu popup;
   JMenuItem menuItem;
   JTable tabel;
   TabMudel dtm;
   Integer rowNumber;
   Integer suurus;
   jpTellijaYld paneel;
   Integer row;
   JScrollPane jsp;
   
    public jpTellimusteTab(jpTellijaYld panel2) throws Exception{
        super();
        paneel = panel2;
     panel3();
    }
    
    public static Connection connect1() { //Ühenduse loomine andmebaasiga.
        
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
        jsp=new JScrollPane(tabel);
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
                    //Teeb popupi nähtavaks ja märgib ära, kus kohas popup avaneb.
			Point p = Me.getPoint();
			rowNumber = tabel.rowAtPoint( p );
			ListSelectionModel model = tabel.getSelectionModel();
			model.setSelectionInterval( rowNumber, rowNumber );
                        //Parema hiire klahviga avaneb popup ja selekteerib rea.
                   }
            }
        });
        
    
        dtm.addTableModelListener(new TableModelListener() {

              public void tableChanged(TableModelEvent e) {
                try {
                    
                    row = e.getFirstRow();
                    int column = e.getColumn();
                    
                    if (column != -1){ //Uue rea lisamisel ei tehta if lauses olevat tegevust.
                        TableModel model = (TableModel)e.getSource();
                        Object value = model.getValueAt(row, column);
                        andmed[row][column] = value;
                        //Tabelis muudatusi tehes kirjutatakse objekt massiivis muudetud andmed üle.
                    }
                    
                    if (column == 1 || column == 2 || column == 3 || column == 4 || column == 5){ //Muudatusi tehakse ainult siis, kui muudetakse andmeid tulpades 1-5.
                        PreparedStatement pstmt2 = conn.prepareStatement("UPDATE tabelandmed SET mat_id=?, kogus=?, markus=?, tel_kuupaev=?, tellitud=? WHERE id=?");
                        pstmt2.setObject(1, andmed[row][1]);
                        pstmt2.setObject(2, andmed[row][2]);
                        pstmt2.setObject(3, andmed[row][3]);
                        pstmt2.setObject(4, andmed[row][4]);
                        pstmt2.setObject(5, andmed[row][5].toString());
                        pstmt2.setObject(6, andmed[row][0]);
                        pstmt2.executeUpdate();
                        //Tabelis tehtud muudatused salvestatakse andmebaasi.
                    }  
                }
                catch (SQLException ex) {
                    Logger.getLogger(jpTellimusteTab.class.getName()).log(Level.SEVERE, null, ex);
                }
              }
         });
        
   }    
    
        class PopupActionListener implements ActionListener {
            public void actionPerformed(ActionEvent actionEvent) {
                
                if(actionEvent.getActionCommand().equals("New Record")){ //Kui vajutada nuppu "New Record", tehakse tegevus.
                    try {
                         PreparedStatement pstmt1 = conn.prepareStatement("INSERT INTO tabelandmed"+ " (tel_id, mat_id, kogus, markus, tel_kuupaev, tellitud)"+ " VALUES (?,'','','','','true')");
                         pstmt1.setObject(1, paneel.tellimus.getText());
                         pstmt1.executeUpdate();
                         //Lisatakse andmebaasi uus rida.
                         tabel_andmed(); 
                         //Andmete uuendamine objekti massiivis - andmed.
                         Object [] uusRida={andmed[suurus][0],"",0,"","",true};
                         dtm.addRow(uusRida);
                         //Uue rea lisamine tabelisse.
                    } catch (SQLException ex) {
                         Logger.getLogger(jpTellimusteTab.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if(actionEvent.getActionCommand().equals("Delete Record")){ //Kui vajutada nuppu "Delete Record", tehakse tegevus.
                    try {
                        PreparedStatement pstmt1 = conn.prepareStatement("DELETE FROM tabelandmed WHERE id=?");
                        pstmt1.setObject(1, dtm.getValueAt(rowNumber,0));
                        pstmt1.executeUpdate();
                        //Kustutatakse ära rida andmebaasist.
                        dtm.removeRow(rowNumber);
                        //Rea kustutamine tabelist.
                    } catch (SQLException ex) {
                        Logger.getLogger(jpTellimusteTab.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        public void tabel_uuenda() throws SQLException{
            tabel_andmed();
            dtm.setDataVector(andmed,tPais);
            //Andmete uuendamine tabelis.
        }
        
        public void tabel_andmed() throws SQLException{

        ResultSet rs = null;
        int tulpi = 6;
        suurus=0;
        conn=connect1();
        
        
        PreparedStatement pstm = conn.prepareStatement("SELECT * FROM tabelandmed WHERE tel_id=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
        pstm.setObject(1, paneel.tellimus.getText());
        rs = pstm.executeQuery();
        //Otsitakse üles andmed tellimuse id järgi.
        
        while(rs.next()) {
            suurus++;
        }
        //Ridade kokkulugemine tel_id järgi.
        rs.close();
        
        andmed=new Object[suurus][tulpi];
        PreparedStatement pstmt = conn.prepareStatement("SELECT id,mat_id,kogus,markus,tel_kuupaev,tellitud FROM tabelandmed WHERE tel_id=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
        pstmt.setObject(1,paneel.tellimus.getText());
        ResultSet rs3 = pstmt.executeQuery();
        //Andmete välja otsimine tellimuse id järgi.
        for(int a=0;a<suurus;a++){ //Tsükkel, mille käigus lisatakse leitud andmed objekt massiivi.
                
                rs3.next();
                Integer id = rs3.getInt(1);
                String artikkel = rs3.getString(2);
                Integer kogus = rs3.getInt(3);
                String markus = rs3.getString(4);
                String kuupaev = rs3.getString(5);
                String cb = rs3.getString(6);
                
                    andmed[a][0]=id;
                    andmed[a][1]=artikkel;
                    andmed[a][2]=kogus;
                    andmed[a][3]=markus;
                    andmed[a][4]=kuupaev;
                    if(cb.equals("true")) {
                        andmed[a][5]=true;
                    }
                    else {
                        andmed[a][5]=false;
                    }
        } //for lõpp
        rs3.close();
        suurus-=1;
     }
 }