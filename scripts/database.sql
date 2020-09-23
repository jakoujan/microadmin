CREATE TABLE IF NOT EXISTS sequences (
    id VARCHAR(25) NOT NULL PRIMARY KEY,
    description VARCHAR(120) NOT NULL,
    sequence INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS `user` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(45) NULL UNIQUE,
    `email` VARCHAR(60) NULL,
    `password` VARCHAR(45) NULL,
    `name` VARCHAR(120) NOT NULL,
    `active` INTEGER NOT NULL DEFAULT 1,
    `modules` TEXT NOT NULL
);


CREATE TABLE IF NOT EXISTS `table_chair` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR (40) NOT NULL,
    `places` INT NOT NULL,
    `active` BOOLEAN NOT NULL DEFAULT TRUE
);


CREATE TABLE `country` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(60) NOT NULL
);

CREATE TABLE `state` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(60) NOT NULL
);

CREATE TABLE `customer` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `business_name` VARCHAR(60) NOT NULL,
    `city` VARCHAR(60) NOT NULL,
    `contact` VARCHAR(60) NOT NULL,
    `country` INTEGER NOT NULL,
    `external_number` VARCHAR(60) NOT NULL,
    `county` VARCHAR(60) NOT NULL,
    `internal_number` VARCHAR(60),
    `postal_code` VARCHAR(60) NOT NULL,
    `settlement` VARCHAR(60) NOT NULL,
    `state` INTEGER NOT NULL,
    `street` VARCHAR(60) NOT NULL,
    `tax_id` VARCHAR(60) NOT NULL,
    `telephone` VARCHAR(60) NOT NULL,
    `email` VARCHAR(60) NOT NULL,
    `active` BOOLEAN NOT NULL DEFAULT TRUE,
    `editable` BOOLEAN NOT NULL DEFAULT TRUE,
CONSTRAINT `fk_customer_country`
    FOREIGN KEY (`country`)
    REFERENCES `country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_customer_state`
    FOREIGN KEY (`state`)
    REFERENCES `state` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `supplier` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `business_name` VARCHAR(60) NOT NULL,
    `city` VARCHAR(60) NOT NULL,
    `contact` VARCHAR(60) NOT NULL,
    `country` INTEGER NOT NULL,
    `external_number` VARCHAR(60) NOT NULL,
    `county` VARCHAR(60) NOT NULL,
    `internal_number` VARCHAR(60) NOT NULL,
    `postal_code` VARCHAR(60) NOT NULL,
    `settlement` VARCHAR(60) NOT NULL,
    `state` INTEGER NOT NULL,
    `street` VARCHAR(60) NOT NULL,
    `tax_id` VARCHAR(60) NOT NULL,
    `telephone` VARCHAR(60) NOT NULL,
    `email` VARCHAR(60) NOT NULL,
    `active` BOOLEAN NOT NULL DEFAULT TRUE,
CONSTRAINT `fk_supplier_country`
    FOREIGN KEY (`country`)
    REFERENCES `country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_supplier_state`
    FOREIGN KEY (`state`)
    REFERENCES `state` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `brand` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `brand` VARCHAR NOT NULL,
  `active` BOOLEAN NOT NULL DEFAULT TRUE
);


CREATE TABLE `payment_method` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `method` VARCHAR NOT NULL,
  `description` VARCHAR,
  `active` BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE `tax_type` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `tax_type` VARCHAR NOT NULL,
  `tax_type_description` VARCHAR,
  `active` BOOLEAN NOT NULL DEFAULT TRUE,
  `percentage` INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE `section` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `section` VARCHAR NOT NULL,
  `description` VARCHAR,
  `active` BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE `unit` (
    `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `unit` VARCHAR NOT NULL,
    `description` VARCHAR,
  `active` BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS `product_type` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR (40) NOT NULL,
    `active` BOOLEAN NOT NULL DEFAULT TRUE
);


CREATE TABLE product (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `barcode` VARCHAR,
  `brand` INTEGER NOT NULL,
  `unit` INTEGER NOT NULL,
  `description` VARCHAR NOT NULL,
  `long_description` TEXT,
  `section` INTEGER NOT NULL,
  `tax_type` INTEGER NOT NULL,
  `retail_price` DECIMAL(8,2) NOT NULL,
  `supplier_price` DECIMAL(8,2) NOT NULL,
  `promo_price` DECIMAL(8,2) NOT NULL,
  `promotion` BOOLEAN NOT NULL,
  `minimum_stock` DECIMAL(8,2) NOT NULL,
  `active` BOOLEAN NOT NULL DEFAULT TRUE,
  `type` INTEGER NOT NULL DEFAULT 1,
CONSTRAINT `brand_product_fk`
    FOREIGN KEY (`BRAND`)
    REFERENCES `BRAND` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `product_type_fk`
    FOREIGN KEY (`type`)
    REFERENCES `product_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   CONSTRAINT `tax_product_type_fk`
    FOREIGN KEY (`tax_type`)
    REFERENCES TAX_TYPE (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   CONSTRAINT `section_product_fk`
    FOREIGN KEY (`section`)
    REFERENCES SECTION (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   CONSTRAINT `unit_product_fk`
    FOREIGN KEY (`unit`)
    REFERENCES `UNIT` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `sale_status` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR (40) NOT NULL
);


CREATE TABLE IF NOT EXISTS `order_comand` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `order_date` TIMESTAMP NOT NULL,
  `responsible` VARCHAR NOT NULL,
  `waiter` INTEGER NOT NULL,
  `cashier` INTEGER NOT NULL,
  `table_chair` INTEGER NOT NULL,
  `payment_method` INTEGER NOT NULL,
  `status` INTEGER NOT NULL,
  `service_type` INTEGER NOT NULL DEFAULT 1,
  `total_amount` DECIMAL(10,2) NOT NULL,
  CONSTRAINT `waiter_order_fk`
    FOREIGN KEY (`waiter`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cashier_order_fk`
    FOREIGN KEY (`cashier`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `table_order_fk`
    FOREIGN KEY (`table_chair`)
    REFERENCES `table_chair` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `pay_method_order_fk`
    FOREIGN KEY (`payment_method`)
    REFERENCES `payment_method` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `status_order_fk`
    FOREIGN KEY (`status`)
    REFERENCES `sale_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);



CREATE TABLE `retail` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `retail_date` TIMESTAMP NOT NULL,
  `cashier` INTEGER NOT NULL,
  `payment_method` INTEGER NOT NULL,
  `status` INTEGER NOT NULL,
  `total_amount` DECIMAL(10,2) NOT NULL,
  CONSTRAINT `cashier_retail_fk`
    FOREIGN KEY (`cashier`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `pay_method_retail_fk`
    FOREIGN KEY (`payment_method`)
    REFERENCES `payment_method` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `status_retail_fk`
    FOREIGN KEY (`status`)
    REFERENCES `sale_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);



CREATE TABLE IF NOT EXISTS `sale` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `sale_date` TIMESTAMP NOT NULL,
    `total_amount` DECIMAL(10,2) NOT NULL,
    `quantity` DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS `order_sale` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `order_comand` INTEGER NOT NULL,
    `sale` INTEGER NOT NULL,
    CONSTRAINT `order_fk`
        FOREIGN KEY (`order_comand`)
        REFERENCES `order_comand` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT `sale_fk`
        FOREIGN KEY (`sale`)
        REFERENCES `sale` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `retail_sale` (
    `retail` INTEGER NOT NULL,
    `sale` INTEGER NOT NULL,
    CONSTRAINT `retail_fk`
        FOREIGN KEY (`retail`)
        REFERENCES `retail` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT `sale_retail_sale_fk`
        FOREIGN KEY (`sale`)
        REFERENCES `sale` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);



CREATE TABLE IF NOT EXISTS `product_order` (
    `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `order_comand` INTEGER NOT NULL,
    `product` INTEGER NOT NULL,
    `quantity` DECIMAL(10,2) NOT NULL,
    CONSTRAINT `order_comand_product_order_fk`
        FOREIGN KEY (`order_comand`)
        REFERENCES `order_comand` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT `product_fk`
        FOREIGN KEY (`product`)
        REFERENCES `product` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);


CREATE UNIQUE INDEX `bar_code_idx`
 ON `PRODUCT`
 ( `barcode` ASC );

CREATE TABLE IF NOT EXISTS `product_kit` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `product` INT NOT NULL,
    `products` TEXT,
    CONSTRAINT `product_kit`
            FOREIGN KEY (`product`)
            REFERENCES `product` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE product_supplier (
    product INT NOT NULL,
    supplier INT NOT NULL,
    CONSTRAINT PRODUCT_SUPPLIER_PK PRIMARY KEY (product, supplier),
    CONSTRAINT PRODUCT_SUPPLIER_P_FK FOREIGN KEY (product) REFERENCES PRODUCT(id),
    CONSTRAINT PRODUCT_SUPPLIER_S_FK FOREIGN KEY (supplier) REFERENCES SUPPLIER(id)
);

CREATE TABLE product_conversion_factor (
    product INT NOT NULL,
    unit INT NOT NULL,
    factor INT NOT NULL,
    CONSTRAINT product_cf_pk PRIMARY KEY (product, unit),
    CONSTRAINT product_cf_p_fk FOREIGN KEY (product) REFERENCES PRODUCT(id),
    CONSTRAINT product_cf_u_fk FOREIGN KEY (unit) REFERENCES UNIT(id)
);

CREATE FORCE VIEW `PRODUCT_VIEW`(ID, BRAND, BARCODE, DESCRIPTION, MINIMUM_STOCK, SUPPLIER_PRICE, RETAIL_PRICE, `SECTION`, UNIT, ACTIVE) AS
SELECT P.ID AS ID, B.BRAND AS BRAND, P.BARCODE AS BARCODE, P.DESCRIPTION AS DESCRIPTION, P.MINIMUM_STOCK AS MINIMUM_STOCK, 
P.SUPPLIER_PRICE AS SUPPLIER_PRICE, P.RETAIL_PRICE AS RETAIL_PRICE, S.`SECTION` AS `SECTION`, U.UNIT AS UNIT, P.ACTIVE AS ACTIVE
FROM PRODUCT P INNER JOIN BRAND B ON (B.ID = P.BRAND) INNER JOIN `SECTION` S ON (S.ID = P.`SECTION`) INNER JOIN UNIT U ON (U.ID = P.UNIT);


CREATE TABLE `STORE` (
    `ID` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `DESCRIPTION` VARCHAR(120) NOT NULL,
    `ADDRESS` TEXT,
    `ACTIVE` BOOLEAN NOT NULL DEFAULT TRUE
);


CREATE TABLE `WORK_STATION` (
    `ID` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `STORE` INT NOT NULL,
    `DESCRIPTION` VARCHAR(120) NOT NULL,
    `ACTIVE` BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT WORK_STATION_STORE FOREIGN KEY (STORE) REFERENCES STORE(id)
);

    

CREATE TABLE stock (
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  product INTEGER NOT NULL,
  store INT NOT NULL,
  current_stock DECIMAL(8,2) NOT NULL,
  last_added TIMESTAMP NOT NULL,
  CONSTRAINT product_stock_fk
    FOREIGN KEY (product)
    REFERENCES PRODUCT (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT product_stock_store_fk
    FOREIGN KEY (store)
    REFERENCES STORE (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS `cash_action` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `action_date` TIMESTAMP NOT NULL,
  `cashier` INTEGER NOT NULL,
  `initial_amount` DECIMAL(10,2) NOT NULL,
  `final_amount` DECIMAL(10,2) NOT NULL,
  `action_status` INTEGER NOT NULL,
  `active` BOOLEAN NOT NULL DEFAULT TRUE,
  CONSTRAINT `cashier_action_fk`
    FOREIGN KEY (`cashier`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE FORCE VIEW CURRENT_STOCK AS
SELECT P.ID, P.BARCODE, P.DESCRIPTION, S.`SECTION` AS `SECTION`, ST.CURRENT_STOCK FROM PRODUCT P INNER JOIN `SECTION` S ON (S.ID = P.`SECTION`) LEFT OUTER JOIN STOCK ST ON (ST.PRODUCT = P.ID);

CREATE FORCE VIEW ORDER_VIEW AS
SELECT O.ID AS ID, TC.`NAME` AS `TABLE_NAME`, RESPONSIBLE, TOTAL_AMOUNT, O.STATUS FROM ORDER_COMAND O INNER JOIN TABLE_CHAIR TC ON (TC.ID = O.TABLE_CHAIR);

CREATE FORCE VIEW SALES_REPORT_VIEW AS
SELECT S.ID AS SALE, SALE_DATE, TOTAL_AMOUNT, QUANTITY, OS.ID AS ORDER_COMAND FROM SALES S INNER JOIN ORDER_SALE OS ON (OS.SALE = S.ID);


INSERT INTO sequences (id, description, sequence) VALUES ('sale', 'Ventas', 0);

INSERT INTO `USER` (USERNAME, EMAIL, PASSWORD, `NAME`, ACTIVE, MODULES) 
	VALUES ('admin', 'e.rangel@ispc.com.mx', 'password', 'Administrador del sistema', TRUE, '[{"id":"administration","submodules":[{"id":"users","access":true,"write":true},{"id":"customers","access":true,"write":true},{"id":"configuration","access":true,"write":true}]}]');

INSERT INTO `COUNTRY` VALUES (52, 'México');

INSERT INTO `STATE` VALUES (1, 'Aguascalientes' );
INSERT INTO `STATE` VALUES (2, 'Baja California' );
INSERT INTO `STATE` VALUES (3, 'Baja California Sur' );
INSERT INTO `STATE` VALUES (4, 'Campeche' );
INSERT INTO `STATE` VALUES (5, 'Coahuila de Zaragoza' );
INSERT INTO `STATE` VALUES (6, 'Colima' );
INSERT INTO `STATE` VALUES (7, 'Chiapas' );
INSERT INTO `STATE` VALUES (8, 'Chihuahua' );
INSERT INTO `STATE` VALUES (9, 'Distrito Federal' );
INSERT INTO `STATE` VALUES (10, 'Durango' );
INSERT INTO `STATE` VALUES (11, 'Guanajuato' );
INSERT INTO `STATE` VALUES (12, 'Guerrero' );
INSERT INTO `STATE` VALUES (13, 'Hidalgo' );
INSERT INTO `STATE` VALUES (14, 'Jalisco' );
INSERT INTO `STATE` VALUES (15, 'México' );
INSERT INTO `STATE` VALUES (16, 'Michoacán de Ocampo' );
INSERT INTO `STATE` VALUES (17, 'Morelos' );
INSERT INTO `STATE` VALUES (18, 'Nayarit' );
INSERT INTO `STATE` VALUES (19, 'Nuevo León' );
INSERT INTO `STATE` VALUES (20, 'Oaxaca' );
INSERT INTO `STATE` VALUES (21, 'Puebla' );
INSERT INTO `STATE` VALUES (22, 'Querétaro' );
INSERT INTO `STATE` VALUES (23, 'Quintana Roo' );
INSERT INTO `STATE` VALUES (24, 'San Luis Potosí' );
INSERT INTO `STATE` VALUES (25, 'Sinaloa' );
INSERT INTO `STATE` VALUES (26, 'Sonora' );
INSERT INTO `STATE` VALUES (27, 'Tabasco' );
INSERT INTO `STATE` VALUES (28, 'Tamaulipas' );
INSERT INTO `STATE` VALUES (29, 'Tlaxcala' );
INSERT INTO `STATE` VALUES (30, 'Veracruz de Ignacio de la Llave' );
INSERT INTO `STATE` VALUES (31, 'Yucatán' );
INSERT INTO `STATE` VALUES (32, 'Zacatecas');

INSERT INTO "DB"."PUBLIC".TAX_TYPE (TAX_TYPE, TAX_TYPE_DESCRIPTION, ACTIVE, PERCENTAGE) 
	VALUES ('Excento', 'Impuesto excento', true, 0);
INSERT INTO "DB"."PUBLIC".TAX_TYPE (TAX_TYPE, TAX_TYPE_DESCRIPTION, ACTIVE, PERCENTAGE) 
	VALUES ('I.V.A. 16%', 'impuesto al valor agregado', DEFAULT, 16);
INSERT INTO "DB"."PUBLIC".TAX_TYPE (TAX_TYPE, TAX_TYPE_DESCRIPTION, ACTIVE, PERCENTAGE) 
	VALUES ('Taza 0%', 'Taza 0%', DEFAULT, 0);