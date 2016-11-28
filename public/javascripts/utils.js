/**
 * Created by Gukov on 18.10.2016.
 */

var utils = (

    function(){
        var notificationElement;

        var showNotification = function (message,type){
            if(!!!notificationElement){
                notificationElement = $("#notification");
                notificationElement.kendoNotification({
                    // hide automatically after 7 seconds
                    autoHideAfter: 7000,
                    // prevent accidental hiding for 1 second
                   // allowHideAfter: 1000,
                    // show a hide button
                    button: false,
                    // prevent hiding by clicking on the notification content
                    position: {
                        pinned:false,
                        // top: 20,
                        right:10,
                        left: $(window).width()- 150 ,
                    },

                    stacking: "up",
                    hideOnClick: false
                });
            }

            var notificationWidget = notificationElement.data("kendoNotification");
            notificationWidget.show(message,type);


        }




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
            createListServiceDstl: createListServiceDstl,
            showNotification:showNotification
        }


    }

)()

