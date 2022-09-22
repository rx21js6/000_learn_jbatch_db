package jp.nauplius.learn.learn_jbach_db.endpoint.response;

import lombok.Data;

@Data
public class BatchResponse {
    private long id;

    private String messeage;

    private Throwable error;
}
