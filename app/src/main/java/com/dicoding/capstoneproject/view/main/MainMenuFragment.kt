package com.dicoding.capstoneproject.view.main

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_DATA
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.core.data.Resource
import com.dicoding.capstoneproject.core.ui.KamusAdapter
import com.dicoding.capstoneproject.view.detail.DetailKamusActivity
import kotlinx.android.synthetic.main.fragment_main_menu.*
import kotlinx.android.synthetic.main.view_error.*
import org.koin.android.viewmodel.ext.android.viewModel



class MainMenuFragment : Fragment() {

    private  val mainMenuViewModel :MainMenuViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
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

            mainMenuViewModel.kamus.observe(viewLifecycleOwner, Observer { kamus ->
                if (kamus != null) {
                    when (kamus) {
                        is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progress_bar.visibility = View.GONE
                            kamusAdapter.setData(kamus.data)
                        }
                        is Resource.Error -> {
                            progress_bar.visibility = View.GONE
                            view_error.visibility = View.VISIBLE
                            tv_error.text = kamus.message ?: getString(R.string.ada_yang_error)
                        }
                    }
                }
            })

            with(rv_kamus) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = kamusAdapter
            }
        }
    }
}
