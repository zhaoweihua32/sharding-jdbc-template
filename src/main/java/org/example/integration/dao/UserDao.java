package org.example.integration.dao;


import org.example.integration.entity.User;

public interface UserDao  {

    void addOne(User user);

    User getOneById(long id);
}
