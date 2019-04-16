package cn.edu.qfnu.meta.context.exception;

import cn.edu.qfnu.meta.model.dto.Response;
import cn.edu.qfnu.meta.util.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Nemo业务异常处理器
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/08/01
 */
@RestControllerAdvice
public class MetaExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MetaExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Response<Object> handleException(Exception e) {
        logger.error("NemoExceptionHandler: ", e);
        return new Response<>(StatusCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(value = MetaException.class)
    public Response<Object> handleNemoException(MetaException e) {
        logger.error("NemoExceptionHandler: ", e);
        return new Response<>(e.getStatusCode());
    }
}
