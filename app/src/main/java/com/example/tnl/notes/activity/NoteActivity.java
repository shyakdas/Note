package com.example.tnl.notes.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tnl.notes.R;
import com.example.tnl.notes.Utils.Constant;
import com.example.tnl.notes.adapter.DataAdapter;
import com.example.tnl.notes.model.DataModel;
import com.example.tnl.notes.database.RealmHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.tnl.notes.Utils.Constant.REQUEST_CODE;

public class NoteActivity extends AppCompatActivity {

    @BindView(R.id.titleOfNote)
    EditText mTitle;
    @BindView(R.id.writeNote)
    EditText mNotes;
    @BindView(R.id.buttonSave)
    Button mSave;
    private DataAdapter dataAdapter;
    private DataModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);
        dataAdapter = new DataAdapter(new ArrayList<DataModel>(), this);
        if (getIntent().hasExtra(Constant.EXTRA)) {
            model = (DataModel) getIntent().getSerializableExtra(Constant.EXTRA);
            mSave.setText(R.string.update);
            mTitle.setText(model.getTitle());
            mNotes.setText(model.getNotes());
            getSupportActionBar().setTitle(R.string.edit_note);
        } else {
            getSupportActionBar().setTitle(R.string.new_note);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.buttonSave)
    public void saveNotes() {
        String title = mTitle.getText().toString();
        String note = mNotes.getText().toString();
        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(note)) {
            Toast.makeText(NoteActivity.this, R.string.titile_note_empty, Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(title)) {
            Toast.makeText(NoteActivity.this, R.string.title_emplty, Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(note)) {
            Toast.makeText(NoteActivity.this, R.string.note_empty, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(NoteActivity.this, R.string.note_saved, Toast.LENGTH_SHORT).show();
            if (model != null) {
                model.setTitle(title);
                model.setNotes(note);
                RealmHelper.getInstance().add(model);
                setResult(REQUEST_CODE);
                finish();
                return;
            }
            DataModel model = new DataModel();
            model.setTitle(title);
            model.setNotes(note);
            model.setId(RealmHelper.getInstance().getSize());
            RealmHelper.getInstance().add(model);
            setResult(REQUEST_CODE);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}