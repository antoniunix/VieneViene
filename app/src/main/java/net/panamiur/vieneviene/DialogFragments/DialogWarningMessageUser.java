package net.panamiur.vieneviene.DialogFragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.panamiur.vieneviene.R;

/**
 * Created by gnu on 15/11/16.
 */

public class DialogWarningMessageUser extends DialogFragment implements View.OnClickListener{

    private View view;
    private Button btn_next;
    private TextView txt_message;
    private String strTitle;
    private String strMsg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        strTitle=getArguments().getString("title");
        strMsg=getArguments().getString("msg");
        getDialog().setTitle(strTitle);
        view = inflater.inflate(R.layout.dialog_warning_message_user, container);

        btn_next=(Button)view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        txt_message=(TextView)view.findViewById(R.id.txt_message);
        txt_message.setText(strMsg);
        return view;
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}
