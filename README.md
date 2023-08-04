## Job Stack (Ver. 1.0.0)

### Description
This program is designed to take a txt file containing a list of jobs that are being considered for application and count
the number of jobs that have been applied to and the number of jobs that have been decided to not consider applying to.

### Txt File Format
In order for this program to function correctly, the txt file must be formatted in the following way:
```
Section Header:
Company Name - Job Title - Status
Company Name - Job Title
```
Please note that in the txt snippet above, the status field is optional and can be left blank.
In addition, the section headers must end with a **: (colon)** and each job entry on each line under the section header must be separated by a **- (dash)** delimeter.

### Usage
To use this program, simply run the following command in the src directory:
```
javac JobStack.java
```
then run the following command:
```
java JobStack
```

or

```
java JobStack <txt file path>
```

If you choose not to enter the txt filename path as a command-line argument, then the program will ask for a txt filename path to be entered upon execution.