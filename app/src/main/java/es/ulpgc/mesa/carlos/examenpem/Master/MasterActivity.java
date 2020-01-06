package es.ulpgc.mesa.carlos.examenpem.Master;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.ulpgc.mesa.carlos.examenpem.Person;
import es.ulpgc.mesa.carlos.examenpem.R;

public class MasterActivity
        extends AppCompatActivity implements MasterContract.View {

    public static String TAG = MasterActivity.class.getSimpleName();

    private MasterContract.Presenter presenter;

    private MasterAdapter masterAdapter;

    private Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_master);
        //llenamos la tableView
        // adapter and button
        masterAdapter = new MasterAdapter(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Person item =(Person) view.getTag();
                    presenter.selectPersonListData(item);
                    Log.d("Click on person", item.toString());
                }
        });
        addButton = findViewById(R.id.deleteButton);

        // declaring the recyclerView, finding its id and changing its adapter
        RecyclerView recyclerView = findViewById(R.id.doorList);
        recyclerView.setAdapter(masterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.startAddScreen();
                finish();
            }
        });

        // do the setup
        MasterScreen.configure(this);

        // fetch list of people
        presenter.fetchData();
    }

    @Override
    protected void onResume() {

        super.onResume();

        // do some work
        presenter.fetchData();
        presenter.masterArrayList();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public void injectPresenter(MasterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayData(final MasterViewModel viewModel) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
            // adapter sets the list items
                masterAdapter.setItems(viewModel.personArrayList);
            }
        });
    }
}