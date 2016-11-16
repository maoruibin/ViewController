# ViewController
[![](https://jitpack.io/v/maoruibin/ViewController.svg)](https://jitpack.io/#maoruibin/ViewController)
[![](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/maoruibin/ViewController/blob/master/LICENSE.txt)

A view controller manages a set of views that make up a portion of your app’s user interface, it aims to make ui develop change more clear and flexible.

English | [中文](./README_CN.md) | [Android 复杂界面开发实践之 ViewController: 介绍](http://gudong.name/2016/11/06/viewcontroler-introduce.html)

## demo

<img src="http://7xr9gx.com1.z0.glb.clouddn.com/practice-demo.png">

## Advantage

* Make UI development components,resolve problem of code bloated.
* flexible, one component can used in more place. 
* easy use, easy develop. 


## Usage 

As a good practice, I recommend you run or watch demo code directly.

### import dependency

you can import dependency or copy source file to your project directly, so far, only one file in this lib.

Add the JitPack repository to your build file
```groovy
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Add the dependency
```groovy
dependencies {
	 compile 'com.github.maoruibin:ViewController:0.9.0'
}
```
## develop component

just like this demo image, we should develop four component. 

<img src="http://7xr9gx.com1.z0.glb.clouddn.com/practice-demo.png">

 
now I want to develop comment component as a demo, i will introduce the point by code annotation.

```java
// 1、every component mast extends ViewController
public class HouseCommentViewController extends ViewController<List<String>> 

// 2、indicate layout id for this component  
@Override
protected int resLayoutId() {
    return R.layout.detail_comment_layout;
}

// init this component's view element 
@Override
protected void onCreatedView(View view) {
    mLlContainer = view.findViewById(R.id.ll_container);
    ...
}

// bind data to this view component 
@Override
protected void onBindView(List<String> comments) {
    for (String comment:comments) {
        TextView view = new TextView(getContext());
        view.setBackgroundResource(R.color.bk_item);
        view.setText(comment);
        int padding = Utils.dp2px(16);
        view.setPadding(padding,padding,padding,padding);
        mLlContainer.addView(view);
    }
}
```

Now, we have finished a simple view component, and you can watch left components implement by demo code. 

## Assemble Component in Activity

We have finished four components for house detail UI.

```java
HousePhotoViewController    //House picture component 
HouseParamViewController    //House param info component
HouseDescViewController     //House description component
HouseCommentViewController  //House comment component
```
  
the left job is assemble. the core of assemble is
  
> every view controller support a way to attach owner's view to root layout,so activity should have a root layout use to
 fill all views.

The java code is like this

```java
// 1、define ViewController instance
private ViewController<List<String>> mHousePhotoViewController;
private ViewController<HouseDetail.Param> mHouseParamViewController;
private ViewController<List<String>> mHouseCommentViewController;
private ViewController<String> mHouseDescViewController;

// 2、init instance 
mHousePhotoViewController = new HousePhotoViewController(this);
mHouseParamViewController = new HouseParamViewController(this);
mHouseDescViewController = new HouseDescViewController(this);
mHouseCommentViewController = new HouseCommentViewController(this);

// 3、attach view controller to activity root, usually the best choose for root is a vertical LinearLayout. 
mHousePhotoViewController.attachRoot(mLlContainer);
mHouseParamViewControler.attachRoot(mLlContainer);
mHouseDescViewControler.attachRoot(mLlContainer);
mHouseCommentViewControler.attachRoot(mLlContainer);

// 4 、mock get data 
getData();

// 5、fill data to UI 

fillData();

// 6、fill data to different view controller
private void fillData(HouseDetail detail) {
    mHousePhotoViewController.fillData(detail.photos);
    mHouseParamViewController.fillData(detail.param);
    mHouseDescViewController.fillData(detail.desc);
    mHouseCommentViewController.fillData(detail.comments);
}
```

and now, a complex ui had split four components, by this way, every components only deal with owner logic.

And if other activity or fragment have a same component need implement, you can reuse code directly, nice!

## TODO

- [ ] Manage lifecycle 
- [ ] Support a AndroidStudio Templete for generate ViewController frame

## Author

[http://gudong.name](http://gudong.name)

[https://github.com/maoruibin](https://github.com/maoruibin)


## License

    Copyright 2016 咕咚
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
