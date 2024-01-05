/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

var seleccionActual = "";

selectInput.onchange = function (e) {
    seleccionActual = e.target.value;
    generalFilter(seleccionActual);
}

function generalFilter(classFiltered) {
    classFiltered = "." + classFiltered;
    var input = document.getElementById('searchInput');
    var filter = input.value.toUpperCase();
    var tableBody = document.getElementById('filteredTableBody');
    var rows = tableBody.getElementsByTagName('tr');

    if (seleccionActual == "") {
        seleccionActual = classFiltered;
    } else if (seleccionActual != classFiltered) {
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

// FilterDate
var originalRows = [];

document.addEventListener("DOMContentLoaded", function () {
    // Obtiene todas las filas y las almacena en originalRows
    var table = document.querySelector(".table");
    var tr = table.getElementsByTagName("tr");

    for (var i = 0; i < tr.length; i++) {
        originalRows.push(tr[i].outerHTML);
    }
});

function filterTable() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("filterDate");
    filter = input.value;
    table = document.querySelector(".table");
    tr = table.getElementsByTagName("tr");

    // Itera sobre todas las filas y oculta aquellas que no coincidan con la fecha de filtrado
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0]; // Cambia el índice a 0 para la columna "Month"
        if (td) {
            txtValue = td.textContent || td.innerText;
            // Utiliza indexOf para verificar si la fecha contiene la cadena de filtro
            if (txtValue.indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }

    // Muestra el botón de restablecimiento
    document.getElementById("resetFilter").style.display = "inline";
}

function resetFilter() {
    // Muestra todas las filas originales
    var table = document.querySelector(".table");
    table.innerHTML = originalRows.join("");

    // Oculta el botón de restablecimiento
    document.getElementById("resetFilter").style.display = "none";
}