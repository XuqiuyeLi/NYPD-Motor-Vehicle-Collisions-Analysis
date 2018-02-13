# NYPD-Motor-Vehicle-Collisions-Analysis

### Summary
In this project you will provide a tool for extraction of certain type of information about motor vehicle collisions in New York City. The New York Police Department (NYPD) provides data regarding all motor vehicle collisions that occur on streets on NYC. This data can be downloaded from NYC Open Data website at
https://data.cityofnewyork.us/Public-Safety/NYPD-Motor-Vehicle-Collisions/h9gi-nx95.
To simplify your task, the course website has a listing of several preprocessed files that contain the data for different time periods and locations. 

Your program should run with one of those files as the input and extract some interesting information (details below) from it:
we’ll look for detail reports for particular (user specified) zip codes and particular time frames.

The program that you write has to be command line based (no graphical interface).


### Objectives

The goal of this programming project is for you to master (or at least get practice on) the following tasks:
* working with multi-file programs
* reading data from input files
* using command line arguments
* working with large data sets
* implementing an AVL tree
* writing complex code projects


### Input File

Your program is given the name of the input text file as its command line argument (the first and only argument used by this
program). Note: this implies the user should not be prompted for the name of the input file by the program itself.
If the filename is omitted from the command line, it is an error. The program should display an error message and terminate. The error message should indicate what went wrong (for example: ”Error: missing name of the input file”).
If the filename is given but the file does not exist or cannot be opened for reading by the program, for any reason, it is an error. The program should display an error message and terminate. The error message should indicate what went wrong (for example: ”Error: file collisions.csv does not exist.”, but make sure to replace the name with the name of the file with which the program was called).
Your program is NOT ALLOWED to hardcode the input filename in its own code. It is up to the user of the program to specify the name of the input file. Your program should not modify the name of the user-specified file (do not append anything to the name).
Your program may not read the input file more than once.
Your program may not modify the input file.


### User Input

The program should prompt the user for the zip code, start date and end date. The program should validate that the zip code is in acorrect format. Valid zip codes have exactly 5 characters and all characters are digits. The program should validate that the dates are in a correct format. The valid dates should be written as mm/dd/yyyy (the month and day can be either one or two digits long, the year has to be four digits long). The start date should be smaller than (earlier) than the end date. After the program displays the results, the user should be prompted for the zip code, start date and end date again. The program should terminate when the user enters ”quit” in place of the zip code.
