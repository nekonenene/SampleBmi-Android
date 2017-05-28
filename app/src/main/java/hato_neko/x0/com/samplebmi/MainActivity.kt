package hato_neko.x0.com.samplebmi

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val bmiLabel = findViewById(R.id.bmiLabel) as TextView
        println(bmiLabel.text)
        bmiLabel.text = ""

        val commentLabel = findViewById(R.id.commentLabel) as TextView
        commentLabel.text = ""

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

    fun onCalcButton(button: View): Int {
        // キーボードをしまう
        val keyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(button.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

        var height: Float = 0f
        var weight: Float = 0f
        val heightField = findViewById(R.id.heightField) as EditText
        val heightValueString = heightField.text.toString()

        if (heightValueString.isNotEmpty()) {
            try {
                val heightCentiMeter = java.lang.Float.parseFloat(heightValueString)
                height = heightCentiMeter / 100
            } catch (e: Exception) {
                println(e)
                return -1
            }
        } else {
            height = 0f
        }

        val weightField = findViewById(R.id.weightField) as EditText
        val weightValueString = weightField.text.toString()

        if (weightValueString.isNotEmpty()) {
            try {
                weight = java.lang.Float.parseFloat(weightValueString)
            } catch (e: Exception) {
                println(e)
                return -1
            }
        } else {
            return -1
        }

        if (height == 0f) {
            return -1
        }

        val bmi = weight / (height * height)
        println("height = $height, weight = $weight, BMI = $bmi")

        val bmiLabel = findViewById(R.id.bmiLabel) as TextView
        println(bmiLabel.text)
        bmiLabel.text = "BMI : " + bmi

        val commentLabel = findViewById(R.id.commentLabel) as TextView
        if (bmi < 17.6) {
            commentLabel.text = "やせすぎです\nもっと食べましょう"
        } else if (bmi < 19.8) {
            commentLabel.text = "やせ気味ですが、まあ、いい感じですね"
        } else if (bmi < 24.2) {
            commentLabel.text = "健康的なBMIです\nいいっすね〜"
        } else if (bmi < 26.4) {
            commentLabel.text = "太り気味ですね\nけっこうまずいので、体を動かしましょう"
        } else if (bmi >= 26.4) {
            commentLabel.text = "やばいです。やばい"
        } else {
            commentLabel.text = "ERROR"
        }

        return 1
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
