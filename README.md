# Auto auctions REST-API

## Description:
    This is an REST-API made to manage auctions on cars and offer to a client 
    informations about what car will suit him the best giving a certain number of attributes
    that the car should have.

## Main business requirements 1️⃣0️⃣: 
    💡 As a user I want to add all the car parts in a very detailed manner;
    💡 As a user I want to use the existing car parts and create a car by using them;
    💡 As a user I want too see a list of all the users;
    💡 As a user I create an account and authenticate;
    💡 As a user I want delete an account by email;
    💡 As a user I want to see all the reports in a detailed form and in a short form;
    💡 As a user I want to see a specific report by id in a detailed form;
    💡 As a user I want to add a report;      ➡️ Only if I'm authenticated;
    💡 As a user I want to approve a report;  ➡️ Only if I'm an admin;
    💡 As a user I want to buy a car;         ➡️ Only if I'm authenticated;
    

## Application features:
### *️⃣ Car related features: 
        ✅🚙 Add car manufacturers;
        ✅🚙 Add car models;
        ✅🚙 Add transmissions;
        ✅🚙 Add drivetrains;
        ✅🚙 Add engines;
        ✅🚙 Combine all of the above in a new customized or not car;

### *️⃣ User related features:
        ✅👤 Add a new user;
        ✅👤 Get a list of all the users;
        ✅👤 Delete a user by email;
        ✅👤 Login with a user in order to work on reports;

### *️⃣ Report related features: 
    ➡️ You have to be authenticated for the important features;

        ✅📑 Get a list of all reports;
        ✅📑 Get a single report;
        ✅📑 Approve a report; ➡️ You have to be logged in and be an admin;
        ✅📑 Add a report;     ➡️ You have to be logged in;
        ✅📑 Buy a car;        ➡️ You have to be logged in;
    