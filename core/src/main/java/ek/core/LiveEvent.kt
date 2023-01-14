package ek.core

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Тип livedata, который отдаёт данные только один раз в отличие от MutableLiveData.
 *
 * @param <Type>
 */
class LiveEvent<Type>: MutableLiveData<Type>() {
    private var isWaiting: AtomicBoolean = AtomicBoolean(false)


    /**
     * {@inheritDoc)
     */
    override fun observe(owner: LifecycleOwner, observer: Observer<in Type>) {
        super.observe(owner) { t ->
            if (isWaiting.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        }
    }

    /**
     * {@inheritDoc)
     */
    @MainThread
    override fun setValue(value: Type?) {
        isWaiting.set(true)
        super.setValue(value)
    }

    override fun postValue(value: Type?) {
        isWaiting.set(true)
        super.postValue(value)
    }

    /**
     * Поместить в очередь вызов без каких-либо данных
     */
    fun setCall() { value = null }

    /**
     * Поместить в очередь вызов без каких-либо данных
     */
    fun postCal() { postValue(null) }
}