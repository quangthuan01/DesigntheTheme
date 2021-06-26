package com.example.thietkegiaodien;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.thietkegiaodien.model.Song;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MusicFragment extends Fragment {
    TextView txtTitle, txtTimeSong, txtTimeTotal;
    SeekBar seekBarSong;
    ImageButton btnPlay, btnPrev, btnNext, btnStop;
    ImageView dvd;
    ArrayList<Song> arraySong;
    int position = 0;
    MediaPlayer mediaPlayer;
    Animation animation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mview = (ViewGroup) inflater.inflate(R.layout.fragment_music, container, false);

        //anhsa
        txtTitle = mview.findViewById(R.id.tv_Title);
        txtTimeSong = mview.findViewById(R.id.textViewTimeSong);
        txtTimeTotal = mview.findViewById(R.id.textViewTimeTotal);
        seekBarSong = mview.findViewById(R.id.seekbar);
        btnPrev = mview.findViewById(R.id.imgBtn_prev);
        btnPlay = mview.findViewById(R.id.imgBtn_pLay);
        btnStop = mview.findViewById(R.id.imgBtn_sTop);
        btnNext = mview.findViewById(R.id.imgBtn_next);
        dvd =mview.findViewById(R.id.dvd);


        //add song
        addSong();

        animation = AnimationUtils.loadAnimation(getActivity(),R.anim.dvd_rotate);

        KhoitaoMediaPlayer();

        //next music
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position > arraySong.size() - 1) {
                    position = 0;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();

                }
                KhoitaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                setTimeTotal();
                upDateTimeSong();
            }
        });
        //prev music
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0) {
                    position = arraySong.size() - 1;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();

                }
                KhoitaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                setTimeTotal();
                upDateTimeSong();
            }
        });
        //stop music
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.play);
                KhoitaoMediaPlayer();
            }
        });

        //play music
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    //neu dang phat -> pause ->doi hinh play
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                } else {
                    //neu dang ngung -> play -> doi hinh pause

                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause);
                }
                setTimeTotal();
                upDateTimeSong();
                dvd.startAnimation(animation);
            }
        });
        //bat su kien click seekbarsong
        seekBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBarSong.getProgress());
            }
        });

        return mview;
    }

    private void addSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Cảm Xúc Yêu Xa", R.raw.camxucyeuxa));
        arraySong.add(new Song("Chờ Ngày Tuyết Tan", R.raw.chongaytuyettan));
        arraySong.add(new Song("Đêm Trăng Tình Yêu", R.raw.demtrangtinhteuhaibang));
        arraySong.add(new Song("Giữ Em Đi", R.raw.giuemdithuychi));
    }

    private void KhoitaoMediaPlayer() {
        mediaPlayer = MediaPlayer.create(getActivity(), arraySong.get(position).getFile());
        txtTitle.setText(arraySong.get(position).getTitle());

    }

    private void setTimeTotal() {
        SimpleDateFormat dinhdangGio = new SimpleDateFormat("mm:ss");

        txtTimeTotal.setText(dinhdangGio.format(mediaPlayer.getDuration()));

        //gan max cua seekbarSong = mediaPlayer.getDuration()
        seekBarSong.setMax(mediaPlayer.getDuration());
    }

    private void upDateTimeSong() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                //update progress seekbarSong
                seekBarSong.setProgress(mediaPlayer.getCurrentPosition());
                //kiem tra thoi gian bai hat->neu ket thuc  -> next
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > arraySong.size() - 1) {
                            position = 0;
                        }
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();

                        }
                        KhoitaoMediaPlayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.pause);
                        setTimeTotal();
                        upDateTimeSong();
                    }
                });

                handler.postDelayed(this, 500);
            }
        }, 100);
    }
}
