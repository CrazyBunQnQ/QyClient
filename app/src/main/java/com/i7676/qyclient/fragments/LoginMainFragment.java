package com.i7676.qyclient.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.BaseActivity;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
@Layout(R.layout.fragment_login_main) public class LoginMainFragment
    extends ToolbarInteractorFragment {

  private NonScrollableRecyclerView mSignInWayList;

  private Button register;
  private TextView fogetPassword;

  @Override protected void initView(View view) {
    ArrayList<SignWayEntity> signWays = new ArrayList<SignWayEntity>() {
      {
        add(new SignWayEntity(R.drawable.ic_login_qq, "QQ登录"));
        add(new SignWayEntity(R.drawable.ic_login_wx, "微信登录"));
        add(new SignWayEntity(R.drawable.ic_login_zfb, "支付宝登录"));
      }
    };
    mSignInWayList = (NonScrollableRecyclerView) view.findViewById(R.id.rv_signIn_way);
    mSignInWayList.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    mSignInWayList.setAdapter(new SignWayAdapter(getContext(), R.layout.item_signin_way, signWays));
    mSignInWayList.setHasFixedSize(true);

    // 手机注册按钮
    register = (Button) view.findViewById(R.id.btn_register);
    register.setOnClickListener(v -> {
      Bundle args = new Bundle();
      args.putString(LoginRegOrFgtpassFragment.RENDER_TYPE,
          LoginRegOrFgtpassFragment.RENDER_TYP_REGISTER);
      replaceTo(args);
    });
    // 忘记密码按钮
    fogetPassword = (TextView) view.findViewById(R.id.btn_fgtpass);
    fogetPassword.setOnClickListener(v -> {
      Bundle args = new Bundle();
      args.putString(LoginRegOrFgtpassFragment.RENDER_TYPE,
          LoginRegOrFgtpassFragment.RENDER_TYPE_FGTPASS);
      replaceTo(args);
    });
  }

  private class SignWayAdapter extends BaseQuickAdapter<SignWayEntity> {

    public SignWayAdapter(Context context, int layoutResId, List<SignWayEntity> data) {
      super(context, layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, SignWayEntity signWayEntity) {
      baseViewHolder.setImageResource(R.id.img_sin_logo, signWayEntity.getResId());
      baseViewHolder.setText(R.id.tv_sin_text, signWayEntity.getText());
    }
  }

  private class SignWayEntity {
    private int resId;
    private String text;

    public SignWayEntity(int resId, String text) {
      this.resId = resId;
      this.text = text;
    }

    public int getResId() {
      return resId;
    }

    public String getText() {
      return text;
    }
  }

  private void replaceTo(Bundle args) {
    getActivity().getSupportFragmentManager()
        .beginTransaction()
        .add(R.id.container, LoginRegOrFgtpassFragment.create(args, ((BaseActivity) getActivity())),
            LoginRegOrFgtpassFragment.class.getSimpleName() + ":" + args.getString(
                LoginRegOrFgtpassFragment.RENDER_TYPE))
        .commit();
  }
}
