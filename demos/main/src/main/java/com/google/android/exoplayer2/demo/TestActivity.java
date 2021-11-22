package com.google.android.exoplayer2.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import java.util.Collections;

public class TestActivity extends AppCompatActivity {

  private EditText mETUrl;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test);

    initView();
  }

  private void initView() {
    mETUrl = findViewById(R.id.et_url);
    mETUrl.setText("http://68789.liveplay.myqcloud.com/live/test_1637290786.flv");
    Button btnPlay = findViewById(R.id.btn_play);

    btnPlay.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        play(mETUrl.getText().toString());

      }
    });
  }

  private void play(String url) {
    Uri uri = Uri.parse(url);

    Intent intent = new Intent(this, PlayerActivity.class);

    MediaItem.Builder mediaItem = new MediaItem.Builder();
    mediaItem
        .setUri(uri)
        .setMediaMetadata(new MediaMetadata.Builder().setTitle("test video").build())
        .setMimeType(null);
    IntentUtil.addToIntent(Collections.singletonList(mediaItem.build()), intent);
    startActivity(intent);
  }
}
