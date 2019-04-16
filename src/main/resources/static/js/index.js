const main = new Vue({
    el: "#main",
    data: {
        bookList: [],
        authorList: []
    },
    methods: {
        setBookList: function (bookList) {
            this.bookList = bookList;
        },
        setAuthorList: function (authorList) {
            this.authorList = authorList;
        }
    },
    mounted: function () {
        axios.get(requestContext + "api/authors/index")
            .then(function (response) {
                let statusCode = response.data.statusCode;
                if (200 === statusCode) {
                    main.setAuthorList(response.data.data);
                }
            });
    }
});