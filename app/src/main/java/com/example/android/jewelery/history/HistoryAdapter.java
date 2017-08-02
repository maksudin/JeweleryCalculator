package com.example.android.jewelery.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.jewelery.R;

/**
 * Created by milju on 7/28/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{

    private Context mContext;
    // How many views the adapter will hold.
    private int mCount;

    public HistoryAdapter(Context context, int count) {
        this.mContext = context;
        this.mCount = count;
    }


    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean shouldAttachParentImmediatly = false;
        View view = inflater.inflate(
                R.layout.item_history, viewGroup, shouldAttachParentImmediatly);
        HistoryViewHolder viewHolder = new HistoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView listItemHistoryView;

        public HistoryViewHolder(View itemView) {
            super(itemView);

            listItemHistoryView = (TextView) itemView.findViewById(R.id.text_ava_proba);
        }

        void bind (int listIndex) {
            listItemHistoryView.setText(String.valueOf(listIndex));
        }

    }

}
