[#ftl]
[#include "/_inc/inc.ftl"]
<!DOCTYPE html>
<html lang="zh">
<head>
[#include "/_inc/meta.ftl"]
[#include "/_inc/css.ftl"]

    <link href="${ctxPath}/resources/lib/bootstrap/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="${ctxPath}/resources/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctxPath}/resources/assets/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" href="${ctxPath}/resources/assets/css/bootstrap-select.min.css"/>
    <script type="text/javascript" src="${ctxPath}/resources/lib/jquery-1.12.4.min.js"></script>
    <script type="text/javascript"
            src="${ctxPath}/resources/lib/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${ctxPath}/resources/assets/js/bootstrap-select.min.js"></script>
    <script type="text/javascript"
            src="${ctxPath}/resources/assets/js/bootstrap-datetimepicker.min.js"></script>

    <style>
        #remark-row {
            margin-top: 20px;
        }

        .user-head-image {
            width: 50px !important;
        }

        #time-order-link {
            border: 1px solid #DDD;
            border-top-left-radius: 25px;
            border-bottom-left-radius: 25px;
        }

        #hot-order-link {
            border: 1px solid #DDD;
            border-top-right-radius: 25px;
            border-bottom-right-radius: 25px;
        }

        #remark-content {
            margin-top: 20px;
            margin-left: 5px;
            margin-right: 5px;
        }

        #remark-content .media {
            border-bottom: 1px solid #DDD;
        }

        .nav-tabs.nav-justified > .active > a,
        .nav-tabs.nav-justified > .active > a:focus,
        .nav-tabs.nav-justified > .active > a:hover {
            background-color: #3b71df;
            color: #FFF;
        }

        #user-select-col {
            padding-left: 0 !important;
        }
    </style>
</head>
<body>
[#include "head.ftl"]

<div class="admin-content">

[#if group??]
    <div class="row" id="group-info-row">
        <div class="media">
            <div class="media-left">
                [#if book??]
                    <a href="#">
                        <img class="media-object" src="${book.image}" alt="...">
                    </a>
                [/#if]
            </div>
            <div class="media-body">
                <h4 class="media-heading">${group.groupName}</h4>
                <p>${group.readSlogan}</p>
                <p>${group.groupDesc}</p>
                <p>
                    <button type="button" class="btn btn-success" data-toggle="modal"
                            data-target="#remarkModal">导出书评
                    </button>
                </p>
            </div>
        </div>
    </div>
[/#if]



[#if groupRemarks??]
<div class="row" id="remark-row">
    <ul class="nav nav-tabs nav-justified" id="remarkTab">
        <li class="active"><a href="#timeOrderList" id="time-order-link">按时间</a></li>
        <li><a href="#hotOrderList" id="hot-order-link">按热度</a></li>
    </ul>

    <div class="tab-content" id="remark-content">
        <div class="tab-pane active" id="timeOrderList">
            [#if groupRemarks.timeorderlist??]
                [#list groupRemarks.timeorderlist as userRemark]
                    <div class="media">
                        <div class="media-left">
                            <a href="#">
                                <img class="user-head-image"
                                     src="${userRemark.headImage}"/>
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">${userRemark.userName ? default("")}</h4>
                            <p>${userRemark.comment ? default("")}</p>
                            <p>
                                            <span class="replay">
                                                <i class="fa fa-comment"></i>&nbsp;${userRemark.totalReplay ? default(0)}
                                            </span>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                            <span class="like">
                                                <i class="fa fa-thumbs-up"></i>&nbsp;${userRemark.totalLike ? default(0)}
                                            </span>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                            <span class="create-time">
                                                <i class="fa fa-clock-o"></i>&nbsp;${userRemark.createTime ? string("yyyy-MM-dd HH:mm:ss")}
                                            </span>
                            </p>
                        </div>
                    </div>
                [/#list]
            [/#if]

        </div>
        <div class="tab-pane" id="hotOrderList">
            [#if groupRemarks.hotlist??]
                [#list groupRemarks.hotlist as userRemark]
                    <div class="media">
                        <div class="media-left">
                            <a href="#">
                                <img class="user-head-image"
                                     src="${userRemark.headImage}"/>
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">${userRemark.userName ? default("")}</h4>
                            <p>${userRemark.comment ? default("")}</p>
                            <p>
                                            <span class="replay">
                                                <i class="fa fa-comment"></i>&nbsp;${userRemark.totalReplay ? default(0)}
                                            </span>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                            <span class="like">
                                                <i class="fa fa-thumbs-up"></i>&nbsp;${userRemark.totalLike ? default(0)}
                                            </span>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                            <span class="create-time">
                                                <i class="fa fa-clock-o"></i>&nbsp;${userRemark.createTime ? string("yyyy-MM-dd HH:mm:ss")}
                                            </span>
                            </p>
                        </div>
                    </div>

                [/#list]
            [/#if]
        </div>
    </div>


    <div class="modal fade" id="remarkModal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">导出书评</h4>
                </div>
                <div class="modal-body">
                    <form action="/mark-backend/admin/remark/download" class="form-horizontal"
                          role="form" method="get">
                        <fieldset>

                            <div class="form-group">
                                <label for="userPicker" class="col-md-2 control-label">选择用户</label>
                                <div class="col-md-5" id="user-select-col">
                                    <select id="userPicker" data-style="btn-success" name="userId">
                                        <option value="-1">所有用户</option>
                                        [#if userList??]
                                            [#list userList as user]
                                                <option value="${user.id}">${user.nickname ? default("")}</option>
                                            [/#list]
                                        [/#if]
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="startTime" class="col-md-2 control-label">开始时间</label>
                                <div class="input-group date form_datetime col-md-5"
                                     data-link-field="startTime">
                                    <input class="form-control" size="16" type="text" value=""
                                           id="startTime" name="startTime"
                                           readonly>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="endTime" class="col-md-2 control-label">结束时间</label>
                                <div class="input-group date form_datetime col-md-5" data-date=""
                                     data-link-field="endTime">
                                    <input class="form-control" size="16" type="text" value=""
                                           id="endTime" name="endTime"
                                           readonly>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </fieldset>
                        <input type="hidden" name="groupId" value="${group.id}">
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success" data-toggle="">确定</button>
                            <button type="button" class="btn btn-primary" data-dismiss="modal">取消
                            </button>
                        </div>
                    </form>
                </div>


            </div>


        </div>
    </div>

    <script>
        $(function () {
            $('#remarkTab a:last').tab('show');//初始化显示哪个tab

            $('#remarkTab a').click(function (e) {
                e.preventDefault();//阻止a链接的跳转行为
                $(this).tab('show');//显示当前选中的链接及关联的content
            })
        });

        $('.form_datetime').datetimepicker({
                                               format: 'yyyy-mm-dd HH:mm:ss',
                                               todayBtn: 1,
                                               autoclose: true,
                                               todayHighlight: 1,
                                               startView: 2,
                                               startDate: "2016-04-01 00:00:00"
                                           });
        $('#userPicker').selectpicker({
                                          style: 'btn-success',
                                          size: 10
                                      });

    </script>


[/#if]


</div>
</body>
</html>
