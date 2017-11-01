package com.va.kotlintaste.net.retrofit;

import android.text.TextUtils;

import com.va.kotlintaste.net.retrofit.constant.NameConstant;
import com.va.kotlintaste.net.retrofit.listener.OnUrlChangeListener;
import com.va.kotlintaste.net.retrofit.parse.DefaultUrlParse;
import com.va.kotlintaste.net.retrofit.parse.UrlParse;
import com.va.kotlintaste.net.retrofit.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Junmeng.Chen
 * @date 2017/11/1
 */

public class RetrofitManager {

    public static final String TAG = RetrofitManager.class.getSimpleName();

    private static final boolean DEPENDENCY_OKHTTP;

    private UrlParse mUrlParser;

    private Interceptor mInterceptor;

    private boolean isRun = true;

    private final Map<String, HttpUrl> mDomainNameHub = new HashMap<>();

    private final List<OnUrlChangeListener> mListeners = new ArrayList<>();

    private static class RetrofitManagerHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static final RetrofitManager getInstance() {
        return RetrofitManagerHolder.INSTANCE;
    }

    static {
        boolean hasDependency;
        try {
            Class.forName("okhttp3.OkHttpClient");
            hasDependency = true;
        } catch (ClassNotFoundException e) {
            hasDependency = false;
        }
        DEPENDENCY_OKHTTP = hasDependency;
    }

    /**
     * 将 {@link okhttp3.OkHttpClient.Builder} 传入,配置一些本管理器需要的参数,
     * 主要是通过设置 请求拦截器 拦截发出的请求 通过header 的标记 动态的替换baseUrl
     *
     * @param builder
     * @return
     */
    public OkHttpClient.Builder with(OkHttpClient.Builder builder) {
        return builder.addInterceptor(mInterceptor);
    }

    public RetrofitManager() {
        //使用 必须依赖OkHttp
        if (!DEPENDENCY_OKHTTP) {
            throw new IllegalStateException("must be dependency okHttp");
        }

        setUrlParse(new DefaultUrlParse());

        this.mInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                if (!isRun) {
                    return chain.proceed(chain.request());
                }
                return chain.proceed(proceedRequest(chain.request()));
            }
        };
    }

    /**
     * 设置 解析器
     *
     * @param urlParse
     */
    private void setUrlParse(UrlParse urlParse) {
        this.mUrlParser = urlParse;
    }

    /**
     * 对拦截得到的 request 进行必要的加工 关键性的代码
     *
     * @param request
     * @return
     */
    private Request proceedRequest(Request request) {

        Request.Builder newBuilder = request.newBuilder();

        String domainName = obtainDomainNameFromHeaders(request);

        HttpUrl httpUrl;

        Object[] listeners = listenersToArray();

        if (!TextUtils.isEmpty(domainName)) {
            notifyListener(request, domainName, listeners);
            httpUrl = fetchDomain(domainName);
            newBuilder.removeHeader(NameConstant.DOMAIN_NAME);
        } else {
            notifyListener(request, NameConstant.GLOBAL_DOMAIN_NAME, listeners);
            httpUrl = fetchDomain(NameConstant.GLOBAL_DOMAIN_NAME);
        }

        if (httpUrl != null) {
            HttpUrl newUrl = mUrlParser.parseUrl(httpUrl, request.url());
            if (listeners != null) {
                int length = listeners.length;
                for (int i = 0; i < length; i++) {
                    ((OnUrlChangeListener) listeners[i]).onUrlChanged(newUrl, request.url());
                }
            }
            return newBuilder.url(newUrl).build();
        }

        return newBuilder.build();
    }

    /**
     * 从Header 中拿到 设置进去的 信息
     *
     * @param request
     * @return
     */
    private String obtainDomainNameFromHeaders(Request request) {
        List<String> headers = request.headers(NameConstant.DOMAIN_NAME);
        if (headers == null || headers.size() == 0) {
            return null;
        }
        if (headers.size() > 1) {
            throw new IllegalArgumentException("only one Domain-name in the headers");
        }
        return request.header(NameConstant.DOMAIN_NAME);
    }

    private Object[] listenersToArray() {
        Object[] listeners = null;
        synchronized (mListeners) {
            if (mListeners.size() > 0) {
                listeners = mListeners.toArray();
            }
        }
        return listeners;
    }

    private void notifyListener(Request request, String domainName, Object[] listeners) {
        if (listeners != null) {
            for (int i = 0; i < listeners.length; i++) {
                ((OnUrlChangeListener) listeners[i]).onUrlChangeBefore(request.url(), domainName);
            }
        }
    }


    /**
     * 管理器是否在运行
     *
     * @return
     */
    public boolean isRun() {
        return this.isRun;
    }

    /**
     * 控制管理器是否运行,在每个域名地址都已经确定,不需要再动态更改时可设置为 false
     *
     * @param run
     */
    public void setRun(boolean run) {
        this.isRun = run;
    }

    /**
     * 全局动态替换 BaseUrl，优先级： Header中配置的url > 全局配置的url
     * 除了作为备用的 BaseUrl ,当你项目中只有一个 BaseUrl ,但需要动态改变
     * 这种方式不用在每个接口方法上加 Header,也是个很好的选择
     *
     * @param url
     */
    public void setGlobalDomain(String url) {
        synchronized (mDomainNameHub) {
            mDomainNameHub.put(NameConstant.GLOBAL_DOMAIN_NAME, Utils.checkUrl(url));
        }
    }

    /**
     * 获取全局 BaseUrl
     */
    public HttpUrl getGlobalDomain() {
        return mDomainNameHub.get(NameConstant.GLOBAL_DOMAIN_NAME);
    }

    /**
     * 移除全局 BaseUrl
     */
    public void removeGlobalDomain() {
        synchronized (mDomainNameHub) {
            mDomainNameHub.remove(NameConstant.GLOBAL_DOMAIN_NAME);
        }
    }

    /**
     * 存放 Domain 的映射关系
     *
     * @param domainName
     * @param domainUrl
     */
    public void putDomain(String domainName, String domainUrl) {
        synchronized (mDomainNameHub) {
            mDomainNameHub.put(domainName, Utils.checkUrl(domainUrl));
        }
    }

    /**
     * 取出对应 DomainName 的 Url
     *
     * @param domainName
     * @return
     */
    public HttpUrl fetchDomain(String domainName) {
        return mDomainNameHub.get(domainName);
    }

    public void removeDomain(String domainName) {
        synchronized (mDomainNameHub) {
            mDomainNameHub.remove(domainName);
        }
    }

    public void clearAllDomain() {
        mDomainNameHub.clear();
    }

    public boolean haveDomain(String domainName) {
        return mDomainNameHub.containsKey(domainName);
    }

    public int domainSize() {
        return mDomainNameHub.size();
    }

}
