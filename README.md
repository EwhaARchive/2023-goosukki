# 2023-goosukki

## AR(Augmented Reality) 파트

### 1. Unity 상에서의 HTTP 통신 & API 사용 방법

#### ▶ 설명
API를 활용해서 해당 서버에 정보를 요청하고 응답 받는 것을 HTTP 통신으로 구현해보았다.

#### ▶ 목적
여러 명이 실시간 소통하는 서버가 아니라 각각의 기기에서 필요한 정보만 웹서버에 요청하면, 웹서버에서 응답을 보내주는 비동기적인 서버 통신
- API : 정보를 요청하면 결과를 보내줌
- HTTP 통신 : 비동기적인 통신

#### ▶ 과정

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

#### ▶ 결과

i) 사이퍼즈 Open API의 '캐릭터 정보'

![서버 통신_사이퍼즈 API](https://github.com/EwhaARchive/2023-goosukki/assets/87654809/c91bcc46-3f29-440e-8ed7-8ed75d92f0aa)

ii) 우리팀의 AWS EC2 서버 데이터

![서버 통신_우리 서버 url](https://github.com/EwhaARchive/2023-goosukki/assets/87654809/0d6735ce-448a-4dd5-941f-137c318bd1f6)

### 2. Unity as a Library (UaaL)

아래 블로그에 자세히 정리해두었습니다. 참고 부탁드립니다. </br>
https://velog.io/@neoseurae12/UaaL-Unity%EB%A5%BC-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC%EB%A1%9C-%ED%91%9C%EC%A4%80-Android-%EC%95%B1%EC%97%90-%ED%86%B5%ED%95%A9%ED%95%98%EA%B8%B0


## Android 파트

### 1. 사용 언어
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
(게시물 등록 screen의 경우 jetpack compose 사용)

### 2. 사용 기술
google map API, retrofit 라이브러리

### 3. 화면 구성
#### 회원가입/ 로그인 화면
<img src="https://github.com/EwhaARchive/2023-goosukki/assets/81233784/f179fb35-f54e-458a-802c-29ae6f9d2448" width="20%">
<img src="https://github.com/EwhaARchive/2023-goosukki/assets/81233784/0e581807-6129-4a2f-b4c9-d7f6b906cbc2" width="20%">

- 아이디, 닉네임, 비밀번호, 프로필 이미지를 등록하고 로그인합니다.

#### 홈 화면

- 지도에 게시물 대략적인 위치가 표시되며, 게시물 근처로 이동 시 AR카메라 화면으로 넘어갈 수 있는 창 생성됩니다.

#### AR 카메라 화면
<img src="https://github.com/EwhaARchive/2023-goosukki/assets/81233784/e6426765-0393-476a-aa5d-0e10fd5c915a" width="20%">

- 유니티와 연동한 화면으로 게시물 위치에서 카메라를 키면 비둘기 AR을 보실 수 있습니다.

#### 유저 화면
- 유저 정보, 유저가 쓴 게시물 내용 확인이 가능합니다.
#### 게시물 등록 화면
<img src="https://github.com/EwhaARchive/2023-goosukki/assets/81233784/126a461e-155b-42e9-ae64-164d8c322e0d" width="20%">
<img src="https://github.com/EwhaARchive/2023-goosukki/assets/81233784/f3b34498-5cc6-430e-9e6a-4528c410a496" width="20%">
<img src="https://github.com/EwhaARchive/2023-goosukki/assets/81233784/e678d58e-e88f-4e5a-83ab-d8cc8c1587aa" width="20%">

- 제목, 공개범위, 이미지, 캡션, 현재 위치, 장소명 입력하면 게시물이 생성되며 비둘기 AR을 보실 수 있습니다.
#### 게시물 상세 화면
<img src="https://github.com/EwhaARchive/2023-goosukki/assets/81233784/dd4e3f42-cb31-4e7a-b5ea-695db61a45d0" width="20%" >

- 게시물 내용 전체를 확인하실 수 있습니다.
