package com.uni.pa.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegistrationDto extends BaseDto {

    @NotNull(message = "First name is required.")
    @NotBlank(message = "First name is required.")
    private String fName;

    @NotNull(message = "Last name is required.")
    @NotBlank(message = "Last name is required.")
    private String lName;

    @NotNull(message = "Family relationship is required.")
    @NotBlank(message = "Family relationship is required.")
    private String subject;

    @NotNull(message = "Please enter your credit. If there is no credit, please enter 0.")
    @NotBlank(message = "Please enter your credit. If there is no credit, please enter 0.")
    private long credit;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }
}
