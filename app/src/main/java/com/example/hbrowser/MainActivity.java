package com.example.hbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView web_View; //references the WebView class
    EditText search_Box;
    Button search_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web_View = (WebView) findViewById(R.id.WV); //references the WebView id
        web_View.setWebViewClient(new WebViewClient()); //enables the app itself to display websites, instead of opening an external browser app
        web_View.loadUrl("https://www.google.com"); //loads google.com

        WebSettings WS = web_View.getSettings();
        WS.setJavaScriptEnabled(true); //enables the sites you visit via the app to use javascript

        search_Box = (EditText) findViewById(R.id.searchTextBox); //references the search text box section
        search_Button = (Button) findViewById(R.id.searchButton); //references the search button section
        search_Button.setOnClickListener(new View.OnClickListener() { //checks if you've pressed the search button
            @Override
            public void onClick(View view){ //executes when the search button is pressed

                String url = search_Box.getText().toString(); //saves the text written in the search text box
                web_View.loadUrl(url); //goes to the website that corresponds to the text written in the search text box
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //happens when the option menu is created

        MenuInflater menu_Inflater = getMenuInflater();
        menu_Inflater.inflate(R.menu.menu_buttons, menu); //creates the menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //determines what the menu options do
        switch (item.getItemId()){ //goes through the item options

            case R.id.goBack: //on the back-arrow icon
                onBackPressed(); //executes the onBackPressed() function
                break; //breaks out of this case

            case R.id.goForward:
                onForwardPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() { //what happens when the back-arrow icon is pressed
        if ( web_View.canGoBack()){ web_View.goBack(); } //goes back if the webview can go back
        else { finish(); } //if it is already at the beginning, it quits the app
    }

    private void onForwardPressed() {
        if ( web_View.canGoForward()){ web_View.goForward(); }
        else { Toast.makeText(this, "Can't go forward", Toast.LENGTH_SHORT).show(); } //displays a "Can't go forward" message, if you are already at the front
    }
}

