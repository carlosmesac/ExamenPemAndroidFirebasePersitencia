package es.ulpgc.mesa.carlos.examenpem.Add;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.mesa.carlos.examenpem.AppMediator;

public class AddScreen {

    public static void configure(AddContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        AddState state = mediator.getAddState();

        AddContract.Router router = new AddRouter(mediator);
        AddContract.Presenter presenter = new AddPresenter(state);
        AddContract.Model model = new AddModel();
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
