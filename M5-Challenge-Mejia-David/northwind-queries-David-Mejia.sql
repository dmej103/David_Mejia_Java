use northwind;

-- What are the categories of products in the database?
SELECT  category
FROM products;

-- What products are made by Dell?
SELECT *
FROM products
WHERE product_name LIKE 'Dell%';

-- List all the orders shipped to Pennsylvania.
SELECT *
FROM orders o
WHERE ship_state LIKE 'Pennsylvania';

-- List the first name and last name of all employees with last names that start with the letter W.
SELECT first_name, last_name
FROM employees e ;
WHERE last_name LIKE 'W%';

-- List all customers from zip codes that start with 55.
SELECT *
FROM customers c
WHERE postal_code LIKE '55%';

-- List all customers from zip codes that end with 0.
SELECT *
FROM customers c
WHERE postal_code LIKE '%0';

-- List the first name, last name, and email for all customers with a ".org" email address.
SELECT first_name, last_name, email
FROM customers c 
WHERE email LIKE '%.org';

-- List the first name, last name, and phone number for all customers from the 202 area code.
SELECT first_name, last_name, phone
FROM customers c
WHERE phone LIKE '%(202)%';

-- List the first name, last name, and phone number for all customers from the 202 area code, ordered by last name, first name.
SELECT last_name, first_name, phone
FROM customers c 
WHERE phone LIKE '%(202)%'
ORDER BY last_name, first_name;




