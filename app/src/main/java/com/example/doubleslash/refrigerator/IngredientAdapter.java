package com.example.doubleslash.refrigerator;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.example.doubleslash.R;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private static final String Tag = "IngredientAdapter";

    public OnItemClickListener mOnItemClickListener = null;
    private Context mContext;
    private ArrayList<Ingredient> ingredients;

    public IngredientAdapter(Context mContext, ArrayList<Ingredient> ingredients) {
        this.mContext = mContext;
        this.ingredients = ingredients;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.ref_ingredient_item, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e(Tag,"onBindViewHolder"+position);
        final Ingredient bindedingredient = ingredients.get(position);
//        Log.e(Tag,"bindedingredient 0 name: "+ingredients.get(position).getIngredients_name()+" "+ingredients.get(position).getThumbnail());
//
//        Glide.with(mContext)
//                .load(bindedingredient.getThumbnail())
//                .into(holder.img_thumb);

        holder.txt_name.setText(bindedingredient.getIngredients_name());
        holder.ingredientView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, bindedingredient);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Ingredient ingredient);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout ingredientView;
//        private ImageView img_thumb;
        private TextView txt_name;

        public ViewHolder(View convertView) {
            super(convertView);
            ingredientView = convertView.findViewById(R.id.ingredient_view);
//            img_thumb = convertView.findViewById(R.id.ingredient_img);
            txt_name = convertView.findViewById(R.id.ingredient_name);
        }
    }
}
