
package data;

import java.sql.ResultSet;
import java.sql.SQLException;



public class UserScore {
   private Integer id; //für Primeary Key 
   //Instanz-Konstanten: jede Instanz eigene Werte
   //einmal setzen und dann immutable => DesignPattern
   private final String name;
   private final int highscore;

    public UserScore(String name, int highscore)
            throws Exception
    {
        this.name = name;
        this.highscore = highscore;
    }

    public UserScore(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.highscore = rs.getInt("highscore");
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHighscore() {
        return highscore;
    }


    @Override
    public String toString() {
        return "User{" + "id=" + id + 
                ", name=" + name + 
                ", highscore=" + highscore;
    }
   
}
