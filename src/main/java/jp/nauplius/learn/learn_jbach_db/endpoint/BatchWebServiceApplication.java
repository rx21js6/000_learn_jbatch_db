/**
 *
 */
package jp.nauplius.learn.learn_jbach_db.endpoint;

import javax.inject.Named;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author developer
 *
 */
@ApplicationPath("rest")
@Named
public class BatchWebServiceApplication extends Application {
    public BatchWebServiceApplication() {
        System.out.println("BatchWebServiceApplication#constructor ****");
    }
}
