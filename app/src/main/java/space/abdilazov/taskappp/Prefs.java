package space.abdilazov.taskappp;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Prefs {

    private final SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveBoardState() {
        preferences.edit().putBoolean("isShown", true).apply();
    }

    public boolean isBoardShown() {
        return preferences.getBoolean("isShown", false);
    }
    public void saveImage(Uri image){
        preferences.edit().putString("image",image.toString()).apply();
    }
    public String getImage(){
        return preferences.getString("image","");
    }
    public void saveName (String name) {
        preferences.edit().putString("name", name).apply();
    }
    public String getName(){
        return preferences.getString("name","");
    }
}
