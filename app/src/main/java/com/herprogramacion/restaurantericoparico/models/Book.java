package com.herprogramacion.restaurantericoparico.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 1DAW on 21/03/17.
 */

public class Book implements Serializable {
    private String openLibraryId;
    private String author;
    private String title;
    private String img;
    private String stars;
    private String rdate;
    private String votecount;
    public String getOpenLibraryId() {
        return openLibraryId;
    }
    public String getStars() {
        return stars;
    }
    public String getTitle() {
        return title;
    }
    public String getRdate() {
        return rdate;
    }
    public String getVotecount() {
        return votecount;
    }
    public String getImg(){return "https://image.tmdb.org/t/p/w185_and_h278_bestv2"+img;}
    // Get medium sized book cover from covers API


    // Get large sized book cover from covers API


    // Returns a Book given the expected JSON
    public static Book fromJson(JSONObject jsonObject) {
        Book book = new Book();
        try {
            // Deserialize json into object fields
            // Check if a cover edition is available
            if (jsonObject.has("id")) {
                book.openLibraryId = jsonObject.getString("id");
            } else if(jsonObject.has("id")) {
                final JSONArray ids = jsonObject.getJSONArray("id");
                book.openLibraryId = ids.getString(0);
            }
            book.title = jsonObject.has("original_title") ? jsonObject.getString("original_title"):"";
            book.img=jsonObject.has("poster_path")? jsonObject.getString("poster_path"):"";
            book.stars=jsonObject.has("vote_average") ? jsonObject.getString("vote_average"):"";
            book.rdate=jsonObject.has("release_date") ? jsonObject.getString("release_date"):"";
            book.votecount=jsonObject.has("vote_count") ? jsonObject.getString("vote_count"):"";
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return book;
    }

    // Decodes array of book json results into business model objects
    public static ArrayList<Book> fromJson(JSONArray jsonArray) {
        ArrayList<Book> books = new ArrayList<Book>(jsonArray.length());
        // Process each result in json array, decode and convert to business
        // object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject bookJson = null;
            try {
                bookJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Book book = Book.fromJson(bookJson);
            if (book != null) {
                books.add(book);
            }
        }
        return books;
    }
}