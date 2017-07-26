/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.sharkit.loginweb.services;

import com.mx.sharkit.loginweb.hibernate.data.Usuario;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jlopez
 */
@Component
public class LoginService {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public boolean acceptLogin(String user, String password) {
        try {
            System.out.println("--------------------------->");
            Session session = sessionFactory.getCurrentSession();
            Query query = session.getNamedQuery("Usuario.control").setString("username", user).setString("password", password);
            return !query.list().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    
    @Transactional
    public Usuario returnUser(String user, String password) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.getNamedQuery("Usuario.control").setString("username", user).setString("password", password);
            Usuario u = null;
            List<Usuario> usuarios = query.list();
            if(!usuarios.isEmpty()){
                return usuarios.get(0);
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
