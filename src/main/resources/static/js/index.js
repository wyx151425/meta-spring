const main = new Vue({
    el: "#main",
    data: {
        courseList: [],
        authorList: []
    },
    methods: {
        setCourseList: function (courseList) {
            this.courseList = courseList;
        },
        setAuthorList: function (authorList) {
            this.authorList = authorList;
        }
    },
    mounted: function () {
        axios.get(requestContext + "api/books")
            .then(function (response) {
                let statusCode = response.data.statusCode;
                if (200 === statusCode) {
                    main.setCourseList(response.data.data);
                }
            });
    }
});