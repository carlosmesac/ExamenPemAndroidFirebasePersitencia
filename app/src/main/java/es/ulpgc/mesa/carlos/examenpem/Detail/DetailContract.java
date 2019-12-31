package es.ulpgc.mesa.carlos.examenpem.Detail;

import java.lang.ref.WeakReference;

import es.ulpgc.mesa.carlos.examenpem.Person;

public interface DetailContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(DetailViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchData();

        void getPerson(String dni);

        void goHome();

        void deletePerson();
    }

    interface Model {
        String fetchData();

        interface OnDetailItemCallback {
            void setPerson(Person person);
        }

        void loadPersonData(String dni, OnDetailItemCallback callback);
    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(DetailState state);

        DetailState getDataFromPreviousScreen();

        void goHome();
    }
}
