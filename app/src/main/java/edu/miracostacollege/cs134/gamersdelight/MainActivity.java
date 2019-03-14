// MainActivity.java

package edu.miracostacollege.cs134.gamersdelight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import java.io.IOException;
import java.util.List;

import edu.miracostacollege.cs134.gamersdelight.model.Game;
import edu.miracostacollege.cs134.gamersdelight.model.JSONLoader;

public class MainActivity extends AppCompatActivity {


    private List<Game> gamesList;
    private GameListAdapter gamesListAdapter;
    private ListView gamesListView;

    // View references
    private EditText nameEditText;
    private EditText descriptionEditText;
    private RatingBar gameRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DONE: Connect the ListView with the layout
        gamesListView = findViewById(R.id.gameListView);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        nameEditText = findViewById(R.id.nameEditText);
        gameRatingBar = findViewById(R.id.gameRatingBar);

        // DONE:  Populate all games list using the JSONLoader
        try {
            gamesList = JSONLoader.loadJSONFromAsset(this);
        }
        catch (IOException e) {
            Log.e("Gamers Delight", e.getMessage());
        }

        // DONE:  Create a new ListAdapter connected to the correct layout file and list
        gamesListAdapter = new GameListAdapter(this, R.layout.game_list_item, gamesList);
        // DONE:  Connect the ListView with the ListAdapter
        gamesListView.setAdapter(gamesListAdapter);
    }

    public void viewGameDetails(View view) {

        // DONE: Use an Intent to start the GameDetailsActivity with the data it needs to correctly inflate its views.
        // Get position for selected game
        int pos = (int) view.getTag();
        // Create object for selected game
        Game selectedGame = gamesList.get(pos);

        // Create intent
        Intent detailsIntent = new Intent(this, GameDetailsActivity.class);
        // Add Game data to intent
        detailsIntent.putExtra("Name", selectedGame.getName());
        detailsIntent.putExtra("Description", selectedGame.getDescription());
        detailsIntent.putExtra("Rating", selectedGame.getRating());
        detailsIntent.putExtra("ImageName", selectedGame.getImageName());

        // Start new activity
        startActivity(detailsIntent);
    }

    public void addGame(View view)
    {
        // DONE:  Read information from EditTexts and RatingBar,
        String name = nameEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        float rating = gameRatingBar.getRating();

        // DONE:  Create a new Game object then add it to the list
        Game newGame = new Game(name, description, rating);
        gamesList.add(newGame);
        // DONE:  Make sure the list adapter is notified
        gamesListAdapter.notifyDataSetChanged();
        // DONE:  Clear all entries the user made (edit text and rating bar)
        nameEditText.setText("");
        descriptionEditText.setText("");
        gameRatingBar.setRating(0.0f);
    }

}
