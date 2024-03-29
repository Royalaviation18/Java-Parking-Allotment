
# Parking Lot Application

### Problem Statement
I own a parking lot that can hold up to 'n' cars at any given point in time. Each slot is given a number starting at 1 increasing with increasing distance from the entry point in steps of one. I want to create an automated ticketing system that allows my customers to use my parking lot without human intervention. 
When a car enters my parking lot, I want to have a ticket issued to the driver. The ticket issuing process includes us documenting the registration number (number plate) and the colour of the car and allocating an available parking slot to the car before actually handing over a ticket to the driver (we assume that our customers are nice enough to always park in the slots allocated to them). The customer should be allocated a parking slot which is nearest to the entry. At the exit the customer returns the ticket which then marks the slot they were using as being available. 
Due to government regulation, the system should provide me with the ability to find out: 

    ● Registration numbers of all cars of a particular colour. 
    ● Slot number in which a car with a given registration number is parked.
    ● Slot numbers of all slots where a car of a particular colour is parked. 
    



## Screenshots

![App Screenshot](https://github.com/Royalaviation18/Java-Parking-Allotment/blob/main/appScreenShots/run.jpg)

![App Screenshot](https://github.com/Royalaviation18/Java-Parking-Allotment/blob/main/appScreenShots/run2.jpg)


## Run Locally

Clone the project

```bash
  git clone https://github.com/Royalaviation18/Java-Parking-Allotment
```

Go to the project directory

```bash
  cd Java-Parking-Allotment-main
```

Compile the program

```bash
  javac parkingApplication.java
```
Run the program

```bash
  java ParkingLotApplication
```

