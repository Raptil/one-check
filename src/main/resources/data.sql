INSERT INTO company (company_name) values ('LIDL');
COMMIT;
INSERT INTO product_item (id_company, product_name, category, price, description, img_url)
VALUES (1, 'Ataulfo mango', 'fresh fruits', 0.89,
        'unique creamy, buttery texture, tender and delicious in salads, smoothies, desserts and salsa',
        'https://production-endpoint.azureedge.net/images/64RJGCPO75FJAC1GF0QJ0C0/b670c13b-7e09-4893-9e47-5b1ff948f818/178389_500x500.jpg');
INSERT INTO product_item (id_company, product_name, category, price, description, img_url)
VALUES (1, 'Avocado', 'fresh fruits', 0.79,
        'great spread for toast',
        'https://production-endpoint.azureedge.net/images/6KS3ICHJBSQJ0C3O6KO30/ed4cc4b8-f2d3-48fa-adab-1956b6033e2d/58923_500x500.jpg');

COMMIT;
