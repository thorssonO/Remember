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
    ReminderDBHandler reminderDBHandler;
    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(); //Används inte till mer än att se till att datumet följer med
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
         reminderDBHandler= new ReminderDBHandler(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rem);
        dateButton = findViewById(R.id.dateButton);
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
                System.out.println("date saved: " + myCalendar);
                System.out.println("title saved: " + ((EditText)findViewById(R.id.titleText)).getText());
                //Kollar bara så texten följer med när jag sparar

                //Följande är så att användaren återvänder till homeactivity efter sparad reminder
                Intent homeIntent = new Intent(AddRemActivity.this, HomeActivity.class);
                startActivity(homeIntent);

                LinearLayout layout = findViewById(R.id.buttonLayout);

                // TODO: ta reda på hur man hittar de nyskapade edittextena, krashar efter mer än en lista


                List<ReminderItem> remItems = new ArrayList<>();

                for (int i = 0; i < itemCount; i++) {
                    EditText anEdit = layout.findViewById(i);

                    ReminderItem remItem = new ReminderItem(anEdit.getText().toString());
                    remItems.add(remItem);

                    //List<EditText> allEds = new ArrayList<EditText>();
                    //allEds.add(anEdit);
                }

                String title = ((EditText)findViewById(R.id.titleText)).getText().toString();
                ReminderTitle remTitle= new ReminderTitle(title);
                ReminderList remList = new ReminderList(title, remItems, myCalendar.getTime());
                //String time = myCalendar.getTime();
                FakeReminderStore.getInstance().addReminderList(remList);
                FakeReminderStore.getInstance().addTitle(remTitle);

                reminderDBHandler.addReminderData(new ReminderList(title));
                reminderDBHandler.addReminderItemData(new ReminderItem(remItem()));

                System.out.println("New list: " + remList);
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

    //Måste fixa ovanstående kod så den för anävndaren till startsidan efter "save" - NEJ

    //Metod för att lägga till nya edittexts i linearlayouten.
    protected void createEditTextView() {

        LinearLayout layout = findViewById(R.id.buttonLayout);
        EditText newEdit = new EditText(this);

        newEdit.setId(itemCount++);
        newEdit.setHint("New item:");
        newEdit.requestFocus();
        layout.addView(newEdit);
    }


}