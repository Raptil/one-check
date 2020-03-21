'use strict';
let productArea = document.querySelector('#product-area');

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
            products.forEach(function (item, product) {
                drawProduct(item);
            });
        })
        .catch(() => console.log('Error messages'));
};

function drawProduct(product) {
    var productElement  =  document.createElement('ul');

    var productImg = document.createElement('li');
    var img = new Image();
    img.src = product.imgUrl;
    productImg.appendChild(img);
    productElement.appendChild(productImg);

    var productName = document.createElement('li');
    var text = document.createTextNode(product.productName);
    productName.appendChild(text);
    productElement.appendChild(productName);

    var productCategory = document.createElement('li');
    var text = document.createTextNode(product.category);
    productCategory.appendChild(text);
    productElement.appendChild(productCategory);

    var productPrice = document.createElement('li');
    var text = document.createTextNode(product.price + ' $');
    productPrice.appendChild(text);
    productElement.appendChild(productPrice);

    var productBut = document.createElement('li');
    var button = document.createElement('button');
    button.setAttribute('type', 'button');
    button.setAttribute('class', 'btn btn-primary');
    var text = document.createTextNode('Add');
    button.appendChild(text);
    productBut.appendChild(button);
    productElement.appendChild(productBut);

    productArea.appendChild(productElement);
}
