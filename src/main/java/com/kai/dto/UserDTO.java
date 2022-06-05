package com.kai.dto;

import lombok.Data;

@Data
//保存非完整的User信息 安全
public class UserDTO {
    private Long id;
    private String nickName;
    private String icon;
}
