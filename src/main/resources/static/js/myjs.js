$(document).ready(function () {
    // Set the employee ID when the modal is shown
    $('#confirmDeleteModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var employeeId = button.data('id');
        $('#confirmDeleteButton').attr('href', '/delete/' + employeeId);
    });
});
