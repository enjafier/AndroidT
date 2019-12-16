package tugas.besar.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tugas.besar.R;
import tugas.besar.SharedPreference;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {
    SharedPreference sharedPrefManager;
    TextView hasil;
    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        hasil = view.findViewById(R.id.hasil);
        sharedPrefManager = new SharedPreference(getActivity());
        hasil.setText(sharedPrefManager.getSpRESULT());
        return view;
    }

}
