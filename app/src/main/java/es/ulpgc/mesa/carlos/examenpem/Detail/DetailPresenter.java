package es.ulpgc.mesa.carlos.examenpem.Detail;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.mesa.carlos.examenpem.AppMediator;
import es.ulpgc.mesa.carlos.examenpem.Person;

public class DetailPresenter implements DetailContract.Presenter {

    public static String TAG = DetailPresenter.class.getSimpleName();

    private WeakReference<DetailContract.View> view;
    private DetailViewModel viewModel;
    private DetailContract.Model model;
    private DetailContract.Router router;
    private AppMediator mediator;
    public DetailPresenter(DetailState state) {
        viewModel = state;
    }

    @Override
    public void fetchData() {
        // Log.e(TAG, "fetchData()");

        // use passed state if is necessary
        Person person = router.getDataFromPreviousScreen();
        if (person != null) {

            // update view and model state
            viewModel.person = person;

            Log.d("dni", viewModel.dni);
            // update the view

            view.get().displayData(viewModel);

            return;
        }

        // call the model  
        String data = model.fetchData();

        // set view state
        viewModel.data = data;

        // update the view

    }



    @Override
    public void goHome() {
        router.goHome();
    }

    @Override
    public void deletePerson() {
        model.deletePerson(viewModel.person, new DetailContract.Model.OnDeletePerson() {
            @Override
            public void deletePerson(boolean error) {
                if(!error){
                    viewModel.data = "Deleted person";
                    view.get().displayMessage(viewModel);
                    router.goHome();
                }
            }
        });
    }

    @Override
    public void injectView(WeakReference<DetailContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(DetailContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(DetailContract.Router router) {
        this.router = router;
    }
}
