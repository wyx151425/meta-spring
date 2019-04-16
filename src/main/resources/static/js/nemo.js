let requestContext = "http://localhost:8080/";

function getUrlParam(url, name) {
    let pattern = new RegExp("[?&]" + name + "\=([^&]+)", "g");
    let matcher = pattern.exec(url);
    let items = null;
    if (null !== matcher) {
        try {
            items = decodeURIComponent(decodeURIComponent(matcher[1]));
        } catch (e) {
            try {
                items = decodeURIComponent(matcher[1]);
            } catch (e) {
                items = matcher[1];
            }
        }
    }
    return items;
}

function getFileNativeUrl(file) {
    let url = null;
    if (undefined !== window.createObjectURL) {
        url = window.createObjectURL(file);
    } else if (undefined !== window.URL) {
        url = window.URL.createObjectURL(file);
    } else if (undefined !== window.webkitURL) {
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}

function getMessage(statusCode) {
    switch (statusCode) {
        case 300:
            return "系统状态错误";
        case 500:
            return "系统运行错误";
        case 600:
            return "请求参数错误";
        case 1000:
            return "用户未注册";
        case 1001:
            return "用户已注册";
        case 1002:
            return "用户被禁用";
        case 1003:
            return "登录超时";
        case 1004:
            return "密码错误";
    }
}

const header = new Vue({
    el: "#header",
    data: {
        isVisible: false,
        timer: null,
        user: {
            avatar: ""
        }
    },
    methods: {
        visible: function () {
            clearInterval(this.timer);
            this.timer = null;
            this.isVisible = true;
        },
        invisible: function () {
            this.timer = setInterval(function () {
                header.isVisible = false;
            }, 500);
        },
        setUserAvatar: function (avatar) {
            this.user.avatar = avatar;
        },
        loginModalVisible: function () {
            loginForm.visible();
        },
        logout: function () {
            axios.post(requestContext + "/api/users/logout")
                .then(function (response) {
                    let statusCode = response.data.statusCode;
                    if (200 === statusCode || 1003 === statusCode) {
                        localStorage.removeItem("user");
                        window.location.reload();
                    }
                }).catch(function () {
                popoverSpace.append("服务器访问失败", false);
            });
        }
    },
    mounted: function () {
        this.user = JSON.parse(localStorage.getItem("user"));
    }
});

Vue.component("popover", {
    props: ["prompt"],
    template: `
            <div class="popover" :class="{success: prompt.success, error: !prompt.success}">
                <span class="icon icon-ok" v-if="prompt.success"></span>
                <span class="icon icon-remove" v-if="!prompt.success"></span>
                <span>{{prompt.message}}</span>
            </div>
        `
});

const popoverSpace = new Vue({
    el: "#popoverSpace",
    data: {
        prompts: [],
        index: 0
    },
    methods: {
        append: function (message, success) {
            let prompt = {id: this.index, success: success, message: message};
            this.index++;
            this.prompts.push(prompt);
            setTimeout(function () {
                popoverSpace.prompts.shift(prompt);
            }, 5000);
        }
    }
});