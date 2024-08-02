/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Manage.dao;

import java.util.List;

/**
 *
 * @author thaohien
 */
abstract public class HomeDAO<Entity,keyType> {
    abstract public void insert(Entity entity);
    abstract public void update(Entity entity);
    abstract public void delete(keyType key);
    abstract public List<Entity> selectAll();
    abstract public Entity selectById(keyType key);
    abstract protected List<Entity> selectBySQL(String sql, Object...args);
}
