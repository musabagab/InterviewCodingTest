package com.musabagab.interviewtest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.musabagab.interviewtest.Model.Medicine
import com.musabagab.interviewtest.R


class MedicineListAdapterViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    private val medicineNameText = view.findViewById<TextView>(R.id.nameTextView)
    private val medicineDoseText = view.findViewById<TextView>(R.id.doseTextView)
    private val medicineStrengthText = view.findViewById<TextView>(R.id.strengthTextView)

    fun bind(medicine: Medicine) {
        medicineNameText.text = medicine.name
        medicineDoseText.text = when (medicine.dose) {
            "" -> "-"
            else -> medicine.dose
        }
        medicineStrengthText.text = medicine.strength
    }
}

class MedicineListAdapter(
    private val clickHandler: (Medicine) -> Unit
) : ListAdapter<Medicine, MedicineListAdapterViewHolder>(
    DIFF_CONFIG
) {
    companion object {
        val DIFF_CONFIG =
            object : DiffUtil.ItemCallback<Medicine>() {
                override fun areItemsTheSame(oldItem: Medicine,
                                             newItem: Medicine
                ): Boolean {
                    return oldItem === newItem
                }

                override fun areContentsTheSame(
                    oldItem: Medicine,
                    newItem: Medicine
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MedicineListAdapterViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.medicine_list_item, parent,
                    false)
        return MedicineListAdapterViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: MedicineListAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickHandler(getItem(position))
        }
    }
}




