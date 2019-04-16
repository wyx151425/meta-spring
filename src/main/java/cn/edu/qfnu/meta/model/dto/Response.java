package cn.edu.qfnu.meta.model.dto;

import cn.edu.qfnu.meta.util.StatusCode;

/**
 * Nemo后端响应对象
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/08/02
 */
public class Response<T> {
    /**
     * 响应状态码
     */
    private int statusCode;
    /**
     * 响应数据
     */
    private T data;

    public Response() {
        this.statusCode = StatusCode.SUCCESS;
        this.data = null;
    }

    public Response(int statusCode) {
        this.statusCode = statusCode;
    }

    public Response(T data) {
        this.statusCode = StatusCode.SUCCESS;
        this.data = data;
    }

    public Response(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public T getData() {
        return data;
    }
}
