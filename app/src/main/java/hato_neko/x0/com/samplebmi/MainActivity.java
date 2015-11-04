package hato_neko.x0.com.samplebmi;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
		setSupportActionBar( toolbar );

		TextView bmiLabel = (TextView)findViewById(R.id.bmiLabel) ;
		System.out.println( bmiLabel.getText() ) ;
		bmiLabel.setText( "" );
		TextView commentLabel = (TextView)findViewById(R.id.commentLabel) ;
		commentLabel.setText( "" );
		/* メールボタンの処理
		FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
		fab.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG ).setAction( "Action", null ).show();
			}
		} );
		*/
	}

	public int onCalcButton(View button)
	{
		// キーボードをしまう
		InputMethodManager keyboard = (InputMethodManager)getSystemService( Context
																					.INPUT_METHOD_SERVICE ) ;
		keyboard.hideSoftInputFromWindow( button.getWindowToken(), InputMethodManager
																		   .HIDE_NOT_ALWAYS ) ;

		Float height = 0f ;
		Float weight = 0f ;
		EditText heightField = (EditText)findViewById(R.id.heightField) ;
		String heightValueString = heightField.getText().toString() ;
		if(heightValueString.length() > 0)
		{
			try{
				Float heightCentiMeter = Float.parseFloat( heightValueString );
				height = heightCentiMeter / 100;
			}
			catch(Exception e) {
				System.out.println(e) ;
						return -1 ;
			}
		}else{ height = 0f ; }
		EditText weightField = (EditText)findViewById(R.id.weightField) ;
		String weightValueString = weightField.getText().toString() ;
		if(weightValueString.length() > 0)
		{
			try{
				weight = Float.parseFloat( weightValueString );
			}
			catch(Exception e) {
				System.out.println(e) ;
				return -1 ;
			}
		}else{ return -1 ; }

		if(height == 0)
		{
			return -1 ;
		}
		Float bmi = weight / (height * height) ;
		System.out.println( "height = " + height + ", weight = " + weight + ", BMI = " + bmi ) ;

		TextView bmiLabel = (TextView)findViewById(R.id.bmiLabel) ;
		System.out.println( bmiLabel.getText() ) ;
		bmiLabel.setText( "BMI : " + bmi ) ;

		TextView commentLabel = (TextView)findViewById(R.id.commentLabel) ;
		if(bmi < 17.6){
			commentLabel.setText( "やせすぎです。\nもっと食べましょう。" ) ;
		}else if(bmi < 19.8){
			commentLabel.setText( "やせ気味ですが、まあ、いい感じですね。" ) ;
		}else if(bmi < 24.2){
			commentLabel.setText( "健康的なBMIです。\nいいっすね〜" ) ;
		}else if(bmi < 26.4){
			commentLabel.setText( "太り気味ですね。\nけっこうまずいので、体を動かしましょう。" ) ;
		}else if(bmi >= 26.4){
			commentLabel.setText( "やばいです。やばい。" ) ;
		}else{
			commentLabel.setText( "ERROR" ) ;
		}

		return 1 ;
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		//noinspection SimplifiableIfStatement
		if(id == R.id.action_settings)
		{
			return true;
		}
		
		return super.onOptionsItemSelected( item );
	}
}
