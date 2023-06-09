using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.XR.ARFoundation;

public class myPigeonBehaviour : MonoBehaviour
{
    public GameObject target;
    
    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        //Check for mouse click 
        if (Input.GetMouseButtonDown(0))
        {
            target = GetClickedObject();

            if (target.Equals(gameObject))
            {
                Debug.Log("Hi, it's me, Gooki!");
            }
            else
            {
                Debug.Log("Error - target : " + target + ", gameObject : " + gameObject);
            }
        }
    }

    private GameObject GetClickedObject()
    {
        RaycastHit hit;

        //GameObject target = null;

        Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

        if (true == (Physics.Raycast(ray.origin, ray.direction * 1000, out hit)))
        {
            target = hit.collider.gameObject;
        } 
        else
        {
            Debug.Log("Error - Pigeon" + target);
        }

        return target;
    }
}