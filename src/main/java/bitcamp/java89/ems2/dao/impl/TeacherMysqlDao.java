package bitcamp.java89.ems2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.util.DataSource;

public class TeacherMysqlDao implements StudentDao {
  DataSource ds;
  
  //Singleton 패턴 - start
  private TeacherMysqlDao() {
    ds = DataSource.getInstance();
  }
 
  static TeacherMysqlDao instance;
 
  public static TeacherMysqlDao getInstance() {
    if (instance == null) {
      instance = new TeacherMysqlDao();
    }
    return instance;
  }
  // end - Singleton 패턴

  public boolean exist(int memberNo) throws Exception {
    Connection con = ds.getConnection(); 
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select count(*)"
          + " from tcher left outer join memb on tcher.tno=memb.mno"
          + " where tno=?"); ) {
      
      stmt.setInt(1, memberNo);
      ResultSet rs = stmt.executeQuery();
      
      rs.next();
      int count = rs.getInt(1);
      rs.close();
      
      if (count > 0) {
        return true;
      } else {
        return false;
      }
      
    } finally {
      ds.returnConnection(con);
    }
  } 
  
 
}
