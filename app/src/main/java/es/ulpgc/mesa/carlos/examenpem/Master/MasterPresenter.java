package es.ulpgc.mesa.carlos.examenpem.Master;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import es.ulpgc.mesa.carlos.examenpem.Person;

public class MasterPresenter implements MasterContract.Presenter {

    public static String TAG = MasterPresenter.class.getSimpleName();

    private WeakReference<MasterContract.View> view;
    private MasterViewModel viewModel;
    private MasterContract.Model model;
    private MasterContract.Router router;

    public MasterPresenter(MasterState state) {
        viewModel = state;
    }

    @Override
    public void fetchData() {
        // Log.e(TAG, "fetchData()");

        // use passed state if is necessary
        MasterState state = router.getDataFromPreviousScreen();
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
    public void startAddScreen() {
        router.goAdd();
    }

    public void masterArrayList(){
        model.loadMasterItemList(new MasterContract.Model.OnMasterItemListFetchedCallback() {
            @Override
            public void setMasterItemList(ArrayList<Person> itemList) {
                viewModel.personArrayList = itemList;
                view.get().displayData(viewModel);
            }
        });
    }

    @Override
    public void selectPersonListData(Person dni) {
        router.passDataToNextScreen(dni);
        router.goDetail();
    }

    @Override
    public void injectView(WeakReference<MasterContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(MasterContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(MasterContract.Router router) {
        this.router = router;
    }
}
