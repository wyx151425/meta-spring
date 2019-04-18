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
            cover: "",
            name: "",
            style: "",
            page: 0,
            star: 0,
            description: ""
        },
        reviews: [],
        isVisible: false,
        timer: null,
        tabIndex: 0
    },
    methods: {
        visible: function () {
            clearInterval(this.timer);
            this.timer = null;
            this.isVisible = true;
        },
        invisible: function () {
            this.timer = setInterval(function () {
                main.isVisible = false;
            }, 500);
        },
        setUser: function (user) {
            this.user = user;
        },
        getUserId: function () {
            return this.user.id;
        },
        setBook: function (book) {
            this.book = book;
        },
        getBook: function () {
            return this.book;
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
        getPage: function (index) {
            return this.book.pageList[index];
        },
        setReviews: function (reviews) {
            this.reviews = reviews;
        },
        setPageImage: function (index, image) {
            this.book.pageList[index].image = image;
        },
        addPage: function (page) {
            this.book.pageList.push(page);
        },
        pushReview: function (review) {
            this.reviews.push(review);
        },
        deleteReview: function (index) {
            let review = this.reviews[index];
            this.reviews.shift(review);
        },
        uploadModalVisible: function () {
            uploadModal.visible();
        },
        reviewModalVisible: function () {
            reviewModal.visible();
        },
        publishModalVisible: function () {
            publishModal.visible();
        },
        updateBookModalVisible: function () {
            bookUpdateModal.visible(this.book);
        },
        deleteModalVisible: function () {
            deleteModal.visible();
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
        deleteReviewModalVisible: function (index) {
            let review = this.reviews[index];
            deleteReviewModal.visible(index, review);
        },
        updateLessonModalVisible: function (index) {
            updateLessonModal.visible(index)
        },
        deleteLessonModalVisible: function (index) {
            deleteLessonModal.visible(index);
        },
        playVideo: function (index) {
            transModal.visible(index);
        },
        changeTab: function (tabIndex) {
            this.tabIndex = tabIndex;
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
            }).catch(function () {
            popoverSpace.append("服务器访问失败", false);
        });
        axios.get(requestContext + "api/courses/" + id + "/reviews")
            .then(function (response) {
                main.setReviews(response.data.data);
            }).catch(function () {

        });
    },
    filters: {
        letter: function (value) {
            return String.fromCharCode(value + 65);
        }
    }
});

const transModal = new Vue({
    el: "#transModal",
    data: {
        isVisible: false,
        index: 0,
        page: {},
        pageList: [],
        book: {}
    },
    methods: {
        visible: function (index) {
            if (index) {
                this.index = index;
            } else {
                this.index = 0;
            }
            this.book = main.getBook();
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

const uploadModal = new Vue({
    el: "#uploadModal",
    data: {
        isVisible: false,
        isDisabled: false,
        action: "上传",
        name: ""
    },
    methods: {
        visible: function () {
            this.isVisible = true;
        },
        invisible: function () {
            this.isVisible = false;
        },
        uploadPage: function () {
            // 校验章节名称和视频的正确性
            if ("" === this.name) {
                popoverSpace.append("请填写视频名称", false);
                return;
            }
            let name = this.name;
            let file = document.getElementById("file");
            if (!file.files[0]) {
                popoverSpace.append("请选择视频文件", false);
                return;
            }
            this.isDisabled = true;
            this.action = "正在上传";
            // 初始化Bmob文件服务器的配置
            Bmob.initialize("75b6a15a8791635241707418e52dcb90", "cf34d2d2b2c325fcf58079c3063526f4");
            let bmobFile = new Bmob.File(file.value, file.files[0]);
            // 将视频文件保存到Bmob文件服务器
            bmobFile.save().then(function (obj) {
                // 视频文件保存成功后，保存课程章节信息
                axios.post(requestContext + "api/lessons", {
                    book: {id: main.getBookId()},
                    name: name,
                    image: obj.url()
                }).then(function (response) {
                    // 根据后端返回的响应状态码，提示保存结果
                    let statusCode = response.data.statusCode;
                    if (200 === statusCode) {
                        main.addPage(response.data.data);
                        uploadModal.uploadCallback("添加成功", true);
                        uploadModal.invisible();
                    } else {
                        uploadModal.uploadCallback("添加失败", false);
                    }
                }).catch(function () {
                    uploadModal.uploadCallback("系统错误", false);
                });
            }, function () {
                uploadModal.uploadCallback("添加失败", false);
            });
        },
        uploadCallback: function (message, success) {
            popoverSpace.append(message, success);
            this.action = "上传";
            if (success) {
                document.getElementById("file").value = "";
            }
            this.isDisabled = false;
        }
    }
});

const publishModal = new Vue({
    el: "#publishModal",
    data: {
        isVisible: false,
        isDisabled: false,
        action: "发布"
    },
    methods: {
        visible: function () {
            this.isVisible = true;
        },
        invisible: function () {
            this.isVisible = false;
        },
        publishBook: function () {
            if (0 === main.getBook().page) {
                popoverSpace.append("请先添加课程章节", false);
                return;
            }
            this.isDisabled = true;
            this.action = "正在发布";
            let url = requestContext + "api/books/" + main.getBookId() + "/publish";
            axios.put(url)
                .then(function (response) {
                    let statusCode = response.data.statusCode;
                    if (200 === statusCode) {
                        publishModal.publishResult("发布成功", true);
                    } else {
                        publishModal.publishResult("发布失败", false);
                    }
                }).catch(function () {
                publishModal.publishResult("服务器访问失败", false);
            });
        },
        publishResult: function (message, success) {
            popoverSpace.append(message, success);
            this.isDisabled = false;
            this.action = "发布";
            if (success) {
                main.getBook().status = 2;
                main.getBook().publish = true;
                publishModal.invisible();
            }
        }
    }
});

const reviewModal = new Vue({
    el: "#reviewModal",
    data: {
        isVisible: false,
        review: {
            question: "",
            optionList: [],
            answer: "A",
            book: {
                id: 0
            }
        },
        option: "",
        optionQuantity: 0,
        isAdd: false,
        isDisabled: false,
        action: "保存"
    },
    methods: {
        visible: function () {
            this.initReview();
            this.isVisible = true;
        },
        invisible: function () {
            this.isVisible = false;
        },
        initReview: function () {
            this.review.question = "";
            this.review.optionList = [];
            this.review.answer = "A";
            this.review.book.id = 0;
        },
        saveOption: function () {
            let option = this.option;
            this.option = "";
            this.isAdd = false;
            this.optionQuantity++;
            this.review.optionList.push(option);
        },
        saveReview: function () {
            if (null == this.review.question || "" === this.review.question) {
                popoverSpace.append("请填写试题问题", false);
                return;
            }
            if (this.optionQuantity !== 4) {
                popoverSpace.append("试题应包含四个选项", false);
                return;
            }
            this.review.book.id = main.getBookId();
            axios.post(requestContext + "api/reviews", this.review).then(function (response) {
                // 根据后端返回的响应状态码，提示保存结果
                let statusCode = response.data.statusCode;
                if (200 === statusCode) {
                    reviewModal.reviewCallback("添加成功", true);
                    main.pushReview(response.data.data);
                    reviewModal.invisible();
                } else {
                    reviewModal.reviewCallback("添加失败", false);
                }
            }).catch(function () {
                reviewModal.reviewCallback("系统错误", false);
            });
        },
        reviewCallback: function (message, success) {
            popoverSpace.append(message, success);
            this.action = "保存";
            this.isDisabled = false;
        }
    },
    filters: {
        letter: function (value) {
            return String.fromCharCode(value + 65);
        }
    }
});

const bookUpdateModal = new Vue({
    el: "#bookUpdateModal",
    data: {
        oldURL: "",
        isVisible: false,
        isChosen: false,
        action: "更新",
        isDisabled: false,
        book: {}
    },
    methods: {
        visible: function (book) {
            this.book.id = book.id;
            this.book.cover = book.cover;
            this.book.name = book.name;
            this.book.style = book.style;
            this.book.type = book.type;
            this.book.description = book.description;
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
            document.getElementById("cover").click();
        },
        changeCover: function () {
            this.isChosen = true;
            this.oldURL = this.book.cover;
            this.book.cover = getFileNativeUrl(document.getElementById("cover").files[0]);
        },
        cancelChoice: function () {
            this.isChosen = false;
            this.book.cover = this.oldURL;
            this.oldURL = "";
            document.getElementById("cover").value = "";
        },
        updateBook: function () {
            if ("" === this.book.name) {
                popoverSpace.append("请填写课程名称", false);
                return;
            }
            if ("" === this.book.description) {
                popoverSpace.append("请填写课程简介", false);
                return;
            }
            this.isDisabled = true;
            this.action = "正在更新";

            let book = this.book;
            let newCover = document.getElementById("cover");
            if (!newCover.files[0]) {
                axios.put(requestContext + "api/books", book)
                    .then(function (response) {
                        let statusCode = response.data.statusCode;
                        if (200 === statusCode) {
                            bookUpdateModal.updateCallback("更新成功", true, response.data.data);
                        } else {
                            bookUpdateModal.updateCallback("更新失败", false);
                        }
                    }).catch(function () {
                    bookUpdateModal.updateCallback("系统错误", false);
                });
            } else {
                Bmob.initialize("75b6a15a8791635241707418e52dcb90", "cf34d2d2b2c325fcf58079c3063526f4");
                let bmobFile = new Bmob.File(newCover.value, newCover.files[0]);
                bmobFile.save().then(function (obj) {
                    book.cover = obj.url();
                    axios.put(requestContext + "api/books", book)
                        .then(function (response) {
                            let statusCode = response.data.statusCode;
                            if (200 === statusCode) {
                                bookUpdateModal.updateCallback("更新成功", true);
                            } else {
                                bookUpdateModal.updateCallback("更新失败", false);
                            }
                        }).catch(function () {
                        bookUpdateModal.updateCallback("系统错误", false);
                    });
                }, function () {
                    bookUpdateModal.updateCallback("更新失败", false);
                });
            }
        },
        updateCallback: function (message, isSuccess) {
            if (isSuccess) {
                window.location.reload();
            } else {
                popoverSpace.append(message, isSuccess);
                this.action = "更新";
                this.isDisabled = false;
            }
        }
    }
});

const deleteModal = new Vue({
    el: "#deleteModal",
    data: {
        isVisible: false,
        isDisabled: false,
        action: "删除"
    },
    methods: {
        visible: function () {
            this.isVisible = true;
        },
        invisible: function () {
            this.isVisible = false;
        },
        deleteBook: function () {
            this.isDisabled = true;
            this.action = "正在删除";
            let url = requestContext + "api/books/" + main.getBookId();
            axios.delete(url)
                .then(function (response) {
                    let statusCode = response.data.statusCode;
                    if (200 === statusCode) {
                        window.location.href = requestContext + "user/index?id=" + main.getUserId();
                    } else {
                        deleteModal.deleteResult("删除失败", false);
                    }
                }).catch(function () {
                deleteModal.deleteResult("服务器访问失败", false);
            });
        },
        deleteResult: function (message, success) {
            popoverSpace.append(message, success);
            this.isDisabled = false;
            this.action = "删除";
        }
    }
});

const deleteReviewModal = new Vue({
    el: "#deleteReviewModal",
    data: {
        isVisible: false,
        isDisabled: false,
        action: "删除",
        review: null,
        index: -1
    },
    methods: {
        visible: function (index, review) {
            this.review = review;
            this.index = index;
            this.isVisible = true;
        },
        invisible: function () {
            this.isVisible = false;
        },
        deleteReview: function () {
            this.isDisabled = true;
            this.action = "正在删除";
            let url = requestContext + "api/reviews/" + this.review.id;
            axios.delete(url)
                .then(function (response) {
                    let statusCode = response.data.statusCode;
                    if (200 === statusCode) {
                        deleteReviewModal.deleteResult("删除成功", true);
                        main.deleteReview(deleteReviewModal.index);
                        deleteReviewModal.invisible();
                    } else {
                        deleteReviewModal.deleteResult("删除失败", false);
                    }
                }).catch(function (error) {
                deleteReviewModal.deleteResult("服务器访问失败", false);
            });
        },
        deleteResult: function (message, success) {
            popoverSpace.append(message, success);
            this.isDisabled = false;
            this.action = "删除";
        }
    }
});

const updateLessonModal = new Vue({
    el: "#updateLessonModal",
    data: {
        name: "",
        action: "更新",
        isVisible: false,
        isDisabled: false,
        index: -1,
    },
    methods: {
        visible: function (index) {
            this.index = index;
            this.name = main.getPage(this.index).name;
            this.isVisible = true;
        },
        invisible: function () {
            this.isVisible = false;
        },
        updatePage: function () {
            if ("" === this.name) {
                popoverSpace.append("请填写视频名称", false);
                return;
            }
            let name = this.name;
            this.isDisabled = true;
            this.action = "正在更新";
            let file = document.getElementById("lesson");
            if (!file.files[0]) {
                let page = main.getPage(this.index);
                page.name = name;
                axios.put(requestContext + "api/lessons", page)
                    .then(function (response) {
                        let statusCode = response.data.statusCode;
                        if (200 === statusCode) {
                            updateLessonModal.updateResult("更新成功", true);
                        } else {
                            updateLessonModal.updateResult("更新失败", false);
                        }
                    })
                    .catch(function () {
                        updateLessonModal.updateResult("服务器访问失败", false);
                    });
            } else {
                Bmob.initialize("75b6a15a8791635241707418e52dcb90", "cf34d2d2b2c325fcf58079c3063526f4");
                let bmobFile = new Bmob.File(file.value, file.files[0]);
                bmobFile.save().then(
                    function (obj) {
                        let page = main.getPage(updateLessonModal.index);
                        page.name = name;
                        page.image = obj.url();
                        axios.put(requestContext + "api/lessons", page)
                            .then(function (response) {
                                let statusCode = response.data.statusCode;
                                if (200 === statusCode) {
                                    main.setPageImage(updateLessonModal.index, obj.url());
                                    updateLessonModal.updateResult("更新成功", true);
                                } else {
                                    updateLessonModal.updateResult("更新失败", false);
                                }
                            })
                            .catch(function () {
                                updateLessonModal.updateResult("服务器访问失败", false);
                            });
                    },
                    function () {
                        updateLessonModal.updateResult("更新失败", false);
                    });
            }
        },
        updateResult: function (message, success) {
            popoverSpace.append(message, success);
            this.action = "更新";
            this.isDisabled = false;
            if (success) {
                this.invisible();
            }
        }
    }
});

const deleteLessonModal = new Vue({
    el: "#deleteLessonModal",
    data: {
        isVisible: false,
        isDisabled: false,
        action: "删除"
    },
    methods: {
        visible: function () {
            this.isVisible = true;
        },
        invisible: function () {
            this.isVisible = false;
        },
        deletePage: function () {
            this.isDisabled = true;
            this.action = "正在删除";
            let url = requestContext + "api/lessons/" + main.getPageId();
            axios.delete(url)
                .then(function (response) {
                    let statusCode = response.data.statusCode;
                    if (200 === statusCode) {
                        window.location.href = requestContext + "user/course?id=" + main.getBookId();
                    } else {
                        deleteLessonModal.deleteResult("删除失败", false);
                    }
                }).catch(function () {
                deleteLessonModal.deleteResult("服务器访问失败", false);
            });
        },
        deleteResult: function (message, success) {
            popoverSpace.append(message, success);
            this.isDisabled = false;
            this.action = "删除";
        }
    }
});