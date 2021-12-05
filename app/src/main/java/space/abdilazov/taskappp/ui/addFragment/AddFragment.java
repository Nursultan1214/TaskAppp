package space.abdilazov.taskappp.ui.addFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import space.abdilazov.taskappp.R;
import space.abdilazov.taskappp.databinding.FragmentAddBinding;
import space.abdilazov.taskappp.models.News;

public class AddFragment extends Fragment {

    private FragmentAddBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             save();
            }
        });
    }
    private void save() {
        String text = binding.Et.getText().toString();
        News news = new News(text,System.currentTimeMillis());
        Bundle bundle = new Bundle();
        bundle.putSerializable("news",news);
        getParentFragmentManager().setFragmentResult("rk_news",bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
        navController.navigateUp();
    }
}