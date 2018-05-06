
<#include "header.ftl">
<div class="container">
    <div class="jumbotron" id="jumb">
        <h2 class="text-gradient">请选择要打印的文件类型</h2>
        <div class="select-div" id="typeSelectFile" data-toggle="modal" href="#documentModal">
            <p class="selecttext">文档</p>
            <img id="fileIcon" src="/images/folder-icon.png" height="100" width="100"/>
        </div>
        <#--<div class="select-div" id="typeSelectImg" data-toggle="modal" href="#documentModal">-->
            <#--<p class="selecttext">图片</p>-->
            <#--<img id="imgIcon" src="/images/folder-picture-icon.png" height="100" width="100"/>-->
        <#--</div>-->
    </div>
</div>

<div class="modal fade" id="documentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    请选择需要打印的文件
                </h4>
            </div>
            <div class="modal-body">
                <div id="success" class="alert alert-success  text-center" style="display: none"></div>
                <div id="notice" class="alert alert-warning  text-center" style="display: none"></div>
                <div id="warning" class="alert alert-danger  text-center" style="display: none"></div>
                <form id="document-form" class="form-horizontal" role="form">
                    <label for="documentInputFile">上传文件</label>
                    <input type="file" id="documentInputFile" name="file" accept=".doc, .docx, .ppt, .pptx, .xls, .xlsx, .wps, .pdf, .txt">
                    <p class="help-block" id="typeHelp"></p>
                    <div class="help-block">
                        <p id="first_file_block" style="display: none">
                            <span id="file_first" uuid = "1"></span>
                            <span class="close" id="delete_file_first">x</span>
                        </p>
                        <p id="second_file_block" style="display: none">
                            <span id="file_second" uuid = ""></span>
                            <span class="close" id="delete_file_second">x</span>
                        </p>
                        <p id="third_file_block" style="display: none">
                            <span id="file_third" uuid = ""></span>
                            <span class="close" id="delete_file_third">x</span>
                        </p>
                    </div>

                </form>
            </div>
            <img src="/images/5-121204193934-51.gif" hidden id="imgWait">
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" id="documentSubmit">
                    提交文件
                </button>
                <a href="/order/${orderUuid}">
                    <button type="button" class="btn btn-success" id="toOrder" style="display: none">
                        结算
                    </button>
                </a>

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<#include "footer.ftl">