package cn.edu.qfnu.meta.util;

/**
 * 应用常量数据类
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/08/04
 */
public class Constant {
    public static final String TOKEN = "token";
    public static final String USER = "user";
    public static final String USER_LIST = "userList";
    public static final String BOOK = "book";
    public static final String BOOK_LIST = "bookList";
    public static final String PAGE = "page";
    public static final String PAGE_LIST = "pageList";
    public static final String RESPONSE = "response";

    public static class PageModel {
        public static class Limit {
            public static final int USER = 32;
            public static final int BOOK = 32;
            public static final int PAGE = 64;
        }
    }

    public static class Request {
        public static class Header {
            public static final String REFERER = "referer";
        }
    }

    public static class View {
        public static final String INDEX = "index";
        public static final String LOGIN = "login";
        public static final String REGISTER = "register";
        public static final String USER_BLOG = "user-blog";
        public static final String USER_SETTINGS = "user-settings";
        public static final String USER_BOOK = "user-book";
        public static final String USER_PAGE_EDIT = "user-page-edit";
        public static final String BOOK_CREATE = "book-create";
    }

    public static class UserStatus {
        public static final int DISABLED = 0;
        public static final int USER = 1;
        public static final int AUTHOR = 2;
    }

    public static class BookStatus {
        public static final int DELETED = 0;
        public static final int CREATED = 1;
        public static final int PUBLISH = 2;
    }

    public static class Status {
        public static final int DELETED = 0;
        public static final int GENERAL = 1;
    }

    public static class Roles {
        public static final String USER = "USER";
        public static final String LECTURER = "LECTURER";
        public static final String MANAGER = "MANAGER";
    }

    public static class Permissions {
        public static final String COMMON = "COMMON";
        public static final String CREATE_COURSE = "CREATE_COURSE";
        public static final String QUERY_COURSE = "QUERY_COURSE";
        public static final String QUERY_FOLLOWER = "QUERY_FOLLOWER";
        public static final String MANAGE_SYSTEM = "MANAGE_SYSTEM";
        public static final String QUERY_USER = "QUERY_USER";
        public static final String AUTHORIZATION = "AUTHORIZATION";
    }
}
