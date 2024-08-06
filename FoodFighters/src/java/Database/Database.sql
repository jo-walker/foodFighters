DROP DATABASE IF EXISTS ProjectJava;
CREATE DATABASE ProjectJava;
USE ProjectJava;

-- Table for user
CREATE TABLE user(
    userID INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    userRole INT NOT NULL, /*1 customers, 2 retailer, 3 charity*/
    isSubscribed BOOLEAN,
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
    price DECIMAL(10, 2) NOT NULL,
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

-- Table for Notifications
CREATE TABLE Notifications (
    notificationID INT AUTO_INCREMENT NOT NULL,
    text VARCHAR(500) NOT NULL,
    PRIMARY KEY (notificationID)
);

-- Associative table between Customer and Notifications
CREATE TABLE CustomerNotification (
    customerNotificationID INT AUTO_INCREMENT NOT NULL,
    customerID INT NOT NULL,
    notificationID INT NOT NULL,
    PRIMARY KEY (customerNotificationID),
    FOREIGN KEY (customerID) REFERENCES Customer(customerID),
    FOREIGN KEY (notificationID) REFERENCES Notifications(notificationID)
);

-- Sample SELECT statements to view the tables
SELECT * FROM CharityOrg;
SELECT * FROM Customer;
SELECT * FROM Product;
SELECT * FROM ProductRetailer;
SELECT * FROM Retailer;


