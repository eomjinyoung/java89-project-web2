package bitcamp.java89.ems2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.util.DataSource;

//@Repository("memberDao")
public class MemberMysqlDao  {
  @Autowired DataSource ds;
  
  

  public Member getOne(String email, String password) throws Exception {
    Connection con = ds.getConnection();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select mno, name, tel, email from memb where email=? and pwd=password(?)"); ) {
      
      stmt.setString(1, email);
      stmt.setString(2, password);
      ResultSet rs = stmt.executeQuery();
      
      if (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        Member member = new Member();
        member.setMemberNo(rs.getInt("mno"));
        member.setEmail(rs.getString("email"));
        member.setName(rs.getString("name"));
        member.setTel(rs.getString("tel"));
        rs.close();
        return member;
        
      } else {
        rs.close();
        return null;
      }
      
    } finally {
      ds.returnConnection(con);
    }
  }
  
 
  
  
  public void delete(int memberNo) throws Exception {
    Connection con = ds.getConnection(); 
    try (
      PreparedStatement stmt = con.prepareStatement(
          "delete from memb where mno=?"); ) {
      
      stmt.setInt(1, memberNo);
      
      stmt.executeUpdate();
      
    } finally {
      ds.returnConnection(con);
    }
  }
  
  
}
