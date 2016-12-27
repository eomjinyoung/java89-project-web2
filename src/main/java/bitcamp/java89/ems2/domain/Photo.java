package bitcamp.java89.ems2.domain;

import java.io.Serializable;

public class Photo implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int no;
  protected String filePath;
  protected int ownerNo;
  
  public Photo() {}
  
  public Photo(String filePath) {
    this.filePath = filePath;
  }
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getFilePath() {
    return filePath;
  }
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
  public int getOwnerNo() {
    return ownerNo;
  }
  public void setOwnerNo(int ownerNo) {
    this.ownerNo = ownerNo;
  }
  
  
  
}
