package es.ulpgc.mesa.carlos.examenpem.Detail;

import java.lang.ref.WeakReference;

import es.ulpgc.mesa.carlos.examenpem.Person;

public interface DetailContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(DetailViewModel viewModel);

        void displayMessage(DetailViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchData();

        void goHome();

        void deletePerson();

        void goEdit();

        void personData();
    }

    interface Model {
        String fetchData();


        interface OnDeletePerson {
            void deletePerson(boolean error);
        }

        void deletePerson(Person person,OnDeletePerson callback);
    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(Person person);

        Person getDataFromPreviousScreen();

        void goHome();

        void goEdit();
    }
}
