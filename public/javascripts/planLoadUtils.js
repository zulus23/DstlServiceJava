/**
 * Created by Gukov on 23.11.2016.
 */


var planLoadUtils = (function () {
    var factLoadGridView = function () {


        return $("#factLoadGridView").kendoGrid({
            // toolbar: ["edit"],
            //dataSource: planShipmentUtil.dataSourcePlanDay(),
            // noRecords: true,
            height: '100%',
            groupable: true,
            sortable: true,
            filterable: true,
            resizable: true,
            // selectable:"true",
            columnMenu: true,
            editable: true,
            //change:onChange,
            /*dataBound: function (e) {
             $("#gridView").find('.k-icon.k-i-collapse').trigger('click');
             },*/

            columns: [

                {
                    //field: "driverTransportCompany",
                    title: "Гос. номер ТС",
                    width: "80px",
                    filterable: false,
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    //  template: "#=planShipmentUtil.isNotNull(driverTransportCompany) ? driverTransportCompany.numberTruck : ''#"
                },
                {
                    //field: "driverTransportCompany",
                    title: "Прибытие ТС",
                    width: "80px",
                    filterable: false,
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    //  template: "#=planShipmentUtil.isNotNull(driverTransportCompany) ? driverTransportCompany.numberTruck : ''#"
                },
                {
                    //field: "driverTransportCompany",
                    title: "Начало погрузки",
                    width: "80px",
                    filterable: false,
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    //  template: "#=planShipmentUtil.isNotNull(driverTransportCompany) ? driverTransportCompany.numberTruck : ''#"
                },
                {
                    //field: "driverTransportCompany",
                    title: "Окончание погрузки",
                    width: "80px",
                    filterable: false,
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    //  template: "#=planShipmentUtil.isNotNull(driverTransportCompany) ? driverTransportCompany.numberTruck : ''#"
                },
                {
                    //field: "driverTransportCompany",
                    title: "Убытие",
                    width: "80px",
                    filterable: false,
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    //  template: "#=planShipmentUtil.isNotNull(driverTransportCompany) ? driverTransportCompany.numberTruck : ''#"
                },
                {
                    field: "timeToLoad",
                    title: "Норма погрузки",
                    width: "80px",
                    filterable: false,


                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    //    footerTemplate: "<div class = '#=planShipmentUtil.isNoMoreWorkTimeLoad(sum)? 'table-footer-cell-red' :'table-footer-cell-blue'#'>#=sum# </div> ",
                    /*  footerAttributes: {
                     //class: "# =planShipmentUtil.isNoMoreWorkTimeLoad(value)? 'table-footer-cell-red' : '' # ",
                     class: "table-footer-cell",
                     //style: "# =planShipmentUtil.isNoMoreWorkTimeLoad(value) ? 'color: red;text-align: right; font-size: 14px ':'color:blue;text-align: right; font-size: 14px'#; "
                     //  style: 'color:# =planShipmentUtil.isNoMoreWorkTimeLoad(value)? blue:red #;text-align: right; font-size: 14px'
                     }*/

                },
                {
                    field: "",
                    title: "ТЭК",
                    width: "140px",
                    filterable: false,
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,


                },
                {
                    //  field: "dateDeliveryFact",
                    title: "Дата доставки план",
                    width: "80px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    filterable: false,
                    groupable: false,
                    format: "{0:dd-MM-yyyy}"
                },
                {
                    //  field: "dateDeliveryFact",
                    title: "Дата доставки факт",
                    width: "80px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    filterable: false,
                    groupable: false,
                    format: "{0:dd-MM-yyyy}"
                },
                {
                    //  field: "dateDeliveryFact",
                    title: "Причина отклонения",
                    width: "80px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    filterable: false,
                    groupable: false,

                },
                {
                    field: "note",
                    title: "Примечание",
                    width: "80px",
                    filterable: false,
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,

                },



            ]
        }).data("kendoGrid");
    }

    var dataSourceShipmentPlan = function(){
        return new kendo.data.DataSource({
            transport:{
                read: function (options) {
                    $.ajax({
                        type: "GET",
                        url: "api/planshipmentfailure",
                        contentType: "application/json; charset=utf-8",
                        dataType: 'json',
                        /*data: {
                            datePlan: getPlanDay()
                        },*/
                        success: function (data) {

                            if(data === "No Data"){
                                options.success([]);
                            }else
                            {

                                options.success(data);
                            }


                        }
                    }).done(function(e){

                    });
                },
            },
            schema: {
                model: {
                    id: "id",

                    fields: {
                        id: {type: "number", editable: false},
                        datePlan:{type:"string"},
                        enterprise: { type:"string"} ,
                        typeShipment: {type: "string"},
                        numberDispatcher: {editable: false,type: "string"},
                        dateShipmentDispatcher:{editable: false,type: "string"},
                        existInStore: {editable: false,type: "boolean"},
                        dateToStore: {editable: false,type:"string"},



                    }
                }
            },
            group: { field: "datePlan" }
        });
    }

    var planShipmentGridView = function () {
        return $("#planShipmentGridView").kendoGrid({
            // toolbar: ["edit"],
            dataSource: dataSourceShipmentPlan(),
            // noRecords: true,
            height: '100%',
          //  groupable: true,
            sortable: true,
            filterable: true,
           // resizable: true,
            selectable:"multiple, row",
           // columnMenu: true,

            //change:onChange,
            /*dataBound: function (e) {
             $("#gridView").find('.k-icon.k-i-collapse').trigger('click');
             },*/

            columns: [
                {
                    field: "datePlan",
                    title: "Дата плана",
                    width: "1px",
                    groupable: false,
                },
                {
                    field: "enterprise",
                    title: "Предприятие отправитель",
                    width: "100px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    groupable: false,
                    editable: false,

                    //  editor: enterpriseDropDownEditor, template: "#=senderEnterprise.name#"
                },
                {
                     field: "typeShipment",
                    title: "Вид отгрузки",
                    width: "100px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    //filterable: false,
                    groupable: false
                },
                {
                    field: "numberDispatcher",
                    title: "№ поручения экспедитору",
                    width: "100px",
                    filterable: true,
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,

                },
                {
                    field: "dateShipmentDispatcher",
                    title: "Дата отгрузки в ПЭ",
                    width: "100px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    // attributes: {class:"#=moment.duration(moment(dateShipmentDispatcher).diff(moment('01-01-2016'))).asDays() > 0 ? 'table-cell-red':'table-cell'#  "},
                    //attributes: {class:"#=planShipmentUtil.getDay(dateShipmentDispatcher) ? 'table-cell-red':'table-cell'#  "},
                    filterable: false,
                    groupable: false,
                    format: "{0:dd-MM-yyyy}",
                    //template:'#=  moment(dateShipmentDispatcher).format("DD-MM-YYYY")#'


                },
                {
                     field: "existInStore",
                    title: "Наличие на складе",
                    width: "80px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    filterable: false,
                    groupable: false,
                    template: '<input type="checkbox" #= existInStore ? "checked=checked" : "" # disabled="disabled" ></input>'
                },
                {
                     field: "dateToStore",
                    title: "Сдача на склад",
                    width: "100px",
                    filterable: false,
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    groupable: false,
                    //template:'#=  moment(dateToStore).format("DD-MM-YYYY HH:mm")#'
                },
            ]

        }).data("kendoGrid")
    }

    var planLoadGridView = function(){
      return $("#planLoadGridView").kendoGrid({
            // toolbar: ["edit"],
            //dataSource: dataSourceShipmentPlan(),
            // noRecords: true,
            height: '100%',
            //  groupable: true,
            sortable: true,
            filterable: true,
            // resizable: true,
            selectable: "multiple, row",
            // columnMenu: true,

            //change:onChange,
            /*dataBound: function (e) {
             $("#gridView").find('.k-icon.k-i-collapse').trigger('click');
             },*/

            columns: [ {
                //field: "driverTransportCompany",
                title: "Гос. номер ТС",
                width: "80px",
                filterable: false,
                headerAttributes: gridUtils.headerFormat,
                attributes: gridUtils.columnFormat,
                //  template: "#=planShipmentUtil.isNotNull(driverTransportCompany) ? driverTransportCompany.numberTruck : ''#"
            },
                {
                    //field: "senderEnterprise",
                    title: "Предприятие отправитель",
                    width: "100px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    groupable: false,
                    /* locked: true,
                     lockable: false,*/
                  //  template: "#=senderEnterprise.name#"
                },
                {
                    //field: "numberOrder",
                    title: "№ заказа",
                    width: "70px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    filterable: false,
                },
                {
                    //field: "numberItem",
                    title: "№ изделия",
                    width: "80px",
                    filterable: false,
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat

                },
            ]
        });
    }



    var toolBar = function(){
        return  $("#headertoolbar").kendoToolBar({
            resizable: true,
            items: [
                {
                    template:"<button id='bSavePlanLoad' class='k-button k-state-default' title='Сохранить план'><i class='fa fa-lg fa-floppy-o'"+
                    "aria-hidden='true'></i></button>",
                    overflow: "never"
                },
                /* {
                 template:"<button id='bCancelPlanLoad' class='k-button k-state-default' title='Отменить изменения'><i class='fa fa-lg fa-times'"+
                 "aria-hidden='true'></i></button>",
                 overflow: "never"
                 },*/
                {
                    template:"<button id='bExportPlanLoad' class='k-button k-state-default' title='Экспортировать план погрузки(Еxcel)'>"+
                    "<i class='fa fa-lg fa-download fa-rotate-270' aria-hidden='true'></i></button>",
                    overflow: "never"
                },
                { type: "separator" },
                { template: "<label  >Дата плана:</label>" },
                {
                    template: "<input id='planLoadDay' style='width: 150px;' />",
                    overflow: "never"
                },
                { template: "<label>Начать с:</label>" },
                {
                    template: "<input id='beginPlanFromTime' style='width: 90px;' />",
                    overflow: "never"
                },
                { type: "separator" },

                { template: "<label>Доклевеллер:</label>" },
                {
                    template: "<input id='dpDoklever' style='width: 60px;' />",
                    overflow: "never"
                },
               /* { template: "<label>№ по порядку:</label>" },
                {
                    template: "<input id='dpOrder' style='width: 60px;' />",
                    overflow: "never"
                },*/
                { type: "separator" },
                { template: "<label>Перерывы:</label>" },
                {
                    type: "buttonGroup",
                    buttons: [
                        { text: "Учитывать", togglable: true, group: "position" , selected: true},
                        { text: "Не учитывать", togglable: true, group: "position" },

                    ]
                },

                { type: "separator" },
                {
                    template:"<button id='bAddElementPlanLoad' class='k-button k-state-default' title='Добавить произвольный элемент в план'><i class='fa fa-lg fa-plus'"+
                    "aria-hidden='true'></i></button>",
                    overflow: "never"
                },
                {
                    template:"<button id='bDeleteElementFromPlanLoad' class='k-button k-state-default' title='Удалить заказ из плана'><i class='fa fa-lg fa-times'"+
                    "aria-hidden='true'></i></button>",
                    overflow: "never"
                }
            ]
        });
    }


    var createDragAndDrop = function () {
        $("#planShipmentGridView").kendoDraggable({
            filter: "tr",
            hint: function (item) {
               console.log(item);
                /*var item = $('<div class="k-grid k-widget" style="background-color: DarkOrange; color: black;"><table><tbody><tr>' + e.html() + '</tr></tbody></table></div>');
              /*   return item;*!/
                var helper = $('<div class="k-grid k-widget drag-helper" style="background-color: DarkOrange; color: black;"/>');
                if (!item.hasClass(selectedClass)) {
                    item.addClass(selectedClass).siblings().removeClass(selectedClass);
                }
                var elements = item.parent().children('.'+selectedClass).clone();
                // item.data('multidrag', elements).siblings('.'+selectedClass).remove();
                return helper.append(elements);*/
                return item.clone();
            },
            group: "planShipmentGridView",
        });

        $("#planLoadGridView").kendoDraggable({
            filter: "tr",
            hint: function (e) {
                var item = $('<div class="k-grid k-widget" style="background-color: MediumVioletRed; color: black;"><table><tbody><tr>' + e.html() + '</tr></tbody></table></div>');
                return item;
            },
            group: "planLoadGridView"
        });
        $("#planLoadGridView").kendoDropTarget({

            drop: function (e) {
                console.log(e);
                console.log(e.draggable.currentTarget.data());
                console.log(e.draggable.currentTarget.text().split(":")[1].trim());

            },
            group: "planShipmentGridView"
        });
    }



    return {
        factLoadGridView: factLoadGridView,
        planShipmentGridView: planShipmentGridView,
        toolBar:toolBar,
        planLoadGridView:planLoadGridView,
        createDragAndDrop:createDragAndDrop
    }


})();
