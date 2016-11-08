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

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO use this class manage ViewController
 * Created by GuDong on 05/11/2016 21:51.
 * Contact with gudong.name@gmail.com.
 */

public class VCHelper {
    private List<ViewController>mControlers;

    private ViewGroup mRootView;

    public VCHelper(ViewGroup rootView){
        mControlers = new ArrayList<>();
        mRootView = rootView;
    }

    public void attachControlerToView(ViewController viewControler){
        if(mRootView == null){
            throw new IllegalStateException("mRootView can not be null,please call rootView() firstly ");
        }
        viewControler.attachRoot(mRootView);
        mControlers.add(viewControler);
    }
}
