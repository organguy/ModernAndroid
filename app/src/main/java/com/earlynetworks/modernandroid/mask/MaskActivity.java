package com.earlynetworks.modernandroid.mask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.earlynetworks.modernandroid.R;
import com.earlynetworks.modernandroid.mask.model.Store;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

public class MaskActivity extends AppCompatActivity {

    private static final String TAG = MaskActivity.class.getSimpleName();

    private MaskViewModel viewModel;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mask);

        viewModel = new ViewModelProvider(this).get(MaskViewModel.class);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                performAction();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {

            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .check();

    }

    @SuppressLint("MissingPermission")
    private void performAction() {
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if(location != null){
                Log.d(TAG, "getLatitude : " + location.getLatitude());
                Log.d(TAG, "getLongitude : " + location.getLongitude());

                viewModel.location = location;
                viewModel.fetchStoreInfo();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        StoreAdapter adapter = new StoreAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.itemLiveData.observe(this, stores -> {
            adapter.updateItems(stores);
            getSupportActionBar().setTitle("마스크 재고 있는 곳: " + stores.size());
        });

        ProgressBar progressBar = findViewById(R.id.progressBar);
        viewModel.loadingLiveData.observe(this, isLoading -> {
            if (isLoading){
                progressBar.setVisibility(View.VISIBLE);
            }else{
                progressBar.setVisibility(View.GONE);
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
                viewModel.fetchStoreInfo();
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
        holder.distanceTextView.setText(String.format("%.2fKm", store.getDistance()));

        String remainStat = "충분";
        String remainCount = "100개 이상";
        int color = Color.GREEN;

        switch (store.getRemainStat()){
            case "plenty":
                remainStat = "충분";
                remainCount = "100개 이상";
                color = Color.GREEN;
                break;

            case "some":
                remainStat = "여유";
                remainCount = "30개 이상";
                color = Color.YELLOW;
                break;

            case "few":
                remainStat = "매진 임박";
                remainCount = "2개 이상";
                color = Color.RED;
                break;

            case "empty":
                remainStat = "재고 없음";
                remainCount = "1개 이하";
                color = Color.GRAY;
                break;

            default:
        }

        holder.remainTextView.setText(remainStat);
        holder.remainTextView.setTextColor(color);

        holder.countTextView.setText(remainCount);
        holder.countTextView.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}