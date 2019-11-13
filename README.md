# Adtalos Unity 插件

Adtalos 的 Unity 插件可以让您在 Unity 游戏或 Unity 应用中方便的集成我们的广告。

## 集成方法

1. 在 Unity 中打开你的项目
2. 在 Assets 中点右键，选 Import Package -> Custom Package
3. 选择 AdtalosAdsSDK.unitypackage
4. 导入所有选中的文件，确保没有冲突的文件
   
## 运行项目

目前我们的广告仅支持 Android 和 iOS，将编译平台选择为 Android 或 iOS，点 Build and Run 就可以了。

## 运行演示

导入到 Scenes 目录下的 AdtalosDemo.cs 是演示代码。把它拖到主摄像头上。然后运行就可以看到广告效果了。

## API 简介

### 获取广告API对象

```C#
AdtalosUnityPlugin ad = AdtalosUnityPlugin.Instance();
```

该方法是获取 AdtalosUnityPlugin 的单例对象，可以安全的多次调用，返回的都是同一个实例。

### 载入开屏广告

```C#
ad.LoadSplashAd("5C3DD65A809B08A2D6CF3DEFBC7E09C7", listener);
```

其中 `"5C3DD65A809B08A2D6CF3DEFBC7E09C7"` 是广告 Id，后面显示时仍然需要它。在实际使用时，最好定义为常量，可以更容易识别。

`listener` 是一个委托，用了表示事件处理器。下面我们来详细说明它。

### 事件处理

```C#
    AdtalosListener listener = (adUnitId, name, data) => Debug.Log("adUnitId: " + adUnitId + " event: " + name + " data: " + data);
```

上面就是 `listener` 的定义方法。其中 `adUnitId` 参数表示触发该事件的广告 Id，`name` 表示触发的事件名称，跟 SDK 标准事件对应的都是 `on` 开头的事件名，自定义事件名称推荐不要加 `on` 开头的字样，防止跟标准事件发生冲突，也便于识别。

下面是标准事件名列表：

* onRendered
* onImpressionFinished
* onImpressionReceivedError
* onLoaded
* onFailedToLoad
* onOpened
* onClicked
* onLeftApplication
* onClosed
* onVideoLoad
* onVideoStart
* onVideoPlay
* onVideoPause
* onVideoEnd
* onVideoVolumeChange
* onVideoTimeUpdate
* onVideoError
* onVideoBreak

这些事件的参数和触发条件可以参考：[SDK 事件](https://github.com/adtalos/android-xy-sdk-demo/wiki/%E4%BA%8B%E4%BB%B6)。

关于自定义事件，请参考 SDK 文档中 [JavaScript 与 Java 交互](https://github.com/adtalos/android-xy-sdk-demo/wiki/%E6%A8%A1%E6%9D%BF#javascript-%E4%B8%8E-java-%E4%BA%A4%E4%BA%92)部分的内容。

### 载入插屏广告

```C#
ad.LoadInterstitialAd("2EF810225D10260506CBB704C96C5325", true, listener);
```

第一个参数和第三个参数含义跟开屏广告相同，第二个参数表示是否才用沉浸模式打开，沉浸模式会在全屏模式的基础上隐藏屏幕上的软按键。

### 载入激励视频广告

```C#
ad.LoadRewardedVideoAd("527E187C5DEA600C35309759469ADAA8", listener);
```

参数含义与开屏广告相同。

只需要在程序开始处执行上面的代码即可，不需要每次显示前都执行，因为载入的广告会先缓存。另外，显示之后也会自动载入下个广告，因此不需要手动再次载入。

### 展示开屏、插屏、激励视频广告

```C#
ad.Show("5C3DD65A809B08A2D6CF3DEFBC7E09C7");
```

上面的参数是对应广告的广告位 Id，三种广告形式的使用方式一样。

### 判断开屏、插屏、激励视频、原生广告是否已载入

```C#
bool isloaded = ad.IsLoaded("5C3DD65A809B08A2D6CF3DEFBC7E09C7");
```

四种广告是否已经载入的判断方式是一样的，只要换成相应的广告位 Id 即可。通常不需要使用该方法，除非你需要在判断该条件的 `if` 语句的 `else` 子句中有别的其它操作。因为 `Show` 方法中也会自动做该判断，所以直接调用 `Show` 方法并不会出错。

### 在相对位置显示横幅广告

```C#
ad.ShowBannerRelative("209A03F87BA3B4EB82BEC9E5F8B41383", 320, 50, AdPosition.BOTTOM_CENTER, 0, listener);
```

其中第一个参数为广告位 Id，第二、三个参数表示广告的宽高比，第四个参数是广告位置，第五个参数是纵向位移，正数为从上往下计算的位移，负数为从下往上计算的位移。最后一个参数为事件处理器。

### 在绝对位置显示横幅广告

```C#
ad.ShowBannerAbsolute("209A03F87BA3B4EB82BEC9E5F8B41383", 320, 50, 0, 0, listener);
```

除了第四、五个参数以外，其它参数含义与上面的方法相同。这里第四、五个参数表示广告显示的绝对位置（左上角）。

### 原生广告单独载入

```C#
ad.LoadNativeAd("98738D91D3BB241458D3FAE5A5BF7D34", -1, -2, listener);
```

该方法可以用来提前载入原生广告，加载完之后，不会自动显示。

### 在相对位置显示原生广告

```C#
ad.ShowNativeRelative("98738D91D3BB241458D3FAE5A5BF7D34", -1, -2, AdPosition.MIDDLE_CENTER, 0, listener);
```

参数跟上面的参数相同，注意第二、三个参数取值为 -1, -2 时表示自适应布局。横幅广告也可以使用 -1, -2 作为宽高比的参数。

```C#
ad.ShowNativeRelative("98738D91D3BB241458D3FAE5A5BF7D34", AdPosition.MIDDLE_CENTER, 0);
```

对于已经使用 `LoadNativeAd` 提前载入的原生广告，可以使用上面的方法来显示已经加载好的广告。

### 在绝对位置显示原生广告

```C#
ad.ShowNativeAbsolute("98738D91D3BB241458D3FAE5A5BF7D34", -1, -2, 0, 0, listener);
```

解释同上。

```C#
ad.ShowBannerAbsolute("98738D91D3BB241458D3FAE5A5BF7D34", 0, 0);
```

对于已经使用 `LoadNativeAd` 提前载入的原生广告，可以使用上面的方法来显示已经加载好的广告。

### 原生视频播放控制

```C#
bool hasVideo = ad.HasVideo(adUnitId);  // 判断是否存在视频

ad.PlayVideo(adUnitId);                 // 播放视频
ad.PauseVideo(adUnitId);                // 暂停播放
ad.MuteVideo(adUnitId, mute);           // 静音视频

AdtalosVideoMetadata metadata = ad.GetVideoMetaData(adUnitId); // 获取视频元数据
```

参数 `adUnitId` 只能是原生广告Id，且只有 `HasVideo` 返回结果为 `true` 时，其它操作执行才会有效。

### 资源回收

```C#
ad.Destroy(adUnitId);    // 回收资源
ad.Pause(adUnitId);      // 暂停广告
ad.Resume(adUnitId);     // 恢复广告
```

参数 `adUnitId` 是横幅广告或原生广告位的 Id。
