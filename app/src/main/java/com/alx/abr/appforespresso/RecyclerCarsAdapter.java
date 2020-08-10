package com.alx.abr.appforespresso;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerCarsAdapter extends RecyclerView.Adapter<RecyclerCarsAdapter.RowHolder> {

    private LayoutInflater inflater;

    public RecyclerCarsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RowHolder(inflater.inflate(R.layout.item_car, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RowHolder rowHolder, int i) {
        rowHolder.bindModel(Data.CARS.get(i));
    }

    @Override
    public int getItemCount() {
        return Data.CARS.size();
    }

    public static class RowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView_brand;
        TextView textView_model;

        public RowHolder(View row) {
            super(row);
            textView_brand = row.findViewById(R.id.textView_brand);
            textView_model = row.findViewById(R.id.textView_model);
            row.setOnClickListener(this);
        }

        public void bindModel(Car book) {
            textView_brand.setText(book.getBrand());
            textView_model.setText(book.getModel());
        }

        @Override
        public void onClick(View v) {
            Car book = Data.CARS.get(getPosition());

            Intent intent = new Intent(v.getContext(), CarDetailActivity.class);
            intent.putExtra("brand", book.getBrand());
            intent.putExtra("model", book.getModel());
            v.getContext().startActivity(intent);
        }
    }
}
