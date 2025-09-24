import java.util.ArrayList;

/***********************************************************************
 * Author: Ibraheem Siddiqui
 * Last Edit: June 13, 2025
 ***********************************************************************/

public class NonFiction extends Book {
    
    /*********************************************************************************
     * CONSTRUCTORS
     *********************************************************************************/

    /***********************************************************************
     * Default constructor:
     * Constructs a NonFiction object with no features
     *
     * @param none
     ***********************************************************************/
    public NonFiction() 
    {
        super();
    } // end default constructor

    /***********************************************************************
     * Constructor Name: Constructor #1
     * Description: Constructs a NonFiction object with a title, author, barcode, call number,
     * ISBN, status, patron status, and hold status
     *
     * @param String title - title of the NonFiction object
     * @param String author - author of the NonFiction object
     * @param long barcode - barcode of the NonFiction object
     * @param String callNum - call number of the NonFiction object
     * @param String ISBN - ISBN of the NonFiction object
     * @param String status - status of the NonFiction object
     * @param String patronStatus - ID of the patron holding the NonFiction object
     * @param String holdStatus - NonFiction object is being held or not
     ***********************************************************************/
    public NonFiction(String title, String author, long barcode, String callNum, String ISBN, String status,
            String patronStatus, String holdStatus) 
    {
        super(title, author, barcode, callNum, ISBN, status, patronStatus, holdStatus);
    } // end constructor #1

    /***********************************************************************
     * Construtor Name: Constructor #2
     * Description: Constructs a NonFiction object based off another NonFiction object
     *
     * @param NonFiction other - The NonFiction object that is copied
     ***********************************************************************/
    public NonFiction(NonFiction other) 
    {
        super(other.getTitle(), other.getAuthor(), other.getBarcode(), other.getCallNum(), other.getISBN(),
                other.getStatus(), other.getPatronStatus(), other.getHoldStatus());
    } // end constructor #2

    /***********************************************************************
     * Construtor Name: Constructor #3
     * Description: Constructs a NonFiction object based off a Book object
     *
     * @param Book other - The book that is copied
     ***********************************************************************/
    public NonFiction(Book other) 
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
    public static Book toBook(NonFiction nonFic) 
    {
        return new Book(nonFic.getTitle(), nonFic.getAuthor(), nonFic.getBarcode(), nonFic.getCallNum(), nonFic.getISBN(), nonFic.getStatus(), nonFic.getPatronStatus(), nonFic.getHoldStatus());   
    } // end toBook

    /***********************************************************************
     * Method Name: toBook
     * Description: Converts a Fiction ArrayList to a Book ArrayList
     * 
     * @param Fiction list - The Fiction ArrayList that is converted 
     *                      to a Book ArrayList
     * @return ArrayList<Book> - Returns the Fiction ArrayList as a Book ArrayList
     ***********************************************************************/
    public static ArrayList<Book> toBook(ArrayList<NonFiction> list) 
    {
        ArrayList<Book> returnedArrList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            returnedArrList.add(list.get(i));
        } // end for

        return returnedArrList;
    } // end toBook

    /*********************************************************************************
     * Method Name: singleSort
     * Description: Sorts a single book object into an ArrayList
     *
     * @param ArrayList<NonFiction> list - The ArrayList being sorted
     * @param NonFiction            nonFic - The NonFiction object being sorted into the ArrayList
     * @param String                comparisonField - The order it is being sorted in by
     *                              (e.g. title, author, etc.)
     * @return void
     *********************************************************************************/
    public static void singleSort(ArrayList<NonFiction> list, NonFiction nonFic, String comparisonField) 
    {
        /*********************************************************************************
         * Variable Dictionary
         * int i - The position of the NonFiction object in the NonFiction ArrayList
         *********************************************************************************/

        // Adding the NonFiction object into the NonFiction ArrayList
        list.add(nonFic);
        int i = list.size() - 1;

        // Moving the NonFiction object through the NonFiction ArrayList until it is sorted in properly
        while (i > 0 && Book.compareBy(comparisonField, NonFiction.toBook(nonFic), NonFiction.toBook(list.get(i - 1))) <= 0) 
        {
            list.set(i, list.get(i - 1));
            list.set(i - 1, nonFic);
            i--;
        } // end while
    }// end singleSort
} // end NonFiction class