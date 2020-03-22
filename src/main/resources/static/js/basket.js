'use strict';
let basketArea = document.querySelector('#basket-area');
let checkArea = document.querySelector('#check-area');
let checkBasketArea = document.querySelector('#check-basket');
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

        getChecks()
        getBasketChecks();

};
function getChecks(){
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
                         while (checkArea.firstChild) {
                            checkArea.removeChild(checkArea.firstChild);
                         }
                        checkDto.forEach(function (item, product) {
                            console.log(item);
                            drawCheck(item);
                        });
                    })
                    .catch(() => console.log('Error messages'));
}
function getBasketChecks(){
    fetch("checkBasket/",
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
                                while (checkBasketArea.firstChild) {
                                    checkBasketArea.removeChild(checkBasketArea.firstChild);
                                }
                                checkDto.forEach(function (item, product) {
                                    console.log(item);
                                    drawBasketCheck(item);
                                });
                                             var button = document.createElement('button');
                                                    button.setAttribute('type', 'button');
                                                    button.setAttribute('class', 'btn btn-success');

                                                    var text = document.createTextNode('Pay');
                                                    button.appendChild(text);
                                             checkBasketArea.appendChild(button);
                                              var buttonPdf = document.createElement('button');
                                               buttonPdf.setAttribute('type', 'button');
                                               buttonPdf.setAttribute('class', 'btn btn-info');
                                                var textPdf = document.createTextNode('Pdf');
                                               buttonPdf.appendChild(textPdf);
                                               buttonPdf.onclick = function(){
                                                                   fetch("api/pdf/basket/",
                                                                   {
                                                                       method:"GET",
                                                                       headers: {
                                                                           'Accept': 'application/json',
                                                                           'Content-type': 'application/json'
                                                                       }
                                                                   })
                                                                   .then (response => {
                                                                       if (response.status!=200){
                                                                           return Promise.reject();
                                                                       }
                                                                   })
                                                                   .then (() => {
                                                                       console.log('pdf');
                                                                   })
                                                                   .catch(() => console.log('pdf'));
                                                               }
                                               checkBasketArea.appendChild(buttonPdf);
                            })
                            .catch(() => console.log('Error messages'));
}
function drawBasketCheck (checkDto) {

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
var div = document.createElement('div');
div.setAttribute('class','card');
div.appendChild(checkElement);
            checkBasketArea.appendChild(div);

}
function drawCheck(checkDto) {
        var checkElement  =  document.createElement('ul');
        checkElement.setAttribute('style','"margin-bottom: 10px;"');
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
        button.onclick = function(){
                    fetch("addCheck",
                    {
                        method:"POST",
                        body: JSON.stringify({id: checkDto.id}),
                        headers: {
                            'Accept': 'application/json',
                            'Content-type': 'application/json'
                        }
                    })
                    .then (response => {
                        if (response.status!=200){
                            return Promise.reject();
                        }
                    })
                    .then (() => {
                        console.log('indusi');
                        getChecks();
                        getBasketChecks();
                    })
                    .catch(() => console.log('Error check'));
                }
        productBut.appendChild(button);
        checkElement.appendChild(productBut);
var div = document.createElement('div');
div.setAttribute('class','card');
div.appendChild(checkElement);
        checkArea.appendChild(div);
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

      if(product.id == 1){
          var li = document.createElement('li');
          var button = document.createElement('button');
          button.setAttribute('type', 'button');
          button.setAttribute('class', 'btn btn-success');
          var text = document.createTextNode('Publish');
          button.appendChild(text);
          li.appendChild(button);
          basket.appendChild(li);
        }


    basketArea.appendChild(basket);
}
