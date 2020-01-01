package es.ulpgc.mesa.carlos.examenpem.Detail;

import android.app.ActivityManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.ulpgc.mesa.carlos.examenpem.Person;

public class DetailModel implements DetailContract.Model {

    public static String TAG = DetailModel.class.getSimpleName();

    private Person person = new Person();

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
    public DetailModel() {

    }

    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }

    @Override
    public void deletePerson(Person person, final OnDeletePerson callback) {
        reference.child(person.getDni()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                callback.deletePerson(false);
            }
        });
    }


}
