using System;
using System.Collections;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using UnityEngine.UI;
using UnityEngine;

#if UNITY_IOS || UNITY_TVOS
public class NativeAPI {
    [DllImport("__Internal")]
    public static extern void showHostMainWindow(string lastStringColor);
}
#endif

public class myGUIButton : MonoBehaviour
{

    public Text text;
    void appendToText(string line) { text.text += line + "\n"; }

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if (Application.platform == RuntimePlatform.Android)
            if (Input.GetKeyDown(KeyCode.Escape)) Application.Quit();
    }

    string lastStringColor = "";
    void ChangeColor(string newColor)
    {
        appendToText("Chancing Color to " + newColor);

        lastStringColor = newColor;
    }

    void showHostMainWindow()
    {
#if UNITY_ANDROID
        try
        {
            AndroidJavaClass jc = new AndroidJavaClass("com.company.product.OverrideUnityActivity");
            AndroidJavaObject overrideActivity = jc.GetStatic<AndroidJavaObject>("instance");
            overrideActivity.Call("showMainActivity", lastStringColor);
        } catch(Exception e)
        {
            appendToText("Exception during showHostMainWindow");
            appendToText(e.Message);
        }
#elif UNITY_IOS || UNITY_TVOS
        NativeAPI.showHostMainWindow(lastStringColor);
#endif
    }

    void OnGUI()
    {
        GUIStyle style = new GUIStyle("button");
        style.fontSize = 30;
        if (GUI.Button(new Rect(10, 10, 200, 100), "Red", style)) ChangeColor("red");
        if (GUI.Button(new Rect(10, 110, 200, 100), "Blue", style)) ChangeColor("blue");
        if (GUI.Button(new Rect(10, 300, 400, 100), "Show Main With Color", style)) showHostMainWindow();

        if (GUI.Button(new Rect(10, 400, 400, 100), "Unload", style)) Application.Unload();
        if (GUI.Button(new Rect(440, 400, 400, 100), "Quit", style)) Application.Quit();
    }
}

