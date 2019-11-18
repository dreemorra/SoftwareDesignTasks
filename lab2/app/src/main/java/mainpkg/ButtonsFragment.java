package mainpkg;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.mariuszgromada.math.mxparser.Expression;

import lab2.R;

public class ButtonsFragment extends Fragment implements View.OnClickListener {

    public static ButtonsFragment newInstance() {
        return new ButtonsFragment();
    }

    private EditText input, result;

    private TextView memoryLabel;

    private Double memory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View rootView = (View) inflater.inflate(R.layout.buttons_fragment, container, false);
        rootView.findViewById(R.id.button0).setOnClickListener(this);
        rootView.findViewById(R.id.button1).setOnClickListener(this);
        rootView.findViewById(R.id.button2).setOnClickListener(this);
        rootView.findViewById(R.id.button3).setOnClickListener(this);
        rootView.findViewById(R.id.button4).setOnClickListener(this);
        rootView.findViewById(R.id.button5).setOnClickListener(this);
        rootView.findViewById(R.id.button6).setOnClickListener(this);
        rootView.findViewById(R.id.button7).setOnClickListener(this);
        rootView.findViewById(R.id.button8).setOnClickListener(this);
        rootView.findViewById(R.id.button9).setOnClickListener(this);
        rootView.findViewById(R.id.button_dot).setOnClickListener(this);
        rootView.findViewById(R.id.button_perc).setOnClickListener(this);
        rootView.findViewById(R.id.button_sum).setOnClickListener(this);
        rootView.findViewById(R.id.button_sub).setOnClickListener(this);
        rootView.findViewById(R.id.button_mul).setOnClickListener(this);
        rootView.findViewById(R.id.button_div).setOnClickListener(this);
        rootView.findViewById(R.id.button_clear).setOnClickListener(this);
        rootView.findViewById(R.id.button_erase).setOnClickListener(this);
        rootView.findViewById(R.id.button_eq).setOnClickListener(this);
        rootView.findViewById(R.id.button_mem_clear).setOnClickListener(this);
        rootView.findViewById(R.id.button_mem_sum).setOnClickListener(this);
        rootView.findViewById(R.id.button_mem_sub).setOnClickListener(this);
        rootView.findViewById(R.id.button_mem_replace).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        input = getActivity().findViewById(R.id.input);
        input.setSelection(input.getText().length());
        result = getActivity().findViewById(R.id.result);
        memoryLabel = getActivity().findViewById(R.id.memory_label);
        memory = 0.;

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                Expression exp = new Expression(text);
                String res = String.valueOf(exp.calculate());
                if (!res.equals("NaN"))
                    result.setText(res);
                else result.setText("");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.one));
                break;
            }
            case R.id.button2:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.two));
                break;
            }
            case R.id.button3:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.three));
                break;
            }
            case R.id.button4:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.four));
                break;
            }
            case R.id.button5:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.five));
                break;
            }
            case R.id.button6:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.six));
                break;
            }
            case R.id.button7:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.seven));
                break;
            }
            case R.id.button8:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.eight));
                break;
            }
            case R.id.button9:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.nine));
                break;
            }
            case R.id.button0:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.zero));
                break;
            }
            case R.id.button_dot:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.dot));
                break;
            }
            case R.id.button_perc:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.percent));
                break;
            }
            case R.id.button_sum:
            {
                if (input.getText().length() != 0)
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.sum));
                break;
            }
            case R.id.button_sub:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.sub));
                break;
            }
            case R.id.button_mul:
            {
                if (input.getText().length() != 0)
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.mul));
                break;
            }
            case R.id.button_div:
            {
                if (input.getText().length() != 0)
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.div));
                break;
            }
            case R.id.button_eq:
            {
                if (result.getText().length() != 0) {
                    input.setText(result.getText().toString());
                    result.getText().clear();
                    input.setSelection(input.getText().length());
 //                   mp.start();
                }
                break;
            }
            case R.id.button_clear:
            {
                input.getText().clear();
                result.getText().clear();
                break;
            }
            case R.id.button_erase:
            {
                CharSequence text = input.getText().toString();
                int cursorPos = input.getSelectionEnd();
                if (cursorPos != 0) {
                    CharSequence cursorToEnd = text.subSequence(cursorPos, text.length());
                    CharSequence startToCursor = text.subSequence(0, cursorPos);
                    startToCursor = startToCursor.subSequence(0, startToCursor.length() - 1);
                    text = startToCursor.toString() + cursorToEnd.toString();
                    input.setText(text);
                    if (cursorPos - 1 != 0)
                        input.setSelection(cursorPos-1);
                    else input.setSelection(text.length());
                }
                break;
            }
            case R.id.button_mem_clear:
            {
                memory = 0.;
                memoryLabel.setText("");
                break;
            }
            case R.id.button_mem_replace:
            {
                if (memory != 0.)
                    input.getText().insert(input.getSelectionStart(), memory.toString());
                break;
            }
            case R.id.button_mem_sum:
            {
                if (result.getText().length() != 0)
                    memory += Double.valueOf(result.getText().toString());
                if (result.getText().length() == 0) {
                    try {
                        memory += Double.valueOf(input.getText().toString());
                    } catch (NumberFormatException e) {
                        //Handle exception here, most of the time you will just log it.
                        e.printStackTrace();
                    }
                }
                if (memory == 0) {
                    memoryLabel.setText("");
                }
                else memoryLabel.setText(getResources().getString(R.string.memory));
                break;
            }
            case R.id.button_mem_sub:
            {
                if (result.getText().length() != 0)
                    memory -= Double.valueOf(result.getText().toString());
                if (result.getText().length() == 0) {
                    try {
                        memory -= Double.valueOf(input.getText().toString());
                    } catch (NumberFormatException e) {
                        //Handle exception here, most of the time you will just log it.
                        e.printStackTrace();
                    }
                }
                if (memory == 0) {
                    memoryLabel.setText("");
                }
                else memoryLabel.setText(getResources().getString(R.string.memory));
                break;
            }
        }
    }
}
