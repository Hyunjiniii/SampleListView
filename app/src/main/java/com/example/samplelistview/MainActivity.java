package com.example.samplelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SingerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);  // 리스트뷰 객체 생성

        adapter = new SingerAdapter();  // 어댑터 객체 생성

        // 어댑터에 각 아이템 데이터 추가
        adapter.addItem(new SingerItem("배터리 20%", "battery 20%", 20, R.drawable.ic_battery_20_black_24dp));
        adapter.addItem(new SingerItem("배터리 50%", "battery 50%", 50, R.drawable.ic_battery_50_black_24dp));
        adapter.addItem(new SingerItem("배터리 80%", "battery 80%", 80, R.drawable.ic_battery_80_black_24dp));
        adapter.addItem(new SingerItem("배터리 100%", "battery 100%", 100, R.drawable.ic_battery_full_black_24dp));

        listView.setAdapter(adapter);  // 리스트뷰에 어댑터 객체 설정

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    class SingerAdapter extends BaseAdapter {
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        public void addItem(SingerItem item) {
            items.add(item);
        }

        // Adapter에서 관리하는 아이템 수 리턴
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // item에 표시할 뷰 리턴
        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            SingerItemView view = new SingerItemView(getApplicationContext());
            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setAge(item.getAge());
            view.setImage(item.getResId());
            return view;
        }
    }
}
