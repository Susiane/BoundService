package br.com.teste.boundservice;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Susiane on 28/05/2016.
 */
public class TesteFragment extends Fragment {
    private static final String PREF_NAME = "MainActivityPreferences";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View freg_teste = inflater.inflate(R.layout.fragmet_teste,container,false);

        SharedPreferences sp1 = getActivity().getSharedPreferences(PREF_NAME,0);
        SharedPreferences.Editor  editor = sp1.edit();
        editor.putString("string_teste","teste");
        editor.commit();
        Log.d("TAG","count1 :"+sp1.getInt("count_1",0));
        Log.d("TAG","string_teste :"+sp1.getString("string_teste",""));

        return freg_teste;
    }
}
