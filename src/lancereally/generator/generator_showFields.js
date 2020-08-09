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
     * 代码生成器常量
     */
    var Generator = {
        entity: 'generator'
    };

    // 获取当前弹出框索引
    var layerIdx = admin.getCurLayerId('#fieldTable');
    // 获取弹窗传递的参数
    var openData = admin.getCurLayerData(layerIdx);

    /**
     * var entity
     * 定义请求的字段(Field)对象实体
     */
    var Fields = {
        elem: '#fieldTable',
        url: Generator.entity+'/fields?dbId=' + openData.dbId + '&tableName=' + openData.tableName,
        method: 'GET',
        height: 350,
        // where: {dbId: dbId, tableName: tableName},
        page: false,
        // toolbar: 'ToolBar',
        defaultToolbar: false,
        cols: [[
            {field: 'camelFieldName', sort: true, title: '字段名'},
            {field: 'columnComment', sort: true, title: '字段注释'},
            //template 和 toolbar， 列和行的区别
            {align: 'center', toolbar: '#fieldCheck', title: '是否作为查询条件', minWidth: 200},
            {align: 'center', templet: '#fieldSelect', title: '字段属性样式', minWidth: 200}
        ]],
        done: function (res) {
            //解决下拉框无法溢出
            $("select#f-select").parents(".layui-table-cell").css("overflow", "visible");
            form.render("select");

            var fieldList = res.data;
            //条件和属性样式更改
            form.on('select(f-select)', function (obj) {
                var index = $(obj.elem).parents("tr").attr("data-index");
                fieldList[index].inputType = obj.value;
                table.cache.fieldTable[index].inputType = obj.value;
            })

            // 注册下拉列表
            form.on("select(inputType)", function (data) {

            });

            // 表单提交事件
            form.on('submit(sessionGo)', function (res) {
                var desStr = $(".layui-form-checked").parents(".layui-table-col-special").prev().text();
                for (var i = 0; i < fieldList.length; i++) {
                    if (desStr.indexOf(fieldList[i].columnComment) != -1){
                        fieldList[i].queryConditionFlag = true;
                    }
                }
                console.log(fieldList);
                debugger;
                var tableNow = {
                    fieldList: JSON.stringify(fieldList),
                    tableName: openData.tableName
                };
                admin.req('generator/session', tableNow
                    , function (res) {
                        if (res) {
                            layer.msg("操作成功", {icon: 1});
                            console.log(res);
                            setTimeout(function () {
                                layer.close(layerIdx);
                            }, 1000)
                        }

                    }, 'post');
            });
        },
        parseData: function (res) {
            return {
                // id: res.id,
                code: res.code || 0,
                data: res
            }
        }
    };
    table.render(Fields);
    element.render();
})
