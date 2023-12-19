/* global totalQuantity */

/*function addToCart(button) {
 const row = button.closest('tr');
 const productName = row.querySelector('.productName').innerText;
 const productSupplier = row.querySelector('.productSupplier').innerText;
 const productDesciption = row.querySelector('.productDesciption').innerText;
 const productQuantity = row.querySelector('.productQuantity').value;
 const productPrice = parseFloat(row.querySelector('.productPrice').innerText.replace('$', ''));
 
 
 
 const cartItems = document.getElementById('cartItems');
 
 // Verificar si el producto ya está en el carrito
 const existingItem = Array.from(cartItems.children).find(item =>
 item.getAttribute('data-product-name') === productName
 );
 
 if (existingItem) {
 const existingQuantity = parseInt(existingItem.getAttribute('data-product-quantity'));
 const existingPrice = parseFloat(existingItem.getAttribute('data-product-price'));
 
 totalQuantity = existingQuantity + parseInt(productQuantity);
 const totalPrice = existingPrice + (parseFloat(productPrice) * parseInt(productQuantity));
 
 existingItem.querySelector('span').innerText = `${productName} - ${productSupplier} - ${productDesciption} - Quantity: ${totalQuantity} - $${totalPrice.toFixed(2)}`;
 existingItem.setAttribute('data-product-quantity', totalQuantity);
 existingItem.setAttribute('data-product-price', totalPrice);
 } else {
 const newItem = document.createElement('li');
 newItem.className = 'list-group-item cart-item';
 newItem.setAttribute('data-product-name', productName);
 newItem.setAttribute('data-product-quantity', productQuantity);
 newItem.setAttribute('data-product-price', parseFloat(productPrice) * parseInt(productQuantity));
 newItem.innerHTML = `<span>${productName} - ${productSupplier} - ${productDesciption} - Quantity: ${productQuantity} - $${(parseFloat(productPrice) * parseInt(productQuantity)).toFixed(2)}</span>
 <button class="btn btn-danger btn-sm remove mx-2" onclick="removeFromCart(this)">
 Remove
 </button>`;
 cartItems.appendChild(newItem);
 }
 
 const totalQuantityElement = document.getElementById('productQuantity');
 totalQuantityElement.value = productQuantity;
 
 }
 
 function removeFromCart(button) {
 const cartItems = document.getElementById('cartItems');
 cartItems.removeChild(button.parentElement);
 }*/


//FilterProduct
/*
function filterProducts() {
    const input = document.getElementById('searchInput');
    const filter = input.value.toUpperCase();
    const tableBody = document.getElementById('productTableBody');
    const rows = tableBody.getElementsByTagName('tr');

    for (let i = 0; i < rows.length; i++) {
        const productName = rows[i].querySelector('.productName').innerText.toUpperCase();
        if (productName.indexOf(filter) > -1) {
            rows[i].style.display = '';
        } else {
            rows[i].style.display = 'none';
        }
    }
}
*/

// BUY PRODUCTS
$(document).ready(function () {
    // Set the product ID when the modal is shown
    $('#confirmUpdateModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var productId = button.data('id');
        $('#confirmUpdateButton').attr('href', '/updateProducts2/' + productId);
    });
});

function confirmPurchase(button) {
    var restminim = button.getAttribute("data-restminim");
    console.log("restminim:", restminim);
    if (restminim !== null) {
        var confirmButton = document.getElementById("confirmUpdateButton");
        confirmButton.disabled = restminim <= 0;
    }
}



/*function buyItems() {
 const cartItems = document.getElementById('cartItems');
 const items = cartItems.getElementsByClassName('cart-item');
 
 
 for (let i = 0; i < items.length; i++) {
 const item = items[i];
 const itemName = item.getAttribute('data-product-name');
 const itemQuantity = parseInt(item.getAttribute('data-product-quantity'));
 const itemPrice = parseFloat(item.getAttribute('data-product-price'));
 }
 
 
 }*/
