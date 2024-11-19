package edu.ufsj.dao;

public interface GenericDao<T> {

    boolean create(T data);

    boolean delete(Integer id);

}
