package com.uni.pa.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AuthenticationRequestDto extends BaseDto {

    @NotNull(message = "Username is required.")
    @NotBlank(message = "Username is required.")
    private String username;

    @NotNull(message = "Password is required.")
    @NotBlank(message = "Password is required.")
    @Length(min = 4, message = "Password should be at least 4 characters")
    private String password;

    //region Getter and Setter
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
    //endregion
}
