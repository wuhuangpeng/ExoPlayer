package com.google.android.exoplayer2.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.util.Util;
import java.util.Collections;

public class TestActivity extends AppCompatActivity {

    final String APP_ID = "xxx";
    final String APP_KEY = "xxx";
    final String APP_SECRET = "xxx";

    private EditText mETUrl;
    private Switch mSwitchP2p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initView();
    }

    private void initView() {
        mETUrl = findViewById(R.id.et_url);
        mETUrl.setText("https://5000-kimmy.r.qlivecloud.com/live/5000_lhls_test.m3u8?bizid=5000&request_lhls=1");
        mSwitchP2p = findViewById(R.id.switch_p2p);

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

        MediaItem.Builder mediaItem = new MediaItem.Builder();
        String adaptiveMimeType = Util.getAdaptiveMimeTypeForContentType(Util.inferContentType(uri));
        MediaItem.ClippingConfiguration.Builder clippingConfiguration =
            new MediaItem.ClippingConfiguration.Builder();
        mediaItem
            .setUri(uri)
            .setMediaMetadata(new MediaMetadata.Builder().setTitle(url).build())
            .setMimeType(adaptiveMimeType)
            .setClippingConfiguration(clippingConfiguration.build());

        Intent intent = new Intent(this, PlayerActivity.class);
        IntentUtil.addToIntent(Collections.singletonList(mediaItem.build()), intent);
        startActivity(intent);
    }
}
