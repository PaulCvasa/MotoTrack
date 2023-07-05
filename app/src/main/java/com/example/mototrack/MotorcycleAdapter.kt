package com.example.mototrack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MotorcycleAdapter(private val mMotoList: ArrayList<MotorcycleAWS>) :
    RecyclerView.Adapter<MotorcycleAdapter.MotorcycleViewHolder>() {

    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    class MotorcycleViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
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
    ): MotorcycleViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        return MotorcycleViewHolder(v, mListener)
    }

    override fun onBindViewHolder(holder: MotorcycleViewHolder, position: Int) {
        val currentItem = mMotoList[position]
        holder.mImageView.setImageResource(currentItem.getImage())
        holder.mMotorcycleMaker.text = currentItem.getMotoBrand()
        holder.mMotorcycleModel.text = currentItem.getMotoModel()
        holder.mMotorcycleMileage.text = currentItem.getMileage().toString()
        holder.mMotorcycleVIN.text = currentItem.getVIN()
    }

    override fun getItemCount(): Int {
        return mMotoList.size
    }
}