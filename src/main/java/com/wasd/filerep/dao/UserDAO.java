package com.wasd.filerep.dao;

import com.wasd.filerep.entity.Section;
import com.wasd.filerep.entity.User;

import java.util.List;

public interface UserDAO {

    public List<User> findAll();

    public User findById(int index);

    public void save(User section);

    public void deleteById(int index);
}
