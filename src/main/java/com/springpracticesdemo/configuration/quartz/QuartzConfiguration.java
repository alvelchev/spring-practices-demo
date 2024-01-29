package com.springpracticesdemo.configuration.quartz;

import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

@Configuration
public class QuartzConfiguration {

    /**
     * Creates a SchedulerFactoryBean for configuring and starting the Quartz Scheduler.
     *
     * @param dataSource
     *            The data source for the Quartz Scheduler.
     * @param cronTriggers
     *            A list of Cron Triggers, one for each configured tenant.
     * @return SchedulerFactoryBean configured for the AutoPlanScheduler.
     */
    @Bean
    public SchedulerFactoryBean schedulerFactory(DataSource dataSource, List<Trigger> cronTriggers) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
        schedulerFactoryBean.setTriggers(cronTriggers.toArray(new Trigger[0]));
        schedulerFactoryBean.setJobFactory(jobFactory());

        return schedulerFactoryBean;
    }

    /**
     * Defines properties for Quartz configuration, such as thread pool settings and database-related properties.
     *
     * @return Properties containing Quartz configuration settings.
     */
    @Bean
    public Properties quartzProperties() {
        Properties properties = new Properties();
        properties.setProperty("org.quartz.jobStore.tablePrefix", "quartz_");
        properties.setProperty("org.quartz.scheduler.instanceId", "AUTO");
        properties.setProperty("org.quartz.jobStore.isClustered", "true");
        // here we set how much threads we want to have available for the scheduler
        properties.setProperty("org.quartz.threadPool.threadCount", "5");
        properties.setProperty("org.quartz.jobStore.misfireThreshold", "60000");
        properties.setProperty("org.quartz.scheduler.instanceName", "spring-practices-demo-scheduler");
        // this property is needed in case we want to have quartz table to our project
        properties.setProperty("org.quartz.jobStore.driverDelegateClass",
                "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate");
        properties.setProperty("org.quartz.plugin.shutdownHook.class",
                "org.quartz.plugins.management.ShutdownHookPlugin");

        return properties;
    }

    /**
     * Creates a SpringBeanJobFactory for managing job creation and Spring Bean injection.
     *
     * @return SpringBeanJobFactory for job creation and dependency injection.
     */
    @Bean
    public SpringBeanJobFactory jobFactory() {
        return new SpringBeanJobFactory();
    }
}
