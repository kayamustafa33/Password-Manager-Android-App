package com.mustafa.passwordmanager.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mustafa.passwordmanager.Fragment.CreateFragment;
import com.mustafa.passwordmanager.Fragment.GenerateFragment;

public class TabLayoutAdapter extends FragmentStateAdapter {
    public TabLayoutAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new CreateFragment();
            case 0:
            default:
                return new GenerateFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
