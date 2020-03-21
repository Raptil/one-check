'use strict';
let basketArea = document.querySelector('#basket-area');

window.onload = function () {
    fetch("basket/",
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
        .then(checkProductDto => {
            console.log(checkProductDto);
            checkProductDto.forEach(function (item, product) {
                console.log(item);
                drawProduct(item);
            });
        })
        .catch(() => console.log('Error messages'));
};

function drawProduct(checkProductDto) {
    var product = checkProductDto.product;
    var basket  =  document.createElement('ul');

    var productImg = document.createElement('li');
    var img = new Image();
    img.src = product.imgUrl;
    productImg.appendChild(img);
    basket.appendChild(productImg);

    var productName = document.createElement('li');
    var text = document.createTextNode(product.productName);
    productName.appendChild(text);
    basket.appendChild(productName);

    var productCategory = document.createElement('li');
    var text = document.createTextNode(product.category);
    productCategory.appendChild(text);
    basket.appendChild(productCategory);

    var productPrice = document.createElement('li');
    var text = document.createTextNode(product.price + ' $');
    productPrice.appendChild(text);
    basket.appendChild(productPrice);

    var productCount = document.createElement('li');
    var text = document.createTextNode('count ' + checkProductDto.count);
    productCount.appendChild(text);
    basket.appendChild(productCount);

    basketArea.appendChild(basket);
}
