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

        #condition-row {
            background-color: #EEE;
            text-align: center;
        }

        .head-jp {
            display: inline-block;
            height: 60px;
            width: 60px;
            background: url("${ctxPath}/resources/assets/imgs/jiangpai_1.png") center no-repeat;
            background-size: 60px 60px;
            margin-left: auto !important;
            margin-right: auto !important;
        }

        .user-top-panel {
            border: 2px solid #d9534f !important;
        }

        .user-top-panel .panel-heading {
            text-align: center;
            background-color: #d9534f;
            border-color: #d9534f;
            position: relative;
        }

        .user-top-panel .panel-heading .user-head-image {
            width: 25px !important;
            height: 25px !important;
            position: absolute;
            top: 20px;
            left: 105px;
        }

        #report-container {
            margin-top: 20px;
            background-color: #e7c3c3;
            padding: 10px;
            border: 2px solid #d9534f;
        }

        #reports-head-row {
            padding-left: 15px;
        }

        #reports-body-row {
            margin-top: 25px;
        }

        #reports-head-row ul {
            padding-left: 0 !important;
        }
    </style>
</head>
<body>
[#include "head.ftl"]

<div class="admin-content">

[#if group??]
    <div class="row" id="condition-row">
        <form action="/mark-backend/admin/remark/reports" class="form-inline"
              role="form" method="get">
            <div class="form-group">
                <label for="startTime" class="control-label">开始时间</label>
                <div class="input-group date form_datetime"
                     data-link-field="startTime">
                    <input class="form-control" size="16" type="text" value=""
                           id="startTime" name="startTime"
                           readonly>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
            <div class="form-group">
                <label for="endTime" class="control-label">结束时间</label>
                <div class="input-group date form_datetime" data-date=""
                     data-link-field="endTime">
                    <input class="form-control" size="16" type="text" value=""
                           id="endTime" name="endTime"
                           readonly>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
            <input type="hidden" name="groupId" value="${group.id}">
            <button type="submit" class="btn btn-success" data-toggle="">查看</button>
        </form>
    </div>
[/#if]
    <div class="row" id="report-container">
        <div class="row" id="reports-head-row">
            <div class="media">
                <div class="media-left">
                [#if book??]
                    <a href="#">
                        <img class="media-object" src="${book.image}" alt="...">
                    </a>
                [/#if]
                </div>
                <div class="media-body">
                    <h3 class="media-heading">${group.groupName}&nbsp;成长报告</h3>
                    <ul>
                        <li>
                        [#if totalUsers??]
                            <h4>总用户数: ${totalUsers}</h4>
                        [/#if]
                        </li>
                        <li>
                        [#if totalRemarks??]
                            <h4>书评条数: ${totalRemarks}</h4>
                        [/#if]
                        </li>
                        <li>
                        [#if totalUsersRemarked??]
                            <h4>总打卡人数: ${totalUsersRemarked}</h4>
                        [/#if]
                        </li>
                        <li>
                        [#if totalCharacters??]
                            <h4>总字符数:${totalCharacters}</h4>
                        [/#if]
                        </li>
                    </ul>
                </div>
            </div>
        </div>


        <div class="row" id="reports-body-row">
        [#if hotOrderTopThree??]
            [#list hotOrderTopThree as remark]
                <div class="col-md-4">
                    <div class="panel panel-success user-top-panel">
                        <div class="panel-heading">
                            <span class="head-jp"></span>
                        <span class="head-img"><img class="user-head-image img-circle"
                                                    src="${remark.headImage}"/></span>
                        </div>
                        <div class="panel-body">
                        <span class="username">
                        <h4>${remark.userName}</h4>
                        </span>
                        <span class="zan-pl">
                            <h5>点赞+评论: ${remark.totalLike + remark.totalReply}</h5>
                        </span>
                        <span class="comment">
                        ${remark.comment}
                        </span>
                        </div>

                    </div>
                </div>
            [/#list]
        [/#if]

        </div>


        <div class="row" id="group-info-row">


        </div>
    </div>


    <script>

        $('.form_datetime').datetimepicker({
                                               format: 'yyyy-mm-dd HH:mm:ss',
                                               todayBtn: 1,
                                               autoclose: true,
                                               todayHighlight: 1,
                                               startView: 2,
                                               startDate: "2016-04-01 00:00:00"
                                           });
    </script>


</div>
</body>
</html>
