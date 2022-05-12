package com.zhiyao.support.utils;

import cn.hutool.core.util.RandomUtil;
import com.zhiyao.support.dto.ConnectInfoDto;

/**
 * 生成随机的连接ID和配对码
 *
 * @author Delonix
 */
public class ConnectUtils {

    private static final String BASE_CHAR = "abcdefghijkmnpqrstuvwxyz023456789";
    private static final int CONNECT_ID_LENGTH = 4;
    private static final int CONNECT_PASSWORD_LENGTH = 4;

    /**
     * 生成随机的连接ID和配对码
     *
     * @return 连接信息
     */
    public static ConnectInfoDto generateConnectInfo() {
        return new ConnectInfoDto(
                RandomUtil.randomString(BASE_CHAR, CONNECT_ID_LENGTH),
                RandomUtil.randomNumbers(CONNECT_PASSWORD_LENGTH)
        );
    }
}
