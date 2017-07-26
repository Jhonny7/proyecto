/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.sharkit.loginweb;


import com.mx.sharkit.loginweb.hibernate.data.Usuario;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author jlopez
 */
public class Main {
    public static void main(String [] args){
		// Create a configuration instance
		Configuration configuration = new Configuration();
		// Provide configuration file
		configuration.configure("hibernate.cfg.xml");
		// Build a SessionFactory
		//SessionFactory factory = new Configuration().configure().buildSessionFactory();
                SessionFactory factory = new Configuration().
            configure("hibernate.cfg.xml").buildSessionFactory();
		// Get current session, current session is already associated with Thread
		Session session = factory.getCurrentSession();
		// Begin transaction, if you would like save your instances, your calling of save must be associated with a transaction
		session.getTransaction().begin();
		// Create employee
		Usuario emp = new Usuario();
		emp.setNombre("Peter Jousha2");
		emp.setApellidoPaterno("Peter2");
		emp.setApellidoMaterno("Jousha2");
		emp.setUsername("admin2");
		emp.setPassword("admin2");
		// Save
		session.save(emp);
		// Commit, calling of commit will cause save an instance of employee
		session.getTransaction().commit();
	}
}
