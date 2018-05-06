<#include "header.ftl">
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2">
            <h3 class="text-center text-primary">
                <label for="order_uuid">订单编号:</label>
                <span id="order_uuid">${orderUuid}</span>
            </h3>
            <ul class="list-group" style="font-size: 22px;">
                <li class="list-group-item list-inline">
                    <div class="">
                        <label for="order_user_name">姓名:</label>
                        <span>
                            <input id="order_user_name" type="text" class="form-control" value="${user.nickName}" placeholder="${user.nickName}">
                        </span>
                    </div>
                </li>
            </ul>
            <ul class="list-group" style="font-size: 20px;">
                <li class="list-group-item list-inline">
                    <div class="">
                        <label for="order_phone_num">手机号:</label>
                        <span>
                            <input id="order_phone_num" type="text" class="form-control" value="${user.phoneNum}" placeholder="${user.phoneNum}">
                        </span>
                    </div>
                </li>
            </ul>
            <input id="file_count" style="display: none" value="${files?size}">
            <#list files>
                <#items as file>
                    <div index="${file_index}" class="order_file_back file" id="${file.fileUuid}">
                        <div class="row">
                            <div class="col-sm-10 text-muted">
                                <p style="font-size: small">
                                    <label for="file_name${file_index}">文件名：</label>
                                    <span id="file_name${file_index}">${file.name}</span>
                                </p>
                                <p>
                                    <label for="file_cover${file_index}">缩略图：</label>
                                    <img id="file_cover${file_index}" style="padding: 10px" src="${file.coverUrl}">

                                    <a target="view_window" href="/document?document_id=${file.documentId}">
                                        预览
                                    </a>
                                </p>
                                <p style="font-size: small">
                                    <label for="file_type">文件类型：</label>
                                    <span id="file_type">${file.type}</span></p>
                                <p style="font-size: small">
                                    <label for="print_way${file_index}">打印方式：</label>
                                    <div id="print_way${file_index}" class="radio">
                                        <label>
                                            <input type="radio" name="duplex${file_index}" id="single" value="false" checked onchange="changePrice();"> 单面
                                        </label>
                                        <label>
                                            <input type="radio" name="duplex${file_index}" id="double" value="true" onchange="changePrice();"> 双面
                                        </label>
                                    </div>
                                </p>
                                <p style="font-size: small">
                                    <label for="print_color${file_index}">色彩：</label>
                                    <div id="print_color${file_index}" class="radio" id="color">
                                        <label>
                                            <input type="radio" name="color${file_index}" id="black" value="false" checked onchange="changePrice();"> 黑白
                                        </label>
                                        <label>
                                            <input type="radio" name="color${file_index}" id="colorful" value="true" onchange="changePrice();"> 彩色
                                        </label>
                                    </div>

                                </p>
                                <p style="font-size: small">
                                    <label for="file_page${file_index}">页数：</label>
                                    <input class="form-control" id="file_page${file_index}" value="${file.pages}" onchange="changePrice();" disabled>
                                </p>
                                <p style="font-size: small">
                                    <label for="file_copies${file_index}">份数</label>
                                    <span>
                                        <input id="file_copies${file_index}" type="number" class="form-control " onchange="changePrice();" value="1" placeholder="请输入该文件需要打印的份数">
                                    </span>
                                </p>
                                <p style="color: #c9302c">
                                    <label for="file_price${file_index}">价格：</label>
                                    <span id="file_price${file_index}">
                                        ${file.pages * 0.1}
                                    </span>
                                </p>
                            </div>
                        </div>
                    </div>
                </#items>
            </#list>
            <p style="font-size: small">
                <label for="address">地址:</label>
                <span >
                    <input id="address" type="text" class="form-control" placeholder="请输入地址">
                </span>
            </p>
            <p>
                <label for="sum_money">总价格</label>
                <span id="sum_money" style="color: #c9302c; font-size: large"">0.2</span>
                <button type="button" class="btn btn-primary pull-right" onclick="commit_order()">提交订单</button>
            </p>
        </div>
    </div>

</div>
<#include "footer.ftl">
