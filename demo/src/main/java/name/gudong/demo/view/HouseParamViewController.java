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

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import name.gudong.demo.R;
import name.gudong.demo.entity.HouseDetail;
import name.gudong.viewcontroller.ViewController;

/**
 * Created by GuDong on 27/10/2016 22:33.
 * Contact with gudong.name@gmail.com.
 */

public class HouseParamViewController extends ViewController<HouseDetail.Param> {
    @Bind(R.id.tv_param_one)
    TextView mTvParamOne;
    @Bind(R.id.tv_param_two)
    TextView mTvParamTwo;
    @Bind(R.id.tv_param_three)
    TextView mTvParamThree;
    @Bind(R.id.tv_param_four)
    TextView mTvParamFour;
    @Bind(R.id.tv_param_five)
    TextView mTvParamFive;
    @Bind(R.id.tv_param_six)
    TextView mTvParamSix;

    public HouseParamViewController(Context context) {
        super(context);
    }

    @Override
    protected int resLayoutId() {
        return R.layout.detail_house_param;
    }

    @Override
    protected void onCreatedView(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    protected void onBindView(HouseDetail.Param data) {
        mTvParamTwo.setText(data.city+"\n城市");
        mTvParamOne.setText(data.address+"\n地址");
        mTvParamThree.setText(data.owner+"\n业主");
        mTvParamFour.setText(data.area+"M"+"\n面积");
        mTvParamFive.setText(formatTime(data.createTime)+"\n发布时间");
        mTvParamSix.setText("(空)");
    }

    private String formatTime(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(time));
    }

}
