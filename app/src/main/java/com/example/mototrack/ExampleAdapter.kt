package com.example.mototrack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ExampleAdapter(private val mExampleList: ArrayList<ExampleItem>) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    class ExampleViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        var mImageView: ImageView
        var mMotorcycleMaker: TextView
        var mMotorcycleModel: TextView
        var mMotorcycleMileage : TextView
        var mMotorcycleVIN : TextView

        init {
            mImageView = itemView.findViewById(R.id.imageView)
            mMotorcycleMaker = itemView.findViewById(R.id.textView)
            mMotorcycleModel = itemView.findViewById(R.id.textView2)
            mMotorcycleMileage = itemView.findViewById(R.id.textView3)
            mMotorcycleVIN = itemView.findViewById(R.id.textView4)

            itemView.setOnClickListener(View.OnClickListener {
                fun onClick(v : View)
                {
                    if (listener != null) {
                        val position = adapterPosition;
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position)
                        }
                    }
                }
            })
        }



    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExampleViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        return ExampleViewHolder(v, mListener)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = mExampleList[position]
        holder.mImageView.setImageResource(currentItem.getImageResource())
        holder.mMotorcycleMaker.text = currentItem.getText1()
        holder.mMotorcycleModel.text = currentItem.getText2()
        holder.mMotorcycleMileage.text = currentItem.getText3()
        holder.mMotorcycleVIN.text = currentItem.getText4()
    }

    override fun getItemCount(): Int {
        return mExampleList.size
    }
}