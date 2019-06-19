using UnityEngine;
using Adtalos;

public class AdtalosDemo : MonoBehaviour
{
    AdtalosUnityPlugin ad;
    AdtalosListener listener = (adUnitId, name, data) => Debug.Log("adUnitId: " + adUnitId + " event: " + name + " data: " + data);
    // Start is called before the first frame update
    void Start()
    {
        ad = AdtalosUnityPlugin.Instance();
        ad.LoadSplashAd("5C3DD65A809B08A2D6CF3DEFBC7E09C7", listener);
        ad.LoadInterstitialAd("2EF810225D10260506CBB704C96C5325", true, listener);
        ad.LoadRewardedVideoAd("527E187C5DEA600C35309759469ADAA8", listener);
    }

    // Update is called once per frame
    void Update()
    {

    }

    private void OnGUI()
    {
        if (GUI.Button(new Rect(100, 100, 320, 100), "show banner ad"))
        {
            ad.ShowBannerRelative("209A03F87BA3B4EB82BEC9E5F8B41383", 320, 50, AdPosition.BOTTOM_CENTER, 0, listener);
        }
        if (GUI.Button(new Rect(500, 100, 320, 100), "show native ad"))
        {
            ad.ShowNativeRelative("98738D91D3BB241458D3FAE5A5BF7D34", -1, -2, AdPosition.MIDDLE_CENTER, 0, listener);
        }
        if (GUI.Button(new Rect(100, 300, 320, 100), "show interstitial ad"))
        {
            ad.Show("2EF810225D10260506CBB704C96C5325");
        }
        if (GUI.Button(new Rect(500, 300, 320, 100), "show rewarded video ad"))
        {
            ad.Show("527E187C5DEA600C35309759469ADAA8");
        }
    }

    void OnDestroy() {
        ad.Destroy("209A03F87BA3B4EB82BEC9E5F8B41383");
        ad.Destroy("98738D91D3BB241458D3FAE5A5BF7D34");
    }

}
