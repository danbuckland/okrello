package com.blocksolid.okrello;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blocksolid.okrello.model.TrelloCard;

import java.util.ArrayList;

/**
 * Created by Dan Buckland on 06/12/2015.
 */
public class ObjectiveAdapter extends RecyclerView.Adapter<ObjectiveAdapter.ObjectiveViewHolder> {

    private ArrayList<TrelloCard> objectivesList;

    public ObjectiveAdapter() {
        objectivesList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return objectivesList.size();
    }

    @Override
    public void onBindViewHolder(ObjectiveViewHolder objectiveViewHolder, int i) {
        TrelloCard objective = objectivesList.get(i);
        objectiveViewHolder.goalText.setText(objective.getObjective());
        objectiveViewHolder.scoreText.setText(objective.getScore());
        objectiveViewHolder.scoreCircle.setColorFilter(objective.getScoreColor(), PorterDuff.Mode.SRC_IN);
        objectiveViewHolder.currentItem = objectivesList.get(i);
    }

    @Override
    public ObjectiveViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.list_objective, viewGroup, false);

        return new ObjectiveViewHolder(itemView);
    }

    public static class ObjectiveViewHolder extends RecyclerView.ViewHolder {
        public TrelloCard currentItem;
        protected TextView goalText;
        protected TextView scoreText;
        protected ImageView scoreCircle;

        public ObjectiveViewHolder(View v) {
            super(v);

            goalText = (TextView) v.findViewById(R.id.list_objective);
            scoreText = (TextView) v.findViewById(R.id.list_score);
            scoreCircle = (ImageView) v.findViewById(R.id.list_score_circle);

            v.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    // Grab the ID of the selected Trello List (Quarter)
                    String cardId = currentItem.getId();
                    String objective = currentItem.getObjective();

                    // Intent to take the user to a new KeyResultsActivity
                    Intent keyResultsIntent = new Intent(v.getContext(), KeyResultsActivity.class);

                    // Pass across the list ID in the intent
                    keyResultsIntent.putExtra("cardId", cardId);
                    keyResultsIntent.putExtra("objective", objective);

                    // start the next Activity using the above intent
                    v.getContext().startActivity(keyResultsIntent);
                }
            });
        }
    }

    public void updateData(ArrayList<TrelloCard> trelloCards) {
        // update the adapter's dataset
        objectivesList = trelloCards;
        notifyDataSetChanged();
    }

}
