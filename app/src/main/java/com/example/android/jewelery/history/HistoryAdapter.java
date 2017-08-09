package com.example.android.jewelery.history;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.jewelery.R;
import com.example.android.jewelery.db.HistoryReaderContract;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.jewelery.db.HistoryReaderContract.*;

/**
 * Created by milju on 7/28/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{

    private Cursor mCursor;
    private Context mContext;

    private static List<Integer> mIdList = new ArrayList<>();
    private final HistoryAdapterOnClickListener mClickHandler;

    public HistoryAdapter(Context context, Cursor cursor, HistoryAdapterOnClickListener clickHandler) {
        this.mContext = context;
        this.mCursor = cursor;
        this.mClickHandler = clickHandler;
    }


    public interface HistoryAdapterOnClickListener {
        void onClick(int position);
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate( R.layout.item_history, viewGroup, false);
        HistoryViewHolder viewHolder = new HistoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {

        if (!mCursor.moveToPosition(position)) return;

        int id = mCursor.getInt(
                mCursor.getColumnIndex(HistoryInputEntry._ID)
        );
        mIdList.add(id);

        int avaProba = mCursor.getInt(
                mCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_AVA_PROBA)
        );
        String avaColor = mCursor.getString(
                mCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_AVA_COLOR)
        );
        float addProba = mCursor.getFloat(
                mCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_ADD_PROBA)
        );
        float desiredProba = mCursor.getFloat(
                mCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_DESIRED_PROBA)
        );
        String desiredColor = mCursor.getString(
                mCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_DESIRED_COLOR)
        );
        holder.avaProbaTextView.setText(String.valueOf(avaProba));
        holder.avaColorTextView.setText(avaColor);
        holder.addProbaTextView.setText(String.valueOf(addProba));
        holder.desiredProbaTextView.setText(String.valueOf(desiredProba));
        holder.desiredColorTextView.setText(desiredColor);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder implements OnClickListener{

        TextView avaProbaTextView;
        TextView avaColorTextView;
        TextView addProbaTextView;
        TextView desiredProbaTextView;
        TextView desiredColorTextView;

        public HistoryViewHolder(View itemView) {
            super(itemView);

            avaProbaTextView = (TextView) itemView.findViewById(R.id.text_ava_proba);
            avaColorTextView = (TextView) itemView.findViewById(R.id.text_ava_color);
            addProbaTextView = (TextView) itemView.findViewById(R.id.text_add_proba);
            desiredProbaTextView = (TextView) itemView.findViewById(R.id.text_results_proba);
            desiredColorTextView = (TextView) itemView.findViewById(R.id.text_results_color);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(adapterPosition);
        }
    }

    public static List<Integer> getmIdList() {
        return mIdList;
    }
}
