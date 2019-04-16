package cn.edu.qfnu.meta.controller;

import cn.edu.qfnu.meta.context.exception.MetaException;
import cn.edu.qfnu.meta.model.domain.User;
import cn.edu.qfnu.meta.util.Constant;
import cn.edu.qfnu.meta.util.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 控制器基类
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/10/08
 */
public class MetaFacade {

    private final Logger logger = LoggerFactory.getLogger(MetaFacade.class);

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession session;

    HttpServletRequest getRequest() {
        return request;
    }

    HttpServletResponse getResponse() {
        return response;
    }

    HttpSession getSession() {
        return session;
    }

    void addCurrentUser(User user) {
        session.setAttribute(Constant.USER, user);
    }

    User getCurrentUser() {
        return (User) session.getAttribute(Constant.USER);
    }

    void removeCurrentUser() {
        session.removeAttribute(Constant.USER);
    }

    void bindingResultInspect(BindingResult result) {
        if (result.hasErrors()) {
            throw new MetaException(StatusCode.PARAM_ERROR);
        }
    }
}
