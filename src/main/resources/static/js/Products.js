// UPDATE
$(document).ready(function () {
    // Set the product ID when the modal is shown
    $('#confirmUpdateModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var productId = button.data('id');
        $('#confirmUpdateButton').attr('href', '/updateProducts/' + productId);
    });
});

// DELETE
$(document).ready(function () {
    // Set the product ID when the modal is shown
    $('#confirmDeleteModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var productId = button.data('id');
        $('#confirmDeleteButton').attr('href', '/deleteProducts/' + productId);
    });
});
