package org.example.integration.service;

import org.example.integration.dao.ConfigDao;
import org.example.integration.entity.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    @Autowired
    private ConfigDao configDao;

    public long addOne(Config config) {
        this.configDao.addOne(config);
        return config.getConfigId();
    }
}
