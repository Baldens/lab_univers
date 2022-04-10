package com.example.labfour.day_planer.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.labfour.R

class PlannerAdapter : RecyclerView.Adapter<PlannerAdapter.PlannerViewHolder>() {
    private var stdList: ArrayList<ModelPlanner> = ArrayList()

    class PlannerViewHolder(var view: View): RecyclerView.ViewHolder(view){
        private var name = view.findViewById<TextView>(R.id.textTitlePlanner)
        private var description = view.findViewById<TextView>(R.id.textDescriptionPlanner)
        fun bindView(std: ModelPlanner){
            name.text = std.name
            description.text = std.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlannerViewHolder {
        return PlannerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_list_to_do2, parent, false))
    }

    override fun onBindViewHolder(holder: PlannerViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

    fun addItems(std: ArrayList<ModelPlanner>){
        this.stdList = std
    }
}