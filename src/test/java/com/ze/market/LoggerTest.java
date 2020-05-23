package com.ze.market;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: market
 * @description: This is a test of log
 * @author: Ze QIN
 * @create: 2020-05-19 23:37
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
//@Data
public class LoggerTest {

    //private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test() {
        /*logger.debug("debug...");
        logger.info("info...");
        logger.error("error...");*/

        String name = "ze";
        String password = "123";
        log.debug("debug...");
        //log.info("name: " + name + ", password: " + password);
        log.info("name: {}, password: {}", name, password);
        log.error("error...");
        log.warn("warn...");
    }
}
