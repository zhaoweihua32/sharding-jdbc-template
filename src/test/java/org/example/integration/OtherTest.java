package org.example.integration;

import lombok.extern.slf4j.Slf4j;
import org.example.integration.dao.OtherTableDao;
import org.example.integration.entity.OtherTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OtherTest {

    @Autowired
    private OtherTableDao otherTableDao;

    @Test
    public void insertOtherTable() {
        OtherTable table = new OtherTable();
        table.setId(199920198888L);
        table.setName("test");
        this.otherTableDao.addOne(table);
        log.info("其它表插入id为：{}", table.getId());
    }

    @Test
    public void selectOtherTable() {

        this.otherTableDao.getAll();
    }

}
