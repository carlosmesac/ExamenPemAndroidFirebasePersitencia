package es.ulpgc.mesa.carlos.examenpem.Edit;

import android.util.Log;
import android.content.Intent;
import android.content.Context;

import es.ulpgc.mesa.carlos.examenpem.AppMediator;
import es.ulpgc.mesa.carlos.examenpem.Master.MasterActivity;
import es.ulpgc.mesa.carlos.examenpem.Person;

public class EditRouter implements EditContract.Router {

    public static String TAG = EditRouter.class.getSimpleName();

    private AppMediator mediator;

    public EditRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, EditActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void passDataToNextScreen(EditState state) {
        mediator.setEditState(state);
    }

    @Override
    public Person getDataFromPreviousScreen() {
        Person person = mediator.getPerson();
        return person;
    }

    @Override
    public void goHome() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, MasterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
