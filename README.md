//Ryan Conway
//ID - G00332826

Overview
==========
We were required to develop a Java API that could rapidly compare two large text files by 
computing  their Jaccard Index.

Contents
==========
The project contains 9 java classes, a UML diagram and a readme

	-Runner.java	=	Runs the menu
	-Menu.java	= 	Lists out the menu items
	-Launcher.java	=	starts threads
	-FileParser.java=	parses .txt files
	-Shingle.java	=	Creates Shingles
	-Poison.java	=	Creates null Shingle
	-MinHasher.java	=	turns word to hashcode and comapres two files
	-Jacard.java	=	Creates jacards
	-Comparer.java	=	Calculates likeness

	-UML.png	=	UML Diagram

Running Project
===============
In command prompt navigate to the directory with the oop.jar file
	-- >java –cp ./oop.jar ie.gmit.sw.Runner
Or the user can open the project in eclipse and run it from the console within.

How it works
===============
When the user runs the program the console will print a menu asking the user if they 
would like to compare files or exit program. When the user selects compare files they 
be prompted to enter the file name, second file name, ammount of Shingles and the size 
of the blocking queue. Once entered the program will run and display the ammount of 
Shingles in file 1 and file 2, the ammount of comparisons and the percentage of likeness.

If any problems occur please email.