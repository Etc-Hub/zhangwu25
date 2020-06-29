package com.example.zhangwu25;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zhangwu25.R;

import java.util.List;

public class MyAdapter  extends ArrayAdapter {

    private final int ImageId;
    private Context mContext;
    private List<DouBanEntity.ListBean> listData;
    private OnBackInfo onItem;

    public MyAdapter(Context context, int layout, List<DouBanEntity.ListBean> obj,OnBackInfo iBack){
        super(context,layout,obj);
        ImageId = layout;
        listData  = obj;
        mContext = context;
        onItem =iBack;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(ImageId,null);

        DouBanEntity.ListBean bean = listData.get(position);

        TextView titleTx   = view.findViewById(R.id.item_name_tx);
        TextView contentTx  =view.findViewById(R.id.item_content_tx);
        ImageView fileImg  = view.findViewById(R.id.item_logo_img);
        TextView seeTx = view.findViewById(R.id.itrm_see_tx);
        TextView lookTx = view.findViewById(R.id.item_look_tx);

        titleTx.setText(bean.getTitle());
        contentTx.setText(bean.getProfile());
        ImageLoader.loadImage(fileImg,bean.getThumbPath());

        seeTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tent = new Intent(mContext,WebViewActivity.class);
                tent.putExtra("weburl",listData.get(position).getFilePath());
                mContext.startActivity(tent);
            }
        });

        lookTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItem.onClickItem(position);
            }
        });
        return view;
    }

    public interface  OnBackInfo{
        void onClickItem(int postion);
    }

}




