'use strict';
let basketArea = document.querySelector('#basket-area');
let checkArea = document.querySelector('#check-area');
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

        fetch("checks/",
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
                .then(checkDto => {
                    checkDto.forEach(function (item, product) {
                        console.log(item);
                        drawCheck(item);
                    });
                })
                .catch(() => console.log('Error messages'));
};
function drawCheck(checkDto) {
        var checkElement  =  document.createElement('ul');

        var checkPrice = document.createElement('li');
        var text = document.createTextNode(checkDto.totalPrice);
        checkPrice.appendChild(text);
        checkElement.appendChild(checkPrice);

        var userName = document.createElement('li');
        var text = document.createTextNode(checkDto.user.userName);
        userName.appendChild(text);
        checkElement.appendChild(userName);

        var userAddr = document.createElement('li');
        var text = document.createTextNode(checkDto.user.address);
        userAddr.appendChild(text);
        checkElement.appendChild(userAddr);

        var productBut = document.createElement('li');
        var button = document.createElement('button');
        button.setAttribute('type', 'button');
        button.setAttribute('class', 'btn btn-primary');
        var text = document.createTextNode('Add');
        button.appendChild(text);

        productBut.appendChild(button);
        productElement.appendChild(productBut);

        checkArea.appendChild(checkElement);
}
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

    var productCount = document.createElement('li');
    var text = document.createTextNode('Count ' + checkProductDto.count);
    productCount.appendChild(text);
    basket.appendChild(productCount);

    var productPrice = document.createElement('li');
    var text = document.createTextNode('Total ' + (Math.round(product.price * checkProductDto.count * 100) / 100).toFixed(2) + ' $');
    productPrice.appendChild(text);
    basket.appendChild(productPrice);

    basketArea.appendChild(basket);
}
