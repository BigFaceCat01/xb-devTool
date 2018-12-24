
//vue
var newsDetail = new Vue({
    el:'#news_detail',
    data:{
        newsInfo:[{
            url:'http://element-cn.eleme.io/static/hamburger.50e4091.png',
            id:1
        }],
        type:'',
        currentDate:new Date(),
        comment:{
            content:''
        }
    },
    methods:{
        registButton:function(row){
            console.log(row);
            var url = "http://localhost:19094/mongo/news/brand";
            var d = JSON.stringify(row);
            $.ajax({
                headers: {
                    "Content-type": "application/json; charset=utf-8"
                },
                type: "PUT",
                data:d,
                url:url,
                success: function (res) {
                    if(res.code=="0"){
                        newsDetail.$message.success(res.msg);
                    }else {
                        newsDetail.$message.error(res.msg);
                    }
                }
            });
        },
        supplyButton:function(row){
            console.log(row);
        },
        queryInfo:function () {
            var url = "http://localhost:19094/mongo/news/brand";
            $.ajax({
                headers: {
                    "Content-type": "application/json; charset=utf-8"
                },
                type: "GET",
                url:url,
                success: function (res) {
                    if(res.code=="0"){
                        newsDetail.newsInfo=res.data;
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