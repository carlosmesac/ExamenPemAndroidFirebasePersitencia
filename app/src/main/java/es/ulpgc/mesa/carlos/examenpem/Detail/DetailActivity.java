package es.ulpgc.mesa.carlos.examenpem.Detail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import es.ulpgc.mesa.carlos.examenpem.AppMediator;
import es.ulpgc.mesa.carlos.examenpem.R;

public class DetailActivity
        extends AppCompatActivity implements DetailContract.View {

    public static String TAG = DetailActivity.class.getSimpleName();

    private DetailContract.Presenter presenter;

    private Button deleteButton;

    private Button cancelButton;

    private TextView name,surname,dni,age,job,cv;

    private AppMediator appMediator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // do the setup
        DetailScreen.configure(this);

        presenter.getPerson(appMediator.getDni());

        deleteButton = findViewById(R.id.deleteButton);

        cancelButton = findViewById(R.id.cancelButton);

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

    }



    @Override
    public void injectPresenter(DetailContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
