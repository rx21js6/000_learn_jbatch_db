package jp.nauplius.learn.learn_jbach_db.service;

import java.io.Serializable;

import javax.inject.Named;

import org.apache.batchee.cdi.scope.JobScoped;

@Named
@JobScoped
public class DummyService implements Serializable {
    private int count;

    public DummyService() {
        this.count = 0;
    }

    public int addCount() {
        this.count += 1;
        return this.count;
    }
}
