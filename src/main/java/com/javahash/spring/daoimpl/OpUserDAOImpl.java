package com.javahash.spring.daoimpl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javahash.spring.Dao.OpUserDAO;

@Repository
@ComponentScan({"com.javahash.spring"})
public class OpUserDAOImpl implements OpUserDAO {

  private JdbcTemplate jdbcTemplate;

  
  @Autowired
  public OpUserDAOImpl(DataSource dataSouce) {
    this.jdbcTemplate = new JdbcTemplate(dataSouce);
  }

  @Override
  public String selectUserNameById(Integer id) {
    String userName = this.jdbcTemplate.queryForObject("select name as userName from s_user where id = ?", new Object[] {id}, String.class);
    System.out.println("search end");
    return userName;
  }

}
