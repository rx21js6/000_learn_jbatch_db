package jp.nauplius.learn.learn_jbach_db.job.dummy01;

import javax.batch.api.Decider;
import javax.batch.runtime.StepExecution;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import jp.nauplius.learn.learn_jbach_db.constants.BatchConstants;

@Named
public class Dummy01Decider implements Decider {
    @Inject
    private transient Logger logger;

    @Override
    public String decide(StepExecution[] executions) throws Exception {
        this.logger.info("decide");
        return BatchConstants.SUCCESS;
    }

}
