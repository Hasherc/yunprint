<#include "header.ftl">
<div class="container"  >
    <div class="row">
        <div class="col-lg-10 col-lg-offset-1" style="padding-top: 100px">
            <h1 class="h3 text-info text-center">订单列表</h1>
            <div class="panel panel-info" >
            <#list orders>
                <#items as order>
                    <div class="panel-heading" style="margin-top: 3px">
                     <h4 class="panel-title">
                         <a data-toggle="collapse" data-parent="#order-group-${order_index}" href="#order_${order_index}">
                             <span class="badge pull-left">${order.count}</span>
                             <span style="padding-left: 15px">订单编号：<span>${order.uuid}</span></span>
                             <span class="pull-right">创建时间：<span>${order.time}</span></span>
                         </a>
                     </h4>
                    </div>
                    <div id="order_${order_index}" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item list-group-item-text">
                                    <label for="phone_${order_index}" class="text-success">手机号：</label>
                                    <span id="phone_${order_index}" >${order.phone}</span>
                                </li>
                                <li class="list-group-item list-group-item-text">
                                    <label for="address_${order_index}" class="text-success">地址：</label>
                                    <span id="address_${order_index}">${order.address}</span>
                                </li>
                                <li class="list-group-item list-group-item-text">
                                    <label for="price_${order_index}" class="text-success">总价：</label>
                                    <span id="price_${order_index}">${order.price}</span>
                                </li>
                                <li class="list-group-item list-group-item-text">
                                    <ul id="file" class="nav nav-tabs">
                                        <#list order.files>
                                            <#items as file>
                                                <#if file_index == 0>
                                                <li class="active">
                                                <#else>
                                                <li>
                                                </#if>
                                                    <a href="#file_${order_index}_${file_index}" data-toggle="tab">${file.name}</a>
                                                </li>
                                            </#items>
                                        </#list>
                                    </ul>
                                    <div id="tab_content_${order_index}" class="tab-content">
                                          <#list order.files>
                                                <#items as file>
                                                    <#if file_index == 0>
                                                    <div class="tab-pane fade in active" id="file_${order_index}_${file_index}">
                                                    <#else >
                                                    <div class="tab-pane fade" id="file_${order_index}_${file_index}">
                                                    </#if>
                                                        <div class="row">
                                                            <div class="col-lg-8">
                                                                <ul class="list-group">
                                                                    <li class="list-group-item list-group-item-success">
                                                                        <label for="page_${order_index}_${file_index}">页数：</label>
                                                                        <span id="page_${order_index}_${file_index}">${file.page}</span>
                                                                    </li>
                                                                    <li class="list-group-item list-group-item-success">
                                                                        <label for="copy_${order_index}_${file_index}">份数：</label>
                                                                        <span id="copy_${order_index}_${file_index}">${file.copy}</span>
                                                                    </li>
                                                                    <li class="list-group-item list-group-item-success">
                                                                        <label for="duplex_${order_index}_${file_index}">单双页：</label>
                                                                        <span id="duplex_${order_index}_${file_index}">${file.duplex}</span>
                                                                    </li>
                                                                    <li class="list-group-item list-group-item-success">
                                                                        <label for="color_${order_index}_${file_index}">色彩：</label>
                                                                        <span id="color_${order_index}_${file_index}">${file.color}</span>
                                                                    </li>
                                                                    <li class="list-group-item list-group-item-success">
                                                                        <label for="price_${order_index}_${file_index}">价格：</label>
                                                                        <span id="price_${order_index}_${file_index}">${file.price}</span>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                            <div class="col-lg-4">
                                                                <img style="padding: 10px" src="${file.cover}">
                                                                <a href="/document?document_id=${file.documentId}">
                                                                    预览
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </#items>
                                          </#list>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </#items>
            </#list>
            </div>
        </div>
    </div>
</div>
<#include "footer.ftl">