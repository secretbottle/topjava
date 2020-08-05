$(function () {
    makeEditable({
            ajaxUrl: "admin/users/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "name"
                    },
                    {
                        "data": "email"
                    },
                    {
                        "data": "roles"
                    },
                    {
                        "data": "enabled"
                    },
                    {
                        "data": "registered"
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
                        "asc"
                    ]
                ]
            })
        }
    );
});

function activeStatus(id) {
    let enabled = $(":checkbox#" + id).prop("checked");
    $.ajax({
        type: "POST",
        url: context.ajaxUrl + id,
        data: "enabled=" + enabled,
        success: function (){
            successNoty("Change success")
            $(":checkbox#" + id).prop("checked", enabled)
            $("#userRow" + id).attr("enabled", enabled)
        },
        fail: function (jqXHR) {
            failNoty(jqXHR);
            $(":checkbox#" + id).prop("checked", !enabled)
            $("#userRow" + id).attr("enabled", !enabled)
        }
    })
}