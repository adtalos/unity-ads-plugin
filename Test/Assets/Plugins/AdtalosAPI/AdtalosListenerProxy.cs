using System.Runtime.InteropServices;
using UnityEngine;
namespace Adtalos {
    public delegate void AdtalosListener(string adUnitId, string name, string data);
#if UNITY_IOS
    [UnmanagedFunctionPointer(CallingConvention.Cdecl)]
    public delegate void AdtalosListenerProxy(string adUnitId, string name, string data);
#endif
#if UNITY_ANDROID
    public class AdtalosListenerProxy : AndroidJavaProxy {
        private AdtalosListener listener;
        internal AdtalosListenerProxy(AdtalosListener listener)
            : base("com.adtalos.ads.plugin.IAdtalosListener") {
            this.listener = listener;
        }
        void onAdtalosEvent(string adUnitId, string name, string data) {
            Debug.Log("c# adtalos listener proxy " + adUnitId + " " + name + " " + data);
            listener?.Invoke(adUnitId, name, data);
        }
        override public string toString() {
            return "AdtalosListenerProxy";
        }
    }
#endif
}
