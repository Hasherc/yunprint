<#include "header.ftl">
<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="well col-md-6" id="loginRegisterDiv" style="margin-top: 100px">
            <br>
            <div id="notice" class="alert alert-warning" style="display: none"></div>
            <form id="login" class="form-horizontal" role="form">
                <div class="form-group">
                    <label for="login_phoneNum" class="col-sm-2 control-label">
                        手机号
                    </label>
                    <div class="col-sm-10">
                        <input class="form-control" id="login_phoneNum" name="phoneNum"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="login_password" class="col-sm-2 control-label">
                        密码
                    </label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="login_password" name="password"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">

                            <label>
                                <input type="checkbox" id="remember"/>记住密码
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">

                        <button id="login_button" type="button" class="btn btn-default" onclick="sendLoginForm()">
                            登录
                        </button>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>


<#include "footer.ftl">