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

    // How many views the adapter will hold.
    private int hNumberItems;

    public HistoryAdapter(int numberOfItems) {
        hNumberItems = numberOfItems;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
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
        return hNumberItems;
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView listItemHistoryView;

        public HistoryViewHolder(View itemView) {
            super(itemView);

            listItemHistoryView = (TextView) itemView.findViewById(R.id.history_item);
        }

        void bind (int listIndex) {
            listItemHistoryView.setText(String.valueOf(listIndex));
        }

    }

}
