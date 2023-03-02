package ek.core

import android.os.Build
import android.os.Bundle
import java.io.Serializable

inline fun <reified T : Serializable?> Bundle.getSerializableOptimized(name: String, clazz: Class<T>): T?
{
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        this.getSerializable(name, clazz)
    else
        @Suppress("DEPRECATION")
        this.getSerializable(name) as T
}