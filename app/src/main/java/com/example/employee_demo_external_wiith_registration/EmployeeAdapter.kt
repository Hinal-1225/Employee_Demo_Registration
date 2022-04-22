package com.example.employee_demo_external_wiith_registration

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item.view.*

class EmployeeAdapter(val context: Context, var arr:ArrayList<Employee>)
    : RecyclerView.Adapter<EmployeeAdapter.PersonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.card_item, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(arr[position])
        holder.itemView.imgDetele.setOnClickListener {
            if (context is ViewAllActivity) {
                context.delete(position)
            }
        }
        holder.itemView.imgUpdate.setOnClickListener {
            if (context is ViewAllActivity) {
                context.update(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return arr.size
    }

    class PersonViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(p: Employee) {
            view.tvEmId.setText(p.em_id.toString())
            view.tvEmPost.setText(p.em_post)
            view.tvEmDept.setText(p.em_dept)
            view.tvEmSalary.setText(p.em_salary.toString())
        }
    }
}