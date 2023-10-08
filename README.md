# Quiz-Game-using-Java
A simple Python Quiz Game Application which has been made from AWT (Abstract Window Toolkit) in Java. Also contains database functionality (MySQL) in which scores of all the users are stored, and also the scores of the previous users can be fetched by entering their name and the corresponding phone number.

# About the project
The quiz game consistes of a total of three windows. The first window asks the user to enter some of the basic details such as the firstname, lastname, phone number, 
email and the college name. 

<br />

- On clicking the submit button, the user is redirected to the main quiz window which consists of a total of 10 questions. The user can answer any one of the four given o
option for the specified question. On submitting the answer of the last question, the user details along with the score of the game is stored into the database and the 
user is shown the another window made of grid layout, which shows users the questions attempted correctly, questions which are attempted wrong and the final score for the game

<br />

- Along with this, funcionality has also been provided where user can see theiry previous score by entering the name and the corresponding phone number of the user

<br />

- If both the name and the phone number matches for a particular user and it is present in the database, then the previous score is shown, else a pop up comes displaying
"No such User Exisists"

<br />

### How To Make Database Connectivity with Java Project
For the database connectivity with the java application, we need to mainly follow these 7 steps <br />

-  Import the package **java.sql**
-  Load and register the driver (**The jdbc, java database connectivity driver can be downloaded from the maven repository in the form of a jar file**)
-  Create a connection
-  Create a query/ statment string
-  Execute the particular query
-  Process the result
-  Close the datbase connection
