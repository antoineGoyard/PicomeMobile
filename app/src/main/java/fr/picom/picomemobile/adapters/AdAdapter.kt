package fr.picom.picomemobile.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import fr.picom.picomemobile.R
import fr.picom.picomemobile.models.Ad

class AdAdapter(private val adList: ArrayList<Ad>, private val context: Context)
    : RecyclerView.Adapter<AdAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return(ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ad, parent, false)
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ad : Ad = adList[position]

        holder.bind(ad)

        holder.itemView.setOnClickListener {
            // Ouvre wikipédia dans un navigateur
        }
    }

    override fun getItemCount(): Int = adList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun loadImageFromUrl(urlString: String, imageView: ImageView) {
            Glide.with(imageView.context)
                .load(urlString)
                .apply(RequestOptions().centerCrop())
                .into(imageView)
        }

        val imageView: ImageView = itemView.findViewById(R.id.image)
        val titleView: TextView = itemView.findViewById(R.id.title)

        fun bind(ad: Ad) {
            if (ad.image !=null) {
                loadImageFromUrl(ad.image, imageView)
            }else
                loadImageFromUrl("https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg?20200913095930", imageView)
            titleView.text = ad.title

        }
    }



}
