package com.wangn.cachetimeout;

import com.wangn.cachetimeout.demo.UsingCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheTimeoutApplication.class)
public class CacheTimeoutApplicationTests {

    @Autowired
    private UsingCacheService usingCacheService;

    @Test
    public void contextLoads() throws InterruptedException {
        String wangnTimeStamp = usingCacheService.getTimeStamp("wangn");
        assertEquals(wangnTimeStamp, usingCacheService.getTimeStamp("wangn"));
        Thread.sleep(500);
        assertEquals(wangnTimeStamp, usingCacheService.getTimeStamp("wangn"));
        Thread.sleep(500);
        assertNotEquals(wangnTimeStamp, usingCacheService.getTimeStamp("wangn"));
    }

}
