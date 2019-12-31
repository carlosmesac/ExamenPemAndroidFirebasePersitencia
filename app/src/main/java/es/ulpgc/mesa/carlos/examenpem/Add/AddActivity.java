package es.ulpgc.mesa.carlos.examenpem.Add;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.mesa.carlos.examenpem.R;

public class AddActivity
        extends AppCompatActivity implements AddContract.View {

    public static String TAG = AddActivity.class.getSimpleName();

    public Button addButton;

    public Button cancelButton;

    public EditText name, surname, cv , dni, job, age;

    private AddContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // do the setup
        AddScreen.configure(this);
        addButton = findViewById(R.id.deleteButton);
        cancelButton = findViewById(R.id.cancelButton);
        name = findViewById(R.id.nameText);
        surname = findViewById(R.id.surnameText);
        cv = findViewById(R.id.cvText);
        dni = findViewById(R.id.dniText);
        job = findViewById(R.id.jobText);
        age = findViewById(R.id.ageText);


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goHome();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addPerson(name.getText().toString().trim(),surname.getText().toString().trim(),age.getText().toString().trim(),job.getText().toString().trim(),cv.getText().toString().trim(),dni.getText().toString().trim());
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        //presenter.fetchData();
    }

    @Override
    public void displayData(AddViewModel viewModel) {
        //Log.e(TAG, "displayData()");
        Toast.makeText(getApplicationContext(), viewModel.message, Toast.LENGTH_LONG).show();

        // deal with the data
    }

    @Override
    public void injectPresenter(AddContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
