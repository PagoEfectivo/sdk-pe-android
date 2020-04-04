package com.example.pagoefectivo_sdk_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pagoefectivo_sdk_android.R;

import java.util.List;

import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipEntity;

public class WhereToPayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_CIP = 0;
    private static final int TYPE_WHERE_PAY = 1;

    private final Context context;
    private final List<Object> itemList;

    //Listener for onClick adapter
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClickAgentMobile(int position, int typeOfHolder);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public WhereToPayAdapter(List<Object> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == TYPE_CIP) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cip_where_pay, parent, false);
            return new CipViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_method_where_pay, parent, false);
            return new WherePayViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_CIP) {
            CipViewHolder cipHolder = (CipViewHolder) holder;
            CipEntity cipEntity = (CipEntity) itemList.get(position);
            String amountWithPEN = String.format(context.getString(R.string.currency_soles), cipEntity.getAmount());

            cipHolder.lblCip.setText(String.valueOf(cipEntity.getCip()));
            cipHolder.lblAmount.setText(amountWithPEN);
            cipHolder.lblDateExpire.setText(cipEntity.getDateExpiry());
            cipHolder.lblCipUrl.setText(cipEntity.getCipUrl());
        } else {
            WherePayViewHolder wherePayHolder = (WherePayViewHolder) holder;
            String wherePay = (String) itemList.get(position);

            wherePayHolder.lblWherePay.setText(wherePay);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (itemList.get(position) instanceof CipEntity) {
            return TYPE_CIP;
        } else {
            return TYPE_WHERE_PAY;
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private class WherePayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView lblWherePay;

        WherePayViewHolder(View view) {
            super(view);
            lblWherePay = view.findViewById(R.id.lblWherePay);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClickAgentMobile(getAdapterPosition(), TYPE_WHERE_PAY);
            }
        }
    }

    private class CipViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView lblCip;
        private final TextView lblAmount;
        private final TextView lblDateExpire;
        private final TextView lblCipUrl;

        CipViewHolder(View view) {
            super(view);
            lblCip = view.findViewById(R.id.lblCip);
            lblAmount = view.findViewById(R.id.lblAmount);
            lblDateExpire = view.findViewById(R.id.lblDateExpire);
            lblCipUrl = view.findViewById(R.id.lblCipUrl);
            this.lblCipUrl.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onItemClickAgentMobile(getAdapterPosition(), TYPE_CIP);
            }
        }
    }
}