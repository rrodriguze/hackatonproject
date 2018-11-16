package grupof.opendata.unex.es.hackaton_project.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import grupof.opendata.unex.es.hackaton_project.R;
import grupof.opendata.unex.es.hackaton_project.adapter.FarmaciasAdapter;
import grupof.opendata.unex.es.hackaton_project.model.FarmaciaModelo;

public class FarmaciasFragment extends Fragment {

    private View v;
    private FarmaciasAdapter mAdapter;
    private RecyclerView mRecyclerView;


    public FarmaciasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_farmacias, container, false);

        bindView();

        // TODO Descomentar cuando carga por ROOM
        loadItems();

        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        loadItems();
    }

    private void bindView() {
        mRecyclerView = v.findViewById(R.id.farmacias_recycler_view);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayout.VERTICAL));
        mAdapter = new FarmaciasAdapter(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadItems() {
        List<FarmaciaModelo> fars = new FarmaciaModelo().getAll(getActivity().getApplicationContext());
        mAdapter.load(fars);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
