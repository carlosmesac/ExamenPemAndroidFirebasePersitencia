package es.ulpgc.mesa.carlos.examenpem.Add;

import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.WeakReference;

import es.ulpgc.mesa.carlos.examenpem.Person;

public class AddPresenter implements AddContract.Presenter {

    public static String TAG = AddPresenter.class.getSimpleName();

    private WeakReference<AddContract.View> view;
    private AddViewModel viewModel;
    private AddContract.Model model;
    private AddContract.Router router;

    public AddPresenter(AddState state) {
        viewModel = state;
    }

    @Override
    public void fetchData() {
        // Log.e(TAG, "fetchData()");

        // use passed state if is necessary
        AddState state = router.getDataFromPreviousScreen();
        if (state != null) {

            // update view and model state
            viewModel.data = state.data;

            // update the view
            view.get().displayData(viewModel);

            return;
        }

        // call the model  
        String data = model.fetchData();

        // set view state
        viewModel.data = data;

        // update the view
        view.get().displayData(viewModel);

    }

    @Override
    public void goHome() {
        router.goHome();
    }

    @Override
    public void addPerson(String nameText, String surnameText, String ageText, String jobText, String cvText, String dniText, ImageView imageView) {

        model.addPerson(nameText, surnameText, ageText, jobText, cvText, dniText,imageView, new AddContract.Model.CreatePersonEntrycCallback() {
            @Override
            public void onAddPerson(boolean error) {
                if(!error){
                    viewModel.message = "Person added";
                    view.get().displayData(viewModel);
                    router.goHome();
                }else{
                    viewModel.message = "Please fill in all fields";
                    view.get().displayData(viewModel);
                }
            }
        });
    }

    @Override
    public void injectView(WeakReference<AddContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(AddContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(AddContract.Router router) {
        this.router = router;
    }
}
