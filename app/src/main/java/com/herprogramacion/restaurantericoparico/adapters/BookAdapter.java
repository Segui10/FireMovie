package com.herprogramacion.restaurantericoparico.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.models.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 1DAW on 21/03/17.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    // View lookup cache
    private static class ViewHolder {
        public ImageView ivCover;
        public TextView tvTitle;
        public TextView tvStars;
        public TextView tvRdate;
        public TextView tvVotecount;
    }

    public BookAdapter(Context context, ArrayList<Book> aBooks) {
        super(context, 0, aBooks);
    }

    // Translates a particular `Book` given a position
    // into a relevant row within an AdapterView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Book book = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_book, parent, false);
            viewHolder.ivCover = (ImageView)convertView.findViewById(R.id.ivBookCover);
            viewHolder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
            viewHolder.tvStars = (TextView)convertView.findViewById(R.id.tvStars);
            viewHolder.tvRdate = (TextView)convertView.findViewById(R.id.tvRdate);
            viewHolder.tvVotecount = (TextView)convertView.findViewById(R.id.tvVotecount);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.tvTitle.setText(book.getTitle());
        viewHolder.tvStars.setText(book.getStars());
        viewHolder.tvRdate.setText(book.getRdate());
        viewHolder.tvVotecount.setText(book.getVotecount());
        Picasso.with(getContext()).load(Uri.parse(book.getImg())).error(R.drawable.ic_nocover).into(viewHolder.ivCover);
        // Return the completed view to render on screen
        return convertView;
    }
}

