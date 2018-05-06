<div class="panel-group" id="accordion">
    <#list orders>
        <#items as order>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion"
                           href="#${order.uuid}">
                            ${file.name}
                        </a>
                    </h4>
                </div>
                <div id="${order.uuid}" class="panel-collapse collapse in">
                    <div class="panel-body">
                        Nihil anim keffiyeh helvetica, craft beer labore wes anderson
                        cred nesciunt sapiente ea proident. Ad vegan excepteur butcher
                        vice lomo.
                    </div>
                </div>
            </div>
        </#items>
    </#list>


</div>