package com.example.android.booklistingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by neuromancer on 9/12/2016.
 */

/**
 * An {@link BookAdapter} knows how to create a list item layout for each book
 * in the data source (a list of {@link Book} objects).
 * <p>
 * These list item layout will be provided to an adapter view like ListView
 * to display to the user.
 */
public class BookAdapter extends ArrayAdapter<Book> {

    /**
     * Constructs a new {@link BookAdapter}.
     *
     * @param context of the app
     * @param books   is the list of books, which is the data source of the adapter
     */
    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    /**
     * Returns a list item view that displays information about the book at the given position
     * in the list of books.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Check if there is an existing list item view (called convertView) that we can reuse,
        //otherwise, if converView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_item, parent, false);
        }

        //FInd the book at the given position in the list of books
        Book currentBook = getItem(position);

        //Find the TextView with the ID book_title
        TextView bookTitleView = (TextView) listItemView.findViewById(R.id.book_title);
        //Get the title from the currentBook object and set this text
        //on the book_title TextView
        bookTitleView.setText(currentBook.getTitle());

        //Find the TextView with the ID book_author
        TextView bookAuthorView = (TextView) listItemView.findViewById(R.id.book_author);
        //Get the author from the currentBook object and set this text
        //on the book_author TextView
        bookAuthorView.setText(currentBook.getAuthor());

        //Find the TextView with the ID book_pages
        TextView pageCountView = (TextView) listItemView.findViewById(R.id.book_pages);
        //Format pageCount to String
        String formattedPageCount = formatPageCount(currentBook.getPageCount());
        //Display the page count of the current book in the TextView
        pageCountView.setText(formattedPageCount);

        //Find the TextView with the ID book_release_date
        TextView bookReleaseDateView  = (TextView) listItemView.findViewById(R.id.book_release_date);
        //Get the releaseDate from the current Book object and set this text
        //on the book_release_date TextView
        bookReleaseDateView.setText(currentBook.getReleaseDate());

        // Return the list view that is now showing the appropriate data
        return listItemView;
    }

    /**
     * Return the formatted pageCount string.
     */
    private String formatPageCount(int pageCount){
        return String.valueOf(pageCount);
    }
}
