import java.util.ArrayList;

/***********************************************************************
 * Author: Ibraheem Siddiqui
 * Last Edit: June 13, 2025
 ***********************************************************************/

public class Fiction extends Book 
{
    /*********************************************************************************
     * CONSTRUCTORS
     *********************************************************************************/

    /***********************************************************************
     * Default constructor:
     * Constructs an empty Fiction object
     *
     * @param none
     ***********************************************************************/
    public Fiction() 
    {
        super();
    } // end default constructor

    /***********************************************************************
     * Constructor Name: Constructor #1
     * Description: Constructs a Fiction object with a title, author, barcode, call number,
     * ISBN, status, patron status, and hold status
     *
     * @param String title - title of the Fiction object
     * @param String author - author of the Fiction object
     * @param long barcode - barcode of the Fiction object
     * @param String callNum - call number of the Fiction object
     * @param String ISBN - ISBN of the Fiction object
     * @param String status - status of the Fiction object
     * @param String patronStatus - ID of the patron holding the Fiction object
     * @param String holdStatus - Fiction object is being held or not
        ***********************************************************************/
    public Fiction(String title, String author, long barcode, String callNum, String ISBN, String status,
            String patronStatus, String holdStatus) 
    {
        super(title, author, barcode, callNum, ISBN, status, patronStatus, holdStatus);
    } // end constructor #1

    /***********************************************************************
     * Construtor Name: Constructor #2
     * Description: Constructs a Fiction object based off another Fiction object
     *
     * @param Book other - The book that is copied
     ***********************************************************************/
    public Fiction(Fiction other) 
    {
        super(other.getTitle(), other.getAuthor(), other.getBarcode(), other.getCallNum(), other.getISBN(),
                other.getStatus(), other.getPatronStatus(),
                other.getHoldStatus());
    } // end constructor #2

    /***********************************************************************
     * Construtor Name: Constructor #3
     * Description: Constructs a Fiction object based off a Book object
     *
     * @param Book other - The book that is copied
     ***********************************************************************/
    public Fiction(Book other) 
    {
        super(other);
    } // end constructor #3


    /*********************************************************************************
     * CLASS METHODS
     *********************************************************************************/

    /***********************************************************************
     * Method Name: toBook
     * Description: Converts a Fiction object to a Book object
     * 
     * @param Fiction fic - The Fiction object that is converted 
     *                      to a Book object
     * @return Book - Returns the Fiction object as a Book object
     ***********************************************************************/
    public static Book toBook(Fiction fic) 
    {
        return new Book(fic.getTitle(), fic.getAuthor(), fic.getBarcode(), fic.getCallNum(), fic.getISBN(), fic.getStatus(), fic.getPatronStatus(), fic.getHoldStatus());   
    } // end toBook

    /***********************************************************************
     * Method Name: toBook
     * Description: Converts a Fiction ArrayList to a Book ArrayList
     * 
     * @param Fiction list - The Fiction ArrayList that is converted 
     *                      to a Book ArrayList
     * @return ArrayList<Book> - Returns the Fiction ArrayList as a Book ArrayList
     ***********************************************************************/
    public static ArrayList<Book> toBook(ArrayList<Fiction> list) 
    {
        /*********************************************************************************
         * Variable Dictionary
         * ArrayList<Book> returnedArrList - The Book ArrayList that stores the Book objects
         *                                   after they are converted from Fiction objects 
         *********************************************************************************/
        ArrayList<Book> returnedArrList = new ArrayList<>();

        // Converting each Fiction Object in list to a Book object and adding it to the returnedArrList 
        for(int i = 0; i < list.size(); i++) 
        {
            returnedArrList.add(list.get(i));
        } // end for

        // Returning returnedArrList
        return returnedArrList;
    } // end toBook

    /*********************************************************************************
     * Method Name: singleSort
     * Description: Sorts a single book object into an ArrayList
     *
     * @param ArrayList<Fiction> list - The ArrayList being sorted
     * @param Fiction            fic - The Fiction object being sorted into the ArrayList
     * @param String             comparisonField - The order it is being sorted in by
     *                           (e.g. title, author, etc.)
     * @return void
     *********************************************************************************/
    public static void singleSort(ArrayList<Fiction> list, Fiction fic, String comparisonField) 
    {    
        /*********************************************************************************
         * Variable Dictionary
         * int i - The position of the Fiction object in the Fiction ArrayList
         *********************************************************************************/

        // Adding the Fiction object to the Fiction ArrayList
        list.add(fic);
        int i = list.size() - 1;

        // Moving the Fiction object down the ArrayList until it is sorted into the ArrayList properly  
        while (i > 0 && Book.compareBy(comparisonField, Fiction.toBook(fic), Fiction.toBook(list.get(i - 1))) <= 0) 
        {
            list.set(i, list.get(i - 1));
            list.set(i - 1, fic);
            i--;
        } // end while
    }// end singleSort
} // end Fiction class