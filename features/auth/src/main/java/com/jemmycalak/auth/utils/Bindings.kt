package com.jemmycalak.auth.utils

import android.util.Patterns
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.jemmycalak.auth.R

object Bindings {

    val passwordRegex =
        "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{9,}\$"
    val numberRegex = "-?\\d+(.\\d+)?"

    @JvmStatic
    @InverseBindingAdapter(attribute = "app:username", event = "android:textAttrChanged")
    fun onUsername(e: TextInputEditText): String {
        e.apply {
            val username = text.toString()
            if (username.isEmpty()) {
                error = context.getString(R.string.label_error_username)
                return ""
            } else return username
        }
    }

    fun isEmail(username: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(username).matches()

    fun isPhone(username: String): Boolean {
        //Patterns.PHONE.matcher(username).matches()
        //phone regex
        return username.matches(numberRegex.toRegex())
    }

    @JvmStatic
    @BindingAdapter("app:username")
    fun onUsername(e: TextInputEditText, s: String?) {
        e.apply {
            if (s != null) {
                if (text.toString() != s) setText(s)
                if (s.length == 0) {
                    error = context.getString(R.string.label_error_username)
                    return
                }
                if (isPhone(s)) {
                    if(s.length <=9 ){
                        error = "Phone number can't less then 10 number"
                        return
                    }
                    return
                }
                if (!isPhone(s) && !isEmail(s)) {
                    error = "Email not valid"
                    return
                }
            }
        }
    }


    @JvmStatic
    @InverseBindingAdapter(attribute = "app:password", event = "android:textAttrChanged")
    fun onPassword(e: TextInputEditText): String {
        val pass = e.text.toString()
        e.apply {
            return pass
        }
    }

    @JvmStatic
    @BindingAdapter("app:password")
    fun onPassword(e: TextInputEditText, s: String?) {
        val pass = e.text.toString()
        e.apply {
            if (s != null) {
                if (pass != s) setText(s)

                if (pass.length == 0) {
                    error = context.getString(R.string.label_error_password_empty)
                    return
                }
                if (pass.length <= 8) {
                    error = context.getString(R.string.label_error_password_morethan_8)
                    return
                }
                if (!pass.matches(passwordRegex.toRegex())) {
                    error = context.getString(R.string.label_error_password_regex)
                    return
                }
            }
        }
    }
}