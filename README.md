# Student Companion App
#### Video Demo:  <https://youtu.be/xOW8W5grA6Q>
#### Description: A simple timetable/reminder app, 

### Introduction
Student companion is an application designed to help students keep track of their classes and other academic related activities, it helps them keep track of activities by displaying information in an intuitive and easy to understand manner. It also helps them to keep track of time by setting reminders and regular alarms to remind them of classes and other academic events of importance.And lastly it is an invaluable tool helping to organise study materials for each course, class or subject in a single place Making studying, attending classes and life as a student in general that much easier. It's an app designed to aid students In thier day to day activities and helps them concentrate on what is important at the moment.

### Design
Student Companion takes advantage of the Google material design principles, and draws 
Inspiration from various apps , including the Google calendar app
and goole note's app
It's features design that focus on the user and unlike must app aims to reduce the time the user is required to spend on the app in order to get useful information, and doesn't aim to maximise usage time, in order to give the user time to focus on other important aspects of their studies. 
Typically a user should pick up the app several time in a day and should spend less than a minute each time as the information is arranged in such a manner that it is easy to see what you need to see and even what you want with the right calibration.

### Implementation

Student companion is implemented mostly using Java as the programming language and XML is used for markup to organise the 
various views in thier various positions. 
As per android development requirements gradle and the grovy languages
Are also very much in use.  Student Companion uses Material design specifications , and takes advantage of the material design components provided by Google

The app was entirely built from scratch (no templates ) on mobile for mobile using the androidIde  application

AndroIDE - is an android application development environment that is currently in beta and is actively been developed the app's structures it's apps using mostly the same pattern as Android studios but you might encounter a little problem transferring projects to android studio - Thier GitHub account has necessary information about the doing this the right way and it's pretty easy

The app uses a database that is created and made possible using sqlite 
which is the standard database structure used for Android development,

### App Structure

The app has three main screens dedicated for different purposes as follows ,
And as infrastructure had classes/events to the app

#### Home Screen 
The home screen has three tabs at the bottom that each organise the same information in different ways 

1. the upcoming tab which displays information about the most recent and currently 
occurring classes or academic events

2. The table tab which displays a time table showing all the classes and events 
happening on various days of the week

3. The courses tab which displays all the various courses registered and provide 
links to learn more about each one

#### Course Screen 
The course screen displays information about a single course and has three pages to
explain various information about the course
1. The classes page has information about various ways in which this course subject 
or events happened and as infrastructure to add new classes

2. The attachment page has instruction to add attachments, documents or files related 
to the current course and to easily access them

3. The activities page has infastructure to add activities related to the current course
 such as exams, tests and view them in a single place
