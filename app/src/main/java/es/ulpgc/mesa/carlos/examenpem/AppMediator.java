package es.ulpgc.mesa.carlos.examenpem;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

import es.ulpgc.mesa.carlos.examenpem.Add.AddScreen;
import es.ulpgc.mesa.carlos.examenpem.Add.AddState;
import es.ulpgc.mesa.carlos.examenpem.Detail.DetailState;
import es.ulpgc.mesa.carlos.examenpem.Edit.EditState;
import es.ulpgc.mesa.carlos.examenpem.Master.MasterState;

public class AppMediator extends Application {
    public MasterState masterState;

    public DetailState detailState;

    public AddState addState;

    public EditState editState;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String dni;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person person;

    public MasterState getMasterState() {
        return masterState;
    }

    public void setMasterState(MasterState masterState) {
        this.masterState = masterState;

    }

    public EditState getEditState() {
        return editState;
    }

    public void setEditState(EditState editState) {
        this.editState = editState;
    }

    public DetailState getDetailState() {
        return detailState;
    }

    public void setDetailState(DetailState detailState) {
        this.detailState = detailState;
    }

    public AddState getAddState() {
        return addState;
    }

    public void setAddState(AddState addState) {
        this.addState = addState;
    }

    public AppMediator() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Establecemos que la base de datos sea persistente en el movil
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        FirebaseDatabase.getInstance().getReference().keepSynced(true);
        masterState = new MasterState();
        detailState = new DetailState();
        editState = new EditState();
        addState = new AddState();
        this.dni = "";
        person = new Person();
    }
}
