package com.javahash.spring.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAsp {
	@Pointcut("execution(* com.javahash.spring.daoimpl.OpUserDAOImpl.selectUserNameById(..))")
	public void performance(){
	}
	@Before("performance()")
	public void takeSeats(){
		System.out.println("search starting");
	}
	
	// @Before("performance()")
	// public void takeSeatsAgain(){
	// System.out.println("search starting");
	// }
	
	@AfterReturning("performance()")
	public void show(){
		System.out.println("searching result show");
	}
}
