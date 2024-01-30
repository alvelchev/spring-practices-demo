package com.springpracticesdemo.configuration.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class QuartzJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(QuartzJob.class);

    @Override
    public void execute(JobExecutionContext context) {
        logger.info("Trigger with name {} and description {} is executing at {}",
                context.getTrigger().getKey().getName(),
                context.getTrigger().getDescription(), LocalDateTime.now());
    }
}
