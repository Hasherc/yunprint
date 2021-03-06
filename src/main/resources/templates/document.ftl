<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>预览文档</title>
</head>
<body>
    <div id="reader"></div>
    <script src="http://static.bcedocument.com/reader/v2/doc_reader_v2.js"></script>
    <script>
        (function () {
            var option = {
                docId: '${documentId}',
                token: 'TOKEN',
                host: 'BCEDOC',
                serverHost: 'http://doc.bj.baidubce.com',
                width: 600, //文档容器宽度
                zoom: true,              //是否显示放大缩小按钮
                zoomStepWidth:200,
                pn: 1,  //定位到第几页，可选
                ready: function (handler) {  // 设置字体大小和颜色, 背景颜色（可设置白天黑夜模式）
                    handler.setFontSize(1);
                    handler.setBackgroundColor('#000');
                    handler.setFontColor('#fff');
                },
                flip: function (data) {    // 翻页时回调函数, 可供客户进行统计等
                    console.log(data.pn);
                },
                fontSize:'big',
                toolbarConf: {
                    page: true, //上下翻页箭头图标
                    pagenum: true, //几分之几页
                    full: false, //是否显示全屏图标,点击后全屏
                    copy: true, //是否可以复制文档内容
                    position: 'center',// 设置 toolbar中翻页和放大图标的位置(值有left/center)
                } //文档顶部工具条配置对象,必选
            };
            new Document('reader', option);
        })();
    </script>
</body>
</html>