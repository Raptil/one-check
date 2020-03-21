INSERT INTO company (company_name) values ('Lidl');
COMMIT;
INSERT INTO product_item (id_company, product_name, category, price, description, img_url)
VALUES (1, 'Ataulfo mango', 'fresh fruits', 0.89,
        'unique creamy, buttery texture, tender and delicious in salads, smoothies, desserts and salsa',
        'https://production-endpoint.azureedge.net/images/64RJGCPO75FJAC1GF0QJ0C0/b670c13b-7e09-4893-9e47-5b1ff948f818/178389_500x500.jpg');
INSERT INTO product_item (id_company, product_name, category, price, description, img_url)
VALUES (1, 'Avocado', 'fresh fruits', 0.79,
        'great spread for toast',
        'https://production-endpoint.azureedge.net/images/6KS3ICHJBSQJ0C3O6KO30/ed4cc4b8-f2d3-48fa-adab-1956b6033e2d/58923_500x500.jpg');
-- TODO: add more product
COMMIT;

INSERT INTO user_dictionary (user_name, first_name, address) values ('User', 'User', 'Boulevar Caputsinov 73');
INSERT INTO user_dictionary (user_name, first_name, address) values ('KrzysztofDev', 'Krzysztof', 'Boulevar Caputsinov 21');
INSERT INTO user_dictionary (user_name, first_name, address) values ('DanDan', 'Dan', 'Boulevar Caputsinov 181');
COMMIT;

INSERT INTO basket (id_user, active_flg, total_price) VALUES (1, 'active', 20);
COMMIT;

INSERT INTO check_products (id_user, id_basket, total_price, description) values (1, 1, 20, 'check-description');
COMMIT;
INSERT INTO check_products (id_user, total_price, description) values (2, 20, 'check-description');
INSERT INTO check_products (id_user, total_price, description) values (3, 20, 'check-description');
COMMIT;

INSERT INTO check_to_product (id_check, id_product) VALUES (2, 1);
INSERT INTO check_to_product (id_check, id_product) VALUES (3, 1);
INSERT INTO check_to_product (id_check, id_product) VALUES (3, 2);
COMMIT;