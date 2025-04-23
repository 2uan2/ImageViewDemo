package com.example.imageviewdemo

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PreviewCardAdapter(val data: List<Bitmap>) : RecyclerView.Adapter<PreviewCardAdapter.PreviewCardViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PreviewCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.preview_card, parent, false)

//        val displayMetrics = parent.context.resources.displayMetrics
//        val screenWidth = displayMetrics.widthPixels
//        val screenHeight = displayMetrics.heightPixels
//
//        val layoutParams = RecyclerView.LayoutParams(screenWidth / 4, screenHeight / 4)//ViewGroup.LayoutParams.WRAP_CONTENT)
//        view.layoutParams = layoutParams

        return PreviewCardViewHolder( view )
    }

    override fun onBindViewHolder(
        holder: PreviewCardViewHolder,
        position: Int
    ) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.text_view).text = position.toString()
            findViewById<ImageView>(R.id.image_view).setImageBitmap(data[position])

            Log.i("PreviewCardAdapter", "width, height passed in: ${data[position].width}, ${data[position].height}")
            Log.i("PreviewCardAdapter", "${width}, ${height}")
        }
    }

    override fun getItemCount(): Int = data.size

    inner class PreviewCardViewHolder(view: View) : RecyclerView.ViewHolder(view)
}