/**
 * Created by Zhukov on 06.11.2016.
 */
var planShipmentUtil = (
    function() {

        var datePlan,ID;

        function enterpriseDropDownEditor(container, options) {
            console.log(options);
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

        function deviationShipmentDropDownEditor(container, options) {
            console.log(options);
            $('<input required name="' + options.field + '"/>')
                .appendTo(container)
                .kendoDropDownList({
                    autoBind: false,
                    dataTextField: "description",
                    dataValueField: "id",
                    dataSource: {
                        transport: {
                            read: function(options){
                                axios.get('/api/deviationShipment').then(function(response){
                                    options.success(response.data);
                                });
                            }
                        }
                    }
                });
        }
        function deviationDeliveryDropDownEditor(container, options) {
            console.log(options);
            $('<input required name="' + options.field + '"/>')
                .appendTo(container)
                .kendoDropDownList({
                    autoBind: false,
                    dataTextField: "description",
                    dataValueField: "id",

                    dataSource: {
                        transport: {
                            read: function(options){
                                axios.get('/api/deviationDelivery').then(function(response){
                                    options.success(response.data);
                                });
                            }
                        }
                    }
                });
        }

        function selectDeviation (deviationDelivery){
            if(deviationDelivery !== null){
                return deviationDelivery.description;
            } else {
                return '';
            }
        };
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


        var setDatePlan  = function(value){
            console.log("setDatePlan");
            datePlan = value;
        }
        function getPlanDay(){
            return moment(datePlan).format("DD-MM-YYYY") ;
        }

        var dataSourcePlanDay =  function() {
            return new kendo.data.DataSource({
                //serverAggregates: true,
                transport: {

                    read: function (options) {

                        $.ajax({
                            type: "GET",
                            url: "api/plandayshipment",
                            contentType: "application/json; charset=utf-8",
                            dataType: 'json',
                            data: {
                                datePlan: getPlanDay()
                            },
                            success: function (data) {
                                console.log(data);
                                options.success(data);
                            }
                        });
                    },
                    create: function(options){

                        $.ajax({
                            type: "POST",
                            url: "api/plandayshipment",
                            contentType: "application/json; charset=utf-8",
                            dataType: 'json',
                            data : kendo.stringify(options),
                            success: function(data) {
                                options.success(data);
                            }
                        });
                    }
                },
                //batch: true,
                aggregate:[
                    {field:"timeToLoad",aggregate:"count"}
                ],


                schema: {
                    model: {
                        id: "id",

                        fields: {
                            id: {type: "number", editable: false},
                            idPlan: {editable: false,type:"number"},
                            senderEnterprise: { editable: false,defaultValue: { id: 0,name:''}} ,
                            kindShipment: {editable: false,type: "string"},
                            inPlanLoad: {editable: false,type: "boolean"},
                            dateShipmentDispatcher: {editable: false,type: "string"},
                            'deviationShipment.description': {defaultValue:{id:-1,description:''}, nullable: true},
                            dateDeliveryDispatcher: {editable: false,type: "string"},
                            dateDeliveryFact: {},
                            deviationDelivery: {defaultValue:{id:-1,description:''}, nullable: true},
                            existInStore: {editable: false,type: "boolean"},
                            dateToStore: {editable: false,type: "string"},
                            placeLoading: {type: "string"},
                            statusDispatcher: {editable: false,type: "string"},
                            numberDispatcher: {editable: false,type: "string"},
                            dateCreateDispatcher: {editable: false,type: "string"},
                            numberOrder: {editable: false,type: "string"},
                            numberItem: {editable: false,type: "string"},
                            nameOrder: {editable: false,type: "string"},
                            nameCustomer: {editable: false,type: "string"},
                            placeDelivery: {editable: false,type: "string"},
                            sizeOrder: {editable: false,type: "number"},
                            sizePallet: {editable: false,type: "string"},
                            packingMethod: {editable: false,type: "string"},
                            countPlace: {editable: false,type: "number"},
                            capacityOrder: {editable: false,type: "number"},
                            typeTransport: {editable: false,type: "string"},
                            timeToLoad: {editable: false,type:"number"},
                            transportCompanyPlan: {},
                            transportCompanyFact: {},
                            numberGate: {type:"number"},
                            deliveryDistance: {type:"number"},
                            costTrip: {type:"number",editable: true, nullable: false},
                            timeLoad: {},
                            managerBackOffice: {editable: false,type: "string"},
                            note: {type: "string"}

                        }
                    }
                }

            });
        }


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


        function onChange(args) {
            var model = this.dataItem(this.select());
            ID = model.uid; //gets the value of the field "ID"
        }
        var  selectPlanDetailID = function(){
            return ID;
        }

        var planGrid =  function(){

            return $("#planDayGrid").kendoGrid({
                // toolbar: ["edit"],
                dataSource: planShipmentUtil.dataSourcePlanDay(),

                height: '100%',
                groupable: true,
                sortable: true,
                filterable: true,
                resizable: true,
               // selectable:"true",
                editable: true,
                change:onChange,
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
                        groupable: false,
                        editor: enterpriseDropDownEditor, template: "#=senderEnterprise.name#"
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
                        field: "deviationShipment",
                        title: "Классификатор отклонений отгрузки",
                        width: "120px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        editor: deviationShipmentDropDownEditor, template: "#=deviationShipment.description#"

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
                        field: "deviationDelivery",
                        title: "Классификатор отклонений доставки",
                        width: "120px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        editor: deviationDeliveryDropDownEditor, template: "#=deviationDelivery#"

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
                        field: "timeToLoad",
                        title: "Время на погрузку",
                        width: "80px",
                        filterable: false,
                        aggregates: ["count"],
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        footerTemplate: "#=count# "

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
                        field: "numberGate",
                        title: "Доклевеллер",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "deliveryDistance",
                        title: "Расстояние доставки",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,

                    },
                    {
                        field: "costTrip",
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
                deviationShipment:{id:0,description:''},
                dateShipmentDispatcher: item.dateShipmentDispatcher,
                dateDeliveryDispatcher: item.dateDeliveryDispatcher,
                deviationDelivery:{id:0,description:''},
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
                costTrip: 0,
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
                        $("#planDayGrid").data("kendoGrid").dataSource.sync();
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
            updateJournalShipment:UpdateJournalShipment,
            selectPlanDetailID:selectPlanDetailID,
            setDatePlan:setDatePlan
        }
    }
)();