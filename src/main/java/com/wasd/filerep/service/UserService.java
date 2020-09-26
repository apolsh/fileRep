package com.wasd.filerep.service;

import com.wasd.filerep.entity.DocumentVersion;
import com.wasd.filerep.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findById(int index);

    public void save(User user);

    public void deleteById(int index);

}
