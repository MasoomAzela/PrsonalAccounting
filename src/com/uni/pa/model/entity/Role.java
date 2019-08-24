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
    private List<PermissionParam> permissions;

    public Role() {
    }

    public Role(String name, String description, List<PermissionParam> permissions) {
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

    //@Column(name = "permissions")
    @Enumerated(EnumType.STRING)
    @ElementCollection
    public List<PermissionParam> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionParam> permissions) {
        this.permissions = permissions;
    }
}