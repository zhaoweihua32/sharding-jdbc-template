package org.example.integration;

import lombok.extern.slf4j.Slf4j;
import org.example.integration.dao.ConfigDao;
import org.example.integration.entity.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ConfigTest {
    @Autowired
    private ConfigDao configDao;

    @Test
    public void insertDictionary() {
        Config config = new Config();
        config.setParaName("key");
        config.setParaValue("value");
        this.configDao.addOne(config);
        log.info("字典表插入id为：{}", config.getConfigId());
    }

}

