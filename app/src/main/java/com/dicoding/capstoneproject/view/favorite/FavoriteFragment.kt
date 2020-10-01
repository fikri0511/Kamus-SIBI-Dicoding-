package com.dicoding.capstoneproject.view.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.core.ui.KamusAdapter
import com.dicoding.capstoneproject.core.ui.ViewModelFactory
import com.dicoding.capstoneproject.view.detail.DetailKamusActivity
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val kamusAdapter = KamusAdapter()
            kamusAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailKamusActivity::class.java)
                intent.putExtra(DetailKamusActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            favoriteViewModel.favoriteKamus.observe(viewLifecycleOwner, Observer { dataKamus ->
                kamusAdapter.setData(dataKamus)
                view_kosong.visibility = if (dataKamus.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(rv_kamus) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = kamusAdapter
            }
        }
    }
}
