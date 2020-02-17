package com.murilobatista.rxretrofitk.main
import com.murilobatista.rxretrofitk.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainInteractor(private var output: MainContract.InteractorOutput?) : MainContract.Interactor {
    private val api by lazy { Api.create() }
    private var disposable: Disposable? = null
    override fun getPost() {
        disposable = api
            .getPost()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { output?.onSuccess(it[0])},
                { output?.onFailure(it)}
            )
    }
}