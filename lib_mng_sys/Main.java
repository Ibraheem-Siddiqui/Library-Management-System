/**************************************************************************************************************
 * Assignment: The Library Management System
 * Description: A library management system that allows the user provide patrons
 * the abiliity to borrow, return, hold and search for books in a library.
 * Authors: Imaad Kotadia, Ibraheem Siddiqui
 * Date: June 13, 2025
 * Course: ICS4U1
 ***************************************************************************************************************/

//imports
import java.util.Scanner;
import java.util.ArrayList;

public class Main
{
	//@author Imaad
	//@date May 30
	/***********************************************************************
	* Method Name: isValidChoice
	* Purpose: Validates if input is valid
	* @param String choice - stores the input string that is to be tested on
	* @return boolean - whether the input is valid or not
	***********************************************************************/
	public static boolean isValidChoice(String choice)
	{
		return choice.equals("1")
		       || choice.equals("2")
		       || choice.equals("3")
		       || choice.equals("4")
		       || choice.equals("5");
	}

	/***********************************************************************
	* Method Name: isValidCallNumChoice
	* Purpose: Validates if input is valid for call numbers
	* @param String choice - stores the input string that is to be tested on
	* @return boolean - whether the input is valid or not
	***********************************************************************/
	public static boolean isValidCallNumChoice(String choice) {
		return choice.equals("1")
		       || choice.equals("2")
		       || choice.equals("3")
		       || choice.equals("4")
		       || choice.equals("5")
		       || choice.equals("6")
		       || choice.equals("7")
		       || choice.equals("8")
		       || choice.equals("9")
		       || choice.equals("10")
		       || choice.equals("11");
	}

	/***********************************************************************
	* Method Name: isNum
	* Purpose: Validates if input is a number
	* @param String num - stores the input string that is to be tested on
	* @return boolean - whether the input is a number or not
	***********************************************************************/
	public static boolean isNum(String num) {

		//enter try catch
		try
		{
			//try to parse the number and if it succeeds num is a number
			Integer.parseInt(num);

		}
		catch(Exception e)
		{
			//if an exception is found return false
			return false;
		} // end try-catch

		//otherwise return true
		return true;
	} // end isNum

	/***********************************************************************
	* Method Name: isValidMenu
	* Purpose: Validates if input is a valid menu choice
	* @param String choice - stores the input string that is to be tested on
	* @return boolean - whether the input is a valid choice or not
	***********************************************************************/
	public static boolean isValidMenu(String choice)
	{
		return choice.equals("1")
		       || choice.equals("2")
		       || choice.equals("3")
		       || choice.equals("4")
		       || choice.equals("5")
		       || choice.equals("6")
		       || choice.equals("7");
	}

	/***********************************************************************
	* Method Name: isValidExit
	* Purpose: Validates if input is a valid exit choice
	* @param String choice - stores the input string that is to be tested on
	* @return boolean - whether the input is a valid exit choice or not
	***********************************************************************/
	//@author Imaad
	//@date May 30
	public static boolean isValidExit(String exitChoice)
	{
		return exitChoice.equals("Y")
		       || exitChoice.equals("N");
	}

	//@author Imaad
	//@date June 3
	public static void main(String[] args) {

		//load patron and library from their respective csv
		Library.loadPatrons();
		Library.createLibrary();

		/*******************************************************************************
		* Variable Dictionary
		* String choice- stores user choice for menu input
		* String menuChoice - stores user choice for submenu input
		* boolean exit - stores whether the user wishes to exit
		* boolean validChoice - stores if the users input is a valid option
		* boolean holdChoice - stores if menu input for the hold menu is valid
		* boolean validIntInput - stores if integer input is valid
		* int patronIndex - stores the index of a patron that is being searched
		* for
		* Patron tempPatron - temporary patron used as an argument for methods
		* String tempSNum - stores user's student number input
		* String tempPNum - stores user's phone number input
		* String tempMail - stores user's email address input
		* Book tempBook - temporary book used as an argument for methods
		* String tempTitle - stores user book title input
		* String tempFirstName - stores user's book author first name input
		* String tempLastName - stores user's book author last name input
		* long tempBarcode - stores user's book barcode input
		* String barcode - stores barcode used as an argument in methods
		* String tempCallNum - stores user's book call number input
		* String tempISBN - stores user's book ISBN input
		* int bookPos - stores the index of a book in a list of books
        * int front - Used to check if there are any instances of the book below the 
        *             orignal book
        * int back - Used to check if there are any instances of the book above the
        *            original book
		* ArrayList<Book> tempBookArrList - stores books that match the given search condition
		*******************************************************************************/

		//create a scanner class
		Scanner input = new Scanner(System.in);

		String choice;
		String menuChoice;
		boolean exit = false;
		boolean validChoice = false;
		boolean holdChoice = false;
		boolean validIntInput = false;
		int patronIndex;
		Patron tempPatron;
		String tempSNum = "";
		String tempPNum = "";
		String tempMail = "";
		Book tempBook;
		String tempTitle = "";
		String tempFirstName = "";
		String tempLastName = "";
		long tempBarcode;
		String barcode;
		String tempCallNum = "";
		String tempISBN = "";
		int bookPos;
        int front = 0;
        int back = 0;
		ArrayList<Book> tempBookArrList = new ArrayList<>();

		//introduces the user to the Library Management System
		System.out.println("--------------------------------------");
		System.out.println("Library Management System");
		System.out.println("Imaad Kotadia, Ibraheem Siddiqui ");
		System.out.println("--------------------------------------");
		System.out.println("Welcome to the Library Management System!");

		//repeats until user wishes to exit
		do
		{
			//lets the user know the possible menu choices
			System.out.println("Please select an option using the corresponding number:");
			System.out.println("(1) Borrow");
			System.out.println("(2) Return");
			System.out.println("(3) Books");
			System.out.println("(4) Patrons");
			System.out.println("(5) Holds");
			System.out.println("(6) Exit");
			System.out.println("(7) DEBUG");
			System.out.print("Your Choice: ");

			//repeats until choice is valid
			do
			{
				//store input in choice
				choice = input.nextLine();

				//tell the user if the input is invalid
				if (!isValidMenu(choice))
				{
					System.out.print("Invalid input. Try again.");
				} //end if
			} while(!isValidMenu(choice));

			//enter switch case
			switch (choice) {

				//case 1, borrow
				case "1":
					//set validChoice to false
					validChoice = false;

					System.out.println("\nYou have selected Borrow.");

					//repeat until a valid input has been entered
					do
					{
						//enter try catch
						try
						{
							//tell the user to input either -1 or the barcode of the book
							//to be borrowed
							System.out.println("Enter -1 to return to the previous menu");
							System.out.println("Please enter the barcode of the book being borrowed:");
							System.out.print("Your Choice: ");
							//#input

							//store choice
							choice = input.nextLine();

							//if choice is -1, return to main menu
							if((choice.equals("-1")))
							{
								validChoice = true;
								break;
							} //end if

							//prompt the user to enter student number of the user that is borrowing
							//the book
							System.out.println("Please enter the student number of the user that is borrowing:");
							System.out.print("Your Choice: ");
							menuChoice = input.nextLine();

							//if the patron is not found, let the user know and exit this menu
							if(Patron.findPatron(menuChoice) == -1)
							{
								System.out.println("This user does not exist");
								choice = "-1";
							}
							//otherwise, borrow the book and set validChoice to true
							else
							{
								Library.borrowBook(menuChoice, Long.parseLong(choice));
								validChoice = true;
							} //end if
						}
						//catch any exceptions and print it out to the user
						catch (Exception e)
						{
							System.out.println("There was an error with your input. Try again.");
						}


					} while(!validChoice);

					break;
				//case 2, return
				case "2":
					//set validChoice to false
					validChoice = false;

					System.out.println("\nYou have selected Return.");

					//repeat until valid input is reached
					do
					{
						//enter try catch
						try
						{
							//prompt user for the barcode of the book to be returned
							System.out.println("Enter -1 to return to the previous menu");
							System.out.println("Please enter the barcode of the book being returned:");
							System.out.print("Your Choice: ");
							//#input

							//store the choice
							choice = input.nextLine();

							//if the input is a -1, return to previous menu
							if((choice.equals("-1")))
							{
								validChoice = true;
								break;
							}
							//otherwise return book and set validChoice to true
							else
							{
								Library.returnBook(Long.parseLong(choice));
								validChoice = true;
							}
						}
						//catch any exceptions and print it out to the user
						catch (Exception e)
						{
							System.out.println("There was an error with your input. Try again.");
						}

					} while(!validChoice);

					break;
				//case 3, books
				case "3":
					//prompt user to enter menu option
					System.out.println("\nYou have selected Books.");
					System.out.println("Please select an option using the corresponding number:");
					System.out.println("(1) Add a book");
					System.out.println("(2) Remove a book");
					System.out.println("(3) Search for a book");
					System.out.println("(4) Print Books");
					System.out.println("(5) Return");
					System.out.print("Your Choice: ");

					//store input in choice
					choice = input.nextLine();

					//repeat while input is not valid
					while(!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5")))
					{
						//prompt the user to try again
						System.out.println("\nInvalid Choice\n");
						System.out.println("Please select an option using the corresponding number:");
						System.out.println("(1) Add a book");
						System.out.println("(2) Remove a book");
						System.out.println("(3) Search for a book");
						System.out.println("(4) Print Books");
						System.out.println("(5) Return");
						System.out.print("Your Choice: ");
						choice = input.nextLine();
					} // end while

					//enter switch case
					switch (choice)
					{
						//case 1, add a book
						case "1":
							//prompt user to title and author input
							System.out.println("\nAdding a Book!");
							System.out.print("\nEnter the Book's title: ");
							tempTitle = input.nextLine();

							System.out.println("Are you sure \"" + tempTitle + "\" is the title of the book you are adding?");
							System.out.println("(1) Yes");
							System.out.println("(2) No");
							System.out.print("Choice: ");
							choice = input.nextLine();

							while(!(choice.equals("1") || choice.equals("2")))
							{
								System.out.println("\nInvalid Choice\nAre you sure \"" + tempTitle + "\" is the title of the book you are adding?");
								System.out.println("(1) Yes");
								System.out.println("(2) No");
								System.out.print("Choice: ");
								choice = input.nextLine();
							} // end while

							while(choice.equals("2"))
							{
								System.out.print("\nEnter the Book's title: ");
								tempTitle = input.nextLine();

								System.out.println("Are you sure \"" + tempTitle + "\" is the title of the book you are adding?");
								System.out.println("(1) Yes");
								System.out.println("(2) No");
								System.out.print("Choice: ");
								choice = input.nextLine();

								while(!(choice.equals("1") || choice.equals("2")))
								{
									System.out.println("\nInvalid Choice\nAre you sure \"" + tempTitle + "\" is the title of the book you are adding?");
									System.out.println("(1) Yes");
									System.out.println("(2) No");
									System.out.println("Choice: ");
									choice = input.nextLine();
								} // end while
							} // end while

							// Ask the user to enter the author's first and last names
							System.out.print("\nEnter the Book's author's first name: ");
							tempFirstName = input.nextLine();
							System.out.print("\nEnter the Book's author's last name: ");
							tempLastName = input.nextLine();

							// Asking the user if this is the name of the author
							System.out.println("Are you sure \"" + tempFirstName + " " + tempLastName + "\" is the author of the book you are adding?");
							System.out.println("(1) Yes");
							System.out.println("(2) No");
							System.out.print("Choice: ");
							choice = input.nextLine();

							// Forcing the user to enter an appropriate response
							while(!(choice.equals("1") || choice.equals("2")))
							{
								System.out.println("\nInvalid Choice\nAre you sure \"" + tempFirstName + " " + tempLastName + "\" is the author of the book you are adding?");
								System.out.println("(1) Yes");
								System.out.println("(2) No");
								System.out.print("Choice: ");
								choice = input.nextLine();
							} // end while

							while(choice.equals("2"))
							{
								System.out.print("\nEnter the Book's author's first name: ");
								tempFirstName = input.nextLine();
								System.out.print("\nEnter the Book's author's last name: ");
								tempLastName = input.nextLine();

								System.out.println("Are you sure \"" + tempFirstName + " " + tempLastName + "\" is the author of the book you are adding?");
								System.out.println("(1) Yes");
								System.out.println("(2) No");
								System.out.print("Choice: ");
								choice = input.nextLine();

								while(!(choice.equals("1") || choice.equals("2")))
								{
									System.out.println("\nInvalid Choice\nAre you sure \"" + tempFirstName + " " + tempLastName + "\" is the author of the book you are adding?");
									System.out.println("(1) Yes");
									System.out.println("(2) No");
									System.out.println("Choice: ");
									choice = input.nextLine();
								} // end while
							} // end while

							//asks the user if the book is fiction or non fiction
							System.out.println("\nIs the Book Fiction or Non-Fiction: ");
							System.out.println("(1) Fiction");
							System.out.println("(2) Non-Fiction");
							System.out.print("Your Choice: ");
							choice = input.nextLine().toLowerCase();
							
							//repeats until input is valid
							while(!(choice.equals("1") || choice.equals("2")))
							{
								System.out.println("\nInvalid Choice\nIs the Book Fiction or Non-Fiction: ");
								choice = input.nextLine().toLowerCase();
							} // end while
							
							//if input is 1, the call number is the letter F and the first 3 letters
							//of the authors name as uppercase
							if(choice.equals("1"))
							{
								if(tempLastName.length() < 3)
								{
									tempCallNum = "F " + tempLastName.toUpperCase();
								}
								else
								{
									tempCallNum = "F " + tempLastName.substring(0, 3).toUpperCase();
								} // end if
							}
							//otherwise it is non fiction
							else
							{
								//ask the user what category the book is
								System.out.println("\nWhat category would the book fall under: ");
								System.out.println("(1) Generalities");
								System.out.println("(2) Philosophy");
								System.out.println("(3) Religion");
								System.out.println("(4) Social Science");
								System.out.println("(5) Languages");
								System.out.println("(6) Natural Sciences");
								System.out.println("(7) Applied Sciences");
								System.out.println("(8) Arts and Recreation");
								System.out.println("(9) Literature");
								System.out.println("(10) Geography and History");
								System.out.println("(11) Biography");
								
								//store input
								choice = input.nextLine();

								//if input is invalid, try again
								while(!isValidCallNumChoice(choice))
								{
									System.out.println("\nInvalid Choice\nWhat category would the book fall under: ");
									System.out.println("(1) Generalities");
									System.out.println("(2) Philosophy");
									System.out.println("(3) Religion");
									System.out.println("(4) Social Science");
									System.out.println("(5) Languages");
									System.out.println("(6) Natural Sciences");
									System.out.println("(7) Applied Sciences");
									System.out.println("(8) Arts and Recreation");
									System.out.println("(9) Literature");
									System.out.println("(10) Geography and History");
									System.out.println("(11) Biography");
									choice = input.nextLine();
								} // end while

								//enter switch case
								switch(choice)
								{
									//for each case, set the last two digits of the call number's number 
									//to a random value and set the hundreds based on the 
									//given category. add the authors first 3 letter of their name
									//to the end of it
									case "1":
										if(tempLastName.length() < 3)
										{
											tempCallNum = String.valueOf(Math.round((Math.random() * 100) * 1000) / 1000.0) + " " + tempLastName.toUpperCase();
										}
										else
										{
											tempCallNum = String.valueOf(Math.round((Math.random() * 100) * 1000) / 1000.0) + " " + tempLastName.substring(0, 3).toUpperCase();
										} // end if

										break;
									case "2":
										if(tempLastName.length() < 3)
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 100) * 1000) / 1000.0) + " " +  tempLastName.toUpperCase();
										}
										else
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 100) * 1000) / 1000.0) + " " +  tempLastName.substring(0, 3).toUpperCase();
										} // end if

										break;
									case "3":
										if(tempLastName.length() < 3)
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 200) * 1000) / 1000.0) + " " +  tempLastName.toUpperCase();
										}
										else
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 200) * 1000) / 1000.0) + " " +  tempLastName.substring(0, 3).toUpperCase();
										} // end if

										break;
									case "4":
										if(tempLastName.length() < 3)
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 300) * 1000) / 1000.0) + " " + tempLastName.toUpperCase();
										}
										else
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 300) * 1000) / 1000.0) + " " + tempLastName.substring(0, 3).toUpperCase();
										} // end if

										break;
									case "5":
										if(tempLastName.length() < 3)
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 400) * 1000) / 1000.0) + " " + tempLastName.toUpperCase();
										}
										else
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 400) * 1000) / 1000.0) + " " + tempLastName.substring(0, 3).toUpperCase();
										} // end if
										
										break;
									case "6":
										if(tempLastName.length() < 3)
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 500) * 1000) / 1000.0) + " " + tempLastName.toUpperCase();
										} 
										else
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 500) * 1000) / 1000.0) + " " + tempLastName.substring(0, 3).toUpperCase();
										} // end if

										break;
									case "7":
										if(tempLastName.length() < 3)
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 600) * 1000) / 1000.0) + " " + tempLastName.toUpperCase();
										}
										else
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 600) * 1000) / 1000.0) + " " + tempLastName.substring(0, 3).toUpperCase();
										} // end if

										break;
									case "8":
										if(tempLastName.length() < 3)
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 700) * 1000) / 1000.0) + " " + tempLastName.toUpperCase();
										}
										else
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 700) * 1000) / 1000.0) + " " + tempLastName.substring(0, 3).toUpperCase();
										} // end if

										break;
									case "9":
										if(tempLastName.length() < 3)
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 800) * 1000) / 1000.0) + " " + tempLastName.toUpperCase();
										}
										else
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 800) * 1000) / 1000.0) + " " + tempLastName.substring(0, 3).toUpperCase();
										} // end if

										break;
									case "10":
										if(tempLastName.length() < 3)
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 900) * 1000) / 1000.0) + " " + tempLastName.toUpperCase();
										}
										else
										{
											tempCallNum = String.valueOf(Math.round(((Math.random() * 100) + 900) * 1000) / 1000.0) + " " + tempLastName.substring(0, 3).toUpperCase();
										} // end if

										break;
									case "11":
										if(tempLastName.length() < 3)
										{
											tempCallNum = "BIO " + String.valueOf(Math.round(((Math.random() * 100) + 900) * 1000) / 1000.0) + " " + tempLastName.toUpperCase();
										}	
										else
										{
											tempCallNum = "BIO " + String.valueOf(Math.round(((Math.random() * 100) + 900) * 1000) / 1000.0) + " " + tempLastName.substring(0, 3).toUpperCase();
										} // end if

										break;
								} // end switch-case

							} // end if

							//empty out the tempISBN string
							tempISBN = "";

							//itserates through each element in the library ordered by title
							for(int i = 0; i < Library.getLibraryOrderTitle().size(); i++)
							{
								if(Library.getLibraryOrderTitle().get(i).getTitle().equals(tempTitle) && Library.getLibraryOrderTitle().get(i).getAuthor().equals(tempLastName + ", " + tempFirstName))
								{
									//if a book with the same name and author already exist, 
									//set the isbn of this book to the other books isbn
									tempISBN = Library.getLibraryOrderTitle().get(i).getISBN();
								} // end if
							} // end for
							
							//checks if the isbn is still empty
							if(tempISBN.isEmpty())
							{
								//asks for user to enter an isbn 
								System.out.print("\nEnter the Book's ISBN: ");
								tempISBN = input.nextLine();
							} // end if

							//asks the user to repeat until input is valid
							while(!Book.isValidISBN(tempISBN))
							{
								System.out.print("\nInvalid Choice\nEnter the Book's ISBN: ");
								tempISBN = input.nextLine();
							} // end while

							//add book to library
							tempBook = new Book(tempTitle, tempLastName + ", " + tempFirstName, Book.generateBarcode(), tempCallNum, tempISBN, "In", "0", "No Hold");
							Library.addBook(tempBook);
							System.out.println("\n" + tempBook.getTitle() + " Added Successfully!");

							break;
						//case 2, remove a book
						case "2":
							//ask the user how would they like to remove the book
							System.out.println("\nRemoving a Book!");
							System.out.println("How would you like to remove the Book:");
							System.out.println("(1) Title");
							System.out.println("(2) Barcode");
							System.out.println("(3) ISBN");
							System.out.println("(4) Return");
							System.out.print("Your Choice: ");
							
							//store user choice
							choice = input.nextLine();

							//repeat until user input is valid
							while(!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")))
							{
								System.out.println("\nInvalid Choice\nHow would you like to remove the Book");
								System.out.println("(1) Title");
								System.out.println("(2) Barcode");
								System.out.println("(3) ISBN");
								System.out.println("(4) Return");
								System.out.print("Your Choice: ");
								choice = input.nextLine();
							} // end while

							//enter switch case
							switch(choice)
							{
								//case 1, title
								case "1":
									//get the title of the book
									System.out.print("\nWhat is the title of the book: ");
									tempTitle = input.nextLine();

									//find the position of the book in the library array list
									bookPos = Library.specificSeqSearch(Library.getLibraryOrderTitle(), "title", tempTitle);

									//if the book doesnt exist let the user know
									if(bookPos == -1)
									{
										System.out.println("\nBook does not exist in library");
									}
									//otherwise, remove the book from the library
									else
									{
										//add a temporary copy of the book at the bookPos index
										tempBook = new Book(Library.getLibraryOrderTitle().get(bookPos));

										//confirm if the user wishes to remove the book
										System.out.println("Are you sure you want to remove \" " + tempBook.getTitle() + "\" by: \"" + tempBook.getAuthor() +  "\" from the library?");
										System.out.println("By doing this you will be removing all instances of this book");
										System.out.println("(1) Yes");
										System.out.println("(2) No");
										System.out.print("Your Choice: ");
										choice = input.nextLine();

										//repeat until user input is correct
										while(!(choice.equals("1") || choice.equals("2")))
										{
											System.out.println("Are you sure you want to remove \" " + tempBook.getTitle() + "\" by: \"" + tempBook.getAuthor() +  "\" from the library?");
											System.out.println("By doing this you will be removing all instances of this book");
											System.out.println("(1) Yes");
											System.out.println("(2) No");
											System.out.print("Your Choice: ");
											choice = input.nextLine();
										} // end while

										//enter switch case
										switch(choice)
										{
											//case 1, remove the book
											case "1":
												// Removing the original book
												Library.removeBook(tempBook);

												// Using a front and back variable to check if there's any more instances of the book
												front = bookPos;
												back = bookPos - 1;
												
												// Checking if there were any instances of the same book below the original book
												while(!(front == Library.getLibraryOrderTitle().size()) 
													&& (Library.getLibraryOrderTitle().get(front).equals(tempBook, "title") 
													&& Library.getLibraryOrderTitle().get(front).equals(tempBook, "author") 
													&& Library.getLibraryOrderTitle().get(front).equals(tempBook, "callnum") 
													&& Library.getLibraryOrderTitle().get(front).equals(tempBook, "isbn")))
												{
														Library.removeBook(Library.getLibraryOrderTitle().get(front));
												} // end while

												// Checking if there were any instances of the same book above the original book
												while(!(back < 0) 
													&& (Library.getLibraryOrderTitle().get(back).equals(tempBook, "title") 
													&& Library.getLibraryOrderTitle().get(back).equals(tempBook, "author") 
													&& Library.getLibraryOrderTitle().get(back).equals(tempBook, "callnum") 
													&& Library.getLibraryOrderTitle().get(back).equals(tempBook, "isbn")))
												{
													Library.removeBook(Library.getLibraryOrderTitle().get(back));
													back--;
												} // end while

												//tell the user the book has been removed
												System.out.println("\n\"" + tempBook.getTitle() + "\" has been successfully Removed!");

												break;
											//case 2, return to menu
											case "2":
												//tell the user that they are returning to the main menu
												System.out.println("\nReturning...");

												break;
											} // end switch-case

										} // end if

										break;
									//case 2, barcode
									case "2":

										//prompt user for the barcode of the book
										System.out.println("\nWhat is the barcode of the book?");
										barcode = input.nextLine();

										//repeat until barcode input is valid
										while(!Book.isValidBarcode(barcode))
										{
											System.out.println("\nInvalid Barcode\nEnter the barcode of the book?");
											barcode = input.nextLine();
										} // end while

										//find the position of the book in the library array list
										bookPos = Library.specificSeqSearch(Library.getLibraryOrderBarcode(), "barcode", barcode);

										//if the book doesnt exist let the user know
										if(bookPos == -1)
										{
											System.out.println("\nBook does not exist in library");
										}
										//otherwise, remove the book from the library
										else
										{
											//add a temporary copy of the book at the bookPos index
											tempBook = new Book(Library.getLibraryOrderBarcode().get(bookPos));

											//confirm if the user wishes to remove the book
											System.out.println("Are you sure you want to remove \" " + tempBook.getTitle() + "\" by: \"" + tempBook.getAuthor() +  "\" from the library?");
											System.out.println("(1) Yes");
											System.out.println("(2) No");
											System.out.print("Your Choice: ");
											choice = input.nextLine();

											//repeat until user input is correct
											while(!(choice.equals("1") || choice.equals("2")))
											{
												System.out.println("Are you sure you want to remove \" " + tempBook.getTitle() + "\" by: \"" + tempBook.getAuthor() +  "\" from the library?");
												System.out.println("(1) Yes");
												System.out.println("(2) No");
												System.out.print("Your Choice: ");
												choice = input.nextLine();
											} // end while

											//enter switch case
											switch(choice)
											{
												//case 1, remove book
												case "1":
													Library.removeBook(Library.getLibraryOrderBarcode().get(bookPos));

													System.out.println("\n\" " + tempBook.getTitle() +  "\" has been successfully Removed!");

													break;
												//case 2, return to main menu
												case "2":
													System.out.println("\nReturning...");

													break;
											} // end switch-case

										} // end if

										break;
									//case 3, ISBN
									case "3":
										//prompt the user for isbn
										System.out.println("\nWhat is the ISBN of the book?");
										tempISBN = input.nextLine();

										//repeat until user input is correct
										while(!Book.isValidISBN(tempISBN))
										{
											System.out.println("\nInvalid ISBN\nEnter the ISBN of the book?");
											tempISBN = input.nextLine();
										} // end while

										//find the position of the book in the library array list
										bookPos = Library.specificSeqSearch(Library.getLibraryOrderISBN(), "isbn", tempISBN);

										//if the book doesnt exist let the user know
										if(bookPos == -1)
										{
											System.out.println("\nBook does not exist in library");
										}
										//otherwise, remove the book from the library
										else
										{
											//add a temporary copy of the book at the bookPos index
											tempBook = new Book(Library.getLibraryOrderISBN().get(bookPos));

											//confirm if the user wishes to remove the book
											System.out.println("Are you sure you want to remove \" " + tempBook.getTitle() + "\" by: \"" + tempBook.getAuthor() +  "\" from the library?");
											System.out.println("By doing this you will be removing all instances of this book");
											System.out.println("(1) Yes");
											System.out.println("(2) No");
											System.out.print("Your Choice: ");
											choice = input.nextLine();

											//repeat until user input is correct
											while(!(choice.equals("1") || choice.equals("2")))
											{
												System.out.println("Are you sure you want to remove\" " + tempBook.getTitle() + "\" by: \"" + tempBook.getAuthor() +  "\" from the library?");
												System.out.println("By doing this you will be removing all instances of this book");
												System.out.println("(1) Yes");
												System.out.println("(2) No");
												System.out.print("Your Choice: ");
												choice = input.nextLine();
											} // end while

											switch(choice)
											{
												//case 1, remove book
												case "1":
													// Removing the original book
													Library.removeBook(tempBook);

													// Using a front and back variable to check if there's any more instances of the book
													front = bookPos;
													back = bookPos - 1;
													
													// Checking if there were any instances of the same book below the original book
													while(!(front == Library.getLibraryOrderISBN().size()) 
														&& (Library.getLibraryOrderISBN().get(front).equals(tempBook, "title") 
														&& Library.getLibraryOrderISBN().get(front).equals(tempBook, "author") 
														&& Library.getLibraryOrderISBN().get(front).equals(tempBook, "callnum") 
														&& Library.getLibraryOrderISBN().get(front).equals(tempBook, "isbn")))
													{
															Library.removeBook(Library.getLibraryOrderISBN().get(front));
													} // end while

													// Checking if there were any instances of the same book above the original book
													while(!(back < 0) 
														&& (Library.getLibraryOrderISBN().get(back).equals(tempBook, "title") 
														&& Library.getLibraryOrderISBN().get(back).equals(tempBook, "author") 
														&& Library.getLibraryOrderISBN().get(back).equals(tempBook, "callnum") 
														&& Library.getLibraryOrderISBN().get(back).equals(tempBook, "isbn")))
													{
														Library.removeBook(Library.getLibraryOrderISBN().get(back));
														back--;
													} // end while

													//tell the user the book has been removed
													System.out.println("\n\"" + tempBook.getTitle() + "\" has been successfully Removed!");

													break;
												//case 2, return to main menu
												case "2":
													//tell the user that they are returning to the main menu
													System.out.println("\nReturning...");

													break;
											} // end switch-case

										} // end if

										break;
									//case 4, return to main menu
									case "4":
										System.out.println("\nReturning...");

										break;
							} // end switch-case

							break;
						//case 3, search for a book
						case "3":
							//prompt the user to enter how they wish to search for the book
							System.out.println("\nSearching for a book!\nWould you like to do a General or Specific search: ");
							System.out.println("(1) Keyword");
							System.out.println("(2) Specific");
							System.out.println("(3) Return");
							System.out.print("Your Choice: ");
							
							//store user input in choice
							choice = input.nextLine();

							//repeat until user input is correct
							while(!(choice.equals("1") || choice.equals("2") || choice.equals("3")))
							{
								System.out.println("\nInvalid Choice\nWould you like to do a Keyword or Specific search: ");
								System.out.println("(1) Keyword");
								System.out.println("(2) Specific");
								System.out.println("(3) Return");
								System.out.print("Your Choice: ");
								choice = input.nextLine();
							} // end while

							//enter switch case
							switch(choice)
							{
								//case 1, keyword search
								case "1":
									//ask the user how they wish to search
									System.out.println("\nYou Chose to do a Keyword Search!\nWhat would you like to search by: ");
									System.out.println("(1) Title");
									System.out.println("(2) Author");
									System.out.print("Your Choice: ");
									choice = input.nextLine();

									//repeat until user input is correct
									while(!(choice.equals("1") || choice.equals("2")))
									{
										System.out.println("\nInvalid Choice\nWhat would you like to search by: ");
										System.out.println("(1) Title");
										System.out.println("(2) Author");
										System.out.print("Your Choice: ");
										choice = input.nextLine();
									} // end while

									//enter switch case
									switch(choice)
									{
										//case 1, keyword search
										case "1":
											//prompt user for keyword
											System.out.print("\n\nYou chose to do a Keyword Search by Title!\nWhat is the keyword you want to search by: ");
											choice = input.nextLine();

											//store all books that match that keyword in tempBookArrList
											tempBookArrList = Library.keywordSeqSearch(Library.getLibraryOrderTitle(), "title", choice);

											//if no book is found, let the user know
											if(tempBookArrList.size() == 0)
											{
												System.out.println("\nNo book with the keyword: " + choice + " in their title is in the library");
											}
											//otherwise
											else
											{
												//iterate through each book in tempBookArrList
												for(int i = 0; i < tempBookArrList.size(); i++)
												{
													//get the book and print it out
													tempBook = tempBookArrList.get(i);
													System.out.println((i + 1) + ": " + tempBook.getCallNum() + ", " + tempBook.getTitle() + ", " + tempBook.getAuthor() + ", " + tempBook.getBarcode() + ", " + tempBook.getISBN());
												} // end for

												//ask the user if they wish to borrow a book
												System.out.println("\nWould you like to borrow a book:");
												System.out.println("(1) Yes");
												System.out.println("(2) No");
												System.out.print("Your Choice: ");
												choice = input.nextLine();

												//repeat until user input is correct
												while(!(choice.equals("1") || choice.equals("2")))
												{
													System.out.println("\nInvalid Choice\nWould you like to borrow a book:");
													System.out.println("(1) Yes");
													System.out.println("(2) No");
													System.out.print("Your Choice: ");
													choice = input.nextLine();
												} // end while

												//enter switch case
												switch(choice)
												{
													//case 1, to borrow
													case "1":
														//asks which book the user wishes to borrow
														System.out.print("\nWhich book would you like to borrow: ");
														choice = input.nextLine();

														//repeat until input is valid
														while(!validIntInput)
														{
															//tell the user if their input is not a number
															if(!isNum(choice))
															{
																System.out.print("\nInvalid Choice\nWhich book would you like to borrow: ");
																choice = input.nextLine();
																continue;
															} // end if

															//tell the user that their choice is invalid
															if(!(Integer.parseInt(choice) >= 1 && Integer.parseInt(choice) <= tempBookArrList.size()))
															{
																System.out.print("\nInvalid Choice\nWhich book would you like to borrow: ");
																choice = input.nextLine();
																continue;
															} // end if

															//set validIntInput to true
															validIntInput = true;
														} // end while

														//print out the temp book and ask the user if they would like to check it out
														tempBook = tempBookArrList.get(Integer.parseInt(choice) - 1);
														System.out.println("\nBook: " + tempBook);
														if(tempBook.getStatus().equals("In") && tempBook.getPatronStatus().equals("0") && tempBook.getHoldStatus().equals("No Hold"))
														{
															System.out.println("\nWould you like to check this book out?");
															System.out.println("(1) Yes");
															System.out.println("(2) No");
															System.out.print("Your Choice: ");
															choice = input.nextLine();

															//repeat until user input is correct
															while(!(choice.equals("1") || choice.equals("2")))
															{
																System.out.println("\nInvalid Choice\nWould you like to check this book out?");
																System.out.println("(1) Yes");
																System.out.println("(2) No");
																System.out.print("Your Choice: ");
																choice = input.nextLine();
															} // end while

															//enter switch case
															switch(choice)
															{

															//case 1
															case "1":
																/***********************************
																copy of code from borrow menu option
																***********************************/
																System.out.println("\nEnter -1 to return\nEnter the Student ID of the user borrowing the book");
																choice = input.nextLine();

																if(choice.equals("-1"))
																{
																	System.out.println("\nReturning...");
																}
																else
																{
																	while(!(choice.equals("-1")) && Patron.findPatron(choice) == -1)
																	{
																		System.out.println("\nInvalid Input\nEnter -1 to return\nEnter the Student ID of the user borrowing the book");
																		choice = input.nextLine();
																	} // end while

																	if(choice.equals("-1"))
																	{
																		System.out.println("\nReturning...");
																	}
																	else
																	{
																		Library.borrowBook(choice, tempBook.getBarcode());
																	} // end if

																} // end if

																break;
															//case 2, return to menu
															case "2":
																System.out.println("\nReturning...");

																break;

															} // end switch-case

														}
														//if the book has been checked out but has no holds, allow the user to hold the book
														else if(tempBook.getHoldStatus().equals("No Hold"))
														{
															//ask the user if they wish to hold the book
															System.out.println("\nIt seems someone else has checked out the book\nWould you like to put the book on hold: ");
															System.out.println("(1) Yes");
															System.out.println("(2) No");
															System.out.print("Your Choice: ");
															choice = input.nextLine();

															//repeat until user input is correct
															while(!(choice.equals("1") || choice.equals("2")))
															{
																System.out.println("\nInvalid Choice\nWould you like to put a hold on this book out?");
																System.out.println("(1) Yes");
																System.out.println("(2) No");
																System.out.print("Your Choice: ");
																choice = input.nextLine();
															} // end while

															//enter switch case
															switch(choice)
															{
															case "1":
																/***********************************
																copy of code from hold menu option
																***********************************/
																System.out.println("\nEnter -1 to return to main menu\nEnter the Student ID of the user holding the book");
																choice = input.nextLine();

																if(choice.equals("-1"))
																{
																	System.out.println("\nReturning...");
																}
																else
																{
																	while(!(choice.equals("-1")) && Patron.findPatron(choice) == -1)
																	{
																		System.out.println("\nInvalid Input\nEnter -1 to return\nEnter the Student ID of the user putting a hold on the book");
																		choice = input.nextLine();
																	} // end while

																	if(choice.equals("-1"))
																	{
																		System.out.println("\nReturning...");
																	}
																	else
																	{
																		Library.holdBook(choice, tempBook.getBarcode());
																	} // end if

																} // end if

																break;
															//case 2, return to main menu
															case "2":
																System.out.println("\nReturning...");

																break;

															} // end switch-case
														}
														//otherwise let the user know the book is unable to be checked out or held
														else
														{
															System.out.println("\nUnforunately this book cannot be checked out nor can it be put on hold\nReturning");
														} // end if

														//ask if the user wishes to change the information of the book
														System.out.println("\nWould you like to change the information of this book:");
														System.out.println("(1) Yes");
														System.out.println("(2) No");
														System.out.print("Your Choice: ");
														choice = input.nextLine();

														//repeat until user input is correct
														while(!(choice.equals("1") || choice.equals("2")))
														{
															System.out.println("\nInvalid Choice\nWould you like to change the information of this book:");
															System.out.println("(1) Yes");
															System.out.println("(2) No");
															System.out.print("Your Choice: ");
															choice = input.nextLine();
														} // end while

														//enter switch case
														switch(choice)
														{
															case "1":
																//ask the user what they would like to change about the book
																System.out.println("\nWhat would you like to change about the book:");
																System.out.println("(1) Title");
																System.out.println("(2) Call Number");
																System.out.println("(3) Barcode");
																System.out.println("(4) Return");
																System.out.print("Your Choice: ");
																choice = input.nextLine();

																//repeat until user input is correct
																while(!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")))
																{
																	System.out.println("\nInvalid Choice\nWhat would you like to change about the book:");
																	System.out.println("(1) Title");
																	System.out.println("(2) Call Number");
																	System.out.println("(3) Barcode");
																	System.out.println("(4) Return");
																	System.out.print("Your Choice: ");
																	choice = input.nextLine();
																} // end while

																//enter switch case
																switch(choice)
																{
																	//case 1, book title
																	case "1":
																		System.out.print("\nEnter -1 to return\nEnter the new Title of the Book: ");
																		choice = input.nextLine();

																		//if the choice is not to return, change the title to the new desired title
																		if(!(choice.equals("-1")))
																		{
																			System.out.println("\nPrevious Title: " + tempBook.getTitle());
																			tempBook.setTitle(choice);
																			System.out.println("New Title: " + tempBook.getTitle());
																		} // end if

																		System.out.println("\nReturning...");

																		break;
																	//case 2, call number
																	case "2":

																		//if a book is fiction, you cannot change its call number
																		if(tempBook.isFiction())
																		{
																			System.out.println("\nUnforunately this book's call number cannot be changed");
																		}
																		//otherwise
																		else
																		{
																			//prompt the user to enter the new dewey decimal number
																			System.out.println("\nEnter -1 to return to main menu\nEnter the new Dewey Decimal of the number: ");
																			choice = input.nextLine();

																			//while the choice is not to return or the input is valid 
																			while(!(choice.equals("-1")) && (!isNum(choice) || !(Double.parseDouble(choice) > 0 && Double.parseDouble(choice) < 1000)))
																			{
																				//let the user know the input was invalid
																				System.out.println("\nInvalid Input\nEnter -1 to return to main menu\nEnter the new Dewey Decimal of the number: ");
																				choice = input.nextLine();
																			} // end while

																			//if the choice is not to return change the call number
																			if(!(choice.equals("-1")))
																			{
																				//in the case the call number had the word BIO at the start keep it 
																				if(tempBook.getCallNum().substring(0, 3).equals("BIO"))
																				{
																					//change the call number
																					System.out.println("\nPrevious Call Number: " + tempBook.getCallNum());
																					tempBook.setCallNum("BIO " + choice + tempBook.getCallNum().substring(tempBook.getCallNum().indexOf(' ', 4)));
																					System.out.println("New Call Number: " + tempBook.getCallNum());
																				}
																				//otherwise 
																				else
																				{
																					//change the call number
																					System.out.println("\nPrevious Call Number: " + tempBook.getCallNum());
																					tempBook.setCallNum(choice + tempBook.getCallNum().substring(tempBook.getCallNum().indexOf(' ')));
																					System.out.println("New Call Number: " + tempBook.getCallNum());
																				} // end if

																			} // end if

																			System.out.println("\nReturning...");

																		} // end if

																		break;
																	//case 3, by the barcode of a book
																	case "3":
																		//prompt the user for a barcode
																		System.out.print("\nEnter -1 to return to main menu\nEnter the new Barcode of the Book: ");
																		choice = input.nextLine();

																		//repeat until user input is correct
																		while(!(choice.equals("-1")) && !(Book.isValidBarcode(choice)))
																		{
																			System.out.print("\nInvalid Input\nEnter the new barcode of the Book: ");
																			choice = input.nextLine();
																		} // end while

																		//if the choice is not to return
																		if(!(choice.equals("-1")))
																		{
																			//change the barcode
																			System.out.println("\nPrevious Barcode: " + tempBook.getBarcode());
																			tempBook.setBarcode(Long.parseLong(choice));
																			System.out.println("New Barcode: " + tempBook.getBarcode());
																		} // end if

																		System.out.println("\nReturning...");

																		break;
																	//case 4, return to main menu
																	case "4":
																		System.out.println("\nReturning...");

																		break;
																} // end switch-case

																break;
															//case 2, return to main menu
															case "2":
																System.out.println("\nReturning...");

																break;
														} // end switch-case

														break;
													//case 2, return to main menu
													case "2":
														System.out.println("\nReturning");

														break;

												} // end switch-case

											} // end if

											break;
										//case 2, keyword search
										case "2":
											System.out.print("\nYou chose to do a Keyword Search by Author!\nWhat is the keyword you want to search by: ");
											choice = input.nextLine();

											// Creating an ArrayList for the all the books in the library that contain the keyword in the author's name 
											tempBookArrList = Library.keywordSeqSearch(Library.getLibraryOrderAuthor(), "author", choice);

											// Checking if the ArrayLists size is zero, if so it means no books had the keyword the user was looking for in the author's name
											if(tempBookArrList.size() == 0)
											{
												System.out.println("\nNo book with the keyword: " + choice + " in the author's name is in the library");
											}
											else
											{
												// Printing all books that were found
												for(int i = 0; i < tempBookArrList.size(); i++)
												{
													System.out.println((i + 1) + ": " + tempBookArrList.get(i));
												} // end for

												// Asking the user if they would like to borrow a book
												System.out.println("\nWould you like to borrow a book:");
												System.out.println("(1) Yes");
												System.out.println("(2) No");
												System.out.print("Your Choice: ");
												choice = input.nextLine();

												// Forcing them to enter an appropriate answer
												while(!(choice.equals("1") || choice.equals("2")))
												{
													System.out.println("\nInvalid Choice\nWould you like to borrow a book:");
													System.out.println("(1) Yes");
													System.out.println("(2) No");
													System.out.print("Your Choice: ");
													choice = input.nextLine();
												} // end while

												switch(choice)
												{
													//case 1
													case "1":
														System.out.print("\nWhich book would you like to borrow: ");
														choice = input.nextLine();

														// Forcing them to enter a number from the book list
														while(!validIntInput)
														{
															if(!isNum(choice))
															{
																System.out.print("\nInvalid Choice\nWhich book would you like to borrow: ");
																choice = input.nextLine();
																continue;
															} // end if

															if(!(Integer.parseInt(choice) >= 1 && Integer.parseInt(choice) <= tempBookArrList.size()))
															{
																System.out.print("\nInvalid Choice\nWhich book would you like to borrow: ");
																choice = input.nextLine();
																continue;
															} // end if

															validIntInput = true;
														} // end while

														// Printing out the book
														tempBook = tempBookArrList.get(Integer.parseInt(choice) - 1);
														System.out.println("\nBook: " + tempBook);

														// Book has no hold on it and is in the library
														if(tempBook.getStatus().equals("In") && tempBook.getPatronStatus().equals("0") && tempBook.getHoldStatus().equals("No Hold"))
														{
															// Asking the user if they would like to check the book out
															System.out.println("\nWould you like to check this book out?");
															System.out.println("(1) Yes");
															System.out.println("(2) No");
															System.out.print("Your Choice: ");
															choice = input.nextLine();

															// Forcing them to enter an appropriate answer
															while(!(choice.equals("1") || choice.equals("2")))
															{
																System.out.println("\nInvalid Choice\nWould you like to check this book out?");
																System.out.println("(1) Yes");
																System.out.println("(2) No");
																System.out.print("Your Choice: ");
																choice = input.nextLine();
															} // end while

															switch(choice)
															{
																//case 1
																case "1":
																	// User can go back to main menu by entering -1
																	// Getting the user to enter an existing student ID
																	System.out.println("\nEnter -1 to return to main menu\nEnter the Student ID of the user borrowing the book");
																	choice = input.nextLine();

																	if(choice.equals("-1"))
																	{
																		System.out.println("\nReturning...");
																	}
																	else
																	{
																		// Forcing the user to enter an existing student ID or quit by entering -1
																		while(!(choice.equals("-1")) && Patron.findPatron(choice) == -1)
																		{
																			System.out.println("\nInvalid Input\nEnter -1 to return\nEnter the Student ID of the user borrowing the book");
																			choice = input.nextLine();
																		} // end while

																		// Going back to main menu
																		if(choice.equals("-1"))
																		{
																			System.out.println("\nReturning...");
																		}
																		// Student ID found and book has been checked out
																		else
																		{
																			Library.borrowBook(choice, tempBook.getBarcode());
																		} // end if

																	} // end if

																	break;
																// case 2
																case "2":
																	System.out.println("\nReturning...");

																	break;
															} // end switch-case

														}
														// Book is checked out but has no hold on it
														else if(tempBook.getHoldStatus().equals("No Hold"))
														{
															// Asking if the user would like to put a hold on the book
															System.out.println("\nIt seems someone else has checked out the book\nWould you like to put the book on hold: ");
															System.out.println("(1) Yes");
															System.out.println("(2) No");
															System.out.print("Your Choice: ");
															choice = input.nextLine();

															// Forcing the user to enter an appropriate answer
															while(!(choice.equals("1") || choice.equals("2")))
															{
																System.out.println("\nInvalid Choice\nWould you like to put a hold on this book out?");
																System.out.println("(1) Yes");
																System.out.println("(2) No");
																System.out.print("Your Choice: ");
																choice = input.nextLine();
															} // end while

															switch(choice)
															{
																//case 1
																case "1":
																	// User can enter -1 to return to main menu
																	// Asking the user to enter an existing student ID
																	System.out.println("\nEnter -1 to return\nEnter the Student ID of the user holding the book");
																	choice = input.nextLine();

																	// Returning to main menu
																	if(choice.equals("-1"))
																	{
																		System.out.println("\nReturning...");
																	}
																	else
																	{
																		// Forcing the user to enter an existing student ID or return to main menu by entering -1
																		while(!(choice.equals("-1")) && Patron.findPatron(choice) == -1)
																		{
																			System.out.println("\nInvalid Input\nEnter -1 to return\nEnter the Student ID of the user putting a hold on the book");
																			choice = input.nextLine();

																		} // end while

																		// Returning to main menu
																		if(choice.equals("-1"))
																		{
																			System.out.println("\nReturning...");
																		}
																		// Putting a hold on the book
																		else
																		{
																			Library.holdBook(choice, tempBook.getBarcode());
																		} // end if

																	} // end if

																	break;
																case "2":
																	System.out.println("\nReturning...");

																	break;

															} // end switch-case
														}
														// Book cannot be checked out nor can it be put on hold
														else
														{
															System.out.println("\nUnforunately this book cannot be checked out nor can it be put on hold\nReturning...");
														} // end if

														// Asking the user if they would like to change the book's information
														System.out.println("\nWould you like to change the information of this book:");
														System.out.println("(1) Yes");
														System.out.println("(2) No");
														System.out.print("Your Choice: ");
														choice = input.nextLine();

														// Forcing the user to enter an appropriate repsonse
														while(!(choice.equals("1") || choice.equals("2")))
														{
															System.out.println("\nInvalid Choice\nWould you like to change the information of this book:");
															System.out.println("(1) Yes");
															System.out.println("(2) No");
															System.out.print("Your Choice: ");
															choice = input.nextLine();
														} // end while

														switch(choice)
														{
															// case 1
															case "1":
																// Asking the user what they would like to change about the book
																System.out.println("\nWhat would you like to change about the book:");
																System.out.println("(1) Title");
																System.out.println("(2) Call Number");
																System.out.println("(3) Barcode");
																System.out.println("(4) Return");
																System.out.print("Your Choice: ");
																choice = input.nextLine();

																// Forcing them to enter an appropriate response
																while(!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")))
																{
																	System.out.println("\nInvalid Choice\nWhat would you like to change about the book:");
																	System.out.println("(1) Title");
																	System.out.println("(2) Call Number");
																	System.out.println("(3) Barcode");
																	System.out.println("(4) Return");
																	System.out.print("Your Choice: ");
																	choice = input.nextLine();
																} // end while

																switch(choice)
																{
																	// Changing title information
																	case "1":
																		// User can return to main menu if they enter -1
																		// Asking the user to enter the new title of the book
																		System.out.print("\nEnter -1 to return\nEnter the new Title of the Book: ");
																		choice = input.nextLine();

																		// User enter's the new title of the book
																		if(!(choice.equals("-1")))
																		{
																			System.out.println("\nPrevious Title: " + tempBook.getTitle());
																			tempBook.setTitle(choice);
																			System.out.println("New Title: " + tempBook.getTitle());
																		} // end if

																		System.out.println("\nReturning...");

																		break;
																		// Changing call number
																	case "2":
																		// If the book is fiction the call number cannot be changed
																		if(tempBook.isFiction())
																		{
																			System.out.println("\nUnforunately this book's call number cannot be changed");
																		}
																		// User can change the book's call number (Only the Dewey Decimal)
																		else
																		{
																			// User can return to main menu by entering -1
																			// Asking the user to enter a dewey decimal
																			System.out.println("\nEnter -1 to return to main menu\nEnter the new Dewey Decimal of the number: ");
																			choice = input.nextLine();

																			// Forcing the user to enter an appropriate dewey decimal or return to main menu if they enter -1
																			while(!(choice.equals("-1")) && (!isNum(choice) || !(Double.parseDouble(choice) > 0 && Double.parseDouble(choice) < 1000)))
																			{
																				System.out.println("\nInvalid Input\nEnter -1 to return\nEnter the new Dewey Decimal of the number: ");
																				choice = input.nextLine();
																			} // end while

																			if(!(choice.equals("-1")))
																			{
																				// If the first three letters of the call number are BIO, it changes the dewey decimal in the middle of the call number
																				if(tempBook.getCallNum().substring(0, 3).equals("BIO"))
																				{
																					System.out.println("Previous Call Number: " + tempBook.getCallNum());
																					tempBook.setCallNum("BIO " + choice + tempBook.getCallNum().substring(tempBook.getCallNum().indexOf(' ', 4)));
																					System.out.println("New Call Number: " + tempBook.getCallNum());
																				}
																				// Changes the dewey decimal at the beginning of the call number
																				else
																				{
																					System.out.println("Previous Call Number: " + tempBook.getCallNum());
																					tempBook.setCallNum(choice + tempBook.getCallNum().substring(tempBook.getCallNum().indexOf(' ')));
																					System.out.println("New Call Number: " + tempBook.getCallNum());
																				} // end if

																			} // end if

																			System.out.println("\nReturning...");

																		} // end if

																		break;
																	// Changing barcode information
																	case "3":
																		// User can enter -1 to return to main menu
																		// Asking user to enter the new barcode of the book
																		System.out.print("\nEnter -1 to return to main menu\nEnter the new Barcode of the Book: ");
																		choice = input.nextLine();

																		// Forcing the user to enter an appropriate barcode or return to main menu
																		while(!(choice.equals("-1")) && !(Book.isValidBarcode(choice)))
																		{
																			System.out.print("\nInvalid Input\nEnter the new barcode of the Book: ");
																			choice = input.nextLine();
																		} // end while

																		// Changing the barcode
																		if(!(choice.equals("-1")))
																		{
																			System.out.println("\nPrevious Barcode: " + tempBook.getBarcode());
																			tempBook.setBarcode(Long.parseLong(choice));
																			System.out.println("New Barcode: " + tempBook.getBarcode());
																		} // end if

																		System.out.println("\nReturning...");

																		break;
																	// Returning to main menu
																	case "4":
																		System.out.println("\nReturning...");

																		break;
																} // end switch-case

																break;
															// returning to main menu
															case "2":
																System.out.println("\nReturning...");

																break;
														} // end switch-case

														break;
													// Returning to main menu
													case "2":
														System.out.println("\nReturning...");
														break;

												} // end switch-case

											} // end if

											break;
									} // end switch-case

									break;
								// Specific Search
								case "2":
									// Asking the user what they would like to do a specific search by or if they want to return to main menu
									System.out.println("\nYou chose to do a Specific Search!\nWhat would you like to search by: ");
									System.out.println("(1) Title");
									System.out.println("(2) Author");
									System.out.println("(3) Return");
									System.out.print("Your Choice: ");
									//#input
									choice = input.nextLine();

									// Forcing the user to enter an appropriate choice
									while(!(choice.equals("1") || choice.equals("2") || choice.equals("3")))
									{
										System.out.println("\nInvalid Choice\nWhat would you like to search by: ");
										System.out.println("(1) Title");
										System.out.println("(2) Author");
										System.out.println("(3) Return");
										System.out.print("Your Choice: ");
										choice = input.nextLine();
									} // end while

									switch(choice)
									{
										// Searching by title
										case "1":
											System.out.print("You chose to do a Specific Search by Title!\nWhat is the Title of the book: ");
											choice = input.nextLine();

											// Finding the book
											bookPos = Library.specificSeqSearch(Library.getLibraryOrderTitle(), "title", choice);

											// Book wasn't found
											if(bookPos == -1)
											{
												System.out.println("\nNo book with the specific keyword: " + choice + " in their title is in the library");
											}
											// Book was found
											else
											{
												// Printing out the book
												tempBook = Library.getLibraryOrderTitle().get(bookPos);
												System.out.println("\nBook: " + tempBook);

												// The book hasn't been checked out and doesn't have a hold on it
												if(tempBook.getStatus().equals("In") && tempBook.getPatronStatus().equals("0") && tempBook.getHoldStatus().equals("No Hold"))
												{
													// Asking the user if they would like to check the book out
													System.out.println("\nWould you like to check this book out?");
													System.out.println("(1) Yes");
													System.out.println("(2) No");
													System.out.print("Your Choice: ");
													choice = input.nextLine();

													// Forcing the user to enter an appropriate response
													while(!(choice.equals("1") || choice.equals("2")))
													{
														System.out.println("\nInvalid Choice\nWould you like to check this book out?");
														System.out.println("(1) Yes");
														System.out.println("(2) No");
														System.out.print("Your Choice: ");
														choice = input.nextLine();
													} // end while

													switch(choice)
													{
														// case 1: Checking the book out
														case "1":
															/***********************************
															copy of code from borrow menu option
															***********************************/
															System.out.println("\nEnter -1 to return to main menu\nEnter the Student ID of the user borrowing the book");
															choice = input.nextLine();

															// Returning to main menu
															if(choice.equals("-1"))
															{
																System.out.println("\nReturning...");
															}
															else
															{
																while(!(choice.equals("-1")) && Patron.findPatron(choice) == -1)
																{
																	System.out.println("\nInvalid Input\nEnter -1 to return\nEnter the Student ID of the user borrowing the book");
																	choice = input.nextLine();
																} // end while

																if(choice.equals("-1"))
																{
																	System.out.println("\nReturning...");
																}
																else
																{
																	Library.borrowBook(choice, tempBook.getBarcode());
																} // end if

															} // end if

															break;
														// case 2: Doesn't want to check the book out
														case "2":
															System.out.println("\nReturning...");

															break;

													} // end switch-case

												}
												// Book has been checked out but has no hold on it
												else if(tempBook.getHoldStatus().equals("No Hold"))
												{
													// Asking the user if they would like to put a hold on the book
													System.out.println("\nIt seems someone else has checked out the book\nWould you like to put the book on hold: ");
													System.out.println("(1) Yes");
													System.out.println("(2) No");
													System.out.print("Your Choice: ");
													choice = input.nextLine();

													// Forcing the user to enter an appropriate response
													while(!(choice.equals("1") || choice.equals("2")))
													{
														System.out.println("\nInvalid Choice\nWould you like to put a hold on this book out?");
														System.out.println("(1) Yes");
														System.out.println("(2) No");
														System.out.print("Your Choice: ");
														choice = input.nextLine();
													} // end while

													switch(choice)
													{
														/***********************************
														copy of code from hold menu option
														***********************************/
														case "1":
															System.out.println("\nEnter -1 to return to main menu\nEnter the Student ID of the user holding the book");
															choice = input.nextLine();

															if(choice.equals("-1"))
															{
																System.out.println("\nReturning...");
															}
															else
															{
																while(!(choice.equals("-1")) && Patron.findPatron(choice) == -1)
																{
																	System.out.println("\nInvalid Input\nEnter -1 to return\nEnter the Student ID of the user putting a hold on the book");
																	choice = input.nextLine();
																} // end while

																if(choice.equals("-1"))
																{
																	System.out.println("\nReturning...");
																}
																else
																{
																	Library.holdBook(choice, tempBook.getBarcode());
																} // end if

															} // end if

															break;
														case "2":
															System.out.println("\nReturning...");

															break;

													} // end switch-case
												}
												// Book cannot be checked out nor can it be put on hold
												else
												{
													System.out.println("\nUnforunately this book cannot be checked out nor can it be put on hold\nReturning...");
												} // end if

												// Asking the user if they would like change the book's information
												System.out.println("\nWould you like to change the information of this book:");
												System.out.println("(1) Yes");
												System.out.println("(2) No");
												System.out.print("Your Choice: ");
												choice = input.nextLine();

												// Forcing the user to enter an appropriate response
												while(!(choice.equals("1") || choice.equals("2")))
												{
													System.out.println("\nInvalid Choice\nWould you like to change the information of this book:");
													System.out.println("(1) Yes");
													System.out.println("(2) No");
													System.out.print("Your Choice: ");
													choice = input.nextLine();
												} // end while

												switch(choice)
												{
													// case 1:
													case "1":
														// Asking the user what they would like to change about the book
														System.out.println("\nWhat would you like to change about the book:");
														System.out.println("(1) Title");
														System.out.println("(2) Call Number");
														System.out.println("(3) Barcode");
														System.out.println("(4) Return");
														System.out.print("Your Choice: ");
														choice = input.nextLine();

														// Forcing the user to enter an appropriate response
														while(!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")))
														{
															System.out.println("\nInvalid Choice\nWhat would you like to change about the book:");
															System.out.println("(1) Title");
															System.out.println("(2) Call Number");
															System.out.println("(3) Barcode");
															System.out.println("(4) Return");
															System.out.print("Your Choice: ");
															choice = input.nextLine();
														} // end while

														switch(choice)
														{
															// Changing the title
															case "1":
																// User can return to main menu by entering -1
																// Asking the user for the new title of the book
																System.out.print("\nEnter -1 to return to main menu\nEnter the new Title of the Book: ");
																choice = input.nextLine();

																// Changing title to what the user entered
																if(!(choice.equals("-1")))
																{
																	System.out.println("\nPrevious Title: " + tempBook.getTitle());
																	tempBook.setTitle(choice);
																	System.out.println("New Title: " + tempBook.getTitle());
																} // end if

																System.out.println("\nReturning...");

																break;
															// Changing call number
															case "2":
																// Checking if the book is fiction
																if(tempBook.isFiction())
																{
																	// Book cannot be changed if it is fiction
																	System.out.println("\nUnforunately this book's call number cannot be changed");
																}
																// Book is non fiction
																else
																{
																	// User can return to main menu by entering -1
																	// Asking the user to enter an appropriate dewey decimal
																	System.out.println("\nEnter -1 to return to main menu\nEnter the new Dewey Decimal of the number: ");
																	choice = input.nextLine();

																	// Forcing the user to enter an appropriate dewey decimal
																	while(!(choice.equals("-1")) && (!isNum(choice) || !(Double.parseDouble(choice) > 0 && Double.parseDouble(choice) < 1000)))
																	{
																		System.out.println("\nInvalid Input\nEnter -1 to return\nEnter the new Dewey Decimal of the number: ");
																		choice = input.nextLine();
																	} // end while

																	// Changing the dewey decimal
																	if(!(choice.equals("-1")))
																	{
																		if(tempBook.getCallNum().substring(0, 3).equals("BIO"))
																		{
																			System.out.println("\nPrevious Call Number: " + tempBook.getCallNum());
																			tempBook.setCallNum("BIO " + choice + tempBook.getCallNum().substring(tempBook.getCallNum().indexOf(' ', 4)));
																			System.out.println("New Call Number: " + tempBook.getCallNum());
																		}
																		else
																		{
																			System.out.println("\nPrevious Call Number: " + tempBook.getCallNum());
																			tempBook.setCallNum(choice + tempBook.getCallNum().substring(tempBook.getCallNum().indexOf(' ')));
																			System.out.println("New Call Number: " + tempBook.getCallNum());
																		} // end if

																	} // end if

																	System.out.println("\nReturning...");

																} // end if

																break;
															// Changing the barcodde
															case "3":
																// User can return to main menu by entering -1
																// Asking the user to enter an appropriate barcode
																System.out.print("\nEnter -1 to return\nEnter the new Barcode of the Book: ");
																choice = input.nextLine();

																// Forcing the user to enter an appropriate barcode
																while(!(choice.equals("-1")) && !(Book.isValidBarcode(choice)))
																{
																	System.out.print("\nInvalid Input\nEnter the new barcode of the Book: ");
																	choice = input.nextLine();
																} // end while

																// Changing the barcode
																if(!(choice.equals("-1")))
																{
																	System.out.println("\nPrevious Barcode: " + tempBook.getBarcode());
																	tempBook.setBarcode(Long.parseLong(choice));
																	System.out.println("New Barcode: " + tempBook.getBarcode());
																} // end if

																System.out.println("\nReturning...");

																break;
															// Returning to main menu
															case "4":
																System.out.println("\nReturning...");

																break;
														} // end switch-case

														break;
													// Returning to main menu
													case "2":
														System.out.println("\nReturning...");

														break;
												} // end switch-case

											} // end if

											break;
										// Specific search by author
										case "2":
											// Asking the user what the author's first and last names are
											System.out.print("\nYou chose to do a Specific Search by Author!\nWhat is the Author's first name: ");
											tempFirstName = input.nextLine();
											System.out.print("What is the Author's last name: ");
											tempLastName = input.nextLine();

											// Searching for the book
											bookPos = Library.specificSeqSearch(Library.getLibraryOrderAuthor(), "author", tempLastName + ", " + tempFirstName);

											// Book does not exist in library
											if(bookPos == -1)
											{
												System.out.println("\nNo book with the specific keyword: " + tempLastName + ", " + tempFirstName + " in the author's name is in the library");
											}
											// Book exists in library
											else
											{
												// Printing out the book
												tempBook = Library.getLibraryOrderAuthor().get(bookPos);
												System.out.println("\nBook: " + tempBook);

												// Book hasn't been checked out and can be put on hold
												if(tempBook.getStatus().equals("In") && tempBook.getPatronStatus().equals("0") && tempBook.getHoldStatus().equals("No Hold"))
												{
													// Asking the user if they would like to check the book out
													System.out.println("\nWould you like to check this book out?");
													System.out.println("(1) Yes");
													System.out.println("(2) No");
													System.out.print("Your Choice: ");
													choice = input.nextLine();

													// Forcing the user to enter an appropriate response
													while(!(choice.equals("1") || choice.equals("2")))
													{
														System.out.println("\nInvalid Choice\nWould you like to check this book out?");
														System.out.println("(1) Yes");
														System.out.println("(2) No");
														System.out.print("Your Choice: ");
														choice = input.nextLine();
													} // end while

													switch(choice)
													{
														//case 1
														case "1":
															// User can return to main menu by entering -1
															// Asking the user to enter an existing student ID
															System.out.println("\nEnter -1 to return to main menu\nEnter the Student ID of the user borrowing the book");
															choice = input.nextLine();

															// Returning to main menu
															if(choice.equals("-1"))
															{
																System.out.println("\nReturning...");
															}
															else
															{
																// Forcing the user to enter an existing student ID or -1 to return to main menu
																while(!(choice.equals("-1")) && Patron.findPatron(choice) == -1)
																{
																	System.out.println("\nInvalid Input\nEnter -1 to return\nEnter the Student ID of the user borrowing the book");
																	choice = input.nextLine();
																} // end while

																// Returning to main menu
																if(choice.equals("-1"))
																{
																	System.out.println("\nReturning...");
																}
																// Checking the book out
																else
																{
																	Library.borrowBook(choice, tempBook.getBarcode());
																} // end if

															} // end if

															break;
														// case 2
														case "2":
															System.out.println("\nReturning...");
															break;

													} // end switch-case

												}
												// Book has been checked out but has no hold on it
												else if(tempBook.getHoldStatus().equals("No Hold"))
												{
													// Asking the user if they want to put a hold on the book
													System.out.println("\nIt seems someone else has checked out the book\nWould you like to put the book on hold: ");
													System.out.println("(1) Yes");
													System.out.println("(2) No");
													System.out.print("Your Choice: ");
													choice = input.nextLine();

													// Forcing the user to enter an appropriate response
													while(!(choice.equals("1") || choice.equals("2")))
													{
														System.out.println("\nInvalid Choice\nWould you like to put a hold on this book out?");
														System.out.println("(1) Yes");
														System.out.println("(2) No");
														System.out.print("Your Choice: ");
														choice = input.nextLine();
													} // end while

													switch(choice)
													{
														/***********************************
														copy of code from hold menu option
														***********************************/
														case "1":
															System.out.print("\nEnter -1 to return\nEnter the Student ID of the user holding the book: ");
															choice = input.nextLine();

															if(choice.equals("-1"))
															{
																System.out.println("\nReturning...");
															}
															else
															{
																while(!(choice.equals("-1")) && Patron.findPatron(choice) == -1)
																{
																	System.out.print("\nInvalid Input\nEnter -1 to return\nEnter the Student ID of the user putting a hold on the book: ");
																	choice = input.nextLine();
																} // end while

																if(choice.equals("-1"))
																{
																	System.out.println("\nReturning...");
																}
																else
																{
																	Library.holdBook(choice, tempBook.getBarcode());
																} // end if

															} // end if

														break;
													case "2":
														System.out.println("\nReturning...");
														break;

													} // end switch-case

												}
												// Book cannot be borrowed
												else
												{
													System.out.println("\nUnforunately this book cannot be checked out nor can it be put on hold");
												} // end if

												// Asking the user if they would like to change the book's information
												System.out.println("\nWould you like to change the information of this book:");
												System.out.println("(1) Yes");
												System.out.println("(2) No");
												System.out.print("Your Choice: ");
												choice = input.nextLine();

												// Forcin them to enter an appropriate response
												while(!(choice.equals("1") || choice.equals("2")))
												{
													System.out.println("\nInvalid Choice\nWould you like to change the information of this book:");
													System.out.println("(1) Yes");
													System.out.println("(2) No");
													System.out.print("Your Choice: ");
													choice = input.nextLine();
												} // end while

												switch(choice)
												{
													//case 1
													case "1":
														// Asking the user what they would like to change about the book
														System.out.println("\nWhat would you like to change about the book:");
														System.out.println("(1) Title");
														System.out.println("(2) Call Number");
														System.out.println("(3) Barcode");
														System.out.println("(4) Return");
														System.out.print("Your Choice: ");
														choice = input.nextLine();

														// Forcing the user to enter an appropriate response
														while(!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")))
														{
															System.out.println("\nInvalid Choice\nWhat would you like to change about the book:");
															System.out.println("(1) Title");
															System.out.println("(2) Call Number");
															System.out.println("(3) Barcode");
															System.out.println("(4) Return");
															System.out.print("Your Choice: ");
															choice = input.nextLine();
														} // end while

														switch(choice)
														{
															// Changing title
															case "1":
																// User can return to main menu by entering -1
																// Asking the user to enter the new title of the book
																System.out.print("\nEnter -1 to return to main menu\nEnter the new Title of the Book: ");
																choice = input.nextLine();

																// Changing the title of the book
																if(!(choice.equals("-1")))
																{
																	System.out.println("\nPrevious Title: " + tempBook.getTitle());
																	tempBook.setTitle(choice);
																	System.out.println("New Title: " + tempBook.getTitle());
																} // end if

																System.out.println("\nReturning...");

																break;
															// Changing call number
															case "2":
																// Book is fiction so call number cannot be changed
																if(tempBook.isFiction())
																{
																	System.out.println("\nUnforunately this book's call number cannot be changed");
																}
																// Book is non fiction
																else
																{
																	// User can enter -1 and return to main menu
																	// Asking the user to enter the new dewey decimal
																	System.out.print("\nEnter -1 to return to main menu\nEnter the new Dewey Decimal of the number: ");
																	choice = input.nextLine();

																	// Forcing the user to enter an appropriate dewey decimal or -1 to return to main menu
																	while(!(choice.equals("-1")) && (!isNum(choice) || !(Double.parseDouble(choice) > 0 && Double.parseDouble(choice) < 1000)))
																	{
																		System.out.print("\nInvalid Input\nEnter -1 to return to main menu\nEnter the new Dewey Decimal of the number: ");
																		choice = input.nextLine();
																	} // end while

																	// Changing dewey decimal
																	if(!(choice.equals("-1")))
																	{
																		if(tempBook.getCallNum().substring(0, 3).equals("BIO"))
																		{
																			System.out.println("\nPrevious Call Number: " + tempBook.getCallNum());
																			tempBook.setCallNum("BIO " + choice + tempBook.getCallNum().substring(tempBook.getCallNum().indexOf(' ', 4)));
																			System.out.println("New Call Number: " + tempBook.getCallNum());
																		}
																		else
																		{
																			System.out.println("\nPrevious Call Number: " + tempBook.getCallNum());
																			tempBook.setCallNum(choice + tempBook.getCallNum().substring(tempBook.getCallNum().indexOf(' ')));
																			System.out.println("New Call Number: " + tempBook.getCallNum());
																		} // end if

																	} // end if

																	System.out.println("\nReturning...");

																} // end if

																break;
															// Changing barcode
															case "3":
																// User can return to main menu by entering -1
																// Asking the user to enter a new barcode for the book
																System.out.print("\nEnter -1 to return to main menu\nEnter the new Barcode of the Book: ");
																choice = input.nextLine();

																// Forcing the user to enter an appropriate barcode or -1 to return to main menu
																while(!(choice.equals("-1")) && !(Book.isValidBarcode(choice)))
																{
																	System.out.print("\nInvalid Input\nEnter -1 to return to main menu\nEnter the new barcode of the Book: ");
																	choice = input.nextLine();
																} // end while

																// Changing the barcode of the book
																if(!(choice.equals("-1")))
																{
																	System.out.println("\nPrevious Barcode: " + tempBook.getBarcode());
																	tempBook.setBarcode(Long.parseLong(choice));
																	System.out.println("New Barcode: " + tempBook.getBarcode());
																} // end if

																System.out.println("\nReturning...");

																break;
															// Returning to main menu 
															case "4":
																System.out.println("\nReturning...");

																break;
														} // end switch-case

														break;
													// Returning to main menu
													case "2":
														System.out.println("\nReturning...");

														break;
												} // end switch-case

											} // end if

											break;
										// Returning to main menu
										case "3":
											System.out.println("\nReturning...");
											break;
										} // end switch-case

										break;
									case "3":
										// Returning to main menu
										System.out.println("\nReturning...");

										break;
							} // end switch-case

							break;
						// Printing books
						case "4":
							// Asking the user what they would like to print out all the book's by or return to main menu
							System.out.println("\nWhat would you like to print the books out by:");
							System.out.println("(1) Title");
							System.out.println("(2) Author");
							System.out.println("(3) Barcode");
							System.out.println("(4) Fiction");
							System.out.println("(5) ISBN");
							System.out.println("(6) Return");
							System.out.print("Your Choice: ");
							choice = input.nextLine();

							// Forcing the user to enter an appropriate response
							while(!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5") || choice.equals("6")))
							{
								System.out.println("\nInvalid Choice\nWhat would you like to print the books out by:");
								System.out.println("(1) Title");
								System.out.println("(2) Author");
								System.out.println("(3) Barcode");
								System.out.println("(4) Fiction");
								System.out.println("(5) ISBN");
								System.out.println("(6) Return");
								System.out.print("Your Choice: ");
								choice = input.nextLine();
							} // end while

							switch(choice)
							{
								// Printing books out by title
								case "1":
									// Asking the user if they want to print out the entire book object or just the title or if they want to return to main menu
									System.out.println("\n\nDo you want to print the books fully or just show the titles:");
									System.out.println("(1) Full Print");
									System.out.println("(2) Title Print");
									System.out.println("(3) Return");
									System.out.print("Your Choice: ");
									choice = input.nextLine();

									// Forcing the user to enter an appropriate response
									while(!(choice.equals("1") || choice.equals("2") || choice.equals("3")))
									{
										System.out.println("\nInvalid Choice\nDo you want to print the books fully or just show the titles:");
										System.out.println("(1) Full Print");
										System.out.println("(2) Title Print");
										System.out.println("(3) Return");
										System.out.print("Your Choice: ");
										choice = input.nextLine();
									} // end while

									switch(choice)
									{
										// Printing out the books fully
										case "1":
											for(int i = 0; i < Library.getLibraryOrderTitle().size(); i++)
											{
												System.out.println((i + 1) + ": " + Library.getLibraryOrderTitle().get(i));
											} // end for

											break;
										// Printing out only the titles of each book
										case "2":
											for(int i = 0; i < Library.getLibraryOrderTitle().size(); i++)
											{
												System.out.println((i + 1) + ": " + Library.getLibraryOrderTitle().get(i).getTitle());
											} // end for

											break;
										// Returning to main menu
										case "3":
											System.out.println("\nReturning...");

											break;

									} // end switch-case

									break;
								// Printing out the book's by author
								case "2":
									// Asking the user if they want to print out the book objects fully or just the authors or if they want to return to main menu
									System.out.println("\nDo you want to print the books fully or just show the authors:");
									System.out.println("(1) Full Print");
									System.out.println("(2) Author Print");
									System.out.println("(3) Return");
									System.out.print("Your Choice: ");
									choice = input.nextLine();

									// Forcing the user to enter an appropriate response
									while(!(choice.equals("1") || choice.equals("2") || choice.equals("3")))
									{
										System.out.println("\nInvalid Choice\nDo you want to print the books fully or just show the authors:");
										System.out.println("(1) Full Print");
										System.out.println("(2) Author Print");
										System.out.println("(3) Return");
										System.out.print("Your Choice: ");
										choice = input.nextLine();
									} // end while

									switch(choice)
									{
										// Fully printing out each book object
										case "1":
											for(int i = 0; i < Library.getLibraryOrderAuthor().size(); i++)
											{
												System.out.println((i + 1) + ": " + Library.getLibraryOrderAuthor().get(i));
											} // end for

											break;
										// Printing only the author of each book
										case "2":
											for(int i = 0; i < Library.getLibraryOrderAuthor().size(); i++)
											{
												System.out.println((i + 1) + ": " + Library.getLibraryOrderAuthor().get(i).getAuthor());
											} // end for

											break;
										// Returning to main menu
										case "3":
											System.out.println("\nReturning...");

											break;

									} // end switch-case

									break;
								// Printing out by barcode
								case "3":
									// Asking the user if they want to print out the book objects fully, show only the barcodes or return to main menu
									System.out.println("\nDo you want to print the books fully or just show the barcodes:");
									System.out.println("(1) Full Print");
									System.out.println("(2) Barcode Print");
									System.out.println("(3) Return");
									System.out.print("Your Choice: ");
									choice = input.nextLine();

									// Forcing the user to enter an appropriate response
									while(!(choice.equals("1") || choice.equals("2") || choice.equals("3")))
									{
										System.out.println("\nInvalid Choice\nDo you want to print the books fully or just show the barcodes:");
										System.out.println("(1) Full Print");
										System.out.println("(2) Barcode Print");
										System.out.println("(3) Return");
										System.out.print("Your Choice: ");
										choice = input.nextLine();
									} // end while

									switch(choice)
									{
										// Printing out the books fully
										case "1":
											for(int i = 0; i < Library.getLibraryOrderBarcode().size(); i++)
											{
												System.out.println((i + 1) + ": " + Library.getLibraryOrderBarcode().get(i));
											} // end for

											break;
										// Printing only the books barcodes
										case "2":
											for(int i = 0; i < Library.getLibraryOrderBarcode().size(); i++)
											{
												System.out.println((i + 1) + ": " + Library.getLibraryOrderBarcode().get(i).getBarcode());
											} // end for

											break;
										// Returning to main menu
										case "3":
											System.out.println("\nReturning...");

											break;

									} // end switch-case

									break;
								// Printing out the book's by fiction
								case "4":
									// Asking the user if they want to print out the fiction or non fiction book and if they want to return to main menu
									System.out.println("\nDo you want to print out Fiction or Non-Fiction Books: ");
									System.out.println("(1) Fiction");
									System.out.println("(2) Non-Fiction");
									System.out.println("(3) Return");
									System.out.print("Your Choice: ");
									choice = input.nextLine();

									// Forcing them to enter an appropriate response
									while(!(choice.equals("1") || choice.equals("2") || choice.equals("3")))
									{
										System.out.println("\nInvalid Choice\nDo you want to print out Fiction or Non-Fiction Books: ");
										System.out.println("(1) Fiction");
										System.out.println("(2) Non-Fiction");
										System.out.println("(3) Return");
										System.out.print("Your Choice: ");
										choice = input.nextLine();
									} // end while

									switch(choice)
									{
										// Printing fiction books
										case "1":
											// Asking the user if they want to print out the full book object, print only the call numbers of each book, or return to main menu
											System.out.println("\nDo you want to print the books fully or just show the call numbers:");
											System.out.println("(1) Full Print");
											System.out.println("(2) Call Number Print");
											System.out.println("(3) Return");
											System.out.print("Your Choice: ");
											choice = input.nextLine();

											// Forcing the user to enter an appropriate response
											while(!(choice.equals("1") || choice.equals("2") || choice.equals("3")))
											{
												System.out.println("\nInvalid Choice\nDo you want to print the books fully or just show the call numbers:");
												System.out.println("(1) Full Print");
												System.out.println("(2) Call Number Print");
												System.out.println("(3) Return");
												System.out.print("Your Choice: ");
												choice = input.nextLine();
											} // end while

											switch(choice)
											{
												// Printing out the books fully
												case "1":
													for(int i = 0; i < Library.getLibraryFiction().size(); i++)
													{
														System.out.println((i + 1) + ": " + Library.getLibraryFiction().get(i));
													} // end for

													break;
												// Printing out the book's call numbers
												case "2":
													for(int i = 0; i < Library.getLibraryFiction().size(); i++)
													{
														System.out.println((i + 1) + ": " + Library.getLibraryFiction().get(i).getCallNum());
													} // end for

													break;
												// Returning to main menu
												case "3":
													System.out.println("\nReturning...");

													break;

											} // end switch-case

											break;
										// Printing out the non fiction books
										case "2":  
											// Asking the user if they want to print out the books fully, print only the call numbers, or return to main menu
											System.out.println("\nDo you want to print the books fully or just show the call numbers:");
											System.out.println("(1) Full Print");
											System.out.println("(2) Call Number Print");
											System.out.println("(3) Return");
											System.out.print("Your Choice: ");
											choice = input.nextLine();
											
											// Forcing the user to enter an appropriate choice
											while(!(choice.equals("1") || choice.equals("2")))
											{
												System.out.println("\nInvalid Choice\nDo you want to print the books fully or just show the call numbers:");
												System.out.println("(1) Full Print");
												System.out.println("(2) Call Number Print");
												System.out.println("(3) Return");
												System.out.print("Your Choice: ");
												choice = input.nextLine();
											} // end while

											switch(choice)
											{
												// Printing the books fully
												case "1":
													for(int i = 0; i < Library.getLibraryNonFiction().size(); i++)
													{
														System.out.println((i + 1) + ": " + Library.getLibraryNonFiction().get(i));
													} // end for

													break;
												// Printing only the call numbers
												case "2":
													for(int i = 0; i < Library.getLibraryNonFiction().size(); i++)
													{
														System.out.println((i + 1) + ": " + Library.getLibraryNonFiction().get(i).getCallNum());
													} // end for

													break;
												// Returning to main menu
												case "3":
													System.out.println("\nReturning...");

													break;

											} // end switch-case

											break;
										// Returning to main menu
										case "3":
											System.out.println("\nReturning...");

											break;

									} // end switch-case

									break;
								// Printing the books by ISBN
								case "5":
									// Asking the user if they want to print the books fully, print only the ISBNs, or return to main menu
									System.out.println("\nDo you want to print the books fully or just show the ISBNs:");
									System.out.println("(1) Full Print");
									System.out.println("(2) ISBN Print");
									System.out.println("(3) Return");
									System.out.print("Your Choice: ");
									choice = input.nextLine();

									// Forcing the user to enter an appropriate response
									while(!(choice.equals("1") || choice.equals("2") || choice.equals("3")))
									{
										System.out.println("\nInvalid Choice\nDo you want to print the books fully or just show the ISBNs:");
										System.out.println("(1) Full Print");
										System.out.println("(2) ISBN Print");
										System.out.println("(3) Return");
										System.out.print("Your Choice: ");
										choice = input.nextLine();
									} // end while

									switch(choice)
									{
										// Printing the book's fully
										case "1":
											for(int i = 0; i < Library.getLibraryOrderISBN().size(); i++)
											{
												System.out.println((i + 1) + ": " + Library.getLibraryOrderISBN().get(i));
											} // end for

											break;
										// Printing the book's only by ISBN
										case "2":
											for(int i = 0; i < Library.getLibraryOrderISBN().size(); i++)
											{
												System.out.println((i + 1) + ": " + Library.getLibraryOrderISBN().get(i).getISBN());
											} // end for

											break;
										// Returning to main menu
										case "3":
											System.out.println("\nReturning...");

											break;

									} // end switch-case

									break;
								// Returning to main menu
								case "6":
									System.out.println("\nReturning...");

									break;

							} // end switch-case

							break;
						// Returning to main menu
						case "5":
							System.out.println("\nReturning...");

							break;
					} // end switch-case

					break;
				//case 4, patrons menu
				case "4":
					//set valid choice to false and let the user know they have selected Patrons
					System.out.println("\nYou have selected Patrons.");
					validChoice = false;

					//until the user inputs a valid input for menu, repeat the menu options
					while(!validChoice)
					{
						//prompt the user to choose an option 
						System.out.println("Please select an option using the corresponding number:");
						System.out.println("(1) Register a patron");
						System.out.println("(2) Remove a patron");
						System.out.println("(3) Search for a patron");
						System.out.println("(4) Print all patrons");
						System.out.println("(5) Return ");
						System.out.print("Your Choice: ");

						//store user input in choice
						choice = input.nextLine();

						//enter switch case
						switch (choice)
						{
						//case 1, adding a new user
						case "1":
							//ask the user for the new Patron's student number, phone number
							//and email address
							System.out.println("Adding a new user!");
							System.out.print("Enter the new user's student number: ");
							tempSNum = input.nextLine();
							System.out.print("Enter the new user's phone number: ");
							tempPNum = input.nextLine();
							System.out.print("Enter the new user's email address: ");
							tempMail = input.nextLine();

							//create a temporary patron containing inputted data
							tempPatron = new Patron(tempSNum, tempPNum, tempMail);

							//checks if no duplicated values are found and the patron is a valid user
							if (!(tempPatron.containsDuplicate()) && (tempPatron.isValidUser()))
							{
								//lets the user know the Patron was added and adds tempPatron
								//to users
								System.out.println("\nUser added!");
								Library.addPatron(tempPatron);
								validChoice = true;
							}
							//otherwise
							else
							{
								//prompt the user to try again
								System.out.println("Please attempt to register a patron again.");
								validChoice = false;
							} //end if
							break;
						//case 2, remove a patron
						case "2":
							//repeat until a valid patron id is chosen
							do
							{
								//ask the user which patron they wish to remove and store it in choice
								System.out.println("Enter the student number of the Patron you wish to remove.");
								choice = input.nextLine();

							} while (!Patron.isValidID(choice));

							//find the index of the patron in users based on their student number
							patronIndex = Patron.findPatron(choice);

							//if no patron is found, let the user know
							if (patronIndex == -1)
							{
								System.out.println("This user does not exist");
							}
							//otherwise remove the patron and remove them from all users array lists
							else
							{
								System.out.println("Patron removed!");
								Library.removePatron(Library.getUsers().get(patronIndex));
							} //end if
							//set validChoice to true
							validChoice = true;
							break;
						//case 3, search patrons
						case "3":
							//ask the user what the wish to search by
							System.out.println("Enter what you wish to search by.");
							System.out.println("Student Number: snum");
							System.out.println("Phone Number: pnum");
							System.out.println("Email Address: mail");
							System.out.print("Enter search by prompt: ");
							
							//store user input in choice
							choice = input.nextLine();
							
							//ask user for the search prompt they wish to search for
							System.out.print("Enter search prompt: ");
							
							//store user input in menuChoice
							menuChoice = input.nextLine();

							//search for the patron
							Patron.patronSearch(menuChoice, choice);

							//set valid choice to true
							validChoice = true;
							break;
						//case 4, print out all the patrons
						case "4":
							//ask the user how they wish to print out all the users by
							System.out.println("Enter what you wish to list users by.");
							System.out.println("(1) Registration number");
							System.out.println("(2) Student number");
							System.out.println("(3) Phone number");
							System.out.println("(4) Email Adress");
							System.out.print("Enter list by prompt: ");
							
							//store user input in choice 
							choice = input.nextLine();
							
							//create a space of empty line for clenliness
							System.out.println("");

							//enter switch case
							switch (choice)
							{
							//case 1, registration order
							case "1":
								//print out all users sorted by the order they
								//registered in
								for(int i = 0; i < Library.getUsers().size(); i++)
								{
									System.out.println(Library.getUsers().get(i));
								}

								break;
							//case 2, student number
							case "2":
								//print out all users sorted by the order of
								//their student numbers
								for(int i = 0; i < Library.getOrderSNum().size(); i++)
								{
									System.out.println(Library.getOrderSNum().get(i));
								}

								break;
							case "3":
								//print out all users sorted by the order of 
								//their phone numbers
								for(int i = 0; i < Library.getOrderPNum().size(); i++)
								{
									System.out.println(Library.getOrderPNum().get(i));
								}

								break;
							case "4":
								//print out all the users sorted by the order of
								//their email addresses
								for(int i = 0; i < Library.getOrderEmail().size(); i++)
								{
									System.out.println(Library.getOrderEmail().get(i));
								}

								break;
							default:
								//otherwise let the user know the input was invalud
								System.out.println("This is an invalid input");
								break;
							}
						//case 5, return to main menu
						case "5":
							validChoice = true;
							break;
						//default, invalid choice 
						default:
							validChoice = false;
							System.out.println("Invalid choice. try again.");
						}
					}
					//print out an empty line for clenliness
					System.out.println("");
					break;
				//case 5, holds
				case "5":
					//let the user know they selected holds and set valid choice
					//to false
					System.out.println("\nYou have selected Holds.");
					validChoice = false;
					
					//repeat until the user enters valid input
					while(!validChoice)
					{
						//prompts the user to select one of the menu options
						System.out.println("Please select an option using the corresponding number:");
						System.out.println("(1) Add a hold");
						System.out.println("(2) Remove a hold");
						System.out.println("(3) Return");

						//store user input in choice
						choice = input.nextLine();

						//enter switch case
						switch (choice)
						{
						//case 1, add a hold
						case "1":
							//set hold choice to false
							holdChoice = false;
							
							System.out.println("\nYou have selected Add a hold.");
							//repeat until hold choice is true
							do
							{
								//enter try catch
								try
								{
									//prompt the user to input the barcode of the book to be held
									System.out.println("Enter -1 to return to the previous menu");
									System.out.println("Please enter the barcode of the book to be held:");
									System.out.print("Your Choice: ");
									choice = input.nextLine();

									//of the user inputs -1, return to main menu
									if((choice.equals("-1")))
									{
										holdChoice = true;
										break;
									}

									//prompt the user to enter the student number of the user that is to hold this book
									System.out.println("Please enter the student number of the user that is to hold this book:");
									System.out.print("Your Choice: ");
									menuChoice = input.nextLine();

									//if the patron does not exist, let the user know
									if(Patron.findPatron(menuChoice) == -1)
									{
										System.out.println("This user does not exist");
										choice = "-1";
									}
									//otherwise, place a hold on the book
									else
									{
										Library.holdBook(menuChoice, Long.parseLong(choice));
										holdChoice = true;
									}
								}
								//catch any exceptions and throw them to the user
								catch (Exception e)
								{
									System.out.println("There was an error with your input. Try again.");
								}
							} while(!holdChoice);

							//set validChoice to true
							validChoice = true;
							break;
						//case 2, removing a hold
						case "2":
							//set hold choice to false
							holdChoice = false;
							
							System.out.println("\nYou have selected Remove hold.");
							//repeat until holdChoice is true
							do
							{
								//enter try catch
								try
								{
									//prompt the user to enter the barcode of the book
									System.out.println("Enter -1 to return to the previous menu");
									System.out.println("Please enter the barcode of the book whose hold is being removed:");
									System.out.print("Your Choice: ");
									choice = input.nextLine();

									//if the user wishes to return, let them
									if((choice.equals("-1")))
									{
										holdChoice = true;
										break;
									}
									//otherwise, remove the hold on the book and set holdChoice to true
									else
									{
										Library.removeHold(Long.parseLong(choice));
										holdChoice = true;
									}
								}
								//catch any exceptions and let the user know
								catch (Exception e)
								{
									System.out.println("There was an error with your input. Try again.");
								}

							} while(!holdChoice);
							//set valid choice to true
							validChoice = true;
							break;
						//case 3, return to main menu
						case "3":
							validChoice = true;
							break;
						//default, invalid choice
						default:
							System.out.println("This is an invalid choice. Try again");
							validChoice = false;
							break;
						}
					}
					break;
				//case 6, exit Library Management System
				case "6":
					//prompt the user to confirm their wish to exit
					System.out.println("\n--------------------------------------");
					System.out.println("Exit Confirmation");
					System.out.println("--------------------------------------");
					System.out.print("Are you sure you want to exit? (Y/N):");

					//continue to ask them until their choice is valud
					do
					{
						//store user input in choice
						choice = input.nextLine().toUpperCase();

						//if the choice is not valid tell the user to try again
						if (!isValidExit(choice))
						{
							System.out.print("Invalid input. Try again.");
						}
					} while(!isValidExit(choice));

					//if the choice is to exit, set exit to true
					if (choice.equals("Y"))
					{
						exit = true;
					}

					break;
				//case 7, toggle debug mode
				case "7":
					Library.toggleDebug();
					break;
			}

			//blank line printed out for clenliness
			System.out.println("");

		}
		while(!exit);

		//ask the user if they would like to save
		System.out.println("Would you like to save? ");
		System.out.println("(Y): Yes");
		System.out.println("(N): No");
		System.out.print("Your Choice: ");
		
		//store user input in choice
		choice = input.nextLine().toUpperCase();

		//while choice is invalid, continue to try again
		while(!(choice.equals("Y") || choice.equals("N")))
		{
		    //prompt the user to try again
			System.out.println("\nInvalid Choice\nWould you like to save? ");
			System.out.println("(Y): Yes");
			System.out.println("(N): No");
			System.out.print("Your Choice: ");
			choice = input.nextLine().toUpperCase();
		} // end while

		//if the user wishes to save, save the library and patron to their respective csv
		//files
		if(choice.equals("Y"))
		{
			Library.savePatron();
			Library.saveLibrary();
		} // end if

		//thank the user for using the Library Management System
		System.out.print("Thank you for using the Library Management System.");

		//close the scanner
		input.close();

	} // end main method

} // end Main class