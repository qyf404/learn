package com.qyf404.learn.dubbo.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qyf404.learn.dubbo.api.HelloWorld;

public class Test {
	public static void main(String[] args) throws Exception {  
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(  
                new String[] { "applicationContext.xml" });  
        context.start();  
  
        HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld"); //  
        for(int i=0;i<100;i++){
        String str = helloWorld.getString();
        	
        System.out.println(str);
        }
        
        // System.out.println(demoService.hehe());  
        System.in.read();  
    }  
}
