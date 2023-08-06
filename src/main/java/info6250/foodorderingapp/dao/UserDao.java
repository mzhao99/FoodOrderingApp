/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info6250.foodorderingapp.dao;

import info6250.foodorderingapp.pojo.Restaurant;
import info6250.foodorderingapp.pojo.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author manlingzhao
 */
@Component
public class UserDao extends Dao{
    public boolean checkLogin(String username, String password) {
        Query q = getSession().createQuery("FROM User WHERE username = :username AND password = :password", User.class);
        q.setParameter("username", username);
        q.setParameter("password", password);
        if (q.uniqueResult() == null)    return false;
        return true;
    }

    public void createUserLogin(User user) {
        begin();
        getSession().persist(user);
        commit();   
    }
    
    public User getUser(String username){
        Query q = getSession().createQuery("FROM User WHERE username = :username", User.class);
        q.setParameter("username", username);
        return (User)q.uniqueResult();
    }
    
    public boolean isUniqueUsername(String username){
        Query q = getSession().createQuery("FROM User WHERE username = :username", User.class);
        q.setParameter("username", username);
        if (q.uniqueResult() == null)    return true;
        return false;
    }
}
