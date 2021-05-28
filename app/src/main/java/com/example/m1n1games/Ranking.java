package com.example.m1n1games;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.m1n1games.databinding.FragmentMenuBinding;
import com.example.m1n1games.databinding.FragmentRankingBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.List;


public class Ranking extends Fragment {

    NavController navController;
    private FragmentRankingBinding binding;
    List<Post> postslist= new ArrayList<>();
    private VIewmodelranking viewmodel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentRankingBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        viewmodel = new ViewModelProvider(requireActivity()).get(VIewmodelranking.class);
        FirebaseFirestore.getInstance().collection("Jugador").orderBy("nivel")
                .addSnapshotListener((value, error) -> {
                    for (DocumentSnapshot documentSnapshot:value){
                        postslist.add(documentSnapshot.toObject(Post.class));
                    }                        viewmodel.actualizar.setValue(true);

                });
        viewmodel.actualizar.observe(getViewLifecycleOwner(), aBoolean -> {
            //DO SOMETHING EX->
            for (int i = 0; i < postslist.size(); i++) {
              //  binding.pruebadeBBDD.setText("HAS LLEGADO HASTA EL NIVEL " + postslist.get(i).nivel +"Con  " + postslist.get(i).movimientos + "Movimientos ");
            }
        });
        PushDownAnim.setPushDownAnimTo(binding.botonsalir).setScale(PushDownAnim.MODE_SCALE,0.89f)
                .setDurationPush(PushDownAnim.DEFAULT_PUSH_DURATION)
                .setDurationRelease(PushDownAnim.DEFAULT_PUSH_DURATION)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navController.navigate(R.id.action_ranking_to_menu);
                    }
                });



    }
}