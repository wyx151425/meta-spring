const main = new Vue({
    el: "#main",
    data: {
        page: {
            book: {
                cover: "../images/default.png"
            },
            image: "../images/default.png"
        }
    },
    methods: {
        getBookId: function () {
            return this.page.book.id;
        },
        setPage: function (page) {
            this.page = page;
        },
        getPage: function () {
            return this.page;
        },
        getPageId() {
            return this.page.id;
        },
        setPageImage: function (image) {
            this.page.image = image;
        },
        updateModalVisible: function () {
            updateModal.visible();
        },
        deleteModalVisible: function () {
            deleteModal.visible();
        }
    },
    mounted: function () {
        let url = window.location;
        let id = getUrlParam(url, "id");
        axios.get(requestContext + "api/pages/" + id)
            .then(function (response) {
                main.setPage(response.data.data);
            })
            .catch(function () {
                popoverSpace.append("服务器访问失败", false);
            });
    }
});

const updateModal = new Vue({
    el: "#updateModal",
    data: {
        name: "",
        action: "更新",
        isVisible: false,
        isDisabled: false
    },
    methods: {
        visible: function () {
            this.name = main.getPage().name;
            this.isVisible = true;
        },
        invisible: function () {
            this.isVisible = false;
        },
        updatePage: function () {
            if ("" === this.name) {
                popoverSpace.append("请填写章节名称", false);
                return;
            }
            let name = this.name;
            this.isDisabled = true;
            this.action = "正在更新";
            let file = document.getElementById("file");
            if (!file.files[0]) {
                console.log("Execute1");
                let page = main.getPage();
                page.name = name;
                axios.put(requestContext + "api/lessons", page)
                    .then(function (response) {
                        let statusCode = response.data.statusCode;
                        if (200 === statusCode) {
                            updateModal.updateResult("更新成功", true);
                        } else {
                            updateModal.updateResult("更新失败", false);
                        }
                    })
                    .catch(function () {
                        updateModal.updateResult("服务器访问失败", false);
                    });
            } else {
                console.log("Execute2");
                Bmob.initialize("75b6a15a8791635241707418e52dcb90", "cf34d2d2b2c325fcf58079c3063526f4");
                let bmobFile = new Bmob.File(file.value, file.files[0]);
                bmobFile.save().then(
                    function (obj) {
                        let page = main.getPage();
                        page.name = name;
                        page.image = obj.url();
                        axios.put(requestContext + "api/lessons", page)
                            .then(function (response) {
                                let statusCode = response.data.statusCode;
                                if (200 === statusCode) {
                                    main.setPageImage(obj.url());
                                    updateModal.updateResult("更新成功", true);
                                } else {
                                    updateModal.updateResult("更新失败", false);
                                }
                            })
                            .catch(function () {
                                updateModal.updateResult("服务器访问失败", false);
                            });
                    },
                    function () {
                        updateModal.updateResult("更新失败", false);
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