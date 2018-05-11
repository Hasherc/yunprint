<#include "header.ftl">
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 chart-plot-background" style="margin-top: 100px">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">个人信息</h3>
                </div>
                <div class="panel-body">
                    <table class="table table-bordered">
                        <tbody>
                            <tr>
                                <td>姓名</td>
                                <td>${user.nickName}</td>

                            </tr>
                            <tr>
                                <td>手机号</td>
                                <td>${user.phoneNum}</td>
                            </tr>
                            <tr>
                                <td>邮箱</td>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <td>余额</td>
                                <td id="balance">${user.balance}</td>
                            </tr>
                        </tbody>
                    </table>
                    <button class="button bg-info" id="addBalance" onclick="addBalance()">充值</button>
                    <input class="input-sm" id="money" type="number"  value="0">
                </div>
            </div>
        </div>
    </div>
</div>
<#include "footer.ftl">