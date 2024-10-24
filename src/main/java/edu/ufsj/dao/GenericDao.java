package edu.ufsj.dao;

public interface GenericDao<T> {

    boolean create(T data);
}
