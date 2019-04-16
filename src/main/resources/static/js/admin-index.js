const main = new Vue({
    el: "#main",
    data: {
        user: {},
        candidate: null,
        courseList: [],
        lecturerList: [],
        queryCode: ""
    },
    methods: {
        setUser: function (user) {
            this.user = user;
        },
        setCandidate: function (candidate) {
            this.candidate = candidate;
        },
        getCandidate: function () {
            return this.candidate;
        },
        setCourseList: function (courseList) {
            this.courseList = courseList;
        },
        setLecturerList: function (lecturerList) {
            this.lecturerList = lecturerList;
        },
        authorizeModalVisible: function () {
            authorizeModal.visible();
        },
        queryUser: function () {
            if ("" === this.queryCode) {
                popoverSpace.append("请填写正确格式的手机号或邮箱", false);
                return;
            }
            let regexp = /^[1][3,4,5,7,8][0-9]{9}$/;
            if (this.queryCode.indexOf("@") < 0 && !regexp.test(this.queryCode)) {
                popoverSpace.append("请填写正确格式的手机号", false);
                return;
            }
            if (this.queryCode.indexOf("@") > 0 && this.queryCode.indexOf(".") < 0) {
                popoverSpace.append("请填写正确格式的邮箱", false);
                return;
            }
            axios.get(requestContext + "api/users/query/" + this.queryCode)
                .then(function (response) {
                    if (200 === response.data.statusCode) {
                        let user = response.data.data;
                        if (null == user) {
                            popoverSpace.append("未查询到用户", false);
                            main.setCandidate(null);
                        } else {
                            main.setCandidate(user);
                        }
                    } else {
                        popoverSpace.append("查询失败", false);
                    }
                }).catch(function () {
                popoverSpace.append("服务器访问失败", false);
            });
        },

    },
    mounted: function () {
        let user = JSON.parse(localStorage.getItem("user"));
        this.setUser(user);
        axios.get(requestContext + "api/courses/rank?index=0")
            .then(function (response) {
                if (200 === response.data.statusCode) {
                    main.setCourseList(response.data.data);
                }
            }).catch(function () {
            popoverSpace.append("服务器访问失败", false);
        });
        axios.get(requestContext + "api/lecturers/rank?index=0")
            .then(function (response) {
                if (200 === response.data.statusCode) {
                    main.setLecturerList(response.data.data);
                }
            }).catch(function () {
            popoverSpace.append("服务器访问失败", false);
        });
    }
});

const authorizeModal = new Vue({
    el: "#authorizeModal",
    data: {
        user: {},
        isVisible: false,
        isDisabled: false,
        action: "确定"
    },
    methods: {
        visible: function () {
            this.user = main.getCandidate();
            this.isVisible = true;
        },
        invisible: function () {
            this.isVisible = false;
        },
        getUser: function () {
            return this.user;
        },
        authorize: function () {
            this.isDisabled = true;
            this.action = "正在授权";
            axios.put(requestContext + "api/users/authorize", this.user)
                .then(function (response) {
                    let statusCode = response.data.statusCode;
                    if (200 === statusCode) {
                        authorizeModal.authorizeCallback("已将讲师角色授予" + authorizeModal.getUser().name, true);
                    } else {
                        authorizeModal.authorizeCallback("授权失败", false);
                    }
                }).catch(function () {
                authorizeModal.authorizeCallback("服务器访问失败", false);
            });
        },
        authorizeCallback: function (message, success) {
            popoverSpace.append(message, success);
            this.isDisabled = false;
            this.action = "确定";
            if (success) {
                this.isVisible = false;
            }
        }
    }
});