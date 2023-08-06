/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info6250.foodorderingapp.dao;

import static info6250.foodorderingapp.dao.Dao.getSession;
import info6250.foodorderingapp.pojo.Restaurant;
import java.util.List;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author manlingzhao
 */
@Component
public class RestaurantDao extends Dao{
    public boolean checkLogin(String username, String password) {
        Query q = getSession().createQuery("FROM Restaurant WHERE username = :username AND password = :password", Restaurant.class);
        q.setParameter("username", username);
        q.setParameter("password", password);
        if (q.uniqueResult() == null)    return false;
        return true;
    }

    public void createRestLogin(Restaurant res) {
        begin();
        getSession().persist(res);
        commit();   
    }
        
    public List<Restaurant> getAllRestaurants(){
        Query q = getSession().createQuery("FROM Restaurant", Restaurant.class);
        return q.list();
    }
    
    public Restaurant getRestaurant(String resId){
        Query q = getSession().createQuery("FROM Restaurant WHERE id = :id", Restaurant.class);
        q.setParameter("id", resId);
        return (Restaurant)q.uniqueResult();
    }
    
    public int getId(String username){
        Query q = getSession().createQuery("FROM Restaurant WHERE username = :username", Restaurant.class);
        q.setParameter("username", username);
        int id = ((Restaurant)q.uniqueResult()).getId();
        return id;
    }
    
    public String getName(String username){
        Query q = getSession().createQuery("FROM Restaurant WHERE username = :username", Restaurant.class);
        q.setParameter("username", username);
        String name = ((Restaurant)q.uniqueResult()).getName();
        return name;
    }
    
    public boolean isUniqueUsername(String username){
        Query q = getSession().createQuery("FROM Restaurant WHERE username = :username", Restaurant.class);
        q.setParameter("username", username);
        if (q.uniqueResult() == null)    return true;
        return false;
    }
}
