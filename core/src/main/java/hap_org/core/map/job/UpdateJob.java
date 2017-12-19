package hap_org.core.map.job;

import com.hand.hap.job.AbstractJob;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class UpdateJob extends AbstractJob {
    //在日志输出时可打印出日志信息所在类
    private final Logger logger = LoggerFactory.getLogger(UpdateJob.class);
    public UpdateJob(){}
    @Override
    public void safeExecute(JobExecutionContext jobExecutionContext) throws Exception {

       /* JobDetail detail = jobExecutionContext.getJobDetail();
        JobKey key = detail.getKey();
        TriggerKey triggerKey = jobExecutionContext.getTrigger().getKey();*/
        String msg = "############# Hello World! - . jobKey:" /*+ key*/ + ", triggerKey:"/* + triggerKey*/ + ", execTime:" + new Date();
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }

       /* try {
            Thread.sle ep(20000L);
        } catch (InterruptedException var7) {
            var7.printStackTrace();
        }*/

        logger.info("#############");
    }

    public boolean isRefireImmediatelyWhenException() {
        return false;
    }

}
