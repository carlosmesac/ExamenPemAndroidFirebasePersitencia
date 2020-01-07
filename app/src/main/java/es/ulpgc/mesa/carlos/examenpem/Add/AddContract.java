package es.ulpgc.mesa.carlos.examenpem.Add;

import android.widget.ImageView;

import java.lang.ref.WeakReference;

public interface AddContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(AddViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchData();

        void goHome();

        void addPerson(String nameText, String surnameText, String dniText, String ageText, String jobText, String cvText, ImageView imageView, String valoracion);
    }

    interface Model {
        String fetchData();

        void addPerson(final String name, final String surname, final String age, String job, String cv, final String dni, ImageView imageView,String valoracion, final AddContract.Model.CreatePersonEntrycCallback callback);

        interface CreatePersonEntrycCallback {
            void onAddPerson(boolean error);
        }
    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(AddState state);

        AddState getDataFromPreviousScreen();

        void goHome();
    }
}
