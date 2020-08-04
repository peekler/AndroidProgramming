package hu.bme.aut.roomgradedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.roomgradedemo.data.AppDatabase
import hu.bme.aut.roomgradedemo.data.Grade
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var enabled = false

    inner class MyThread : Thread() {
        override fun run() {
            while (enabled) {

                runOnUiThread {
                    tvData.append("#")
                }

                sleep(1000)

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            enabled = true
            MyThread().start()
        }

        btnSave.setOnClickListener {
            saveGrade()
        }

        btnQuery.setOnClickListener {
            queryGrades()
        }
    }

    fun saveGrade() {
        Thread {
            AppDatabase.getInstance(this@MainActivity).gradeDao().insertGrades(
                Grade(null, etStudentName.text.toString(),
                    etGrade.text.toString())
            )
        }.start()
    }

    fun queryGrades() {
        Thread {
            var grades = AppDatabase.getInstance(this@MainActivity).gradeDao().getSomeGrades("C")

            runOnUiThread {
                tvResult.text = ""
                grades.forEach {
                    tvResult.append("${it.studentName} - ${it.grade}\n")
                }
            }

        }.start()
    }

    override fun onStop() {
        enabled = false
        super.onStop()
    }
}