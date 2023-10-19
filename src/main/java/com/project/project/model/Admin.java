package com.project.project.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;

@Entity
public class Admin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    
    @ManyToMany(fetch = FetchType.EAGER , cascade =  CascadeType.ALL)
    @JoinTable(name = "admin_roles",joinColumns = @JoinColumn(name = "admin_id",referencedColumnName = "admin_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id"))
    private List<Roles>roles;
    public Admin(){}
    public Admin(Long id, String firstname, String lastname, String username, String password, String image,
            List<Roles> roles) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.image = image;
        this.roles = roles;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
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
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public List<Roles> getRoles() {
        return roles;
    }
    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
    
}
