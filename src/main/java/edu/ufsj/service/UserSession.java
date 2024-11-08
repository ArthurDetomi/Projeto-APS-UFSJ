package edu.ufsj.service;

import edu.ufsj.model.Usuario;

public class UserSession {
    private static UserSession instance;
    private Usuario loggedUser;

    public UserSession() {

    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public Usuario getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Usuario loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void clearSession() {
        this.loggedUser = null;
    }
}
