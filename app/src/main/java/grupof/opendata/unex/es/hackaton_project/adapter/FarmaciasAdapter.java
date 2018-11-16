package grupof.opendata.unex.es.hackaton_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import grupof.opendata.unex.es.hackaton_project.R;
import grupof.opendata.unex.es.hackaton_project.activity.DetalleFarmaciaActivity;
import grupof.opendata.unex.es.hackaton_project.model.FarmaciaModelo;

public class FarmaciasAdapter extends RecyclerView.Adapter<FarmaciasAdapter.FarmaciasHolder> {

    private Context mContext;
    private List<FarmaciaModelo> mData = new ArrayList<>();

    public FarmaciasAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public FarmaciasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.farmacia_recycler, parent, false);
        return new FarmaciasHolder(mContext, v);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmaciasHolder holder, int position) {
        holder.bind(mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void load(List<FarmaciaModelo> fars) {
        mData.clear();
        mData = fars;
        notifyDataSetChanged();
    }

    class FarmaciasHolder extends RecyclerView.ViewHolder {

        private Context mContext;

        private TextView farmacia_name;
        private TextView farmacia_dir;
        private TextView farmacia_tlfn;

        FarmaciasHolder(Context context, View itemView) {

            super(itemView);
            mContext = context;

            // Reference every widget of the itemView
            farmacia_name = itemView.findViewById(R.id.farmacia_name);
            farmacia_dir = itemView.findViewById(R.id.farmacia_dir);
            farmacia_tlfn = itemView.findViewById(R.id.farmacia_tlfn);

        }

        void bind(final FarmaciaModelo far, int posicion) {

            // Mostrar el nombre de la farmacia en un ItemView
            farmacia_name.setText(far.getName());

            // Mostrar la direccion de la farmacia en un ItemView
            farmacia_dir.setText(far.getAddress());

            // Mostrar el telefono de la farmacia en un ItemView
            farmacia_tlfn.setText(far.getTelephone());

            // Añadir un onClickListener al itemView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Abrir la actividad detalle Farmacia
                    // TODO uncoment
                    Intent intent = new Intent(mContext, DetalleFarmaciaActivity.class);
                    intent.putExtra("farObject", far);
                    mContext.startActivity(intent);
                    Log.i("ADAPTER FARMACIA", "ABRIR DETALLE");
                }
            });

        }

    }

}
