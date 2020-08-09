layui.use(['layer', 'form', 'table', 'util', 'adminPro', 'element', 'zTree', 'index'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var util = layui.util;
    var admin = layui.adminPro;
    var element = layui.element;
    var config = layui.config;
    var index = layui.index;

    /**
     *代码生成器常量
     */
    var Generator = {
        entity: 'generator'
    };

    var dbArr = [];
    var dbNow = null;
    /**
     * req
     *请求数据源列表刷新下拉框
     */
    admin.req(Generator.entity+'/source', "", function (res) {
        layer.closeAll('loading');
        if (res) {
            for (var i = 0; i < res.data.length; i++) {
                $("#dbSource").append("<option value=\"" + i + "\">" + res.data[i].dbName + "</option>");
                dbArr.push(res.data[i]);
            }
            //重新渲染
            layui.form.render("select");

            //数据源绑定点击事件
            //元素(jquery绑定)&状态(lay-filter绑定)
            $(".layui-unselect").attr({"type":"select","lay-filter":"dbSourceOption"});
            form.on('select(dbSourceOption)', function () {
                var index = $(this).attr("lay-value");
                var param = dbArr[index];
                dbNow = param;
                delete param.createTime;
                getForms(dbNow.dbId);
            })
        } else {
            layer.msg(res.message);
        }
    }, 'get');

    /**
     * jQuery.click
     *重置按钮绑定事件
     */
    $("#dataEmpty").click(function () {
        $("#tableForm")[0].reset();
        getForms(0);

        /**
         * reload空请求方法，where不能为{},否则继承上次reload的参数
         * */
        // table.reload('lay-filter' ,{
        //     url: '',
        //     where: null
        // })
    })

    /**
     * form.on
     * 生成代码 表单提交事件
     */
    form.on('submit(codeSubmit)', function (data) {
        // layer.load(2);
        data.field.dbSource = dbNow;
        data.field.layTableCheckbox = "";
        var tableList = $(".layui-form-checked").parents("td").next();
        var length = tableList.length;
        if (length == 1){
            data.field.layTableCheckbox += tableList[0].textContent;
            downloadFile(data);
        }else if(length > 1){
            for (var i = 0; i < length; i++){
                if (i!=(length-1))
                    data.field.layTableCheckbox += (tableList[i].textContent+",");
                else
                    data.field.layTableCheckbox += (tableList[i].textContent)
            }
            downloadFile(data);
        }else{
            layer.msg("您需要选择至少一个表项");
            // setTimeout(function(){layer.closeAll()},2000);
        }
        //拒绝刷新
        return false;
    });

    /**
     * table.on
     *工具条点击事件
     */
    table.on('tool(Tables)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'edit') { // 修改
            showEditModel(data);
        } else if (layEvent === 'del') { // 待用
        }
    });

    /**
     * table.on
     *复选框点击事件
     */
    table.on('checkbox(Tables)', function (obj) {
        var display;
        if (obj.checked){
            display = "inline-block";
        }else{
            display = "none";
        }

        if (obj.type === "all") {
            $("table").find(".layui-btn").css("display", display);
        } else {
            $(obj.tr[0]).find(".layui-btn").css("display", display)
        }
    });

    /**
     * function
     * 代码下载请求， 使用window方法
     */
    function downloadFile(data){
        var tableNames = data.field.layTableCheckbox;
        var author = data.field.authorName;
        var proPackage = data.field.path;
        var dbId = dbNow.dbId;
        var codeWindow = window.open('_blank');
        codeWindow.location = "/"+Generator.entity+"/code?tableList="+tableNames+"&author="+author+"&proPackage="+proPackage+"&dbId="+dbId;
        layer.close();
    }

    /**
     * function
     *获取数据源表格
     */
    function getForms(dbId) {
        var Forms = {
            elem: 'Table',
            url: Generator.entity+'/tables?dbId='+dbId,
            method: 'GET',
            page: false,
            // toolbar: 'ToolBar',
            defaultToolbar: false,
            cols: [[
                {type: 'checkbox'},
                {field: 'tableName', sort: true, title: '表名称'},
                {field: 'tableComment', sort: true, title: '表描述'},
                {align: 'center', toolbar: '#tableBarRole', title: '操作', minWidth: 200}

            ]],
            done: function (res) {
                if (dbId != "0")
                layer.msg('已获取表列');
            },
            parseData: function (res) {
                return {
                    // id: res.id,
                    code: res.code || 0,
                    // msg: res.message || '获取成功',
                    // count: res.data.total,
                    data: res
                }
            }
        };
        $(".layui-table-view").remove();
        table.render(Forms);
    }


    /**
     * function
     * 显示字段配置弹窗
     */
    function showEditModel(data) {
        dbNow.tableName = data.tableName;
        admin.open({
            offset: 't',
            title: '配置字段',
            area: ["900px", "500px"],
            shade: [0.8, '#393D49'],
            // orderType: (mRole ? 0 : 1),
            url: window.snpit.appModuleAbs+Generator.entity+'/'+'generator_showFields.html',
            data: {dbId: dbNow.dbId, tableName: data.tableName},
            success: function () {
               }
        });
    }
    form.render();
});
//保证拆分后弹窗JS可用
