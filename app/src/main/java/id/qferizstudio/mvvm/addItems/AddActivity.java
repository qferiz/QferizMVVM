package id.qferizstudio.mvvm.addItems;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import id.qferizstudio.mvvm.R;
import id.qferizstudio.mvvm.db.BorrowModel;

/**
 * Created by Qferiz on 24/05/2017.
 */

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Date date;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    private EditText itemEditText, nameEditText;

    private AddBorrowViewModel addBorrowViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        itemEditText = (EditText) findViewById(R.id.itemName);
        nameEditText = (EditText) findViewById(R.id.personName);

        calendar = Calendar.getInstance();
        addBorrowViewModel = ViewModelProviders.of(this).get(AddBorrowViewModel.class);

        datePickerDialog = new DatePickerDialog(this, AddActivity.this, calendar.get(calendar.YEAR),
                calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemEditText.getText() == null ||
                        nameEditText.getText() == null || date == null) {

                    Toast.makeText(AddActivity.this, "Missing fields", Toast.LENGTH_SHORT).show();

                } else {
                    addBorrowViewModel.addBorrow(new BorrowModel(0,
                            itemEditText.getText().toString(),
                            nameEditText.getText().toString(),
                            date
                    ));
                    finish();
                }
            }
        });

        findViewById(R.id.dateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTime();
    }
}
