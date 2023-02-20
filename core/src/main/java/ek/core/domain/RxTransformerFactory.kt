package ek.core.domain

import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxTransformerFactory {
    /**
     * io -> main thread
     * для запросов удаленного/локального репозитория
     *
     * @param <R>любой тип
     * @return сингл поток с любым типом
     */
    fun <R> getIoToMainSingle(): SingleTransformer<R?, R?> {
        return SingleTransformer { upstream: Single<R> ->
            upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * io -> main schedulers
     * для запросов из удалённого/локального репозитория
     *
     * @return поток без типа
     */
    fun getIoToMainCompletable(): CompletableTransformer {
        return CompletableTransformer { upstream: Completable ->
            upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

}