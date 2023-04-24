CREATE TABLE IF NOT EXISTS clients
(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(75) NOT NULL,
    lastName VARCHAR(75) NOT NULL,
    docNumber VARCHAR(11) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS invoice
(
    id INT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    client_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE IF NOT EXISTS products
(
    id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(150) NOT NULL,
    code VARCHAR(50) NOT NULL,
    stock INT NOT NULL,
    price DECIMAL(10,2),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS invoice_details
(
    invoice_detail_id INT NOT NULL AUTO_INCREMENT,
    invoice_id INT,
    subtotal DECIMAL(10,2) NOT NULL,
    applied_price DECIMAL(10,2) NOT NULL,
    amount INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (invoice_detail_id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
ALTER TABLE clients
ADD COLUMN status BOOLEAN NOT NULL DEFAULT TRUE;

