package space.abdilazov.taskappp.ui.addFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.Objects;

import space.abdilazov.taskappp.App;
import space.abdilazov.taskappp.R;
import space.abdilazov.taskappp.databinding.FragmentAddBinding;
import space.abdilazov.taskappp.models.News;

public class AddFragment extends Fragment {

    private FragmentAddBinding binding;
    private News news;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        news = (News) requireArguments().getSerializable("news");
        if (news != null){
            binding.Et.setText(news.getTitle());
        }
        binding.sendBtn.setOnClickListener(v -> save());
    }
    private void save() {
        String text = Objects.requireNonNull(binding.Et.getText()).toString();
        Bundle bundle = new Bundle();
        if (news == null) {
            news = new News(text, System.currentTimeMillis());
            bundle.putSerializable("news", news);
            getParentFragmentManager().setFragmentResult("rk_news", bundle);
            App.getInstance().getDatabase().newsDao().insert(news);
        } else {
            news.setTitle(text);
            bundle.putSerializable("news", news);
            getParentFragmentManager().setFragmentResult("rk_news_update", bundle);
            App.getInstance().getDatabase().newsDao().update(news);
        }
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
        navController.navigateUp();
    }
}