package com.springpracticesdemo.configuration.quartz;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Configuration class for setting up Quartz scheduler to execute the Job at specified intervals for each configured
 * trigger.
 *
 * <p>
 * The class defines and configures the JobDetail bean for the {@link QuartzJob}, which is responsible for some logic
 * Additionally, it creates a list of Cron Triggers, one for each configured tenant, based on the provided cron
 * expressions.
 */
@Configuration
public class QuartzConfig {

    private static final String QUARTZ_GROUP_NAME = "QuartzTriggerGroup";
    private static final String QUARTZ_JOB_NAME = "QuartzJob";
    private static final String QUARTZ_JOB_BEAN_NAME = "quartzJobDetail";
    private static final String QUARTZ_TRIGGER_NAME = "QuartzTrigger_";

    /**
     * Creates a JobDetail bean, which defines the job to be executed by Quartz.
     *
     * @return JobDetail bean configured for the AutoPlanJob.
     */
    @Bean(name = QUARTZ_JOB_BEAN_NAME)
    public JobDetail quartzJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(QuartzJob.class);
        factoryBean.setDurability(true);
        factoryBean.setGroup(QUARTZ_GROUP_NAME);
        factoryBean.setName(QUARTZ_JOB_NAME);
        factoryBean.setDescription("Some description");
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    /**
     * Creates a list of Cron Triggers, one for each configured tenant.
     *
     * @param autoPlanJobDetail
     *            The JobDetail bean for the AutoPlanJob.
     * @return A list of Cron Triggers, each associated with a specific tenant.
     * @throws ParseException
     *             If there's an issue parsing the cron expression.
     */
    @Bean
    public List<Trigger> cronTriggers(@Qualifier(QUARTZ_JOB_BEAN_NAME) JobDetail autoPlanJobDetail)
            throws ParseException {
        List<Trigger> triggers = new ArrayList<>();
        Map<String, String> tenantCronExpressionConfig = new HashMap<>();
        tenantCronExpressionConfig.put("PS_EHP", "0 * * * * ?");
        for (Map.Entry<String, String> entry : tenantCronExpressionConfig.entrySet()) {
            // Use the configured tenant to get the cron expression
            String configuredTenant = entry.getKey();
            String cronExpression = entry.getValue();

            if (cronExpression == null) {
                throw new IllegalArgumentException(
                        "Cron expression not defined for the configured tenant: " + configuredTenant);
            }

            CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
            factoryBean.setJobDetail(autoPlanJobDetail);
            factoryBean.setCronExpression(cronExpression);
            factoryBean.setGroup(QUARTZ_GROUP_NAME);
            factoryBean.setName(QUARTZ_TRIGGER_NAME + configuredTenant);
            factoryBean.setDescription(configuredTenant);
            factoryBean.setTimeZone(TimeZone.getTimeZone("UTC"));
            factoryBean.afterPropertiesSet();
            triggers.add(factoryBean.getObject());
        }

        return triggers;
    }
}