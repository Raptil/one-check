'use strict';
let productArea = document.querySelector('#productArea');

window.onload = function () {
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
            console.log(products);
            products.forEach(function (item, product) {
                drawProduct(product);
            });
        })
        .catch(() => console.log('Error messages'));
};

function drawProduct(product) {
    var productElement = document.createElement('li');
    var label = document.createElement("label");
    var text = document.createTextNode(product.productName);
    label.appendChild(text);
    productElement.appendChild(label);
    productArea.appendChild(productElement);

    console.log(product);
}
