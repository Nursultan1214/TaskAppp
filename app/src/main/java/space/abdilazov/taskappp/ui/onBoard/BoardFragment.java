package space.abdilazov.taskappp.ui.onBoard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import space.abdilazov.taskappp.R;
import space.abdilazov.taskappp.databinding.FragmentBoardBinding;
import space.abdilazov.taskappp.ui.BoardAdapter;

public class BoardFragment extends Fragment {

    private FragmentBoardBinding binding;
    private BoardAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new BoardAdapter();
        adapter.setClick(() -> {
            NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_home);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.viewPager.setAdapter(adapter);
        binding.dots.setViewPager2(binding.viewPager);
        binding.buttonSkip.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_home);
        });
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 2){
                    binding.buttonSkip.setVisibility(View.GONE);
                }else {
                    binding.buttonSkip.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}