/**
 * Created by Zhukov on 25.11.2016.
 */


var helper = (function () {
    var enterpriseDropDownEditor =  function (container, options) {
        $('<input required name="' + options.field + '"/>')
            .appendTo(container)
            .kendoDropDownList({
                autoBind: false,
                dataTextField: "name",
                dataValueField: "id",
                dataSource: {
                    transport: {
                        read: function(options){
                            axios.get('/api/enterprises').then(function(response){
                                options.success(response.data);
                            });
                        }
                    }
                }
            });
    }

    return {
        enterpriseDropDownEditor:enterpriseDropDownEditor
    }


})()