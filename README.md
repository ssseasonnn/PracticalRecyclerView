# PracticalRecyclerView

标签（空格分隔）： Android RecyclerView

---

## 对RecyclerView的一个封装,添加一些实用的功能

### 效果图与示例APK

![single item](https://github.com/ssseasonnn/PracticalRecyclerView/blob/master/gif/GIF.gif?raw=true)

![multi item](https://github.com/ssseasonnn/PracticalRecyclerView/blob/master/gif/multiitem.gif?raw=true)

![grid](https://github.com/ssseasonnn/PracticalRecyclerView/blob/master/gif/grid.gif?raw=true)

![stagger](https://github.com/ssseasonnn/PracticalRecyclerView/blob/master/gif/stagger.gif?raw=true)

[APK下载地址](https://raw.githubusercontent.com/ssseasonnn/PracticalRecyclerView/master/app/demo.apk)

![扫描二维码下载](http://tool.oschina.net/action/qrcode/generate?data=https%3A%2F%2Fraw.githubusercontent.com%2Fssseasonnn%2FPracticalRecyclerView%2Fmaster%2Fapp%2Fdemo.apk&output=image%2Fgif&error=L&type=0&margin=0&size=4&1476000940942)

### 主要功能:

- 下拉刷新,使用默认的下拉刷新控件
- 页面加载数据过程中显示LoadingView
- 页面加载数据出错时显示ErrorView
- 页面数据大小为0时显示EmptyView
- 自动加载,当页面到底部或者当前页面显示不全时,自动加载其余数据,并显示LoadMoreView
- 加载更多时出错自动停止加载更多,并显示LoadMoreFailedView
- 没有更多数据加载时,显示NoMoreView
- 支持Header View和Footer View
- 除下拉刷新以外,其余View均可自定义
- 支持多种Item类型
- 支持GridLayout和瀑布流



### 2016-10-11 更新:

- 新增动态显示或关闭NoMoreView,LoadMoreView,LoadMoreFailedView功能
- 新增打开或关闭自动加载功能
- 新增手动触发加载demo,效果图:


![manual](https://github.com/ssseasonnn/PracticalRecyclerView/blob/master/gif/manual.gif?raw=true)


### 2016-10-17 更新:

- 增加获取RecyclerView接口

```java
    public RecyclerView get() {
            return mRecyclerView;
    }
```
- 增加拖拽功能与demo,效果图:



![拖拽](https://github.com/ssseasonnn/PracticalRecyclerView/blob/master/gif/drag.gif?raw=true)




### 2016-10-18 更新:

- 向下支持到API 11(android 3.0)
- AbstractAdapter增加更多操作数据接口, 如:

```java
    public void clearData() {}

    public void clearHeader() {}

    public void clearFooter() {}

    public void insert(){}

    public void insertBack(){}

    //等等

```
- 增加ExpandItemList demo, 效果图:


![ExpandItem](https://github.com/ssseasonnn/PracticalRecyclerView/blob/master/gif/expand.gif?raw=true)


### 2016-11-7 更新:

- 新增添加单个数据接口 void add(T item);



### 使用方式:

1.添加Gradle依赖

[![Download](https://api.bintray.com/packages/ssseasonnn/android/PracticalRecyclerView/images/download.svg)](https://bintray.com/ssseasonnn/android/PracticalRecyclerView/_latestVersion)

```groovy
	dependencies{
   		 compile 'zlc.season:practicalrecyclerview:1.1.1'
	}
```
2.在布局文件中添加PracticalRecyclerView


```xml

     <zlc.season.practicalrecyclerview.PracticalRecyclerView
        android:id="@+id/recycler"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:loading_layout="@layout/default_loading_layout" //可以自定义,也可以不设置使用默认的布局
        app:empty_layout="@layout/default_empty_layout"     //以下属性同样不设置即为使用默认的布局
        app:error_layout="@layout/default_error_layout"
        app:load_more_layout="@layout/default_load_more_layout"
        app:no_more_layout="@layout/default_no_more_layout"
        app:load_more_failed_layout="@layout/default_load_more_failed_layout"/>
```

3.添加代码

首先定义一个POJO类, 并实现ItemType接口:

```java
   class NormalBean implements ItemType {
       String mImg;
       String mTitle;
       String mContent;

       NormalBean(String img, String title, String content) {
           mImg = img;
           mContent = content;
           mTitle = title;
       }

       @Override
       public int itemType() {
           return 0;
       }
   }
```

   > 这里返回的item type 表示是item的类型, 如果列表只有一种类型的item, 那么返回0就可以了, 如果有多种item, 则对应类型的item返回对应类型的item type. 这里简单的返回0 .

   接着定义ViewHolder, 继承自AbstractViewHolder<T>, 并提供泛型参数:

```java
   class NormalViewHolder extends AbstractViewHolder<NormalBean> {
       @BindView(R.id.head)
       ImageView mHead;
       @BindView(R.id.title)
       TextView mTitle;
       @BindView(R.id.content)
       TextView mContent;

       private Context mContext;

       NormalViewHolder(ViewGroup parent) {
           super(parent, R.layout.normal_item); //与对应的item layout进行绑定.
           mContext = parent.getContext();      //如果viewholder需要context,在这里获取.
           ButterKnife.bind(this, itemView);    //这里使用了butterknife进行控件绑定,也可以手写											 // itemView.findViewById()来获取对应的控件.
       }

       @Override
       public void setData(NormalBean data) {
           Picasso.with(mContext).load(Uri.parse(data.mImg)).into(mHead);
           mTitle.setText(data.mTitle);
           mContent.setText(data.mContent);
       }
   }
```

   > 在Viewholder中进行View的创建和绑定, 如果需要绑定按钮的单击事件或者其他的一些事件, 在此处进行再好不过了.

   接下来创建Adatper, 继承自AbstractAdapter<T , VH>, 并提供泛型参数:

```java
   class SingleItemAdapter extends AbstractAdapter<NormalBean, NormalViewHolder> {

       @Override
       protected NormalViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
           return new NormalViewHolder(parent);
       }

       @Override
       protected void onNewBindViewHolder(NormalViewHolder holder, int position) {
           holder.setData(get(position));
       }
   }
```

   > adapter 类中非常简洁, 只需要在onNewCreateViewHolder()中创建ViewHolder, 在onNewBindViewHolder()中调用viewholder的setData()即可, 这样adapter和viewholder的逻辑就分离开来,互不干扰.

   最后,在Activity或者Fragment中进行最后的配置:

```java
   PracticalRecyclerView mRecycler;
   private SingleItemAdapter mAdapter = new SingleItemAdapter();
   ...
   mRecycler.setLayoutManager(new LinearLayoutManager(this));
   mRecycler.setAdapterWithLoading(mAdapter);
   mRecycler.setRefreshListener(new PracticalRecyclerView.OnRefreshListener() {
               @Override
               public void onRefresh() {
                   mPresenter.loadData(true);
               }
           });
   mRecycler.setLoadMoreListener(new PracticalRecyclerView.OnLoadMoreListener() {
               @Override
               public void onLoadMore() {
                   mPresenter.loadData(false);
               }
           });
   ...
   //更多详细代码请下载demo查看
```

4.添加Header 和 Footer

要添加Header和Footer, 可以选择实现SectionItem接口, 或者继承SectionItemImpl, 如下所示:

```java
   class Header implements SectionItem {
           @BindView(R.id.banner_guide_content)
           BGABanner mBanner;

           @Override
           public View createView(ViewGroup parent) {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
               ButterKnife.bind(this, view);
               return view;
           }

           @Override
           public void onBind() {
               mBanner.setAdapter(new BGABanner.Adapter() {
                   @Override
                   public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                       ((ImageView) view).setImageResource((int) model);
                   }
               });
               mBanner.setData(Arrays.asList(R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e, R.mipmap.f,
                       R.mipmap.g, R.mipmap.h), null);
           }
       }
```

   > 这里使用了一个开源轮播库BGABanner当作Header, 该库的链接在此: [BGAbanner](https://github.com/bingoogolapple/BGABanner-Android)

   接着调用Adapter的addHeader() 或者addFooter()方法将该Header或Footer添加到adapter中:

```java
    mAdapter.addHeader(new Header());
```

5.Configure 接口和ConfigureAdapter类

当默认的属性不能满足需求时, 可以自定义layout 并设置为PracticalRecyclerView的属性:

```xml
   ...
   app:load_more_failed_layout="@layout/custom_load_more_failed_layout"  //当加载更多失败时显示的View
   ...
```

   若需要对该View进行设置, 就需要用到Configure接口, Configure接口定义了以下方法,分别对属性中设置的View进行设置:

```java
   public interface Configure {
       void configureEmptyView(View emptyView); //对Empty View 进行设置

       void configureErrorView(View errorView); //对Error View 进行设置

       void configureLoadingView(View loadingView); // 对LoadingView 进行设置

       void configureLoadMoreView(View loadMoreView); // 对LoadMore View进行设置

       void configureNoMoreView(View noMoreView); //对NoMore View进行设置

       void configureLoadMoreErrorView(View loadMoreErrorView); // 对LoadMoreError View进行设置
   }
```

   ConfigureAdapter是对Configure接口的一个包装类,  可以选择实现其中的某一些方法,从而设置对应的View:

```java
    mRecycler.configureView(new ConfigureAdapter() {
               @Override
               public void configureLoadMoreFailedView(View loadMoreFailedView) {
                   super.configureLoadMoreFailedView(loadMoreFailedView);
                   loadMoreFailedView.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Toast.makeText(SingleItemActivity.this, "点击LoadMoreFailedView时应该进行的操作", Toast.LENGTH_SHORT).show();
                       }
                   });
               }

               @Override
               public void configureErrorView(View errorView) {
                   super.configureErrorView(errorView);
                   errorView.dosomething();
               }
           });
```

6.更多功能将会继续开发和完善

若您对此项目有一些自己的想法 , 欢迎来提Pull Request.

### 关于我

若您对该项目有疑问,请联系我:

QQ:270362455

Gmail: ssseasonnn@gmail.com



### License

> ```
> Copyright 2015 Season.Zlc
>
> Licensed under the Apache License, Version 2.0 (the "License");
> you may not use this file except in compliance with the License.
> You may obtain a copy of the License at
>
>    http://www.apache.org/licenses/LICENSE-2.0
>
> Unless required by applicable law or agreed to in writing, software
> distributed under the License is distributed on an "AS IS" BASIS,
> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
> See the License for the specific language governing permissions and
> limitations under the License.
> ```