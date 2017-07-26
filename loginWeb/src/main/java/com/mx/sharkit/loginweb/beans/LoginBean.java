/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.sharkit.loginweb.beans;

import com.mx.sharkit.loginweb.hibernate.data.Usuario;
import com.mx.sharkit.loginweb.services.LoginService;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jlopez
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

    private String username;
    private String password;
    private Usuario u;

    @ManagedProperty("#{loginService}")
    private LoginService loginService;

    public String loginControl() {
        System.out.println("En loginControl");
        System.out.println("--> "+username);
        System.out.println("--> "+password);
        if (loginService.acceptLogin(username, password)) {
            u = loginService.returnUser(username, password);
            RequestContext.getCurrentInstance().update("growl");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", u.getNombre()));
            return "home.xhtml?faces-redirect=true";
        }
        RequestContext.getCurrentInstance().update("growl");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error de acceso"));
        return "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
