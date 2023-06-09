using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

public class myNetworkTest : MonoBehaviour
{

	// Start is called before the first frame update
	void Start()
	{
		StartCoroutine(UnityWebRequestGet());
	}

	IEnumerator UnityWebRequestGet()
	{
		//string apikey = "tLd6shy4OZXJv2mfiJLTqSiiAOmN25uY";
		//string url = "https://api.neople.co.kr/cy/characters?apikey=" + apikey;
		string url = "http://43.200.243.113:8081/api/posts";

		UnityWebRequest www = UnityWebRequest.Get(url);

		yield return www.SendWebRequest();

		if (www.error == null)
		{
			Debug.Log(www.downloadHandler.text);
		}
		else
		{
			Debug.Log("ERROR : " + www.error);
		}
	}
}
