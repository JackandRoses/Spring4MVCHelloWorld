package com.javahash.spring.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javahash.spring.Dao.OpUserDAO;
import com.javahash.spring.config.DaoConfig;

@Repository
@ComponentScan({ "com.javahash.spring" })
public class OpUserDAOImpl implements OpUserDAO {

	@Autowired(required = true)
	@Qualifier("myJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public String selectUserNameById(Integer id) {
		String userName = this.jdbcTemplate.queryForObject("select name as userName from s_user where id = ?", new Object[] { id }, String.class);
		System.out.println(userName + " using: " + DaoConfig.CustomerContextHolder.getType());
		return userName;
	}

}
