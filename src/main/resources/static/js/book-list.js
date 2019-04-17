const main = new Vue({
    el: "#main",
    data: {
        style: "全部",
        type: "不限",
        typeIndex: -1,
        typeArray: [
            {style: "全部", typeList: ["不限", "高三", "高二", "高一", "初三", "初二", "初一", "六年级", "五年级", "四年级", "三年级", "二年级", "一年级"]},
            {style: "语文", typeList: ["不限", "高三", "高二", "高一", "初三", "初二", "初一", "六年级", "五年级", "四年级", "三年级", "二年级", "一年级"]},
            {style: "数学", typeList: ["不限", "高三", "高二", "高一", "初三", "初二", "初一", "六年级", "五年级", "四年级", "三年级", "二年级", "一年级"]},
            {style: "英语", typeList: ["不限", "高三", "高二", "高一", "初三", "初二", "初一", "六年级", "五年级", "四年级", "三年级", "二年级", "一年级"]},
            {style: "物理", typeList: ["不限", "高三", "高二", "高一", "初三", "初二", "初一"]},
            {style: "化学", typeList: ["不限", "高三", "高二", "高一", "初三", "初二", "初一"]},
            {style: "生物", typeList: ["不限", "高三", "高二", "高一", "初三", "初二", "初一"]},
            {style: "政治", typeList: ["不限", "高三", "高二", "高一", "初三", "初二", "初一"]},
            {style: "历史", typeList: ["不限", "高三", "高二", "高一", "初三", "初二", "初一"]},
            {style: "地理", typeList: ["不限", "高三", "高二", "高一", "初三", "初二", "初一"]}
        ],
        typeList: [],
        courseList: [],
        name: ""
    },
    methods: {
        setCourseList: function (courseList) {
            this.courseList = courseList;
        },
        selectStyle: function (style) {
            this.style = style;
            this.typeIndex = -1;
            this.selectTypeList();
            this.selectType(0, "不限");
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
            axios.get(requestContext + "api/courses?style=" + this.type + "&type=" + this.style)
                .then(function (response) {
                    let statusCode = response.data.statusCode;
                    if (200 === statusCode) {
                        main.setCourseList(response.data.data);
                    }
                }).catch(function () {
                popoverSpace.append("服务器访问失败", false);
            });
        },
        queryCourseByName: function () {
            axios.get(requestContext + "api/courses/name?name=" + this.name)
                .then(function (response) {
                    let statusCode = response.data.statusCode;
                    if (200 === statusCode) {
                        main.setCourseList(response.data.data);
                        main.initStyleAndType();
                    }
                }).catch(function () {
                popoverSpace.append("服务器访问失败", false);
            });
        },
        initStyleAndType: function () {
            this.style = "全部";
            this.type = "不限";
        }
    },
    mounted: function () {
        this.selectStyle(this.style);
        axios.get(requestContext + "api/courses?style=" + this.type + "&type=" + this.style)
            .then(function (response) {
                let statusCode = response.data.statusCode;
                if (200 === statusCode) {
                    main.setCourseList(response.data.data);
                }
            }).catch(function () {
            popoverSpace.append("服务器访问失败", false);
        });
    }
});