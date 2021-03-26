package com.project.whattowatch.ui.detail

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.whattowatch.R
import com.project.whattowatch.adapter.MovieAdapter
import com.project.whattowatch.adapter.ReviewerAdapter
import com.project.whattowatch.common.base.BaseActivity
import com.project.whattowatch.common.util.Utils
import com.project.whattowatch.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {
    companion object {
        const val EXTRA_ITEM = "MOVIE_ID"
        const val EXTRA_CATEGORY = "CATEGORY_TYPE"
        const val EXTRA_TITLE = "MOVIE_TITLE"
    }

    val vm: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private lateinit var adapterReviewer: ReviewerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        with(binding) {
            lifecycleOwner = this@DetailActivity
            viewModel = vm
        }

        with(vm) {
            imagePath.observe(this@DetailActivity, Observer {
                if (!imagePath.value.isNullOrEmpty()) {
                    Utils.showImage(imagePath.value ?: "", binding.itemMoviePoster, binding.itemMoviePoster)
                }
            })
            actionType.observe(this@DetailActivity, Observer {
                when (it) {
                    DetailViewModel.UPDATE_ADAPTER -> adapterReviewer.notifyDataSetChanged()
                }
            })
        }

        initView()
    }

    private fun initView() {
        setupToolbar()

        if (intent.getStringExtra(EXTRA_ITEM).isNotEmpty()) {
            vm.movieId.value = intent.getStringExtra(EXTRA_ITEM).toLong()
        }

        setReviewerList()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (intent.getStringExtra(EXTRA_CATEGORY).isNotEmpty()) {
            if (intent.getStringExtra(EXTRA_CATEGORY) == resources.getString(R.string.favorite)) {
                supportActionBar?.title = intent.getStringExtra(EXTRA_TITLE)
            } else {
                supportActionBar?.title = intent.getStringExtra(EXTRA_CATEGORY)
            }
        } else {
            supportActionBar?.title = ""
        }
    }

    private fun setReviewerList() {
        adapterReviewer = ReviewerAdapter(vm.reviewerList, applicationContext)
        val lLayout = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.rvReviewItem.layoutManager = lLayout
        binding.rvReviewItem.setHasFixedSize(true)
        binding.rvReviewItem.adapter = adapterReviewer
        binding.rvReviewItem.itemAnimator?.changeDuration = 0L
        vm.loadData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}