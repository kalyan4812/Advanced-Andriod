package com.example.advancedandriod_1.TOAST_LIBRARY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.advancedandriod_1.R;

public class Toasty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toasty);
    }
    public void showToast(View v) {
        switch (v.getId()) {
            case R.id.button_error:

                es.dmoral.toasty.Toasty.error(this, "This is an error Toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_success:
                es.dmoral.toasty.Toasty.success(this, "This is a success Toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_info:
                es.dmoral.toasty.Toasty.info(this, "This is an info Toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_warning:
                es.dmoral.toasty.Toasty.warning(this, "This is a warning Toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_normal:
                es.dmoral.toasty.Toasty.normal(this, "This is a normal Toast", Toast.LENGTH_SHORT, ContextCompat.getDrawable(this, R.drawable.cube)).show();
                break;
        }
    }
    //You can also create your custom Toasts with the custom() method:
    //
    //Toasty.custom(yourContext, "I'm a custom Toast", yourIconDrawable, tintColor, duration, withIcon,
    //shouldTint).show();

   /* This step is optional, but if you want you can configure some Toasty parameters. Place this anywhere in your app:

Toasty.Config.getInstance()
    .tintIcon(boolean tintIcon) // optional (apply textColor also to the icon)
    .setToastTypeface(@NonNull Typeface typeface) // optional
    .setTextSize(int sizeInSp) // optional
    .allowQueue(boolean allowQueue) // optional (prevents several Toastys from queuing)
    .apply(); // required
You can reset the configuration by using reset() method:

Toasty.Config.reset();  */











}
