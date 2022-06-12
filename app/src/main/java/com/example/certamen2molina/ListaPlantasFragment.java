package com.example.certamen2molina;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
/* Valentina Molina Jara
    19.987.243-5 */
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaPlantasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaPlantasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaPlantasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaPlantasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaPlantasFragment newInstance(String param1, String param2) {
        ListaPlantasFragment fragment = new ListaPlantasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View listaP;
    List listaPlanta = new ArrayList<classPlanta>();
    ArrayAdapter adapter;
    BDMolina BDM;
    ListView listView_planta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listaP = inflater.inflate(R.layout.fragment_lista_plantas, container, false);

        listView_planta = listaP.findViewById(R.id.listPlant);
        BDM = new BDMolina(getActivity());
        listaPlanta = BDM.listarclassPlanta();
        if (listaPlanta != null) {
            adapter = new ArrayAdapterPlanta(getActivity(), 0,listaPlanta);;
            listView_planta.setAdapter(adapter);
        }
        return  listaP;
    }
}