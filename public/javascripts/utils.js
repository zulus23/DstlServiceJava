/**
 * Created by Gukov on 18.10.2016.
 */

var utils = (
    function(){
        var dataSourceDB = new kendo.data.DataSource({
            transport: {
                read: function(options) {
                    $.ajax({
                        type: "GET",
                        url: "api/listservicedstl",
                        contentType: "application/json; charset=utf-8",
                        dataType: 'json',

                        success: function(data) {
                            options.success(data);
                        }
                    });
                }
            }
        });

        var createListServiceDstl =  function createListServiceDstl(){$("#enterprise").kendoDropDownList({
            dataTextField: "name",
            dataValueField: "id",
            dataSource: dataSourceDB,
            index: 0

        });
        };

        return {
            createListServiceDstl: createListServiceDstl
        }


    }

)()

