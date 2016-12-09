package com.example.android.booklistingapp;

/**
 * Created by neuromancer on 9/12/2016.
 */

/**
 * An {@link Book} object contains information relateed to a single book.
 */
public class Book {

    /**
     * Tilte of the book
     */
    private String mTitle;

    /**
     * Author of the book
     */
    private String mAuthor;

    /**
     * Release date of the book
     */
    private String mReleaseDate;

    /**
     * Number of pages of the book
     */
    private int mPageCount;

    /**
     * Website URL of the book
     */
    private String mUrl;

    /**
     * @param title       is the title of the book
     * @param author      is the name of the author for the book
     * @param pageCount   is the number of pages for the book
     * @param releaseDate is the release date of the book
     * @param url         is the website URL to find more details about the book
     */
    public Book(String title, String author, int pageCount, String releaseDate, String url) {
        mTitle = title;
        mAuthor = author;
        mPageCount = pageCount;
        mReleaseDate = releaseDate;
        mUrl = url;
    }

    /**
     * @return the title of the book
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * @return the name of the author for the book
     */
    public String getAuthor() {
        return mAuthor;
    }

    /**
     * @return the number of total pages of the book
     */
    public int getPageCount() {
        return mPageCount;
    }

    /**
     * @return the release date of the book
     */
    public String getReleaseDate() {
        return mReleaseDate;
    }

    /**
     * @return the website URL to find more information about the book
     */
    public String getUrl() {
        return mUrl;
    }
}
