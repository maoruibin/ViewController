/*
 * Copyright 2016 GuDong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package name.gudong.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * core of view controler
 * Created by GuDong on 7/13/16 22:42.
 * Contact with gudong.name@gmail.com.
 * @param <T> data type for this ViewController, a view controler should map a data type
 */
public abstract class ViewController<T> {
    private T mData;
    private Context mContext;
    private View mView;

    public ViewController(Context context) {
        this.mContext = context;
    }

    public void attachRoot(ViewGroup root) {
        int resLayoutId = resLayoutId();
        if (resLayoutId <= 0) {
            throw new IllegalStateException("Please check your layout id in resLayoutId() method");
        }
        mView = LayoutInflater.from(mContext).inflate(resLayoutId, root, true);
        onCreatedView(mView);
    }

    public void fillData(T data) {
        this.mData = data;
        if (mData != null) {
            onBindView(data);
        }
    }

    /**
     * indicate layout id of this ViewControl
     *
     * @return layout id
     */
    protected abstract int resLayoutId();

    /**
     * view has been
     *
     * @param view real view
     */
    protected abstract void onCreatedView(View view);

    /**
     * bind data to view
     *
     * @param data data
     */
    protected abstract void onBindView(T data);

    public Context getContext() {
        return mContext;
    }

    public View getView() {
        return mView;
    }

    public T getData() {
        return mData;
    }
}
