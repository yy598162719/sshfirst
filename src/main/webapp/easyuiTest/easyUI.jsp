<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/4
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- 引入easyUI的CSS和JS -->
    <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="themes/icon.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="locale/easyui-lang-zh_CN.js"></script>
    <style type="text/css">
        .menuA {
            text-decoration: none;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $(".menuA").click(function () {
                var textContent = this.innerHTML;
                var url = this.href;
                // alert(textContent);
                // 编写函数创建选项卡:
                createTabs(textContent, url);
                // 超链接就不跳转
                return false;
            });
        });

        // 创建选项卡的函数:
        function createTabs(textContent, url) {
            // 判断选项卡是否存在:
            var flag = $("#tt").tabs("exists", textContent);
            if (flag) {
                // 如果已经存在，让其被选中
                $("#tt").tabs("select", textContent);
            } else {
                // 如果不存在,创建新的选项卡
                $('#tt').tabs('add', {
                    title: textContent,
                    content: createUrl(url),
                    closable: true
                });
            }
        }

        function createUrl(url) {
            return "<iframe src='" + url + "' style='width:100%;height:95%;border:none;'></iframe>";
        }
    </script>
</head>
<body>
<div id="cc" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',title:'CRM管理系统',split:true" style="height:100px;"></div>

    <div data-options="region:'west',title:'系统菜单',split:true,iconCls:'icon-reload'" style="width:200px;">
        <div class="easyui-accordion" data-options="fit:true">
            <div title="客户管理" data-options="iconCls:'icon-ok',selected:true" style="overflow:auto;padding:10px;">
                <a href="customer.html" class="menuA">客户管理</a>
            </div>
            <div title="联系人管理" data-options="iconCls:'icon-help'" style="padding:10px;">
                <a href="linkman.html" class="menuA">联系人管理</a>
            </div>
            <div title="拜访记录管理" data-options="iconCls:'icon-search'" style="padding:10px;">
                <a href="salevisit.html" class="menuA">拜访记录管理</a>
            </div>
            <div title="统计分析管理" data-options="iconCls:'icon-search'" style="padding:10px;">
                <a href="#" class="menuA">统计分析管理</a>
            </div>
            <div title="系统管理" data-options="iconCls:'icon-search'" style="padding:10px;">
                <a href="#" class="menuA">系统管理</a>
            </div>
        </div>
    </div>

    <div data-options="region:'center'" style="padding:5px;background:#eee;">
        <div id="tt" class="easyui-tabs" data-options="fit:true">
            <div title="数据区域" style="padding:20px;display:none;">
                欢迎！
            </div>
        </div>
    </div>
</div>
</body>

</html>
