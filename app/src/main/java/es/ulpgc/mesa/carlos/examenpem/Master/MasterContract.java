package es.ulpgc.mesa.carlos.examenpem.Master;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import es.ulpgc.mesa.carlos.examenpem.Person;

public interface MasterContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(MasterViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchData();

        void startAddScreen();

        void masterArrayList();

        void selectPersonListData(Person dni);
    }

    interface Model {
        String fetchData();

        interface OnMasterItemListFetchedCallback {
            void setMasterItemList(ArrayList<Person> itemList);
        }

        void loadMasterItemList(OnMasterItemListFetchedCallback callback);
    }


    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(Person person);

        MasterState getDataFromPreviousScreen();

        void goAdd();

        void goDetail();
    }
}
