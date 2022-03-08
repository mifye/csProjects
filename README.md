# csProjects
A collection of cs related projects/programs/websites I've developed

## CryptographicUtilities
Using the set of 3 truths below, I developed a program that would generate a large number with high confidence of it being prime. This is supposed to simulate the use of RSA and other public-key encryptions.

If n is prime and 1 < w < n – 1, then w2 mod n ≠ 1.  
If n is prime and 0 < w < n, then wn – 1 mod n = 1.  
If n is composite (i.e., not prime) and 1 < w < n, then it is "likely" that wn – 1 mod n ≠ 1.


## GloassarySearch
After being provided a text file with "dictionary terms" followed by their definitions below them (seperated by an empty new line), the program outputs a formatted html page. This webpage consists of the clickable terms that bring you to a new page displaying their definition. If any words in the definition are also a term, they are clickable as well.



## NaturalNumberCalculator
_(see NaturalNumberCalculator.java's contract for a more detailed description)_  
This project utilized separate interfaces to create a calculator with the buttons "clear", "swap", "enter", and basic operands. These are all displayed in an application window and work based on natural numbers.


## NaturalNumberOnString
Using an SVN, I collaborated with a classmate to create a String represention of NaturalNumber and it's primary methods. In addition we developed a thurough JUnit test plan (both files found in test folder).

## P1
This program reads a text file and outputs a table of words and how many times they are used in the document.

## P3
A representation of the Map class and its primary methods using a hash table (utilizing maps as buckets). JUnit tests were also created for good measure.

## P4
Another respresentation, but this time the Set class is being represnted as a BinaryTree.

## P5
Creation of a SortingMachine using Queue as representation and an array as a means for heapsorting. The primary methods for the SortingMachine were implemented and I included JUnit testing for such as well.
