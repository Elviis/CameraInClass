package butterycontent.camerainclass;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static android.R.attr.x;

public class MainActivity extends AppCompatActivity {
    static final int TAKE_AVATAR_CAMERA_REQUEST = 1;
    boolean keepGoing = true;
    ImageButton avatarButton;
    Timer timer, timer2, timer3, timer4;
    TimerTask timerTask, timerTask2, timerTask3, timerTask4;
    final android.os.Handler handler = new android.os.Handler();
    final android.os.Handler handler2 = new android.os.Handler();
    final android.os.Handler handler3 = new android.os.Handler();
    final android.os.Handler handler4 = new android.os.Handler();



    public Button upButton, downButton, leftButton, rightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAvatar();

    }

    private void initAvatar() {
        avatarButton = (ImageButton) findViewById(R.id.imgBtnAvatar);
        avatarButton.setOnClickListener(new ChooseCameraListener());

        upButton = (Button) findViewById(R.id.btnUp);

        downButton = (Button) findViewById(R.id.btnDown);
        leftButton = (Button) findViewById(R.id.btnLeft);
        rightButton = (Button) findViewById(R.id.btnRight);


//        if (settings.contains(SETTINGS_PREFS_AVATAR)){
//            String avatarUri = settings.getString(SETTINGS_PREFS_AVATAR,"");
//
//            if(avatarUri != ""){
//                Uri imageUri = Uri.parse(avatarUri);
//                avatarButton.setImageURI(imageUri);
//            }else{
//                avatarButton.setImageResource(R.drawable.avatar);
//            }
//        }else{
//            avatarButton.setImageResource(R.drawable.avatar);
//        }

    }

    public void moveUp(View view) {

        if (avatarButton.getScrollY() > 210) {

        } else {
            avatarButton.scrollBy(0, 20);
        }

    }

    public void moveDown(View view) {
        if (avatarButton.getScrollY() < -210) {

        } else {
            avatarButton.scrollBy(0, -20);
        }

    }

    public void moveLeft(View view) {

        if (avatarButton.getScrollX() > 420) {

        } else {
            avatarButton.scrollBy(20, 0);
        }

    }

    public void moveRight(View view) {
        if (avatarButton.getScrollX() < -420) {

        } else {
            avatarButton.scrollBy(-1, 0);
        }

    }

    public class ChooseCameraListener implements View.OnClickListener {
        ImageButton avatarButton = (ImageButton) findViewById(R.id.imgBtnAvatar);

        @Override
        public void onClick(View arg0) {
            Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(Intent.createChooser(pictureIntent, "Take your Photo"), TAKE_AVATAR_CAMERA_REQUEST);


        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_AVATAR_CAMERA_REQUEST:
                if (resultCode == Activity.RESULT_CANCELED) {
                    //avatar camera mode was cancelled
                } else if (resultCode == Activity.RESULT_OK) {
                    //avatar camera executed ok

                    Bitmap camearPic = (Bitmap) data.getExtras().get("data");
                    if (camearPic != null) {
                        try {
                            saveAvatar(camearPic);
                        } catch (Exception e) {
                            //error saving the image
                        }
                    }

                }
        }
    }

    private void saveAvatar(Bitmap avatar) {
        String avatarFilename = "avatar.jpg";

        try {
            avatar.compress(Bitmap.CompressFormat.JPEG, 100, openFileOutput(avatarFilename, MODE_PRIVATE));
            Uri avatarUri = Uri.fromFile(new File(this.getFilesDir(), avatarFilename));
            ImageButton avatarButton = (ImageButton) findViewById(R.id.imgBtnAvatar);
            avatarButton.setImageURI(null);
            avatarButton.setImageURI(avatarUri);

            //save to shared preferences
//            SharedPreferences settings = this.getSharedPreferences(avatarFilename,);
//            SharedPreferences.Editor editor = settings.edit();

            // editor.putString(SETTINGS_PREFS_AVATAR,avatarUri.getPath());
//            editor.commit();


        } catch (Exception e) {

        }

    }

    public void stopMoveLots(View view){

        timer.cancel();

    }
    public void moveLots2(){

        timer2 = new Timer();
        initTimerTask2();
        timer2.schedule(timerTask2,1000,237);

    }

    public void moveLots3(){

        timer3 = new Timer();
        initTimerTask3();
        timer3.schedule(timerTask3,1000,237);

    }

    public void moveLots4(){

        timer4 = new Timer();
        initTimerTask4();
        timer4.schedule(timerTask4,1000,237);

    }




    public void moveLots(View view) {


      moveAlot();

    }

    public void moveAlot() {

        timer = new Timer();
        initTimerTask1();
        timer.schedule(timerTask,1000,137);

    }

    public void moveLotsRepeat() {

        timer = new Timer();
        initTimerTask1();
        timer.schedule(timerTask,1000,237);

    }

    public void initTimerTask1(){

        timerTask = new TimerTask(){
                public void run(){

                    handler.post(new Runnable(){

                        public void run() {


                                if (avatarButton.getScrollX() < -420) {

                                    moveLots2();

                                } else {
                                    avatarButton.scrollBy(-20, 0);
                                }
                        }
                    });





                }
        };
    }

    public void initTimerTask2(){

        timerTask2 = new TimerTask(){
            public void run(){

                handler2.post(new Runnable(){

                    public void run() {


                        if (avatarButton.getScrollY() < -210) {

                                   moveLots3();

                        } else {
                            avatarButton.scrollBy(0, -20);
                        }
                    }
                });
            }
        };
    }

    public void initTimerTask3(){

        timerTask3 = new TimerTask(){
            public void run(){

                handler3.post(new Runnable(){

                    public void run() {


                        if (avatarButton.getScrollX() > 420) {
                                moveLots4();
                        } else {
                            avatarButton.scrollBy(20, 0);
                        }
                    }
                });
            }
        };
    }

    public void initTimerTask4(){

        timerTask4 = new TimerTask(){
            public void run(){

                handler4.post(new Runnable(){

                    public void run() {


                        if (avatarButton.getScrollY() > 210) {

                        } else {
                            avatarButton.scrollBy(0, 20);
                        }
                    }
                });
            }
        };
    }


    public void moveImg(View view) throws InterruptedException {


    }


}

//    if (avatarButton.getScrollY() < -210){
//
//        if (avatarButton.getScrollX() > 420){
//
//            if (avatarButton.getScrollY() > 210){
//
//            }else{
//                avatarButton.scrollBy(0,20);
//            }
//
//
//        }else{
//            avatarButton.scrollBy(20,0);
//        }
//
//    }else{
//        avatarButton.scrollBy(0,-20);
//    }


