package techmarket.uno.russian_rullet;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {//AppCompatActivity - с панелью верхней!!!!!
    private SoundPool sounds;
    private int sound_shot;
    private int sound_shot_false;
    private int sound_baraban;
    private ImageView blood_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSoundPool();
        loadSounds();
        blood_image = findViewById(R.id.image_blood);
    }
    protected void createSoundPool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            createNewSoundPool();
        } else {
            createOldSoundPool();
        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)// если версия выше чем
    protected void createNewSoundPool(){
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        sounds = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    @SuppressWarnings("deprecation") //важно в новых версиях
    protected void createOldSoundPool()
    {
        sounds = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
    }

    private void loadSounds()
    {
        sound_shot = sounds.load(this,R.raw.revolver_shot, 1);
        sound_shot_false = sounds.load(this,R.raw.gun_false, 1);
        sound_baraban = sounds.load(this,R.raw.revolver_baraban, 1);

    }

    public void onShot(View view)
    {
        sounds.play(sound_shot,1.0f,1.0f,1,0,1);
        blood_image.setVisibility(View.VISIBLE);
    }

    public void onShotFalse(View view)
    {
        sounds.play(sound_shot_false,1.0f,1.0f,1,0,1);
    }

    public void onBaraban(View view)
    {
        sounds.play(sound_baraban,1.0f,1.0f,1,0,1);
        blood_image.setVisibility(View.GONE);
    }
}