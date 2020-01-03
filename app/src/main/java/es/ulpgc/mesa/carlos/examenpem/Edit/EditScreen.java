package es.ulpgc.mesa.carlos.examenpem.Edit;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.mesa.carlos.examenpem.AppMediator;

public class EditScreen {

    public static void configure(EditContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        EditState state = mediator.getEditState();

        EditContract.Router router = new EditRouter(mediator);
        EditContract.Presenter presenter = new EditPresenter(state);
        EditContract.Model model = new EditModel();
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
