package tellimine;

import java.awt.BorderLayout;
import java.awt.Color;
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
        this.setLayout(new BorderLayout());
        //this.setBorder(BorderFactory.createLineBorder(Color.black));
        Boolean cb = true;
        Object rowData[][] = { { "New Record", "1", "-", "05.10.2012", cb},
                       { "Old Record", "1", "-", "05.10.2012", cb},
                       { "Old qweqweqwe", "1", "-", "05.10.2012", cb},
                { "ASD", "1", "-", "05.10.2012", cb},
                { "QWE", "1", "-", "05.10.2012", cb},
                { "ASFAS", "1", "-", "05.10.2012", cb},
                { "QWEQWR", "1", "-", "05.10.2012", cb},
                { "ASFQRQW", "1", "-", "05.10.2012", cb},
                { "ASDQWT", "1", "-", "05.10.2012", cb},
                { "QWEASD", "1", "-", "05.10.2012", cb},
                { "ASCWE", "1", "-", "05.10.2012", cb},
                { "Old aewqe", "1", "-", "05.10.2012", cb} };

        TabMudel dtm=new TabMudel(rowData,tPais);
        JTable tabel=new JTable(dtm);
        JScrollPane jsp=new JScrollPane(tabel);
        this.add(jsp,BorderLayout.CENTER);
        }    
}    