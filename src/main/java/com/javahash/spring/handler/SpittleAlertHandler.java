package com.javahash.spring.handler;

import org.springframework.stereotype.Component;

import com.javahash.spring.po.Spittle;

@Component(value = "spittleHandler")
public class SpittleAlertHandler {
	public void processSpittle(Spittle spittle) {
		System.out.println("I got" + spittle.getVolume());
	}
}
