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

package name.gudong.demo.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import name.gudong.demo.R;
import name.gudong.viewcontroller.ViewController;

/**
 * Created by GuDong on 05/11/2016 21:30.
 * Contact with gudong.name@gmail.com.
 */

public class HouseDescViewController extends ViewController<String> {
    @Bind(R.id.tv_desc)
    TextView mTvDesc;

    public HouseDescViewController(Context context) {
        super(context);
    }

    @Override
    protected int resLayoutId() {
        return R.layout.detail_desc_layout;
    }

    @Override
    protected void onCreatedView(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    protected void onBindView(String data) {
        mTvDesc.setText(data);
    }
}
