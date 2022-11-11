package com.example.recyclerview.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.Supplier
import com.example.recyclerview.adapter.ItemsDetailsLookup
import com.example.recyclerview.adapter.ItemsKeyProvider
import com.example.recyclerview.adapter.MovieAdapter
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ActionMode.Callback {

    private lateinit var tracker: SelectionTracker<String>
    private lateinit var adapter: MovieAdapter
    private lateinit var binding: ActivityMainBinding
    private var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initializing LayoutManager & Divider
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this,RecyclerView.VERTICAL))

        //Creating a Custom adapter
        adapter = MovieAdapter(Supplier.Movies)
        binding.recyclerView.adapter = adapter

        //RecyclerView Selection Based Library
        tracker = SelectionTracker.Builder(
            "selectionItem",
            binding.recyclerView,
            ItemsKeyProvider(adapter),
            ItemsDetailsLookup(binding.recyclerView),
            StorageStrategy.createStringStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        tracker.addObserver(object : SelectionTracker.SelectionObserver<String>() {
            override fun onSelectionChanged() {
                super.onSelectionChanged()
                if (actionMode == null) {
                    actionMode = startSupportActionMode(this@MainActivity)
                }
                val items = tracker.selection.size()
                if (items > 0) {
                    actionMode?.title = "$items Selected"
                } else {
                    actionMode?.finish()
                }
            }
        }
        )
        adapter.tracker = tracker
        /**
         * Another way of handling the listener,
         * without interface.
         */
        adapter.onItemClick = { s: String, i: Int ->
            Toast.makeText(this, "$s , $i", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.menu_item,menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean = true

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.menu_delete -> {
                //write the logic here to remove
                val selected = adapter.movieList.filterNot {
                    tracker.selection.contains(it.title)
                }.toMutableList()
                adapter.movieList = selected
                adapter.notifyDataSetChanged()
                actionMode?.finish()
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        tracker.clearSelection()
        actionMode = null
    }


}