package com.deye.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.deye.activity.device.base.BaseActivity;
import com.deye.helper.DialogHelper;
import com.mxchipapp.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ErrorListAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Map<String, String> errorCodeMap;
    BaseActivity mActivity;
    List<String> errorCodeList = new ArrayList();
    List<String> errorTextList = new ArrayList();

    public ErrorListAdapter(BaseActivity baseActivity, Map<String, String> map) {
        this.mActivity = baseActivity;
        this.errorCodeMap = map;
        for (String str : map.keySet()) {
            this.errorCodeList.add(str);
            this.errorTextList.add(this.errorCodeMap.get(str));
        }
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.mActivity).inflate(R.layout.error_list_item, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_error_code.setText(this.errorCodeList.get(i));
        myViewHolder.tv_error_text.setText(this.errorTextList.get(i));
        if (i % 2 != 0) {
            myViewHolder.ll_error_bg.setBackgroundColor(this.mActivity.getResources().getColor(R.color.error_code_bg));
        }
        myViewHolder.ll_error_bg.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.ErrorListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ErrorListAdapter errorListAdapter = ErrorListAdapter.this;
                errorListAdapter.showTipDialog(errorListAdapter.errorTextList.get(i));
            }
        });
    }

    public int getItemCount() {
        return this.errorTextList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_error_bg;
        TextView tv_error_code;
        TextView tv_error_text;

        public MyViewHolder(View view) {
            super(view);
            this.ll_error_bg = (LinearLayout) view.findViewById(R.id.ll_error_bg);
            this.tv_error_code = (TextView) view.findViewById(R.id.tv_error_code);
            this.tv_error_text = (TextView) view.findViewById(R.id.tv_error_text);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTipDialog(String str) {
        DialogHelper.oneBtnDialog(this.mActivity, str, null);
    }
}
