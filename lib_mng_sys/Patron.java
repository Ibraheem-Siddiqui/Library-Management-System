public class Patron
{


	/*****************************
	* Variables
	*****************************/
	/*******************************************************************************
	* Instance Variable Dictionary
	* private String studentNum - stores the student number of the Patron
	* private String phoneNum - stores the phone number of the Patron
	* private String email - stores the email of the Patron
	* private String checkedOut - stores books a user has checked out
	*******************************************************************************/
	//#data
	private String studentNum;
	private String phoneNum;
	private String email;

	/*****************************
	* Boolean methods
	*****************************/

	/***********************************************************************
	* Method Name: isNumeric
	* Purpose: Validates if a string contains a numeric value
	* @param String str - stores the input string that is to be tested on
	* @return boolean - whether the input is a number or not
	***********************************************************************/
	//@author Imaad
	//@date June 4
	public static boolean isNumeric(String str)
	{
		//attempts to parse the string as a long
		try
		{
			//if it is successfully parsed, it returns true
			Long.parseLong(str);
			return true;
		}
		//catches the error if the string is unable to be parsed to a long
		catch(NumberFormatException e)
		{
			//if it is unable to parse the value, it returns false
			return false;
		} //end tru catch
	} //end isNumeric

	/***********************************************************************
	* Method Name: isValidID
	* Purpose: Validates if a student number is valid, meaning
	* it is 9 digits long
	* @param String sNum - stores the studentNum that is to be
	* tested on for validity
	* @return boolean - whether the input is a valid studentNum or not
	***********************************************************************/
	//@author Imaad
	//@date June 4
	public static boolean isValidID(String sNum)
	{
		//if the length of sNum is not 9 and it is not numeric,
		//notify the user that the input is invalid
		if(!(sNum.length() == 9 && isNumeric(sNum)))
		{
			System.out.println("This is an invalid student id!");
		} //end if
		//return whether the conditions for a valid student number
		//is met
		return sNum.length() == 9
		       && isNumeric(sNum);
	} //end isValidID

	/***********************************************************************
	* Method Name: isValidPhone
	* Purpose: Validates if a phone number is valid, meaning
	* it is 10 digits long
	* @param String pNum - stores the phoneNum that is to be
	* tested on for validity
	* @return boolean - whether the input is a valid phoneNum or not
	***********************************************************************/
	//@author Imaad
	//@date June 4
	public static boolean isValidPhone(String pNum)
	{
		//if the length of sNum is not 10 and it is not numeric,
		//notify the user that the input is invalid
		if(!(pNum.length() == 10 && isNumeric(pNum)))
		{
			System.out.println("This is an invalid phone number!");
		} //end if
		//return whether the conditions for a valid phone number
		//is met
		return pNum.length() == 10
		       && isNumeric(pNum);
	} //end isValidPhone

	/***********************************************************************
	* Method Name: isValidMail
	* Purpose: Validates if a email is valid, containing only valid
	* charecters
	* @param String mail - stores the email adress that is to be
	* tested on for validity
	* @return boolean - whether the input is a valid email or not
	***********************************************************************/
	//@author Imaad
	//@date June 3
	public static boolean isValidMail(String mail)
	{
		/*******************************************************************************
		* Variable Dictionary
		* String[] parts - stores the 2 sections of the string as an array
		* by splitting at an @ symbol
		*******************************************************************************/
		//returns false if the email is null or if it doesnt contain an @
		//or contains a comma
		if (mail == null || !mail.contains("@") || mail.contains(","))
		{
			//notify the user of the invalid email adress and return false
			System.out.println("This is an invalid email address!");
			return false;
		} //end if

		//splits at the @ symbol
		String[] parts = mail.split("@");

		//checks if there is two non empty parts
		if (parts.length != 2 || parts[0].equals("") || parts[1].equals(""))
		{
			System.out.println("This is an invalid email address!");
			return false;
		} //end if

		//converts the second half of the email to a lowercase string
		String domain = parts[1].toLowerCase();

		//if the domain is not one of the 3 valid email adresses, notify the user
		if(!(domain.equals("gmail.com") || domain.equals("yahoo.com") || domain.equals("hotmail.com")))
		{
			System.out.println("This is an invalid email address!");
			System.out.println(domain);
		} //end if

		//return the validity of the email
		return domain.equals("gmail.com") || domain.equals("yahoo.com") || domain.equals("hotmail.com");
	} //end isValidMail

	public boolean isValidUser()
	{
		return !(this.studentNum.equals("No Student Number")
		         && this.phoneNum.equals("No Phone Number")
		         && this.email.equals("No Email"));
	}


	/*****************************
	* constructors
	*****************************/
	/***********************************************************************
	* Method Name: Default Contstuctor
	* Purpose: Contstuctor using deafault value stating no student number,
	* phone number or email and no books checked out.
	* @param none
	* @return none
	***********************************************************************/
	//@author Imaad
	//@date May 30
	//#define
	public Patron()
	{
		this.studentNum = "No Student Number";
		this.phoneNum = "No Phone Number";
		this.email = "No Email";
	}


	/***********************************************************************
	* Method Name: 3 Parameter Constructor
	* Purpose: Contstuctor using String values
	* @param sNum - stores the student number
	* @param pNum - stores the phone number
	* @param mail - stores the email adress
	* @param borrowed - stores books that have been borrowed
	* @return none
	***********************************************************************/
	//@author Imaad
	//@date May 30
	//#constructor
	public Patron(String sNum, String pNum, String mail)
	{
		//checks if student num is valid
		if(isValidID(sNum))
		{
			//set this studentNum to sNum
			this.studentNum = sNum;
		}
		//if it is not valid, set this studentNum to "No Student Number"
		else
		{
			this.studentNum = "No Student Number";
		} //end if

		//checks if the phone number is valid
		if(isValidPhone(pNum))
		{
			//set this phoneNum to pNum
			this.phoneNum = pNum;
		}
		//otherwise, set this phoneNum to "No Phone Number"
		else
		{
			this.phoneNum = "No Phone Number";
		} //end if

		//checks if email is valid
		if(isValidMail(mail))
		{
			//sets this email to mail
			this.email = mail;
		}
		//if it is not valid, set this email to "No Email"
		else
		{
			this.email = "No Email";
		} //end if

	}

	/****************************
	* Get Methods
	***************************/

	/***********************************************************************
	* Method Name: getStudentNum
	* Purpose: retreives the value of studentNum
	* @return String - the student number of the Patron
	***********************************************************************/
	//@author Imaad
	//@date May 30
	public String getStudentNum()
	{
		return this.studentNum;
	} //end getStudentNum

	/***********************************************************************
	* Method Name: getPhoneNum
	* Purpose: retreives the value of phoneNum
	* @return String - the phone number of the Patron
	***********************************************************************/
	//@author Imaad
	//@date May 30
	public String getPhoneNum()
	{
		return this.phoneNum;
	} //end getPhoneNum

	/***********************************************************************
	* Method Name: getEmail
	* Purpose: retreives the value of email
	* @return String - the email of the Patron
	***********************************************************************/
	//@author Imaad
	//@date May 30
	public String getEmail()
	{
		return this.email;
	} //end getEmail

	/****************************
	 * Set Methods
	 ***************************/

	/***********************************************************************
	* Method Name: setStudentNum
	* Purpose: Sets the studentNum
	* @param sNum - stores the new student number
	* @return none
	***********************************************************************/
	//@author Imaad
	//@date May 30
	public void setStudentNum(String sNum)
	{
		//if sNum is a valid ID, set this studentNum to sNum
		if(isValidID(sNum))
		{
			this.studentNum = sNum;
		} //end if
	} //end setStudentNum

	/***********************************************************************
	* Method Name: setPhoneNum
	* Purpose: Sets the phoneNum
	* @param pNum - stores the new phone number
	* @return none
	***********************************************************************/
	//@author Imaad
	//@date May 30
	public void setPhoneNum(String pNum)
	{
		//if pNum is a valid number, set this phoneNum to pNum
		if(isValidPhone(pNum))
		{
			this.phoneNum = pNum;
		} //end if
	} //end setPhoneNum

	/***********************************************************************
	* Method Name: setEmail
	* Purpose: Sets the email adress
	* @param mail - stores the new email
	* @return none
	***********************************************************************/
	//@author Imaad
	//@date May 30
	public void setEmail(String mail)
	{
		if(isValidMail(mail))
		{
			//if mail is a valid number, set this email to mail
			this.email = mail;
		} //end if
	}//end setEmail

	/****************************
	 * Class Methods
	 ***************************/
	/***********************************************************************
	* Method Name: compareBy
	* Purpose: A custom compareBy method that returns 1 or -1 based on
	* the comparison field
	* @param comparedField - stores what fields are being compared
	* @param p1 - first patron being compared
	* @param p2 - second patron being compared
	* @return int - a value of either 1 or -1
	***********************************************************************/
	//@author Imaad
	//@date June 2
	//#staticmethod
	public static int compareBy(String comparedField, Patron p1, Patron p2)
	{
		
		//switch case with the comparedField
		switch(comparedField)
		{
		//checks if the comparedField is studentNum
		case "studentNum":
			//compare the Long values of studentNum. if p1 is greater, return a 1
			if(Long.parseLong(p1.studentNum) <= Long.parseLong(p2.studentNum))
			{
				return -1;
			}
			//otherwise, return a -1
			else
			{
				return 1;
			} //end if
			

		//checks if the comparedField is phoneNum
		case "phoneNum":
			//compare the Long values of phoneNum. if p1 is greater, return a 1
			if(Long.parseLong(p1.phoneNum) <= Long.parseLong(p2.phoneNum))
			{
				return -1;
			}
			//otherwise, return a -1
			else
			{
				return 1;
			} //end if

		//checks if the comparedField is email
		case "email":
			//use the compareTo method to compare p1 to p2
			return p1.email.compareTo(p2.email);
		//otherwise, always return -1
		default:
			return -1;
		} //end switch case
		

	} //end compareBy

	/***********************************************************************
	* Method Name: patronSearch
	* Purpose: given a search prompt and method of searching, print out
	* the patron that matches the search prompt
	* @param toSearch- stores what is being searched for
	* @param searchBy- stores by what field should patron be searched by
	* @return none
	***********************************************************************/
	//@author Imaad
	//@date June 6
	public static void patronSearch(String toSearch, String searchBy)
	{
		/*******************************************************************************
		* Variable Dictionary
		* boolean found - stores whether a book has been found or not
		* Patron p - temporarily stores a single patron from the users list
		*******************************************************************************/
		
	    //DEBUG
		if (Library.getDebug())
		{
			System.out.println("Entering patronSearch");
			System.out.println("toSearch:" + toSearch);
			System.out.println("searchBy:" + searchBy);
		} //end if
		
		boolean found = false;
		Patron p;

		//iterates through each Patron in users
		for (int i = 0; i < Library.getUsers().size(); i++)
		{
			//stores users[i] in p
			p = Library.getUsers().get(i);

			//switch case using the searchBy parameter
			switch (searchBy.toLowerCase())
			{
			//if searchBy is snum, and a patron with this student number is found,
			//print out all of their patron details and change found to true
			case "snum":
				if (p.getStudentNum().equals(toSearch))
				{
					System.out.println("Found Patron:");
					System.out.println("Student Number: " + p.getStudentNum());
					System.out.println("Phone Number: " + p.getPhoneNum());
					System.out.println("Email: " + p.getEmail());
					found = true;
				} //end if
				break;

            //if searchBy is pnum, and a patron with this phone number is found,
			//print out all of their patron details and change found to true
			case "pnum":
				if (p.getPhoneNum().equals(toSearch))
				{
					System.out.println("Found Patron:");
					System.out.println("Student Number: " + p.getStudentNum());
					System.out.println("Phone Number: " + p.getPhoneNum());
					System.out.println("Email: " + p.getEmail());
					found = true;
				} //end if
				break;

            //if searchBy is mail, and a patron with this email is found,
			//print out all of their patron details and change found to true
			case "mail":
				if (p.getEmail().equalsIgnoreCase(toSearch))
				{
					System.out.println("Found Patron:");
					System.out.println("Student Number: " + p.getStudentNum());
					System.out.println("Phone Number: " + p.getPhoneNum());
					System.out.println("Email: " + p.getEmail());
					found = true;
				} //end if
				break;

			//if no case matches, let the user know that the search field is invalid
			default:
				System.out.println("Invalid search field: " + searchBy);
				return;
			} //end switch case
		}
        
        //if no patron is found in the search, let the user know 
		if (!found)
		{
			System.out.println("No patron found with " + searchBy + ": " + toSearch);
		} //end if
		
	    //DEBUG
		if (Library.getDebug())
		{
			System.out.println("Exiting patronSearch");
			System.out.println("toSearch:" + toSearch);
			System.out.println("searchBy:" + searchBy);
		} //end if
	} //end patronSearch

	/***********************************************************************
	* Method Name: findPatron
	* Purpose: given a student number, find the index of a patron in 
	* users
	* @param studentNumber - stores the student number that will be matched
	* with a Patron
	* @return int - index of patron 
	***********************************************************************/
	//@author Imaad
	//@date June 9
	public static int findPatron(String studentNumber)
	{
		/*******************************************************************************
		* Variable Dictionary
		* Patron p - temporarily stores a single patron from the users list
		*******************************************************************************/
		
	    //DEBUG
		if (Library.getDebug())
		{
			System.out.println("Entering findPatron");
			System.out.println("studentNumber:" + studentNumber);
		} //end if
		
		Patron p;
		
		
		//iterates through each Patron in users
		for (int i = 0; i < Library.getUsers().size(); i++)
		{
		    //store the user in p 
			p = Library.getUsers().get(i);
			//if a user exists with that student number, return i
			if (p.getStudentNum().equals(studentNumber))
			{
	    	    //DEBUG
        		if (Library.getDebug())
        		{
        			System.out.println("Exiting findPatron");
        			System.out.println("studentNumber:" + studentNumber);
        		} //end if
        		
				return i;
			} //end if
		}
		//otherwise, return -1
		
	    //DEBUG
		if (Library.getDebug())
		{
			System.out.println("Exiting findPatron");
			System.out.println("studentNumber:" + studentNumber);
		} //end if
		
		return -1; // Not found
		
	} //end findPatron

	/****************************
	* Instance Methods
	***************************/
	/***********************************************************************
	* Method Name: containsDuplicate
	* Purpose: checks if a patron has the same studentNum, phoneNum, or
	* email as another user, and returns true if that is the case
	* @param none
	* @return boolean - whether this object is contains a duplicate
	***********************************************************************/
	//@author Imaad
	//@date June 9
	//#instancemethod
	public boolean containsDuplicate()
	{
	    /*******************************************************************************
		* Variable Dictionary
		* Patron other - temporarily stores a single patron from the users list
		*******************************************************************************/
		
		//DEBUG
		if (Library.getDebug())
		{
			System.out.println("Entering containsDuplicate");
		} //end if
		
		Patron other;

		//#loop
		//iterates through the loop for each Patron in users
		for (int i = 0; i < Library.getUsers().size(); i++)
		{
		    //store a patron at i in users in other
			other = Library.getUsers().get(i);
			//checks if this studentNum matches the other studentNum
			if (other.getStudentNum().equals(this.studentNum))
			{
			    //tell the user that a duplicate exists and return true
				System.out.println("A user with this student number already exists");
				return true;
			}
			//otherwise if checks if this phoneNum matches the other phoneNum
			else if (other.getPhoneNum().equals(this.phoneNum))
			{
				//tell the user that a duplicate exists and return true
				System.out.println("A user with this phone number already exists");
				return true;
			}
			//otherwise if checks if this email matches the other email
			else if (other.getEmail().equals(this.email))
			{
				//tell the user that a duplicate exists and return true
				System.out.println("A user with this email address already exists");
				return true;
			} //end if
		}
		
		//DEBUG
		if (Library.getDebug())
		{
			System.out.println("Exiting containsDuplicate");
		} //end if
        
        //if no duplicates are found, return false
		return false;
	} //end containsDuplicate

	//@author Imaad
	//@date May 29
	/***********************************************************************
	* Method Name: toString
	* Purpose: Outputs a properly formatted string
	* @return String - returns Patron as a String
	***********************************************************************/
	//#tostring
	public String toString()
	{
		return "Student Number: " + this.studentNum
		       + "\nPhone Number: " + this.phoneNum
		       + "\nEmail: " + this.email + "\n";
	} //end toString
} //end Patron