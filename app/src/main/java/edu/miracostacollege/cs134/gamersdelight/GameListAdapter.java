// GameListAdapter.java

package edu.miracostacollege.cs134.gamersdelight;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import edu.miracostacollege.cs134.gamersdelight.model.Game;

/**
 * Helper class to provide custom adapter for the <code>Game</code> list.
 */
public class GameListAdapter extends ArrayAdapter<Game> {

    private Context mContext;
    private List<Game> mGamesList;
    private int mResourceId;

    /**
     * Creates a new <code>GameListAdapter</code> given a context, resource id and list of games.
     *
     * @param c The context for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param games The list of games to display
     */
    public GameListAdapter(Context c, int rId, List<Game> games) {
        super(c, rId, games);
        mContext = c;
        mResourceId = rId;
        mGamesList = games;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the Game selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        //DONE:  Code for getting the view of a list item correctly inflated.
        //DONE:  This should inflate every part of the game_list_item layout
        //DONE:  Be sure to set the tag of the view to its position.
        // Link View objects
        ImageView gameListImageView = view.findViewById(R.id.gameListImageView);
        TextView gameListNameTextView = view.findViewById(R.id.gameListNameTextView);
        TextView gameListDescriptionTextView = view.findViewById(R.id.gameListDescriptionTextView);
        RatingBar gameListRatingBar = view.findViewById(R.id.gameListRatingBar);

        // Get Game object at current position
        Game focusedGame = mGamesList.get(pos);

        // Set text for TextViews
        gameListNameTextView.setText(focusedGame.getName());
        gameListDescriptionTextView.setText(focusedGame.getDescription());

        // Set rating
        gameListRatingBar.setRating(focusedGame.getRating());

        // Set image
        AssetManager am = mContext.getAssets();
        try {
            InputStream stream = am.open(focusedGame.getImageName());
            Drawable image = Drawable.createFromStream(stream, focusedGame.getImageName());
            gameListImageView.setImageDrawable(image);
            stream.close();
        }
        catch (IOException e) {
            Log.e("Gamers Delight", e.getMessage());
        }

        // set tag
        view.setTag(pos);

        return view;
    }
}
