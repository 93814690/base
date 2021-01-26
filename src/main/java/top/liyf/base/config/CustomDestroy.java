package top.liyf.base.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.liyf.base.util.ThreadPoolManager;

import javax.annotation.PreDestroy;

/**
 * @author liyf
 * Created in 2020-11-20
 */
@Component
@Slf4j
public class CustomDestroy {

    @PreDestroy
    public void destroy() {

        log.info("------------- custom closing ... ---------------");

        ThreadPoolManager.getInstance().shutdown();
        log.info("关闭线程池");

        log.info("------------- custom closed ---------------");
    }

}
