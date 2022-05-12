package com.zhiyao.support.dto;

import lombok.Data;

/**
 * 传递连接信息
 *
 * @author Delonix
 */
@Data
public class ConnectInfoDto {

    /**
     * 连接匹配号
     */
    private String connectId;

    /**
     * 配对密码
     */
    private String password;

    public ConnectInfoDto() {
    }

    public ConnectInfoDto(String connectId, String password) {
        this.connectId = connectId;
        this.password = password;
    }
}
