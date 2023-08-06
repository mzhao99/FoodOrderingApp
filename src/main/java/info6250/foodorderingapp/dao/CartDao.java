/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info6250.foodorderingapp.dao;

import static info6250.foodorderingapp.dao.Dao.getSession;
import info6250.foodorderingapp.pojo.Cart;
import info6250.foodorderingapp.pojo.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author manlingzhao
 */
@Component
public class CartDao extends Dao{
    public void addToCart(Cart cart){
        try {
            begin();
            getSession().persist(cart);
            commit();
        } catch(HibernateException e) {
            rollback();
        }
    }

    public Cart getOneCart(String cartId){
        Query q = getSession().createQuery("FROM Cart WHERE id = :id", Cart.class);
        q.setParameter("id", cartId);
        return (Cart)q.uniqueResult();
    }
    
    public Map<String, List<Cart>> getCart(String userId, RestaurantDao resdao){
        Map<String, List<Cart>> hashmap = new HashMap<>();  //key is the restaurant id
        
        Query q = getSession().createQuery("FROM Cart WHERE userId = :userId", Cart.class);
        q.setParameter("userId", userId);
        List<Cart> cart = q.list();
        
        for (Cart c : cart){
            String resName = resdao.getRestaurant(c.getResId()).getName();
            
            if (hashmap.containsKey(resName)){
                hashmap.get(resName).add(c);
            }else{
                List<Cart> new_lst = new ArrayList<>();
                new_lst.add(c);
                hashmap.put(resName, new_lst);
            }
        }
        return hashmap;
    }

//    public void deleteItemFromCart(String cartId){
//        try {
//            begin();
//            Cart cart = new Cart();
//            cart.setId(Integer.parseInt(cartId));
//            getSession().delete(cart);
//            commit();
//        } catch(HibernateException e) {
//            rollback();
//        }
//    }
    public void deleteItemFromCart(List<Cart> lst){
        try {
            begin();
            for (Cart c : lst){
                getSession().delete(c);
            }
            commit();
        } catch(HibernateException e) {
            rollback();
        }
    }
}
