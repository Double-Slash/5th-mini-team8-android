package com.example.doubleslash.refrigerator;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doubleslash.R;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String Tag = "IngredientAdapter";
    private final int IsLastOne = 0;

    public OnItemClickListener mOnItemClickListener = null;
    private Context mContext;
    private ArrayList<Ingredient> ingredients;

    public IngredientAdapter(Context mContext, ArrayList<Ingredient> ingredients) {
        this.mContext = mContext;
        this.ingredients = ingredients;
        ingredients.add(getItemCount(), new Ingredient("last_one"));
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
        Log.e(Tag,"1");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(viewType == IsLastOne){
            view = inflater.inflate(R.layout.ref_ingredient_last_one, parent, false);
            return new LastViewHolder(view);
        }
        else{
            view = inflater.inflate(R.layout.ref_ingredient_item, parent, false);
            return new itemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e(Tag,"onBindViewHolder"+position);
        final Ingredient bindedingredient = ingredients.get(position);
//        Glide.with(mContext)
//                .load(bindedingredient.getThumbnail())
//                .into(holder.img_thumb);
        if(holder instanceof LastViewHolder){
            ((LastViewHolder) holder).LastAddBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(Tag,"마지막 버튼 눌림");
                    //+ 버튼 클릭시 새로운 액티비티로 이동
                    mContext.startActivity(new Intent(view.getContext(), AddIngredientActivity.class));
                }
            });
        }
        else{
            ((itemViewHolder) holder).txt_name.setText(bindedingredient.getIngredients_name());
//            ((itemViewHolder) holder).ingredientView.setOnClickListener(v -> mOnItemClickListener.onItemClick(v, bindedingredient));
            ((itemViewHolder) holder).ingdeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //아이템 삭제하기
                    ingredients.remove(position);
                    //서버통신으로 알려주기

                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == getItemCount()){
            return IsLastOne;
        }
        else{
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Ingredient ingredient);
    }

    public class itemViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout ingredientView;
        private TextView txt_name;
        private Button ingdeleteBtn;

        public itemViewHolder(View convertView) {
            super(convertView);
            ingredientView = convertView.findViewById(R.id.ingredient_view);
            txt_name = convertView.findViewById(R.id.ingredient_name);
            ingdeleteBtn = convertView.findViewById(R.id.ingdeleteBtn);
        }
    }

    public class LastViewHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout lastButtonView;
        private Button LastAddBtn;

        public LastViewHolder(View itemView) {
            super(itemView);
            lastButtonView = itemView.findViewById(R.id.last_ingredient_view);
            LastAddBtn = itemView.findViewById(R.id.last_add_btn);
        }
    }
}
