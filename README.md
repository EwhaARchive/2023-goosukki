# 2023-goosukki

## AR(Augmented Reality) 파트

### Unity 상에서의 HTTP 통신 & API 사용 방법

#### 설명
API를 활용해서 해당 서버에 정보를 요청하고 응답 받는 것을 HTTP 통신으로 구현해보았다.

#### 목적
여러 명이 실시간 소통하는 서버가 아니라 각각의 기기에서 필요한 정보만 웹서버에 요청하면, 웹서버에서 응답을 보내주는 비동기적인 서버 통신
- API : 정보를 요청하면 결과를 보내줌
- HTTP 통신 : 비동기적인 통신

#### 과정

① 서버 url / API
- request를 보내고 get할 서버 url을 백엔드 담당자로부터 받음
- 당장 서버 url이 없지만 통신 확인을 해보고 싶다면 Open API를 사용할 것.
	- 우리는 단순 통신 확인 테스트를 위해 사이퍼즈 Open API 목록 중 '11. 캐릭터 정보'의 API Key를 발급 받음
	- 참고 : https://developers.neople.co.kr/contents/apiDocs/cyphers

② HTTP 통신
- 유니티에서 HTTP 통신 하는 방법 (2가지) : 1) WWW 방식, 2) UnityWebRequest 방식
	- 우리는 '2) UnityWebRequest 방식'을 채택함
	- 이유 :  UnityWebRequest 방식은 WWW 방식에서 할 수 있는 모든 것을 할 수 있고, 최신의 방법이며, Unity에서도 WWW 방식보다는 UnityWebRequest 방식을 더 권장함
- HTTP 통신 시 주의사항 : (1) 코루틴을 사용해야 함, (2) using문을 사용하는 경우가 있음
- 'myNetworkTest'라는 이름의 Script를 작성
	- UnityWebRequest 방식을 사용하기 위해, Script 상단에 `using UnityEngine.Networking;` 작성
	- 코루틴을 사용하기 위해, Start() 함수 내부에 `StartCoroutine(UnityWebRequest());` 작성
	- IEnumerator UnityWebRequestGet() 함수 작성 (구체적인 코드 내용은 myNetworkTest.cs 파일을 참고)
	- 이후 Unity를 Run 시키면, coroutine이 돌면서 서버에 요청을 보내고 응답을 받음
- 만약 '요청변수'가 2개 이상이라면, UnityWwebRequestGet() 함수 내부에 해당 요청변수와 똑같이 꼭 맞춰줘야하고, url도 String이 아닌 '템플릿 스트링($)'으로 바꿔줘야 함

## Android 파트
