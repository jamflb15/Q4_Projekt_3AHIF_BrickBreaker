
package sql;


public enum UsersSQL {
   STMT_FIND_ALL("SELECT * from Users"),
   PSTMT_FIND_BY_CITY("SELECT * from Users WHERE city=?"),
   STMT_DELETE("DELETE from Users"),
   PSTMT_DELETE_BY_ID("DELETE from Users WHERE id=?"),
   PSTMT_INSERT( " INSERT INTO Users ( " 
        + " User, Highscore) "
        + " VALUES (?, ?) " ),
   PSTMT_FIND_BY_ID(" SELECT * FROM Users WHERE id = ? ");

   private String sql;

    private UsersSQL(String sql ) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
   
   
}
