package com.uni.pa.model.entity;

import com.uni.pa.utility.DigestUtil;
import com.uni.pa.utility.RandomUtil;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "authentication")
public class Authentication extends AbstractEntity {
    private int id;
    private User user;
    private String userName;
    private String password;
    private String salt;
  //  private Role role;

    private boolean activationMode;

    public Authentication() {
    }

    public Authentication(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Basic
    @Column(name = "name")
    public String
    getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /*@Basic
    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }*/

    @Basic
    @Column(name = "active")
    public boolean isActivationMode() {
        return activationMode;
    }

    public void setActivationMode(boolean activationMode) {
        this.activationMode = activationMode;
    }

    public void changePassword(String plainPassword) throws NoSuchAlgorithmException {
        this.salt = new RandomUtil().randomGenerator(10);
        String dirtyPassword = makeSaltPassword(plainPassword);
        this.password = new DigestUtil().digest(dirtyPassword);
    }

    public boolean validatePassword(String password) throws NoSuchAlgorithmException {
        boolean result = false;
        String dirtyPassword = makeSaltPassword(password);
        String hashedPassword = new DigestUtil().digest(dirtyPassword);
        if (hashedPassword.equals(this.password)) {
            result = true;
        }
        return result;
    }

    public String makeSaltPassword(String clearPassword) {
        return this.salt + clearPassword;
    }
}