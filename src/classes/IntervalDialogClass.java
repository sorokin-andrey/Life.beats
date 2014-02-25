package classes;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.quickblox.lifebeats.R;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 03.11.13
 * Time: 15:39
 */
public class IntervalDialogClass extends Dialog implements
        View.OnClickListener {

    public Activity activity;
    public Dialog dialog;
    public Button yes, no;

    public IntervalDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_interval);
        yes = (Button) findViewById(R.id.dialog_interval_Button_confirm);
        no = (Button) findViewById(R.id.dialog_interval_Button_cancel);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_interval_Button_confirm:
                dismiss();
                break;
            case R.id.dialog_interval_Button_cancel:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}