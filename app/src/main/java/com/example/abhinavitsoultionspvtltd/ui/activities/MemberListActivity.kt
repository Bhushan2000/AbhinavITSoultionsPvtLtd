package com.example.abhinavitsoultionspvtltd.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abhinavitsoultionspvtltd.R
import com.example.abhinavitsoultionspvtltd.data.room.entity.Member
import com.example.abhinavitsoultionspvtltd.data.viewModel.MemberViewModel
import com.example.abhinavitsoultionspvtltd.ui.adapter.MemberAdapter
import com.example.abhinavitsoultionspvtltd.utils.MemberState
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class MemberListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvMemberCount: TextView
    private val viewModel: MemberViewModel by viewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var fabAddMember: ExtendedFloatingActionButton
    private lateinit var adapter: MemberAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_member_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView()
        setUpRecyclerView()
        getDataFromRoom()
        getCountOfMembers()
        onClick()
    }

    private fun getCountOfMembers() {
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                val itemCount = adapter.itemCount
                if (itemCount == 0) {
                    tvMemberCount.text = "0"
                    recyclerView.visibility = View.GONE
                } else {
                    tvMemberCount.text = "$itemCount"
                    recyclerView.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun onClick() {
        fabAddMember.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun getDataFromRoom() {
        lifecycleScope.launchWhenStarted {
            viewModel.memberState.collect { state ->
                when (state) {
                    is MemberState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }

                    is MemberState.Success -> {
                        progressBar.visibility = View.GONE
                        adapter.submitList(state.members)
                    }

                    is MemberState.Error -> {
                        progressBar.visibility = View.GONE
                        Snackbar.make(recyclerView, state.message, Snackbar.LENGTH_SHORT).show()

                    }
                }
            }
        } // deprecated
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.memberState.collect { state ->
                    when (state) {
                        is MemberState.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }

                        is MemberState.Success -> {
                            progressBar.visibility = View.GONE
                            adapter.submitList(state.members)
                        }

                        is MemberState.Error -> {
                            progressBar.visibility = View.GONE
                            Snackbar.make(recyclerView, state.message, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }


    private fun setUpRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MemberAdapter()
        recyclerView.adapter = adapter
     }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
        tvMemberCount = findViewById(R.id.tvMemberCount)
        progressBar = findViewById(R.id.progressBar)
        fabAddMember = findViewById(R.id.fabAddMember)
    }
}