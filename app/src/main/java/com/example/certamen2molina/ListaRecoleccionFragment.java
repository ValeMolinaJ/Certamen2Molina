package com.example.certamen2molina;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaRecoleccionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaRecoleccionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaRecoleccionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaRecoleccionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaRecoleccionFragment newInstance(String param1, String param2) {
        ListaRecoleccionFragment fragment = new ListaRecoleccionFragment();
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

    View view;
    List<classCientifico> lista_Cientifico;
    List lista_rec = new ArrayList<classRecoleccion>();
    ArrayAdapter adapt;
    ArrayAdapter<classCientifico> adapterC;
    ArrayAdapter<String> adapterEmpty;
    String[] vaciocient = {"No existen cientificos en la base de datos"};
    BDMolina BDM;
    ListView listV;
    Spinner sp;
    String rutC;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista_recoleccion, container, false);

        sp = view.findViewById(R.id.spRecoRut);
        listV = view.findViewById(R.id.listR);
        BDM = new BDMolina(getActivity());

        lista_Cientifico = BDM.listarclassCientificos();
        adapt = new ArrayAdapter<>(getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, lista_Cientifico);
        adapterEmpty = new ArrayAdapter<>(getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, vaciocient);

        if (lista_Cientifico != null) {
            sp.setAdapter(adapt);
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    rutC = ((classCientifico) adapterView.getSelectedItem()).getRut();
                    lista_rec = BDM.listarRecoleccionRut(rutC);
                    adapt = new ArrayAdapterRecoleccion(getActivity(), 0, lista_rec);
                    listV.setAdapter(adapt);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        } else {
            sp.setAdapter(adapterEmpty);
        }
        return view;
    }
}