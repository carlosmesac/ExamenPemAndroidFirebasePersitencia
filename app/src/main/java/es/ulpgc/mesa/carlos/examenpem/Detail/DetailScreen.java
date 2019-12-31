package es.ulpgc.mesa.carlos.examenpem.Detail;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.mesa.carlos.examenpem.AppMediator;

public class DetailScreen {

    public static void configure(DetailContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        DetailState state = mediator.getDetailState();

        DetailContract.Router router = new DetailRouter(mediator);
        DetailContract.Presenter presenter = new DetailPresenter(state);
        DetailContract.Model model = new DetailModel();
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
