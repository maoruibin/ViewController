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

package name.gudong.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import name.gudong.demo.entity.HouseDetail;
import name.gudong.demo.view.HouseCommentViewControler;
import name.gudong.demo.view.HouseDescViewControler;
import name.gudong.demo.view.HouseParamViewControler;
import name.gudong.demo.view.HousePhotoViewController;
import name.gudong.viewcontrol.ViewControler;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.ll_container)
    LinearLayout mLlContainer;
    @Bind(R.id.pb)
    ProgressBar mPb;

private ViewControler<List<String>> mHousePhotoViewController;
private ViewControler<HouseDetail.Param> mHouseParamViewControler;
private ViewControler<List<String>> mHouseCommentViewControler;
private ViewControler<String> mHouseDescViewControler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);

        mHousePhotoViewController = new HousePhotoViewController(this);
        mHouseParamViewControler = new HouseParamViewControler(this);
        mHouseDescViewControler = new HouseDescViewControler(this);
        mHouseCommentViewControler = new HouseCommentViewControler(this);

        mHousePhotoViewController.attachRoot(mLlContainer);
        mHouseParamViewControler.attachRoot(mLlContainer);
        mHouseDescViewControler.attachRoot(mLlContainer);
        mHouseCommentViewControler.attachRoot(mLlContainer);

        getData();
    }

    private void fillData(HouseDetail detail) {
        mHousePhotoViewController.fillData(detail.photos);
        mHouseParamViewControler.fillData(detail.param);
        mHouseDescViewControler.fillData(detail.desc);
        mHouseCommentViewControler.fillData(detail.comments);
    }

    /**
     * mock fetch data
     */
    private void getData() {
        mPb.setVisibility(View.VISIBLE);
        mLlContainer.setVisibility(View.GONE);
        mLlContainer.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPb.setVisibility(View.GONE);
                mLlContainer.setVisibility(View.VISIBLE);

                HouseDetail detail = mockHouseData();
                fillData(detail);
            }
        }, 1000);
    }

    private HouseDetail mockHouseData() {
        HouseDetail detail = new HouseDetail();

        List<String> photos = new ArrayList<>();
        photos.add("http://cdn.mse.mlwplus.com/avenger/mlw_default_18.jpg@540w.jpg");
        photos.add("http://cdn.mse.mlwplus.com/avenger/mlw_default_02.jpg@540w.jpg");

        List<String> comments = new ArrayList<>();
        comments.add("GuDong: 房子不错,但是太远了,上班很不方便啊,每天折腾3小时上班,小身板折腾不起啊。");
        comments.add("Jack: a nice house,i like, but the price is too ...");
        comments.add("Rose: good source");
        comments.add("MJ: just so so,just so so,just so so,just so so,");


        HouseDetail.Param param = new HouseDetail.Param();
        param.address = "ChaoYang";
        param.area = 80;
        param.city = "Beijing";
        param.createTime = System.currentTimeMillis();
        param.owner = "Mr Mao";


        detail.photos = photos;
        detail.comments = comments;
        detail.param = param;
        detail.desc = "小区离宋家庄地铁站只有100米距离，宋家庄是一个交通枢纽，有地铁5号线10号线还有亦庄线，所以说交通非常方便\n" +
                "房子是一个三居室，出租主卧，房间里面很干净，家电齐全，可以拎包入住\n" +
                "小区周边有多个大型超市和商场，有首开福茂，美廉美超市，物美超市等等生活和购物都很方便\n" +
                "要求租客有正当工作，下班不要打扰邻居间的休息，别扰民就行";
        return detail;
    }

}
