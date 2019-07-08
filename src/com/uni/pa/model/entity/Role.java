package com.uni.pa.model.entity;

import com.uni.pa.assets.PermissionParam;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role extends AbstractEntity {
    private int id;
    private String name;
    private String description;
    private String [] permissions;

    public Role() {
    }

    public Role(String name, String description, String [] permissions) {
        this.name = name;
        this.description = description;
        this.permissions = permissions;
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
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String roleName) {
        this.name = roleName;
    }
    @Basic
    @Column(name = "description")
    @Lob
    public String getDescription() {
        return description;
    }

    public void setDescription(String roleDescription) {
        this.description = roleDescription;
    }

    @Basic
    @Column(name = "permission")
    //@Enumerated(EnumType.STRING)
   // @ElementCollection
    public String [] getPermissions() {
        return permissions;
    }

    public void setPermissions(String [] permissions) {
        this.permissions = permissions;
    }
}