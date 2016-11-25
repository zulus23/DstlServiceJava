/**
 * Created by Gukov on 25.11.2016.
 */

var workTime = (function(){
    var dataSourceWorkTime = function(){
        return new kendo.data.DataSource({
        serverAggregates: true,
        transport: {
            read: function(options) {
                $.ajax({
                    type: "GET",
                    url: "api/worktimes",
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',

                            success: function(data) {
                                options.success(data);
                            }
            });
            },
            update: function(options){
                console.log(options);
                $.ajax({
                    type: "PATCH",
                    url: "api/worktime/"+options.data.id,
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    data:  kendo.stringify({
                        "id":options.data.id,
                        "name":options.data.name,
                        "startTime":moment(options.data.startTime).format("DD-MM-YYYY HH:mm"),
                        "endTime":moment(options.data.endTime).format("DD-MM-YYYY HH:mm"),
                        "workTime":options.data.workTime
                    }),
                    success: function(data) {
                        options.success(data);
                    }
                });
            },
            destroy: function(options) {
                $.ajax({
                    type: "DELETE",
                    url: "api/worktime/"+options.data.id,
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    data:  kendo.stringify({
                        "id":options.data.id,
                        "name":options.data.name,
                        "startTime":moment(options.data.startTime).format("DD-MM-YYYY HH:mm"),
                        "endTime":moment(options.data.endTime).format("DD-MM-YYYY HH:mm"),
                        "workTime":options.data.workTime
                    }),
                    success: function(data) {
                        options.success(data);
                    }
                });
            },
            create: function(options){
                console.log(options);
                $.ajax({
                    type: "POST",
                    url: "api/worktime",
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    data : kendo.stringify({
                        "id":options.data.id,
                        "name":options.data.name,
                        "startTime":moment(options.data.startTime).format("DD-MM-YYYY HH:mm"),
                        "endTime":moment(options.data.endTime).format("DD-MM-YYYY HH:mm"),
                        "workTime":options.data.workTime
                    }),
                    success: function(data) {
                        options.success(data);
                    }
                });
            }
            /* parameterMap: function (options, operation) {
             console.log(" ------------------------- ");
             if(operation !== 'read' && options){
             console.log(options.data);
             console.log(kendo.stringify(options.data));
             return endo.stringify(options.data);
             }
             }*/
        },

        //batch: true,
        schema: {
            model: {
                id:"id",

                fields: {
                    id:{type:"number",editable:false},
                    name: { type:"string" },
                    startTime: { type: "date" },
                    endTime:{type:"date"},
                    workTime:{type:"boolean"}
                }
            }
        },

        change: function(e) {
            var data = this.data();
            console.log(data.length); // displays "77"
        }


    })};
    var dataSourceWorkTimeReadOnly = function(){
        return new kendo.data.DataSource({
            serverAggregates: true,
            transport: {
                read: function(options) {
                    $.ajax({
                        type: "GET",
                        url: "api/worktimes",
                        contentType: "application/json; charset=utf-8",
                        dataType: 'json',

                        success: function(data) {
                            options.success(data);
                        }
                    });
                },


            },

            //batch: true,
            schema: {
                model: {
                    id:"id",

                    fields: {
                        id:{type:"number",editable:false},
                        name: { type:"string" },
                        startTime: { type: "date" },
                        endTime:{type:"date"},
                        workTime:{type:"boolean"}
                    }
                }
            },

           /* change: function(e) {
                var data = this.data();
                console.log(data.length); // displays "77"
            }
*/

        })};
    function timeEditor(container, options) {
        $('<input data-text-field="' + options.field + '" data-value-field="' + options.field + '" data-bind="value:' + options.field + '" data-format="' + options.format + '"/>')
            .appendTo(container)
            .kendoTimePicker({culture: "ru-RU"});
    }
    var gridWorkTimeView =  function(){
        return $("#gridWorkTimeView").kendoGrid({
        toolbar: ["create"],
        dataSource: dataSourceWorkTime(),
        editable: {
            mode: "inline",
            confirmation: false
        },
        //  height: '300px',
        sortable: true,


        dataBound: function() {
            $(".k-grid-myDelete span").addClass("k-icon k-delete");
        },
        cancel: function() {
            setTimeout(function(){
                $(".k-grid-myDelete span").addClass("k-icon k-delete");
            });
        },
        columns: [
            {
                field: "name",
                title: "Наименование",
                width: "250px",
                headerAttributes: gridUtils.headerFormat,
                attributes: gridUtils.columnFormat,
            },
            {
                field: "startTime",
                title: "Начало",
                width: "60px",
                headerAttributes: gridUtils.headerFormat,
                attributes: gridUtils.columnFormat,
                format:"{0:HH:mm}", editor: timeEditor


            },
            {
                field: "endTime",
                title: "Окончание",
                width: "60px",
                headerAttributes: gridUtils.headerFormat,
                attributes: gridUtils.columnFormat,
                format:"{0:HH:mm}", editor: timeEditor

            },
            {
                field: "workTime",
                title: "Рабочие время",
                width: "180px",
                headerAttributes: gridUtils.headerFormat,
                attributes: gridUtils.columnFormat,
                template: '<input type="checkbox" #= workTime ? "checked=checked" : "" # disabled="disabled" ></input>'


            },
            { command: ["edit", {
                name: "myDelete",
                text: "Удалить"
            }], title: "&nbsp;", width: "150px",
                headerAttributes: gridUtils.headerFormat,
                attributes: gridUtils.columnFormat,
            }
        ]
    }).data("kendoGrid");
    }
    var gridWorkTimeViewReadOnly =  function(){
        return $("#gridWorkTimeView").kendoGrid({

            dataSource: dataSourceWorkTime(),

            //  height: '300px',
            sortable: true,
            columns: [
                {
                    field: "name",
                    title: "Наименование",
                    width: "250px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                },
                {
                    field: "startTime",
                    title: "Начало",
                    width: "60px",
                    format:"{0:HH:mm}",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                },
                {
                    field: "endTime",
                    title: "Окончание",
                    width: "60px",
                    format:"{0:HH:mm}",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                },
                {
                    field: "workTime",
                    title: "Рабочие время",
                    width: "180px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    template: '<input type="checkbox" #= workTime ? "checked=checked" : "" # disabled="disabled" ></input>',


                }
            ]
        }).data("kendoGrid");
    }

    return {
        gridWorkTimeView:gridWorkTimeView,
        gridWorkTimeViewReadOnly:gridWorkTimeViewReadOnly
    }

})();