package com.murilobatista.rxretrofitk.main
import com.murilobatista.rxretrofitk.model.Post
import com.murilobatista.rxretrofitk.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainPresenter : MainContract.Presenter {
    private val api by lazy { Api.create() }
    private var disposable: Disposable? = null
    var view: MainContract.View? = null

    override fun getPost() {
        view?.disableButton()
        view?.showProgressBar()
        disposable = api
            .getPost()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse, this::handleError)
    }

    private fun handleResponse(post: List<Post>) {
        view?.setPostToText(post[0])
        view?.dismissProgressBar()
        view?.enableButton()
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
        view?.showError("Erro ao carregar postagens")
        view?.dismissProgressBar()
        view?.enableButton()
    }

    override fun dettachView() {
        view = null
    }
}