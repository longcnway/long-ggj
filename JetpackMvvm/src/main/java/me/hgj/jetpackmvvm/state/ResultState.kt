package me.hgj.jetpackmvvm.state

import androidx.lifecycle.MutableLiveData
import me.hgj.jetpackmvvm.network.AppException
import me.hgj.jetpackmvvm.network.BaseHttpResultBean
import me.hgj.jetpackmvvm.network.BaseResponse
import me.hgj.jetpackmvvm.network.ExceptionHandle

/**
 * 作者　: hegaojian
 * 时间　: 2020/4/9
 * 描述　: 自定义结果集封装类
 */
sealed class ResultState<out T> {
    companion object {
        fun <T> onAppSuccess(data: T): ResultState<T> = Success(data)
        fun <T> onAppLoading(loadingMessage: String): ResultState<T> = Loading(loadingMessage)
        fun <T> onAppError(error: AppException): ResultState<T> = Error(error)
    }

    data class Loading(val loadingMessage: String) : ResultState<Nothing>()
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val error: AppException) : ResultState<Nothing>()
}


/**
 * 处理返回值
 * @param result 请求结果
 */
fun <T> MutableLiveData<ResultState<T>>.paresResult(result: BaseHttpResultBean<T>) {
    value = if ("000" == result.head.code||("004"==result.head.code&&result.head.msg=="失败")) ResultState.onAppSuccess(result.body) else
        ResultState.onAppError(AppException(result.head.code, result.head.msg))
}

/**
 * 不处理返回值 直接返回请求结果
 * @param result 请求结果
 */
fun <T> MutableLiveData<ResultState<T>>.paresResult(result: T) {
    value = ResultState.onAppSuccess(result)
}

/**
 * 异常转换异常处理
 */
fun <T> MutableLiveData<ResultState<T>>.paresException(e: Throwable) {
    this.value = ResultState.onAppError(ExceptionHandle.handleException(e))
}

