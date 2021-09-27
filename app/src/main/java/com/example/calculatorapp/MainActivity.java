package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity {


    TextView dis_userInput;
    TextView dis_userResult;

    String workings = "";
    String formula = "";
    String tempFormula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextView();
    }

    private void initTextView() {
        dis_userInput = findViewById(R.id.dis_userInput);
        dis_userResult = findViewById(R.id.dis_userResult);
    }

    private void setWorkings(String givenValue) {
        workings = workings + givenValue;
        dis_userInput.setText(workings);
    }

    public void equalEvent(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

        try{
            result = (double)engine.eval(formula);
        } catch (ScriptException e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
        if (result != null)
            dis_userResult.setText(String.valueOf(result.doubleValue()));
    }

    private void changeFormula(Integer index) {
        String numberLeft = "";
        String numberRight = "";

        for (int i= index+1; i< workings.length(); i++)
        {
            if(isNumeric(workings.charAt(i)))
            {
                numberRight = numberRight + workings.charAt(i);
            }
            else
                break;
        }
        for (int i= index-1; i>=0; i--)
        {
            if(isNumeric(workings.charAt(i)))
            {
                numberLeft = numberLeft + workings.charAt(i);
            }
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "math.pow(" + numberLeft + "," + numberRight + ")";
        tempFormula= tempFormula.replace(original , changed);
    }

    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || (c == '.')) {
            return true;
        }

        return false;
    }


    public void clearEvent(View view) {
        dis_userResult.setText("");
        workings="";
        dis_userResult.setText("");
        leftBracket=true;
    }

    boolean leftBracket = true;

    public void bracketsOnClick(View view) {
        if (leftBracket) {
            setWorkings("(");
            leftBracket=false;
        }
        else {
            setWorkings(")");
            leftBracket=true;
        }

    }

    public void percentageEvent(View view) {
        setWorkings("%");
    }

    public void DivideEvent(View view) {
        setWorkings("/");
    }

    public void MultiplyEvent(View view) {
        setWorkings("*");
    }

    public void sevenEvent(View view) {
        setWorkings("7");
    }

    public void eightEvent(View view) {
        setWorkings("8");
    }

    public void NineEvent(View view) {
        setWorkings("9");
    }

    public void minusEvent(View view) {
        setWorkings("-");
    }

    public void fourEvent(View view) {
        setWorkings("4");
    }

    public void fiveEvent(View view) {
        setWorkings("5");
    }

    public void sixEvent(View view) {
        setWorkings("6");
    }

    public void addEvent(View view) {
        setWorkings("+");
    }

    public void oneEvent(View view) {
        setWorkings("1");
    }

    public void twoEvent(View view) {
        setWorkings("2");
    }

    public void threeEvent(View view) {
        setWorkings("3");
    }



    public void dotEvent(View view) {
        setWorkings(".");
    }

    public void zeroEvent(View view) {
        setWorkings("0");
    }

    public void doubleZeroEvent(View view) {
        setWorkings("00");
    }
}
