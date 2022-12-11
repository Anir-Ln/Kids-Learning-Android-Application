package ideanity.oceans.kidslearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout color, number, alphabet, week, shape;
    LinearLayout poem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        color = findViewById(R.id.color);
        number = findViewById(R.id.number);
        alphabet = findViewById(R.id.alphabet);
        week = findViewById(R.id.week);
        shape = findViewById(R.id.shape);
        poem = findViewById(R.id.lil_poem);

//        String[] lessonsNames = new String[] {"numbers", "shapes",,,,};


        number.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GenericActivity.class);
            intent.putExtra("lessonName", "numbers");
            startActivity(intent);
        });
//
        alphabet.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GenericActivity.class);
            intent.putExtra("lessonName", "alphabets");
            startActivity(intent);            });

        week.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GenericActivity.class);
            intent.putExtra("lessonName", "week");
            startActivity(intent);
        });

        shape.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GenericActivity.class);
            intent.putExtra("lessonName", "shapes");
            startActivity(intent);
        });
    }

}