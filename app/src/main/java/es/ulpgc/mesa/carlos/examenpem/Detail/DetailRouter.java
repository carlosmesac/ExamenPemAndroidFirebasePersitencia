package es.ulpgc.mesa.carlos.examenpem.Detail;

import android.content.Intent;
import android.content.Context;

import es.ulpgc.mesa.carlos.examenpem.AppMediator;
import es.ulpgc.mesa.carlos.examenpem.Master.MasterActivity;
import es.ulpgc.mesa.carlos.examenpem.Person;

public class DetailRouter implements DetailContract.Router {

    public static String TAG = DetailRouter.class.getSimpleName();

    private AppMediator mediator;

    public DetailRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, DetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void passDataToNextScreen(DetailState state) {
        mediator.setDetailState(state);
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
