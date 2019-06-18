using UnityEngine;
using System;
namespace Adtalos {
    [Serializable]
    public class AdtalosVideoMetadata {
        public double currentTime;
        public double duration;
        public double videoWidth;
        public double videoHeight;
        public bool autoplay;
        public bool muted;
        public double volume;
        public AdtalosVideoMetadata(string json) {
            JsonUtility.FromJsonOverwrite(json, this);
        }
        public override string ToString() {
            return JsonUtility.ToJson(this);
        }
    }
}