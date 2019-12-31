package es.ulpgc.mesa.carlos.examenpem.Detail;

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
        DetailState state = router.getDataFromPreviousScreen();
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
    public void getPerson(String dni) {
        model.loadPersonData(dni, new DetailContract.Model.OnDetailItemCallback() {
            @Override
            public void setPerson(Person person) {
                viewModel.person = person;
                view.get().displayData(viewModel);
            }
        });

    }

    @Override
    public void goHome() {
        router.goHome();
    }

    @Override
    public void deletePerson() {

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
