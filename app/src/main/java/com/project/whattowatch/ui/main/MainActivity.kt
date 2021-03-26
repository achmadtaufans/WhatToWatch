package com.project.whattowatch.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.whattowatch.R
import com.project.whattowatch.adapter.MovieAdapter
import com.project.whattowatch.common.base.BaseActivity
import com.project.whattowatch.databinding.ActivityMainBinding
import com.project.whattowatch.ui.detail.DetailActivity
import com.project.whattowatch.ui.dialog.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), MovieAdapter.ItemMovieClickCallback {
    val vm: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterMovie: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(binding) {
            lifecycleOwner = this@MainActivity
            viewModel = vm
        }

        with(vm) {
            actionType.observe(this@MainActivity, Observer {
                when (it) {
                    MainViewModel.UPDATE_ADAPTER -> {
                        adapterMovie.notifyDataSetChanged()
                    }
                    MainViewModel.OPEN_DIALOG_CATEGORY -> {
                        binding.rvMovieList.stopScroll()
                        showCategoryDialog()
                    }
                }
            })
            categoryType.observe(this@MainActivity, Observer {
                when (it) {
                    MainViewModel.POPULAR -> {
                        categoryTitle.value = resources.getString(R.string.popular)
                        categorySubtitle.value = resources.getString(R.string.subtitle_popular)
                    }
                    MainViewModel.UPCOMING -> {
                        categoryTitle.value = resources.getString(R.string.upcoming)
                        categorySubtitle.value = resources.getString(R.string.subtitle_upcoming)
                    }
                    MainViewModel.TOP_RATED -> {
                        categoryTitle.value = resources.getString(R.string.top_rated)
                        categorySubtitle.value = resources.getString(R.string.subtitle_top_rated)
                    }
                    MainViewModel.NOW_PLAYING -> {
                        categoryTitle.value = resources.getString(R.string.now_playing)
                        categorySubtitle.value = resources.getString(R.string.subtitle_now_playing)
                    }
                    MainViewModel.FAVORITE -> {
                        categoryTitle.value = resources.getString(R.string.favorite)
                        categorySubtitle.value = resources.getString(R.string.subtitle_favorite)
                    }
                }
            })
        }

        initView()
    }

    private fun initView() {
        setMovieList()
    }

    private fun setMovieList() {
        adapterMovie = MovieAdapter(vm.movieList, applicationContext)
        val lLayout = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.rvMovieList.layoutManager = lLayout
        binding.rvMovieList.setHasFixedSize(true)
        binding.rvMovieList.adapter = adapterMovie
        binding.rvMovieList.itemAnimator?.changeDuration = 0L
        vm.loadData()
        adapterMovie.setItemClickCallback(this)
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ITEM, vm.movieList[position].id.toString())
        intent.putExtra(DetailActivity.EXTRA_TITLE, vm.movieList[position].title)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, vm.categoryType.value.toString())
        startActivity(intent)
    }

    private fun showCategoryDialog() {
        val dialog = BottomSheetDialog.newInstance()
        dialog.isCancelable = true
        dialog.dialogClickListener = object : BottomSheetDialog.ItemClickListener {
            override fun onItemClickListener(category: String) {
                vm.setAdapterByCategory(category)
            }
        }
        dialog.show(supportFragmentManager, "")
    }

    override fun onResume() {
        super.onResume()
        vm.loadData()
    }
}