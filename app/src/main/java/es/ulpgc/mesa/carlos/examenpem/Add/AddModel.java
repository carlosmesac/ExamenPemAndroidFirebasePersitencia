package es.ulpgc.mesa.carlos.examenpem.Add;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.ulpgc.mesa.carlos.examenpem.Person;

public class AddModel implements AddContract.Model {

    public static String TAG = AddModel.class.getSimpleName();
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
    public AddModel() {

    }

    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }

    @Override
    public void addPerson(final String name, final String surname, final String age, final String job, final String cv, final String dni, final CreatePersonEntrycCallback callback) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (name.equals("")||surname.equals("")||dni.equals("")||age.equals("")||job.equals("")||cv.equals("")){
                    callback.onAddPerson(true);
                }else{
                    Person person = new Person(name,surname,age,job,cv,dni);
                    reference.child(person.getDni()).setValue(person);
                    callback.onAddPerson(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
