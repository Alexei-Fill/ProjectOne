package com.SpEx7.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "PORTAL_USER")
public class PortalUser implements Serializable {
    private int id;
    private String login;
    private String password;
    private String token;

    @Id
    @Column(name = "USER_ID")
    @SequenceGenerator(name = "userGenerator", sequenceName = "USER_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userGenerator")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public PortalUser setId(int id) {
        this.id = id;
        return this;
    }

    @Column(name = "USER_LOGIN")
    @NotEmpty(message = "{err.empty}")
    public String getLogin() {
        return login;
    }

    public PortalUser setLogin(String login) {
        this.login = login;
        return this;
    }

    @Column(name = "USER_PASSWORD")
    @NotEmpty(message = "{err.empty}")
    public String getPassword() {
        return password;
    }

    public PortalUser setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column(name = "TOKEN")
    public String getToken() {
        return token;
    }

    public PortalUser setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String toString() {
        return "PortalUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortalUser that = (PortalUser) o;
        return id == that.id &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, token);
    }

    public PortalUser(int id, String login, String password, String token) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.token = token;
    }

    public PortalUser() {
    }
}
