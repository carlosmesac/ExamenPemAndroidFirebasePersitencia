package es.ulpgc.mesa.carlos.examenpem.Add;

import android.content.Context;
import android.content.Intent;

import es.ulpgc.mesa.carlos.examenpem.AppMediator;
import es.ulpgc.mesa.carlos.examenpem.Master.MasterActivity;
import es.ulpgc.mesa.carlos.examenpem.Master.MasterAdapter;

public class AddRouter implements AddContract.Router {

    public static String TAG = AddRouter.class.getSimpleName();

    private AppMediator mediator;

    public AddRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, AddActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void passDataToNextScreen(AddState state) {
        mediator.setAddState(state);
    }

    @Override
    public AddState getDataFromPreviousScreen() {
        AddState state = mediator.getAddState();
        return state;
    }

    @Override
    public void goHome() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, MasterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
