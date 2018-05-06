$("#delete_file_first").click(function () {
    var uuid = $("#file_first").attr("uuid");
    delete_file(uuid);
});
$("#delete_file_second").click(function () {
    var uuid = $("#file_second").attr("uuid");
    delete_file(uuid);

});
$("#delete_file_third").click(function () {
    var uuid = $("#file_third").attr("uuid");
    delete_file(uuid);

});
$("#typeSelectFile").click(function () {
    check_file_num();
    get_file_list();
});
$("#typeSelectImg").click(function () {
    check_file_num();
    get_file_list();
});
var file_num = 0;
function delete_file(uuid) {

    $.ajax({
        url: '/file/deleteFile',
        type: 'post',
        data: uuid,
        cache: false,
        processData: false,
        contentType: false,
        success: function (result) {
            check_file_num();
            get_file_list();
            if (result.status == 1) {
                $("#success").show();
                $("#success").text("文件删除成功");
            }
            if (result.status == 0) {

            }


        }
    })
}

function get_file_list() {
    $.ajax({
        url: '/file/fileList',
        type: 'get',
        cache: false,
        processData: false,
        contentType: false,
        success: function (result) {
            var files = JSON.parse(result);
            if (file_num == 0) {
                $("#first_file_block").hide();
                $("#second_file_block").hide();
                $("#third_file_block").hide();
            }
            if (file_num == 1) {
                $("#first_file_block").show();
                $("#file_first").text(files[0].name);
                $("#file_first").attr("uuid",files[0].uuid);
                $("#second_file_block").hide();
                $("#third_file_block").hide();
            }
            if (file_num >= 2) {
                $("#first_file_block").show();
                $("#file_first").text(files[0].name);
                $("#second_file_block").show();
                $("#file_second").text(files[1].name);
                $("#file_first").attr("uuid",files[0].uuid);
                $("#file_first").attr("uuid",files[1].uuid);
                $("#third_file_block").hide();
            }
            if (file_num == 3) {
                $("#first_file_block").show();
                $("#file_first").text(files[0].name);
                $("#second_file_block").show();
                $("#file_second").text(files[1].name);
                $("#third_file_block").show();
                $("#file_third").text(files[2].name);

                $("#file_first").attr("uuid",files[0].uuid);
                $("#file_second").attr("uuid",files[1].uuid);
                $("#file_third").attr("uuid",files[2].uuid);
            }
        }
    })
}

function check_file_num() {
    $.ajax({
        url: '/file/getFileCount',
        type: 'get',
        success: function (result) {
            var jsonOb = JSON.parse(result);
            file_num = jsonOb.count;

            if (file_num > 0) {
                $("#toOrder").show();
                if (file_num == 3) {
                    $("#warning").show();
                    $("#documentSubmit").hide();
                    document.getElementById("warning").innerHTML = "每个订单最多打印3个文件</br>目前已经上传了3个文件了哦";
                } else {
                    $("#warning").hide();
                    $("#documentSubmit").show();
                }
            } else {
                $("#toOrder").hide();
            }

        }
    })
}

$("#documentSubmit").click(function () {
    $("#notice").hide();
    $("#success").hide();
    var formData = new FormData($('#document-form')[0]);
    if ($('#documentInputFile').val() == "") {
        $("#notice").show();
        $("#notice").text("请选择文件");
        return false;
    }
    $("#imgWait").show();
    $.ajax({
        url: '/file/upload',
        type: 'post',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        success: function (json) {
            check_file_num();
            get_file_list();
            $("#documentInputFile").val("");
            var status = JSON.parse(json).status;
            if (status == 1) {
                $("#success").show();
                document.getElementById("success").innerHTML = "<strong>文件上传成功</strong><br><br>您可以继续上传或点击结算按钮进入结算页面";
                $("#imgWait").hide();
            } else if (status == 0) {
                $("#notice").show();
                $("#notice").text("文件上传失败");
                $("#imgWait").hide();
            } else{
                $("#notice").show();
                $("#notice").text("服务器错误");
                $("#imgWait").hide();
            }

        },
        error: function () {
            $("#notice").show();
            $("#notice").text("文件上传失败");
            $("#imgWait").hide();
        }
    });
});
$(document).ready(function () {
    $("#typeSelectFile").hover(function () {
        $("#typeSelectFile").addClass("select-div2");
    }, function () {
        $("#typeSelectFile").removeClass("select-div2");
    });
    $("#typeSelectImg").hover(function () {
        $("#typeSelectImg").addClass("select-div2");
    }, function () {
        $("#typeSelectImg").removeClass("select-div2");
    });
});

$('#typeSelectFile').click(function () {
    $("#notice").hide();
    $("#success").hide();
    $("#typeHelp").text("暂支持文件类型：doc, docx, ppt, pptx, xls, xlsx, wps, pdf, txt");
});

$('#typeSelectImg').click(function () {
    $("#notice").hide();
    $("#success").hide();
    $("#typeHelp").text("暂支持文件类型：.jpg .png ");
});
