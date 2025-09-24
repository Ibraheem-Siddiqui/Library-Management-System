//#parent

/***********************************************************************
 * Author: Ibraheem Siddiqui
 * Last Edit: June 13, 2025
 ***********************************************************************/

public class Book {

    //#instancefield
    /***********************************************************************
     * INSTANCE FIELDS
     ***********************************************************************/

    /***********************************************************************
     * Instance Fields Variable Dictionary
     * private String title - Stores the title of the book
     * private String author - Stores the author's name of the book
     * private long barcode - Stores the barcode of the book
     * private String callNum - Stores the call number of the book
     * private String ISBN - Stores the ISBN of the book
     * private String status - Stores the book's status (In, Out, Lost/Missing)
     * private String patronStatus - Stores which user has the book
     * private String holdStatus - Stores a value indicating whether a patron has a hold on it
     ***********************************************************************/
    private String title;
    private String author;
    private long barcode;
    private String callNum;
    private String ISBN;
    private String status;
    private String patronStatus;
    private String holdStatus;


    /***********************************************************************
     * CONSTRUCTORS
     ***********************************************************************/

    /***********************************************************************
     * Default constructor:
     * Constructs a book with no features
     *
     * @param none
     ***********************************************************************/
    public Book()
    {
        this.title = "";
        this.author = "";
        this.barcode = 0L;
        this.callNum = "";
        this.ISBN = "0";
        this.status = "0";
        this.patronStatus = "0";
        this.holdStatus = "0";
    } // end default constructor

    /***********************************************************************
     * Constructor Name: Constructor #1
     * Description: Constructs a book with a title, author, barcode, call number,
     * ISBN, status,
     * patron status, and hold status
     *
     * @param String title - title of the book
     * @param String author - author of the book
     * @param long barcode - barcode of the book
     * @param String callNum - call number of the book
     * @param String ISBN - ISBN of the book
     * @param String status - status of the book
     * @param String patronStatus - ID of the patron holding the book
     * @param String holdStatus - book is being held or not
        ***********************************************************************/
    //#constructor
    public Book(String title, String author, long barcode, String callNum, String ISBN, String status,
            String patronStatus, String holdStatus)
    {
        this.title = title.trim();
        this.author = author;
        this.barcode = barcode;
        this.callNum = callNum;
        this.ISBN = ISBN;
        this.status = status;
        this.patronStatus = patronStatus;
        this.holdStatus = holdStatus;

        // Checks if the call number is empty
        if (this.callNum.isEmpty())
        {
            // Call number becomes: Unkown CallNum
            this.callNum = "Unknown CallNum";
        } // end if

        // Checks if the author is valid
        if (!isValidAuthor(author))
        {
            // Trims the author name
            this.trimAuthor();
        } // end if

        // Checks if the ISBN is valid
        if (!isValidISBN(ISBN))
        {
            // Trims the ISBN
            this.trimISBN();
        } // end if
    } // end constructor #1

    /***********************************************************************
     * Construtor Name: Constructor #2
     * Description: Constructs a book based off another book
     *
     * @param Book other - The book that is copied
     ***********************************************************************/
    public Book(Book other)
    {
        this.title = other.title;
        this.author = other.author;
        this.barcode = other.barcode;
        this.callNum = other.callNum;
        this.ISBN = other.ISBN;
        this.status = other.status;
        this.patronStatus = other.patronStatus;
        this.holdStatus = other.holdStatus;
    } // end constructor #2


    /***********************************************************************
     * GETTERS
     ***********************************************************************/

     /***********************************************************************
     * Method Name: getTitle
     * Description: Returns the title of the Book
     *
     * @param none
     * @return String - The title of the book
     ***********************************************************************/
    //#get
    public String getTitle()
    {
        return this.title;
    } // end getTitle

    /***********************************************************************
     * Method Name: getAuthor
     * Description: Returns the author of a book
     *
     * @param none
     * @return String - The author's name
     ***********************************************************************/
    public String getAuthor()
    {
        return this.author;
    } // end getAuthor

    /***********************************************************************
     * Method Name: getBarcode
     * Description: Returns the barcode of a book
     *
     * @param none
     * @return long - The barcode
     ***********************************************************************/
    public long getBarcode()
    {
        return this.barcode;
    } // end getBarcode

    /***********************************************************************
     * Method Name: getCallNum
     * Description: Returns the call number of a book
     *
     * @param none
     * @return String - The call number
     ***********************************************************************/
    public String getCallNum()
    {
        return this.callNum;
    } // end getCallNum

    /***********************************************************************
     * Method Name: getDeweyDecimal
     * Description: Returns the dewey decimal of a non-fiction book
     * found in the call number. If it is a fiction book,
     * it will return -1
     *
     * @param none
     * @return double - The dewey decimal
     ***********************************************************************/
    public double getDeweyDecimal() {

        /*******************************************************************************
         * Variable Dictionary
         * int pointIndex - The index of the first decimal point in the call number
         * int spaceIndex - The index of the first space in the call number
         * int count - Used to run through the call number
         * int spcaeCount - The number of spaces in the call number
         * String deweyDec - Stores the digits of the dewey decimal. Returned at the end of the method
         *******************************************************************************/

        int pointIndex = this.callNum.indexOf('.');
        int spaceIndex = this.callNum.indexOf(' ');
        int count = 0;
        int pointCount = 0;
        int spaceCount = 0;
        String deweyDec = "";

        // Checks if the book is fiction, if so, returns -1
        if (this.isFiction()) 
        {
            return -1;
        } 
        // Checks if the first character in the call number is a number and there isn't a decimal point
        else if (isNum(this.callNum.charAt(0)) && (pointIndex == -1)) 
        {
            // Runs through the call number until it reaches the first space
            while ((count != this.callNum.length()) && (this.callNum.charAt(count) != ' ')) 
            {
                // Appends each character to the dewey decimal string
                deweyDec = deweyDec + this.callNum.charAt(count);
                count++;
            } // end while
        } 
        // If the first character in the call number is a number but there is a decimal point
        else if (isNum(this.callNum.charAt(0))) 
        {
            // Runs through the call number as long as the amount of decimal points and spaces is less than or equal to 1 or the current character being run through is a number
            while ((count != this.callNum.length())
                    && ((count == pointIndex && pointCount <= 1) || (count == spaceIndex && spaceCount <= 1)
                            || isNum(this.callNum.charAt(count)))) 
            {
                // If the current index of the call number is a decimal point, the decimal will be added to the dewey decimal string and the pointCount will increase 
                if (count == pointIndex) 
                {
                    deweyDec = deweyDec + '.';
                    pointCount++;
                } 
                // If the current index of the call number is a space, the spaceCount will increase
                else if (count == spaceIndex) 
                {
                    spaceCount++;
                } 
                // The current character is a number and is added to the dewey decimal string 
                else 
                {
                    deweyDec = deweyDec + this.callNum.charAt(count);
                } // end if

                count++;
            } // end while
        } 
        // Checks if the first character in the call number is not a number and there is a number after the first space but there is no decimal point
        else if (!isNum(this.callNum.charAt(0)) && isNum(this.callNum.charAt(spaceIndex + 1)) && (pointIndex == -1)) 
        {
            // Starts looking for numbers after the first space
            count = spaceIndex + 1;

            // Runs through the call number up until the next non-numerical character is found
            while (isNum(this.callNum.charAt(count))) 
            {
                // Appends the current character to the dewey decimal string
                deweyDec = deweyDec + this.callNum.charAt(count);
                count++;
            } // end while
        } 
        // Checks if the first character in the call number is not a number and there is a number after the first space with a decimal point
        else if (!isNum(this.callNum.charAt(0)) && isNum(this.callNum.charAt(spaceIndex + 1))) 
        {
            // Starts looking for numbers after the space
            count = spaceIndex + 1;
            // In the case there is a space between the whole number and decimal, it finds the index of the second space in the call number
            spaceIndex = this.callNum.indexOf(' ', spaceIndex + 1);

            // Runs through the call number so long as the number of decimal points and spaces is less than or equal to one and the current character is a number
            while (count != this.callNum.length()
                    && ((count == pointIndex && pointCount <= 1) || (count == spaceIndex && spaceCount <= 1)
                            || isNum(this.callNum.charAt(count)))) 
            {
                // If the current character is a decimal point, it appends it to the dewey decimal string and increases the pointCount
                if (count == pointIndex) 
                {
                    deweyDec = deweyDec + '.';
                    pointCount++;
                } 
                // If the current character is a space, it increases the spaceCount
                else if (count == spaceIndex) 
                {
                    spaceCount++;
                } 
                // If the current character is a number, it appends it to the dewey decimal string
                else 
                {
                    deweyDec = deweyDec + this.callNum.charAt(count);
                } // end if

                count++;
            } // end while
        } // end if

        // Returns the double version of the dewey decimal string
        return Double.parseDouble(deweyDec);
    } // end getDeweyDecimal

    /***********************************************************************
     * Method Name: getISBN
     * Description: Returns the ISBN of a book
     *
     * @param none
     * @return String - The ISBN
     ***********************************************************************/
    public String getISBN()
    {
        return this.ISBN;
    } // end getISBN

    /***********************************************************************
     * Method Name: getStatus
     * Description: Returns the status of a book
     *
     * @param none
     * @return String - The status
     ***********************************************************************/
    public String getStatus()
    {
        return this.status;
    } // end getStatus

    /***********************************************************************
     * Method Name: getPatronStatus
     * Description: Returns the patron status of a book
     *
     * @param none
     * @return String - The patron status
     ***********************************************************************/
    public String getPatronStatus()
    {
        return this.patronStatus;
    } // end getPatronStatus

    /***********************************************************************
     * Method Name: getHoldStatus
     * Description: Returns the hold status of a book
     *
     * @param none
     * @return String - The hold status
     ***********************************************************************/
    public String getHoldStatus()
    {
        return this.holdStatus;
    } // end getHoldStatus


    /***********************************************************************
     * SETTERS
     ***********************************************************************/
    
     /***********************************************************************
     * Method Name: setTitle
     * Description: Sets the title of the Book
     *
     * @param String title - The new title of the book
     * @return void
     ***********************************************************************/
     //#set
    public void setTitle(String title)
    {
        this.title = title;
    } // end setTitle

    /***********************************************************************
     * Method Name: setAuthor
     * Description: Changes the author of the Book
     *
     * @param String author - The new author of the book
     * @return void
     ***********************************************************************/
    public void setAuthor(String author)
    {
        this.author = author;
    } // end setAuthor

    /***********************************************************************
     * Method Name: setBarcode
     * Description: Changes the barcode of the Book
     *
     * @param long barcode - The new barcode of the book
     * @return void
     ***********************************************************************/
    public void setBarcode(long barcode)
    {
        this.barcode = barcode;
    } // end setBarcode

    /***********************************************************************
     * Method Name: setCallNum
     * Description: Changes the call number of the Book
     *
     * @param String callNum - The new call number of the book
     * @return void
     ***********************************************************************/
    public void setCallNum(String callNum)
    {
        this.callNum = callNum;
    } // end setCallNum

    /***********************************************************************
     * Method Name: setISBN
     * Description: Changes the ISBN of the Book
     *
     * @param String ISBN - The new ISBN of the book
     * @return void
     ***********************************************************************/
    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    } // end setISBN

    /***********************************************************************
     * Method Name: setStatus
     * Description: Changes the status of the Book
     *
     * @param String status - The new status of the book
     * @return void
     ***********************************************************************/
    public void setStatus(String status)
    {
        this.status = status;
    } // end setStatus

    /***********************************************************************
     * Method Name: setPatronStatus
     * Description: Changes the patron status of the book
     *
     * @param String patronStatus - The new patron status of the book
     * @return void
     ***********************************************************************/
    public void setPatronStatus(String patronStatus)
    {
        this.patronStatus = patronStatus;
    } // end setPatronStatus

    /***********************************************************************
     * Method Name: setHoldStatus
     * Description: Changes the hold status of the Book
     *
     * @param String holdStatus - The new hold status of the book
     * @return void
     ***********************************************************************/
    public void setHoldStatus(String holdStatus)
    {
        this.holdStatus = holdStatus;
    } // end setHoldStatus


    /***********************************************************************
     * INSTANCE METHODS
     ***********************************************************************/

     /***********************************************************************
     * Method Name: isFiction
     * Description: Checks the call number of a book to check
     * if it is fiction or non-fiction
     *
     * @param none
     * @return boolean - Returns true if a book is fiction
     ***********************************************************************/
    public boolean isFiction() 
    {
        // Checks if the call number contains an F followed by a space or contains an F that is between two spaces - Returns true
        if (((this.callNum.charAt(0) == 'F') && (this.callNum.charAt(1) == ' ')) || this.callNum.contains(" F ")) 
        {
            return true;
        } // end if

        // Checks if there is a Dewey Decimal in the call number
        for (int i = 0; i < this.callNum.length(); i++) 
        {
            if ((int) this.callNum.charAt(i) >= 48 && (int) this.callNum.charAt(i) <= 57) {
                return false;
            } // end if
        } // end for

        return true;
    } // end isFiction

    /***********************************************************************
     * Method Name: trimAuthor
     * Description: Corrects the name of the author of a book
     *
     * @param none
     * @return void
     ***********************************************************************/
    public void trimAuthor() {

        /*******************************************************************************
         * Variable Dictionary
         * int commaCount - The number of commas in the authors name
         * int openBracketIndex - The index of the open bracket in the author's name
         * int closeBracketIndex - The index of the closed bracket in the author's name
         * int spaceIndex - The index of the first space in the author's name
         * String firstHalf - The first half of the author's name (Last Name)
         * String secondHalf - The second half of the author's name (First Name)
         *******************************************************************************/

        int commaCount = 0;
        int commaIndex = this.author.indexOf(',');
        int openBracketIndex = this.author.indexOf('(');
        int closeBracketIndex = this.author.indexOf(')');
        int spaceIndex = this.author.indexOf(' ');
        String firstHalf = "";
        String secondHalf = "";

        // Checks if the author's name is empty
        if(this.author.isEmpty()) 
        {
            // Sets the author name to Author, Unkown and ends the method 
            this.author = "Author, Unknown";
            return;
        } // end if

        // Checks if the authors name ends in a period
        if(this.author.charAt(this.author.length() - 1) == '.') 
        {
            // Removes the period from the end of the author's name
            this.author = this.author.substring(0, this.author.length() - 1);
        } // end if

        // Checks if there is an open bracket, close bracket, and comma in the author's name
        if(openBracketIndex != -1 && closeBracketIndex != -1 && commaIndex != -1) 
        {
            // Sets the first half of the author's name to the string of characters from the beginning of the author's name to the comma index
            firstHalf = this.author.substring(0, commaIndex);
            // Sets the second half of the author's name to the string of characters between the open and closed brackets
            secondHalf = this.author.substring(openBracketIndex + 1, closeBracketIndex);
            // Corrects the author's name by putting their last name first and their first name last and seperating both by a comma
            this.author = firstHalf + ", " + secondHalf;
            // Ends the function
            return;

        }
        // Checks if there is an open bracket and close bracket but no comma 
        else if(openBracketIndex != -1 && closeBracketIndex != -1) 
        {
            // Sets the first half of the author's name to the string of characters from the beginnning of the author's name to the first space index 
            firstHalf = this.author.substring(0, spaceIndex);
            // Sets the second half of the author's name to the string of characters between the open and close brackets
            secondHalf = this.author.substring(openBracketIndex + 1, closeBracketIndex);
            // Corrects the authors name by putting their last name first and their first name last and seperating both by a comma
            this.author = firstHalf + ", " + secondHalf;
            return;
        } // end if

        // Runnning through the author's name
        for(int i = 0; i < this.author.length(); i++) 
        {
            // Updating the amount of commas in the author's name
            if (this.author.charAt(i) == ',') 
            {
                commaCount++;
            } // end if

            // Checking if the author's name contains numbers or has more than one comma
            if(((int) this.author.charAt(i) >= 48 && (int) this.author.charAt(i) <= 57)
                    || (this.author.charAt(i) == ',' && commaCount > 1)) 
            {
                this.author = this.author.substring(0, i);
            } // end if
        } // end for
    } // end trimAuthor

    /***********************************************************************
     * Method Name: trimISBN
     * Description: Corrects a book's ISBN
     *
     * @param none
     * @return void
     ***********************************************************************/
    public void trimISBN()
    {
        // Running through the ISBN
        for (int i = 0; i < this.ISBN.length(); i++)
        {
            // Checking if the ISBN contains non-numerical characters
            if ((i != this.ISBN.length() - 1) && ((int) this.ISBN.charAt(i) < 48 || (int) this.ISBN.charAt(i) > 57))
            {
                // Setting the ISBN to a substring removing the non-numerical character
                this.ISBN = this.ISBN.substring(0, i) + this.ISBN.substring(i + 1);
                i--;
            }
            else if (((int) this.ISBN.charAt(i) < 48 || (int) this.ISBN.charAt(i) > 57))
            {
                // Removing the non-numerical character
                this.ISBN = this.ISBN.substring(0, i);
            } // end if
        } // end for
    } // end trimISBN


    /***********************************************************************
     * CLASS METHODS
     ***********************************************************************/

    /***********************************************************************
     * Method Name: isNum
     * Description: Checks if a character is a number
     *
     * @param char c - The character being checked
     * @return boolean - Returns true if the character is a number
     ***********************************************************************/
    private static boolean isNum(char c) 
    {
        return (c >= 48) && (c <= 57);
    } // end isNum

    /***********************************************************************
     * Method Name: isValidAuthor
     * Description: Checks if an authors name is valid
     *
     * @param author - The name of the author being checked
     * @return boolean - Returns true if the author is valid
     ***********************************************************************/
    public static boolean isValidAuthor(String author)
    {
        /***********************************************************************
         * Variable Dictionary
         * int commacount - Counts how many commas are in the sentence
         ***********************************************************************/

        int commaCount = 0;

        //#conditional
        // Checks if the author is empty - Returns false
        if (author.isEmpty())
        {
            return false;
        }
        // Checks if the author name doesn't have a comma in it - Returns false
        else if (author.indexOf(',') == -1)
        {
            return false;
        }  
        // Checks if the author name ends with a period - Returns false
        else if(author.charAt(author.length() - 1) == '.')
        {
            return false;
        }
        // Checks if the author name has brackets - Returns false
        else if (author.indexOf('(') != -1 || author.indexOf(')') != -1)
        {
            return false;
        } // end if

        // Runs through the author name
        for (int i = 0; i < author.length(); i++)
        {
            // Keeping track of how many commas there are
            if (author.charAt(i) == ',')
            {
                commaCount++;
            } // end if

            // Checks if there is a number in the author name, or the author has more than one comma - Returns false
            if (isNum(author.charAt(i)) || (author.charAt(i) == ',' && commaCount > 1))
            {
                return false;
            } // end if
        } // end for

        // Returns true if all other cases don't occur
        return true;
    } // end isValidAuthor

    /***********************************************************************
     * Method Name: isValidBarcode
     * Description: Checks if a barcode is valid
     *
     * @param String barcode - The barcode being validated
     * @return boolean - True if the barcode is valid
     ***********************************************************************/
    public static boolean isValidBarcode(String barcode)
    {
        // try-catch to see if the barcode can be parsed to a long
        try
        {
            Long.parseLong(barcode);
        }
        catch(Exception e)
        {
            // Returns false if an error occurs
            return false;
        } // end try-catch
        
        // Returns true otherwise
        return true;
    } // end isValidBarcode

    /***********************************************************************
     * Method Name: isValidCallNum
     * Description: Checks if a call number is valid by checking it's last
     *              three letters are the first three letters of the author's
     *              last name
     *
     * @param String callNum - The call number being validated
     * @param String authorLastName - The author's last name
     * @return boolean - True if the call number is valid
     ***********************************************************************/
    public static boolean isValidCallNum(String callNum, String authorLastName)
    {
        // Checks if the call number is empty - Returns false 
        if(callNum.isEmpty())
        {
            return false;
        }
        // Checks if the call number's last three letters are the same as the first three letters of the author's last name - If not: Returns false
        else if(!(callNum.substring(callNum.length() - 3).equals(authorLastName.substring(0, 3).toUpperCase())))
        {
            return false;
        } // end if

        // Returns true if all other cases don't occur
        return true;
    } // end isValidCallNum

    /***********************************************************************
     * Method Name: isValidISBN
     * Description: Validates an string meant to become a book's ISBN
     *
     * @param ISBN - The ISBN being checked
     * @return boolean - The
     ***********************************************************************/
    public static boolean isValidISBN(String ISBN)
    {    
        // try-catch to see if an ISBN can be parsed to a Long
        try
        {
            Long.parseLong(ISBN);
        }
        catch(Exception e)
        {
            // Returns false if any errors occur
            return false;
        } // end try-catch

        // Returns true otherwise
        return true;
    } // end isValidISBN

    //#math
    /***********************************************************************
     * Method Name: generateBarcode
     * Description: Generates a barcode for a book by getting the highest barcode in the ArrayList of barcodes 
     *              in the library class and adding 10
     * 
     * @param none
     * @return long - The generated barcode
     ***********************************************************************/
    public static long generateBarcode()
    {
        return Library.getLibraryOrderBarcode().get(Library.getLibraryOrderBarcode().size() - 1).getBarcode() + 10L;
    } // end generateBarcode

    /***********************************************************************
     * Method Name: compareBy
     * Description: Compares two book objects lexicographically or numerically
     * depending on what
     * field to compare by
     *
     * @param String comparisonType - The field to compare the books by
     * @param Book   one - The first book in the comparison
     * @param Book   two - The book being compared to
     * @return int - A value of -1 is returned if book one is lexicographically or
     *         numerically smaller than book two,
     *         1 is returned if book one is larger than book two, (TEMP) -2 is
     *         returned if an invalid string is entered
     ***********************************************************************/
    public static int compareBy(String comparisonField, Book one, Book two)
    {
        // Switch-case for the different fields that the two books could compared by
        switch (comparisonField.toLowerCase())
        {
            // The two books are compared to each other lexicographically by title
            case "title":
                return one.title.toLowerCase().compareTo(two.title.toLowerCase());
                
            // The two books are compared to each other lexicographically by author
            case "author":
                return one.author.toLowerCase().compareTo(two.author.toLowerCase());

            // The two books are compared by call number
            case "call number":

                // Checks if both books are non fiction
                if (!one.isFiction() && !two.isFiction())
                {
                    // Checks if book one's dewey decimal is smaller than book two's - Returns -1
                    if (one.getDeweyDecimal() <= two.getDeweyDecimal())
                    {
                        return -1;
                    }
                    // Returns 1 otherwise
                    else
                    {
                        return 1;
                    } // end if

                }
                // Checks if book one is fiction and book two is non fiction - Returns -1
                else if (one.isFiction() && !two.isFiction())
                {
                    return -1;
                }
                // Checks book one is non fiction and book two is fiction - Returns 1
                else if (!one.isFiction() && two.isFiction())
                {
                    return 1;
                }
                // Compares both book's call numbers lexicographically
                else
                {
                    return one.callNum.toLowerCase().compareTo(two.callNum.toLowerCase());
                } // end if
                
            // The two book's are compared by barcode
            case "barcode":

                // Checks if book one's barcode is smaller than book two's barcode - Returns -1
                if (one.barcode <= two.barcode)
                {
                    return -1;
                }
                // Returns 1 otherwise
                else
                {
                    return 1;
                } // end if

            // The two book's are compared by ISBN
            case "isbn":

                // Checks if book one's ISBN is smaller than book two's ISBN - Returns -1
                if (Long.parseLong(one.ISBN) <= Long.parseLong(two.ISBN))
                {
                    return -1;
                }
                // Returns 1 otherwise
                else
                {
                    return 1;
                } // end if

            default:
                return -1;
        } // end switch-case
    } // end compareBy


    /***********************************************************************
     * equals
     ***********************************************************************/

    /***********************************************************************
     * Method Name: equals
     * Description: Compares two book objects and checks if they are the exact same
     *
     * @param Book other - The book being compared to
     * @return boolean - Returns true if both book objects are the exact same, false
     *         if their is one difference
     ***********************************************************************/ 
    public boolean equals(Book other)
    {
        // Returns true if all fields are exact matches of each other
        return (this.getTitle().equals(other.getTitle()))
                && (this.getAuthor().equals(other.getAuthor()))
                && (this.getBarcode() == other.getBarcode())
                && (this.getCallNum().equals(other.getCallNum()))
                && (this.getISBN().equals(other.getISBN()))
                && (this.getStatus().equals(other.getStatus()))
                && (this.getPatronStatus().equals(other.getPatronStatus()))
                && (this.getHoldStatus().equals(other.getHoldStatus()));
    } // end equals

    /***********************************************************************
     * Method Name: equals
     * Description: Compares to book objects based on a specific field
     *
     * @param Book   other - The book being compared to
     * @param String comparisonField - The field the two books are compared on
     * @return boolean - Returns true if both book objects have the same field
     ***********************************************************************/
     //#equals
    public boolean equals(Book other, String comparisonField)
    {
        // Checks if both the instance and the other book have the exact same information in the specified field
        switch (comparisonField.toLowerCase())
        {
            // Checks if both book's titles are the same
            case "title":
                return this.title.equals(other.title);

            // Checks if both book's authors are the same
            case "author":
                return this.author.equals(other.author);

            // Checks if both book's barcodes are the same
            case "barcode":
                return this.barcode == other.barcode;

            // Checks if both book's call numbers are the same
            case "callnum":
                return this.callNum.equals(other.callNum);

            // Checks if both book's ISBNs are the same
            case "isbn":
                return this.ISBN.equals(other.ISBN);

            default:
                return false;
        } // end switch-case
    } // end equals


    /***********************************************************************
     * toString
     ***********************************************************************/

    /***********************************************************************
     * Method Name: toString
     * Description: Prints out the book object
     *
     * @param none
     * @return String - The printed version of the book object listing it's features
     ***********************************************************************/
    public String toString()
    {
        return "\"" + this.title + "\",\"" + this.author + "\"," + this.barcode + "," + this.callNum + "," + this.ISBN
                + "," + this.status + "," + this.patronStatus + "," + this.holdStatus + "\n";
    } // end toString
} // end Book class