package com.uni.pa.dto;

import com.uni.pa.assets.PermissionParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AddRoleDto extends BaseDto {

    @NotNull(message = "Role name is required.")
    @NotBlank(message = "Role name is required.")
    private String name;

    private String description;

    @NotNull(message = "Please select at least a permission.")
    private List<PermissionParam> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PermissionParam> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionParam> permissions) {
        this.permissions = permissions;
    }
}
