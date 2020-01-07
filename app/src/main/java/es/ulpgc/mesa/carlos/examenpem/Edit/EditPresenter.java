package es.ulpgc.mesa.carlos.examenpem.Edit;

import android.accessibilityservice.GestureDescription;
import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.mesa.carlos.examenpem.Person;

public class EditPresenter implements EditContract.Presenter {

    public static String TAG = EditPresenter.class.getSimpleName();

    private WeakReference<EditContract.View> view;
    private EditViewModel viewModel;
    private EditContract.Model model;
    private EditContract.Router router;

    public EditPresenter(EditState state) {
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
    public void editValues(String name, String surname, String age, String job, String cv, String dni,String valoracion) {
        model.editPerson(name, surname, age, job, cv, dni,valoracion, new EditContract.Model.EditPersonEntrycCallback() {
            @Override
            public void onEditPerson(boolean error) {
                if(!error){
                    viewModel.message = "Edited";
                    view.get().displayMessage(viewModel);
                    router.goHome();
                }else{
                    viewModel.message = "Please fill in all fields and make sure valoracion from 1 to 5";
                    view.get().displayMessage(viewModel);
                }
            }
        });
    }

    @Override
    public void injectView(WeakReference<EditContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(EditContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(EditContract.Router router) {
        this.router = router;
    }
}
