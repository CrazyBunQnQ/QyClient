package com.i7676.qyclient.functions.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.RankingGameEntity;
import com.i7676.qyclient.functions.main.adapters.GameGridAdapter;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/28.
 *
 * 最新上线
 *
 * 没有支持 MVP 是因为此 Fragment 只需要关注两个功能，展示，loadMore，如果后续业务变得复杂了可以做重构考虑
 */
public class ShowGameFragment extends Fragment implements BaseQuickAdapter.RequestLoadMoreListener {

    public static final int SHOW_CATEGORY_HOTTEST = 1;
    public static final int SHOW_CATEGORY_NEWEST = 2;
    public static final String SHOW_CATEGORY_TYPE = ShowGameFragment.class.getSimpleName();
    public static final String SHOW_DATA = "SHOW_DATA";

    private ArrayList<RankingGameEntity> data;

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
        RecyclerView mList = (RecyclerView) view.findViewById(R.id.rv_game_list);
        mGameGridAdapter = new GameGridAdapter(R.layout.item_game_list_grid, gameEntities);
        mList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mList.setAdapter(mGameGridAdapter);
        mList.setHasFixedSize(true);

        // 加载更多设置
        mGameGridAdapter.setOnLoadMoreListener(this);

        loadingData();
    }

    private void loadingData() {
        argsCheck();
        //Bundle args = getArguments();
        //int gameCategory = -1;
        //switch (args.getInt(SHOW_CATEGORY_TYPE)) {
        //    case SHOW_CATEGORY_HOTTEST:
        //        gameCategory = SHOW_CATEGORY_HOTTEST;
        //        break;
        //    case SHOW_CATEGORY_NEWEST:
        //        gameCategory = SHOW_CATEGORY_NEWEST;
        //        break;
        //}
        //requestDataFromCloud(gameCategory);
        mGameGridAdapter.setNewData(data);
    }

    //private void requestDataFromCloud(int gameCategory) {
    //    Map<String, String> params = new HashMap<>();
    //    switch (gameCategory) {
    //        case SHOW_CATEGORY_HOTTEST:
    //            params.put("a", "getHotGame");
    //            break;
    //        case SHOW_CATEGORY_NEWEST:
    //            params.put("a", "getNewGame");
    //            break;
    //    }
    //    params.put("page", "1");
    //    params.put("size", "10");
    //
    //    mSubscription = mYNetApiService.getRankingGames(params)
    //        .subscribeOn(Schedulers.io())
    //        .observeOn(AndroidSchedulers.mainThread())
    //        .map(reqResult -> {
    //            if (reqResult.getRet() == 0) {
    //                return reqResult.getData();
    //            } else {
    //                throw new NullPointerException();
    //            }
    //        })
    //        .subscribe(// next
    //            entities -> mGameGridAdapter.setNewData(entities),
    //            // error
    //            throwable -> {
    //                Logger.e(">>> onError:" + throwable.getMessage());
    //                ((MainActivity) getContext()).closeDialog();
    //            },
    //            // complement
    //            () -> {
    //                Logger.i(">>> onComplement: getGameList");
    //                ((MainActivity) getContext()).closeDialog();
    //            });
    //}

    private void argsCheck() {
        Bundle args = getArguments();
        // Bundle#getInt default value is 0.
        if (args == null || args.getInt(SHOW_CATEGORY_TYPE) == 0) {
            throw new NullPointerException(
                "Using ShowGameFragment#create to create this fragment please.");
        }

        if ((data = args.getParcelableArrayList(SHOW_DATA)) == null) {
            throw new NullPointerException("No fucking data in here!");
        }
    }

    @Override public void onLoadMoreRequested() {
        Logger.i(">>> catch load more event.");
    }
}
