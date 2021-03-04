package adapter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendingmachine.FoodActivity;
import com.example.vendingmachine.R;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import model.FoodModel;

import static android.content.Context.MODE_PRIVATE;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private List<FoodModel> modelList;


    public FoodAdapter(List<FoodModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_info,parent,false);
        view.setClickable(true);

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "Item is clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//        view.setOnClickListener(true);
        return new ViewHolder(view);
    }
    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    FoodModel fm = modelList.get(position);
    final String imgPath = fm.getItemImage();
        StrictMode();
        try {
            URL url=new URL(imgPath);
            holder.image.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        }catch (Exception e){
            e.printStackTrace();
        }

    holder.itemname.setText(fm.getItemName());
    holder.itemprice.setText("Rs." +fm.getItemPrice());
//    holder.image.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Toast.makeText(view.getContext(), fm.getItemName() +" " + fm.getItemPrice() , Toast.LENGTH_SHORT).show();
//            SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("User", MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("item",  fm.getItemName());
//            editor.putString("price",  fm.getItemPrice());
//            editor.commit();
//
//        }
//    });


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemname,itemprice;
        ImageView image,qr;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname=itemView.findViewById(R.id.textName);
            itemprice=itemView.findViewById(R.id.textPrice);
            image = itemView.findViewById(R.id.itemImage);
            qr= itemView.findViewById(R.id.imageView);
//            label = itemView.findViewById(R.id.label);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(view.getContext(), FoodActivity.class);
                    intent.putExtra("itemName",modelList.get(getAdapterPosition()).getItemName());
                    intent.putExtra("itemPrice",modelList.get(getAdapterPosition()).getItemPrice());
                    intent.putExtra("itemImage",modelList.get(getAdapterPosition()).getItemImage());
                    ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                    progressDialog.setTitle("loading..");
                    progressDialog.show();

                    view.getContext().startActivity(intent);

                }
            });

        }
    }


}
