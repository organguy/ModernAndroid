package com.earlynetworks.modernandroid.mask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.earlynetworks.modernandroid.MainActivity;
import com.earlynetworks.modernandroid.R;
import com.earlynetworks.modernandroid.mask.model.Store;
import com.earlynetworks.modernandroid.mask.model.StoreInfo;
import com.earlynetworks.modernandroid.mask.repository.MaskService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MaskActivity extends AppCompatActivity {

    private static final String TAG = MaskActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mask);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        StoreAdapter adapter = new StoreAdapter();
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MaskService.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        MaskService service = retrofit.create(MaskService.class);

        Call<StoreInfo> storeInfoCall = service.fetchStoreInfo();

        storeInfoCall.enqueue(new Callback<StoreInfo>() {
            @Override
            public void onResponse(Call<StoreInfo> call, Response<StoreInfo> response) {
                Log.d(TAG, "onResponse: refresh");
                List<Store> items = response.body().getStores();
                adapter.updateItems(items);
                getSupportActionBar().setTitle("마스크 재고 있는 곳:" + items.size() + "곳");
            }

            @Override
            public void onFailure(Call<StoreInfo> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_refresh:
                
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder>{

    private List<Store> mItems = new ArrayList<>();

    static class StoreViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView addressTextView;
        TextView distanceTextView;
        TextView remainTextView;
        TextView countTextView;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.name_text_view);
            addressTextView = itemView.findViewById(R.id.addr_text_view);
            distanceTextView = itemView.findViewById(R.id.distance_text_view);
            remainTextView = itemView.findViewById(R.id.remain_text_view);
            countTextView = itemView.findViewById(R.id.count_text_view);
        }
    }

    public void updateItems(List<Store> items){
        this.mItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {

        Store store = mItems.get(position);

        holder.nameTextView.setText(store.getName());
        holder.addressTextView.setText(store.getAddr());
        holder.distanceTextView.setText("1.0km");
        holder.remainTextView.setText(store.getRemainStat());
        holder.countTextView.setText("100개 이상");
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}