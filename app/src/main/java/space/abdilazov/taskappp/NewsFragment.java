package space.abdilazov.taskappp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import space.abdilazov.taskappp.databinding.FragmentNewsBinding;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private boolean trueUpdate = false;
    private boolean trueUpdate2 = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            binding.imgProfile.setOnClickListener(v -> {
                if (trueUpdate && trueUpdate2){
                    AlertDialog.Builder alert = new AlertDialog.Builder(requireContext());
                    alert.setPositiveButton("Заменить", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            launchSomeActivity.launch("image/*");
                        }
                    }).setNegativeButton("Удалить", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Glide.with(requireActivity()).load(R.drawable.profile).circleCrop().into(binding.imgProfile);
                            trueUpdate2 = false;
                        }
                    }).show();
                }else {
                    launchSomeActivity.launch("image/*");
                    trueUpdate = true;
                }
            });

        }


    public ActivityResultLauncher<String> launchSomeActivity = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if (result == null){
                trueUpdate = false;
            }else {
                Glide.with(requireContext()).load(result).circleCrop().into(binding.imgProfile);
                trueUpdate2 = true;
            }
        }
    });
}