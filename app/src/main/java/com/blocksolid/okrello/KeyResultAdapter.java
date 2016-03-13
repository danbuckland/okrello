package com.blocksolid.okrello;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blocksolid.okrello.model.TrelloCheckItem;

import java.util.ArrayList;

/**
 * Created by Dan Buckland on 06/12/2015.
 */
public class KeyResultAdapter extends RecyclerView.Adapter<KeyResultAdapter.KeyResultViewHolder> {

    private ArrayList<TrelloCheckItem> keyResultList;

    public KeyResultAdapter() {
        keyResultList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return keyResultList.size();
    }

    @Override
    public void onBindViewHolder(KeyResultViewHolder keyResultViewHolder, int i) {
        TrelloCheckItem keyResult = keyResultList.get(i);
        keyResultViewHolder.goalText.setText(keyResult.getKeyResult());
        keyResultViewHolder.scoreText.setText(keyResult.getScore());
        keyResultViewHolder.scoreCircle.setColorFilter(keyResult.getScoreColor(), PorterDuff.Mode.SRC_IN);
        keyResultViewHolder.currentItem = keyResultList.get(i);
    }

    @Override
    public KeyResultViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.list_key_result, viewGroup, false);

        return new KeyResultViewHolder(itemView);
    }

    public static class KeyResultViewHolder extends RecyclerView.ViewHolder {
        public TrelloCheckItem currentItem;
        protected TextView goalText;
        protected TextView scoreText;
        protected ImageView scoreCircle;

        public KeyResultViewHolder(View v) {
            super(v);

            goalText = (TextView) v.findViewById(R.id.list_key_result);
            scoreText = (TextView) v.findViewById(R.id.list_score);
            scoreCircle = (ImageView) v.findViewById(R.id.list_score_circle);

            v.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    // Add onClick behaviour here
                }
            });
        }
    }

    public void updateData(ArrayList<TrelloCheckItem> trelloCheckItems) {
        // update the adapter's dataset
        keyResultList = trelloCheckItems;
        notifyDataSetChanged();
    }

}