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

    <style>
        #condition-container {
            background-color: #EEE;
            text-align: center;
            width: 100%;
            margin-left: auto;
            margin-right: auto;
        }

        #black-reports-container,
        #red-reports-container {
            margin-top: 20px;
            width: 100%;
            margin-left: auto;
            margin-right: auto;
        }

        #red-reports-container {
            background-color: #FEF8EF;
            border: 1px solid #d87c7c;
        }

        #black-reports-container {
            border: 1px solid #4a4a4a;
        }

        .row.head {
            text-align: center;
            color: #fff;
        }

        #red-reports-container .row.head {
            background: #d87c7c;
        }

        #black-reports-container .row.head {
            background: #4a4a4a;
        }

        .book-image {
            width: 100px !important;
            height: 150px !important;
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
            height: 400px;
            overflow-y: hidden;
            border: 1px solid #d87c7c !important;
        }

        .user-top-panel .panel-heading {
            text-align: center;
            background-color: #d87c7c;
            border-color: #d87c7c;
            position: relative;
        }

        .user-top-panel .panel-body {
            height: 300px;
            overflow-y: scroll;
        }

        .user-top-panel .panel-heading .user-head-image {
            width: 25px !important;
            height: 25px !important;
            position: absolute;
            top: 20px;
            left: 100px;
        }

        #head-summary-col {
            background-color: #FEF8EF;
        }

        #reports-body-row {
            margin-top: 2px;
        }

        #reports-head-row ul {
            padding-left: 0 !important;
        }

        #user-and-punch-pie {
            height: 150px;;
        }

        #hot-punch-zhuzhuang {
            height: 200px;
        }

        #continue-punch-zhu {
            height: 200px;
        }

        #not-punch-dates-zhu {
            width: 100%;
            height: 400px;
        }

        #punch-dates-card {
            width: 100%;
            height: 400px;
        }

    </style>
</head>
<body>
[#include "head.ftl"]

<div class="admin-content">

[#if group??]
    <div class="container" id="condition-container">
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

    <div class="container" id="red-reports-container">

        <div class="row head">
            <h3>${group.groupName}&nbsp;</h3>
        </div>

        <div class="row" id="report-container">

            <div class="col-md-6" id="head-summary-col">
                <div class="media">
                    <div class="media-left">
                    [#if book??]
                        <a href="#">
                            <img class="media-object book-image" src="${book.image}" alt="...">
                        </a>
                    [/#if]
                    </div>
                    <div class="media-body">
                        <ul>
                            <li>
                            [#if totalUsers??]
                                <h5>总用户数量: <span class="label label-primary">${totalUsers}</span></h5>
                            [/#if]
                            </li>
                            <li>
                            [#if totalRemarks??]
                                <h5>总书评条数: <span class="label label-danger">${totalRemarks}</span></h5>
                            [/#if]
                            </li>
                            <li>
                            [#if totalUsersRemarked??]
                                <h5>总打卡人数: <span class="label label-warning">${totalUsersRemarked}</span>
                                </h5>
                            [/#if]
                            </li>
                            <li>
                            [#if totalCharacters??]
                                <h5>总字符数量: <span class="label label-success">${totalCharacters}</span></h5>
                            [/#if]
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-6" id="user-and-punch-pie">
            </div>
        </div>
        <div class="row" id="reports-body-row">
            <div class="col-md-6" id="hot-punch-zhuzhuang">
            </div>
            <div class="col-md-6" id="continue-punch-zhu">
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

    </div>

    <div class="container" id="black-reports-container">

        <div class="row head">
            <h3>${group.groupName}&nbsp;</h3>
        </div>

        <div class="row">
            <div id="not-punch-dates-zhu"></div>
        </div>

        <div class="row">
            <div id="punch-dates-card"></div>
        </div>

    </div>


    <script type="text/javascript" src="${ctxPath}/resources/lib/jquery-1.12.4.min.js"></script>
    <script type="text/javascript"
            src="${ctxPath}/resources/lib/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${ctxPath}/resources/assets/js/bootstrap-select.min.js"></script>
    <script type="text/javascript"
            src="${ctxPath}/resources/assets/js/bootstrap-datetimepicker.min.js"></script>
    <script src="${ctxPath}/resources/lib/echarts.min.js"></script>
    <script src="${ctxPath}/resources/lib/vintage.js"></script>
    <script src="${ctxPath}/resources/lib/roma.js"></script>
    <script>

        console.log(${totalUsers});

        $('.form_datetime').datetimepicker({
                                               format: 'yyyy-mm-dd HH:mm:ss',
                                               todayBtn: 1,
                                               autoclose: true,
                                               todayHighlight: 1,
                                               startView: 2,
                                               startDate: "2016-04-01 00:00:00"
                                           });
    </script>


    <script>
        var userPunchPieChart = echarts.init(
                document.getElementById('user-and-punch-pie'), 'vintage');

        var userPunchPieOption = {
            title: {
                text: '打卡用户分布',
                subtext: '总用户数目 ' +${totalUsers},
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['未打卡人数', '打卡人数']
            },
            series: [
                {
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: [
                        {value:${totalUsers} - ${totalUsersRemarked}, name: '未打卡'},
                        {value:${totalUsersRemarked}, name: '打卡'}
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        userPunchPieChart.setOption(userPunchPieOption);
    </script>

    <script>
        var hotPunchZhuzhuang = echarts.init(
                document.getElementById('hot-punch-zhuzhuang'), 'vintage');

        var remarkUsers = [];
        var remarkUsersPingLun = [];
        var remarkUsersLikes = [];


        [#if hotOrderTopThree??]
            [#list hotOrderTopThree as remark]
            remarkUsers.push("${remark.userName}");
            remarkUsersPingLun.push(${remark.totalReply});
            remarkUsersLikes.push(${remark.totalLike});
            [/#list]
        [/#if]
        var hotPunchOption = {
            title: {
                text: '点赞评论数Top3',
                x: 'center'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['评论数目', '点赞数目']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: remarkUsers
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '评论数目',
                    type: 'bar',
                    data: remarkUsersPingLun
                },
                {
                    name: '点赞数目',
                    type: 'bar',
                    data: remarkUsersLikes
                }
            ]
        };
        hotPunchZhuzhuang.setOption(hotPunchOption);
    </script>

    <script>
        var continuePunchZhu = echarts.init(
                document.getElementById('continue-punch-zhu'), 'vintage');

        var continuePunchUsers = [];
        var continuePunchTimes = [];



        [#if continuePunchMap??]
            [#list continuePunchMap?keys as name]
            continuePunchUsers.push("${name}");
            continuePunchTimes.push(${continuePunchMap[name]});
            [/#list]
        [/#if]
        var continuePunchOption = {
            title: {
                text: '连续打卡Top5',
                x: 'center'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },

            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: continuePunchUsers
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '连续打卡天数',
                    type: 'bar',
                    data: continuePunchTimes
                }
            ]
        };
        continuePunchZhu.setOption(continuePunchOption);
    </script>

    <script>
        var notPunchZhu = echarts.init(
                document.getElementById('not-punch-dates-zhu'), 'roma');

        var notPunchUsers = [];
        var notPunchTimes = [];



        [#if notPunchDatesMap??]
            [#list notPunchDatesMap?keys as name]
            notPunchUsers.push("${name}");
            notPunchTimes.push(${notPunchDatesMap[name]?size});
            [/#list]
        [/#if]
        var notPunchDatesOption = {
            title: {
                text: '打卡次数最少Top5',
                subtext: '在统计时间段内打卡次数最少的用户',
                x: 'center'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            color:["#0066ff"],
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                data: notPunchUsers
            },
            series: [
                {
                    name: '未打卡次数',
                    type: 'bar',
                    data: notPunchTimes
                }
            ]
        };
        notPunchZhu.setOption(notPunchDatesOption);
    </script>


    <script>

        var punchCardChart = echarts.init(
                document.getElementById('punch-dates-card'), 'roma');

        var users = [];
        var duarDates = [];
        var usersNotPunchDates = [];



        [#if notPunchDatesMap??]
            [#list notPunchDatesMap?keys as name]
            users.push("${name}");
            notPunchDates = [];
                [#list notPunchDatesMap[name] as notPunchDate]
                notPunchDates.push("${notPunchDate}");
                [/#list]
            usersNotPunchDates.push(notPunchDates);
            [/#list]
        [/#if]

        [#list duaritionDates as day]
        duarDates.push("${day}");
        [/#list]

        var data = [];

        for (var i = 0; i < users.length; i++) {
            var find = false;
            for (var j = 0; j < duarDates.length; j++) {
                console.log(duarDates[j] + ',' + usersNotPunchDates[i]);

                for (var k = 0; k < usersNotPunchDates[i].length; k++) {
                    if (usersNotPunchDates[i][k] == duarDates[j]) {
                        find = true;
                        break;
                    }
                }
                if (find) {
                    data.push([j, i, 0]);
                } else {
                    data.push([j, i, 1]);
                }
                find = false;
            }
        }

        var punchCardOptions =
                option = {
                    title: {
                        text: '打卡详细情况',
                        subtext: '未打卡次数最多的人打卡分布情况',
                        x: 'center'
                    },
                    legend: {
                        data: ['打卡与否'],
                        left: 'right'
                    },
                    tooltip: {
                        position: 'top',
                        formatter: function (params) {
                            if (params.value[2] === 1) {
                                return params.name + ' 打卡了';
                            } else {
                                return params.name + ' 没有打卡';
                            }
                        }
                    },
                    grid: {
                        left: 2,
                        bottom: 10,
                        right: 20,
                        containLabel: true
                    },
                    xAxis: {
                        type: 'category',
                        data: duarDates,
                        boundaryGap: false,
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: '#4a4a4a',
                                type: 'dashed'
                            }
                        },
                        axisLine: {
                            show: true
                        }
                    },
                    yAxis: {
                        type: 'category',
                        data: users,
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: '#ddd',
                                type: 'dashed'
                            }
                        },
                        axisLine: {
                            show: true
                        }
                    },
                    series: [{
                        name: '打卡',
                        type: 'effectScatter',
                        symbolSize: function (val) {
                            return (val[2] + 1) * 10;
                        },
                        data: data
                    }]
                };

        punchCardChart.setOption(punchCardOptions);
    </script>


</div>
</body>
</html>
