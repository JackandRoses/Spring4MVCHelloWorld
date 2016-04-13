package com.javahash.spring.po;

public class User {

  private Integer id;

  private String userName;

  private Integer userAge;

  private String userAdress;

  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * @return the userAge
   */
  public Integer getUserAge() {
    return userAge;
  }

  /**
   * @param userAge the userAge to set
   */
  public void setUserAge(Integer userAge) {
    this.userAge = userAge;
  }

  /**
   * @return the userAdress
   */
  public String getUserAdress() {
    return userAdress;
  }

  /**
   * @param userAdress the userAdress to set
   */
  public void setUserAdress(String userAdress) {
    this.userAdress = userAdress;
  }

}
