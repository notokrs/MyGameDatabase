package com.rusnoto.mygamedatabase

import android.annotation.SuppressLint
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class ExampleUnitTest {
	@Test
	fun addition_isCorrect() {
		assertEquals(4, 2 + 2)
	}

	@Test
	@SuppressLint("SimpleDateFormat")
	fun getDate(){
		val calendar = Calendar.getInstance()
		val formatter = SimpleDateFormat("yyyy-MM-dd")

		val year = calendar.get(Calendar.YEAR)
		val month = calendar.get(Calendar.MONTH) + 1
		val firstDay = calendar.set(Calendar.DAY_OF_MONTH, 1)

		val firstDate = formatter.format(calendar.time)
		val secondDate = formatter.format(Calendar.getInstance().time)

		 print("$firstDate,$secondDate")
	}
}