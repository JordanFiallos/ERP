//UPDATE
$(document).ready(function () {
    // Set the employee ID when the modal is shown
    $('#confirmUpdateModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var employeeId = button.data('id');
        $('#confirmUpdateButton').attr('href', '/update/' + employeeId);
    });
});

//DELETE
$(document).ready(function () {
    // Set the employee ID when the modal is shown
    $('#confirmDeleteModal').on('show.bs.modal', function (event) {
        const currentUrl = window.location.href;
        
        var button = $(event.relatedTarget);
        var id = button.data('id');
        //$('#confirmDeleteButton').attr('href', '/delete/' + employeeId);
        $('#confirmDeleteButton').attr('href', currentUrl + '/delete/' + id);
    });
});
