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

public class SciFragment extends Fragment {

    boolean inverted = false;

    private Button closeBracketButton, openBracketButton, oneDivXButton, xSquaredButton,
            xCubedButton, xNButton, xFactButton, rootButton, nRootButton, eButton, lnButton,
            logButton, sinButton, cosButton, tanButton, piButton, degRadButton, invButton;

    private EditText input;
    private TextView degRadLabel;

    public static ButtonsFragment newInstance() {
        return new ButtonsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sci_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        closeBracketButton = getActivity().findViewById(R.id.close_bracket);
        openBracketButton = getActivity().findViewById(R.id.open_bracket);
        oneDivXButton = getActivity().findViewById(R.id.one_div_x);
        xSquaredButton = getActivity().findViewById(R.id.x__squared);
        xCubedButton = getActivity().findViewById(R.id.x__cubed);
        xNButton = getActivity().findViewById(R.id.x__n);
        xFactButton = getActivity().findViewById(R.id.x_fact);
        rootButton = getActivity().findViewById(R.id.root);
        nRootButton = getActivity().findViewById(R.id.n_root);
        eButton = getActivity().findViewById(R.id.e);
        lnButton = getActivity().findViewById(R.id.ln);
        logButton = getActivity().findViewById(R.id.log);
        sinButton = getActivity().findViewById(R.id.sin);
        cosButton = getActivity().findViewById(R.id.cos);
        tanButton = getActivity().findViewById(R.id.tan);
        piButton = getActivity().findViewById(R.id.pi);
        degRadButton = getActivity().findViewById(R.id.deg_rad);
        invButton = getActivity().findViewById(R.id.inv);

        input = getActivity().findViewById(R.id.input);
        degRadLabel = getActivity().findViewById(R.id.deg_rad_label);

        closeBracketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.close_bracket));
            }
        });

        openBracketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.open_bracket));
            }
        });

        oneDivXButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.one_div_x_action));
            }
        });

        xSquaredButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.x__squared_action));
            }
        });

        xCubedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.x__cubed_action));
            }
        });

        xNButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.x__n_action));
            }
        });

        xFactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.x_fact_action));
            }
        });

        rootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.root_action));
            }
        });

        nRootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.n_root_action));
            }
        });

        eButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.e));
            }
        });

        lnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inverted) {
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.ln_inv));
                }
                else input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.ln_action));
            }
        });

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inverted) {
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.log_inv));
                }
                else input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.log_action));
            }
        });

        sinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inverted) {
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.asin));
                }
                else input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.sin_action));
            }
        });

        cosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inverted) {
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.acos));
                }
                else input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.cos_action));
            }
        });

        tanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inverted) {
                    input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.atan));
                }
                else input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.tan_action));
            }
        });

        piButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.pi_action));
            }
        });

        piButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().insert(input.getSelectionStart(), getResources().getString(R.string.pi_action));
            }
        });

        degRadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        //TODO: add to inv buttons that "if"
        invButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
    }
}
