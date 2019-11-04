package lab2;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

public class ButtonsFragment extends Fragment {

    public static ButtonsFragment newInstance() {
        return new ButtonsFragment();
    }

    private Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonDot, buttonPerc, buttonSum, buttonSub, buttonDiv,
            buttonMul, buttonC, buttonEqual, buttonErase, buttonMC, buttonMSum, buttonMSub, buttonMR;

    private EditText input, result;

    private TextView memoryLabel;

    private Double memory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.buttons_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        button0 = getActivity().findViewById(R.id.button0);
        button1 = getActivity().findViewById(R.id.button1);
        button2 = getActivity().findViewById(R.id.button2);
        button3 = getActivity().findViewById(R.id.button3);
        button4 = getActivity().findViewById(R.id.button4);
        button5 = getActivity().findViewById(R.id.button5);
        button6 = getActivity().findViewById(R.id.button6);
        button7 = getActivity().findViewById(R.id.button7);
        button8 = getActivity().findViewById(R.id.button8);
        button9 = getActivity().findViewById(R.id.button9);
        buttonDot = getActivity().findViewById(R.id.button_dot);
        buttonPerc = getActivity().findViewById(R.id.button_perc);
        buttonSum = getActivity().findViewById(R.id.button_sum);
        buttonSub = getActivity().findViewById(R.id.button_sub);
        buttonMul = getActivity().findViewById(R.id.button_mul);
        buttonDiv = getActivity().findViewById(R.id.button_div);
        buttonC = getActivity().findViewById(R.id.button_clear);
        buttonErase = getActivity().findViewById(R.id.button_erase);
        buttonEqual = getActivity().findViewById(R.id.button_eq);
        buttonMC = getActivity().findViewById(R.id.button_mem_clear);
        buttonMSum = getActivity().findViewById(R.id.button_mem_sum);
        buttonMSub = getActivity().findViewById(R.id.button_mem_sub);
        buttonMR = getActivity().findViewById(R.id.button_mem_replace);

        input = getActivity().findViewById(R.id.input);
        input.setSelection(input.getText().length());
        result = getActivity().findViewById(R.id.result);
        memoryLabel = getActivity().findViewById(R.id.memory_label);
        memory = 0.;

        final MediaPlayer mp = MediaPlayer.create(this.getActivity(), R.raw.honk);

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

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.one));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.two));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.three));
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.four));
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.five));
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.six));
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.seven));
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.eight));
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.nine));
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.zero));
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.dot));
            }
        });

        buttonPerc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.percent));
            }
        });

        buttonSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().length() != 0)
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.sum));
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.sub));
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().length() != 0)
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.mul));
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().length() != 0)
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.div));
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result.getText().length() != 0) {
                    input.setText(result.getText().toString());
                    result.getText().clear();
                    input.setSelection(input.getText().length());
                    mp.start();
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().clear();
                result.getText().clear();
            }
        });

        buttonErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        buttonMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memory = 0.;
                memoryLabel.setText("");
            }
        });

        buttonMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (memory != 0.)
                    input.getText().insert(input.getSelectionStart(), memory.toString());
            }
        });

        buttonMSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        buttonMSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                }
        });
    }
}
