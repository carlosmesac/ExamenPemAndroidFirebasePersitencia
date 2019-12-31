package es.ulpgc.mesa.carlos.examenpem.Detail;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.ulpgc.mesa.carlos.examenpem.Person;

public class DetailModel implements DetailContract.Model {

    public static String TAG = DetailModel.class.getSimpleName();

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
    public DetailModel() {

    }

    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }

    @Override
    public void loadPersonData(final String dni, final OnDetailItemCallback callback) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Person person = (Person) dataSnapshot.child(dni).getValue();
                callback.setPerson(person);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
