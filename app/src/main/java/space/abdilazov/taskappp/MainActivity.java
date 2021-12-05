package space.abdilazov.taskappp;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

import space.abdilazov.taskappp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.navView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull  NavController controller, @NonNull  NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.navigation_home ||
                destination.getId() == R.id.navigation_dashboard || destination.getId() == R.id.navigation_notifications ||
                destination.getId() == R.id.navigation_profile){
                    binding.navView.setVisibility(View.VISIBLE);
                }else {
                    binding.navView.setVisibility(View.GONE);
                }
                if (destination.getId() == R.id.boardFragment){
                    getSupportActionBar().hide();
                }else {
                    getSupportActionBar().show();
                }
            }
        });
    }
}