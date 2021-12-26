package space.abdilazov.taskappp.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.List;

import space.abdilazov.taskappp.App;
import space.abdilazov.taskappp.R;
import space.abdilazov.taskappp.databinding.FragmentHomeBinding;
import space.abdilazov.taskappp.models.News;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NewsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        adapter = new NewsAdapter();
        List<News> list = App.getInstance().getDatabase().newsDao().getAll();
        adapter.addItems(list);
        adapter.setOnClick(new NewsAdapter.OnClick() {
            @Override
            public void onLongClick(int position) {
                AlertDialog.Builder alert = new AlertDialog.Builder(requireContext());
                alert.setTitle("Внимание!").setMessage("Вы точно хотите удалить текст?");
                alert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        News news = adapter.getItem(position);
                        App.getInstance().getDatabase().newsDao().delete(news);
                        adapter.removeItem(position);
                    }
                }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }

            @Override
            public void onClick(int pos) {
                News news = adapter.getItem(pos);
                openFragment(news);
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("rk_news", getViewLifecycleOwner(), (requestKey, result) -> {
            News news = (News) result.getSerializable("news");
            Log.e("TAG", "onFragmentResult: " + news.getTitle());
            adapter.addItem(news);
        });
        getParentFragmentManager().setFragmentResultListener("rk_news_update", getViewLifecycleOwner(), (requestKey, result) -> {
            News news = (News) result.getSerializable("news");
            Log.e("TAG", "onFragmentResult: " + news.getTitle());
            adapter.updateItem(news);
        });
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(null);
            }
        });
        initList();
    }

    private void initList() {
        binding.recycler.setAdapter(adapter);
    }

    private void openFragment(News news) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("news", news);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.addFragment, bundle);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.action_bar_btn,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_btn){
        getSortedAlphabet();
        }
        return super.onOptionsItemSelected(item);

    }

    private void getSortedAlphabet() {
        List<News> list = App.getInstance().getDatabase().newsDao().getSortedMethod();
        adapter.addItems(list);
    }
}