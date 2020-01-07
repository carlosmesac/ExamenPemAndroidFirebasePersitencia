package es.ulpgc.mesa.carlos.examenpem.Detail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import es.ulpgc.mesa.carlos.examenpem.AppMediator;
import es.ulpgc.mesa.carlos.examenpem.Person;
import es.ulpgc.mesa.carlos.examenpem.R;

public class DetailActivity
        extends AppCompatActivity implements DetailContract.View {

    public static String TAG = DetailActivity.class.getSimpleName();

    private DetailContract.Presenter presenter;

    private Button deleteButton;

    private Button cancelButton;

    private Button editButton;

    private TextView name,surname,dni,age,job,cv,valoracion;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // do the setup
        DetailScreen.configure(this);

        deleteButton = findViewById(R.id.deleteButton);

        cancelButton = findViewById(R.id.cancelButton);

        editButton = findViewById(R.id.editButton);


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goHome();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deletePerson();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goEdit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data

        presenter.fetchData();
    }

    @Override
    public void displayData(DetailViewModel viewModel) {
        //Log.e(TAG, "displayData()");
        Person person = viewModel.person;
        if(person!= null){
            name = findViewById(R.id.nameLabel);

            surname = findViewById(R.id.surnameLabel);

            age = findViewById(R.id.ageLabel);

            dni = findViewById(R.id.dniLabel);

            job = findViewById(R.id.jobLabel);

            cv = findViewById(R.id.cvLabel);

            imageView = findViewById(R.id.image);

            valoracion = findViewById(R.id.valoracionText);
            name.setText(person.getName());
            surname.setText(person.getSurname());
            dni.setText(person.getDni());
           age.setText(person.getAge());
           job.setText(person.getJob());
            cv.setText(person.getCV());
            valoracion.setText(person.getValoracion());
            imageView.setImageBitmap(viewModel.image);
            presenter.personData();

        }
    }



    @Override
    public void displayMessage(DetailViewModel viewModel) {
        Toast.makeText(this, viewModel.data, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void injectPresenter(DetailContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
