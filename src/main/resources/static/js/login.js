const loginForm = new Vue({
    el: "#loginForm",
    data: {
        isVisible: false,
        user: {
            mobilePhoneNumber: "",
            password: ""
        },
        type: "password",
        loginAction: "登录",
        loginDisabled: false
    },
    methods: {
        visible: function () {
            this.isVisible = true;
        },
        invisible: function () {
            this.isVisible = false;
        },
        visibility: function () {
            return this.isVisible;
        },
        removeMobilePhoneNumber: function () {
            this.user.mobilePhoneNumber = '';
        },
        changeType: function () {
            if ("password" === this.type) {
                this.type = "text";
            } else {
                this.type = "password";
            }
        },
        login: function () {
            let regexp = /^[1][3,4,5,7,8][0-9]{9}$/;
            // 校验用户手机号的合理性
            if ("" === this.user.mobilePhoneNumber) {
                popoverSpace.append("请填写手机号", false);
                return;
            }
            if (!regexp.test(this.user.mobilePhoneNumber)) {
                popoverSpace.append("请填写正确格式的手机号", false);
                return;
            }
            // 校验用户密码的合理性
            if ("" === this.user.password) {
                popoverSpace.append("请填写密码", false);
                return;
            }
            if (this.user.password.length < 6 || this.user.password.length > 32) {
                popoverSpace.append("请填写正确格式的密码", false);
                return;
            }
            this.loginDisabled = true;
            this.loginAction = "正在登录";
            // 发送AJAX请求访问后端，验证用户登录信息
            axios.post(requestContext + "api/users/login", this.user)
                .then(function (response) {
                    let statusCode = response.data.statusCode;
                    if (200 === statusCode) {
                        // 在Web端缓存中保存服务端返回的用户对象
                        localStorage.setItem("user", JSON.stringify(response.data.data));
                        if (loginForm.visibility()) {
                            window.location.reload();
                        } else {
                            window.location.href = "../index";
                        }
                    } else {
                        // 根据错误状态码获取错误提示信息
                        let message = getMessage(statusCode);
                        loginForm.loginResult(message, false);
                    }
                }).catch(function () {
                loginForm.loginResult("服务器访问失败", false);
            });
        },
        loginResult: function (message, success) {
            popoverSpace.append(message, success);
            this.loginAction = "登录";
            this.loginDisabled = false;
            if (success && this.isVisible) {
                window.location.href = requestContext + "index";
            }
        }
    }
});

const regForm = new Vue({
    el: "#regForm",
    data: {
        user: {
            name: "",
            mobilePhoneNumber: "",
            password: "",
        },
        type: "password",
        regAction: "注册",
        regDisabled: false
    },
    methods: {
        removeName: function () {
            this.user.name = "";
        },
        removeMobilePhoneNumber: function () {
            this.user.mobilePhoneNumber = '';
        },
        changeType: function () {
            if ("password" === this.type) {
                this.type = "text";
            } else {
                this.type = "password";
            }
        },
        register: function () {
            let mobileRegexp = /^[1][3,4,5,7,8][0-9]{9}$/;
            let smsRegexp = /^[0-9]{6}$/;
            if ("" === this.user.name) {
                popoverSpace.append("请填写姓名", false);
                return;
            }
            if (this.user.name.length < 2 || this.user.name.length > 3) {
                popoverSpace.append("请填写正确格式的姓名", false);
                return;
            }
            if ("" === this.user.mobilePhoneNumber) {
                popoverSpace.append("请填写手机号", false);
                return;
            }
            if (!mobileRegexp.test(this.user.mobilePhoneNumber)) {
                popoverSpace.append("请填写正确格式的手机号", false);
                return;
            }
            if ("" === this.user.password) {
                popoverSpace.append("请填写密码", false);
                return;
            }
            if (this.user.password.length < 6 || this.user.password.length > 32) {
                popoverSpace.append("请填写正确格式的密码", false);
                return;
            }
            this.regDisabled = true;
            this.regAction = "正在注册";
            axios.post(requestContext + "api/users/register", this.user)
                .then(function (response) {
                    let statusCode = response.data.statusCode;
                    if (200 === statusCode) {
                        localStorage.setItem("user", JSON.stringify(response.data.data));
                        regForm.registerResult("注册成功", true);
                    } else {
                        let message = getMessage(statusCode);
                        regForm.registerResult(message, false);
                    }
                }).catch(function () {
                regForm.registerResult("服务器访问失败", false);
            });
        },
        registerResult: function (message, success) {
            popoverSpace.append(message, success);
            this.regAction = "注册";
            this.regDisabled = false;
            if (success) {
                window.location.href = requestContext + "index";
            }
        }
    }
});

const regTeacherForm = new Vue({
    el: "#regTeacherForm",
    data: {
        user: {
            name: "",
            mobilePhoneNumber: "",
            password: "",
        },
        invitationCode: "",
        type: "password",
        regTeacherAction: "注册",
        regTeacherDisabled: false
    },
    methods: {
        removeName: function () {
            this.user.name = "";
        },
        removeMobilePhoneNumber: function () {
            this.user.mobilePhoneNumber = '';
        },
        removeInvitationCode: function () {
            this.invitationCode = '';
        },
        changeType: function () {
            if ("password" === this.type) {
                this.type = "text";
            } else {
                this.type = "password";
            }
        },
        registerTeacher: function () {
            let mobileRegexp = /^[1][3,4,5,7,8][0-9]{9}$/;
            let smsRegexp = /^[0-9]{6}$/;
            if ("" === this.user.name) {
                popoverSpace.append("请填写用户名", false);
                return;
            }
            if (this.user.name.length < 2 || this.user.name.length > 3) {
                popoverSpace.append("请填写正确格式的姓名", false);
                return;
            }
            if ("" === this.user.mobilePhoneNumber) {
                popoverSpace.append("请填写手机号", false);
                return;
            }
            if (!mobileRegexp.test(this.user.mobilePhoneNumber)) {
                popoverSpace.append("请填写正确格式的手机号", false);
                return;
            }
            if ("" === this.user.password) {
                popoverSpace.append("请填写密码", false);
                return;
            }
            if (this.user.password.length < 6 || this.user.password.length > 32) {
                popoverSpace.append("请填写正确格式的密码", false);
                return;
            }
            if (8 !== this.invitationCode.length) {
                popoverSpace.append("请填写正确格式的注册邀请码", false);
                return;
            }
            this.regTeacherDisabled = true;
            this.regTeacherAction = "正在注册";
            axios.post(requestContext + "api/teachers/register", this.user)
                .then(function (response) {
                    let statusCode = response.data.statusCode;
                    if (200 === statusCode) {
                        localStorage.setItem("user", JSON.stringify(response.data.data));
                        regTeacherForm.registerTeacherResult("注册成功", true);
                    } else {
                        let message = getMessage(statusCode);
                        regTeacherForm.registerTeacherResult(message, false);
                    }
                }).catch(function () {
                regTeacherForm.registerTeacherResult("服务器访问失败", false);
            });
        },
        registerTeacherResult: function (message, success) {
            popoverSpace.append(message, success);
            this.regTeacherAction = "注册";
            this.regTeacherDisabled = false;
            if (success) {
                window.location.href = requestContext + "index";
            }
        }
    }
});