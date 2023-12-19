/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

var seleccionActual = "";

selectInput.onchange = function(e) {
   seleccionActual = e.target.value;
}

function generalFilter(tableId,classFiltered) {
    var input = document.getElementById('searchInput');
    var filter = input.value.toUpperCase();
    var tableBody = document.getElementById(tableId);
    var rows = tableBody.getElementsByTagName('tr');
    
    if(classFiltered === null) {
       classFiltered = "." + seleccionActual;
    }
    
    for (let i = 0; i < rows.length; i++) {
        const productName = rows[i].querySelector(classFiltered).innerText.toUpperCase();
        if (productName.indexOf(filter) > -1) {
            rows[i].style.display = '';
        } else {
            rows[i].style.display = 'none';
        }
    }
}