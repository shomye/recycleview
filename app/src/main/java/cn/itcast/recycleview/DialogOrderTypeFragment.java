package cn.itcast.recycleview;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by 123 on 2018/2/11.
 * Desc:   选择工单类型
 */

public class DialogOrderTypeFragment extends DialogFragment implements View.OnClickListener{

    TextView baidu;
    TextView gaode;
    TextView cancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.activity_reach, null);

        baidu=(TextView)view.findViewById(R.id.baidu_tv);
        gaode=(TextView)view.findViewById(R.id.gaode_tv);
        cancel=(TextView)view.findViewById(R.id.cancel_tv);

        baidu.setOnClickListener(this);
        gaode.setOnClickListener(this);
        return view;
    }

    public OnDialogListener mlistener;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.baidu_tv:
                mlistener.onDialogClick("Baidu Map","0");
                dismiss();
                break;
            case R.id.gaode_tv:
                mlistener.onDialogClick("Gaode Map","1");
                dismiss();
                break;
            case R.id.cancel_tv:
                mlistener.onDialogClick("Cancel","2");
                dismiss();
                break;

        }
    }
    public interface OnDialogListener {
        void onDialogClick(String person, String code);
    }
    public void setOnDialogListener(OnDialogListener dialogListener){
        this.mlistener = dialogListener;
    }
    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        //设置背景透明
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
