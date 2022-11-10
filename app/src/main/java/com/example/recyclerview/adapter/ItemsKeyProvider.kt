package com.example.recyclerview.adapter

import androidx.recyclerview.selection.ItemKeyProvider

class ItemsKeyProvider(private val movieAdapter: MovieAdapter): ItemKeyProvider<String>(SCOPE_CACHED) {
    override fun getKey(position: Int): String {
        return movieAdapter.movieList[position].title
    }

    override fun getPosition(key: String): Int {
        return movieAdapter.movieList.indexOfFirst { it.title == key }
    }
}