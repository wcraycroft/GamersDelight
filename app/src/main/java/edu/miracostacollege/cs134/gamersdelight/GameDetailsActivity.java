// GameDetailsActivity.java

package edu.miracostacollege.cs134.gamersdelight;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class GameDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        // Link View objects
        ImageView gameDetailsImageView = findViewById(R.id.gameDetailsImageView);
        TextView gameDetailsNameTextView = findViewById(R.id.gameDetailsNameTextView);
        TextView gameDetailsDescriptionTextView = findViewById(R.id.gameDetailsDescriptionTextView);
        RatingBar gameDetailsRatingBar = findViewById(R.id.gameDetailsRatingBar);

        // DONE:  Use the Intent object sent from MainActivity to populate the Views on
        // DONE:  this layout, including the TextViews, RatingBar and ImageView with the Game details.
        Intent detailsIntent = getIntent();

        String name = detailsIntent.getStringExtra("Name");
        String description = detailsIntent.getStringExtra("Description");
        float rating = detailsIntent.getFloatExtra("Rating", 0.0f);
        String imageName = detailsIntent.getStringExtra("ImageName");

        gameDetailsNameTextView.setText(name);
        gameDetailsDescriptionTextView.setText(description);
        gameDetailsRatingBar.setRating(rating);

        AssetManager am = this.getAssets();
        try {
            InputStream stream = am.open(imageName);
            Drawable image = Drawable.createFromStream(stream, imageName);
            gameDetailsImageView.setImageDrawable(image);
            stream.close();
        }
        catch (IOException e) {
            Log.e("Gamers Delight", e.getMessage());
        }

    }
}
