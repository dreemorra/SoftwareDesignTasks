package lab2;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.*;

import mainpkg.ButtonsFragment;

public class SciFragment extends Fragment implements View.OnClickListener {

    boolean inverted = false;

    private EditText input;
    private Button degRadButton, sinButton, cosButton, tanButton, logButton, lnButton;
    private TextView degRadLabel;


    public static ButtonsFragment newInstance() {
        return new ButtonsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = (View) inflater.inflate(R.layout.sci_fragment, container, false);
        v.findViewById(R.id.close_bracket).setOnClickListener(this);
        v.findViewById(R.id.open_bracket).setOnClickListener(this);
        v.findViewById(R.id.one_div_x).setOnClickListener(this);
        v.findViewById(R.id.x__squared).setOnClickListener(this);
        v.findViewById(R.id.x__squared).setOnClickListener(this);
        v.findViewById(R.id.x__n).setOnClickListener(this);
        v.findViewById(R.id.x_fact).setOnClickListener(this);
        v.findViewById(R.id.root).setOnClickListener(this);
        v.findViewById(R.id.n_root).setOnClickListener(this);
        v.findViewById(R.id.e).setOnClickListener(this);
        v.findViewById(R.id.ln).setOnClickListener(this);
        v.findViewById(R.id.log).setOnClickListener(this);
        v.findViewById(R.id.sin).setOnClickListener(this);
        v.findViewById(R.id.cos).setOnClickListener(this);
        v.findViewById(R.id.tan).setOnClickListener(this);
        v.findViewById(R.id.pi).setOnClickListener(this);
        v.findViewById(R.id.deg_rad).setOnClickListener(this);
        v.findViewById(R.id.inv).setOnClickListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        input = getActivity().findViewById(R.id.input);
        degRadButton = getActivity().findViewById(R.id.deg_rad);
        degRadLabel = getActivity().findViewById(R.id.deg_rad_label);

        sinButton = getActivity().findViewById(R.id.sin);
        cosButton = getActivity().findViewById(R.id.cos);
        tanButton = getActivity().findViewById(R.id.tan);
        logButton = getActivity().findViewById(R.id.log);
        lnButton = getActivity().findViewById(R.id.ln);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_bracket:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.close_bracket));
                break;
            }
            case R.id.open_bracket:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.open_bracket));
                break;
            }
            case R.id.one_div_x:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.one_div_x_action));
                break;
            }
            case R.id.x__squared:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.x__squared_action));
                break;
            }
            case R.id.x__cubed:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.x__cubed_action));
                break;
            }
            case R.id.x__n:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.x__n_action));
                break;
            }
            case R.id.x_fact:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.x_fact_action));
                break;
            }
            case R.id.root:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.root_action));
                break;
            }
            case R.id.n_root:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.n_root_action));
                break;
            }
            case R.id.e:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.e));
                break;
            }
            case R.id.ln:
            {
                if (inverted) {
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.ln_inv));
                }
                else input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.ln_action));
                break;
            }
            case R.id.log:
            {
                if (inverted) {
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.log_inv));
                }
                else input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.log_action));
                break;
            }
            case R.id.sin:
            {
                if (inverted) {
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.asin));
                }
                else input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.sin_action));
                break;
            }
            case R.id.cos:
            {
                if (inverted) {
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.acos));
                }
                else input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.cos_action));
                break;
            }
            case R.id.tan:
            {
                if (inverted) {
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.atan));
                }
                else input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.tan_action));
                break;
            }
            case R.id.pi:
            {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.pi_action));
                break;
            }
            case R.id.deg_rad:
            {
                if (mXparser.checkIfDegreesMode()) {
                    mXparser.setRadiansMode();
                    degRadButton.setText(getResources().getString(R.string.deg));
                    degRadLabel.setText(getResources().getString(R.string.rad));
                }
                else {
                    mXparser.setDegreesMode();
                    degRadButton.setText(getResources().getString(R.string.rad));
                    degRadLabel.setText(getResources().getString(R.string.deg));
                }
                break;
            }
            case R.id.inv:
            {
                inverted = !inverted;
                if (inverted) {
                    sinButton.setText(getResources().getString(R.string.asin_button));
                    cosButton.setText(getResources().getString(R.string.acos_button));
                    tanButton.setText(getResources().getString(R.string.atan_button));
                    logButton.setText(getResources().getString(R.string.log_inv_button));
                    lnButton.setText(getResources().getString(R.string.ln_inv_button));
                }
                else {
                    sinButton.setText(getResources().getString(R.string.sin));
                    cosButton.setText(getResources().getString(R.string.cos));
                    tanButton.setText(getResources().getString(R.string.tan));
                    logButton.setText(getResources().getString(R.string.log));
                    lnButton.setText(getResources().getString(R.string.ln));
                }
                break;
            }
        }
    }
}
