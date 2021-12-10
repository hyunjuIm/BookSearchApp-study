package com.example.aop_part3_chapter4.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aop_part3_chapter4.databinding.ItemBookBinding
import com.example.aop_part3_chapter4.model.Book

class BookAdapter:ListAdapter<Book, BookAdapter.BookItemViewHolder>(diffUtil) {

    inner class BookItemViewHolder(private val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}