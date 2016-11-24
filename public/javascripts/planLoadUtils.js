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

                }


            ]
        }).data("kendoGrid");
    }

    var planShipmentGridView = function () {
        return $("#planShipmentGridView").kendoGrid({
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

            //change:onChange,
            /*dataBound: function (e) {
             $("#gridView").find('.k-icon.k-i-collapse').trigger('click');
             },*/

            columns: [
                {
                    //field: "senderEnterprise.name",
                    title: "Предприятие отправитель",
                    width: "100px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    groupable: false,
                    editable: false,

                    //  editor: enterpriseDropDownEditor, template: "#=senderEnterprise.name#"
                },
                {
                    //  field: "typeShipment",
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
                    filterable: false,
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,

                },
                {
                    // field: "dateShipmentDispatcher",
                    title: "Дата отгрузки в ПЭ",
                    width: "100px",
                    headerAttributes: gridUtils.headerFormat,
                    // attributes: {class:"#=moment.duration(moment(dateShipmentDispatcher).diff(moment('01-01-2016'))).asDays() > 0 ? 'table-cell-red':'table-cell'#  "},
                    //attributes: {class:"#=planShipmentUtil.getDay(dateShipmentDispatcher) ? 'table-cell-red':'table-cell'#  "},
                    filterable: false,
                    groupable: false,
                    format: "{0:dd-MM-yyyy}",
                    //template:'#=  moment(dateShipmentDispatcher).format("DD-MM-YYYY")#'


                },
                {
                    // field: "existInStore",
                    title: "Наличие на складе",
                    width: "80px",
                    headerAttributes: gridUtils.headerFormat,
                    attributes: gridUtils.columnFormat,
                    filterable: false,
                    groupable: false,
                    template: '<input type="checkbox" #= existInStore ? "checked=checked" : "" # disabled="disabled" ></input>'
                },
                {
                    //  field: "dateToStore",
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

    return {
        factLoadGridView: factLoadGridView,
        planShipmentGridView: planShipmentGridView
    }


})();
