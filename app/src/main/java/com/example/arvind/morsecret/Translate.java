package com.example.arvind.morsecret;

import android.hardware.Camera; // allows for use of the phone's camera
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Vibrator;
import java.util.ArrayList;
import android.content.Context;

/**
 * @author Veena, Alice, Shannon
 * @created April 12, 2015
 *
 * Translate Fragment displays on app. Two types of translate used includes vibration and flash.
 *      Also uses helper static methods from MorseTranslate.
 */
public class Translate extends Fragment {
    public Translate() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View in = inflater.inflate(R.layout.activity_translate, container, false);
        return in;


    }
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        // creates Button object that communicates with xml button
        // user clicks on it to translate user input into Morse code on the screen
        Button b_Translate = (Button) view.findViewById(R.id.translate_Button);

        // after user clicks on button, program will retrieve translated text, set it to lowercase
        // (because in Morse code, lower and upper case letters are
        //  the same), and outputs text in Morse Code.
        b_Translate.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {
                        EditText edit = (EditText) view.findViewById(R.id.edit_message);
                        TextView t_Flash = (TextView) view.findViewById(R.id.flash_Text);
                        String s = edit.getText().toString().toLowerCase();
                        t_Flash.setText(MorseTranslate.translate(s));
                    }
                }
        );

        // creates Button object that communicates with xml button
        // user clicks on it to translate user input into Morse Code flashes
        Button b_Flash = (Button) view.findViewById(R.id.flash_Button);

        // after user clicks on button, program will retrieve text from textbox in the form of a
        //  String, set it to lowercase (because in Morse code, lower and upper case letters are
        //  the same), and outputs flash from the phone. This is FLASH ONLY. No text is shown.
        b_Flash.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {
                        EditText edit = (EditText) view.findViewById(R.id.edit_message);
                        String s = edit.getText().toString().toLowerCase();
                        if (MorseTranslate.vibrateTranslate(s)== "x") {
                            TextView t_Flash = (TextView) view.findViewById(R.id.flash_Text);
                            t_Flash.setText(MorseTranslate.translate(s));
                        }
                        else {
                            MorseTranslate.flashInOwnThread(s);
                        }
                    }
                }
        );



        // creates Button object that communicates with xml button
        // user clicks on it to translate user input into Morse Code vibrations
        Button b_Vibration = (Button) view.findViewById(R.id.vibrate_Button);

        // after user clicks on button, program will retrieve text from textbox in the form of a
        //  String, set it to lowercase (because in Morse code, lower and upper case letters are
        //  the same), and outputs flash from the phone. This is FLASH ONLY. No text is shown.
        b_Vibration.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {
                        EditText edit = (EditText) view.findViewById(R.id.edit_message);
                        String s = edit.getText().toString().toLowerCase();
                        if (MorseTranslate.vibrateTranslate(s)== "x") {
                            TextView t_Flash = (TextView) view.findViewById(R.id.flash_Text);
                            t_Flash.setText(MorseTranslate.translate(s));
                        } else {
                            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                            long dot = 500;          // Length of a Morse Code "dot" in milliseconds
                            long dash = 1500;         // Length of a Morse Code "dash" in milliseconds
                            long short_gap = 500;    // Length of Gap Between dots/dashes
                            long medium_gap = 1500;   // Length of Gap Between Letters
                            long long_gap = 3500;    // Length of Gap Between Words
                            String temp = MorseTranslate.vibrateTranslate(s);
                            ArrayList<Long> list = new ArrayList<Long>();
                            list.add(new Long(0));
                            for (char c : temp.toCharArray()) {
                                if (c == '.') {
                                    list.add(dot);
                                } else if (c == '-') {
                                    list.add(dash);
                                } else if (c == ' ') {
                                    list.add(short_gap);
                                } else if (c == '!') {
                                    list.add(medium_gap);
                                } else if (c == '~') {
                                    int index = list.lastIndexOf(medium_gap);
                                    list.remove(index);
                                    list.add(long_gap);
                                }
                            }
                            long[] pattern = new long[list.size()];
                            int i = 0;
                            for (Long l : list) {
                                long prim = l.longValue();
                                pattern[i] = prim;
                                i++;
                            }
                            vibrator.vibrate(pattern, -1);
                        }
                    }

                }
        );
    }
}