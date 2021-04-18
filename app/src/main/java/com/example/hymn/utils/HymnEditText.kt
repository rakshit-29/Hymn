package com.example.hymn.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class HymnEditText(context: Context, attrs:AttributeSet):AppCompatEditText(context,attrs) {
    init {
        //Call the function to apply the font to the components
        applyFont()
    }
    private fun applyFont(){
        //This is used to get the file from the assets folder and set it to the title textView
        val typeface: Typeface =
                Typeface.createFromAsset(context.assets, "Montserrat-Bold.ttf")
        setTypeface(typeface)

    }
}