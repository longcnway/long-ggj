package me.hgj.jetpackmvvm.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.Stack;

/**
* Author:jianbo
* <P>
* Create Time:2019/4/23 20:10
* <P>
* Email:1245092675@qq.com
* <P>
* Describe:activity活动管理器
*/

public class AppActivityManager {

    private static AppActivityManager mInstance;

    private final Stack<Activity> mActivityStack = new Stack<>();

    private AppActivityManager() {
    }

    public static AppActivityManager getInstance() {
        synchronized (AppActivityManager.class) {
            if (mInstance == null) {
                mInstance = new AppActivityManager();
            }
        }
        return mInstance;
    }

    /**
     * 注册ActivityManager
     *
     * @param application
     */
    public void register(Application application, ActivityManagerLifecycleCallbackImpl callback) {
        application.registerActivityLifecycleCallbacks(callback);
    }

    /**
     * 注册ActivityManager
     *
     * @param application
     */
    public void register(Application application) {
        register(application, new ActivityManagerLifecycleCallbackImpl());
    }

    public void addActivity(Activity activity) {
        if (mActivityStack != null) {
            mActivityStack.push(activity);
        }
    }

    /**
     * 移除栈内指定activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (mActivityStack != null) {
            mActivityStack.remove(activity);
        }
    }

    /**
     * finish掉class对应的所有activity
     *
     * @param cls 要关闭的Activity Class
     */
    public void finishActivity(Class<? extends Activity>... cls) {
        if (mActivityStack != null) {
            for (int i = mActivityStack.size() - 1; i >= 0; i--) {
                Activity activity = mActivityStack.get(i);
                for (Class<? extends Activity> c : cls) {
                    if (c.getName().equals(activity.getClass().getName())) {
                        finishActivity(activity);
                    }
                }
            }
        }
    }


    /**
     * 关闭栈顶的Activity
     */
    public void finishTopActivity() {
        if (mActivityStack != null) {
            Activity pop = mActivityStack.pop();
            if (!pop.isFinishing()) {
                pop.finish();
            }
        }
    }


    /**
     * finish除白名单以外的所有activity
     *
     * @param activityWhitelist 要保留的activity
     */
    public void finishAllActivityByWhitelist(Class<? extends Activity>... activityWhitelist) {
        if (mActivityStack != null) {
            for (int i = mActivityStack.size() - 1; i >= 0; i--) {
                Activity activity = mActivityStack.get(i);
                for (Class<? extends Activity> c : activityWhitelist) {
                    if (c.getName().equals(activity.getClass().getName())) {
                        break;
                    } else {
                        finishActivity(activity);
                    }
                }
            }
        }
    }


    /**
     * finish所有activity
     */
    public void finishAllActivity() {
        if (mActivityStack != null) {
            for (int i = mActivityStack.size() - 1; i >= 0; i--) {
                Activity activity = mActivityStack.get(i);
                finishActivity(activity);
            }
        }
    }


    private void finishActivity(Activity activity) {
        if (mActivityStack != null) {
            if (activity != null) {
                if (!activity.isFinishing()) {
                    mActivityStack.remove(activity);
                    activity.finish();
                    activity = null;
                }
            }
        }
    }

    /**
     * 获取Activity栈
     *
     * @return
     */
    public Stack<Activity> getActivityStack() {
        if (mActivityStack != null) {
            return mActivityStack;
        }
        return null;
    }

    /**
     * 同过类名获取Activity
     *
     * @param cls
     * @return
     */
    public Activity getActivityToClass(Class<?> cls) {
        if (mActivityStack != null) {
            for (Activity activity : mActivityStack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        }
        return null;
    }

    /**
     * @param activity
     * @return 是否存在此activity
     */
    public boolean isContainsActivity(Activity activity) {
        return mActivityStack.contains(activity);
    }

    /**
     * 获取栈内activity数量
     *
     * @return
     */
    public int getActivityCount() {
        if (mActivityStack != null) {
            return mActivityStack.size();
        }
        return 0;
    }


    /**
     * 退出应用程序
     *
     * @param context
     * @param isNormalExit 是否正常退出程序
     */
    public void AppExit(Context context, boolean isNormalExit) {
        try {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            if (isNormalExit) {
                System.exit(0);//正常退出，程序正常执行结束退出
            } else {
                System.exit(1);//非正常退出，就是说无论程序正在执行与否，都退出
            }
        } catch (Exception e) {
        }
    }
}
