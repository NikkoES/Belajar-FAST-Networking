package id.co.kosankoding.belajarfastnetworking.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.kosankoding.belajarfastnetworking.R;
import id.co.kosankoding.belajarfastnetworking.adapter.PenghuniAdapter;
import id.co.kosankoding.belajarfastnetworking.model.Penghuni;
import id.co.kosankoding.belajarfastnetworking.model.ResponsePenghuni;

import static id.co.kosankoding.belajarfastnetworking.Constant.BASE_URL;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    List<Penghuni> penghuniList;
    PenghuniAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        AndroidNetworking.initialize(getApplicationContext());

        initRecyclerview();
    }

    private void initRecyclerview() {
        penghuniList = new ArrayList<>();
        adapter = new PenghuniAdapter(this, penghuniList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.hasFixedSize();

        loadItems();
    }

    private void loadItems() {
        progressBar.setVisibility(View.VISIBLE);

        AndroidNetworking.get(BASE_URL + "penghuni/")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(ResponsePenghuni.class, new ParsedRequestListener<ResponsePenghuni>() {
                    @Override
                    public void onResponse(ResponsePenghuni response) {
                        List<Penghuni> data = response.getData();
                        if (data != null) {
                            penghuniList.addAll(data);
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(MainActivity.this, "Data kosong !", Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MainActivity.this, "Cannot load data !", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });

        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_tambah_penghuni)
    public void buttonPenghuni() {
        startActivity(new Intent(this, FormActivity.class));
    }
}