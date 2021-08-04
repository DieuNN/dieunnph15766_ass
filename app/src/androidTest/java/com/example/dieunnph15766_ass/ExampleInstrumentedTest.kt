package com.example.dieunnph15766_ass

import android.app.Activity
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.IncomeTypeDB
import com.example.dieunnph15766_ass.model.IncomeType

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.dieunnph15766_ass", appContext.packageName)
    }
}