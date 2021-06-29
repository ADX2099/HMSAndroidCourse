package com.adx2099.frag_perm_intents

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adx2099.frag_perm_intents.databinding.FragmentIntentsBinding

class IntentsFragment : Fragment() {
    private var _binding: FragmentIntentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentIntentsBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnNavegador.setOnClickListener {
            val webpage: Uri = Uri.parse("https://developer.huawei.com/en/")
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(intent)
        }
        binding.btnCall.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:55445565898")
            }
            startActivity(intent)
        }
        binding.btnPetalSerach.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
                putExtra(SearchManager.QUERY, "Petal Search")
            }
                startActivity(intent)

        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}