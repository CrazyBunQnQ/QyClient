package com.i7676.qyclient.functions.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.i7676.qyclient.R;
import com.i7676.qyclient.api.RetrofitFactory;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.entity.RankingGameEntity;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.adapters.GameGridAdapter;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/28.
 *
 * 最新上线
 */
public class ShowGameFragment extends Fragment {

    public static final int SHOW_CATEGORY_HOTTEST = 1;
    public static final int SHOW_CATEGORY_NEWEST = 2;
    public static final String SHOW_CATEGORY_TYPE = ShowGameFragment.class.getSimpleName();

    private YNetApiService mYNetApiService;
    private Subscription mSubscription;

    /**
     * Must contain {@link #SHOW_CATEGORY_TYPE}
     */
    public static ShowGameFragment create(Bundle args) {
        final ShowGameFragment fragment = new ShowGameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private GameGridAdapter mGameGridAdapter;
    private ArrayList<RankingGameEntity> gameEntities = new ArrayList<>();

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_games, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).showDialog2User("加载中...");
        // 再创建一个API请求实例
        mYNetApiService =
            RetrofitFactory.createService(YNetApiService.BASE_URL, YNetApiService.class,
                getContext().getCacheDir());

        NonScrollableRecyclerView mList =
            (NonScrollableRecyclerView) view.findViewById(R.id.rv_game_list);
        mGameGridAdapter = new GameGridAdapter(R.layout.item_game_list_grid, gameEntities);
        mList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mList.setAdapter(mGameGridAdapter);
        mList.setHasFixedSize(true);

        loadingData();
    }

    private void loadingData() {
        argsCheck();

        Bundle args = getArguments();
        int gameCategory = -1;
        switch (args.getInt(SHOW_CATEGORY_TYPE)) {
            case SHOW_CATEGORY_HOTTEST:
                gameCategory = SHOW_CATEGORY_HOTTEST;
                break;
            case SHOW_CATEGORY_NEWEST:
                gameCategory = SHOW_CATEGORY_NEWEST;
                break;
        }

        requestDataFromCloud(gameCategory);
    }

    @Override public void onPause() {
        super.onPause();
        if (mSubscription.isUnsubscribed()) mSubscription.unsubscribe();
    }

    private void requestDataFromCloud(int gameCategory) {
        Map<String, String> params = new HashMap<>();
        switch (gameCategory) {
            case SHOW_CATEGORY_HOTTEST:
                params.put("a", "getHotGame");
                break;
            case SHOW_CATEGORY_NEWEST:
                params.put("a", "getNewGame");
                break;
        }
        params.put("page", "1");
        params.put("size", "10");

        mSubscription = mYNetApiService.getRankingGames(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(reqResult -> {
                if (reqResult.getRet() == 0) {
                    return reqResult.getData();
                } else {
                    throw new NullPointerException();
                }
            })
            .subscribe(// next
                entities -> mGameGridAdapter.setNewData(entities),
                // error
                throwable -> {
                    Logger.e(">>> onError:" + throwable.getMessage());
                    ((MainActivity) getContext()).closeDialog();
                },
                // complement
                () -> {
                    Logger.i(">>> onComplement: getGameList");
                    ((MainActivity) getContext()).closeDialog();
                });
    }

    private void argsCheck() {
        Bundle args = getArguments();
        // Bundle#getInt default value is 0.
        if (args == null || args.getInt(SHOW_CATEGORY_TYPE) == 0) {
            throw new NullPointerException(
                "Using ShowGameFragment#create to create this fragment please.");
        }
    }
}
