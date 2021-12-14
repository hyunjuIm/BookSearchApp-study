package com.example.aop_part3_chapter4

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.aop_part3_chapter4.databinding.ActivityDetailBinding
import com.example.aop_part3_chapter4.model.Book
import com.example.aop_part3_chapter4.model.Review

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = getAppDatabase(this)

        val model = intent.getParcelableExtra<Book>("bookModel")
        binding.titleTextView.text = model?.title.orEmpty()
        binding.descriptionTextView.text = model?.contents.orEmpty()

        Glide.with(binding.coverImageView.context)
            .load(model?.thumbnail.orEmpty())
            .into(binding.coverImageView)

        val id = model?.id?.split(" ")?.get(0)?.substring(0,4)

        Log.d(TAG, "DetailActivity - $id")

        Thread {
            val review = db.reviewDao().getOneReview(id?.toInt() ?: 0)

            if (review != null) {
                runOnUiThread {
                    binding.reviewEditText.setText(review.review.orEmpty())
                }
            }
        }.start()

        binding.saveButton.setOnClickListener {
            Thread {
                db.reviewDao().saveReview(
                    Review(
                        id?.toInt() ?: 0,
                        binding.reviewEditText.text.toString()
                    )
                )

            }.start()
        }
    }
}