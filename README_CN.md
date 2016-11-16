# ViewController
ViewController 是一种界面开发组件化实现方式,利用它可以将一些复杂的 UI 界面开发组件化,
从而更好的组织代码结构,从而提高开发效率,降低维护成本。

<img src="http://7xr9gx.com1.z0.glb.clouddn.com/practice-demo.png">

## 使用方法 

你可以通过下面的依赖导入自己的项目，也可以直接拷贝 ViewController 类到自己项目，目前只包含一个类。

Add the JitPack repository to your build file

```java
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
Add the dependency

dependencies {
     compile 'com.github.maoruibin:ViewController:0.9.0'
}

```java

所有的组件都可以通过继承 ViewController 实现, ViewController 是一个抽象类,你只需要实现下面三个抽象方法即可。

* resLayoutId()
* onCreatedView(View view)
* onBindView(T data)

#### resLayoutId
该方法指定该 ViewController 对应的 layout ID。所以这里需要去根据具体的 UI 样式去创建一个评论组件的 layout,然后在这里返回对应的 layout id。 

#### onCreatedView(View view)
该方法是用于初始化 View 的一个方法,你可以在这里实例化对应组件内的所有 View,也可以在
这里对 View 做一些事件监听等等。

#### onBindView(T data)
这个方法是最终进行数据绑定的地方。

ViewController 是一个泛型类,这里的 T 是这个 ViewController 对应的数据类型。对于评论组件,只是展示一列不同用户的评价信息,
使用简单的字符串集合即可,所以这里的 T 应该是 List<String>.

#### 示例

下面是 HouseCommentViewController 的实现方式

```java
//1、继承 ViewController
public class HouseCommentViewController extends ViewController<List<String>> 

//2、指定组件对应的 layoutid 
@Override
protected int resLayoutId() {
    return R.layout.detail_comment_layout;
}

//3、初始化该组件对应的 View 元素
@Override
protected void onCreatedView(View view) {
    mLlContainer = view.findViewById(R.id.ll_container);
    ...
}

//4、绑定具体的数据到 view
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

具体实现可查看对应 demo 中 HouseCommentViewController 的[实现源码](https://github.com/maoruibin/ViewController/blob/master/demo/src/main/java/name/gudong/demo/view/HouseCommentViewController.java)

至此,评论组件的实现类 HouseCommentViewController 已经开发完毕,剩下的其他组件开发方式都一样。

下面是不同组件对应的实现类。
 
 * 房屋图片组件 -> HousePhotoViewController
 * 房屋信息组件 -> HouseParamViewController
 * 房屋描述组件 -> HouseDescViewController
 * 房屋评论组件 -> HouseCommentViewController
 

### 3、在 Activity 中配置组件

开发完成组件后,剩下的事就是在 Activity 中去组合这几个组件了,
这里通过 Activity 中的一个容器 View 与组件关联,所以这里需要在 Activity 中定义
一个容器 View,一般可以选择一个垂直的 LinearLayout。

接下来首先需要实例化组件,接着将组件与 Activity 关联,然后在合适的时机向组件填充数据,如下所示。
 
```java
//1、定义组件实例
private ViewController<List<String>> mHousePhotoViewController;
private ViewController<HouseDetail.Param> mHouseParamViewController;
private ViewController<List<String>> mHouseCommentViewController;
private ViewController<String> mHouseDescViewController;

//2、实例化每个组件对象
mHousePhotoViewController = new HousePhotoViewController(this);
mHouseParamViewController = new HouseParamViewController(this);
mHouseDescViewController = new HouseDescViewController(this);
mHouseCommentViewController = new HouseCommentViewController(this);

//3、模拟数据获取操作
getData();

//4、合适的地方调用 fillData
fillData();

//5、向组件填充数据
private void fillData(HouseDetail detail) {
    mHousePhotoViewController.fillData(detail.photos);
    mHouseParamViewController.fillData(detail.param);
    mHouseDescViewController.fillData(detail.desc);
    mHouseCommentViewController.fillData(detail.comments);
}
```

到此使用 ViewController 开发的界面就已经完成，如果有任何问题，欢迎 issue.

## 待做

[ ] 管理 ViewController 的生命周期
[ ] 开发一个 AndroidStudio 模板用于简化 ViewController 的实现

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
