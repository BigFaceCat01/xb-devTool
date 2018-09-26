
//vue
var newsDetail = new Vue({
    el:'#news_detail',
    data:{
        newsInfo:'',
    },
    methods:{
        clear:function(){
            this.newsInfo.type = '';
            this.newsInfo.body = '';
            this.newsInfo.source = '';
            this.newsInfo.title = '';
            editor.txt.html('');
        },
        save:function () {
            var userId = "2422736121541824512";
            this.newsInfo.body = editor.txt.html();
            var data = JSON.stringify(this.newsInfo);
            $.ajax({
                headers: {
                    "Content-type": "application/json; charset=utf-8",
                    "X-AUTH-USER":userId
                },
                type: "POST",
                url:"http://localhost:19090/mongo/news",
                data:data,
                success: function (res) {
                    if(res.code=="0"){
                        addNews.$message.success("保存成功");
                        addNews.clear();
                    }else {
                        addNews.$message.error(res.msg);
                    }
                }
            });
        },
        confirm:function () {
            var userId = "2422736121541824512";
            this.newsInfo.body = editor.txt.html();
            var data = JSON.stringify(this.newsInfo);
            $.ajax({
                headers: {
                    "Content-type": "application/json; charset=utf-8",
                    "X-AUTH-USER":userId
                },
                type: "POST",
                url:"http://localhost:19090/mongo/news/confirm",
                data:data,
                success: function (res) {
                    if(res.code=="0"){
                        addNews.$message.success("提交成功");
                        addNews.clear();
                    }else {
                        addNews.$message.error(res.msg);
                    }
                }
            });
        }
    }
});

//editor
var E = window.wangEditor;
var editor = new E('#news_body');
editor.customConfig.uploadImgShowBase64 = true   // 使用 base64 保存图片
editor.customConfig.uploadImgServer = '/upload'  // 上传图片到服务器
editor.create();