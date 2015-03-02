package com.exchangerates.server.listeners;


import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

import java.util.Date;

public class RatesDownloadedListener implements JobListener {
    private static final String LISTENER_NAME = "RatesDownloadedListener";
    private Logger logger = Logger.getLogger(RatesDownloadedListener.class);

    @Override
    public String getName() {
        return LISTENER_NAME;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().toString();
        logger.info(new Date() + "  Job : " + jobName + " is going to start...");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException e) {
        String jobName = context.getJobDetail().getKey().toString();
        logger.info(new Date() + "  Job : " + jobName + " is finished...");

        if(e != null) {
            logger.info("Exception thrown by: " + jobName + " Exception: " + e.getMessage());
        }
    }
}
