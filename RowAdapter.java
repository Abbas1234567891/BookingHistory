package com.tcs.sqlitedatabaseexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by 1256010 on 6/30/2016.
 */
public class RowAdapter extends RecyclerView.Adapter<RowAdapter.MyViewHolder> {
    Context context;

   /* public RowAdapter(CallBack callBack) {
        this.callBack = callBack;
    }*/

    static List<DetailsRowHolder> mList =null;

    public RowAdapter(List<DetailsRowHolder> mList) {
        this.mList = mList;
    }


    @Override
    public RowAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout,viewGroup,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RowAdapter.MyViewHolder myViewHolder, int i) {

        DetailsRowHolder detailsRowHolder = Booking.mList.get(i);
        myViewHolder.mTxtMobile.setText(detailsRowHolder.getmMobile());
        myViewHolder.mTextview.setText(detailsRowHolder.getmDate());


    }

    @Override
    public int getItemCount() {
        return Booking.mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtMobile,mTextview;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTxtMobile = (TextView)itemView.findViewById(R.id.edtMobile);
            mTextview = (TextView)itemView.findViewById(R.id.textView);
            /*Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(c.getTime());
            textview.setText(strDate);*/
        }
    }
}
