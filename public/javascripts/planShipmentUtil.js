/**
 * Created by Zhukov on 06.11.2016.
 */
var planShipmentUtil = (
    function() {

        function UpdateJournalShipment(item) {
            item.set('inPlanDay', true);
        }

        var dataSourceJournal =  function() {
           return new kendo.data.DataSource({
                serverAggregates: true,
                transport: {
                    read: function (options) {
                        $.ajax({
                            type: "GET",
                            url: "api/journalship",
                            contentType: "application/json; charset=utf-8",
                            //beforeSend: setHeaderRequest,
                            dataType: 'json',

                            success: function (data) {
                                options.success(data);
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
                            senderEnterprise: {type: "string"},
                            kindShipment: {type: "string"},
                            numberDispatcher: {type: "string"},
                            inPlanDay: {type: "boolean"},
                            dateCreateDispatcher: {type: "string"},
                            dateShipmentDispatcher: {type: "string"},
                            dateDeliveryDispatcher: {type: "string"},
                            existInStore: {type: "boolean"},
                            dateToStore: {type: "string"},
                            placeLoading: {type: "string"},
                            statusDispatcher: {type: "string"},
                            numberOrder: {type: "string"},
                            numberItem: {type: "string"},
                            nameOrder: {type: "string"},
                            nameCustomer: {type: "string"},
                            placeDelivery: {type: "string"},
                            sizeOrder: {type: "number"},
                            sizePallet: {type: "string"},
                            packingMethod: {type: "string"},
                            countPlace: {type: "number"},
                            capacityOrder: {type: "number"},
                            typeTransport: {type: "string"},
                            managerBackOffice: {type: "string"},
                            note: {type: "string"}

                        }
                    }
                }

            });
        }



     /*   var gridJournalShipment = function() {
            return _p = $("#journalGridView").kendoGrid({

                dataSource: planShipmentUtil.dataSourceJournal(),
                height: '100%',
                groupable: true,
                sortable: true,
                filterable: true,
                resizable: true,
                pageable: {
                    pageSizes: true
                },
                /!* dataBound: function (e) {
                 $("#gridView").find('.k-icon.k-i-collapse').trigger('click');
                 },*!/
                columns: [
                    {
                        field: "senderEnterprise",
                        title: "Предприятие отправитель",
                        width: "70px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupable: false
                    },
                    {
                        field: "kindShipment",
                        title: "Вид отгрузки",
                        width: "60px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        //filterable: false,
                        groupable: false
                    },
                    {
                        field: "numberDispatcher",
                        title: "№ поручения экспедитору",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "inPlanDay",
                        title: "В плане на сутки",
                        width: "180px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        template: '<input type="checkbox" #= inPlanDay ? "checked=checked" : "" # disabled="disabled" ></input>'
                    },
                    {
                        field: "dateCreateDispatcher",
                        title: "Дата и время создания ПЭ",
                        width: "130px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        filterable: false,
                        groupable: false
                    },
                    {
                        field: "dateShipmentDispatcher",
                        title: "Дата отгрузки в ПЭ",
                        width: "50px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupable: false,
                        groupHeaderTemplate: "Месяц отгрузки: #=value# : (#= calcAll(data,field,value)#)"
                        //groupHeaderTemplate: "Месяц отгрузки: #=value# : (#= count#)"
                    },
                    {
                        field: "dateDeliveryDispatcher",
                        title: "Дата доставки в ПЭ",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormatChangeDeliveryDate,
                        filterable: false,
                        groupable: false
                    },
                    {
                        field: "existInStore",
                        title: "Наличие на складе",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        filterable: false,
                        groupable: false
                    },
                    {
                        field: "dateToStore",
                        title: "Сдача на склад",
                        width: "65px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupable: false
                    },
                    {
                        field: "placeLoading",
                        title: "Площадка отгрузки",
                        width: "65px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupable: true


                    },

                    {
                        field: "statusDispatcher",
                        title: "Статус ПЭ",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat

                    },
                    {
                        field: "numberOrder",
                        title: "№ заказа",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat

                    },
                    {
                        field: "numberItem",
                        title: "№ изделия",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat

                    },
                    {
                        field: "nameOrder",
                        title: "Наименование заказа",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat

                    },
                    {
                        field: "nameCustomer",
                        title: "Грузополучатель",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "placeDelivery",
                        title: "Пункт доставки",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "sizeOrder",
                        title: "Объем заказа",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "sizePallet",
                        title: "Размер палетты",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "packingMethod",
                        title: "Способ упаковки",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "countPlace",
                        title: "Количество мест",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "capacityOrder",
                        title: "Объем заказа",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "typeTransport",
                        title: "Вид транспорта",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "managerBackOffice",
                        title: "Менеджер Back-Office",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "note",
                        title: "Примечание",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    }


                ]
            }).data("kendoGrid");
        }*/


        var dataSourcePlanDay =  function() {
            return new kendo.data.DataSource({
                serverAggregates: true,
                transport: {

                    read: function (options) {
                        var sentdatePlan;
                        $.ajax({
                            type: "GET",
                            url: "api/plandayshipment",
                            contentType: "application/json; charset=utf-8",
                            beforeSend: function () {
                                sentdatePlan = $("#planShipmentDay").data("kendoDatePicker");
                                console.log(sentdatePlan);
                            },
                            dataType: 'json',
                            data: {
                                datePlan: sentdatePlan
                            },
                            success: function (data) {
                                options.success(data);
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
                            idPlan: {},
                            senderEnterprise: {type: "string"},
                            kindShipment: {type: "string"},
                            inPlanLoad: {type: "boolean"},
                            dateShipmentDispatcher: {type: "string"},
                            deviationShipment: {},
                            dateDeliveryDispatcher: {type: "string"},
                            dateDeliveryFact: {},
                            deviationDelivery: {},
                            existInStore: {type: "boolean"},
                            dateToStore: {type: "string"},
                            placeLoading: {type: "string"},
                            statusDispatcher: {type: "string"},
                            numberDispatcher: {type: "string"},
                            dateCreateDispatcher: {type: "string"},
                            numberOrder: {type: "string"},
                            numberItem: {type: "string"},
                            nameOrder: {type: "string"},
                            nameCustomer: {type: "string"},
                            placeDelivery: {type: "string"},
                            sizeOrder: {type: "number"},
                            sizePallet: {type: "string"},
                            packingMethod: {type: "string"},
                            countPlace: {type: "number"},
                            capacityOrder: {type: "number"},
                            typeTransport: {type: "string"},
                            timeToLoad: {},
                            transportCompanyPlan: {},
                            transportCompanyFact: {},
                            numberGate: {},
                            deliveryDistance: {},
                            costTrip: {},
                            timeLoad: {},
                            managerBackOffice: {type: "string"},
                            note: {type: "string"}

                        }
                    }
                }

            });
        }

      /*  var planDayGrid =  function(){ return _pd = $("#planDayGrid").kendoGrid({
                // toolbar: ["excel"],
                //dataSource: planShipmentUtil.dataSourcePlanDay(),
                height: '100%',
                groupable: true,
                sortable: true,
                filterable: true,
                resizable: true,

                dataBound: function (e) {
                    $("#gridView").find('.k-icon.k-i-collapse').trigger('click');
                },
                columns: [
                    {
                        field: "senderEnterprise",
                        title: "Предприятие отправитель",
                        width: "70px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupable: false
                    },
                    {
                        field: "kindShipment",
                        title: "Вид отгрузки",
                        width: "60px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        //filterable: false,
                        groupable: false
                    },
                    {
                        field: "",
                        title: "Дата плана",
                        width: "60px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        //filterable: false,
                        groupable: false
                    },
                    {
                        field: "inPlanLoad",
                        title: "В плане погрузки",
                        width: "180px",
                        aggregates: ["count"],
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat

                    },
                    {
                        field: "dateShipmentDispatcher",
                        title: "Дата отгрузки в ПЭ",
                        width: "50px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupable: false

                    },
                    {
                        field: "",
                        title: "Классификатор отклонений отгрузки",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Менеджер: #=value#: #=count# : (#=  Math.round((count/calcAll(data,field,value))*100)#%): Count : #=count#"
                    },
                    {
                        field: "dateDeliveryDispatcher",
                        title: "Дата доставки в ПЭ",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormatChangeDeliveryDate,
                        filterable: false,
                        groupable: false
                    },
                    {
                        field: "",
                        title: "Дата доставки факт",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormatChangeDeliveryDate,
                        filterable: false,
                        groupable: false
                    },
                    {
                        field: "",
                        title: "Классификатор отклонений доставки",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Менеджер: #=value#: #=count# : (#=  Math.round((count/calcAll(data,field,value))*100)#%): Count : #=count#"
                    },
                    {
                        field: "existInStore",
                        title: "Наличие на складе",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        filterable: false,
                        groupable: false
                    },
                    {
                        field: "dateToStore",
                        title: "Сдача на склад",
                        width: "65px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupable: false
                    },
                    {
                        field: "placeLoading",
                        title: "Площадка отгрузки",
                        width: "65px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupable: true,

                        groupHeaderTemplate: "Причина отклонений: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "statusDispatcher",
                        title: "Статус ПЭ",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "numberDispatcher",
                        title: "№ поручения экспедитору",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Менеджер: #=value#: #=count# : (#=  Math.round((count/calcAll(data,field,value))*100)#%): Count : #=count#"
                    },
                    {
                        field: "dateCreateDispatcher",
                        title: "Дата и время создания ПЭ",
                        width: "130px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        filterable: false,
                        groupable: false
                    },
                    {
                        field: "numberOrder",
                        title: "№ заказа",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "numberItem",
                        title: "№ изделия",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "nameOrder",
                        title: "Наименование заказа",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "nameCustomer",
                        title: "Грузополучатель",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "placeDelivery",
                        title: "Пункт доставки",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "sizeOrder",
                        title: "Объем заказа",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "sizePallet",
                        title: "Размер палетты",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "packingMethod",
                        title: "Способ упаковки",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "countPlace",
                        title: "Количество мест",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "capacityOrder",
                        title: "Объем заказа",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "typeTransport",
                        title: "Вид транспорта",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "",
                        title: "Время на погрузку",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "",
                        title: "Наименование ТЭК план",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "",
                        title: "Наименование ТЭК факт",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "",
                        title: "Гос. номер ТС",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "",
                        title: "Водитель",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "",
                        title: "Телефон водителя",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "",
                        title: "Доклевеллер",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "",
                        title: "Расстояние доставки",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "",
                        title: "Ставка рейса",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "",
                        title: "Время погрузки ТС",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "managerBackOffice",
                        title: "Менеджер Back-Office",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    },
                    {
                        field: "note",
                        title: "Примечание",
                        width: "80px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Состояние: #=value#: #=count# : (#= calcAll(data,field,value,count)#)"
                    }


                ]
            }).data("kendoGrid");}*/
        var journalShipmentView =  function() {
           return   $("#journalGridView").kendoGrid({

                dataSource: dataSourceJournal(),
                height: '100%',
                groupable: true,
                sortable: true,
                filterable: true,
                resizable: true,
                pageable: {
                    pageSizes: true
                },
                /* dataBound: function (e) {
                 $("#gridView").find('.k-icon.k-i-collapse').trigger('click');
                 },*/
                columns: [
                    {
                        field: "senderEnterprise",
                        title: "Предприятие отправитель",
                        width: "100px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupable: false
                    },
                    {
                        field: "kindShipment",
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
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "inPlanDay",
                        title: "В плане на сутки",
                        width: "70px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        template: '<input type="checkbox" #= inPlanDay ? "checked=checked" : "" # disabled="disabled" ></input>'
                    },
                    {
                        field: "dateCreateDispatcher",
                        title: "Дата и время создания ПЭ",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        filterable: false,
                        groupable: false
                    },
                    {
                        field: "dateShipmentDispatcher",
                        title: "Дата отгрузки в ПЭ",
                        width: "100px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupable: false,
                        filterable: false,
                        groupHeaderTemplate: "Месяц отгрузки: #=value# : (#= calcAll(data,field,value)#)"
                        //groupHeaderTemplate: "Месяц отгрузки: #=value# : (#= count#)"
                    },
                    {
                        field: "dateDeliveryDispatcher",
                        title: "Дата доставки в ПЭ",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormatChangeDeliveryDate,

                        filterable: false,
                        groupable: false
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
                        width: "110px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        filterable: false,
                        groupable: false
                    },
                    {
                        field: "placeLoading",
                        title: "Площадка отгрузки",
                        width: "95px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupable: true


                    },

                    {
                        field: "statusDispatcher",
                        title: "Статус ПЭ",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        filterable: false

                    },
                    {
                        field: "numberOrder",
                        title: "№ заказа",
                        width: "70px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        filterable: false,
                    },
                    {
                        field: "numberItem",
                        title: "№ изделия",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat

                    },
                    {
                        field: "nameOrder",
                        title: "Наименование заказа",
                        width: "180px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat

                    },
                    {
                        field: "nameCustomer",
                        title: "Грузополучатель",
                        width: "120px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "placeDelivery",
                        title: "Пункт доставки",
                        width: "100px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "sizeOrder",
                        title: "Объем заказа",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "sizePallet",
                        title: "Размер палетты",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "packingMethod",
                        title: "Способ упаковки",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "countPlace",
                        title: "Количество мест",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "capacityOrder",
                        title: "Объем заказа",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "typeTransport",
                        title: "Вид транспорта",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "managerBackOffice",
                        title: "Менеджер Back-Office",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    },
                    {
                        field: "note",
                        title: "Примечание",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat
                    }


                ]
            }).data("kendoGrid");
        }

        var planGrid =  function(){
            return $("#planDayGrid").kendoGrid({
                // toolbar: ["excel"],
                dataSource: planShipmentUtil.dataSourcePlanDay(),
                height: '100%',
                groupable: true,
                sortable: true,
                filterable: true,
                resizable: true,

                dataBound: function (e) {
                    $("#gridView").find('.k-icon.k-i-collapse').trigger('click');
                },
                columns: [
                    {
                        field: "senderEnterprise",
                        title: "Предприятие отправитель",
                        width: "100px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupable: false
                    },
                    {
                        field: "kindShipment",
                        title: "Вид отгрузки",
                        width: "100px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        //filterable: false,
                        groupable: false
                    },
                    {
                        field: "",
                        title: "Дата плана",
                        width: "60px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        //filterable: false,
                        groupable: false
                    },
                    {
                        field: "inPlanLoad",
                        title: "В плане погрузки",
                        width: "180px",
                        aggregates: ["count"],
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat

                    },
                    {
                        field: "dateShipmentDispatcher",
                        title: "Дата отгрузки в ПЭ",
                        width: "100px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        filterable: false,
                        groupable: false

                    },
                    {
                        field: "",
                        title: "Классификатор отклонений отгрузки",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Менеджер: #=value#: #=count# : (#=  Math.round((count/calcAll(data,field,value))*100)#%): Count : #=count#"
                    },
                    {
                        field: "dateDeliveryDispatcher",
                        title: "Дата доставки в ПЭ",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormatChangeDeliveryDate,
                        filterable: false,
                        groupable: false
                    },
                    {
                        field: "",
                        title: "Дата доставки факт",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormatChangeDeliveryDate,
                        filterable: false,
                        groupable: false
                    },
                    {
                        field: "",
                        title: "Классификатор отклонений доставки",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupHeaderTemplate: "Менеджер: #=value#: #=count# : (#=  Math.round((count/calcAll(data,field,value))*100)#%): Count : #=count#"
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
                        groupable: false
                    },
                    {
                        field: "placeLoading",
                        title: "Площадка отгрузки",
                        width: "95px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupable: true,
                        filterable: false

                    },
                    {
                        field: "statusDispatcher",
                        title: "Статус ПЭ",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "numberDispatcher",
                        title: "№ поручения экспедитору",
                        width: "100px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "dateCreateDispatcher",
                        title: "Дата и время создания ПЭ",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        filterable: false,
                        groupable: false
                    },
                    {
                        field: "numberOrder",
                        title: "№ заказа",
                        width: "70px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "numberItem",
                        title: "№ изделия",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "nameOrder",
                        title: "Наименование заказа",
                        width: "180px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "nameCustomer",
                        title: "Грузополучатель",
                        width: "120px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "placeDelivery",
                        title: "Пункт доставки",
                        width: "100px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "sizeOrder",
                        title: "Объем заказа",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "sizePallet",
                        title: "Размер палетты",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "packingMethod",
                        title: "Способ упаковки",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "countPlace",
                        title: "Количество мест",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "capacityOrder",
                        title: "Объем заказа",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "typeTransport",
                        title: "Вид транспорта",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "",
                        title: "Время на погрузку",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "",
                        title: "Наименование ТЭК план",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "",
                        title: "Наименование ТЭК факт",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "",
                        title: "Гос. номер ТС",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "",
                        title: "Водитель",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "",
                        title: "Телефон водителя",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "",
                        title: "Доклевеллер",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "",
                        title: "Расстояние доставки",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "",
                        title: "Ставка рейса",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "",
                        title: "Время погрузки ТС",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "managerBackOffice",
                        title: "Менеджер Back-Office",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "note",
                        title: "Примечание",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    }


                ]
            }).data("kendoGrid");
        }

        var addToPlan = function (item) {
            return {
                senderEnterprise: item.senderEnterprise,
                kindShipment: item.kindShipment,
                numberDispatcher: item.numberDispatcher,
                inPlanDay: false,
                dateCreateDispatcher: item.dateCreateDispatcher,
                dateShipmentDispatcher: item.dateShipmentDispatcher,
                dateDeliveryDispatcher: item.dateDeliveryDispatcher,
                existInStore: item.existInStore,
                dateToStore: item.dateToStore,
                placeLoading: item.placeLoading,
                statusDispatcher: item.statusDispatcher,
                numberOrder: item.numberOrder,
                numberItem: item.numberItem,
                nameOrder: item.nameOrder,
                nameCustomer: item.nameCustomer,
                placeDelivery: item.placeDelivery,
                sizeOrder: item.sizeOrder,
                sizePallet: item.sizePallet,
                packingMethod: item.packingMethod,
                countPlace: item.countPlace,
                capacityOrder: item.capacityOrder,
                typeTransport: item.typeTransport,
                managerBackOffice: item.managerBackOffice,
                note: item.note
            }
        }

        var createDragAndDrop = function(){
                $("#journalGridView").kendoDraggable({
                    filter: "tr",
                    hint: function (e) {

                        var item = $('<div class="k-grid k-widget" style="background-color: DarkOrange; color: black;"><table><tbody><tr>' + e.html() + '</tr></tbody></table></div>');
                        return item;
                    },
                    group: "gridGroupJournal",
                });


                $("#planDayGrid").kendoDraggable({
                    filter: "tr",
                    hint: function (e) {
                        var item = $('<div class="k-grid k-widget" style="background-color: MediumVioletRed; color: black;"><table><tbody><tr>' + e.html() + '</tr></tbody></table></div>');
                        return item;
                    },
                    group: "gridGroupPlan"
                });
                $("#planDayGrid").kendoDropTarget({

                    drop: function (e) {

                        var dataItem = $("#journalGridView").data("kendoGrid").dataSource.getByUid(e.draggable.currentTarget.data("uid"));

                        $("#planDayGrid").data("kendoGrid").dataSource.add(planShipmentUtil.addToPlan(dataItem));
                        planShipmentUtil.updateJournalShipment(dataItem);

                    },
                    group: "gridGroupJournal"
                });

                $(journalShipmentView.element).kendoDropTarget({
                    drop: function (e) {


                    },
                    group: "gridGroupPlan"
                });



            }

        return {
            dataSourceJournal:dataSourceJournal,
            dataSourcePlanDay:dataSourcePlanDay,
            createDragAndDrop:createDragAndDrop,
            journalShipmentView:journalShipmentView,
            planGrid:planGrid,
            addToPlan:addToPlan,
            updateJournalShipment:UpdateJournalShipment
        }
    }
)();