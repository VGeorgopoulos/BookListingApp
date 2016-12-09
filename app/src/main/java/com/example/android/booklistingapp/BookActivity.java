package com.example.android.booklistingapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity implements LoaderCallbacks<List<Book>> {

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

    /**
     * Adapter for the list of books
     */
    private BookAdapter mAdapter;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    /**
     * SearchView for the users input
     */
    private SearchView searchView;


    /**
     * Icon for FloatingActionButton when SearchView is open
     */
    private Bitmap icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.android.booklistingapp.R.layout.book_activity);

        //Get a reference to the LoaderManager, in order to interact with loaders.
        final LoaderManager loaderManager = getLoaderManager();

        //Find a reference to the {@link ListView} in the layout
        ListView bookListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        bookListView.setEmptyView(mEmptyStateTextView);

        //Reference of SearchVIew
        searchView = (SearchView) findViewById(R.id.search_view);

        //Create a new adapter that takes an empty list of books as input
        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        //Set tha adapter on the {@link ListView}
        //so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);


        //Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //Get details on the currently active defaults data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        //If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(BOOK_LOADER_ID, null, BookActivity.this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet);
        }

        //Set an item click listener on the ListView, which sends an intent to a web browser
        //to open a website with more information about the selected earthquake.
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current book that was clicked on
                Book currentBook = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri bookUri = Uri.parse(currentBook.getUrl());

                // Create a new intent to view the book URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                googleBookRequestUrl = QUERY_ADDRESS + query;
                loaderManager.restartLoader(BOOK_LOADER_ID, null, BookActivity.this);
                return false;

            }

            @Override
            public boolean onQueryTextChange(String query) {

                googleBookRequestUrl = "";
                return false;
            }
        });
    }


    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle bundle) {

        //Create a new loader for the given URL
        return new BookLoader(this, googleBookRequestUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {

        //Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        //Set empty state text to display "No books found."
        mEmptyStateTextView.setText(R.string.no_books);

        //Clear the adapter of the previous book data
        mAdapter.clear();

        //If there is a valid list of {@link Book}s, then add them to the adapter's
        //data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }


}
