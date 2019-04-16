package cn.edu.qfnu.meta.context.exception;

/**
 * Nemo业务异常类
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/08/01
 */
public class MetaException extends RuntimeException {
    private int statusCode;

    public MetaException(int statusCode) {
        this.statusCode = statusCode;
    }

    public MetaException(Throwable cause, int statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
