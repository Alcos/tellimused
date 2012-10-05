package tellimine;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class JPanel3 extends JPanel{
    
   private static String[] tPais={"ArtikliID","Kogus","Märkus","Tellimiskuupäev","Tellitud"};
   private static Object andmed[][]=null;
   
    public JPanel3(){
        super();
     panel3();
    }
    
    private void panel3(){
        this.setPreferredSize(new Dimension(800,150));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        Boolean cb = true;
        Object rowData[][] = { { "New Record", "1", "-", "05.10.2012", cb},
                       { "Old Record", "1", "-", "05.10.2012", cb} };

        TabMudel dtm=new TabMudel(rowData,tPais);
        JTable tabel=new JTable(dtm);
        JScrollPane jsp=new JScrollPane(tabel);
        add(jsp);
        
        }
}    