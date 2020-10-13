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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String Tag = "IngredientAdapter";
    private final int IsLastOne = 1;

    public OnItemClickListener mOnItemClickListener = null;
    private Context mContext;
    private List<Ingredient> ingredients;
    private NetworkService api;

    public IngredientAdapter(Context mContext, List<Ingredient> ingredients, NetworkService api) {
        this.mContext = mContext;
        this.ingredients = ingredients;
        this.api = api;
    }

    public List<Ingredient> getIngredients(){
        return ingredients;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(viewType == IsLastOne){
            view = inflater.inflate(R.layout.ref_ingredient_last_one, parent, false);
            Log.e(Tag,"hi");
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
            ((itemViewHolder) holder).ingdeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    api.deleteRefingredient(getIngredients().get(position).getIngredients_name()).enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if(response.isSuccessful()){ //response.code()
                                Log.e(Tag,response.body().toString());
                                //아이템 삭제하기
                                ingredients.remove(position);
                                //삭제된 아이템만 새로 뷰 업데이트
                                notifyItemRemoved(position);
//                                notifyItemRangeChanged(position, ingredients.size());
                            }
                            else{
                                Log.e(Tag,"onResponse: 아이템 삭제에 실패했습니다.");
                            }
                        }
                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Log.e(Tag,"아이템 삭제에 실패했습니다.");
                        }
                    });
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == ingredients.size()-1){
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
