package com.springpageable.configuration.autoplan;

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
 * Configuration class for setting up Quartz scheduler to execute the AutoPlanJob at specified intervals for each
 * configured tenant.
 *
 * <p>
 * The class defines and configures the JobDetail bean for the {@link QuartzAutoPlanJob}, which is responsible for
 * retrieving all workshops for a given tenant with the auto plan toggle enabled and sending requests to Planning to
 * create auto plans. Additionally, it creates a list of Cron Triggers, one for each configured tenant, based on the
 * provided cron expressions.
 */
@Configuration
public class AutoPlanQuartzConfig {

    private static final String AUTO_PLAN_GROUP_NAME = "AutoPlanTriggerGroup";
    private static final String AUTO_PLAN_JOB_NAME = "AutoPlanJob";
    private static final String AUTO_PLAN_JOB_BEAN_NAME = "autoPlanJobDetail";
    private static final String AUTO_PLAN_TRIGGER_NAME = "AutoPlanTrigger_";


    public AutoPlanQuartzConfig() {

    }

    /**
     * Creates a JobDetail bean, which defines the job to be executed by Quartz.
     *
     * @return JobDetail bean configured for the AutoPlanJob.
     */
    @Bean(name = AUTO_PLAN_JOB_BEAN_NAME)
    public JobDetail autoPlanJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(QuartzAutoPlanJob.class);
        factoryBean.setDurability(true);
        factoryBean.setGroup(AUTO_PLAN_GROUP_NAME);
        factoryBean.setName(AUTO_PLAN_JOB_NAME);
        factoryBean.setDescription(
                "This job retrieves all workshops for given tenant with auto plan toggle enabled and send request to Planning to create autoplans");
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
    public List<Trigger> cronTriggers(@Qualifier(AUTO_PLAN_JOB_BEAN_NAME) JobDetail autoPlanJobDetail)
            throws ParseException {
        List<Trigger> triggers = new ArrayList<>();
        Map<String, String> tenantCronExpressionConfig = new HashMap<>();
        tenantCronExpressionConfig.put("PS_EHP", "0 * * * * ?");
        for (String configuredTenant : tenantCronExpressionConfig.keySet()) {
            // Use the configured tenant to get the cron expression
            String cronExpression = tenantCronExpressionConfig.get(configuredTenant);
            if (cronExpression == null) {
                throw new IllegalArgumentException(
                        "Cron expression not defined for the configured tenant: " + configuredTenant);
            }

            CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
            factoryBean.setJobDetail(autoPlanJobDetail);
            factoryBean.setCronExpression(cronExpression);
            factoryBean.setGroup(AUTO_PLAN_GROUP_NAME);
            factoryBean.setName(AUTO_PLAN_TRIGGER_NAME + configuredTenant);
            factoryBean.setDescription(configuredTenant);
            factoryBean.setTimeZone(TimeZone.getTimeZone("UTC"));
            factoryBean.afterPropertiesSet();
            triggers.add(factoryBean.getObject());
        }

        return triggers;
    }
}