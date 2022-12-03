package com.mustafa.passwordmanager.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.mustafa.passwordmanager.Adapter.TabLayoutAdapter;
import com.mustafa.passwordmanager.R;
import com.mustafa.passwordmanager.databinding.FragmentGeneratePasswordBinding;

import java.util.Objects;

public class GeneratePasswordFragment extends Fragment {

    FragmentGeneratePasswordBinding binding;
    TabLayoutAdapter tabLayoutAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGeneratePasswordBinding.inflate(inflater,container,false);

        tabLayoutAdapter = new TabLayoutAdapter(requireActivity());

        binding.viewPager2.setAdapter(tabLayoutAdapter);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.tabLayout.getTabAt(position).select();
            }
        });

        return binding.getRoot();
    }

}