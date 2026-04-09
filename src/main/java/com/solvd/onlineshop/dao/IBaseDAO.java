package com.solvd.onlineshop.dao;

public interface IBaseDAO<T> {
    T save (T entity);
    void update(T entity);
    T getById(Long id);
    void deleteById(Long id);
}