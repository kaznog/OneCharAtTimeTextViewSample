package com.kaznog.as.onecharattimetextviewsample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.os.Handler;

public class MainActivity extends Activity {
    private TextView textView;
    private String base_text = "文字列を一文字ずつ出力するテスト";
    private int MessageSequence = 0;
    private String put_word = "";
    private String put_text = "";

    private static final int TIMEOUT_MESSAGE = 1;
    private static final int INTERVAL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.message_view);

        message_handler.sendEmptyMessage(TIMEOUT_MESSAGE);
    }

    private Handler message_handler = new Handler() {

        @Override
        public void dispatchMessage(Message msg) {
            if (msg.what == TIMEOUT_MESSAGE) {
                // 文字列を配列に１文字ずつセット
                char data[] = base_text.toCharArray();
                // キャラ配列数を取得
                int arr_num = data.length;
                if (MessageSequence < arr_num) {
                    put_word = String.valueOf(data[MessageSequence]);
                    put_text = put_text + put_word;

                    textView.setText(put_text);
                    MessageSequence++;
                    if (MessageSequence < arr_num) {
                        message_handler.sendEmptyMessageDelayed(TIMEOUT_MESSAGE, INTERVAL * 1000);
                    }
                }
            } else {
                super.dispatchMessage(msg);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
