package com.herprogramacion.restaurantericoparico.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.models.Book;
import com.herprogramacion.restaurantericoparico.net.BookClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 1DAW on 21/03/17.
 */

public class BookDetailActivity extends AppCompatActivity {
    private ImageView ivBookCover;
    private TextView tvTitle;
    private TextView tvPublisher;
    private TextView tvPageCount;
    private BookClient client;
    private TextView tvRdate;
    private TextView description;
    private TextView budget;
    private TextView revenue;
    private TextView status;
    private TextView runtime;
    private TextView stars2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        // Fetch views
        ivBookCover = (ImageView) findViewById(R.id.ivBookCover);
        tvRdate = (TextView) findViewById(R.id.tvRdate);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvPageCount = (TextView) findViewById(R.id.tvPageCount);
        //description = (ImageView) findViewById(R.id.description);
        budget = (TextView) findViewById(R.id.budget);
        revenue = (TextView) findViewById(R.id.revenue);
        status = (TextView) findViewById(R.id.status);
        runtime = (TextView) findViewById(R.id.runtime);
       // stars2 = (TextView) findViewById(R.id.stars2);
        // Use the book to populate the data into our views
        Book book = (Book) getIntent().getSerializableExtra(BookListActivity.BOOK_DETAIL_KEY);
        loadBook(book);
    }

    // Populate data for the book
    private void loadBook(Book book) {
        //change activity title
        this.setTitle(book.getTitle());
        // Populate data

        Picasso.with(this).load(Uri.parse(book.getImg())).error(R.drawable.ic_nocover).into(ivBookCover);
        tvRdate.setText(book.getRdate());
        tvTitle.setText(book.getTitle());
        budget.setText(book.getBudget());
        revenue.setText(book.getRevenue());
        status.setText(book.getStatus());
        runtime.setText(book.getRuntime());

        // fetch extra book data from books API
        client = new BookClient();
        client.getExtraBookDetails(book.getOpenLibraryId(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    if (response.has("publishers")) {

                        // display comma separated list of publishers
                        final JSONArray publisher = response.getJSONArray("publishers");
                        final int numPublishers = publisher.length();
                        final String[] publishers = new String[numPublishers];
                        for (int i = 0; i < numPublishers; ++i) {
                            publishers[i] = publisher.getString(i);
                        }
                        tvPublisher.setText(TextUtils.join(", ", publishers));
                    }
                    if (response.has("vote_count")) {
                        tvPageCount.setText(Integer.toString(response.getInt("vote_count")));
                    }
                    if (response.has("release_date")) {
                        tvRdate.setText(Integer.toString(response.getInt("release_date")));
                    }
                    if (response.has("budget")) {
                        budget.setText(Integer.toString(response.getInt("budget")));
                    }
                    if (response.has("description")) {
                        description.setText(Integer.toString(response.getInt("description")));
                    }
                    if (response.has("revenue")) {
                        revenue.setText(Integer.toString(response.getInt("revenue")));
                    }
                    if (response.has("status")) {
                        status.setText(Integer.toString(response.getInt("status")));
                    }
                    if (response.has("runtime")) {
                        runtime.setText(Integer.toString(response.getInt("runtime")));
                    }
                    if (response.has("stars2")) {
                        stars2.setText(Integer.toString(response.getInt("stars2")));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_share) {
            setShareIntent();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setShareIntent() {
        ImageView ivImage = (ImageView) findViewById(R.id.ivBookCover);
        final TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
        // Get access to the URI for the bitmap
        Uri bmpUri = getLocalBitmapUri(ivImage);
        // Construct a ShareIntent with link to image
        Intent shareIntent = new Intent();
        // Construct a ShareIntent with link to image
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("*/*");
        shareIntent.putExtra(Intent.EXTRA_TEXT, (String)tvTitle.getText());
        shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
        // Launch share menu
        startActivity(Intent.createChooser(shareIntent, "Share Image"));

    }

    // Returns the URI path to the Bitmap displayed in cover imageview
    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }
}
