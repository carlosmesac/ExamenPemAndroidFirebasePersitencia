package es.ulpgc.mesa.carlos.examenpem.Add;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.mesa.carlos.examenpem.R;

public class AddActivity
        extends AppCompatActivity implements AddContract.View {

    public static String TAG = AddActivity.class.getSimpleName();

    public Button addButton;

    public Button cancelButton;

    public EditText name, surname, cv , dni, job, age;

    private AddContract.Presenter presenter;

    private ImageView image;

    Integer REQUEST_CAMERA=1,SELECT_FILE=0;
    private final int READ_EXTERNAL_STORAGE = 1;

    private Uri filePath;


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
        image = findViewById(R.id.addImage);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goHome();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addPerson(name.getText().toString().trim(),surname.getText().toString().trim(),age.getText().toString().trim(),job.getText().toString().trim(),cv.getText().toString().trim(),dni.getText().toString().trim(),image);
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    /**
     * Method that creates a window where you select the way to get an image
     */
    private void selectImage(){
        final CharSequence[] items= {"Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(items[i].equals("Gallery")){
                    Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent,"Select File"),SELECT_FILE);
                }else if(items[i].equals("Cancel")){
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    /**
     * Method that sets the ItemView with the image selected by the user
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==REQUEST_CAMERA){
                Bundle bundle= data.getExtras();
                final Bitmap bitmap=(Bitmap) bundle.get("data");
                image.setImageBitmap(bitmap);
            }else if(requestCode== SELECT_FILE){
                Uri selectedImage= data.getData();
                filePath= selectedImage;
                image.setImageURI(selectedImage);
            }

        }

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
