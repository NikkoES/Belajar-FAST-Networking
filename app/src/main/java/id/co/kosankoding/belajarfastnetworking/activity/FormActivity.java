package id.co.kosankoding.belajarfastnetworking.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.kosankoding.belajarfastnetworking.R;
import id.co.kosankoding.belajarfastnetworking.model.Penghuni;
import id.co.kosankoding.belajarfastnetworking.model.ResponsePenghuni;

import static id.co.kosankoding.belajarfastnetworking.Constant.BASE_URL;

public class FormActivity extends AppCompatActivity {

    @BindView(R.id.nama)
    EditText editTextNama;
    @BindView(R.id.hp)
    EditText editTextNomorHp;
    @BindView(R.id.gender)
    RadioGroup radioGroupGender;
    @BindView(R.id.status)
    Spinner spinnerStatus;
    @BindView(R.id.tv)
    CheckBox checkBoxTelevisi;
    @BindView(R.id.ac)
    CheckBox checkBoxAC;
    @BindView(R.id.kulkas)
    CheckBox checkBoxKulkas;
    @BindView(R.id.alamat)
    EditText editTextAlamat;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.bind(this);

        AndroidNetworking.initialize(getApplicationContext());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.button)
    public void simpan() {
        //ambil data nama
        String nama = editTextNama.getText().toString();

        //ambil data nomor hp
        String hp = editTextNomorHp.getText().toString();

        //ambil dara gender
        int genderId = radioGroupGender.getCheckedRadioButtonId();
        String gender = null;
        if (genderId == R.id.pria) {
            gender = "Pria";
        } else if (genderId == R.id.wanita) {
            gender = "Wanita";
        }

        //ambil data status
        String status = spinnerStatus.getSelectedItem().toString();

        //ambil data fasilitas
        String fasilitas = "";
        if (checkBoxTelevisi.isChecked()) {
            fasilitas = fasilitas + "TV, ";
        }
        if (checkBoxAC.isChecked()) {
            fasilitas = fasilitas + "AC, ";
        }
        if (checkBoxKulkas.isChecked()) {
            fasilitas = fasilitas + "Kulkas, ";
        }

        //ambil data alamat
        String alamat = editTextAlamat.getText().toString();

        Penghuni penghuni = new Penghuni("", nama, hp, gender, status, fasilitas, alamat);

        simpanData(penghuni);

        // destroy main activity dan kembali ke list
        finish();
    }

    private void simpanData(Penghuni penghuni) {
        progressBar.setVisibility(View.VISIBLE);

        AndroidNetworking.post(BASE_URL + "penghuni/")
                .addBodyParameter(penghuni)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(ResponsePenghuni.class, new ParsedRequestListener<ResponsePenghuni>() {
                    @Override
                    public void onResponse(ResponsePenghuni response) {
                        if (response.getStatus().equalsIgnoreCase("success")) {
                            Toast.makeText(FormActivity.this, "Data berhasil disimpan !", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            finish();
                        } else {
                            Toast.makeText(FormActivity.this, "Data gagal disimpan !", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(FormActivity.this, "Cannot save data !", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // check apakah home button
        if (item.getItemId() == android.R.id.home) {
            // destroy detail activty
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
