package com.murilobatista.rxretrofitk.main
import com.murilobatista.rxretrofitk.model.Post

interface MainContract {
    interface View {
        fun showProgressBar()
        fun disableButton()
        fun dismissProgressBar()
        fun enableButton()
        fun setPostToText(post: Post)
        fun showError(message: String)
    }

    interface Presenter {
        fun getPost()
        fun dettachView()
    }
}