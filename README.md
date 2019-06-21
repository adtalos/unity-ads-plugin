# Adtalos Unity 插件

Adtalos 的 Unity 插件可以让您在 Unity 游戏或 Unity 应用中方便的集成我们的广告。

## 集成方法

1. 在 Unity 中打开你的项目
2. 在 Assets 中点右键，选 Import Package -> Custom Package
3. 选择 AdtalosAdsSDK.unitypackage
4. 导入所有选中的文件，确保没有冲突的文件
   
## 运行项目

目前我们的广告仅支持 Android，将编译平台选择为 Android，点 Build and Run 就可以了。

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

上面就是 `listener` 的定义方法。其中 `adUnitId` 参数表示触发该事件的广告 Id，`name` 表示触发的事件名称，跟 SDK 标准事件对应的都是 `on` 开头的事件名，自定义事件名称推荐不要加 `on` 开头的字样，防止跟标准事件发生冲突，也便于识别。关于自定义事件，请参考 [SDK 文档](https://github.com/adtalos/android-ads-sdk-demo)中 [JavaScript 与 Java 交互](https://github.com/adtalos/android-ads-sdk-demo/wiki/%E6%A8%A1%E6%9D%BF#javascript-%E4%B8%8E-java-%E4%BA%A4%E4%BA%92)部分的内容。

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

### 判断开屏、插屏、激励视频广告是否已载入

```C#
bool isloaded = ad.IsLoaded("5C3DD65A809B08A2D6CF3DEFBC7E09C7");
```

三种广告是否已经载入的判断方式是一样的，只要换成相应的广告位 Id 即可。通常不需要使用该方法，除非你需要在判断该条件的 `if` 语句的 `else` 子句中有别的其它操作。因为 `Show` 方法中也会自动做该判断，所以直接调用 `Show` 方法并不会出错。

### 在相对位置显示横幅广告

```C#
ad.ShowBannerRelative("209A03F87BA3B4EB82BEC9E5F8B41383", 320, 50, AdPosition.BOTTOM_CENTER, 0, listener);
```

其中第一个参数为广告位 Id，第二、三个参数表示广告的宽高比，第四个参数是广告位置，第五个参数是纵向位移，正数为从上往下计算的位移，负数为从下往上计算的位移。最后一个参数为事件处理器。

### 在相对位置显示横幅广告

```C#
ad.ShowBannerRelative("209A03F87BA3B4EB82BEC9E5F8B41383", 320, 50, AdPosition.BOTTOM_CENTER, 0, listener);
```

其中第一个参数为广告位 Id，第二、三个参数表示广告的宽高比，第四个参数是广告位置，第五个参数是纵向位移，正数为从上往下计算的位移，负数为从下往上计算的位移。最后一个参数为事件处理器。

### 在相对位置显示原生广告

```C#
ad.ShowNativeRelative("98738D91D3BB241458D3FAE5A5BF7D34", -1, -2, AdPosition.MIDDLE_CENTER, 0, listener);
```

参数跟上面的参数相同，注意第二、三个参数取值为 -1, -2 时表示自适应布局。横幅广告也可以使用 -1, -2 作为宽高比的参数。

### 在绝对位置显示横幅广告

```C#
ad.ShowBannerAbsolute("209A03F87BA3B4EB82BEC9E5F8B41383", 320, 50, 0, 0, listener);
```

除了第四、五个参数以外，其它参数含义与上面的方法相同。这里第四、五个参数表示广告显示的绝对位置（左上角）。

### 在绝对位置显示原生广告

```C#
ad.ShowNativeAbsolute("98738D91D3BB241458D3FAE5A5BF7D34", -1, -2, 0, 0, listener);
```

解释同上。

