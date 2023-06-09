using UnityEngine;
using UnityEngine.UI;

public class myButtonsBehaviour: MonoBehaviour
{

    public Button backBtn, addBtn, refreshBtn, pigeonBtn;
    //public GameObject pigeon;

    // Start is called before the first frame update
    void Start()
    {
        backBtn.onClick.AddListener(backBtnprint);
        addBtn.onClick.AddListener(delegate { addBtnprint("add button is clicked"); });
        refreshBtn.onClick.AddListener(() => refreshBtnprint("refresh button is clicked"));
        pigeonBtn.onClick.AddListener(pigeonPrint);
    }

    // Update is called once per frame
    void Update()
    {

    }

    void backBtnprint()
    {
        Debug.Log("back button is clicked");
    }

    void addBtnprint(string message)
    {
        Debug.Log(message);
    }

    void refreshBtnprint(string message)
    {
        Debug.Log(message);
    }

    
    void pigeonPrint()
    {
        Debug.Log("hi I'm your pigeon");
    }
    
}
