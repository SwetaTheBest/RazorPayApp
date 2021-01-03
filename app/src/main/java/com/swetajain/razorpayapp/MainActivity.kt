package com.swetajain.razorpayapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import com.swetajain.razorpayapp.databinding.ActivityMainBinding
import org.json.JSONObject
import java.lang.Exception
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(),PaymentResultListener {
    lateinit var mBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mBinding.root
        var amountString = "100"
        setContentView(view)

        var amount = (amountString.toFloat() * 100).roundToInt()

        mBinding.payButton.setOnClickListener {
            var checkout = Checkout()
            checkout.setKeyID("rzp_test_o57X5qeswStRoM")
            checkout.setImage(R.drawable.rzp_logo)
            var jsonObject = JSONObject()
            try {
                jsonObject.put("name", "Sweta")
                jsonObject.put("description", "test payment")
                jsonObject.put("theme.color", "#0093DD")
                jsonObject.put("amount", amount)
                jsonObject.put("currency", "INR")
                jsonObject.put("prefill.contact", "9876543210")
                jsonObject.put("prefill.email", "sweta@gmail.com")
                checkout.open(this,jsonObject)
            }catch (e: Exception){
                e.printStackTrace()
            }


        }

    }

    override fun onPaymentSuccess(p0: String?) {
        var alertDialogBuilder= AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Payment ID")
        alertDialogBuilder.setMessage(p0)
        alertDialogBuilder.show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this,p0,Toast.LENGTH_SHORT).show()
    }
}