const main = new Vue({
    el: "#main",
    data: {
        isList: true,
        isTrans: false,
        book: {
            author: {
                avatar: "../images/default.png",
                name: ""
            },
            cover: "../images/default.png",
            name: "",
            style: "",
            page: 0,
            star: 0,
            description: ""
        },
        favorite: null,
        isFavorite: false,
        isDisabled: false,
        user: {}
    },
    methods: {
        setUser: function (user) {
            this.user = user;
        },
        setBook: function (book) {
            this.book = book;
        },
        getBookId: function () {
            return this.book.id;
        },
        getBookName: function () {
            return this.book.name;
        },
        getPageList: function () {
            return this.book.pageList;
        },
        addPage: function (page) {
            this.book.pageList.push(page);
        },
        uploadModalVisible: function () {
            uploadModal.visible();
        },
        transModalVisible: function () {
            if (this.book.pageList.length > 0) {
                transModal.visible();
                this.isTrans = true;
                this.isList = false;
            }
        },
        transModalInvisible: function () {
            this.isTrans = false;
            this.isList = true;
        },
        playVideo: function (index) {
            transModal.visible(index);
        },
        favoriteBook: function () {
            this.isDisabled = true;
            if (!this.isFavorite) {
                axios.post(requestContext + "api/favorites", {
                    book: this.book,
                    user: this.user
                }).then(function (response) {
                    let statusCode = response.data.statusCode;
                    if (200 === statusCode) {
                        main.favoriteCallback(response.data.data);
                        popoverSpace.append("收藏成功", true);
                    } else if (1003 === statusCode) {
                        popoverSpace.append("登录Rumo 收藏该课程", true);
                    } else {
                        popoverSpace.append("收藏失败", false);
                    }
                }).catch(function () {
                    popoverSpace.append("服务器访问失败", false);
                });
            } else {
                axios.delete(requestContext + "api/favorites/" + this.favorite.id)
                    .then(function (response) {
                        let statusCode = response.data.statusCode;
                        if (200 === statusCode) {
                            main.disfavorCallback();
                            popoverSpace.append("取消成功", true);
                        } else {
                            popoverSpace.append("取消失败", false);
                        }
                    })
                    .catch(function () {
                        popoverSpace.append("服务器访问失败", false);
                    });
            }
        },
        favoriteCallback: function (favorite) {
            this.favorite = favorite;
            this.isFavorite = true;
            this.isDisabled = false;
        },
        disfavorCallback: function () {
            this.favorite = null;
            this.isFavorite = false;
            this.isDisabled = false;
        }
    },
    mounted: function () {
        let url = window.location;
        let id = getUrlParam(url, "id");
        let user = JSON.parse(localStorage.getItem("user"));
        this.setUser(user);
        axios.get(requestContext + "api/books/" + id)
            .then(function (response) {
                main.setBook(response.data.data);
                if (null != user) {
                    axios.get(requestContext + "api/favorites?bookId=" + id + "&userId=" + user.id)
                        .then(function (response) {
                            if (null !== response.data.data) {
                                main.favoriteCallback(response.data.data);
                            }
                        });
                }
            }).catch(function () {
            popoverSpace.append("服务器访问失败", false);
        });
    }
});

const transModal = new Vue({
    el: "#transModal",
    data: {
        isVisible: false,
        index: 0,
        page: {},
        pageList: [],
        bookName: ""
    },
    methods: {
        visible: function (index) {
            if (index) {
                this.index = index;
            } else {
                this.index = 0;
            }
            this.bookName = main.getBookName();
            this.pageList = main.getPageList();
            if (this.pageList.length > 0) {
                this.page = this.pageList[this.index];
                this.isVisible = true;
            }
        },
        invisible: function () {
            main.transModalInvisible();
            this.pageList = null;
            this.isVisible = false;
        },
        prevTrans: function () {
            if (this.index > 0) {
                this.index--;
                this.page = this.pageList[this.index];
            }
        },
        nextTrans: function () {
            if (this.index < this.pageList.length - 1) {
                this.index++;
                this.page = this.pageList[this.index];
            }
        }
    }
});