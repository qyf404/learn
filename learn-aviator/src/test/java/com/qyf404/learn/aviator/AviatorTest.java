package com.qyf404.learn.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Options;
import com.googlecode.aviator.spring.SringContextFunctionLoader;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;

public class AviatorTest {
    /**
     * 这个测试在3.3.0版本中会有异常抛出, 在4.2.0中能正常执行
     * 风控主要因为这个特性想升级的.
     */
    @Test
    public void should_execute_success_when_object_is_null() {
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("amount", null);
        AviatorEvaluator.setOption(Options.NIL_WHEN_PROPERTY_NOT_FOUND, true);
        boolean r = (Boolean) AviatorEvaluator.execute(
                "amount.eos > 18",
                env);
        assertFalse(r);
    }

    /**
     * 在4.2.0中, 针对数组增加了一些预设的方法
     * 风控主要因为这个特性想升级的.
     */
    @Test
    public void should_execute_success_with_default_fun() {
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("arr", Arrays.asList(1, 10, 2, 4));
        boolean r = (Boolean) AviatorEvaluator.execute(
                "seq.max(arr) > 18",
                env);
        assertFalse(r);
    }


    /**
     * 在4.2.0中,自定义方法可以是一个spring的bean了
     *
     * 这个特性感觉能用上.目前没需求.
     */
    @Test
    @Ignore
    public void should_execute_success_with_spring_bean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        SringContextFunctionLoader loader = new SringContextFunctionLoader(ctx);
        AviatorEvaluator.addFunctionLoader(loader);
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("amount", new HashMap<String, Integer>());
        boolean r = (Boolean) AviatorEvaluator.execute(
                "myfun(amount) > 18",
                env);
        assertFalse(r);
    }

}
