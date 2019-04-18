const main = new Vue({
    el: "#main",
    data: {
        tabIndex: 0,
        user: {
            avatar: "../images/default.png",
            name: "",
            motto: "",
            book: 0,
            follow: 0,
            follower: 0,
            favorite: 0,
            permissions: {
                CREATE_COURSE: false,
                OBTAIN_FOLLOWER: false
            }
        },
        authorList: [],
        followerList: [],
        bookList: []
    },
    methods: {
        setUser: function (user) {
            this.user = user;
        },
        getUser: function () {
            return this.user;
        },
        addBook: function (book) {
            this.user.bookList.push(book);
        },
        setAuthorList: function (authorList) {
            this.authorList = authorList;
        },
        setFollowerList: function (followerList) {
            this.followerList = followerList;
        },
        setBookList: function (bookList) {
            this.bookList = bookList;
        },
        changeTab: function (tabIndex) {
            this.tabIndex = tabIndex;
            if (1 === tabIndex) {
                axios.get(requestContext + "api/follows/author?userId=" + main.getUser().id)
                    .then(function (response) {
                        let statusCode = response.data.statusCode;
                        if (200 === statusCode) {
                            main.setAuthorList(response.data.data);
                        }
                    }).catch(function () {
                    popoverSpace.append("服务器访问失败", false);
                });
            } else if (2 === tabIndex) {
                axios.get(requestContext + "api/follows/user?authorId=" + main.getUser().id)
                    .then(function (response) {
                        let statusCode = response.data.statusCode;
                        if (200 === statusCode) {
                            main.setFollowerList(response.data.data);
                        }
                    }).catch(function () {
                    popoverSpace.append("服务器访问失败", false);
                });
            } else if (3 === tabIndex) {
                axios.get(requestContext + "api/favorites/book?userId=" + main.getUser().id)
                    .then(function (response) {
                        let statusCode = response.data.statusCode;
                        if (200 === statusCode) {
                            main.setBookList(response.data.data);
                        }
                    }).catch(function () {
                    popoverSpace.append("服务器访问失败", false);
                });
            }
        },
        bookCreateModalVisible: function () {
            bookCreateModal.visible();
        },
        bookUpdateModalVisible: function (book) {
            bookUpdateModal.visible(book);
        }
    },
    mounted: function () {
        axios.get(requestContext + "api/users/current")
            .then(function (response) {
                main.setUser(response.data.data);
            }).catch(function () {
            popoverSpace.append("服务器访问失败", false);
        });
    }
});

const bookCreateModal = new Vue({
    el: "#bookCreateModal",
    data: {
        isVisible: false,
        book: {
            name: "",
            style: "请选择",
            type: "请选择",
            description: "",
            cover: "../images/default.png"
        },
        typeArray: [
            {style: "高三", typeList: ["语文", "数学", "英语", "物理", "化学", "生物", "政治", "历史", "地理"]},
            {style: "高二", typeList: ["语文", "数学", "英语", "物理", "化学", "生物", "政治", "历史", "地理"]},
            {style: "高一", typeList: ["语文", "数学", "英语", "物理", "化学", "生物", "政治", "历史", "地理"]},
            {style: "初三", typeList: ["语文", "数学", "英语", "物理", "化学", "政治", "历史"]},
            {style: "初二", typeList: ["语文", "数学", "英语", "物理", "政治", "历史", "地理", "生物"]},
            {style: "初一", typeList: ["语文", "数学", "英语", "政治", "历史", "地理", "生物"]},
            {style: "六年级", typeList: ["语文", "数学", "英语"]},
            {style: "五年级", typeList: ["语文", "数学", "英语"]},
            {style: "四年级", typeList: ["语文", "数学", "英语"]},
            {style: "三年级", typeList: ["语文", "数学", "英语"]},
            {style: "二年级", typeList: ["语文", "数学", "英语"]},
            {style: "一年级", typeList: ["语文", "数学", "英语"]},
        ],
        typeList: [],
        oldURL: "",
        isChosen: false,
        action: "创建",
        isDisabled: false,
    },
    methods: {
        visible: function () {
            this.isVisible = true;
        },
        invisible: function () {
            this.isVisible = false;
        },
        selectType: function () {
            for (let index = 0; index < this.typeArray.length; index++) {
                if (this.book.style === this.typeArray[index].style) {
                    this.typeList = this.typeArray[index].typeList;
                    break;
                }
            }
        },
        chooseCover: function () {
            document.getElementById("file").click();
        },
        changeCover: function () {
            this.isChosen = true;
            this.oldURL = this.book.cover;
            this.book.cover = getFileNativeUrl(document.getElementById("file").files[0]);
        },
        cancelChoice: function () {
            this.isChosen = false;
            this.book.cover = this.oldURL;
            this.oldURL = "";
            document.getElementById("file").value = "";
        },
        createBook: function () {
            if (!document.getElementById("file").files[0]) {
                popoverSpace.append("请选择课程封面", false);
                return;
            }
            if ("" === this.book.name) {
                popoverSpace.append("请填写课程名称", false);
                return;
            }
            if ("请选择" === this.book.style) {
                popoverSpace.append("请选择课程所属年级", false);
                return;
            }
            if ("请选择" === this.book.type) {
                popoverSpace.append("请选择课程所属学科", false);
                return;
            }
            if ("" === this.book.description) {
                popoverSpace.append("请填写课程简介", false);
                return;
            }
            this.isDisabled = true;
            this.action = "正在创建";
            Bmob.initialize("75b6a15a8791635241707418e52dcb90", "cf34d2d2b2c325fcf58079c3063526f4");
            let file = document.getElementById("file");
            let bmobFile = new Bmob.File(file.value, file.files[0]);
            bmobFile.save().then(function (obj) {
                bookCreateModal.book.cover = obj.url();
                axios.post(requestContext + "api/books", bookCreateModal.book)
                    .then(function (response) {
                        let statusCode = response.data.statusCode;
                        if (200 === statusCode) {
                            bookCreateModal.createCallback("创建成功", true, response.data.data);
                        } else {
                            bookCreateModal.createCallback("创建失败", false);
                        }
                    }).catch(function () {
                    bookCreateModal.createCallback("系统错误", false);
                });
            }, function () {
                bookCreateModal.createCallback("创建失败", false);
            });
        },
        createCallback: function (message, isSuccess, book) {
            popoverSpace.append(message, isSuccess);
            this.action = "创建";
            if (isSuccess) {
                this.book.cover = "../images/default.png";
                this.book.name = "";
                this.book.type = "请选择";
                this.book.style = "请选择";
                this.book.description = "";
                this.isChosen = false;
                document.getElementById("file").value = "";
            }
            this.isDisabled = false;
            if (this.isVisible) {
                main.addBook(book);
                this.isVisible = false;
            }
        }
    }
});