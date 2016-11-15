/**
 * Created by Zhukov on 06.11.2016.
 */
var planShipmentUtil = (
    function() {

        var datePlan,ID, selectShipItem;

        function enterpriseDropDownEditor(container, options) {

            $('<input required name="' + options.field + '"/>')
                .appendTo(container)
                .kendoDropDownList({
                    autoBind: false,
                    dataTextField: "name",
                    dataValueField: "id",
                    dataSource: {
                        transport: {
                            read: function(options){
                                $.ajax({
                                    type:"GET",
                                    url:'/api/enterprises',
                                    contentType: "application/json; charset=utf-8",
                                    dataType: 'json',
                                    success: function (data) {
                                        options.success(data);
                                    }
                                });

                            }
                        }
                    }
                });
        }

        function transportCompanyDropDownEditor(container, options) {
            var enterpriseName = options.model.senderEnterprise.name;

            $('<input  name="' + options.field + '"/>')
                .appendTo(container)
                .kendoDropDownList({
                    autoBind: false,
                    dataTextField: "name",
                    dataValueField: "rowPointer",
                    dataSource: {
                        transport: {
                            read: function(options){
                                $.ajax({
                                    type:"GET",
                                    url:'/api/transportcompany/'+enterpriseName,
                                   /* beforeSend: function(){
                                        console.log(enterpriseName);

                                    },*/
                                    contentType: "application/json; charset=utf-8",
                                    dataType: 'json',
                                    success: function (data) {
                                        options.success(data);
                                    }
                                });

                            }
                        }
                    }
                });
        }




        function deviationShipmentDropDownEditor(container, options) {

            $('<input required name="' + options.field + '"/>')
                .appendTo(container)
                .kendoDropDownList({
                    autoBind: false,
                    dataTextField: "description",
                    dataValueField: "id",
/*
                    optionLabel: {
                        description: " ",
                        Id: "-1"
                    },*/
                    dataSource: {
                        transport: {
                            read: function(options){
                                $.ajax({
                                     type:"GET",
                                     url:'/api/deviationShipment',
                                    contentType: "application/json; charset=utf-8",
                                    dataType: 'json',
                                    success: function (data) {

                                        options.success(data);
                                    }
                                });
                            }
                        }
                    },
                   /* change: function (e) {

                        var dataItem = e.sender.dataItem();
                        if(parseInt(dataItem.Id) === -1 ){
                            options.model.set("deviationShipment",{id:-1,description:" "})
                        }
                        console.log(options);
                        console.log(dataItem);

                    }*/
                });
        }
        function deviationDeliveryDropDownEditor(container, options) {

            $('<input required name="' + options.field + '"/>')
                .appendTo(container)
                .kendoDropDownList({
                    autoBind: false,
                    dataTextField: "description",
                    dataValueField: "id",

                    dataSource: {
                        transport: {
                            read: function(options){
                                $.ajax({
                                    type:"GET",
                                    url:'/api/deviationDelivery',
                                    contentType: "application/json; charset=utf-8",
                                    dataType: 'json',
                                    success: function (data) {
                                        options.success(data);
                                    }
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
                            senderEnterprise: {},
                            typeShipment: {type: "string"},
                            numberDispatcher: {type: "string"},
                            inPlanDay: {type: "boolean"},
                            dateCreateDispatcher: {editable:false},
                            dateShipmentDispatcher: {type: "string"},
                            dateDeliveryDispatcher: {type: "string"},
                            existInStore: {type: "boolean"},
                            dateToStore: {editable:false},
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
             datePlan = value;
        }


        function getPlanDay(){
            return moment(datePlan).format("DD-MM-YYYY") ;
        }

        function ErrorShow(request) {
            var notificationElement = $("#notification").kendoNotification({
                // hide automatically after 7 seconds
                autoHideAfter: 7000,
                // prevent accidental hiding for 1 second
                allowHideAfter: 1000,
                // show a hide button
                button: true,
                // prevent hiding by clicking on the notification content


                hideOnClick: false
            });

            var notificationWidget = notificationElement.data("kendoNotification");
            notificationWidget.show("Ошибка сохранения данных. " + request.responseText,"error");
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
                            },
                            error: function (request, status, error) {
                                console.log(request);
                                //alert("Ошибка сохранения данных. "+request.responseText);
                                ErrorShow(request);

                                planGrid().dataSource.fetch();

                            }
                        });
                    },
                    update: function(options){

                        $.ajax({
                            type: "PATCH",
                            url: "/api/plandayshipment/"+options.data.id,
                            contentType: "application/json; charset=utf-8",
                            dataType: 'json',
                            beforeSend: function(){
                               options.data.dateDeliveryFact = moment(options.data.dateDeliveryFact).format("DD-MM-YYYY");

                            },
                            data:  kendo.stringify(options),
                            success: function(data) {
                                options.success(data);
                            }
                        });
                    },
                    destroy: function(options) {

                        $.ajax({
                            type: "DELETE",
                            url: "/api/plandayshipment/"+options.data.id,
                            contentType: "application/json; charset=utf-8",
                            dataType: 'json',
                            /*data:  kendo.stringify({
                                "id":options.data.id,
                                "name":options.data.name,
                                "startTime":moment(options.data.startTime).format("DD-MM-YYYY HH:mm"),
                                "endTime":moment(options.data.endTime).format("DD-MM-YYYY HH:mm"),
                                "workTime":options.data.workTime
                            }),*/
                            success: function(data) {
                                options.success(data);
                            }
                        });
                    },
                },
                //batch: true,
                aggregate:[
                    {field:"timeToLoad",aggregate:"sum"}
                ],


                schema: {
                    model: {
                        id: "id",

                        fields: {
                            id: {type: "number", editable: false},
                            idPlan: {editable: false,type:"number"},
                            senderEnterprise: { editable: false,defaultValue: { id: 0,name:''}} ,
                            typeShipment: {editable: false,type: "string"},
                            planLoad: {editable: false,type: "boolean"},
                            dateShipmentDispatcher: {editable: false,type: "date",format:"{0:dd-MM-yyyy}"},
                            deviationShipment: {defaultValue:{id:-1,description:''}, nullable: true},
                            dateDeliveryDispatcher: {editable: false,type: "string"},
                            dateDeliveryFact: {type:"date"},
                            deviationDelivery: {defaultValue:{id:-1,description:''}, nullable: true},
                            existInStore: {editable: false,type: "boolean"},
                            dateToStore: {editable: false,type:"string"},
                            placeShipment: {type: "string"},
                            statusDispatcher: {editable: false,type: "string"},
                            numberDispatcher: {editable: false,type: "string"},
                            dateCreateDispatcher: {editable: false,type:"string"},
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
                            transportCompanyPlan: {editable:true,defaultValue:{rowPointer:"",name:""}},
                            transportCompanyFact: {},
                            numberGate: {type:"number"},
                            deliveryDistance: {type:"number"},
                            costTrip: {type:"number",editable: true, nullable: false},
                            timeLoad: {},
                            managerBackOffice: {editable: false,type: "string"},
                            note: {type: "string"},
                            dataPlan:{type:"string"}

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
                        groupable: false,
                        template: "#=senderEnterprise.name#"
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
                        groupable: false,
                        //template:'#=  moment(dateCreateDispatcher).format("DD-MM-YYYY HH:mm")#'
                       // template:'#=  moment(dateCreateDispatcher,"DD-MM-YYYY HH:mm")#'
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
                        groupable: false,
                        //template:'#=  moment(dateToStore).format("DD-MM-YYYY HH:mm")#'
                     //   template:'#=  moment(dateToStore,"DD-MM-YYYY HH:mm")#'
                      //  format:"{0:dd-MM-yyyy}"
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
        var getDay = function(dateShipmentDispatcher){
            moment.locale('ru');
            return moment(datePlan) > moment(dateShipmentDispatcher);
        }
        var isTransportCompany = function(transportCompany) {
            return transportCompany !== null
        }




        function onChange(e) {
            var model = this.dataItem(this.select());
            ID = model.uid; //gets the value of the field "ID"

            if(e.values.dateDeliveryFact !== e.model.dateDeliveryFact){
                e.model.set('dateDeliveryFact',moment(values.dateDeliveryFact).format("DD-MM-YYYY"));
            }

        }
        var  selectPlanDetailID = function(){
            return ID;
        }
       /* function getColor(dateShipmentDispatcher)
        {
            return {"class" : "table-cell-red"};
        }*/
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
                        field: "senderEnterprise.name",
                        title: "Предприятие отправитель",
                        width: "100px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        groupable: false,
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
                  /*  {
                        field: "",
                        title: "Дата плана",
                        width: "60px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        //filterable: false,
                        groupable: false
                    },*/
                    {
                        field: "planLoad",
                        title: "В плане погрузки",
                        width: "180px",

                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat

                    },
                    {
                        field: "dateShipmentDispatcher",
                        title: "Дата отгрузки в ПЭ",
                        width: "100px",
                        headerAttributes: gridUtils.headerFormat,
                       // attributes: {class:"#=moment.duration(moment(dateShipmentDispatcher).diff(moment('01-01-2016'))).asDays() > 0 ? 'table-cell-red':'table-cell'#  "},
                        attributes: {class:"#=planShipmentUtil.getDay(dateShipmentDispatcher) ? 'table-cell-red':'table-cell'#  "},
                        filterable: false,
                        groupable: false,
                        format:"{0:dd-MM-yyyy}",
                        //template:'#=  moment(dateShipmentDispatcher).format("DD-MM-YYYY")#'


                    },
                    {
                        field: "deviationShipment",
                        title: "Классификатор отклонений отгрузки",
                        width: "120px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        editor: deviationShipmentDropDownEditor, template: "#= deviationShipment.description#"

                    },
                    {
                        field: "dateDeliveryDispatcher",
                        title: "Дата доставки в ПЭ",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormatChangeDeliveryDate,
                        filterable: false,
                        groupable: false,

                    },
                    {
                        field: "dateDeliveryFact",
                        title: "Дата доставки факт",
                        width: "80px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormatChangeDeliveryDate,
                        filterable: false,
                        groupable: false,
                        format:"{0:dd-MM-yyyy}"
                    },
                    {
                        field: "deviationDelivery",
                        title: "Классификатор отклонений доставки",
                        width: "120px",
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        editor: deviationDeliveryDropDownEditor, template: "#=deviationDelivery.description#"

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
                    {
                        field: "placeShipment",
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
                        groupable: false,
                      //  template:'#=  moment(dateCreateDispatcher).format("DD-MM-YYYY HH:mm")#'
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
                        footerTemplate: "#=sum# "

                    },
                    {
                        field: "transportCompanyPlan",
                        title: "Наименование ТЭК план",
                        width: "80px",
                        filterable: false,
                        headerAttributes: gridUtils.headerFormat,
                        attributes: gridUtils.columnFormat,
                        editor:transportCompanyDropDownEditor, template: "#=planShipmentUtil.isTransportCompany(transportCompanyPlan) ? transportCompanyPlan.name : ''#"

                    },
                    {
                        field: "transportCompanyFact",
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
                typeShipment: item.typeShipment,
                numberDispatcher: item.numberDispatcher,
                planLoad: false,
                dateCreateDispatcher: item.dateCreateDispatcher,
                deviationShipment:{id:-1,description:''},
                dateShipmentDispatcher: item.dateShipmentDispatcher,
                dateDeliveryDispatcher: item.dateDeliveryDispatcher,
                deviationDelivery:{id:-1,description:''},
                existInStore: item.existInStore,
                dateToStore: item.dateToStore,
                placeShipment: item.placeLoading,
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
                transportCompanyPlan: {},
                costTrip: 0,
                managerBackOffice: item.managerBackOffice,
                note: item.note,
                datePlan: getPlanDay()

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

                $("#journalGridView").kendoDropTarget({
                    drop: function (e) {
                        //var deleteItem =
                        var dataItem = $("#planDayGrid").data("kendoGrid").dataSource.getByUid(e.draggable.currentTarget.data("uid"));
                        console.log(dataItem);
                        $("#planDayGrid").data("kendoGrid").dataSource.remove(dataItem);
                        $("#planDayGrid").data("kendoGrid").dataSource.sync();
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
            setDatePlan:setDatePlan,
            getDay:getDay,
            isTransportCompany:isTransportCompany
        }
    }
)();