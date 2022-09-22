package jp.nauplius.learn.learn_jbach_db.job.dummy02;

import java.io.Serializable;

import javax.batch.api.Batchlet;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import jp.nauplius.learn.learn_jbach_db.constants.BatchConstants;
import jp.nauplius.learn.learn_jbach_db.service.DummyService;

@Named
public class Dummy02DbDeleteBatchlet implements Batchlet, Serializable {
    @Inject
    private transient Logger logger;

    @Inject
    private DummyService dummyService;

    @Override
    public String process() throws Exception {
        this.logger.info("process");
        int count = this.dummyService.addCount();
        this.logger.info("count: " + count);
        return BatchConstants.SUCCESS;
    }

    @Override
    public void stop() throws Exception {
        this.logger.info("stop");
    }
}
