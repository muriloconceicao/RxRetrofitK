package com.murilobatista.rxretrofitk.main
import com.murilobatista.rxretrofitk.model.Post

class MainPresenter : MainContract.Presenter, MainContract.InteractorOutput {
    private var interactor: MainContract.Interactor? = MainInteractor(this)
    var view: MainContract.View? = null

    override fun getPost() {
        view?.disableButton()
        view?.showProgressBar()
        interactor?.getPost()
    }
    override fun dettachView() {
        view = null
    }

    override fun onSuccess(post: Post) {
        view?.setPostToText(post)
        view?.dismissProgressBar()
        view?.enableButton()
    }

    override fun onFailure(t: Throwable) {
        view?.showError("Erro")
        view?.dismissProgressBar()
        view?.enableButton()
    }
}