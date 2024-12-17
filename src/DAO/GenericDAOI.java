package DAO;

import Model.Employee;
import Model.Holiday;

import java.util.*;

public interface GenericDAOI<T>{


    List<T> findAll();
    void add(T E);
    void update(T E,int id);
    void delete(int id);

}