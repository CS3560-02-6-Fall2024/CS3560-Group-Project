CREATE DATABASE IF NOT EXISTS GroupAssignment;
USE GroupAssignment;




Create Table IF NOT EXISTS Item(
itemID int,
name VARCHAR(40),
description VARCHAR(400),
PRIMARY KEY(itemID)
);

Create Table IF NOT EXISTS Image(
itemID int,
imagePath VARCHAR(100),
FOREIGN KEY (itemID) REFERENCES Item(itemID),
PRIMARY KEY (itemID)
);

Create Table IF NOT EXISTS Product(
itemID int,
standardPrice float,
FOREIGN KEY(itemID) REFERENCES Item(itemID),
PRIMARY KEY(itemID)
);

Create Table IF NOT EXISTS Ingredient(
itemID int,
storageInstructions VARCHAR(400),
FOREIGN KEY(itemID) REFERENCES Item(itemID),
PRIMARY KEY(itemID)
);


Create Table if NOT EXISTS Supplier(
supplierID int,
name VARCHAR(40),
phoneNumber VARCHAR(12),
email VARCHAR(100),
description VARCHAR(400),
PRIMARY KEY(supplierID)
);

Create Table IF NOT EXISTS Batch(
batchNumber int,
quantity float,
units VARCHAR(10),
batchStatus VARCHAR(20),
dateAdded DATETIME,
expirationDate DATETIME,
PRIMARY KEY(batchNumber)
);

Create Table IF NOT EXISTS IngredientBatch(
batchNumber int,
itemID int,
status VARCHAR(20),
FOREIGN KEY (itemID) REFERENCES Ingredient(itemID),
FOREIGN KEY (batchNumber) REFERENCES Batch(batchNumber),
PRIMARY KEY (batchNumber)
);

Create Table IF NOT EXISTS ProductBatch(
batchNumber int,
itemID int,
FOREIGN KEY (itemID) REFERENCES Product(itemID),
FOREIGN KEY (batchNumber) REFERENCES Batch(batchNumber),
PRIMARY KEY (batchNumber)
);

Create Table IF NOT EXISTS RecipeIngredient(
id int,
productID int,
ingredientID int,
quantity float,
units varchar(10),
FOREIGN KEY (productID) REFERENCES Product(itemID),
FOREIGN KEY (ingredientID) REFERENCES Ingredient(itemID),
PRIMARY KEY(id)
);

Create Table IF NOT EXISTS SupplierBatch(
batchNumber int,
supplierID int,
itemID int,
batchPrice float,
expirationDate DATETIME,
FOREIGN KEY (itemID) REFERENCES Ingredient(itemID),
FOREIGN KEY (supplierID) REFERENCES Supplier(supplierID),
FOREIGN KEY (batchNumber) REFERENCES Batch(batchNumber),
PRIMARY KEY (batchNumber)
);