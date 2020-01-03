package es.ulpgc.mesa.carlos.examenpem.Edit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.mesa.carlos.examenpem.R;

public class EditActivity
        extends AppCompatActivity implements EditContract.View {

    public static String TAG = EditActivity.class.getSimpleName();

    private EditContract.Presenter presenter;

    private Button cancelButton;

    private Button editButton;

    private EditText name,surname,dni,age,job,cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        cancelButton = findViewById(R.id.cancelButton);

        editButton = findViewById(R.id.editButton);

        name = findViewById(R.id.nameText);

        surname = findViewById(R.id.surnameText);

        dni = findViewById(R.id.dniText);

        age = findViewById(R.id.ageText);

        job = findViewById(R.id.jobText);

        cv = findViewById(R.id.cvText);


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goHome();
            }

        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.editValues(name.getText().toString().trim(),surname.getText().toString().trim(),age.getText().toString().trim(),job.getText().toString().trim(),cv.getText().toString().trim(),dni.getText().toString().trim());
            }
        });

        // do the setup
        EditScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        presenter.fetchData();
    }

    @Override
    public void displayData(EditViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        name = findViewById(R.id.nameText);

        surname = findViewById(R.id.surnameText);

        dni = findViewById(R.id.dniText);

        age = findViewById(R.id.ageText);

        job = findViewById(R.id.jobText);

        cv = findViewById(R.id.cvText);

        name.setText(viewModel.person.getName());
        surname.setText(viewModel.person.getSurname());
        dni.setText(viewModel.person.getDni());
        dni.setEnabled(false);
        age.setText(viewModel.person.getAge());
        job.setText(viewModel.person.getJob());
        cv.setText(viewModel.person.getCV());

    }

    public void displayMessage(EditViewModel viewModel){
        Toast.makeText(this, viewModel.message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void injectPresenter(EditContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
