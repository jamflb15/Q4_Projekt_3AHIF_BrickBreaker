package db;

import data.UserScore;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import sql.UsersSQL;

/**
 *
 * @author Admin
 */
public class DBAccess {

    private DBAccess() {
    }

    public static DBAccess getInstance() {
        return AccessDBHolder.INSTANCE;
    }

    private static class AccessDBHolder {

        private static final DBAccess INSTANCE = new DBAccess();
    }

    /**
     * Instanz Methoden **
     */
    private final DBManager dbm = DBManager.getInstance();

    /**
     * Model ** Testdaten
     */
    public List<UserScore> createTestdata() throws Exception {
        return Arrays.asList(new UserScore(
                        "Benutzer1", 120
                ),
                new UserScore(
                        "Benutzer2", 130
                )
        );
    }

    public List<UserScore> insertTestdata(List<UserScore> liste) throws Exception {
        for (UserScore user : liste) {
            persistEntity(user);
        }
        return liste; //mit id != null
    }

    public List<UserScore> findAllEmployee() throws Exception {
        List<UserScore> liste = new ArrayList<>();
        Statement stmt = dbm.createStatement();
        ResultSet rs = stmt.executeQuery(UsersSQL.STMT_FIND_ALL.getSql());

        while (rs.next())// ist Datensatz vorhanden und setzt auf nächsten Datensatz
        {
            UserScore e = new UserScore(rs); //Entity mit Key-ID!!
            liste.add(e);
        }
        stmt.close();//Resource 
        return liste;
    }

    public void clear() throws Exception
    {
        Statement stmt = dbm.createStatement();
        int affectedRows = stmt.executeUpdate(" DELETE FROM Employee ");
        System.out.println("Clear affected Rows:" + affectedRows);
    }
    
    /* CRUD Operationen 
        C .. persist..Objekt (Entity) wird in der DB persistiert => Entity erhält einen ID-Key
        R .. load...Datensatz wird von DB gelesen und als Objekt (Entity) erstellt
        U .. update... aktueller Datensatz (PrimaryKey bleibt gleich) wird geändert
        D .. delete...aktueller Datensatz wird gelöscht => Entity(Objekt) IDKEy wird null
     */
    public void persistEntity(UserScore e) throws Exception {
        PreparedStatement pstmt = dbm.createPreparedStatement(UsersSQL.PSTMT_INSERT.getSql(),
                Statement.RETURN_GENERATED_KEYS);
        //Employee-Objekt als Datesnatz setzen
        pstmt.setString(1, e.getName()); //?-Parameter-Index startet bei 1
        pstmt.setString(2, e.getHighscore()+""); //?-Parameter-Index startet bei 1+
        //pstmt.setString(3, e.getId()+""); 
        
        

        int affectedRows = pstmt.executeUpdate();//Parameter in die DB übertragen
        System.out.println("persist-affectedRows:" + affectedRows);
        int keyId = dbm.readGeneratedKeys(pstmt);
        e.setId(keyId);// the one and only setter for Key-Id
        System.out.println("persist-keyId:" + keyId);
        pstmt.close();//Resource 

    }

    public UserScore loadEntity(int index) throws Exception {
        PreparedStatement pstmt = dbm.createPreparedStatement(UsersSQL.PSTMT_FIND_BY_ID.getSql()
        );
        pstmt.setInt(1, index);

        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            throw new Exception(" ResultSet is null or empty!");
        }
        //pstmt.close();//Resource 
        return new UserScore(rs);
    }
    
    /**
     * 
     * ** Unit Test *
     */
    public static void main(String[] args) {
        try {
            DBAccess adb = DBAccess.getInstance();
            UserScore e = new UserScore(
                    "Benutzer1", 120
            );
            System.out.println("e bevor persist:" + e.toString());
            adb.persistEntity(e);
            System.out.println("e danach persist:" + e.toString());
//            Employee clone = adb.loadEntity(16);
//            System.out.println("clone load:" + clone.toString());
            
            /*Massentests */
            //Fake EIngabe
//            UserScore clone = new UserScore (
//                   e.getName(),
//                   e.getHighscore(),
//            ); 
//            clone.setId(e.getId()); //Entity das persistiert ist, wird geclont un verändert
//            adb.updateEntity(clone);
//            UserScore cloneloaded = adb.loadEntity(e.getId());
//            System.out.println("update clone:" + cloneloaded);
//            List<Employee> liste = adb.insertTestdata(adb.createTestdata());
//            System.out.println("Testdaten persited:" + liste);
//            liste = adb.findAllEmployee();
//            System.out.println("Testdaten loaded:" + liste);
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
    }
}
