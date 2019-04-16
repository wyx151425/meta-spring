const main = new Vue({
    el: "#main",
    data: {
        style: "",
        typeIndex: -1,
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
        bookList: []
    },
    methods: {
        setBookList: function (bookList) {
            this.bookList = bookList;
        },
        selectStyle: function (style) {
            this.style = style;
            this.typeIndex = -1;
            this.selectTypeList();
        },
        selectType: function (typeIndex, type) {
            this.typeIndex = typeIndex;
            this.type = type;
            this.queryCourseByStyleAndType();
        },
        selectTypeList: function () {
            for (let index = 0; index < this.typeArray.length; index++) {
                if (this.style === this.typeArray[index].style) {
                    this.typeList = this.typeArray[index].typeList;
                    break;
                }
            }
        },
        queryCourseByStyleAndType: function () {
            axios.get(requestContext + "api/courses?style=" + this.style + "&type=" + this.type)
                .then(function (response) {
                    let statusCode = response.data.statusCode;
                    if (200 === statusCode) {
                        if (response.data.data.length === 0) {
                            popoverSpace.append("未查询到课程", true);
                        }
                        main.setBookList(response.data.data);
                    }
                }).catch(function () {
                popoverSpace.append("服务器访问失败", false);
            });
        }
    },
    mounted: function () {
        let url = window.location;
        let grade = getUrlParam(url, "grade");
        let styleIndex = grade - 1001;
        this.selectStyle(this.typeArray[styleIndex].style);
    }
});