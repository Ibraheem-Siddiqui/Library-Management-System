import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/***********************************************************************
 * Authors: Ibraheem Siddiqui, Imaad Kotadia
 * Last Edit: June 13, 2025
 ***********************************************************************/

public class Library {

	/***********************************************************************
	* Static Variable Dictionary
	* ArrayList<Patron> users - stores the list of all users
	* ArrayList<Patron> orderSNum - stores the list of all users sorted by
	* student number
	* ArrayList<Patron> orderPNum - stores the list of all users sorted by
	* phone number
	* ArrayList<Patron> orderEmail - stores the list of all users sorted by
	* email adress
	* ArrayList<Book> libraryOrderTitle - stores the list of all books sorted by
	* title
	* ArrayList<Book> libraryOrderAuthor - stores the list of all books sorted by
	* author
	* ArrayList<Book> libraryOrderBarcode - stores the list of all books sorted by
	* barcode
	* ArrayList<Book> libraryOrderCallNum - stores the list of all books sorted by
	* call number
	* ArrayList<Book> libraryOrderISBN - stores the list of all books sorted by
	* ISBN
	* ArrayList<Fiction> libraryFiction - stores the list of all fiction books
	* ArrayList<NonFiction> libraryNonFiction - stores the list of all 
	* non fiction books
	* int numWriteLibrary - stores how many times the library has been written to
	* boolean debug - stores whether debug mode is on
	***********************************************************************/

    //#data
    //#staticfield
	private static ArrayList<Patron> users = new ArrayList<>();
	private static ArrayList<Patron> orderSNum = new ArrayList<>();
	private static ArrayList<Patron> orderPNum = new ArrayList<>();
	private static ArrayList<Patron> orderEmail = new ArrayList<>();
	private static ArrayList<Book> libraryOrderTitle = new ArrayList<>();
	private static ArrayList<Book> libraryOrderAuthor = new ArrayList<>();
	private static ArrayList<Book> libraryOrderBarcode = new ArrayList<>();
	private static ArrayList<Book> libraryOrderCallNum = new ArrayList<>();
	private static ArrayList<Book> libraryOrderISBN = new ArrayList<>();
	private static ArrayList<Fiction> libraryFiction = new ArrayList<>();
	private static ArrayList<NonFiction> libraryNonFiction = new ArrayList<>();
	private static int numWriteLibrary;
	private static boolean debug = false;   

	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: toggleDebug
	* Purpose: Toggles debug on and off
	* @param none
	* @return none
	***********************************************************************/
	//#debug
	public static void toggleDebug()
	{
		//swtiches debug to the opposite of its current value
		debug = !debug;

		//tells the user if debug mode is on
		if(debug)
		{
			System.out.println("\nDebug mode is now on");
		}
		else
		{
			System.out.println("\nDebug mode is now off");
		} //end if
	} //end toggleDebug

    /*********************************************************************************
     * Author: Imaad Kotadia
     * Method Name: getDebug
     * Description: Returns the debug boolean
     *
     * @param none
     * @return ArrayList<Book> - The debug boolean
     *********************************************************************************/
    public static boolean getDebug()
    {
        // Returning the debug boolean
        return debug;
    } // end getLibrary

	/***********************************************************************
	* Patron Section
	***********************************************************************/

	/***********************************************************************
	* Get methods
	***********************************************************************/

	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: getUsers
	* Purpose: Retrieves the users list ordered by registration date
	* @param none
	* @return ArrayList<Patron> - the array list of Patrons
	***********************************************************************/
	//@author Imaad
	//@date May 30
	public static ArrayList<Patron> getUsers()
	{
		return users;
	} //end getUsers

	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: getOrderSNum
	* Purpose: retreives the users list ordered by student number
	* @param none
	* @return ArrayList<Patron> - the array list of Patrons
	***********************************************************************/
	//@author Imaad
	//@date June 3
	public static ArrayList<Patron> getOrderSNum()
	{
		return orderSNum;
	} //end getOrderSNum

	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: getOrderPNum
	* Purpose: retreives the users list ordered by phone number
	* @param none
	* @return ArrayList<Patron> - the array list of Patrons
	***********************************************************************/
	//@author Imaad
	//@date June 3
	public static ArrayList<Patron> getOrderPNum()
	{
		return orderPNum;
	} //end getOrderPNum

	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: getOrderEmail
	* Purpose: retreives the users list ordered by email address
	* @param none
	* @return ArrayList<Patron> - the array list of Patrons
	***********************************************************************/
	//@author Imaad
	//@date June 3
	//#get
	public static ArrayList<Patron> getOrderEmail()
	{
		return orderEmail;
	} //end getOrderEmail

	/***********************************************************************
	* Set methods
	***********************************************************************/

	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: setArrayListPatron
	* Purpose: sets one array list to the value of another
	* @param p1 - stores array list that is being modified
	* @param p2 - stores the array list that p1 is being set to
	* @return none
	***********************************************************************/
	//@author Imaad
	//@date June 3
	//#set
	private static void setArrayListPatron(ArrayList<Patron> p1, ArrayList<Patron> p2)
	{
		//iterates through each element of p2 and adds it to p1
		for(int i = 0; i < p2.size(); i++)
		{
			p1.add(p2.get(i));
		} // end for
	} // end setArrayList


	/****************************
	* Class Methods
	***************************/

	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: loadPatrons
	* Purpose: loads the Patron list into various array lists which are
	* ordered differently based on a csv file
	* @param none
	* @return none
	***********************************************************************/
	//@author Imaad
	//@date June 5
	public static void loadPatrons()
	{
		/*******************************************************************************
		* Variable Dictionary
		* ArrayList<String> tempUsers - stores the values from line parsed
		* into an array list of strings
		* Patron p - Stores the created patrons before adding it to users
		* String line - temporarily stores a line of the CSV file in order
		* to be converted into a Patron object
		*******************************************************************************/
		//#object
		ArrayList<String> tempUsers = new ArrayList<String>();
		Patron p = new Patron();

		//try to read the file
		try
		{
			//#filein
			//create a BufferedReader based on the csv input file
			BufferedReader br = new BufferedReader(new FileReader("patrons.csv"));
			String line = "";

			//reads the first line of the BufferedReader, but does nothing with
			//its contents
			br.readLine();

			//iterate through each line of the BufferedReader until an empty
			//line is found
			while ((line = br.readLine()) != null)
			{
				//uses the method usersParse and stores it into tempUsers
				tempUsers = usersParse(line);

				//as long as the size of tempUsers is greater than 2 create
				//a patron object using the indexes 1, 2 and 3 and adds it
				//to users
				if (tempUsers.size() >= 3)
				{
					p = new Patron(tempUsers.get(1), tempUsers.get(2), tempUsers.get(3));
					users.add(p);
				} // end if
			} // end while

			//closes the BufferedReader
			br.close();
		}
		//if an exception is caught, output a error message to the user
		catch (Exception e)
		{
			System.out.println("Failed to load patrons: " + e.getMessage());
		} // end try-catch

		//creates copies of users to the different ordered lists
		Library.setArrayListPatron(orderSNum, users);
		Library.setArrayListPatron(orderPNum, users);
		Library.setArrayListPatron(orderEmail, users);

		//sorts the list into different orders using merge sort
		Library.mergeSortPatron(orderSNum, "studentNum");
		Library.mergeSortPatron(orderPNum, "phoneNum");
		Library.mergeSortPatron(orderEmail, "email");

	} // end loadPatrons

	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: usersParse
	* Purpose: parses through the values of a line in the csv file and
	* splits it by any commas found
	* @param line - holds the line that is to be parsed
	* @return ArrayList<String> - array list of elements after parsing
	***********************************************************************/
	//@author Imaad
	//@date June 2
	private static ArrayList<String> usersParse(String line)
	{
		/*******************************************************************************
		* Variable Dictionary
		* ArrayList<String> values - stores the values that have been parsed
		* from the line
		* String val - stores each element that has been split from the string
		*******************************************************************************/
		ArrayList<String> values = new ArrayList<>();

		//for each comma seperated value, add it to the array list of strings
		for (String val : line.split(","))
		{
			//trims any white space
			values.add(val.trim());
		}
		//return values
		return values;
	} // end usersParse

	/***********************************************************************
	* Method Name: addPatron
	* Purpose: adds a new patron to the lists of users
	* @param newUser - the patron that is to be added
	* @return none
	***********************************************************************/
	//@author Imaad
	//@date June 4
	public static void addPatron(Patron newUser)
	{
		//DEBUG
		if (debug)
		{
			System.out.println("Entering addPatron");
			System.out.println(newUser);
		} //end if

		//adds a user to the users list and sorts it into all the ordered lists
		users.add(newUser);
		Library.singleSort(orderSNum, newUser, "studentNum");
		Library.singleSort(orderPNum, newUser, "studentNum");
		Library.singleSort(orderEmail, newUser, "studentNum");

		//DEBUG
		if (debug)
		{
			System.out.println("Exiting addPatron");
			System.out.println("Added newUser to users");
			System.out.println(users);
		} //end if
	} // end addPatron

	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: removePatron
	* Purpose: removes patron to the lists of users
	* @param removedUser - the patron that is to be remove
	* @return none
	***********************************************************************/
	//@author Imaad
	//@date June 4
	public static void removePatron(Patron removedUser)
	{
		//DEBUG
		if (debug)
		{
			System.out.println("Entering removePatron");
			System.out.println(removedUser);
		} //end if

		//removes the user from all versions of the list
		users.remove(removedUser);
		orderSNum.remove(removedUser);
		orderPNum.remove(removedUser);
		orderEmail.remove(removedUser);

		//DEBUG
		if (debug)
		{
			System.out.println("Exiting removePatron");
			System.out.println(users);
		} //end if
	} // end removePatron

	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: savePatron
	* Purpose: saves the list of patrons to a csv file
	* @param none
	* @return none
	***********************************************************************/
	//@author Imaad
	//@date June 2
	public static void savePatron()
	{
		/*******************************************************************************
		* Variable Dictionary
		* PrintWriter outFile - holds a PrintWriter in order to write to file
		* from the line
		* String tempLine - stores a temporary line that is to be saved to the csv
		*******************************************************************************/
		//enter try catch

		//DEBUG
		if (debug)
		{
			System.out.println("Entering savePatron");
		} //end if

		try
		{
			//#fileout
			PrintWriter outFile = new PrintWriter(new FileWriter("patrons.csv"));
			String tempLine;

			//prints the opening line of the csv
			outFile.println("#,Student Number,Phone Number,Email,Books Checked Out");

			//iterates through each Patron in users
			for(int i = 0; i < users.size(); i++)
			{

				//creates a line of the csv storing a single patron
				tempLine = (i + 1) + ", "
				           + users.get(i).getStudentNum() + ", "
				           + users.get(i).getPhoneNum() + ", "
				           + users.get(i).getEmail();

				//stores tempLine in the csv
				outFile.println(tempLine);

				//DEBUG
				if (debug)
				{
					System.out.println(tempLine);
				} //end if
			} // end for

			//closes the PrintWriter
			outFile.close();
		}
		//catches any exceptions
		catch (Exception e)
		{
			//tells the user there was an error in saving patrons
			System.out.println("There was an error in saving patrons: " + e);
		} // end try-catch

		//DEBUG
		if (debug)
		{
			System.out.println("Exiting savePatron");
		} //end if
	} // end savePatron

	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: mergeSortPatron
	* Purpose: splits users into a form that is ready to be merged for
	* a merge sort
	* @param userList- stores the array list of patrons that is to be
	* sorted
	* @param comparisonField- stores by what the list will be sorted by
	* @return none
	***********************************************************************/
	//@author Imaad
	//@date June 3
	private static void mergeSortPatron(ArrayList<Patron> userList, String comparisonField)
	{
		/*******************************************************************************
		* Variable Dictionary
		* int inLength- stores the size of users
		* int midIndex- stores the value of the middle index of users
		* ArrayList<Patron> leftHalf- stores the left half of the userList
		* ArrayList<Patron> rightHalf- stores the right half of the userList
		*******************************************************************************/

		//DEBUG
		if (debug)
		{
			System.out.println("Entering mergeSortPatron");
			System.out.println("userList: " + userList);
			System.out.println("comparisonField: " + comparisonField);
		} //end if

		//#sort
		//set inLength to the size of users
		int inLength = userList.size();

		//if the list of users is 1 or less, return the list itself
		if (inLength < 2)
		{
			return;
		} //end if

		int midIndex = inLength / 2;
		ArrayList<Patron> leftHalf = new ArrayList<Patron>();
		ArrayList<Patron> rightHalf = new ArrayList<Patron>();

		//iterates through the for loop for the beginning to the middle of userList
		for(int i = 0; i < midIndex; i++)
		{
			//add userList at i to leftHalf
			leftHalf.add(userList.get(i));
		} // end for

		////iterates through the for loop for the middle to the end of userList
		for(int i = midIndex; i < inLength; i++)
		{
			//add userList at i to rightHalf
			rightHalf.add(userList.get(i));
		} // end for

		//recursively call mergeSortPatron for the left and right half of userList
		mergeSortPatron(leftHalf, comparisonField);
		mergeSortPatron(rightHalf, comparisonField);

		//DEBUG
		if (debug)
		{
			System.out.println("Exiting mergeSortPatron");
			System.out.println("userList: " + userList);
			System.out.println("leftHalf: " + leftHalf);
			System.out.println("rightHalf: " + rightHalf);
			System.out.println("comparisonField: " + comparisonField);
		} // end if

		// merge the two parts together
		mergePatron(userList, leftHalf, rightHalf, comparisonField);
	} // end mergeSortPatron

	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: mergePatron
	* Purpose: merges back the split up array from mergeSortPatron
	* @param userList- stores the array list of patrons that is to be
	* sorted
	* @param leftHalf - stores the left half of userList
	* @param rightHalf - stores the right half of userList
	* @param comparisonField- stores by what the list will be sorted by
	* @return none
	***********************************************************************/
	//@author Imaad
	//@date June 3
	private static void mergePatron(ArrayList<Patron> userList, ArrayList<Patron> leftHalf, ArrayList<Patron> rightHalf, String comparisonField)
	{
		/*******************************************************************************
		* Variable Dictionary
		* int leftSize - stores the size of the left half of the userList
		* int rightSize - stores the size of the right half of the userList
		* int i - index for iterating through the leftHalf ArrayList
		* int j - index for iterating through the rightHalf ArrayList
		* int k - index for placing sorted elements back into the original userList
		*******************************************************************************/

		//DEBUG
		if (debug)
		{
			System.out.println("Entering mergePatron");
			System.out.println("userList: " + userList);
			System.out.println("leftHalf: " + leftHalf);
			System.out.println("rightHalf: " + rightHalf);
			System.out.println("comparisonField: " + comparisonField);
		} //end if

		//#sort
		int leftSize = leftHalf.size();
		int rightSize = rightHalf.size();

		int i = 0;
		int j = 0;
		int k = 0;

		//loop while i and j are both smaller the left and right size respectively
		while(i < leftSize && j < rightSize)
		{
			// If the value in leftHalf is less than or equal to the value in rightHalf
			// (based on the comparisonField), add it to userList
			if(Patron.compareBy(comparisonField, leftHalf.get(i), rightHalf.get(j)) <= 0)
			{
				//add patron at i with the value to k and increment i and k
				userList.set(k, leftHalf.get(i));
				i++;
				k++;
			}
			//otherwise
			else
			{
				//add patron at j with the value to k and increment j and k
				userList.set(k, rightHalf.get(j));
				j++;
				k++;
			} //end if
		}

		//repeat while i is less than the left size
		while(i < leftSize)
		{
			//set userlist at k to leftHalf at i and increment i and k
			userList.set(k, leftHalf.get(i));
			i++;
			k++;
		}

		//repeat while j is less than the right size
		while(j < rightSize)
		{
			//set userList at k to rightHalf at j and increment j and k
			userList.set(k, rightHalf.get(j));
			j++;
			k++;
		}

		//DEBUG
		if (debug)
		{
			System.out.println("Exiting mergePatron");
			System.out.println("userList: " + userList);
			System.out.println("comparisonField: " + comparisonField);
		} //end if
	} //end mergePatron

	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: singleSort
	* Purpose: sorts a single new patron into users based on the comparisonField
	* @param users - stores the array list that is to be sorted
	* @param patron - stores the patron that is to be added
	* @param comparisonField - stores how the patron will be sorted
	* @return none
	***********************************************************************/
	//@author Imaad
	//@date June 4
	public static void singleSort(ArrayList<Patron> users, Patron patron, String comparisonField)
	{
		/*******************************************************************************
		* Variable Dictionary
		* int i - index for iterating through the array list backwards
		*******************************************************************************/

		//DEBUG
		if (debug)
		{
			System.out.println("Entering singleSort");
			System.out.println("users: " + users);
			System.out.println("patron: " + patron);
			System.out.println("comparisonField: " + comparisonField);
		} //end if

		//#sort
		//adds patron to users
		users.add(patron);
		int i = users.size() - 1;

		//repeats until the end of users is reached or its correct position is found
		while (i > 0 && Patron.compareBy(comparisonField, patron, users.get(i - 1)) <= 0)
		{
			//swaps users at i and i-1 and reduced i by 1
			users.set(i, users.get(i-1));
			users.set(i-1, patron);
			i--;
		} // end while

		//DEBUG
		if (debug)
		{
			System.out.println("Exiting singleSort");
			System.out.println("users: " + users);
			System.out.println("patron: " + patron);
			System.out.println("comparisonField: " + comparisonField);
		} //end if
	} // end singleSort


	/*********************************************************************************
    * Library-Book Section
    *********************************************************************************/


    /*********************************************************************************
     * LIBRARY LOADING/SAVING
     *********************************************************************************/

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: createLibrary
     * Description: Loads all the ArrayLists storing the books in different orders
     * by reading the library.csv file
     *
     * @return void
     *********************************************************************************/
    //#staticmethod
    public static void createLibrary() 
    {
        /*******************************************************************************
         * Variable Dictionary
         * ArrayList<String> tempLine - Stores the split version of each line in the CSV
         *                              and is used to give each book object it's values
         * String line - The line that will be parsing through each line in the CSV
         * Book currentBook - The book that is being added to the libraryOrderTitle 
         *                    ArrayList
         * int bookPos - The position of the book being searched
         *******************************************************************************/

        ArrayList<String> tempLine = new ArrayList<String>();
        String line = "";
        Book currentBook = new Book();
        int bookPos = 0;

        if(debug)
        {   
            System.out.println("Creating Library...");
        } // end if
   
        // Reading the txt file with the amount of times the file has been saved
        // This is done to ensure that the main libraryOrderTitle ArrayList
        // which is the ArrayList that is given the information from the CSV file isn't sorted when it is already sorted
        // try-catch for reading from the write.txt file
        try
        {
            // Debugging
            if(debug)
            {
                System.out.println("Reading write.txt...");
            } // end if

            // Creating a BufferedReader to read through the file
            BufferedReader txtBr = new BufferedReader(new FileReader("write.txt"));

            while ((line = txtBr.readLine()) != null)
            {
                numWriteLibrary = Integer.parseInt(line);
            } // end while

            // Closing the reader to the text file
            txtBr.close();

            // Debugging
            if(debug)
            {
                System.out.println("Finished");
            } // end if

        }
        catch (Exception e)
        {
            System.out.println("There was an issue parsing the write.txt file:" + e);
        } // end try-catch

        // try-catch for reading from the library.csv file
        //#error
        try
        {
            // Debugging
            if(debug)
            {
                System.out.println("Reading library.csv...");
            } // end if

            // reading library.csv
            BufferedReader csvBr = new BufferedReader(new FileReader("library.csv"));

            // to get rid of first line
            csvBr.readLine();

            // loops if the line is not null
            while ((line = csvBr.readLine()) != null)
            {
                // adds the arraylist version of the row to the library
                tempLine = parseLine(line);
                // Setting the current book being parsed in the CSV to a book object and adding it to the libraryOrderTitle ArrayList
                currentBook = new Book(tempLine.get(1), tempLine.get(2), Long.parseLong(tempLine.get(3)), tempLine.get(4), tempLine.get(5), tempLine.get(6), tempLine.get(7), tempLine.get(8));
                
                // In the case that the ISBN is empty, it will be set to 3 values higher than
                // the previous book read
                if (currentBook.getISBN().isEmpty())
                {
                    bookPos = specificSeqSearch(libraryOrderTitle, "title", currentBook.getTitle());

                    if(bookPos != -1 && libraryOrderTitle.get(bookPos).getAuthor().equals(currentBook.getAuthor()))
                    {
                        // Debugging
                        if(debug)
                        {
                            System.out.println("Name of Book: " + libraryOrderTitle.get(bookPos).getTitle() + ", " + libraryOrderTitle.get(bookPos).getAuthor());
                            System.out.println("Another book of the same title had this issue: " + libraryOrderTitle.get(bookPos).getTitle() + ", by: " + libraryOrderTitle.get(bookPos).getAuthor() + ", " + libraryOrderTitle.get(bookPos).getISBN());
                        } // end if

                        currentBook.setISBN(libraryOrderTitle.get(bookPos).getISBN());
                        
                        // Debugging
                        if(debug)
                        {
                            System.out.println("New ISBN of book: " + currentBook.getISBN());
                        } // end if
                    } 
                    else 
                    {
                        // Debugging
                        if(debug)
                        {
                            System.out.println("Empty ISBN found: " + currentBook.getTitle() + ", " + currentBook.getISBN());
                        } // end if

                        currentBook.setISBN(String.valueOf(Long.parseLong(libraryOrderTitle.get(libraryOrderTitle.size() - 1).getISBN()) + 3));
                        
                        // Debugging
                        if(debug)
                        {
                            System.out.println("previousBook: " + libraryOrderTitle.get(libraryOrderTitle.size() - 1).getISBN() +  ", currentBook: " + currentBook.getISBN());
                        } // end if
                    } // end if
                } // end if
                
                libraryOrderTitle.add(currentBook);                               

            } // end while

            // closing BufferedReader
            csvBr.close();

            // Debugging
            if(debug)
            {
                System.out.println("Finished");
            } // end if

        }
        catch (Exception e)
        {
            System.out.println("An error was encountered while parsing the file: " + e);
        } // end try-catch

        // Setting each ArrayList to the libraryOrderTitle ArrayList so they can be sorted
        setArrayList(libraryOrderAuthor, libraryOrderTitle);
        
        // Debugging
        if(debug)
        {
            System.out.println("libraryOrderAuthor set to libraryOrderTitle");
        } // end if

        setArrayList(libraryOrderBarcode, libraryOrderTitle);
        
        // Debugging
        if(debug)
        {
            System.out.println("libraryOrderBarcode set to libraryOrderTitle");
        } // end if
        
        setArrayList(libraryOrderCallNum, libraryOrderTitle);

        // Debugging
        if(debug)
        {
            System.out.println("libraryOrderCallNum set to libraryOrderTitle");
        } // end if

        setArrayList(libraryOrderISBN, libraryOrderTitle);

        // Debugging
        if(debug)
        {
            System.out.println("libraryOrderISBN set to libraryOrderTitle");
        } // end if

        // Checks if the main title library that is saved to the CSV needs to be sorted
        if (numWriteLibrary == 0)
        {
            // Sorting the libraryOrderTitle ArrayList
            mergeSort(libraryOrderTitle, "title");
        
            // Debugging
            if(debug)
            {
                System.out.println("numWriteLibrary: " + numWriteLibrary);
                System.out.println("libraryOrderTitle sorted");
            } // end if
        
        } // end if
    
        // Sorting all other ArrayList       
        mergeSort(libraryOrderAuthor, "author");
        
        // Debugging
        if(debug)
        {
            System.out.println("libraryOrderAuthor sorted");
        } // end if

        mergeSort(libraryOrderBarcode, "barcode");
        
        // Debugging
        if(debug)
        {
            System.out.println("libraryOrderBarcode sorted");
        } // end if
        
        mergeSort(libraryOrderCallNum, "call number");

        // Debugging
        if(debug)
        {
            System.out.println("libraryOrderCallNum sorted");
        } // end if

        mergeSort(libraryOrderISBN, "ISBN");

        // Debugging
        if(debug)
        {
            System.out.println("libraryOrderISBN sorted");
        } // end if
       
        // Dividing the fiction and non-fiction books into two ArrayList
        for (int i = 0; i < libraryOrderCallNum.size(); i++)
        {
            if (libraryOrderCallNum.get(i).isFiction())
            {
                libraryFiction.add(new Fiction(libraryOrderCallNum.get(i)));
            }
            else
            {
                libraryNonFiction.add(new NonFiction(libraryOrderCallNum.get(i)));
            } // end if
        } // end for
    } // end createLibrary
   
    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: parseLine
     * Description: Converts a line in the CSV to an ArrayList of Strings by
     * splitting it up based on the position
     * of commas. This allows it to be easier to set all of the parameters of the
     * book that is added
     * to the libraryOrderTitle ArrayList
     *
     * @param String  line - The line in the CSV that will be converted to and
     *                ArrayList of Strings
     * @return ArrayList<String> - The ArrayList version of the line
     *********************************************************************************/
    private static ArrayList<String> parseLine(String line)
    {
        /*******************************************************************************
         * Variable Dictionary
         * ArrayList<String> values - Stores the values between each comma seperating
         *                            values in the line being parsed
         * boolean inQuotes - Checks if a char is in quotes or not
         * String currentValue - Appends all the char values between two commas and adds
         *                       it to the values ArrayList
         *******************************************************************************/
        
        ArrayList<String> values = new ArrayList<String>(); 
        boolean inQuotes = false;
        String currentValue = new String();

        // Debugging
        if(debug)
        {
            System.out.println("line: " + line);
        } // end if

        // converting the line parameter into an array of chars
        for (char c : line.toCharArray())
        {    
            // Checking if the current character being parsed through is a quotation mark
            // If it is in quotes, it flips the quotes variable. This is done to check if the portion being read is in quotes
            if (c == '"')
            {
                inQuotes = !inQuotes;
            }
            // Checks if the comma is not in quotes, so checking if it's a divider in the CSV
            else if (c == ',' && !inQuotes)
            {
                // adds the string version of the stringbuilder
                values.add(currentValue);
                currentValue = "";
            }
            else
            {
                // otherwise stringbuilder appends the current letter
                currentValue = currentValue + c;
            } // end if
        } // end for

        // Adds the last value of the line being parsed to the values ArrayList
        values.add(currentValue);

        // Debugging
        if(debug)
        {
            System.out.println("parsed line: " + values);
        } // end if
        
        return values;
    } // end parseLine

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: saveLibrary
     * Description: Writes the information of each book from the libraryOrderTitle
     * ArrayList
     * onto the CSV file to save it for later use. Also writes to the write.txt file
     * that keeps track of how many times the program has been run
     *
     * @param none
     * @return void
     *********************************************************************************/
    public static void saveLibrary()
    {
        // Debugging
        if(debug)
        {
            System.out.println("Starting save...");
        } // end if

        // try-catch for writing to the write.txt file
        //#error
        try
        {
            // Debugging
            if(debug)
            {
                System.out.println("write.txt saving...");            
            } // end if

            // Creating a PrintWriter to write to the write.txt file
            PrintWriter txtWriter = new PrintWriter(new FileWriter("write.txt"));
            // Updating the number of times the library has been saved
            numWriteLibrary++;
            txtWriter.println(numWriteLibrary);
            
            // Closing txtWriter
            txtWriter.close();

            // Debugging
            if(debug)
            {
                System.out.println("write.txt updated");
            } // end if
        }
        catch (Exception e)
        {
            System.out.println("There was an issue writing to the write.txt file:\n" + e);
        } // end try-catch
       
        // try-catch for writing to the library.csv file
        try
        {
            // Debugging
            if(debug)
            {
                System.out.println("library.csv saving...");
            } // end if

            // Creating a PrintWriter to write to the library.csv file
            PrintWriter csvWriter = new PrintWriter(new FileWriter("library.csv"));
            csvWriter.println("#,Title,Authors,Barcode,Call No,ISBN,Status,Patron Status,Hold Status");

            // Writing to the CSV file by putting the information of each book
            for (int i = 0; i < libraryOrderTitle.size(); i++)
            {
                // Printing each book to the CSV
                csvWriter.print((i + 1) + "," + libraryOrderTitle.get(i));
            } // end for

            // Closing the csvWriter           
            csvWriter.close();

            // Debugging
            if(debug)
            {
                System.out.println("library.csv saved");
            } // end if
        }
        catch (Exception e)
        {
            System.out.println("An error occurred while writing to the file:\n" + e);
        } // end try-catch
    } // end saveLibrary


    /*********************************************************************************
     * GETTERS
     *********************************************************************************/

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: getLibraryOrderTitle
     * Description: Returns the libraryOrderTitle ArrayList
     *
     * @param none
     * @return ArrayList<Book> - The ArrayList of Books sorted by title
     *********************************************************************************/
    public static ArrayList<Book> getLibraryOrderTitle()
    {
        // Returning the libraryOrderTitle ArrayList
        return libraryOrderTitle;
    } // end getLibrary

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: getLibraryOrderAuthor
     * Description: Returns the libraryOrderAuthor ArrayList
     *
     * @param none
     * @return ArrayList<Book> - The ArrayList of Books sorted by author
     *********************************************************************************/
    public static ArrayList<Book> getLibraryOrderAuthor()
    {
        // Returning the libraryOrderAuthor ArrayList
        return libraryOrderAuthor;
    } // end getLibrarySortedByAuthor

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: getLibraryOrderBarcode
     * Description: Returns the libraryOrderBarcode ArrayList
     *
     * @param none
     * @return ArrayList<Book> - The ArrayList of Books sorted by barcode
     *********************************************************************************/
    public static ArrayList<Book> getLibraryOrderBarcode()
    {
        // Returning the libraryOrderBarcode ArrayList
        return libraryOrderBarcode;
    } // end getLibrarySortedByBarcode

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: getLibraryOrderCallNum
     * Description: Returns the libraryOrderCallNum ArrayList
     *
     * @param none
     * @return ArrayList<Book> - The ArrayList of Books sorted by call number
     *********************************************************************************/
    public static ArrayList<Book> getLibraryOrderCallNum()
    {
        // Returning the libraryOrderCallNum ArrayList
        return libraryOrderCallNum;
    } // end getLibrarySortedByCallNum

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: getLibraryOrderISBN
     * Description: Returns the libraryOrderISBN ArrayList
     *
     * @param none
     * @return ArrayList<Book> - The ArrayList of Books sorted by ISBN
     *********************************************************************************/
    public static ArrayList<Book> getLibraryOrderISBN()
    {
        // Returning the libraryOrderISBN ArrayList
        return libraryOrderISBN;
    } // end getLibrarySortedByISBN

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: getLibraryFiction
     * Description: Returns the libraryFiction ArrayList
     *
     * @param none
     * @return ArrayList<Fiction> - The ArrayList of fiction books
     *********************************************************************************/
    public static ArrayList<Fiction> getLibraryFiction()
    {
        // Returning the libraryFiction ArrayList
        return libraryFiction;
    } // end getLibraryFiction

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: getLibraryNonFiction
     * Description: Returns the libraryNonFiction ArrayList
     *
     * @param none
     * @return ArrayList<NonFiction> - The ArrayList of non-fiction books
     *********************************************************************************/
    public static ArrayList<NonFiction> getLibraryNonFiction()
    {
        // Returning the libraryNonFiction ArrayList
        return libraryNonFiction;
    } // end getLibraryNonFiction


    /*********************************************************************************
     * CLASS METHODS  
     *********************************************************************************/

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: setArrayList
     * Description: Sets an ArrayList to another ArrayList
     *
     * @param ArrayList<Book> one - The ArrayList that gets the information from two
     * @param ArrayList<Book> two - The ArrayList that transfers the information to
     *                        one
     * @return void
     *********************************************************************************/
    private static void setArrayList(ArrayList<Book> one, ArrayList<Book> two)
    {
        // Getting the values of two and adding them to one
        for (int i = 0; i < two.size(); i++)
        {
            one.add(two.get(i));
        } // end for
    } // end setArrayList

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: addBook
     * Description: Adds a book to the library
     *
     * @param Book book - The book being added
     * @return void
     *********************************************************************************/
    public static void addBook(Book book)
    {
        // Sorting in the book into each of the ArrayLists
        singleSort(libraryOrderTitle, book, "title");
        singleSort(libraryOrderAuthor, book, "author");
        singleSort(libraryOrderBarcode, book, "barcode");
        singleSort(libraryOrderCallNum, book, "call number");
        singleSort(libraryOrderISBN, book, "isbn");
       
        // Checking if the book is fiction, if it is, it is sorted into the Fiction ArrayList, otherwise it is sorted into the NonFiction ArrayList
        if(book.isFiction())
        {
            Fiction.singleSort(libraryFiction, new Fiction(book), "call number");
        }
        else
        {
            NonFiction.singleSort(libraryNonFiction, new NonFiction(book), "call number");
        } // end if
        
    } // end addBookToLibrary

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: removeBook
     * Description: Removes a book from the library
     *
     * @param Book book - The book being removed
     * @return void
     *********************************************************************************/
    public static void removeBook(Book book)
    {
        // Removing the book from each ArrayList
        libraryOrderTitle.remove(book);
        libraryOrderAuthor.remove(book);
        libraryOrderBarcode.remove(book);
        libraryOrderCallNum.remove(book);
        libraryOrderISBN.remove(book);
       
        // Running through the Fiction ArrayList and checking if a book has the exact same values as the book being removed. If so, it is removed from that ArrayList
        // Although this is inefficient, doing this just like the other ArrayLists wouldn't do anything because you are attempting to remove a book object from a Fiction ArrayList
        for(int i = 0; i < libraryFiction.size(); i++)
        {
            if(libraryFiction.get(i).getTitle().equals(book.getTitle())
            && libraryFiction.get(i).getAuthor().equals(book.getAuthor())
            && libraryFiction.get(i).getBarcode() == book.getBarcode()
            && libraryFiction.get(i).getCallNum().equals(book.getCallNum())
            && libraryFiction.get(i).getISBN().equals(book.getISBN())
            && libraryFiction.get(i).getStatus().equals(book.getStatus())
            && libraryFiction.get(i).getPatronStatus().equals(book.getPatronStatus())
            && libraryFiction.get(i).getHoldStatus().equals(book.getHoldStatus()))
            {
                libraryFiction.remove(libraryFiction.get(i));
            } // end if
        } // end for

        // Running through the NonFiction ArrayList and checking if a book has the exact same values as the book being removed. If so, it is removed from that ArrayList
        // Although this is inefficient, doing this just like the other ArrayLists wouldn't do anything because you are attempting to remove a book object from a NonFiction ArrayList
        for(int i = 0; i < libraryNonFiction.size(); i++)
        {
            if(libraryNonFiction.get(i).getTitle().equals(book.getTitle())
            && libraryNonFiction.get(i).getAuthor().equals(book.getAuthor())
            && libraryNonFiction.get(i).getBarcode() == book.getBarcode()
            && libraryNonFiction.get(i).getCallNum().equals(book.getCallNum())
            && libraryNonFiction.get(i).getISBN().equals(book.getISBN())
            && libraryNonFiction.get(i).getStatus().equals(book.getStatus())
            && libraryNonFiction.get(i).getPatronStatus().equals(book.getPatronStatus())
            && libraryNonFiction.get(i).getHoldStatus().equals(book.getHoldStatus()))
            {
                libraryNonFiction.remove(libraryNonFiction.get(i));
            } // end if
        } // end for
    } // end removeBook

    
    /*********************************************************************************
     * SORTING     
     *********************************************************************************/

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: mergeSort
     * Description: Sorts all of the ArrayLists by splitting them up into single
     * element
     * ArrayLists and merging them back together
     *
     * @param ArrayList<Book> list - The ArrayList being sorted
     * @param String          comparisonField - The order it is being sorted by
     *                        (e.g. title, author, etc.)
     * @param boolean         DEBUG - Used to debug the method
     * @return void
     *********************************************************************************/
    public static void mergeSort(ArrayList<Book> list, String comparisonField)
    {
        /*******************************************************************************
		* Variable Dictionary
		* int listLen - The length of the list
        * ArrayList<Book> leftHalf - The ArrayList that will take the left half of the
        *                            original ArrayList
        * ArrayList<Book> rightHalf - The ArrayList that will take the right half of the
        *                            original ArrayList
		*******************************************************************************/

        int listLen = list.size();
        ArrayList<Book> leftHalf = new ArrayList<Book>();
        ArrayList<Book> rightHalf = new ArrayList<Book>();

        //#debug
        // Debugging: Printing out the list before sorting
        if(debug) 
        {
            System.out.println("Before:\n" + list);
        } // end if
       
        // Base case
        if (listLen < 2)
        {
            return;
        } // end if
       
        // Creating the left and right halves
        int midIndex = listLen / 2;

        // Setting the values of the two parts
        for (int i = 0; i < midIndex; i++)
        {
            leftHalf.add(list.get(i));
        } // end for

        for (int i = midIndex; i < listLen; i++)
        {
            rightHalf.add(list.get(i));
        } // end for

        // Recursively splitting up and merging each section together
        mergeSort(leftHalf, comparisonField);
        mergeSort(rightHalf, comparisonField);
        merge(list, leftHalf, rightHalf, comparisonField);
        
        // Debugging: Printing out the list after sorting
        if(debug)
        {
            System.out.println("After:\n" + list);
        } // end if
    } // end mergeSort

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: merge
     * Description: Merges the ArrayLists back together
     *
     * @param ArrayList<Book> list - The ArrayList that will contain the final
     *                        merged ArrayList
     *                        between the leftHalf and rightHalf
     * @param ArrayList<Book> leftHalf - The left half of the ArrayList being merged
     *                        back together
     * @param ArrayList<Book> rightHalf - The right half of the ArrayList being
     *                        merged back together
     * @param String          comparisonField - The order it is being sorted by
     *                        (e.g. title, author, etc.)
     * @param boolean         DEBUG - Used to debug the method
     * @return void
     *********************************************************************************/
    public static void merge(ArrayList<Book> list, ArrayList<Book> leftHalf, ArrayList<Book> rightHalf,
                             String comparisonField)
    {
        /*******************************************************************************
		* Variable Dictionary
		* int i - index for iterating through the leftHalf ArrayList
		* int j - index for iterating through the rightHalf ArrayList
		* int k - index for placing sorted elements back into the original userList
		*******************************************************************************/
        int i = 0;
        int j = 0;
        int k = 0;

        // Will continue until one arraylist has been fully placed into the list
        while (i < leftHalf.size() && j < rightHalf.size())
        {
            // Checking if the element at i in the leftHalf is less than or equal to the
            // element at j in the rightHalf
            if (Book.compareBy(comparisonField, leftHalf.get(i), rightHalf.get(j)) <= 0)
            {
                list.set(k, leftHalf.get(i));
                i++;
                k++;
            }
            else
            {
                list.set(k, rightHalf.get(j));
                j++;
                k++;
            } // end if
        } // end while

        // Any remaining elements in the right or left half will be placed in the list
        while (i < leftHalf.size())
        {
            list.set(k, leftHalf.get(i));
            i++;
            k++;
        } // end while

        while (j < rightHalf.size())
        {
            list.set(k, rightHalf.get(j));
            j++;
            k++;
        } // end while
    } // end merge
       
    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: singleSort
     * Description: Sorts a single book object into an ArrayList
     *
     * @param ArrayList<Book> list - The ArrayList being sorted
     * @param Book            book - The book being sorted into the ArrayList
     * @param String          comparisonField - The order it is being sorted in by
     *                        (e.g. title, author, etc.)
     * @return void
     *********************************************************************************/
    public static void singleSort(ArrayList<Book> list, Book book, String comparisonField)
    {    
        // Debugging: Printing the book and list before sorting
        if(debug) 
        {
            System.out.println("Before:");
            System.out.println("comparisonField: " + comparisonField);
            System.out.println("book: " + book);
            System.out.println("list:\n" + list);
        } // end if
        
        // Adding the book to the ArrayList
        list.add(book);
        int i = list.size() - 1;
       
        // Moving the book down the ArrayList until the Book below it is lesser than it in terms of the field they are both being compared by or until the loop reaches the end of the ArrayList
        while (i > 0 && Book.compareBy(comparisonField, book, list.get(i - 1)) <= 0)
        {
            list.set(i, list.get(i - 1));
            list.set(i - 1, book);
            i--;
        } // end while
        
        // Debugging: Printing the list after sorting in the book
        if(debug)
        {
            System.out.println("After:");
            System.out.println("List:\n" + list);
        } // end if
        
    }// end singleSort


    /*********************************************************************************
     * SEARCHING     
     *********************************************************************************/

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: binSearch
     * Description: Searches for a book object in an ArrayList that sorts
     * books based on different fields of the book
     *
     * @param ArrayList<Book> list - The ArrayList being searched
     * @param Book            book - The book being searched for
     * @param String          searchField - The way the ArrayList is sorted
     * @return void
     *********************************************************************************/
     //#search
    public static int binSearch(ArrayList<Book> list, Book book, String searchField)
    {
        /*******************************************************************************
		* Variable Dictionary
        * int midIndex - The middle of the ArrayList
        * int location - Set by default to -1, will change to the position of the book
        *                in the ArrayList once found. It is the value that is returned
        *                at the end
        * int top - Set by default to the last index in the ArrrayList. The index in the 
                    ArrayList at the top of the search
        * int bottom - Set by default to 0. The index in the ArrayList at the bottom of 
                       the search
        * boolean found - Set by default to false. Checks if the book has been found yet
		*******************************************************************************/

        // Debugging: Printing out the searchField, book, and list before finding the book in the list
        if(debug)
        {
            System.out.println("searchField: " + searchField);
            System.out.println("book: " + book);
        } // end if
        
        int midIndex;
        int location = -1;
        int top = list.size() - 1;
        int bottom = 0;
        boolean found = false;
       
        while (bottom <= top && !found)
        {
            midIndex = (top + bottom) / 2;

            // The book has been found at the current midIndex
            if (list.get(midIndex).equals(book, searchField))
            {
                found = true;
                location = midIndex;
            }
            // The book is higher up the ArrayList than the book at the current midIndex
            else if (Book.compareBy(searchField, list.get(midIndex), book) < 0)
            {
                bottom = midIndex + 1;
            }
            // The book is lower in the ArrayList than the book at the current midIndex
            else
            {
                top = midIndex - 1;
            } // end if

        } // end while
        
        // Debugging: Printing out the location of the book in the list
        if(debug)
        {
            System.out.println("location: " + location + "\n" + list.get(location));
        } // end if

        return location;
    } // end binSearch
    
    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: specificSeqSearch
     * Description: Uses a sequential search algorithm to search for a book based on
     *              a specified field and compares that field of each book with the
     *              given information being searched for. Returns the index of the
     *              book object in the list
     *
     * @param ArrayList<Book> list - The list being searched through
     * @param String searchField - The field that will be used to compare the object
     *                             with the specified search information
     * @param String searchName - The information that is searched for  
     * @return int - The position of the object in the list. Returns -1 if it doesn't  
     *               exist
     *********************************************************************************/
    public static int specificSeqSearch(ArrayList<Book> list, String searchField, String searchName) 
    {
        // Debugging: Printing out the searchField, searchName, and list before searching
        if(debug)
        {
            System.out.println("searchField: " + searchField);
            System.out.println("searchName: " + searchName);
        } // end if
        
        int location = -1;
        switch(searchField.toLowerCase()) 
        {
            // Searching for a book based on it's title
            case "title":
                
                for(int i = 0; i < list.size(); i++)
                {
                    // The title being searched for has been found
                    if(list.get(i).getTitle().toLowerCase().equals(searchName.toLowerCase()))
                    {
                        location = i;
                    } // end if
                } // end for

                break;

            // Searching for a book based on it's author
            case "author":
                
                for(int i = 0; i < list.size(); i++)
                {
                    // The author being searched for has been found
                    if(list.get(i).getAuthor().toLowerCase().equals(searchName.toLowerCase()))
                    {
                        location = i;
                    } // end if
                } // end for

                break;

            // Searching for a book based on it's barcode
            case "barcode":
                
                for(int i = 0; i < list.size(); i++)
                {
                    // The barcode being searched for has been found
                    if(list.get(i).getBarcode() == Long.parseLong(searchName))
                    {
                        location = i;
                    } // end if
                } // end for

                break;

            // Searching for a book based on it's call number
            case "call number":

                for(int i = 0; i < list.size(); i++)
                {
                    // The call number being searched for has been found
                    if(list.get(i).getCallNum().equals(searchName))
                    {
                        location = i;
                    } // end if
                } // end for

                break;

            // Searching for a book based on it's ISBN
            case "isbn":

                for(int i = 0; i < list.size(); i++)
                {
                    // The ISBN being searched for has been found
                    if(list.get(i).getISBN().equals(searchName))
                    {
                        location = i;
                    } // end if
                } // end for

                break;
        } // end switch-case
        
        // Debugging: Printing out the location of the book after searching for the book
        if(debug)
        {
            System.out.println("location: " + location + "\n" + list.get(location));
        } // end if

        // Returning the index of the Book
        return location;
    } // end specificSeqSearch

    /*********************************************************************************
     * Author: Ibraheem Siddiqui
     * Method Name: keywordSeqSearch
     * Description: Uses a sequential search algorithm to search for a list of books
     *              that contain a given string in their fields
     *
     * @param ArrayList<Book> list - The list being searched through
     * @param String searchField - The field that will be used to compare the object
     *                             with the specified search information
     * @param String searchName - The information that is searched for  
     * @return ArrayList<Book> - The list of books that contain the given string being
     *                           searched for
     *********************************************************************************/
    public static ArrayList<Book> keywordSeqSearch(ArrayList<Book> list, String searchField, String searchName) 
    {
        /*******************************************************************************
		* Variable Dictionary
        * ArrayList<Book> returnedList - The list of books that contain the keyword the 
                                         user is searching that is returned at the end
		*******************************************************************************/

        // Debugging: Printing out the searchField, searchName, and list before searching
        if(debug)
        {
            System.out.println("searchField: " + searchField);
            System.out.println("searchName: " + searchName);
        } // end if
        
        ArrayList<Book> returnedList = new ArrayList<Book>();
        
        switch(searchField.toLowerCase())
        {
            // Searching for a book based on it's title
            case "title":
            
                for(int i = 0; i < list.size(); i++)
                {
                    // If the current book's title contains the keyword, it adds the book to returnedList
                    if(list.get(i).getTitle().toLowerCase().contains(searchName.toLowerCase()))
                    {
                        returnedList.add(list.get(i));
                    } // end if
                } // end for

                break;

            // Searching for a book based on it's author
            case "author":

                for(int i = 0; i < list.size(); i++)
                {
                    // If the current book's author contains the keyword, it adds the book to returnedList
                    if(list.get(i).getAuthor().toLowerCase().contains(searchName.toLowerCase()))
                    {
                        returnedList.add(list.get(i));
                    } // end if
                } // end for
               
                break;

            // Searching for a book based on it's barcode
            case "barcode":

                for(int i = 0; i < list.size(); i++)
                {
                    // If the current book's barcode contains the keyword, it adds the book to returnedList
                    if(String.valueOf(list.get(i).getBarcode()).contains(searchName))
                    {
                        returnedList.add(list.get(i));
                    } // end if
                } // end for
               
                break;

            // Searching for a book based on it's ISBN
            case "isbn":

                for(int i = 0; i < list.size(); i++)
                {
                    // If the current book's ISBN contains the keyword, it adds the book to returnedList
                    if(list.get(i).getISBN().contains(searchName))
                    {
                        returnedList.add(list.get(i));
                    } // end if
                } // end for

                break;
            } // end switch-case
            
            // Debugging: Printing out the list of books found
            if(debug)
            {
                System.out.println("list found:\n" + returnedList);
            } // end if
            
        // Returning the list of books found during the search
        return returnedList;
    } // end seqSearch


    
	/***********************************************************************
	* Borrow / Return / Hold
	***********************************************************************/
	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: borrowBook
	* Purpose: allow a patron to borrow a book
	* @param sNum - stores the borrowers student number
	* @param barcode - stores the barcode of the book that is to be borrowed
	* @return none
	***********************************************************************/
	public static void borrowBook(String sNum, long barcode)
	{
		/*******************************************************************************
		* Variable Dictionary
		* Book tempBook - stores a temporary book to used for borrowing
		* int bookIndex - stores the index of the book in the array list
		*******************************************************************************/
		
		//DEBUG
		if (debug)
		{
			System.out.println("Entering borrowBook");
			System.out.println("sNum: " + sNum);
			System.out.println("barcode: " + barcode);
		} //end if
		
		Book tempBook = new Book();
		
		//sets the tempBook's barcode to barcode
		tempBook.setBarcode(barcode);
		
		//set bookIndex to the index found using binary search with the barcode
		int bookIndex = binSearch(libraryOrderBarcode, tempBook, "barcode");
		
		//checks if the book exists
		if(bookIndex == -1)
		{
			System.out.println("\nThis book does not exist");
		}
		//if it does, checks if the book is already checked out
		else if(libraryOrderBarcode.get(bookIndex).getStatus().equals("Out"))
		{
		    //tells the user the book is checked out by another patron
			System.out.println("\nThis book is already checked out by: "
			                   + libraryOrderBarcode.get(bookIndex).getPatronStatus());
		}
		//otherwise if the book is ready for pickup
		else if(libraryOrderBarcode.get(bookIndex).getStatus().equals("Ready for pickup"))
		{
		    //tell the user the book already has a hold 
		    System.out.println("\nThis book has a hold by :" + libraryOrderBarcode.get(bookIndex).getHoldStatus());
		    System.out.println("This book will now be checked out to them:");
			System.out.println(libraryOrderBarcode.get(bookIndex).getTitle());
			System.out.println(libraryOrderBarcode.get(bookIndex).getAuthor());
		    
		    //sets status to out, patron status to the holders id and hold status to no hold
		    libraryOrderBarcode.get(bookIndex).setStatus("Out");
		    libraryOrderBarcode.get(bookIndex).setPatronStatus(libraryOrderBarcode.get(bookIndex).getHoldStatus());
		    libraryOrderBarcode.get(bookIndex).setHoldStatus("No Hold");
		}
		//otherwise if the book is checked in
		else if (libraryOrderBarcode.get(bookIndex).getStatus().equals("In"))
		{
		    //set the patron status to the patrons student number and status to out
			libraryOrderBarcode.get(bookIndex).setPatronStatus(sNum);
			libraryOrderBarcode.get(bookIndex).setStatus("Out");
			
			//let the user know which book has just been checked out
			System.out.println("\nYou have borrowed the following book:");
			System.out.println(libraryOrderBarcode.get(bookIndex).getTitle());
			System.out.println(libraryOrderBarcode.get(bookIndex).getAuthor());
		}
		//otherwise let the user know the book may be lost or missing
		else
		{
			System.out.println("\nThis book is not registered as in or out");
			System.out.println("This book may be lost or missing");
		} //end if
		
		//DEBUG
		if (debug)
		{
			System.out.println("Exiting borrowBook");
			System.out.println("sNum: " + sNum);
			System.out.println("barcode: " + barcode);
			System.out.println("bookIndex: " + bookIndex);
		} //end if

	} //end borrowBook

	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: returnBook
	* Purpose: allow a patron to return a book
	* @param barcode - stores the barcode of the book to be returned
	* @return none
	***********************************************************************/
	public static void returnBook(long barcode)
	{
		//DEBUG
		if (debug)
		{
			System.out.println("Entering returnBook");
			System.out.println("barcode: " + barcode);
		} //end if
		
		/*******************************************************************************
		* Variable Dictionary
		* Book tempBook - stores a temporary book to used for returning
		* int bookIndex - stores the index of the book in the array list
		*******************************************************************************/
		Book tempBook = new Book();
		tempBook.setBarcode(barcode);

        //set bookIndex to the index found using binary search with the barcode
		int bookIndex = binSearch(libraryOrderBarcode, tempBook, "barcode");
		
		//checks if the book exists
		if(bookIndex == -1)
		{
		    //tells the user the book does not exist
			System.out.println("\nThis book does not exist");
		}
		//if it does, checks if book is already checked in
		else if(libraryOrderBarcode.get(bookIndex).getStatus().equals("In"))
		{
		    //tells the user the book is already checked in
			System.out.println("\nThis book is already checked in");
		}
		//otherwise checks if the book is checked out
		else if (libraryOrderBarcode.get(bookIndex).getStatus().equals("Out"))
		{
		    //sets patron status to 0 and book status to in
			libraryOrderBarcode.get(bookIndex).setPatronStatus("0");
			libraryOrderBarcode.get(bookIndex).setStatus("In");
			
			//tells the user which book has been returned
			System.out.println("\nYou have returned the following book:");
			System.out.println(libraryOrderBarcode.get(bookIndex).getTitle());
			System.out.println(libraryOrderBarcode.get(bookIndex).getAuthor());
			
			//if there is a hold on a book, set the status to ready for pickup and
			//let the user know
			if(!(libraryOrderBarcode.get(bookIndex).getHoldStatus().equals("No Hold")))
			{
			    System.out.println("\nThis book is ready for pickup by the holder");
			    libraryOrderBarcode.get(bookIndex).setStatus("Ready for pickup");
			} //end if
		}
		//otherwise let the user know the book may be lost or missing
		else
		{
			System.out.println("\nThis book is not registered as in or out");
			System.out.println("This book may be lost or missing");
		} //end if
		
		//DEBUG
		if (debug)
		{
			System.out.println("Exiting returnBook");
			System.out.println("barcode: " + barcode);
			System.out.println("bookIndex: " + bookIndex);
		} //end if
	} //end returnBook

	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: holdBook
	* Purpose: allow a patron to hold a book
	* @param sNum - stores the holders student number
	* @param barcode - stores the barcode of the book that is to be held
	* @return none
	***********************************************************************/
	public static void holdBook(String sNum, long barcode)
	{
		/*******************************************************************************
		* Variable Dictionary
		* Book tempBook - stores a temporary book to used for borrowing
		* int bookIndex - stores the index of the book in the array list
		*******************************************************************************/
		
		//DEBUG
		if (debug)
		{
			System.out.println("Entering borrowBook");
			System.out.println("sNum: " + sNum);
			System.out.println("barcode: " + barcode);
		} //end if

		Book tempBook = new Book();
		
		//sets the tempBook's barcode to barcode
		tempBook.setBarcode(barcode);

        //set bookIndex to the index found using binary search with the barcode
		int bookIndex = binSearch(libraryOrderBarcode, tempBook, "barcode");
		
		//checks if the book exists
		if(bookIndex == -1)
		{
		    //tells the user the book does not exist
			System.out.println("\nThis book does not exist");
		}
		//if it does, checks if the book already has a hold
		else if(!(libraryOrderBarcode.get(bookIndex).getHoldStatus().equals("No Hold")))
		{
		    //lets the user know a hold is already placed by another patron
			System.out.println("\nThis book is already held by: "
			                   + libraryOrderBarcode.get(bookIndex).getHoldStatus());
		}
		//otherwise checks in the book hasnt been checked out yet
		else if(libraryOrderBarcode.get(bookIndex).getStatus().equals("In"))
		{
		    //tells the user the book hasnt been checked out yet and checks out the book automatically
		    System.out.println("\nThis book has not been checked out yet, you may not place a hold.");
		    System.out.println("\nChecking out book instead...");
		    borrowBook(sNum, barcode);
		}
		//otherwise checks if the user already has this book checked out
		else if(libraryOrderBarcode.get(bookIndex).getPatronStatus().equals(sNum))
		{
		    //tells the user that they already have this book checked out
		    System.out.println("\nThis user already has this book checked out.");
		}
		//otherwise place a hold on the book
		else
		{
		    //changes the holdStatus to sNum
			libraryOrderBarcode.get(bookIndex).setHoldStatus(sNum);
			
			//lets the user know they have put a hold on the book
			System.out.println("\nYou have put a hold on the following book:");
			System.out.println(libraryOrderBarcode.get(bookIndex).getTitle());
			System.out.println(libraryOrderBarcode.get(bookIndex).getAuthor());
		} //end if
		
				//DEBUG
		if (debug)
		{
			System.out.println("Exiting borrowBook");
			System.out.println("sNum: " + sNum);
			System.out.println("barcode: " + barcode);
			System.out.println("bookIndex: " + bookIndex);
		} //end if
		
	} //end holdBook
	
	/***********************************************************************
	* Author: Imaad Kotadia
    * Method Name: removeHold
	* Purpose: allow a patron to remove a hold
	* @param barcode - stores the barcode of the book to be unheld
	* @return none
	***********************************************************************/
	public static void removeHold(long barcode)
	{
		/*******************************************************************************
		* Variable Dictionary
		* Book tempBook - stores a temporary book to used for returning
		* int bookIndex - stores the index of the book in the array list
		*******************************************************************************/
		
		//DEBUG
		if (debug)
		{
			System.out.println("Entering returnBook");
			System.out.println("barcode: " + barcode);
		} //end if
		
		Book tempBook = new Book();
		
		//sets the tempBook's barcode to barcode
		tempBook.setBarcode(barcode);
		
        //set bookIndex to the index found using binary search with the barcode
		int bookIndex = binSearch(libraryOrderBarcode, tempBook, "barcode");
		
		//checks if the book exists
		if(bookIndex == -1)
		{
		    //tells the user the book does not exist
			System.out.println("\nThis book does not exist");
		}
		//if it does, checks if the book already has no holds
		else if(libraryOrderBarcode.get(bookIndex).getHoldStatus().equals("No Hold"))
		{
		    //tells the user the book already has no holds
			System.out.println("\nThis book already has no holds");
		}
		//otherwise remove the hold
		else
		{
		    //change hold status to no hold
			libraryOrderBarcode.get(bookIndex).setHoldStatus("No Hold");
			
			//let the user know they removed the hold
			System.out.println("\nYou have removed the hold on the following book:");
			System.out.println(libraryOrderBarcode.get(bookIndex).getTitle());
			System.out.println(libraryOrderBarcode.get(bookIndex).getAuthor());
		} //end if
		
		//DEBUG
		if (debug)
		{
			System.out.println("Exiting returnBook");
			System.out.println("barcode: " + barcode);
			System.out.println("bookIndex: " + bookIndex);
		} //end if
	} //end removeHold

} // end Library Class