const main = new Vue({
    el: "#main",
    data: {
        isList: true,
        isTrans: false,
        book: {
            author: {
                avatar: "../images/default.png",
                name: ""
            },
            cover: "../images/default.png",
            name: "",
            style: "",
            page: 0,
            star: 0,
            description: ""
        },
        favorite: null,
        isFavorite: false,
        isDisabled: false,
        user: {},
        tabIndex: 0,
        reviews: []
    },
    methods: {
        setUser: function (user) {
            this.user = user;
        },
        setBook: function (book) {
            this.book = book;
        },
        setReviews: function (reviews) {
            this.reviews = reviews;
        },
        getBookId: function () {
            return this.book.id;
        },
        getBookName: function () {
            return this.book.name;
        },
        getPageList: function () {
            return this.book.pageList;
        },
        addPage: function (page) {
            this.book.pageList.push(page);
        },
        uploadModalVisible: function () {
            uploadModal.visible();
        },
        transModalVisible: function () {
            if (this.book.pageList.length > 0) {
                transModal.visible();
                this.isTrans = true;
                this.isList = false;
            }
        },
        transModalInvisible: function () {
            this.isTrans = false;
            this.isList = true;
        },
        playVideo: function (index) {
            transModal.visible(index);
        },
        optionChosen: function (order, index) {
            this.reviews[order].choice = String.fromCharCode(index + 65);
            this.reviews[order].complete = true;
        },
        changeTab: function (tabIndex) {
            this.tabIndex = tabIndex;
        },
        submitReviews: function () {
            for (let review of this.reviews) {
                if (!review.complete) {
                    popoverSpace.append("请完成所有试题后再提交");
                    return;
                }
            }
            axios.post(requestContext + "api/reviews/submit", this.reviews)
                .then(function (response) {

                }).catch(function () {

            });
        }
    },
    mounted: function () {
        let url = window.location;
        let id = getUrlParam(url, "id");
        axios.get(requestContext + "api/books/" + id)
            .then(function (response) {
                main.setBook(response.data.data);
            }).catch(function () {
            popoverSpace.append("服务器访问失败", false);
        });
        axios.get(requestContext + "api/courses/" + id + "/reviews")
            .then(function (response) {
                main.setReviews(response.data.data);
            }).catch(function () {

        });
    },
    filters: {
        letter: function (value) {
            return String.fromCharCode(value + 65);
        }
    }
});

const transModal = new Vue({
    el: "#transModal",
    data: {
        isVisible: false,
        index: 0,
        page: {},
        pageList: [],
        bookName: ""
    },
    methods: {
        visible: function (index) {
            if (index) {
                this.index = index;
            } else {
                this.index = 0;
            }
            this.bookName = main.getBookName();
            this.pageList = main.getPageList();
            if (this.pageList.length > 0) {
                this.page = this.pageList[this.index];
                this.isVisible = true;
            }
        },
        invisible: function () {
            main.transModalInvisible();
            this.pageList = null;
            this.isVisible = false;
        },
        prevTrans: function () {
            if (this.index > 0) {
                this.index--;
                this.page = this.pageList[this.index];
            }
        },
        nextTrans: function () {
            if (this.index < this.pageList.length - 1) {
                this.index++;
                this.page = this.pageList[this.index];
            }
        }
    }
});