package com.example.tnl.notes.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tnl.notes.R;
import com.example.tnl.notes.notes.NoteActivity;
import com.example.tnl.notes.model.DataModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.tnl.notes.Utils.Constant.EXTRA;
import static com.example.tnl.notes.Utils.Constant.REQUEST_CODE;

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

    class NoteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.titleView)
        TextView mTitle;
        @BindView(R.id.noteView)
        TextView mNote;
        @BindView(R.id.timeView)
        TextView mTime;
        @BindView(R.id.item_view)
        RelativeLayout mItemView;
        private DataModel model;

        public NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            model = mList.get(position);
            mTitle.setText(model.getTitle());
            mNote.setText(model.getNotes());
            mTime.setText(model.getDateTimeFormatted(mConext) + "");
        }

        @OnClick(R.id.item_view)
        public void itemClick() {
            (mConext).startActivityForResult(new Intent(mConext, NoteActivity.class)
                    .putExtra(EXTRA, model), REQUEST_CODE);
        }
    }
}