package es.ulpgc.mesa.carlos.examenpem.Master;

import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.ulpgc.mesa.carlos.examenpem.Person;

public class MasterModel implements MasterContract.Model {

    public static String TAG = MasterModel.class.getSimpleName();
    private ArrayList<Person> personArrayList = new ArrayList<Person>();
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    public MasterModel() {

    }

    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }

    @Override
    public void loadMasterItemList(final OnMasterItemListFetchedCallback callback) {
        Person person = new Person("miguel","martín","21","ocupa","no ha hecho nada con su vida","12345678w");
        database.child("users").child(person.getDni()).setValue(person);

        database.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                personArrayList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String name = dataSnapshot1.child("name").getValue(String.class);
                    String surname = dataSnapshot1.child("surname").getValue(String.class);
                    String age = dataSnapshot1.child("age").getValue(String.class);
                    String job = dataSnapshot1.child("job").getValue(String.class);
                    String cv = dataSnapshot1.child("cv").getValue(String.class);
                    String dni = dataSnapshot1.child("dni").getValue(String.class);
                    Person person = new Person(name, surname, age, job, cv, dni);
                        personArrayList.add(person);


                }

                callback.setMasterItemList(personArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
