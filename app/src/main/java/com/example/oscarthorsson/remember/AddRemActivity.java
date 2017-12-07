package com.example.oscarthorsson.remember;

import android.app.DatePickerDialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;



public class AddRemActivity extends AppCompatActivity {
        //implements DatePickerDialog.OnDateSetListener

    //undersök datepickerdialog

    public Button dateButton;


    public Button addButton;
    public Button saveButton;
    static int itemCount = 0;

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(); // just logging the data for now.
        }
        //TODO använd TAG istället för ID på de Edittext som skapas dynamiskt
    };

    private void updateLabel() {
        String myFormat = "MM/dd/YYYY";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        System.out.println(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rem);
        dateButton = findViewById(R.id.dateButton);
        //parentLayout = (LinearLayout)findViewById(R.id.parentLayout);
        dateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddRemActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Add button clicked");
                createEditTextView();
            }
        });

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Save button clicked - date: " + myCalendar);
                System.out.println("save button - title: " + ((EditText)findViewById(R.id.titleText)).getText());
                //Kollar bara så texten följer med när jag sparar
                LinearLayout layout = findViewById(R.id.buttonLayout);

                // TODO: find out how to find the edittext things added dynamically - I have no idea...

                List<ReminderItem> remItems = new ArrayList<>();
                for (int i = 0; i < itemCount; i++) {
                    EditText anEdit = layout.findViewById(i);
                    ReminderItem remItem = new ReminderItem(anEdit.getText().toString());
                    remItems.add(remItem);
                }

                String title = ((EditText)findViewById(R.id.titleText)).getText().toString();
                ReminderTitle remTitle= new ReminderTitle(title);
                ReminderList remList = new ReminderList(title, remItems, myCalendar.getTime());
                System.out.println("New list: " + remList);
                FakeReminderStore.getInstance().addReminderList(remList);
                FakeReminderStore.getInstance().addTitle(remTitle);


            }
        });
    }

    /*public void onSaveBtn(View v) {
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent but22 = new Intent(AddRemActivity.this, HomeActivity.class);
                startActivity(but22);
            }
        });
    }*/

    protected void createEditTextView() {
        LinearLayout layout = findViewById(R.id.buttonLayout);
        EditText newEdit = new EditText(this);
        newEdit.setId(itemCount++);
        newEdit.setHint("New item:");
        newEdit.requestFocus();
        layout.addView(newEdit);
    }
}