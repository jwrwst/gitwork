#Framework7-Plus

## Framework7-Plus 是什么
[Framework7](http://framework7.taobao.org/) 是一个开源免费的框架可以用来开发混合移动应用（原生和HTML混合）或者开发iOS7风格的WEB APP。也可以用来作为原型开发工具，可以迅速创建一个应用的原型。Framework7 的特点是对iOS提供最好的体验，像素级模仿iOS的设计，不过它并不能保证对安卓设备的兼容性。
Framework7-Plus的目标是修复F7在安卓4.0+上的兼容性问题，并且尽可能不改变现有的API，这样可以方便已经使用F7开发的项目迁移到F7-Plus。
如果你打算开发一个兼容安卓和iOS设备的Web App，或者你已经基于F7开发完成但是在安卓设备下碰到了很多问题，那么F7-Plus将是你很好的选择。关于如何将 F7项目迁移到F7-Plus，请参见[F7迁移到F7-Plus](#transfer)。如果你对兼容性问题感兴趣，可以参见[Framework7 在安卓上的主要兼容性问题](#compitable)。
**现在还处在测试阶段，欢迎试用并反馈问题。有任何问题或者建议都可以通过pull request/issue形式提交，或者直接发邮件给我 lihongxun945@gmail.com （或者 lihongxun945@163.com)**

## Framework7-Plus的改动和文档
F7-Plus影响最大的改动是用[iScroll](https://github.com/cubiq/iscroll)替换了原生的滚动条，但是除了增加了和滚动条相关的API和滚动容器的改变之外，并没有影响其他组件，包括下拉刷新和无限滚动等组件都保持和F7一样的API。滚动条相关的详细改动请参见 [iscroll滚动条](#iscroll)。
一些基于flexbox布局的组件被修改成了兼容性更好的float布局，参见 [其他组件的修改](#other-components)。

Framework7-Plus 暂时是没有官网的，所有改动的地方都在这里列出了，这里没有列出的就是没有做任何修改。所以除了这里列出的几处修改，其它的文档请依然参考官方文档。

**http://framework7.taobao.org/ 仅仅做了翻译，其中用的依然是原版的 F7 代码，如果你想用 Framework7-Plus 的代码，需要clone这个仓库。** 

<a name='iscroll'></a>
## scroller滚动条
为了解决安卓上的滚动兼容性问题，F7-Plus 增加了一个 scroller 对象，这个对象提供了统一的滚动API。它在底层会自动判断系统的版本，对高版本的IOS和安卓使用原生滚动条，否则会使用JS滚动条（参见 [iscroll](https://github.com/cubiq/iscroll))。
所以在低版本的系统中，`.page-content`内部增加了一个 `.page-content-inner` 容器，这个容器通过 `translate` 在 `.page-content` 中滚动。如果你的HTML代码中， `.page-content` 下面没有 `.page-content-inner` 容器，那么在初始化 page 的时候会自动创建一个，所以请确保你的页面相关的所有JS代码都是在 pageInit 事件回调中执行的。

### scroller 配置
初始化配置中增加了一个 `scroller` 参数，可以用来配置滚动条类型，有以下三种值可以配置：

- `auto` 根据系统版本自动选择滚动条类型。默认值。会根据系统版本自动选择原生滚动条或者JS滚动条。在 ios >=6.0 以及 android >= 4.4.0 的系统上会使用原生滚动条，否则会使用JS滚动条。
- `js` 总是使用JS滚动条
- `native` 总是使用原生滚动条。如果你的页面结构比较简单，比如是一大段文案说明,可能使用原生滚动条不会有问题，可以使用这个选项强制使用原生滚动条。

例如：
```
var myApp = new Framework7({scroller:"native"});  //总是使用原生滚动条。
```

### iscroll 新增的API
F7-Plus 在 `pageinit` 的时候会自动初始化一个滚动条，并且把它存储到对应的 `.page` DOM元素上。不过不建议直接从DOM元素上获取，而是通过下面的API来使用。
新增了这几个API：

**app.getScroller(content)**

获取滚动条示例，这个content应该是一个 `.page-content` 节点。如果你没有传入content，则自动获取当前显示的页面的滚动条。返回值是一个 scroller 实例。

**app.refreshScroller(content)**

刷新滚动条。任何导致 `.page-content-inner` 容器产生高度变化的操作之后，都需要刷新JS滚动条。不过F7的原生组件，包括下拉刷新，手风琴，标签页等已经自动做了刷新操作，不需要再次刷新。
`content` 是一个 `.page-content` 节点，如果没有传入 `content` 参数，则直接刷新当前显示的页面。
如果当前是原生滚动条，则此函数不做任何操作直接返回。
**注意，如果滚动条正在执行滚动动画，那么这时候刷新滚动条会导致页面闪一下，请避免在滚动的时候刷新滚动条。下拉刷新之后不要刷新滚动条，因为下拉刷新组件自己做了刷新操作。**

**scroller.scrollTop(top, duration)**
滚动到某一位置，top是滚动距离，duration第二个参数是时间。如果没有传入任何参数，则直接返回当前页面滚动距离顶部的位置。

**scroller.on(event, callback)**
绑定事件，和原生滚动条是一样的API。 
例如 `scroller.on("scroll", onScroll);`

**scroller.off(event, callback)**
解绑事件

**scroller.refresh()**
刷新滚动条


**scroller.scrollHeight()**
返回当前滚动内容的高度

滚动条相关改动的需要注意以下三点：
1. `.page-content` 中增加了一个 `.page-content-inner` 容器.
2. 原生的滚动事件不可用，应该用 scroller 封装的事件。
3. 任何导致 `.page-content` 高度变化的操作都要刷新滚动条。

<a name="other-components"></a>
## 其他组件的修改

因为一些兼容性的问题，部分组件的CSS做了修改。如果你是从F7迁移的项目，并且定制过下面这些组件的样式，那么需要额外注意。

**grid**

栅格从 `flexbox` 布局改成了 `float` 布局，这个改动同时会导致样式的变化。flexbox布局的间距是固定的15px，而float布局的间距是一个百分比，也就意味着不同屏幕宽度下，间距大小是不同的。

**message**

message 组件使用了 `flexbox` 布局，这里把 `.message` 改成了 float 布局，因为 `flexbox` 容器无法完美自适应屏幕宽度。

**searchbar**

搜索栏的 form是使用 `flexbox` 布局，改成了 `position: absolute` 的布局。

<a name='transfer'></a>
## F7迁移到F7-Plus

F7-Plus 尽可能保证了原来的API不变，减少迁移难度。不过迁移的时候，除了替换掉F7的库之外，还是有一些需要修改的代码和需要注意的地方。
- 首先需要修改的是所有和滚动条相关的逻辑，因为在低版本的系统上会自动启用JS滚动条，所以导致页面高度变化的操作之后需要刷新滚动条，具体参见 [scroller滚动条](#iscroll)。
- 如果你用到了 [其他组件的修改](#other-components) 中提到的组件，并且修改了他们的样式，那么你可能需要重新检查一下你的CSS，因为他们的布局已经发生了变化。

上面就是你所有需要注意的地方，很多时候，你唯一需要做的就是刷新一下滚动条而已，是不是很简单。

如果你对 Framework7 的兼容性有兴趣，可以继续阅读下一章。

<a name='compitable'></a>
## Framework7 在安卓上的主要兼容性问题
目前主要测试了如下几种设备：
1. 三星 S4 (4.4.2)
2. 三星 s3 (4.3)
3. 三星 note2 (4.3)
4. LG nexus 5 (4.4.4)
5. nexus 4(?)
6. 小米 M3 (4.4.2)
7. 小米 M2 (4.1.1)
8. 红米 Note 增强版 (4.2.2)
9. 魅族 MX2 (4.2.1)

主要兼容性问题是以下这些：

**原生滚动条滚动时的闪烁问题**

在低版本的安卓比如魅族MX2上，绝对定位 `position: absolute;` 或者使用了 `transform: translate3D;`的容器，在原生滚动条滚动的时候会有滚动延迟，视觉上就是这些容器在滚动时会闪烁。主要在swipeout,sortable list, accordion, photo browser这几个组件上有问题。
现在已经解决，解决方法是把原生的滚动条替换成了JS滚动条，JS滚动条使用的是大名鼎鼎的 [iScroll](https://github.com/cubiq/iscroll)，基本可以达到和原生滚动条相同的性能。替换完成之后，因为JS滚动条在页面高度变化的时候需要手动更新，所以会增加一个新的 `app.refreshScroller` 接口。已有的所有组件都完成了改造，所以使用他们的时候是不需要手动调用 `refreshScroller`的。

**内置的fast click库的bug**

Framework7 内置了一个fast click库，它在处理label的prevent default逻辑上有问题（对所有低于 4.4的机型都不做 prevent default处理），这样会导致部分低于4.4的机型上label无法正确触发其中的radio或者checkbox的选择，影响到 form中使用label作为容器的 radio，checkbox，switch和smart select。现在修改了 prevent default的版本判断逻辑，在上述测试机上测试没有问题，不排除还会有其他机型上会出现无法选择的问题。

**CSS calc 不支持**

部分低于4.4的机型不支持 `calc` 函数，一些使用 `calc` 计算宽度的组件会出现宽度问题，主要包括：grid, search bar, messages等组件。现在已经把他们替换成了 `float` 或者 `absolute` 布局。

**display: flex;不支持**

部分低于 4.4 的机型不支持新的 `display: flex;` ，不过并不是所有的，小米M2是4.1.1却支持。不支持 `display: flex;` 的机型会自动降级到 `display: box;`，这是旧的flexbox规范，它的宽度适应没有新规范那么灵活，所以grid组件在这些机型上在同一个 row 中放入的列超出宽度之后，无法自动换到下一行。
关于flexbox的新规范和旧规范，可以参考stackoverflow上的一个问题 [CSS3 Flexbox: display: box vs. flexbox vs. flex](http://stackoverflow.com/questions/16280040/css3-flexbox-display-box-vs-flexbox-vs-flex)
现在的做法是把基于flexbox布局的grid，改成了基于 `float` 的布局。有一点和以前不一样，以前的布局是固定的15px 间距，现在变成一个百分比。
另外一个是message组件，他用的也是 flexbox 布局，而flexbox容器的宽度无法完美自适应，已经把flex替换成了float布局。

**SVG 背景图片的bug **

在 安卓 4.2.1 以及 安卓 4.2.2 的webview容器中，SVG 背景的支持有bug，如果同时使用SVG背景图片和 `background-size` ，则 `background-size` 不生效，导致背景图片太大而显示错误。解决方法是替换成 png 格式的背景图片。目前在下列几个组件中存在问题：

- list 中的箭头，**已修复**
- sortable 中的排序icon， **已修复**


**更新到 v1.0.2。因为新版本用 `before` 和 `after` 伪元素实现了一像素的边框，和iconfont背景图标有冲突，所以恢复了svg作为背景图标。**
