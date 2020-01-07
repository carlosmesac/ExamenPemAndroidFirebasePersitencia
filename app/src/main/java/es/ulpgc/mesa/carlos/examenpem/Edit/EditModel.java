package es.ulpgc.mesa.carlos.examenpem.Edit;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.ulpgc.mesa.carlos.examenpem.Person;

public class EditModel implements EditContract.Model {
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");

    public static String TAG = EditModel.class.getSimpleName();

    public EditModel() {

    }

    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }

    @Override
    public void editPerson(final String name, final String surname, final String age, final String job, final String cv, final String dni, final String valoracion, final EditPersonEntrycCallback callback) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (name.equals("") || surname.equals("") || dni.equals("") || age.equals("") || job.equals("") || cv.equals("") || valoracion.equals("")) {
                    callback.onEditPerson(true);
                } else {

                        Person person = new Person(name, surname, age, job, cv, dni, valoracion);
                        switch (valoracion){
                            case "1":
                            case"2":
                            case "3":
                            case"4":
                            case "5":
                                reference.child(person.getDni()).setValue(person);

                                callback.onEditPerson(false);
                                break;

                            default:
                                callback.onEditPerson(true);
                                break;

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
