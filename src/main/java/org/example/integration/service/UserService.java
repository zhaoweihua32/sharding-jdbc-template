package org.example.integration.service;

import groovy.util.logging.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.example.integration.dao.UserDao;
import org.example.integration.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDao userDao;

    public long addOne(User user) {
        this.userDao.addOne(user);
        return user.getUserId();
    }

    public User getOne(long id) {
        return userDao.getOneById(id);
    }

    /**
     * 测试跨库事务
     */
    @ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = Exception.class)
    public void testTransactional() {
        User user1 = new User(101, "张三", 12);
        this.userDao.addOne(user1);
        User user2 = new User(102, "李四", 13);
        // 主键冲突
        this.userDao.addOne(user2);
        this.userDao.addOne(user2);
    }
}
