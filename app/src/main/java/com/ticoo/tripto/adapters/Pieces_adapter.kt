package com.ticoo.tripto.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ticoo.tripto.R
import com.ticoo.tripto.models.Pieces_model

class Pieces_adapter(// An Activity's Context.
    private val mcontext: Context,
    private var mRecyclerViewItems: List<Any>

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class MenuItemViewHolder internal constructor(view: View) :
        RecyclerView.ViewHolder(view) {
        val pieces_Name: TextView = view.findViewById(R.id.pieces_name) as TextView
        val pieces_decription:TextView = view.findViewById(R.id.pieces_decription)as TextView
        val pieces_date: TextView = view.findViewById(R.id.pieces_date) as TextView
        val pieces_culture:TextView = view.findViewById(R.id.pieces_culture)as TextView
        val pieces_period: TextView = view.findViewById(R.id.pieces_period) as TextView
        val pieces_place_of_origin:TextView = view.findViewById(R.id.pieces_place_of_origin)as TextView
        val pieces_type_of_object: TextView = view.findViewById(R.id.pieces_type_of_object) as TextView
        val pieces_dimension_details:TextView = view.findViewById(R.id.pieces_dimension_details)as TextView
        val pieces_material: TextView = view.findViewById(R.id.pieces_material) as TextView
        val pieces_Image: ImageView = view.findViewById(R.id.pieces_photo) as ImageView
    }

    class NativeExpressAdViewHolder internal constructor(view: View) :
        RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {
        return mRecyclerViewItems.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            MENU_ITEM_VIEW_TYPE -> {
                val menuItemLayoutView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.pieces_view, viewGroup, false)
                return MenuItemViewHolder(menuItemLayoutView)
            }
            else -> {
                val menuItemLayoutView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.pieces_view, viewGroup, false)
                return MenuItemViewHolder(menuItemLayoutView)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            MENU_ITEM_VIEW_TYPE -> {
                val menuItemHolder = holder as MenuItemViewHolder
                val menuItem = mRecyclerViewItems[position] as Pieces_model

                menuItemHolder.pieces_Name.text=menuItem.name
                menuItemHolder.pieces_decription.text=menuItem.decription
                menuItemHolder.pieces_date.text=menuItem.date
                menuItemHolder.pieces_culture.text=menuItem.culture
                menuItemHolder.pieces_period.text=menuItem.period
                menuItemHolder.pieces_place_of_origin.text=menuItem.place_of_origin
                menuItemHolder.pieces_type_of_object.text=menuItem.type_of_object
                menuItemHolder.pieces_dimension_details.text=menuItem.dimension_details
                menuItemHolder.pieces_material.text=menuItem.material
                Picasso.get().load(menuItem.photo).fit().centerInside().into(holder.pieces_Image)
                val tf : Typeface = Typeface.createFromAsset(mcontext.assets,"Cairo_Regular.ttf")
                menuItemHolder.pieces_Name.typeface = tf
                menuItemHolder.pieces_decription.typeface = tf
                menuItemHolder.pieces_date.typeface = tf
                menuItemHolder.pieces_culture.typeface=tf
                menuItemHolder.pieces_period.typeface = tf
                menuItemHolder.pieces_place_of_origin.typeface=tf
                menuItemHolder.pieces_type_of_object.typeface=tf
                menuItemHolder.pieces_dimension_details.typeface = tf
                menuItemHolder.pieces_material.typeface=tf
                // Add the menu item details to the menu item view.
            }
            else -> {
            }
        }
    }
    fun filterList(filteredList: ArrayList<Pieces_model>) {
        mRecyclerViewItems = filteredList
        notifyDataSetChanged()
    }
    companion object {
        private val MENU_ITEM_VIEW_TYPE = 0
    }
}