package com.ticoo.tripto.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ticoo.tripto.Pieces
import com.ticoo.tripto.R
import com.ticoo.tripto.models.Museums_model


class Museums_adapter(// An Activity's Context.
    private val mcontext: Context,
    private var mRecyclerViewItems: List<Any>


) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class MenuItemViewHolder internal constructor(view: View) :
        RecyclerView.ViewHolder(view) {
        val museum_Name: TextView = view.findViewById(R.id.musim_name) as TextView
        val museum_Image: ImageView = view.findViewById(R.id.musim_photo) as ImageView

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
                    .inflate(R.layout.museums_view, viewGroup, false)
                return MenuItemViewHolder(menuItemLayoutView)
            }
            else -> {
                val menuItemLayoutView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.museums_view, viewGroup, false)
                return MenuItemViewHolder(menuItemLayoutView)
            }
        }
    }

    @SuppressLint("SetTextI18n", "ShowToast")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            MENU_ITEM_VIEW_TYPE -> {
                val menuItemHolder = holder as MenuItemViewHolder
                val menuItem = mRecyclerViewItems[position] as Museums_model

                menuItemHolder.museum_Name.text=menuItem.name
                Picasso.get().load(menuItem.photo).fit().centerInside().into(holder.museum_Image)
                val tf : Typeface = Typeface.createFromAsset(mcontext.assets,"Cairo_Regular.ttf")
                menuItemHolder.museum_Name.typeface = tf
                // Add the menu item details to the menu item view.
                menuItemHolder.museum_Name.setOnClickListener {
                   // Toast.makeText(mcontext, "Wait for new Update", Toast.LENGTH_SHORT).show()
                    val myPrefs=mcontext.getSharedPreferences("Museums", Context.MODE_PRIVATE)
                    val editor = myPrefs.edit()
                    editor.putString("museums",menuItem.name)
                    editor.apply()
                    mcontext.startActivity(Intent(mcontext,Pieces::class.java))
                }
                menuItemHolder.museum_Image.setOnClickListener {
                   // Toast.makeText(mcontext, "Wait for new Update", Toast.LENGTH_SHORT).show()
                    val myPrefs=mcontext.getSharedPreferences("Museums", Context.MODE_PRIVATE)
                    val editor = myPrefs.edit()
                    editor.putString("museums",menuItem.name)
                    editor.apply()
                    mcontext.startActivity(Intent(mcontext,Pieces::class.java))
                }
            }
            else -> {
            }
        }
    }
    fun filterList(filteredList: ArrayList<Museums_model>) {
        mRecyclerViewItems = filteredList
        notifyDataSetChanged()
    }
    companion object {
        private val MENU_ITEM_VIEW_TYPE = 0
        private val NATIVE_EXPRESS_AD_VIEW_TYPE = 1
    }
}