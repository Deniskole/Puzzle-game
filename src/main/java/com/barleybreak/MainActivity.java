package com.barleybreak;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.Collections;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {


    private static LinkedList<Integer> numbers;
    private static LinkedList<Integer> cleanNumbers;
    private RecyclerView recyclerView;
    private static NumberAdapter numberAdapter;

    public static final int EMPTY = 999;
    public int PLACE_SIZE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intentStart = getIntent();
        PLACE_SIZE = getIntent().getIntExtra("placeSize", 3);


        numbers = new LinkedList<>();
        cleanNumbers = new LinkedList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, PLACE_SIZE));

        makeRandomNumbers();

        numberAdapter = new NumberAdapter();
        numberAdapter.setListNum(numbers);
        numberAdapter.setContext(this);
        recyclerView.setAdapter(numberAdapter);

        numberAdapter.setOnNumberClickListener(new NumberAdapter.OnNumberClickListener() {
            @Override
            public void onNumberClick(int i) {
                int temp = 0;
                if (i != numbers.size() - 1) {
                    if (numbers.get(i + 1).equals(EMPTY)) {
                        temp = numbers.get(i);
                        numbers.set(i, EMPTY);
                        numbers.set(i + 1, temp);
                    }
                }
                if (i != 0) {
                    if (numbers.get(i - 1).equals(EMPTY)) {
                        temp = numbers.get(i);
                        numbers.set(i, EMPTY);
                        numbers.set(i - 1, temp);
                    }
                }
                if (i < numbers.size() - PLACE_SIZE) {
                    if (numbers.get(i + PLACE_SIZE).equals(EMPTY)) {
                        temp = numbers.get(i);
                        numbers.set(i, EMPTY);
                        numbers.set(i + PLACE_SIZE, temp);
                    }
                }
                if (i >= PLACE_SIZE) {
                    if (numbers.get(i - PLACE_SIZE).equals(EMPTY)) {
                        temp = numbers.get(i);
                        numbers.set(i, EMPTY);
                        numbers.set(i - PLACE_SIZE, temp);
                    }
                }
                recyclerView.setAdapter(numberAdapter);
                numberAdapter.notifyDataSetChanged();

                checkWinGame();
            }
        });
    }

    public void checkWinGame() {
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();

        for (int aNum : numbers) {
            a.append(aNum);
        }

        for (int bNum : cleanNumbers) {
            b.append(bNum);
        }

        String str1 = a.toString();
        String str2 = b.toString();

        if (str1.equals(str2)) {
            Toast.makeText(this, "Вы выиграли!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PlayAgain.class);
            startActivity(intent);
        }
    }


    public void makeRandomNumbers() {
        numbers.clear();
        for (int i = 0; i <= PLACE_SIZE * PLACE_SIZE - 2; i++) {
            numbers.add(i);
            cleanNumbers.add(i);
        }
        numbers.add(EMPTY);
        cleanNumbers.add(EMPTY);
        Collections.shuffle(numbers);
    }

    public void newGame(View view) {
        Intent intent = new Intent(this, PlayAgain.class);
        startActivity(intent);
    }
}
