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
document.addEventListener("DOMContentLoaded", function () {
    // Oculta el botón de restablecimiento al cargar la página
    document.getElementById("resetFilter").style.display = "none";
});

function filterTable() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("filterDate");
    filter = input.value;
    table = document.querySelector(".table");
    tr = table.getElementsByTagName("tr");

    // Itera sobre todas las filas y muestra/oculta según la fecha de filtrado
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

    // Muestra el botón de restablecimiento si hay filas filtradas
    var filteredRows = Array.from(tr).filter(row => row.style.display === "none");
    document.getElementById("resetFilter").style.display = filteredRows.length > 0 ? "inline" : "none";
}

function resetFilter() {
    // Muestra todas las filas
    var table = document.querySelector(".table");
    var tr = table.getElementsByTagName("tr");
    for (var i = 0; i < tr.length; i++) {
        tr[i].style.display = "";
    }

    // Oculta el botón de restablecimiento
    document.getElementById("resetFilter").style.display = "none";
}
