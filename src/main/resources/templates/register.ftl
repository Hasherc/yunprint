<#include "header.ftl">
<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="well col-md-6" id="loginRegisterDiv">
            <br>
            <div id="notice" class="alert alert-warning" style="display: none" id="userNotice">
            </div>
            <form id="register" class="form-horizontal" role="form">
                <div class="form-group">
                    <label for="register_name" class="col-sm-2 control-label">
                        姓名
                    </label>
                    <div class="col-sm-6" data-container="body" data-toggle="popover" data-placement="right"
                         data-trigger="focus"
                         data-content="请输入您的真实姓名">
                        <input class="form-control" id="register_name" name="nickName"/>
                    </div>


                </div>
                <div class="form-group">
                    <label for="register_phoneNum" class="col-sm-2 control-label">
                        手机号
                    </label>
                    <div class="col-sm-6" data-container="body" data-toggle="popover" data-placement="right"
                         data-trigger="focus"
                         data-content="请输入您的13位手机号码">
                        <input class="form-control" id="register_phoneNum" name="phoneNum"/>
                    </div>

                </div>
                <div class="form-group">
                    <label for="register_password" class="col-sm-2 control-label">
                        密码
                    </label>
                    <div class="col-sm-6" data-container="body" data-toggle="popover" data-placement="right"
                         data-trigger="focus"
                         data-content="以字母开头，长度在6~18之间，只能包含字符、数字和下划线">
                        <input type="password" class="form-control" id="register_password" name="password"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="repeat_password" class="col-sm-2 control-label">
                        再次输入密码
                    </label>
                    <div class="col-sm-6" data-container="body" data-toggle="popover" data-placement="right"
                         data-trigger="focus"
                         data-content="再次输入密码以确认">
                        <input type="password" class="form-control" id="repeat_password" name="password"/>
                    </div>

                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">

                        <button id="register_button" type="button" class="btn btn-default" onclick="sendRegisterForm()">
                            注册
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<#include "footer.ftl">