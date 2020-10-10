package com.example.foodmn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<CardViewItemDTO> cardViewItemDTOS = new ArrayList<>();
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public MyRecyclerViewAdapter() {
        cardViewItemDTOS.add(new CardViewItemDTO(R.drawable.food1,"떡볶이","0kcal"));
        cardViewItemDTOS.add(new CardViewItemDTO(R.drawable.food2,"돈까스","0kcal"));
        cardViewItemDTOS.add(new CardViewItemDTO(R.drawable.food3,"볶음밥","0kcal"));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //XML세팅
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item,parent,false);
        return new RowCell(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RowCell)holder).imageView.setImageResource(cardViewItemDTOS.get(position).imageview);
        ((RowCell)holder).title.setText(cardViewItemDTOS.get(position).title);
        ((RowCell)holder).subtitle.setText(cardViewItemDTOS.get(position).subtitle);
        //아이템 세팅
    }

    @Override
    public int getItemCount() {
        //이미지 카운터
        return cardViewItemDTOS.size();
    }

    private class RowCell extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView subtitle;

        public RowCell(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.cardview_imageview);
            title = (TextView)view.findViewById(R.id.cardview_title);
            subtitle = (TextView)view.findViewById(R.id.cardview_subtitle);
        }
    }
}
