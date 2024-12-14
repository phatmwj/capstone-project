package winwin.customer.app.ui.sunmiD3mini;

import android.annotation.SuppressLint;
import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;

import winwin.customer.app.R;

public class SecondaryDisplayPresentation extends Presentation {

    public SecondaryDisplayPresentation(Context context, Display display) {
        super(context, display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentation_view);

        TextView textView = findViewById(R.id.presentation_text);
        textView.setText("Chào mừng đến với WinWin!");
    }
}
