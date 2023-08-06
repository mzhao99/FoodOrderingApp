/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info6250.foodorderingapp.dao;

import static info6250.foodorderingapp.dao.Dao.getSession;
import info6250.foodorderingapp.pojo.Order;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author manlingzhao
 */
@Component
public class OrderDao extends Dao{

    public void placeOrder(List<Order> lst){
        try {
            begin();
            for (Order o : lst){
                getSession().persist(o);
            }
            commit();
        } catch(HibernateException e) {
            rollback();
        }
    }
    
    public List<Order> getUserOrders(String un){
        Query q = getSession().createQuery("FROM Order WHERE userId = :userId", Order.class);
        q.setParameter("userId", un);
        return q.list();
    }
    
    public List<Order> getResOrders(String resId){
        Query q = getSession().createQuery("FROM Order WHERE resId = :resId", Order.class);
        q.setParameter("resId", resId);
        return q.list();
    }
    
    public void updateStatus(String orderId, String resId, String status){
        Query q = getSession().createQuery("FROM Order WHERE orderId = :orderId AND resId = :resId", Order.class);
        q.setParameter("orderId", orderId);
        q.setParameter("resId", resId);
        List<Order> orders = q.list();
        
        try {
            begin();
            for (Order o : orders){
                o.setOrderStatus(status);
                getSession().update(o);
            }
            commit();
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
