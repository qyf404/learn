package com.qyf404.learn.dubbo.impl;

import com.qyf404.learn.dubbo.api.HelloWorld;

public class HelloWorldImpl implements HelloWorld {

	public String getString() {
		// TODO Auto-generated method stub
		System.out.println("server 20882");
		return "hi, my name is qyf404!";
	}

}
