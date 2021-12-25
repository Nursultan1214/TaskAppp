package space.abdilazov.taskappp.ui.onBoard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import space.abdilazov.taskappp.Prefs;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.viewPager.setAdapter(adapter);
        binding.dots.setViewPager2(binding.viewPager);
        adapter.setClick(() -> {
            new Prefs(requireContext()).saveBoardState();
            close();
        });
        binding.buttonSkip.setOnClickListener(v -> close());
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 2) {
                    binding.buttonSkip.setVisibility(View.GONE);
                } else {
                    binding.buttonSkip.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigateUp();
    }
}