package com.example.employee_demo_external_wiith_registration

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_all.*
import kotlinx.android.synthetic.main.custom_dialog.*

class ViewAllActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all)
        refreshRecycler()

    }
    private fun refreshRecycler() {
        var db=DBHelper(this)
        var arr=db.retriveAll()
        var employeeadapter = EmployeeAdapter(this,arr)
        myrecycle.adapter = employeeadapter
    }
    fun delete(position:Int)
    {
        var db=DBHelper(this)
        var arr:ArrayList<Employee> = db.retriveAll()
        var employee=arr.get(position)
        db.delete(employee.em_id)
        refreshRecycler()
    }
    fun update(position: Int)
    {
        var db=DBHelper(this)
        var arr:ArrayList<Employee> = db.retriveAll()
        var employee = arr.get(position)
        var dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.txtUpadateId.setText(employee.em_id.toString())
        dialog.edtUpdatePost.setText(employee.em_post)
        dialog.edtUpdateDept.setText(employee.em_dept)
        dialog.edtUpdateSalary.setText(employee.em_salary.toString())

        dialog.btnUpdate.setOnClickListener {
            var id = dialog.txtUpadateId.text.toString().toInt()
            var post = dialog.edtUpdatePost.text.toString()
            var dept = dialog.edtUpdateDept.text.toString()
            var salary = dialog.edtUpdateSalary.text.toString().toInt()
            var employee = Employee(id,post,dept,salary)
            db.update(employee)
            dialog.dismiss()
            refreshRecycler()
        }
        dialog.show()
    }
}