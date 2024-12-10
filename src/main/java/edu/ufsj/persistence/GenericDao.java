package edu.ufsj.persistence;

public interface GenericDao<T> {

    boolean create(T data);

    boolean delete(Integer id);

}
