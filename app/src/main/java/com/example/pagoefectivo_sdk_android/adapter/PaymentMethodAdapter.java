package com.example.pagoefectivo_sdk_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pagoefectivo_sdk_android.R;
import com.example.pagoefectivo_sdk_android.model.PaymentMethodEntity;

import java.util.List;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.PaymentItemViewHolder> {

    private final List<PaymentMethodEntity> paymentMethodList;

    //Listener for onClick adapter
    private PaymentMethodAdapter.OnItemClickListener listener;
    public interface OnItemClickListener { void onItemClickMethodPayment(int typeItem); }
    public void setOnItemClickListener(OnItemClickListener listener) { this.listener = listener; }

    public PaymentMethodAdapter(List<PaymentMethodEntity> paymentMethodList) {
        this.paymentMethodList = paymentMethodList;
    }

    @Override
    public PaymentItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_method_payment, parent, false);
        return new PaymentItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PaymentItemViewHolder holder, int position) {
        holder.lblPaymentMethod.setText(paymentMethodList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return paymentMethodList.size();
    }

    class PaymentItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView lblPaymentMethod;

        PaymentItemViewHolder(View itemView) {
            super(itemView);
            lblPaymentMethod = itemView.findViewById(R.id.lblPaymentMethod);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClickMethodPayment(paymentMethodList.get(getAdapterPosition()).getType());
            }
        }
    }
}
