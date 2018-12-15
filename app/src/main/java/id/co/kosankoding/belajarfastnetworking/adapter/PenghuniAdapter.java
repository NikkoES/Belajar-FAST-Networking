package id.co.kosankoding.belajarfastnetworking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.kosankoding.belajarfastnetworking.R;
import id.co.kosankoding.belajarfastnetworking.model.Penghuni;

public class PenghuniAdapter extends RecyclerView.Adapter<PenghuniAdapter.MyViewHolder> {

    private Context context;
    private List<Penghuni> listPenghuni;

    public PenghuniAdapter(Context context, List<Penghuni> listPenghuni) {
        this.context = context;
        this.listPenghuni = listPenghuni;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_penghuni, parent, false);
        return new PenghuniAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenghuniAdapter.MyViewHolder holder, int position) {
        Penghuni penghuni = listPenghuni.get(position);
        holder.nama.setText(penghuni.getNama());
        holder.gender.setText(penghuni.getGender());
        holder.hp.setText(penghuni.getNoHp());
        holder.status.setText(penghuni.getStatus());
    }

    @Override
    public int getItemCount() {
        return listPenghuni.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nama)
        TextView nama;
        @BindView(R.id.gender)
        TextView gender;
        @BindView(R.id.hp)
        TextView hp;
        @BindView(R.id.status)
        TextView status;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    Toast.makeText(context, "Clicked !"+pos, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
