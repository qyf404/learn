package com.qyf404.learn.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.Properties;

public class Velocity2Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Velocity2Test.class);

    @Test
    public void should_merge_content_from_file() {
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(p);

        VelocityContext context = new VelocityContext();

        context.put("tableName", "t_user");
        context.put("conditionName", "create_time");
        context.put("conditionValue", "2018-01-01");

        Template template = null;
        try {
            template = Velocity.getTemplate("sql.vm");
        } catch (ResourceNotFoundException rnfe) {
            // couldn't find the template
        } catch (ParseErrorException pee) {
            // syntax error: problem parsing the template
        } catch (MethodInvocationException mie) {
            // something invoked in the template
            // threw an exception
        } catch (Exception e) {
        }

        StringWriter sw = new StringWriter();

        template.merge(context, sw);

        LOGGER.info("Out string:{}", sw);
        Assert.assertEquals("select * from t_user where create_time = 2018-01-01;", sw.getBuffer().toString());
    }

    @Test
    public void should_merge_content_from_string() {
        Velocity.init();

        VelocityContext context = new VelocityContext();

        context.put("tableName", "t_user");
        context.put("conditionName", "create_time");
        context.put("conditionValue", "2018-01-01");

        String template = "select * from $tableName where $conditionName = $conditionValue;";
        StringWriter sw = new StringWriter();
        Velocity.evaluate(context, sw, "sql", template);

        LOGGER.info("Out string:{}", sw);
        Assert.assertEquals("select * from t_user where create_time = 2018-01-01;", sw.getBuffer().toString());
    }
}
