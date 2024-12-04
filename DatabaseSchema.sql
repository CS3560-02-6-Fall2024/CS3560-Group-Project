CREATE DATABASE IF NOT EXISTS GroupAssignment;
USE GroupAssignment;


Create Table IF NOT EXISTS Image(
productID int,
imagePath VARCHAR(100),
FOREIGN KEY (productID) REFERENCES Product(productID),
PRIMARY KEY (productID)
);

Create Table IF NOT EXISTS Product(
productID int,
name VARCHAR(40),
calories int,
description VARCHAR(400),
standardPrice float,
PRIMARY KEY(productID)
);

Create Table IF NOT EXISTS Ingredient(
ingredientID int,
name VARCHAR(40),
description VARCHAR(400),
storageInstructions VARCHAR(400),
PRIMARY KEY(ingredientID)
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
PRIMARY KEY(batchNumber)
);

Create Table IF NOT EXISTS IngredientBatch(
batchNumber int,
ingredientID int,
expirationDate DATETIME,
FOREIGN KEY (ingredientID) REFERENCES Ingredient(ingredientID),
FOREIGN KEY (batchNumber) REFERENCES Batch(batchNumber),
PRIMARY KEY (batchNumber)
);

Create Table IF NOT EXISTS ProductBatch(
batchNumber int,
productID int,
expirationDate DATETIME,
FOREIGN KEY (productID) REFERENCES Product(productID),
FOREIGN KEY (batchNumber) REFERENCES Batch(batchNumber),
PRIMARY KEY (batchNumber)
);

Create Table IF NOT EXISTS RecipeIngredient(
id int,
productID int,
ingredientID int,
quantity float,
units varchar(10),
FOREIGN KEY (productID) REFERENCES Product(productID),
FOREIGN KEY (ingredientID) REFERENCES Ingredient(ingredientID),
PRIMARY KEY(id)
);

Create Table IF NOT EXISTS SupplierBatch(
batchNumber int,
supplierID int,
ingredientID int,
expirationDate DATETIME,
FOREIGN KEY (ingredientID) REFERENCES Ingredient(ingredientID),
FOREIGN KEY (supplierID) REFERENCES Supplier(supplierID),
FOREIGN KEY (batchNumber) REFERENCES Batch(batchNumber),
PRIMARY KEY (batchNumber)
);