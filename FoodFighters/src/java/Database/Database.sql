DROP DATABASE IF EXISTS projectjava;
CREATE DATABASE projectjava;
USE projectjava;

-- Table for user
CREATE TABLE user(
    userID INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    userRole INT NOT NULL, /*1 customers, 2 retailer, 3 charity*/
    PRIMARY KEY (userID)
);

-- Table for Customer
CREATE TABLE Customer (
    customerID INT AUTO_INCREMENT NOT NULL,
    userID INT NOT NULL,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    mobile VARCHAR(15) NOT NULL,    
    isVegetarian BOOLEAN NOT NULL,
    FOREIGN KEY (userID) REFERENCES user(userID),
    PRIMARY KEY (customerID),
    isVeggie BOOLEAN NOT NULL
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
    productID INT AUTO_INCREMENT NOT NULL,
    productName VARCHAR(100) NOT NULL,
    price INT NOT NULL,
    isVeggie BOOLEAN NOT NULL,
    PRIMARY KEY (productID)
);

-- Table for associative table between Product and Retailer
CREATE TABLE ProductRetailer (
    productRetailerID INT AUTO_INCREMENT NOT NULL,
    productID INT NOT NULL,
    retailerID INT NOT NULL,
    productQuantity INT NOT NULL,
    expiryDate DATE NOT NULL,
    isSurplus BOOLEAN,
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

CREATE TABLE CharityProductDonation (
    donationID INT AUTO_INCREMENT NOT NULL,
    charityOrgID INT NOT NULL,
    productID INT NOT NULL,
    quantity INT NOT NULL,
    isSurplus BOOLEAN NOT NULL,
    expiryDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (donationID),
    FOREIGN KEY (charityOrgID) REFERENCES CharityOrg(charityOrgID),
    FOREIGN KEY (productID) REFERENCES Product(productID)
);


-- Sample SELECT statements to view the tables
SELECT * FROM CharityOrg;
SELECT * FROM Customer;
SELECT * FROM Product;
SELECT * FROM ProductRetailer;
SELECT * FROM Retailer;
SELECT * FROM user;
SELECT * FROM CharityProductDonation;





