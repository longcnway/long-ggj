package me.hgj.jetpackmvvm.network.exception;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;


/**
 * Author:jianbo
 * <p>
 * Create Time:2019/4/16 9:40
 * <p>
 * Email:1245092675@qq.com
 * <p>
 * Describe:网络统一异常处理
 */

public class NetworkError {

    /**
     * @param context 可以用于跳转Activity等操作
     */
    public static void error(Context context, Throwable throwable) {
        RetrofitException.ResponseThrowable responseThrowable = RetrofitException.retrofitException(throwable);
        // 此处可以通过判断错误代码来实现根据不同的错误代码做出相应的反应
        switch (responseThrowable.code) {
            case HttpStatusCode.CODE_NOT_LOGON:
                Toast.makeText(context, responseThrowable.message, Toast.LENGTH_SHORT).show();
                // 跳转到登陆页面
                ARouter.getInstance().build("/common/Logon")
                        .withTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
                        .navigation();

                break;
            case RetrofitException.ERROR.UNKNOWN:
            case RetrofitException.ERROR.PARSE_ERROR:
            case RetrofitException.ERROR.NETWORD_ERROR:
            case RetrofitException.ERROR.HTTP_ERROR:
            case RetrofitException.ERROR.SSL_ERROR:
                Toast.makeText(context, responseThrowable.message, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, responseThrowable.message, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
