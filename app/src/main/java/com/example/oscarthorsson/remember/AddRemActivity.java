package com.example.oscarthorsson.remember;

import android.app.AlertDialog;
import android.app.DatePickerDialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddRemActivity extends AppCompatActivity {

    public Button dateButton;
    public Button addButton;
    public Button saveButton;
    public int itemCount = 0;
    ReminderDBHandler reminderDB;
    Calendar myCalendar = Calendar.getInstance();


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(); // <--Används inte till mer än att se till att datumet följer med
        }
    };

    //Används i LOG-syfte, skriver ut i Logcat
    private void updateLabel() {
        String myFormat = "MM/dd/YYYY";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        System.out.println(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        reminderDB = new ReminderDBHandler(this);

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
                //LOg
                createEditTextView();
            }
        });

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("date saved: " + myCalendar);
                System.out.println("title saved: " + ((EditText)findViewById(R.id.titleText)).getText());
                //Kollar bara så titeln följer med när jag sparar

                //Användaren behöver fylla i ett datum för att spara
                if (myCalendar.getTime().equals("")) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AddRemActivity.this);
                    builder1.setMessage("Don't forget to set a date");
                    builder1.setCancelable(true);
                }

                //Följande 2 rader är till för att användaren
                //återvänder till homeactivity efter sparad reminder
                Intent homeIntent = new Intent(AddRemActivity.this, HomeActivity.class);
                startActivity(homeIntent);

                LinearLayout layout = findViewById(R.id.buttonLayout);
                List<ReminderItem> remItems = new ArrayList<>();

                //itemCount=reminderDB.getItemCount();
                //itemCount++;

                for (int i = 0; i <itemCount ; i++) {
                    //titleText
                    System.out.println("itemCount:"+ itemCount);
                    String tag = "edit-" + i;
                    EditText anEdit = layout.findViewWithTag(tag);
                    Log.d("Idiot-henrik", "   * " + tag + " => anEdit " + anEdit);
                    String item = anEdit.getText().toString();
                    ReminderItem remItem = new ReminderItem(item);
                    remItems.add(remItem);
                }

                String title = ((EditText)findViewById(R.id.titleText)).getText().toString();
                ReminderList remList = new ReminderList(title, remItems, myCalendar.getTime());

                reminderDB.addReminderData(title);
                String id = reminderDB.getReminderID(title);
                for(ReminderItem item : remItems) {
                    reminderDB.addReminderItemData(item, id);
                }
                //Loggar bara...
                System.out.println("New list: " + remList);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);

    }

    //Metod för att lägga till nya edittexts i
    //linearlayouten. Anropas längre upp i klassen, i onClick()
    protected void createEditTextView(){
        LinearLayout layout = findViewById(R.id.buttonLayout);
        EditText newEdit = new EditText(this);
        newEdit.setTag("edit-" + itemCount);
        newEdit.setId(itemCount++);
        newEdit.setHint("New item:");
        newEdit.requestFocus();
        newEdit.setMaxLines(1);
        newEdit.setSingleLine(true);
        newEdit.setLines(1);
        Log.d("henrik", "  tag: " + newEdit.getTag());
        layout.addView(newEdit);
    }
}