To run this program easily perform the following steps

1. cd into src
2. run javac *.java
3. java Main

Ensure the text files are within src so the file can be easily found.

This program is structured as follows.

Models: 
* Flight
* Order
Services: 
* FlightScheduler (Stores and outputs Flight Information)
* FlightInventoryService (Stores and outputs order Information)

You will also noice, that there are 2 concrete implementations of FlightScheduler and FlightInventoryService.
These are specific for the input and output requirements of this project.

Note:

Flight Scheduler takes in as input flight data in the following format:


Day x:
Flight <num>: <city name> airport (<3 letterCode>) to <city name> (<3 letterCode>)
Flight <num>: <city name> (<3 letterCode>) to <city name> (<3 letterCode>)
....