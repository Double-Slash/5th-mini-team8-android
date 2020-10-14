package com.example.doubleslash.refrigerator;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doubleslash.R;

import java.util.List;

public class SearchIngredientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String Tag = "SearchIngredientAdapter";
    private Context mContext;
    private List<Ingredients> ingredients;

    public SearchIngredientAdapter(Context mContext, List<Ingredients> ingredients) {
        this.mContext = mContext;
        this.ingredients = ingredients;
    }

    public List<Ingredients> getIngredients(){
        return ingredients;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view  = inflater.inflate(R.layout.add_ingredient_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e(Tag,"onBindViewHolder"+position);

        final Ingredients bindedingredient = ingredients.get(position);
        ((ViewHolder) holder).itemName.setText(bindedingredient.getIngredient_name());
        ((ViewHolder) holder).itemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //담기버튼 클릭했을때 slideingredient list로 넘어가야 함
                ingredients.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView itemName;
        private Button itemAdd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.ingredient_item_name);
            itemAdd = itemView.findViewById(R.id.item_add_btn);
        }
    }
}
