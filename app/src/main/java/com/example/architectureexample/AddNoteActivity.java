package com.example.architectureexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    public static final String KEY_ID = "extra_id";
    public static final String KEY_TITLE = "extra_title";
    public static final String KEY_DESCRIPTION = "extra_description";
    public static final String KEY_PRIORITY = "extra_priority";

    private EditText editTitle;
    private EditText editDescription;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTitle = findViewById(R.id.edit_title);
        editDescription = findViewById(R.id.edit_description);
        numberPicker = findViewById(R.id.number_picker_priority);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Bundle extras = getIntent().getExtras();
        if (extras!= null) {
            setTitle("Edit Note");
            editTitle.setText(extras.getString(KEY_TITLE));
            editDescription.setText(extras.getString(KEY_DESCRIPTION));
            numberPicker.setValue(extras.getInt(KEY_PRIORITY));
        } else
            setTitle("Add Note");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                onSaveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void onSaveNote() {
        String title = editTitle.getText().toString();
        String description = editDescription.getText().toString();
        int priority = numberPicker.getValue();
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "please insert title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        bundle.putString(KEY_DESCRIPTION, description);
        bundle.putInt(KEY_PRIORITY, priority);
        if (getIntent().getExtras()!= null) {
            int id = getIntent().getExtras().getInt(KEY_ID, -1);
            if (id != -1) {
                bundle.putInt(KEY_ID, id);
            }
        }
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
