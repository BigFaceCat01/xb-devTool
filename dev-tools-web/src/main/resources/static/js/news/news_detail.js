
//vue
var newsDetail = new Vue({
    el:'#news_detail',
    data:{
        newsInfo:'',
        comment:{
            content:''
        }
    },
    methods:{
        queryInfo:function () {
            var newsId = this.$refs.newsId.value;
            var url = "http://localhost:19090/mongo/news/"+newsId;
            $.ajax({
                headers: {
                    "Content-type": "application/json; charset=utf-8"
                },
                type: "GET",
                url:url,
                success: function (res) {
                    if(res.code=="0"){
                        var d = new Date(res.data.createTime);
                        var r = util.formatDate(d,"yyyy年MM月dd日 hh:mm:ss")
                        newsDetail.newsInfo=res.data;
                        newsDetail.newsInfo.createTime = r;
                    }else {
                        newsDetail.$message.error(res.msg);
                    }
                }
            });
        }
    },
    mounted:function () {
        this.queryInfo();
    }
});