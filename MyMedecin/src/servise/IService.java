/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servise;

import java.util.ArrayList;

/**
 *
 * @author badi9
 */
public interface IService<T> {
    
    void insert(T t);
    void delete(T t);
    void update(T t);
    ArrayList<T> getAll();
    T getById(int id);
    
}
