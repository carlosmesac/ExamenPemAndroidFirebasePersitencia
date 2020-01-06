package es.ulpgc.mesa.carlos.examenpem.Detail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
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
            viewModel.image = decodeBase64(person.getDni());
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
                    viewModel.data = "Deleted person from database";
                    view.get().displayMessage(viewModel);
                }
            }
        });
        router.goHome();

    }


    private static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    @Override
    public void goEdit() {
        router.goEdit();
    }

    @Override
    public void personData() {
        router.passDataToNextScreen(viewModel.person);
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
