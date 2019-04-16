package cn.edu.qfnu.meta.util;

import java.util.UUID;

/**
 * 特殊数据生成器
 *
 * @author 王振琦
 * createAt: 2018/08/02
 * updateAt: 2018/08/02
 */
public class Generator {

    public static String getObjectId() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
