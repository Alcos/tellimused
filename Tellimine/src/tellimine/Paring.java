/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tellimine;
import cubrid.jdbc.driver.*;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author reget.kalamees
 */
public class Paring {
    //TODO - muutmisvõimalus
       //private static String url      = "jdbc:h2:tcp://localhost/~/Documents/NetBeansProjects/baas/test";   //database specific url.
   // private static String url ="jdbc:h2:tcp://localhost/~/Documents/NetBeansProjects/baas/proovbaas";  
   // private static String url="jdbc:odbc:nipi"; //nipi on DSN!
    private static String url="jdbc:CUBRID:127.0.0.1:33000:Access:::?charset=utf-8";
    private static String user     = "dba";
    private static String pwds = "qwerty";
     private Object [][] resSet;
        
        
       // 
    public Paring(){
   /* user=Detail.ko.seaded.getProperty("abUser");
    pwds=Detail.ko.seaded.getProperty("abPwds");
    url=Detail.ko.seaded.getProperty("abUrl");*/
    
    }
    
    public Object[][] SelectParing(String ps, ArrayList param){
    ResultSet rs=null;
    Connection conn=null;
        try {//avatakse andmebaasi ühendus
           conn =DriverManager.getConnection(url, user, pwds);
           // conn =DriverManager.getConnection(url);
            //valmistatakse ette parameetritega (? märkidega lause)
          PreparedStatement pLause=conn.prepareStatement(ps,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
          //  PreparedStatement pLause=conn.prepareStatement(ps);
            //parameetrid loetakse listist, tehakse kindlaks nende
            //andmetüüp ja lisatakse ? märkide asemele
            for(int a=0;a<param.size();a++){
                //saadakse parameetri andmetüüp tekstina 
                String kNimi=param.get(a).getClass().toString();
                if(kNimi.compareTo("class java.lang.String")==0){ 
                    pLause.setString(a+1, param.get(a).toString());
                }
                if(kNimi.compareTo("class java.lang.Integer")==0){ 
                    Integer iArv=(Integer)param.get(a);
                    pLause.setInt(a+1, iArv.intValue());
                
                }
                if(kNimi.compareTo("class java.lang.Long")==0){ 
                    Long iArv=(Long)param.get(a);
                    pLause.setLong(a+1, iArv.longValue());
                }
                
                if(kNimi.compareTo("class java.sql.Timestamp")==0){ 
                    pLause.setTimestamp(a+1, (Timestamp)param.get(a));
                    
                }
                if(kNimi.compareTo("class [B")==0){ 
                    pLause.setBytes(a+1, (byte[])param.get(a));
                    
                }
                
                if(kNimi.compareTo("class java.lang.Double")==0){ 
                    Double dArv=(Double)param.get(a);
                    pLause.setDouble(a+1, dArv.doubleValue());
                
                }
                //byte[]
                
                
                //TODO osa andmetüüpe tegemata Timestamp ja varbinary ...
            }
            
            rs=pLause.executeQuery();
            
            ResultSetMetaData rsm=rs.getMetaData();
            int tulpi=rsm.getColumnCount();
            rs.last();
            int ridu=rs.getRow();
            this.resSet=new Object[ridu][tulpi];
            if(rs.first())
            { //keri algusesse tagasi
                for(int realoendur=0;realoendur<ridu;realoendur++)
                { 
                   
                    for(int tl=0;
                            tl<tulpi;
                            tl++){
                        this.resSet[realoendur][tl]=rs.getObject(tl+1);
                    } 
                    if(!rs.next()) break;
            
            
                } //objetimassiivi täitmine
            } //if rs.first 
            
                   
            
        } catch (SQLException ex) {
           //Logi.KirjutaLogi(ex.getMessage()); 
        }
        finally{
            try {
                //kui ühenduse objekt on loodud ja ühendus on lahti
                //pane see kinni
                if((conn!=null) && (!conn.isClosed())) conn.close();
            } catch (SQLException ex) {
             //Logi.KirjutaLogi("SQL viga 2 select päringus");
            }
        }
    
    
        
    return this.resSet;
    }
   
    
    
    public ArrayList ODBCParing(String ps){
    ArrayList tulemus=new ArrayList(); //dünaamiline massiiv
    ResultSet rs=null; //objekt kus hoitakse (SELECT) päringu tulemust
    Connection conn=null; //objekt mis teab kõike andmebaasi ühendusest
    Statement lause=null; 
        try {
            conn =DriverManager.getConnection(url); //ühendus püsti
            lause=conn.createStatement(); //lause object
            rs=lause.executeQuery(ps); //täidetakse päring
            
            ResultSetMetaData rsm=rs.getMetaData();
            int tulpi=rsm.getColumnCount(); //selleks et leida tulpade arv
            Object[] oMass=new Object[tulpi]; //üks rida 
            while(rs.next()){
                //täida rida
                for(int aa=0;aa<tulpi;aa++){
                  Object oAjutine=new Object();
                  oAjutine=rs.getObject(aa+1);
                  oMass[aa]=oAjutine;
               } //fori lõpp
                //if(!rs.next()) break;
                tulemus.add(oMass);
                oMass=new Object[tulpi]; //uus rida
                
            } //while lõpp
            
            System.out.println(oMass[0]);
            
            conn.close(); //ühendus kinni
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    
    return tulemus;
    }
    
    
    
    
    
    
    
    
    public int DMLParing(String ps, ArrayList param){
    int tulem=-1;
    CUBRIDConnection conn=null;
        try {
            conn=(CUBRIDConnection) DriverManager.getConnection(url,user,this.pwds);
            
            
            PreparedStatement pLause=conn.prepareStatement(ps);
            
            for(int a=0;a<param.size();a++){
                String kNimi=param.get(a).getClass().toString();
                if(kNimi.compareTo("class java.lang.String")==0){ 
                    pLause.setString(a+1, param.get(a).toString());
                }
                if(kNimi.compareTo("class java.lang.Integer")==0){ 
                    Integer iArv=(Integer)param.get(a);
                    pLause.setInt(a+1, iArv.intValue());
                
                }
                if(kNimi.compareTo("class java.lang.Long")==0){ 
                    Long iArv=(Long)param.get(a);
                    pLause.setLong(a+1, iArv.longValue());
                }
                if(kNimi.compareTo("class java.sql.Timestamp")==0){ 
                    pLause.setTimestamp(a+1, (Timestamp)param.get(a));
                    
                }
                if(kNimi.compareTo("class [B")==0){ 
                    pLause.setBytes(a+1, (byte[])param.get(a));
                    
                }
                if(kNimi.compareTo("class java.lang.Double")==0){ 
                    Double dArv=(Double)param.get(a);
                    pLause.setDouble(a+1, dArv.doubleValue());
                }
                
                
                //TODO osa andmetüüpe tegemata Timestamp ja varbinary ...
            }
            
            tulem=pLause.executeUpdate();
            conn.close();
            
        } catch (SQLException ex) {
           //Logi.KirjutaLogi("DML päring"+ex.getMessage()); 
        }
        finally{
            try {
                //kui ühenduse objekt on loodud ja ühendus on lahti
                //pane see kinni
                if((conn!=null) || (!conn.isClosed())) conn.close();
            } catch (SQLException ex) {
             System.out.println("SQL viga 2");
            }
        }
    
    
        
    return tulem;
    }
    
    public int teeTehing(ArrayList<String> laused, boolean koosCommitiga){
     CUBRIDConnection conn=null;
     try{
       conn=(CUBRIDConnection) DriverManager.getConnection(url,user,pwds);
       int lauseid=laused.size();
       //massiiv
       PreparedStatement psMas[]=new PreparedStatement[lauseid];
       //kui ei ole autocommitiga
       if(!koosCommitiga) conn.setAutoCommit(false);
       for(int a=0;a<lauseid;a++){
           psMas[a]=conn.prepareStatement(laused.get(a));
           psMas[a].execute();
       //kui võib ise commitida + autocommit
       if(koosCommitiga) conn.commit();
       }
       //kui oli tehingurežiim siis nüüd commit
       if(!koosCommitiga) conn.commit();
       for(int a=0;a<psMas.length;a++){
        if(psMas[a]!=null) psMas[a].close();
       }
     }
    catch(Exception e){
    //Logi.KirjutaLogi("SQL viga tehing "+e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                
            }
    }
    finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                
            }
    }
    
    
    return 0;
    }
    /**
     * See on mõeldud lisamise jaoks a.la lisa uus kirje ja anna selle id
     * 
     * @param laused
     * @return 
     */
     public Object[][] insSel(ArrayList<String> laused){
     CUBRIDConnection conn=null;
     ResultSet rs=null;
     try{
       conn=(CUBRIDConnection) DriverManager.getConnection(url,user,pwds);
       
           PreparedStatement psIns =conn.prepareStatement(laused.get(0));
           psIns.executeUpdate();
           
           
           PreparedStatement psSel =conn.prepareStatement(laused.get(1),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
           rs=psSel.executeQuery();
           ResultSetMetaData rsm=rs.getMetaData();
            int tulpi=rsm.getColumnCount();
            rs.last();
            int ridu=rs.getRow();
            this.resSet=new Object[ridu][tulpi];
            if(rs.first())
            { //keri algusesse tagasi
                for(int realoendur=0;realoendur<ridu;realoendur++)
                { 
                   
                    for(int tl=0;
                            tl<tulpi;
                            tl++){
                        this.resSet[realoendur][tl]=rs.getObject(tl+1);
                    } 
                    if(!rs.next()) break;
            
            
                } //objetimassiivi täitmine
            } //if rs.first
      
     }
    catch(Exception e){
    //Logi.KirjutaLogi("SQL viga insSel "+ e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                
            }
    }
    finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                
            }
    }
    
    
    return this.resSet;
    }   
   
     
     /**
      * Lisamise päring, mis tagastab viimati lisatud id
      * @param ps - insert lause
      * @param param insert lause parameetrid
      * @return  - kui viga siis -1, kui OK siis last_inserted_id
      */
     public long insSel2(String ps,ArrayList param){
     CUBRIDConnection conn=null;
     
     long id=-1;
     try{
       conn=(CUBRIDConnection) DriverManager.getConnection(url,user,pwds);
       PreparedStatement pLause=conn.prepareStatement(ps);
            
            for(int a=0;a<param.size();a++){
                String kNimi=param.get(a).getClass().toString();
                if(kNimi.compareTo("class java.lang.String")==0){ 
                    pLause.setString(a+1, param.get(a).toString());
                }
                if(kNimi.compareTo("class java.lang.Integer")==0){ 
                    Integer iArv=(Integer)param.get(a);
                    pLause.setInt(a+1, iArv.intValue());
                
                }
                if(kNimi.compareTo("class java.lang.Long")==0){ 
                    Long iArv=(Long)param.get(a);
                    pLause.setLong(a+1, iArv.longValue());
                }
                if(kNimi.compareTo("class java.sql.Timestamp")==0){ 
                    pLause.setTimestamp(a+1, (Timestamp)param.get(a));
                    
                }
                if(kNimi.compareTo("class [B")==0){ 
                    pLause.setBytes(a+1, (byte[])param.get(a));
                    
                }
                if(kNimi.compareTo("class java.lang.Double")==0){ 
                    Double dArv=(Double)param.get(a);
                    pLause.setDouble(a+1, dArv.doubleValue());
                }
                
                
                //TODO osa andmetüüpe tegemata Timestamp ja varbinary ...
            }
            
          int tulem=pLause.executeUpdate();
           if(tulem!=1) {
             return -1;
         }
           PreparedStatement psSel =conn.prepareStatement("SELECT LAST_INSERT_ID()");
           ResultSet rs=psSel.executeQuery();
           rs.next();
           id=rs.getLong(1);
           
           
     } //try
    catch(Exception e){
    //Logi.KirjutaLogi("SQL viga insSel "+ e.getMessage());
            try {
                conn.rollback();
                return id;
            } catch (SQLException ex) {
                
            }
    }
    finally {
            try {
                conn.setAutoCommit(true);
                return id;
            } catch (SQLException ex) {
                
            }
    }
    
    return id;
    
    }   
    
    
}
