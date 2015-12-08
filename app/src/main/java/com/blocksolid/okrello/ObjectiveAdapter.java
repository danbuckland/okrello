package com.blocksolid.okrello;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blocksolid.okrello.model.TrelloCard;

import java.util.ArrayList;

/**
 * Created by Dan Buckland on 06/12/2015.
 */
public class ObjectiveAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater mInflater;
    ArrayList<TrelloCard> mTrelloCards;

    public ObjectiveAdapter (Context context, LayoutInflater inflater) {
        mContext = context;
        mInflater = inflater;
        mTrelloCards = new ArrayList<>();

    }

    @Override
    public int getCount() {
        return mTrelloCards.size();
    }

    @Override
    public Object getItem(int position) {
        return mTrelloCards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // check if the view already exists
        // if so, no need to inflate and findViewById again!
        if (convertView == null) {

            // Inflate the custom row layout from your XML.
            convertView = mInflater.inflate(R.layout.list_objective, null);

            // create a new "Holder" with subviews
            holder = new ViewHolder();
            holder.goalText = (TextView) convertView.findViewById(R.id.list_goal);
            holder.scoreText = (TextView) convertView.findViewById(R.id.list_score);
            holder.scoreCircle = (ImageView) convertView.findViewById(R.id.list_score_circle);

            // hang onto this holder for future recycling
            convertView.setTag(holder);
        } else {

            // skip all the expensive inflation/findViewById
            // and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }

        TrelloCard trelloCard = mTrelloCards.get(position);
        holder.goalText.setText(trelloCard.getObjective());
        holder.scoreText.setText(trelloCard.getScore());
        holder.scoreCircle.setColorFilter(trelloCard.getScoreColor(), PorterDuff.Mode.SRC_IN);

        return convertView;
    }

    private static class ViewHolder {
        public TextView goalText;
        public TextView scoreText;
        public ImageView scoreCircle;
    }

    public void updateData(ArrayList<TrelloCard> trelloCards) {
        // update the adapter's dataset
        mTrelloCards = trelloCards;
        notifyDataSetChanged();
    }
}
