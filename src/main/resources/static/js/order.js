function commit_order() {
    var fileList = $(".file");

    var OrderArray = new Array();
    for(var i = 0; i < fileList.length; i++){
        var order = new Object();
        order.uuid = $("#order_uuid").text();
        order.name = $("#order_user_name").val();
        order.phoneNum = $("#order_phone_num").val();
        order.fileUuid = fileList[i].id;
        order.duplex = getTypeVal(i);
        order.colorful = getColorVal(i);
        console.log(order.color);
        order.pages = getPageVal(i);
        if ("" === $("#address").val()){
            $("#address").parent().addClass("has-error");
            $("#address").focus();
            return;
        }else {
            $("#address").parent().removeClass("has-error");
        }
        order.page = $("#file_page" + i).val();
        order.copies = getCopyVal(i);
        order.address = $("#address").val();
        OrderArray.push(order);
    }
    var json = JSON.stringify(OrderArray);
    $.ajax({
        type: "post",
        url: "/order/commit",
        data: json,
        dataType: "json",
        contentType: "text/plain",
        success: function (result) {
            if (result.status === 1) {
                window.location.href = "/toOrderSuccess";
            }
        }
    });


}
function getTypeVal(i) {
    if (i === 0){
        return $("input[name='duplex0']:checked").val();
    }
    if (i === 1){
        return $("input[name='duplex1']:checked").val();
    }
    if (i === 2){
        return $("input[name='duplex2']:checked").val();
    }
}
function getColorVal(i) {
    if (i === 0){
        return $("input[name='color0']:checked").val();
    }
    if (i === 1){
        return $("input[name='color1']:checked").val();
    }
    if (i === 2){
        return $("input[name='color2']:checked").val();
    }
}
function getPageVal(i) {
    if (i === 0){
        return $("#file_page0").val();
    }
    if (i === 1){
        return $("#file_page1").val();
    }
    if (i === 2){
        return $("#file_page2").val();
    }
}
function getCopyVal(i) {
    if (i === 0){
        return $("#file_copies0").val();
    }
    if (i === 1){
        return $("#file_copies1").val();
    }
    if (i === 2){
        return $("#file_copies2").val();
    }
}
function changePrice() {
    var count = $("#file_count").val();
    var sumPrice = 0;
    for(var i = 0; i < count; i++) {
        var type = getTypeVal(i);
        var copy = getCopyVal(i);
        var color = getColorVal(i);
        var page = getPageVal(i);
        var price = 0;
        $.ajax({
            type: "get",
            url: "/caculatePrice",
            data: {duplex:type,page:page,color:color,copy:copy},
            dataType: "json",
            contentType: "text/plain",
            async:false,
            success: function (result) {
                price = parseFloat(result);
                console.log(price);
                $("#file_price" + i).text("" + price);
            }
        });
        sumPrice += price;

    }
    $("#sum_money").text(sumPrice);
}
$(document).ready(function () {
    changePrice();
});
