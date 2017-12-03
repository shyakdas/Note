package com.example.tnl.notes.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tnl.notes.R;
import com.example.tnl.notes.activity.NoteActivity;
import com.example.tnl.notes.model.DataModel;

import java.util.List;

/**
 * Created by tnl on 12/3/2017;
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.NoteViewHolder> {
    public List<DataModel> mList;
    private Activity mConext;

    public DataAdapter(List<DataModel> list, Activity context) {
        this.mList = list;
        this.mConext = context;
    }

//    public void add(String title, String note, long dateTime) {
//        DataModel dataModel = new DataModel(title, note, dateTime);
//        mList.add(dataModel);
//        notifyDataSetChanged();
//    }

    public void add(List<DataModel> dataModel) {
        mList.clear();
        mList.addAll(dataModel);
        notifyDataSetChanged();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View postView = layoutInflater.inflate(R.layout.item_note, parent, false);
        NoteViewHolder noteViewHolder = new NoteViewHolder(postView);
        return noteViewHolder;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitle, mNote, mTime;
        private DataModel model;

        public NoteViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.titleView);
            mNote = itemView.findViewById(R.id.noteView);
            mTime = itemView.findViewById(R.id.timeView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            ((Activity)mConext).startActivityForResult(new Intent(mConext, NoteActivity.class).putExtra("extra",model),100);
        }

        public void bind(int position) {
            model = mList.get(position);
            mTitle.setText(model.getTitle());
            mNote.setText(model.getNotes());
            mTime.setText(model.getDateTimeFormatted(mConext) + "");
        }
    }
}



