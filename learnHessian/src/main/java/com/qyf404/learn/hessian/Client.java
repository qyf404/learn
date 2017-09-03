package com.qyf404.learn.hessian;

import java.net.MalformedURLException;

import com.ai.sh.dubboplatform.security.dubbo.sv.UserOpenDubboSv;
import com.caucho.hessian.client.HessianProxyFactory;


public class Client {
	public static void main(String[] args) throws MalformedURLException{  
		String url = "dubbo://10.1.48.135:20881/com.ai.sh.dubboplatform.security.dubbo.sv.UserOpenDubboSv";
//        String url = "http://localhost/HessianServer/hessian";  
        HessianProxyFactory factory = new HessianProxyFactory();  
        UserOpenDubboSv d = (UserOpenDubboSv) factory.create(UserOpenDubboSv.class, url);  
        
        d.getUserOpenVo("1");
    }  
}
