$(function () {
    makeEditable({
            ajaxUrl: "profile/meals/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "dateTime"
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "desc"
                    ]
                ]
            })
        }
    );
});

function filter() {
    $.ajax({
        type: "GET",
        url: context.ajaxUrl + "filter",
        data: "startDate=" + $("#startDate").val() + "&endDate=" + $("#endDate").val()
            + "&startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val()
    }).done(function (data) {
        context.datatableApi.clear().rows.add(data).draw();
        successNoty("Filtered");
    });
}

function resetFilter() {
    $("#filter")[0].reset();
    filter();
}