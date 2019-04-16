const main = new Vue({
    el: "#main",
    data: {
        isChosen: false,
        tabIndex: 0,
        emailPassword: "",
        oldPassword: "",
        newPassword: "",
        repeatPassword: "",
        user: {
            name: "",
            avatar: "",
            email: "",
            motto: "",
            profile: "",
            gender: "",
        },
        locationList: [
            {
                province: "北京市",
                prefectures: ["东城区", "西城区", "朝阳区", "丰台区", "石景山区", "海淀区", "顺义区", "通州区", "大兴区", "房山区", "门头沟区", "昌平区", "平谷区", "密云区", "怀柔区", "延庆区"]
            },
            {
                province: "天津市",
                prefectures: ["和平区", "河东区", "河西区", "南开区", "河北区", "红桥区", "滨海新区", "东丽区", "西青区", "津南区", "北辰区", "武清区", "宝坻区", "宁河区", "静海区", "蓟州区"]
            },
            {
                province: "河北省",
                prefectures: ["石家庄市", "唐山市", "秦皇岛市", "邯郸市", "邢台市", "保定市", "张家口市", "承德市", "沧州市", "廊坊市", "衡水市"]
            },
            {
                province: "山西省",
                prefectures: ["太原市", "大同市", "朔州市", "忻州市", "阳泉市", "吕梁市", "晋中市", "长治市", "晋城市", "临汾市", "运城市"]
            },
            {
                province: "内蒙古自治区",
                prefectures: ["呼和浩特市", "包头市", "乌海市", "赤峰市", "通辽市", "鄂尔多斯市", "呼伦贝尔市", "巴彦淖尔市", "乌兰察布市", "兴安盟", "锡林郭勒盟", "阿拉善盟"]
            },
            {
                province: "辽宁省",
                prefectures: ["沈阳市", "大连市", "鞍山市", "抚顺市", "本溪市", "锦州市", "营口市市", "阜新市", "辽阳市", "盘锦市", "铁岭市", "朝阳市", "葫芦岛市"]
            },
            {
                province: "吉林省",
                prefectures: ["长春市", "吉林市", "四平市", "辽源市", "通化市", "白山市", "白城市", "松原市", "延边朝鲜族自治州"]
            },
            {
                province: "黑龙江省",
                prefectures: ["哈尔滨市", "齐齐哈尔市", "牡丹江市", "佳木斯市", "大庆市", "鸡西市", "双鸭山市", "伊春市", "七台河市", "鹤岗市", "黑河市", "绥化市", "大兴安岭地区"]
            },
            {
                province: "上海市",
                prefectures: ["黄浦区", "徐汇区", "长宁区", "静安区", "普陀区", "虹口区", "杨浦区", "浦东新区", "闵行区", "宝山区", "嘉定区", "金山区", "松江区", "青浦区", "奉贤区", "崇明区"]
            },
            {
                province: "江苏省",
                prefectures: ["南京市", "无锡市", "徐州市", "常州市", "苏州市", "南通市", "连云港市", "淮安市", "盐城市", "扬州市", "镇江市", "泰州市", "宿迁市"]
            },
            {
                province: "浙江省",
                prefectures: ["杭州市", "宁波市", "温州市", "绍兴市", "湖州市", "嘉兴市", "金华市", "衢州市", "台州市", "丽水市", "舟山市"]
            },
            {
                province: "安徽省",
                prefectures: ["合肥市", "芜湖市", "蚌埠市", "淮南市", "马鞍山市", "淮北市", "铜陵市", "安庆市", "黄山市", "阜阳市", "宿州市", "滁州市", "六安市", "宣城市", "池州市", "亳州市"]
            },
            {
                province: "福建省",
                prefectures: ["福州市", "厦门市", "漳州市", "泉州市", "三明市", "莆田市", "南平市", "龙岩市", "宁德市"]
            },
            {
                province: "江西省",
                prefectures: ["南昌市", "九江市", "上饶市", "抚州市", "宜春市", "吉安市", "赣州市", "景德镇市", "萍乡市", "新余市", "鹰潭市"]
            },
            {
                province: "山东省",
                prefectures: ["济南市", "青岛市", "淄博市", "枣庄市", "东营市", "烟台市", "潍坊市", "济宁市", "泰安市", "威海市", "日照市", "滨州市", "德州市", "聊城市", "临沂市", "菏泽市", "莱芜市"]
            },
            {
                province: "河南省",
                prefectures: ["郑州市", "开封市", "洛阳市", "平顶山市", "安阳市", "鹤壁市", "新乡市", "焦作市", "濮阳市", "许昌市", "漯河市", "三门峡市", "商丘市", "周口市", "驻马店市", "南阳市", "信阳市", "济源市"]
            },
            {
                province: "湖北省",
                prefectures: ["武汉市", "黄石市", "十堰市", "宜昌市", "襄阳市", "鄂州市", "荆门市", "孝感市", "荆州市", "黄冈市", "咸宁市", "随州市", "恩施土家族苗族自治州", "仙桃市", "潜江市", "天门市", "神农架林区"]
            },
            {
                province: "湖南省",
                prefectures: ["长沙市", "株洲市", "湘潭市", "衡阳市", "邵阳市", "岳阳市", "常德市", "张家界市", "益阳市", "娄底市", "郴州市", "永州市", "怀化市", "湘西土家族苗族自治州"]
            },
            {
                province: "广东省",
                prefectures: ["广州市", "深圳市", "珠海市", "汕头市", "佛山市", "韶关市", "湛江市", "肇庆市", "江门市", "茂名市", "惠州市", "梅州市", "汕尾市", "河源市", "阳江市", "清远市", "东莞市", "中山市", "潮州市", "揭阳市", "云浮市"]
            },
            {
                province: "广西壮族自治区",
                prefectures: ["南宁市", "柳州市", "桂林市", "梧州市", "北海市", "防城港市", "钦州市", "贵港市", "玉林市", "百色市", "贺州市", "河池市", "来宾市", "崇左市"]
            },
            {
                province: "海南省",
                prefectures: ["海口市", "三亚市", "三沙市", "儋州市"]
            },
            {
                province: "重庆市",
                prefectures: ["渝中区", "万州区", "涪陵区", "大渡口区", "江北区", "沙坪坝区", "九龙坡区", "南岸区", "北碚区", "綦江区", "大足区", "渝北区", "巴南区", "黔江区", "长寿区", "江津区", "合川区", "永川区", "南川区", "璧山区", "铜梁区", "潼南区", "荣昌区", "开州区", "梁平区", "武隆区", "城口县", "丰都县", "垫江县", "忠县", "云阳县", "奉节县", "巫山县", "巫溪县", "石柱土家族自治县", "秀山土家族苗族自治县", "酉阳土家族苗族自治县", "彭水苗族土家族自治县"]
            },
            {
                province: "四川省",
                prefectures: ["成都市", "绵阳市", "自贡市", "攀枝花市", "泸州市", "德阳市", "广元市", "遂宁市", "内江市", "乐山市", "资阳市", "宜宾市", "南充市", "达州市", "雅安市", "阿坝藏族羌族自治州", "甘孜藏族自治州", "凉山彝族自治州", "广安市", "巴中市", "眉山市"]
            },
            {
                province: "贵州省",
                prefectures: ["贵阳市", "遵义市", "六盘水市", "安顺市", "毕节市", "铜仁市", "黔东南苗族侗族自治州", "黔南布依族苗族自治州", "黔西南布依族苗族自治州"]
            },
            {
                province: "云南省",
                prefectures: ["昆明市", "曲靖市", "玉溪市", "昭通市", "保山市", "丽江市", "普洱市", "临沧市", "德宏傣族景颇族自治州", "怒江傈僳族自治州", "迪庆藏族自治州", "大理白族自治州", "楚雄彝族自治州", "红河哈尼族彝族自治州", "文山壮族苗族自治州", "西双版纳傣族自治州"]
            },
            {
                province: "西藏自治区",
                prefectures: ["拉萨市", "日喀则市", "昌都市", "林芝市", "山南市", "那曲市", "阿里地区"]
            },
            {
                province: "陕西省",
                prefectures: ["西安市", "宝鸡市", "咸阳市", "铜川市", "渭南市", "延安市", "榆林市", "汉中市", "安康市", "商洛市"]
            },
            {
                province: "甘肃省",
                prefectures: ["兰州市", "嘉峪关市", "金昌市", "白银市", "天水市", "武威市", "张掖市", "平凉市", "酒泉市", "庆阳市", "定西市", "陇南市", "临夏回族自治州", "甘南藏族自治州"]
            },
            {
                province: "青海省",
                prefectures: ["西宁市", "海东市", "海北藏族自治州", "黄南藏族自治州", "海南藏族自治州", "果洛藏族自治州", "玉树藏族自治州", "海西蒙古族藏族自治州"]
            },
            {
                province: "宁夏回族自治区",
                prefectures: ["银川市", "石嘴山市", "吴忠市", "固原市", "中卫市"]
            },
            {
                province: "新疆维吾尔自治区",
                prefectures: ["乌鲁木齐市", "克拉玛依市", "吐鲁番市", "哈密市", "阿克苏地区", "喀什地区", "和田地区", "昌吉回族自治州", "博尔塔拉蒙古自治州", "巴音郭楞蒙古自治州", "克孜勒苏柯尔克孜自治州", "伊犁哈萨克自治州", "塔城地区", "阿勒泰地区"]
            },
            {
                province: "香港特别行政区",
                prefectures: ["中西区", "湾仔区", "东区", "南区", "油尖旺区", "深水埗区", "九龙城区", "黄大仙区", "观塘区", "北区", "大埔区", "沙田区", "西贡区", "荃湾区", "屯门区", "元朗区", "葵青区", "离岛区"]
            },
            {
                province: "澳门特别行政区",
                prefectures: ["花地玛堂区", "圣安多尼堂区", "大堂区", "望德堂区", "风顺堂区", "嘉模堂区", "圣方济各堂区", "路氹城"]
            },
            {
                province: "台湾省",
                prefectures: ["台北市", "新北市", "桃园市", "台中市", "台南市", "高雄市", "基隆市", "新竹市", "嘉义市", "新竹县", "苗栗县", "彰化县", "南投县", "云林县", "嘉义县", "屏东县", "宜兰县", "花莲县", "台东县", "澎湖县", "金门县", "连江县"]
            }
        ],
        location: {},
        saveAvatarAction: "保存",
        isSaveAvatarBtnDisabled: false,
        saveAction: "保存",
        isSaveBtnDisabled: false
    },
    methods: {
        setUser: function (user) {
            this.user = user;
            if (null === user.province) {
                this.user.province = "请选择";
            }
            if (null === user.prefecture) {
                this.user.prefecture = "请选择";
            }
            if (null === user.avatar) {
                this.user.avatar = "../images/default.png";
            }
            if (null === user.portrait) {
                this.user.portrait = "../images/default.png";
            }
        },
        selectLocation: function () {
            for (let index = 0; index < this.locationList.length; index++) {
                if (this.user.province === this.locationList[index].province) {
                    this.location = this.locationList[index];
                    break;
                }
            }
        },
        chooseAvatar: function () {
            document.getElementById("file").click();
        },
        analyzeAvatar: function () {
            this.isChosen = true;
            this.oldURL = this.user.avatar;
            this.user.avatar = getFileNativeUrl(document.getElementById("file").files[0]);
        },
        cancelChoice: function () {
            this.isChosen = false;
            this.user.avatar = this.oldURL;
            this.oldURL = "";
            document.getElementById("file").value = "";
        },
        changeTab: function (tabIndex) {
            this.tabIndex = tabIndex;
        },
        changeProvince: function () {
            this.selectLocation();
            this.user.prefecture = "请选择";
        },
        saveAvatar: function () {
            this.isSaveAvatarBtnDisabled = true;
            this.saveAvatarAction = "正在保存";
            Bmob.initialize("75b6a15a8791635241707418e52dcb90", "cf34d2d2b2c325fcf58079c3063526f4");
            let file = document.getElementById("file");
            let bmobFile = new Bmob.File(file.value, file.files[0]);
            bmobFile.save().then(
                function (obj) {
                    let avatarUrl = obj.url();
                    axios.put(requestContext + "api/users/avatar", {
                        avatar: avatarUrl
                    }).then(function (response) {
                        let statusCode = response.data.statusCode;
                        if (200 === statusCode) {
                            main.saveAvatarCallback("保存成功", true);
                            main.user.avatar = avatarUrl;
                            let currentUser = JSON.parse(localStorage.getItem("user"));
                            currentUser.avatar = avatarUrl;
                            localStorage.setItem("user", JSON.stringify(currentUser));
                            header.setUserAvatar(avatarUrl);
                        } else {
                            let message = getMessage(statusCode);
                            main.saveAvatarCallback(message, false);
                        }
                    }).catch(function () {
                        main.saveAvatarCallback("服务器访问失败", false);
                    });
                },
                function () {
                    main.saveAvatarCallback("保存失败", false);
                });
        },
        saveAvatarCallback: function (message, success) {
            popoverSpace.append(message, success);
            this.saveAvatarAction = "保存";
            if (success) {
                this.isChosen = false;
            }
            this.isSaveAvatarBtnDisabled = false;
        },
        saveInfo: function () {
            this.saveStart();
            let province = null;
            let prefecture = null;
            if ("请选择" !== this.user.province && "请选择" !== this.user.prefecture) {
                province = this.user.province;
                prefecture = this.user.prefecture;
            }
            axios.put(requestContext + "api/users", {
                motto: this.user.motto,
                profile: this.user.profile,
                province: province,
                prefecture: prefecture,
                gender: this.user.gender,
                birthday: this.user.birthday
            }).then(function (response) {
                let statusCode = response.data.statusCode;
                if (200 === statusCode) {
                    main.saveCallback("保存成功", true);
                } else {
                    let message = getMessage(statusCode);
                    main.saveCallback(message, false);
                }
            }).catch(function (error) {
                main.saveCallback("服务器访问失败", false);
            });
        },
        saveEmail: function () {
            if (-1 === this.user.email.indexOf("@") || -1 === this.user.email.indexOf(".")) {
                popoverSpace.append("请输入正确格式的邮箱地址", false);
                return;
            }
            if ("" === this.emailPassword || this.emailPassword.length < 6) {
                popoverSpace.append("请输入正确格式的密码", false);
                return;
            }
            this.saveStart();
            axios.put(requestContext + "api/users/email", {
                email: this.user.email,
                password: this.emailPassword
            }).then(function (response) {
                let statusCode = response.data.statusCode;
                if (200 === statusCode) {
                    main.saveCallback("保存成功", true);
                } else {
                    let message = getMessage(statusCode);
                    main.saveCallback(message, false);
                }
            }).catch(function () {
                main.saveCallback("系统错误", false);
            });
        },
        savePassword: function () {
            if ("" === this.oldPassword || this.oldPassword.length < 6) {
                popoverSpace.append("请输入正确格式的原密码", false);
                return;
            }
            if ("" === this.newPassword || this.newPassword.length < 6) {
                popoverSpace.append("请输入正确格式的新密码", false);
                return;
            }
            if (this.newPassword !== this.repeatPassword) {
                popoverSpace.append("新密码输入不一致", false);
                return;
            }
            this.saveStart();
            axios.put(requestContext + "api/users/password", {
                oldPassword: this.oldPassword,
                newPassword: this.newPassword
            }).then(function (response) {
                let statusCode = response.data.statusCode;
                if (200 === statusCode) {
                    main.saveCallback("保存成功", true);
                } else {
                    let message = getMessage(statusCode);
                    main.saveCallback(message, false);
                }
            }).catch(function () {
                main.saveCallback("服务器访问失败", false);
            });
        },
        saveStart: function () {
            this.isSaveBtnDisabled = true;
            this.saveAction = "正在保存";
        },
        saveCallback: function (message, success) {
            popoverSpace.append(message, success);
            this.saveAction = "保存";
            this.isSaveBtnDisabled = false;
        }
    },
    mounted: function () {
        axios.get(requestContext + "api/users/current")
            .then(function (response) {
                let statusCode = response.data.statusCode;
                if (200 === statusCode) {
                    main.setUser(response.data.data);
                    main.selectLocation();
                }
            }).catch(function () {
            main.saveCallback("服务器访问失败", false);
        });
    }
});