package jp.nauplius.learn.learn_jbach_db.endpoint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.StepExecution;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.batchee.container.impl.JobExecutionImpl;
import org.apache.batchee.container.impl.StepExecutionImpl;
import org.slf4j.Logger;

import jp.nauplius.learn.learn_jbach_db.endpoint.response.BatchResponse;
import jp.nauplius.learn.learn_jbach_db.endpoint.response.StatusExecutionResponse;

@Path("dummyBatchEndpoint")
@Named
@RequestScoped
public class BatchEndpoint implements Serializable {
    @Inject
    private transient Logger logger;

    private JobOperator jobOperatior;

    @PostConstruct
    private void init() {
        this.jobOperatior = BatchRuntime.getJobOperator();
        this.logger.info("BatchEndpoint#init(): " + this);
        this.logger.info("jobOperator: " + this.jobOperatior);
    }

    @Path("dummy01")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BatchResponse executeDummy01() {
        BatchResponse response = new BatchResponse();

        try {
            this.logger.info("dummy01");
            Properties properties = new Properties();
            long jobId = this.jobOperatior.start("dummy01-job", properties);
            response.setId(jobId);

            this.logger.info("jobId: " + jobId);
        } catch (Throwable e) {
            this.logger.warn("exception: " + e.getMessage());
            response.setError(e);
            response.setMesseage(e.getCause().getMessage());
            response.setId(-1);

            e.printStackTrace();
        }

        return response;
    }

    @GET
    @Path("execution")
    @Produces(MediaType.APPLICATION_JSON)
    public StatusExecutionResponse getExecution(final @QueryParam("id") long id) {
        StatusExecutionResponse response = new StatusExecutionResponse();
        response.setId(id);

        JobExecution jobExecution = this.jobOperatior.getJobExecution(id);

        if (jobExecution == null) {
            response.setMessage("Job ID: " + id + " jobExecution not found.");
        } else {
            response.setJobExecution((JobExecutionImpl) jobExecution);

            List<StepExecution> stepExecutions = this.jobOperatior.getStepExecutions(id);

            if (stepExecutions == null || stepExecutions.isEmpty()) {
                response.setMessage("Job ID: " + id + " stepExecution not found.");
            } else {
                List<StepExecutionImpl> stepExecutionResponses = new ArrayList<>();
                stepExecutions.forEach(execution -> stepExecutionResponses.add((StepExecutionImpl) execution));
                response.setStepExecutions(stepExecutionResponses);
            }
        }


        return response;
    }

}
