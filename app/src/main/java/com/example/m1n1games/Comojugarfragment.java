package com.example.m1n1games;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.m1n1games.databinding.FragmentComojugarfragmentBinding;
import com.example.m1n1games.databinding.FragmentMenuBinding;
import com.thekhaeng.pushdownanim.PushDownAnim;


public class Comojugarfragment extends Fragment {
    NavController navController;
    private FragmentComojugarfragmentBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentComojugarfragmentBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        PushDownAnim.setPushDownAnimTo(binding.botonsalir).setScale(PushDownAnim.MODE_SCALE,0.89f)
                .setDurationPush(PushDownAnim.DEFAULT_PUSH_DURATION)
                .setDurationRelease(PushDownAnim.DEFAULT_PUSH_DURATION)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navController.navigate(R.id.action_comojugarfragment_to_menuLightOutFragment);
                    }
                });
    }
}