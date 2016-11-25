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
})();