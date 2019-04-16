const main = new Vue({
    el: "#main",
    data: {
        user: {},
        author: {},
        isDisabled: false
    },
    methods: {
        setAuthor: function (author) {
            this.author = author;
        }
    },
    mounted: function () {
        let url = window.location;
        let id = getUrlParam(url, "id");
        axios.get(requestContext + "api/authors/" + id)
            .then(function (response) {
                let statusCode = response.data.statusCode;
                if (200 === statusCode) {
                    main.setAuthor(response.data.data);
                } else {
                    popoverSpace.append("课程讲师获取失败", false);
                }
            }).catch(function (error) {
            console.log(error);
            popoverSpace.append("服务器访问失败", false);
        });
    }
});