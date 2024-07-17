DROP DATABASE IF EXISTS foodFighters;
CREATE DATABASE foodFighters;
USE foodFighters;

CREATE TABLE user(
    userID int auto_increment NOT NULL,
    username varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    userRole int NOT NULL,
    PRIMARY KEY (userID),
    CONSTRAINT chk_userRole CHECK (userRole IN (1, 2, 3))
);


CREATE TABLE Product (
    productID int auto_increment NOT NULL,
    productName varchar(100) NOT NULL,
    PRIMARY KEY (productID)
);

CREATE TABLE Retailer (
    retailerID INT AUTO_INCREMENT NOT NULL,
    userID INT NOT NULL,
    retailerName VARCHAR(100) NOT NULL,
    PRIMARY KEY (retailerID),
    FOREIGN KEY (userID) REFERENCES user(userID),
    UNIQUE KEY (userID)
);


CREATE TABLE Customer (
    customerID INT AUTO_INCREMENT NOT NULL,
    userID INT NOT NULL,
    customerName VARCHAR(100) NOT NULL,
    PRIMARY KEY (customerID),
    FOREIGN KEY (userID) REFERENCES user(userID),
    UNIQUE KEY (userID)
);


CREATE TABLE CharityOrg (
    charityOrgID INT AUTO_INCREMENT NOT NULL,
    userID INT NOT NULL,
    charityOrgName VARCHAR(100) NOT NULL,
    PRIMARY KEY (charityOrgID),
    FOREIGN KEY (userID) REFERENCES user(userID),
    UNIQUE KEY (userID)
);


CREATE TABLE ProductRetailer (
    productRetailerID INT auto_increment NOT NULL,
    productID INT NOT NULL,
    retailerID INT NOT NULL,
    quantity int NOT NULL,/* === NOT SURE ===*/
    expiryDate Date NOT NULL,/* === NOT SURE ===*/
    isSurplus Boolean,/* === NOT SURE ===*/
    PRIMARY KEY (productRetailerID),
    FOREIGN KEY (productID) REFERENCES Product(productID),
    FOREIGN KEY (retailerID) REFERENCES Retailer(retailerID)
);



SELECT * FROM CharityOrg;
SELECT * FROM Customer;
SELECT * FROM Product;
SELECT * FROM ProductRetailer;
SELECT * FROM Retailer;
SELECT * FROM user;


