package ideanity.oceans.kidslearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import ideanity.oceans.kidslearning.adapter.MainAdapter;
import ideanity.oceans.kidslearning.helpers.LessonHelper;
import ideanity.oceans.kidslearning.helpers.SQLiteDbHelper;

public class MainActivity extends AppCompatActivity implements RecyclerViewAction  {



    RecyclerView recyclerViewShape;
    RecyclerView.Adapter adapter;
    String[] elementsNames ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        int age =5  ;
        recyclerViewShape = findViewById(R.id.recycler_main);
        elementsNames = new SQLiteDbHelper(MainActivity.this).readDataLessonsByage(age);
        featuredShapes(elementsNames);
    }




    private void featuredShapes(String[] elementsNames) {
        ArrayList<LessonHelper> questionLocations = new ArrayList<>();
        int drawable;
        for (String element: elementsNames) {
            questionLocations.add(new LessonHelper(element));
        }
        adapter = new MainAdapter(questionLocations, this, this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerViewShape.setLayoutManager(gridLayoutManager);
        recyclerViewShape.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewClicked(int clickedViewId, int clickedItemPosition) {
        Intent intent = new Intent(MainActivity.this, GenericActivity.class);
        intent.putExtra("lessonName", elementsNames[clickedItemPosition]);
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