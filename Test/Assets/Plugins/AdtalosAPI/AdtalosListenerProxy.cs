using System.Runtime.InteropServices;
using UnityEngine;
namespace Adtalos {
    public delegate void AdtalosListener(string adUnitId, string name, string data);
#if UNITY_IOS
    [UnmanagedFunctionPointer(CallingConvention.Cdecl)]
    public delegate void AdtalosListenerProxy(string adUnitId, string name, string data);
#endif
#if UNITY_ANDROID
    public class ListenerProxy : AndroidJavaProxy {
        private AdtalosListener listener;
        internal ListenerProxy(AdtalosListener listener)
            : base("com.unity.xy.plugin.bridge.IListener") {
            this.listener = listener;
        }
        void on(string adUnitId, string name, string data) {
            Debug.Log("c# listener proxy " + adUnitId + " " + name + " " + data);
            listener?.Invoke(adUnitId, name, data);
        }
        override public string toString() {
            return "ListenerProxy";
        }
    }
#endif
}
