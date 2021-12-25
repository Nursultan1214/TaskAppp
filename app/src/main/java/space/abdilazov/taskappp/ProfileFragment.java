package space.abdilazov.taskappp;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import space.abdilazov.taskappp.databinding.FragmentNewsBinding;

public class ProfileFragment extends Fragment {

    private FragmentNewsBinding binding;
    private Prefs prefs;
    private boolean trueUpdate = false;
    private boolean trueUpdate2 = false;
    private final ActivityResultLauncher<String> launchSomeActivity = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if (result == null) {
                trueUpdate = false;
                Glide.with(requireContext()).load(R.drawable.profile).circleCrop().into(binding.imgProfile);
            } else {
                Glide.with(requireContext()).load(result).circleCrop().into(binding.imgProfile);
                prefs.saveImage(result);
                trueUpdate2 = true;

            }
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater);
        prefs = new Prefs(requireContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.imgProfile.setOnClickListener(v -> {
            alertLogic();

        });
        Name();
        saver();
    }

    private void Name() {
        binding.edName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                prefs.saveName(s.toString());
            }
        });
    }

    private void alertLogic() {
        if (trueUpdate && trueUpdate2) {
            AlertDialog.Builder alert = new AlertDialog.Builder(requireContext());
            alert.setPositiveButton("Заменить", (dialog, which) -> launchSomeActivity.launch("image/*")).setNegativeButton("Удалить", (dialog, which) -> {
                Glide.with(requireActivity()).load(R.drawable.profile).circleCrop().into(binding.imgProfile);
                trueUpdate2 = false;
            }).show();
        } else {
            launchSomeActivity.launch("image/*");
            trueUpdate = true;
        }
    }

    private void saver() {
        String image = prefs.getImage();
        Glide.with(requireActivity()).load(image).circleCrop().into(binding.imgProfile);
        String name = prefs.getName();
        binding.edName.setText(name);
    }
}