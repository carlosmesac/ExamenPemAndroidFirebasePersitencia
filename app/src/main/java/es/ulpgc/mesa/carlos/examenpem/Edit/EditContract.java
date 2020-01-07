package es.ulpgc.mesa.carlos.examenpem.Edit;

import java.lang.ref.WeakReference;

import es.ulpgc.mesa.carlos.examenpem.Person;

public interface EditContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(EditViewModel viewModel);

        void displayMessage(EditViewModel viewModel);

        }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchData();

        void goHome();

        void editValues(String name, String surname, String age, String job, String cv, String dni,String valoracion);
    }

    interface Model {
        String fetchData();

        void editPerson(final String name, final String surname, final String age, String job, String cv, final String dni, String valoracion, final EditContract.Model.EditPersonEntrycCallback callback);

        interface EditPersonEntrycCallback {
            void onEditPerson(boolean error);
        }
    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(EditState state);

        Person getDataFromPreviousScreen();

        void goHome();
    }
}
