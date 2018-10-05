package com.creeps.tokenstore.tokens;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.security.MessageDigest;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Long consumer;
    private Long user;

    /**
     * use bit shifting to know whether or not the user has access to this bit
     * 0 - photo
     * 1 - name
     * 2 - address
     * 3 - proof of id docs
     * 4 - proof of address docs
     */
    private Integer permissions;


    private String token;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConsumer() {
        return consumer;
    }

    public void setConsumer(Long consumer) {
        this.consumer = consumer;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

    public String getToken() {
        return token;
    }

    public void setToken() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String a = this.id+""+this.user+""+System.currentTimeMillis();
            this.token = DigestUtils.sha256Hex(a);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
