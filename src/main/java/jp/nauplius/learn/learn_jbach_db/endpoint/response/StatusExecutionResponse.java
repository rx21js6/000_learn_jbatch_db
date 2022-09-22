package jp.nauplius.learn.learn_jbach_db.endpoint.response;

import java.util.List;

import org.apache.batchee.container.impl.JobExecutionImpl;
import org.apache.batchee.container.impl.StepExecutionImpl;

import lombok.Data;

@Data
public class StatusExecutionResponse {
    private long id;

    private JobExecutionImpl jobExecution;

    private List<StepExecutionImpl> stepExecutions;

    private String message;
}
