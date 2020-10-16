package com.example.fooda;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String data1[], data2[], data3[], data4[], data5[], data6[];
    int images[];
    Context context;

    public MyAdapter(Context ct, String s1[], String s2[], String s3[], String s4[], String s5[], String s6[],  int img[]){
        context =ct;
        data1 = s1;
        data2 = s2;
        data3 = s3;
        data4 = s4;
        data5 = s5;
        data6 = s6;
        images = img;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView TextView_Title;
        public TextView TextView_Content;
        public ImageView ImageView_title;
        public LinearLayout mainLayout;

        public MyViewHolder(@NonNull View v) {
            super(v);
            TextView_Title = v.findViewById(R.id.TextView_Title);
            TextView_Content = v.findViewById(R.id.TextView_Content);
            ImageView_title = v.findViewById(R.id.ImageView_title);
            mainLayout = v.findViewById(R.id.mainLayout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                       // RecyclerItem item = mDataset.get(pos);
                        Toast.makeText(v.getContext(),"테스트",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
  //  public MyAdapter(String[] myDataset) {
  //      mDataset = myDataset;
    //}

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(context)
                .inflate(R.layout.row_food, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    //뷰홀더가 반복되면서 mDataset 수만큼 onBindViewHolder에 세팅되는중
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.TextView_Title.setText(data1[position]);
        holder.TextView_Content.setText(data2[position]);
        holder.ImageView_title.setImageResource(images[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,food_info.class);
                intent.putExtra("data1", data1[position]);
                intent.putExtra("data2", data2[position]);
                intent.putExtra("myImage", images[position]); //////////
                context.startActivity(intent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return images.length;
    }
}
