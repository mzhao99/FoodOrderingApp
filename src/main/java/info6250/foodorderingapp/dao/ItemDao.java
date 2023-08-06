/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info6250.foodorderingapp.dao;

import static info6250.foodorderingapp.dao.Dao.getSession;
import info6250.foodorderingapp.pojo.Item;
import info6250.foodorderingapp.pojo.User;
import java.util.*;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author manlingzhao
 */
@Component
public class ItemDao extends Dao{
    public List<Item> getAllItems(String resId){
        Query q = getSession().createQuery("FROM Item WHERE resId = :resId", Item.class);
        q.setParameter("resId", resId);
        return q.list();
    }
    
    public Item getItem(String itemId){
        Query q = getSession().createQuery("FROM Item WHERE id = :id", Item.class);
        q.setParameter("id", itemId);
        return (Item)q.list().get(0);
    }
    
    public void addItem(Item item) {
        try {
            begin();
            getSession().persist(item);
            commit();
        } catch(HibernateException e) {
            rollback();
        }
    }
    
    public void deleteItem(String itemId){
        try {
            begin();
            Item item = new Item();
            item.setId(Integer.parseInt(itemId));
            getSession().delete(item);
            commit();
        } catch(HibernateException e) {
            rollback();
        }
    }
    
    public void updateItem(Item item){
        try {
            begin();
            getSession().update(item);
            commit();
        } catch(HibernateException e) {
            rollback();
        }
    }
}
