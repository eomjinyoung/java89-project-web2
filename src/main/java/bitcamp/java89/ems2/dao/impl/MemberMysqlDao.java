package bitcamp.java89.ems2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.util.DataSource;

public class MemberMysqlDao implements StudentDao {
  DataSource ds;
  
  //Singleton 패턴 - start
  private MemberMysqlDao() {
    ds = DataSource.getInstance();
  }
 
  static MemberMysqlDao instance;
 
  public static MemberMysqlDao getInstance() {
    if (instance == null) {
      instance = new MemberMysqlDao();
    }
    return instance;
  }
  // end - Singleton 패턴

  public boolean exist(String email) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select count(*) from memb where email=?"); ) {
      
      stmt.setString(1, email);
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
  
  public void insert(Member member) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
      PreparedStatement stmt = con.prepareStatement(
          "insert into memb(email,pwd,name,tel) values(?,password(?),?,?)",
          Statement.RETURN_GENERATED_KEYS); ) {
      
      stmt.setString(1, member.getEmail());
      stmt.setString(2, member.getPassword());
      stmt.setString(3, member.getName());
      stmt.setString(4, member.getTel());
      stmt.executeUpdate();
      
      ResultSet keyRS = stmt.getGeneratedKeys();
      keyRS.next();
      member.setMemberNo(keyRS.getInt(1));
      keyRS.close();

    } finally {
      ds.returnConnection(con);
    }
  }  
}
