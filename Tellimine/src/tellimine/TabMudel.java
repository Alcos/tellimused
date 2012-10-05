package tellimine;

import javax.swing.table.DefaultTableModel;


public class TabMudel extends DefaultTableModel{
    
    @Override
    public boolean isCellEditable(int row, int col) {  
            
        if (col == 4) {  
                return true;  
            } 
            else {  
                return false;  
            }         
}
    
    private Class[] tTyyp=new Class[]{
        String.class,
        Integer.class,
        String.class,
        String.class,
        Boolean.class};
       
    
    public TabMudel (Object andmed[][], String[] tPais){
    
    super(andmed, tPais);
}
    @Override
    public Class getColumnClass (int col){
        return this.tTyyp[col];
    }
}