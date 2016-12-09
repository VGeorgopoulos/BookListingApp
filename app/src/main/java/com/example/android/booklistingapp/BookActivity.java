package com.example.android.booklistingapp;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class BookActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>{

    /**
     * URL query address.
     */
    private final String QUERY_ADDRESS = "https://www.googleapis.com/books/v1/volumes?q=";

    /**
     * URL for book data from google api.
     */
    private String googleBookRequestUrl;

    /**
     * Constant value for the book loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int BOOK_LOADER_ID = 1;

    private BookAdapter mAdapter;

    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.android.booklistingapp.R.layout.book_activity);
    }





    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

    }
}
