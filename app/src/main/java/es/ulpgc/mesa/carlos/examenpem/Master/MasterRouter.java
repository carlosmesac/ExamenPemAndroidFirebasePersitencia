package es.ulpgc.mesa.carlos.examenpem.Master;

import android.content.Intent;
import android.content.Context;

import es.ulpgc.mesa.carlos.examenpem.Add.AddActivity;
import es.ulpgc.mesa.carlos.examenpem.AppMediator;
import es.ulpgc.mesa.carlos.examenpem.Detail.DetailActivity;
import es.ulpgc.mesa.carlos.examenpem.Person;

public class MasterRouter implements MasterContract.Router {

    public static String TAG = MasterRouter.class.getSimpleName();

    private AppMediator mediator;

    public MasterRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, MasterActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void passDataToNextScreen(Person person) {
        mediator.setPerson(person);
    }



    @Override
    public MasterState getDataFromPreviousScreen() {
        MasterState state = mediator.getMasterState();
        return state;
    }

    @Override
    public void goAdd() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, AddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void goDetail() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
