package com.dicoding.capstoneproject.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.core.domain.model.Kamus
import com.dicoding.capstoneproject.core.ui.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_kamus.*
import kotlinx.android.synthetic.main.content_detail_kamus.*


class DetailKamusActivity : AppCompatActivity() {

    private lateinit var detailKamusViewModel: DetailKamusViewModel

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kamus)
        setSupportActionBar(toolbar)

        val factory = ViewModelFactory.getInstance(this)
        detailKamusViewModel = ViewModelProvider(this, factory)[DetailKamusViewModel::class.java]

        val detailKamus = intent.getParcelableExtra<Kamus>(EXTRA_DATA)
        showDetailKamus(detailKamus)
    }

    private fun showDetailKamus(detailKamus: Kamus?) {
        detailKamus?.let {
            supportActionBar?.title = detailKamus.namakata
            tv_detail_description.text = detailKamus.deskripsi
            Glide.with(this@DetailKamusActivity)
                .load(detailKamus.image)
                .into(text_detail_image)

            var statusFavorite = detailKamus.isFavorite
            setStatusFavorite(statusFavorite)
            fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailKamusViewModel.setFavoriteKamus(detailKamus, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_putih))
        } else {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_tidak_putih))
        }
    }
}
