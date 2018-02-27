package com.xiaoe.assetsdemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rlv);

        CityAdapter cityAdapter = new CityAdapter(getAllCities());
        recyclerView.setAdapter(cityAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
//        Android 关于RecyclerView嵌套Scrollview滑动卡顿问题,最主要的是点setLayoutManager重写他的canScrollVertically方法，返回false就行了。
//        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1){
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        });



    }



    public List<City> getAllCities(){
        SQLiteDatabase db = DBManager.openDataBases(MainActivity.this);
        Cursor cursor = db.rawQuery("select * from city", null);
        List<City> result = new ArrayList<>();
        City city;
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
            city = new City(name, pinyin);
            result.add(city);
        }
        cursor.close();
        db.close();
        Collections.sort(result, new CityComparator());
        return result;
    }


    /**
     * sort by a-z
     */
    private class CityComparator implements Comparator<City> {
        @Override
        public int compare(City lhs, City rhs) {
            String a = lhs.getPinyin().substring(0, 1);
            String b = rhs.getPinyin().substring(0, 1);
            return a.compareTo(b);
        }
    }



}
