package com.anirayoubil.kidsLearning;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.anirayoubil.kidsLearning.helpers.SQLiteDbHelper;

import java.util.Calendar;

public class CreateProfileActivity extends AppCompatActivity {

    private Button pickDateBtn;
    private TextView selectedDateTV;
    private EditText nameEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        // on below line we are initializing our variables.
        pickDateBtn = findViewById(R.id.idBtnPickDate);
        selectedDateTV = findViewById(R.id.idTVSelectedDate);
        nameEditText = findViewById(R.id.name);
        submitButton = findViewById(R.id.create_profile_btn);

        submitButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String date = selectedDateTV.getText().toString();

            if (name.length() == 0 || date.length() == 0) {
                return;
            }

            SQLiteDbHelper.getInstance(CreateProfileActivity.this).createProfile(name, date);
            Intent intent = new Intent(CreateProfileActivity.this, ProfilesActivity.class);
            startActivity(intent);
            finish();
        });

        pickDateBtn.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    CreateProfileActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // on below line we are setting date to our text view.
                            selectedDateTV.setText(year + "-" + (monthOfYear + 1) + "-" + (dayOfMonth + 1));

                        }
                    },
                    year, month, day);
            datePickerDialog.show();
        });
    }
}
