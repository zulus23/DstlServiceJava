/**
 * Created by Zhukov on 25.11.2016.
 */

var normTimeLoad = (function() {

    var token;
    userService.token(function (response) {
        token = response;
    });
    function setHeaderRequest(request) {

        if (!!token) {
            request.setRequestHeader("token", token);
        }
    }

    var dataSourceDB = function () {
        return new kendo.data.DataSource({
            serverAggregates: true,
            transport: {
                read: function (options) {
                    $.ajax({
                        type: "GET",
                        url: "api/normloads",
                        contentType: "application/json; charset=utf-8",
                        //beforeSend: setHeaderRequest,
                        dataType: 'json',

                        success: function (data) {
                            options.success(data);
                        }
                    });
                },
                update: function (options) {
                    $.ajax({
                        type: "PATCH",
                        url: "api/normload/" + options.data.id,
                        contentType: "application/json; charset=utf-8",
                        dataType: 'json',
                        beforeSend: setHeaderRequest,
                        data: kendo.stringify(options.data),
                        success: function (data) {
                            options.success(data);
                        },
                        error: function (request, status, error) {

                            $("#gridNormTimeView").data("kendoGrid").dataSource.fetch();

                        }
                    });
                },
                destroy: function (options) {
                    $.ajax({
                        type: "DELETE",
                        url: "api/normload/" + options.data.id,
                        contentType: "application/json; charset=utf-8",
                        beforeSend: setHeaderRequest,
                        dataType: 'json',
                        data: kendo.stringify(options.data),
                        success: function (data) {
                            options.success(data);
                        },
                        error: function (request, status, error) {

                            $("#gridNormTimeView").data("kendoGrid").dataSource.fetch();

                        }
                    });
                },
                create: function (options) {
                    console.log(options);
                    $.ajax({
                        type: "POST",
                        url: "api/normload",
                        contentType: "application/json; charset=utf-8",
                        dataType: 'json',
                        beforeSend: setHeaderRequest,
                        data: kendo.stringify(options.data)
                        ,
                        success: function (data) {
                            options.success(data);
                        },
                        error: function (request, status, error) {
                            $("#gridNormTimeView").data("kendoGrid").dataSource.fetch();

                        }
                    });
                }
            },
            //batch: true,
            schema: {
                model: {
                    id: "id",

                    fields: {
                        id: {type: "number", editable: false},
                        enterprise: {defaultValue: {id: 0, name: ''}},
                        packageTime: {type: "number"},
                        commissionTime: {type: "number"},
                        placerTime: {type: "number"}
                    }
                }
            },


        });
    }
    var gridNormTimeLoad = function () {
        return $("#gridNormTimeView").kendoGrid({
            toolbar: ["create"],
            dataSource: dataSourceDB(),
            editable: {
                mode: "inline",
                confirmation: false
            },
            sortable: true,


            dataBound: function () {
                $(".k-grid-myDelete span").addClass("k-icon k-delete");
            },
            cancel: function () {
                setTimeout(function () {
                    $(".k-grid-myDelete span").addClass("k-icon k-delete");
                });
            },
            columns: [
                {
                    field: "enterprise",
                    title: "Предприятие",
                    width: "70px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    editor: helper.enterpriseDropDownEditor, template: "#=enterprise.name#"

                },
                {
                    field: "packageTime",
                    title: "Пакет",
                    width: "60px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,

                },
                {
                    field: "commissionTime",
                    title: "Комиссионная отгрузка",
                    width: "60px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,

                },
                {
                    field: "placerTime",
                    title: "Россыпь",
                    width: "180px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,

                },
                {
                    command: ["edit", {
                        name: "myDelete",
                        text: "Удалить"
                    }], title: "&nbsp;", width: "350px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                }
            ]
        }).data("kendoGrid");
    }
    function dataSourceDBReadOnly() {
        return new kendo.data.DataSource({

            transport: {
                read: function (options) {
                    $.ajax({
                        type: "GET",
                        url: "api/normloads",
                        contentType: "application/json; charset=utf-8",
                        //beforeSend: setHeaderRequest,
                        dataType: 'json',

                        success: function (data) {
                            options.success(data);
                        }
                    });
                },

            },
            //batch: true,
            schema: {
                model: {
                    id: "id",

                    fields: {
                        id: {type: "number", editable: false},
                        enterprise: {defaultValue: {id: 0, name: ''}},
                        packageTime: {type: "number"},
                        commissionTime: {type: "number"},
                        placerTime: {type: "number"}
                    }
                }
            },


        });
    }
    var gridNormTimeLoadReadOnly = function () {
        return $("#gridNormTimeView").kendoGrid({

            dataSource: dataSourceDBReadOnly(),
             sortable: true,
            columns: [
                {
                    field: "enterprise",
                    title: "Предприятие",
                    width: "70px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    editor: helper.enterpriseDropDownEditor, template: "#=enterprise.name#"

                },
                {
                    field: "packageTime",
                    title: "Пакет",
                    width: "60px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,

                },
                {
                    field: "commissionTime",
                    title: "Комиссионная отгрузка",
                    width: "60px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                },
                {
                    field: "placerTime",
                    title: "Россыпь",
                    width: "180px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,

                },

            ]
        }).data("kendoGrid");
    }



    return {
        dataSourceDB:dataSourceDB,
        gridNormTimeLoad:gridNormTimeLoad,
        gridNormTimeLoadReadOnly:gridNormTimeLoadReadOnly
    }

})();