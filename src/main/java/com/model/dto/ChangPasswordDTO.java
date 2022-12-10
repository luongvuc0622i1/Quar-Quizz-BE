package com.model.dto;

import lombok.Data;

@Data
public class ChangPasswordDTO {
    private String oldPassword;
    private String newPassword;

    public ChangPasswordDTO(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
