# ViewController
ViewController 是一种界面开发组件化实现方式,利用它可以将一些复杂的 UI 界面开发组件化,
从而更好的组织代码结构,从而提高开发效率,降低维护成本。


关于 ViewController 这种思想的介绍,也可以查看之前的一篇博客,[Android 复杂界面开发实践之 ViewController: 前言](http://gudong.name/2016/10/13/viewcontroller-foreword.html)

如果你还没有阅读上篇文章，建议先阅读，这样你会更加清楚 ViewController 是一种什么样的思路。

这里先放一张 ViewController 示意图，如下所示。

<img src="http://7xr9gx.com1.z0.glb.clouddn.com/practice-demo.png">

## 优点

* 界面开发组件化，解决 Activity/Fragment 中 UI 代码臃肿问题。
* 灵活的 UI 开发，同一组件可用于多处，代码重用。
* 易维护，开发简单。


## 使用方法 

目前已经使用 ViewController 完成了一个房屋详情页开发 [demo](https://github.com/maoruibin/ViewController) ,你可以直接运行代码,查看 demo 如何运行,下面是一般的使用流程。

### 1、根据业务需求,划分 UI 组件

如下所示是一个示例的 UI 开发场景,这是一个房屋详情页面,

<img src="http://7xr9gx.com1.z0.glb.clouddn.com/practice-demo-product.png">

按照一般的开发方式,我们需要在 layout 中先写好所有的布局,然后在对应的 Activity 中
实例化所有的 View 示例,然后请求数据后,把数据分别填充在不同的 View 上,最终完成这个界面的开发。

但是现在,我们利用 ViewController 后,可以把这儿界面组件化,显然我们可以把它分为四个 UI 组件。

* 房屋图片组件
* 房屋信息组件
* 房屋描述组件
* 房屋评论组件

至于怎么划分组件,完全取决于具体的 UI 情景,这里只是以一个房屋详情页进行举例。

### 2、实现组件

只要划分完组件后,接下来就是分别实现组件的过程,这里以评论组件位例进行介绍,这里命名为 HouseCommentViewController。

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
//继承 ViewController
public class HouseCommentViewController extends ViewController<List<String>> 

//指定 layout id 
@Override
protected int resLayoutId() {
    return R.layout.detail_comment_layout;
}

// View 初始化
@Override
protected void onCreatedView(View view) {
    ButterKnife.bind(this,view);
}

// 绑定数据到 view
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
//定义组件实例
private ViewController<List<String>> mHousePhotoViewController;
private ViewController<HouseDetail.Param> mHouseParamViewController;
private ViewController<List<String>> mHouseCommentViewController;
private ViewController<String> mHouseDescViewController;

//实例化组件
mHousePhotoViewController = new HousePhotoViewController(this);
mHouseParamViewController = new HouseParamViewController(this);
mHouseDescViewController = new HouseDescViewController(this);
mHouseCommentViewController = new HouseCommentViewController(this);

//模拟数据获取操作
getData();

//数据获取成功后向组件填充数据
private void fillData(HouseDetail detail) {
    mHousePhotoViewController.fillData(detail.photos);
    mHouseParamViewController.fillData(detail.param);
    mHouseDescViewController.fillData(detail.desc);
    mHouseCommentViewController.fillData(detail.comments);
}
```

最终的实现结果如下

<img src="http://7xr9gx.com1.z0.glb.clouddn.com/practice-demo.png">

### 总结 

至此, 界面开发完成。

你会发现主界面中看不到任何具体 View 操作相关的代码,
因为这些代码都已经被分发到不同的 ViewController 中去了。如上图所示，每一部分的开发逻辑都被分发到了不同的 ViewController 中，这样在后期在开发维护过程中，如果某一部分要修改一些逻辑或者 UI 结构，你只需要到对应的 ViewController 中去进行修改即可，如果这个界面需要新增一个模块，只需要新建一个 ViewController 就可以灵活的添加新模块。

你会发现，Activity\Fragment 很少做改动。一般的操作直接在对应的 ViewController 中完成即可。这种方案极大的解决了 Activity 中 UI 逻辑代码复杂的问题。总之，在特定的界面开发中使用这种组件化的开发模式后,后续的维护开发就会变得特别有条理，非常高效。

另外，由于示例的原因，这里的 View 结构看山去比较简单，但是在实际开发中，你遇到的情况，View 会比这复杂很多，在越复杂的场景中，ViewController 的威力也就越大。

如果有任何问题,欢迎 [issue](https://github.com/maoruibin/ViewController/issues).


## Author

[http://gudong.name](http://gudong.name)

[https://github.com/maoruibin](https://github.com/maoruibin)


## License

    Copyright 2016 GuDong
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.