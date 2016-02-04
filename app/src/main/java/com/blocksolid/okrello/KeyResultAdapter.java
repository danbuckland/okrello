package com.blocksolid.okrello;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blocksolid.okrello.model.TrelloCheckItem;

import java.util.ArrayList;

/**
 * Created by Dan Buckland on 06/12/2015.
 */
public class KeyResultAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater mInflater;
    ArrayList<TrelloCheckItem> mTrelloCheckItems;

    public KeyResultAdapter(Context context, LayoutInflater inflater) {
        mContext = context;
        mInflater = inflater;
        mTrelloCheckItems = new ArrayList<>();

    }

    @Override
    public int getCount() {
        if (mTrelloCheckItems != null) {
            return mTrelloCheckItems.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mTrelloCheckItems.get(position);
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
            convertView = mInflater.inflate(R.layout.list_key_result, null);

            // create a new "Holder" with subviews
            holder = new ViewHolder();
            holder.keyResultText = (TextView) convertView.findViewById(R.id.list_key_result);
            holder.scoreText = (TextView) convertView.findViewById(R.id.list_score);
            holder.scoreCircle = (ImageView) convertView.findViewById(R.id.list_score_circle);

            // hang onto this holder for future recycling
            convertView.setTag(holder);
        } else {

            // skip all the expensive inflation/findViewById
            // and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }

        TrelloCheckItem trelloCheckItem = mTrelloCheckItems.get(position);
        holder.keyResultText.setText(trelloCheckItem.getKeyResult());
        holder.scoreText.setText(trelloCheckItem.getScore());
        holder.scoreCircle.setColorFilter(trelloCheckItem.getScoreColor(), PorterDuff.Mode.SRC_IN);

        return convertView;
    }

    private static class ViewHolder {
        public TextView keyResultText;
        public TextView scoreText;
        public ImageView scoreCircle;
    }

    public void updateData(ArrayList<TrelloCheckItem> trelloCheckItems) {
        // update the adapter's dataset
        mTrelloCheckItems = trelloCheckItems;
        notifyDataSetChanged();
    }
}
