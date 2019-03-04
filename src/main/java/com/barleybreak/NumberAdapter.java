package com.barleybreak;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import static com.barleybreak.MainActivity.EMPTY;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder> {

    private LinkedList<Integer> listNum;
    private Context context;
    private OnNumberClickListener onNumberClickListener;

    interface OnNumberClickListener {
        void onNumberClick(int i);
    }

    public void setOnNumberClickListener(OnNumberClickListener onNumberClickListener) {
        this.onNumberClickListener = onNumberClickListener;
    }

    public void setListNum(LinkedList<Integer> listNum) {
        this.listNum = listNum;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, int i) {
        int num = listNum.get(i);
        if (num == EMPTY) {
            numberViewHolder.textView.setText(" ");
            numberViewHolder.textView.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        } else {
            numberViewHolder.textView.setText(num + " ");
        }
    }

    @Override
    public int getItemCount() {
        return listNum.size();
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewNumber);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNumberClickListener != null) {
                        onNumberClickListener.onNumberClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
