const main = new Vue({
    el: "#main",
    data: {
        authorList: []
    },
    methods: {
        setAuthorList: function (authorList) {
            this.authorList = authorList;
        }
    },
    mounted: function () {
        axios.get(requestContext + "api/authors")
            .then(function (response) {
                let statusCode = response.data.statusCode;
                if (200 === statusCode) {
                    main.setAuthorList(response.data.data);
                } else {
                    popoverSpace.append("漫画家获取失败", false);
                }
            }).catch(function () {
            popoverSpace.append("服务器访问失败", false);
        });
    }
});