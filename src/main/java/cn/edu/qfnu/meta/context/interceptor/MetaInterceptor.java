package cn.edu.qfnu.meta.context.interceptor;

import cn.edu.qfnu.meta.model.domain.User;
import cn.edu.qfnu.meta.model.dto.Response;
import cn.edu.qfnu.meta.util.Constant;
import cn.edu.qfnu.meta.util.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Nemo请求拦截器
 *
 * @author 王振琦
 * createAt: 2018/08/03
 * updateAt: 2018/08/03
 */
public class MetaInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MetaInterceptor.class);

    private static final String API_USER_REGISTER = "%s/api/users/register";
    private static final String API_TEACHER_REGISTER = "%s/api/teachers/register";
    private static final String API_USER_LOGIN = "%s/api/users/login";
    private static final String API_INDEX_AUTHOR = "%s/api/authors/index";
    private static final String API_BOOK_LIST = "%s/api/books";
    private static final String API_AUTHOR = "%s/api/authors";
    private static final String API_BOOK = "%s/api/books";
    private static final String API_COURSE = "%s/api/courses";
    private static final String PAGE_DEFAULT = "%s/";
    private static final String PAGE_INDEX = "%s/index";
    private static final String PAGE_BOOK_LIST = "%s/book/list";
    private static final String PAGE_AUTHOR_BLOG = "%s/author/blog";
    private static final String PAGE_AUTHOR_BOOK = "%s/author/book";
    private static final String PAGE_AUTHOR_LIST = "%s/author/list";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("MetaInterceptor: preHandle");
        logger.info("User Address: " + request.getRemoteHost());
        logger.info("Request URL: " + request.getRequestURL().toString());
        logger.info("Request Method: " + request.getMethod());

        /* 1.请求相关数据 操作 */
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");

        /* 2.非拦截路由 检查 */
        if (contextPath.equals(uri)
                || String.format(PAGE_DEFAULT, contextPath).equals(uri)
                || String.format(PAGE_INDEX, contextPath).equals(uri)
                || String.format(PAGE_BOOK_LIST, contextPath).equals(uri)
                || String.format(PAGE_AUTHOR_BLOG, contextPath).equals(uri)
                || String.format(PAGE_AUTHOR_BOOK, contextPath).equals(uri)
                || String.format(PAGE_AUTHOR_LIST, contextPath).equals(uri)) {
            return true;
        }
        if (String.format(API_USER_LOGIN, contextPath).equals(uri)
                || String.format(API_USER_REGISTER, contextPath).equals(uri)
                || String.format(API_TEACHER_REGISTER, contextPath).equals(uri)
                || String.format(API_INDEX_AUTHOR, contextPath).equals(uri)
                || String.format(API_BOOK_LIST, contextPath).equals(uri)
                || uri.contains(String.format(API_AUTHOR, contextPath))
                || uri.contains(String.format(API_BOOK, contextPath))
                || uri.contains(String.format(API_COURSE, contextPath))) {
            if (null == request.getHeader(Constant.Request.Header.REFERER)) {
                ObjectMapper om = new ObjectMapper();
                PrintWriter out = response.getWriter();
                out.print(om.writeValueAsString(new Response<>(StatusCode.REQUEST_ILLEGAL, "REQUEST_ILLEGAL")));
                out.flush();
                return false;
            } else {
                return true;
            }
        }

        /* 3.Token 检查 */
        User user = (User) request.getSession().getAttribute(Constant.USER);
        if (null == user) {
            if (uri.contains("/api")) {
                ObjectMapper om = new ObjectMapper();
                PrintWriter out = response.getWriter();
                out.print(om.writeValueAsString(new Response<>(StatusCode.USER_LOGIN_TIMEOUT, "LOGIN_TIMEOUT")));
                out.flush();
            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        logger.info("MetaInterceptor: postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        logger.info("MetaInterceptor: afterCompletion");
    }
}
