DROP DATABASE IF EXISTS ProjectJava;
CREATE DATABASE ProjectJava;
USE ProjectJava;

-- Table for user
CREATE TABLE user(
	userID int auto_increment NOT NULL,
    username varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    userRole int NOT NULL,
    PRIMARY KEY (userID)
);



-- Table for user
CREATE TABLE Customer (
    customerID INT AUTO_INCREMENT NOT NULL,
    userID INT NOT NULL,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    mobile INT NOT NULL,    
    FOREIGN KEY (userID) REFERENCES user(userID),
    PRIMARY KEY (customerID)
);

-- Table for Charity Organization
CREATE TABLE CharityOrg (
    charityOrgID INT AUTO_INCREMENT NOT NULL,
    userID INT NOT NULL,
    charityOrgName VARCHAR(100) NOT NULL,    
    FOREIGN KEY (userID) REFERENCES user(userID),
    PRIMARY KEY (charityOrgID)
);

-- Table for Retailer
CREATE TABLE Retailer (
    retailerID INT AUTO_INCREMENT NOT NULL,
    userID INT NOT NULL,
    retailerName VARCHAR(100) NOT NULL,
    FOREIGN KEY (userID) REFERENCES user(userID),
    PRIMARY KEY (retailerID)
);

-- Table for Product
CREATE TABLE Product (
    productID int auto_increment NOT NULL,
    productName varchar(100) NOT NULL,
    price int NOT NULL,
    PRIMARY KEY (productID)
);

-- Table for associative table between Product and Retailer
CREATE TABLE ProductRetailer (
    productRetailerID INT auto_increment NOT NULL,
    productID INT NOT NULL,
    retailerID INT NOT NULL,
	productQuantity int NOT NULL,
    expiryDate date NOT NULL,
    isSurplus Boolean,
    PRIMARY KEY (productRetailerID),
    FOREIGN KEY (productID) REFERENCES Product(productID),
    FOREIGN KEY (retailerID) REFERENCES Retailer(retailerID)
);

-- Associative table between CharityOrg and Retailer
CREATE TABLE CharityRetailer (
    charityRetailerID INT AUTO_INCREMENT NOT NULL,
    charityOrgID INT NOT NULL,
    retailerID INT NOT NULL,
    PRIMARY KEY (charityRetailerID),
    FOREIGN KEY (charityOrgID) REFERENCES CharityOrg(charityOrgID),
    FOREIGN KEY (retailerID) REFERENCES Retailer(retailerID)
);








SELECT * FROM CharityOrg;
SELECT * FROM Customer;
SELECT * FROM Product;
SELECT * FROM ProductRetailer;
SELECT * FROM Retailer;
SELECT * FROM user;


