package com.murilobatista.rxretrofitk.main
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.murilobatista.rxretrofitk.R
import com.murilobatista.rxretrofitk.model.Post
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private val presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.view = this
        btnGetPosts.setOnClickListener { presenter.getPost() }
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissProgressBar()
        enableButton()
        presenter.dettachView()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun disableButton() {
        btnGetPosts.isEnabled = false
        btnGetPosts.isClickable = false
    }

    override fun dismissProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun enableButton() {
        btnGetPosts.isEnabled = true
        btnGetPosts.isClickable = true
    }

    override fun setPostToText(post: Post) {
        txtTitle.text = post.title
        txtBody.text = post.body
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}