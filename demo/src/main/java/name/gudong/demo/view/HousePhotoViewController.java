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
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import name.gudong.demo.R;
import name.gudong.viewcontroller.ViewController;

/**
 * Created by GuDong on 27/10/2016 22:31.
 * Contact with gudong.name@gmail.com.
 */

public class HousePhotoViewController extends ViewController<List<String>> {
    @Bind(R.id.view_page)
    ViewPager mViewPage;
    private PhotoAdapter mAdapter;
    public HousePhotoViewController(Context context) {
        super(context);
    }

    @Override
    protected int resLayoutId() {
        return R.layout.detail_head_layout;
    }

    @Override
    protected void onCreatedView(View view) {
        ButterKnife.bind(this,view);
        mViewPage = (ViewPager) view.findViewById(R.id.view_page);
        mAdapter = new PhotoAdapter();
        mViewPage.setAdapter(mAdapter);
    }

    @Override
    protected void onBindView(List<String> data) {
        mAdapter.fillData(data);
    }

    class PhotoAdapter extends PagerAdapter{

        List<String>mPhotos;
        List<View>mPhotoViews ;

        public PhotoAdapter() {
            mPhotos = new ArrayList<>();
            mPhotoViews = new ArrayList<>();
        }

        void fillData(List<String>photos){
            this.mPhotos = photos;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            Logger.i("size "+mPhotos.size());
            return mPhotos.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView(mPhotoViews.get(position));
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(getContext()).load(mPhotos.get(position)).into(imageView);
            container.addView(imageView);
            mPhotoViews.add(imageView);
            return imageView;
        }
    }
}
