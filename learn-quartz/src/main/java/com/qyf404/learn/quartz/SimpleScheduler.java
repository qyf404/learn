package com.qyf404.learn.quartz;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.jobs.DirectoryScanJob;
import org.quartz.jobs.DirectoryScanListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;


public class SimpleScheduler implements DirectoryScanListener

{

    static Logger logger = LoggerFactory.getLogger(SimpleScheduler.class);

    public static void main(String[] args)

    {

        SimpleScheduler simple = new SimpleScheduler();

        try

        {

            // Create a Scheduler and schedule the Job

            Scheduler scheduler = simple.createScheduler();
            scheduler.getContext().put("abcd", simple);


            // Jobs can be scheduled after Scheduler is running

            scheduler.start();

            logger.info("Scheduler started at " + new Date());

            // Schedule the first Job

            simple.scheduleJob(scheduler, "ScanDirectory1", DirectoryScanJob.class, "/Users/yfqi/temp/a", 10);

            // Schedule the second Job

//            simple.scheduleJob(scheduler, "ScanDirectory2", DirectoryScanJob.class, "/Users/yfqi/temp/b", 15);

        } catch (SchedulerException ex)

        {

            logger.error("", ex);

        }

    }

    public Scheduler createScheduler() throws SchedulerException

    {//创建调度器

        return StdSchedulerFactory.getDefaultScheduler();

    }


    private void scheduleJob(Scheduler scheduler, String jobName, Class jobClass, String scanDir, int scanInterval) throws SchedulerException

    {


        // Create a JobDetail for the Job

        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, Scheduler.DEFAULT_GROUP).build();

        // Configure the directory to scan

        jobDetail.getJobDataMap().put("DIRECTORY_NAME", scanDir);
        jobDetail.getJobDataMap().put("DIRECTORY_SCAN_LISTENER_NAME", "abcd");

        // Trigger that repeats every "scanInterval" secs forever


        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(simpleSchedule().withIntervalInSeconds(scanInterval).repeatForever()).build();


        scheduler.scheduleJob(jobDetail, trigger);

    }

    public void filesUpdatedOrAdded(File[] files) {
        logger.info("into file listener.{}", Arrays.toString(files));
    }
}