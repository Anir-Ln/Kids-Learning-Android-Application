package com.anirayoubil.kidsLearning;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import com.anirayoubil.kidsLearning.adapter.MainAdapter;
import com.anirayoubil.kidsLearning.data.models.Profile;
import com.anirayoubil.kidsLearning.helpers.LessonHelper;
import com.anirayoubil.kidsLearning.helpers.SQLiteDbHelper;

public class ProfilesActivity extends AppCompatActivity implements RecyclerViewAction{

    RecyclerView recyclerViewShape;
    RecyclerView.Adapter adapter;
    List<Profile> profiles;
    Button createProfileButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profiles);

        createProfileButton = (Button) findViewById(R.id.create_profile_btn);
        createProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilesActivity.this, CreateProfileActivity.class);
            startActivity(intent);
            finish();
        });

        recyclerViewShape = findViewById(R.id.recycler_element);
//        elementsNames = new String[] {"shapes", "daysOfWeek", "numbers", "alphabets"};
        profiles = new SQLiteDbHelper(ProfilesActivity.this).getProfiles();

        featuredShapes(profiles);
    }




    private void featuredShapes(List<Profile> profiles) {
        ArrayList<LessonHelper> questionLocations = new ArrayList<>();
        int drawable;
        for (Profile profile: profiles) {
            questionLocations.add(new LessonHelper(profile.getName()));
        }
        adapter = new MainAdapter(questionLocations, this, this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerViewShape.setLayoutManager(gridLayoutManager);
        recyclerViewShape.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewClicked(int clickedViewId, int clickedItemPosition) {
        Intent intent = new Intent(ProfilesActivity.this, MainActivity.class);
        intent.putExtra("profileName", profiles.get(clickedItemPosition).getName());
        intent.putExtra("profileAge", profiles.get(clickedItemPosition).getAge());
        startActivity(intent);
    }

    @Override
    public void onViewLongClicked(int clickedViewId, int clickedItemPosition) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}