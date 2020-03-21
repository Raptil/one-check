'use strict';
var productArea = document.querySelector('#productArea');

window.onload = function () {
    alert('Отрисовка продукта');
    fetch("products/",
        {
            method: "GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.status !== 200) {

                return Promise.reject();
            }
            return response.json();
        })
        .then(products => {
            products.forEach(function (item, product) {
                drawProduct(product);
            });
        })
        .catch(() => console.log('Error messages'));
};

function drawProduct(product) {
    alert('Отрисовка продукта');
    var productElement = document.createElement('li');
    var label = document.createElement("label");
    label.setAttribute("value", product.productName);
    productElement.appendChild(label);

    productArea.appendChild(productElement)
}
