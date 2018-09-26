var newsIndex = new Vue({
    el:"#news_index",
    data:{
        newsList:'',
        pageNum:1,
        pageSize:10,
        totalSize:'',
        pageSizeArray:[
            10,50,100,200
        ]
    },
    methods: {
        search: function () {
            var url = "http://localhost:19090/mongo/news?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize;
            var userId = "2422736121541824512";
            $.ajax({
                headers: {
                    "X-AUTH-USER": userId
                },
                type: "GET",
                url: url,
                success: function (res) {
                    if (res.code == "0") {
                        newsIndex.newsList = res.data.data;
                        newsIndex.pageNum = res.data.pageNum;
                        newsIndex.pageSize = res.data.pageSize;
                        newsIndex.totalSize = res.data.totalSize;
                    } else {
                        newsIndex.$message.error(res.msg);
                    }
                }
            });

        },
        pageNumChange:function (val) {
            this.pageNum=val;
            this.search();
        },
        pageSizeChange:function (val) {
            this.pageSize=val;
            this.search();
        },
        formatTime:function (row,column,cellValue,index) {
            var date = new Date(row.createTime);
            return util.formatDate(date,"yyyy-MM-dd hh:mm:ss")
        },
        newsDetail:function (row,event,column) {
            console.log(row.newsId);
        }
    },
    mounted:function () {
        this.search();
    }
});