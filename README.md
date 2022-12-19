# 채팅 프로젝트
SSE를 적용하여 채팅 메세지가 즉각적으로 페이지에 적용되도록 채팅 서비스를 개발했습니다.
* SSE : 서버와 한번 연결을 맺은 후, 일정 시간동안 서버에서 변경이 발생할 때마다 데이터를 전송받는 방법

![image](https://user-images.githubusercontent.com/84280815/208385679-03cd2efb-3aac-4d34-8c9f-83d3d9be415f.png)

## Endpoint
### View
- 채팅창 `GET /chat/room`

### API
- 채팅 등록 `POST /chat/writeMessage`
```json
{
  "authorName":"String",
  "content":"String"
}
```
- 채팅 전체 조회 `GET /chat/messages`
- fromId 이후 채팅 조회 `GET /chat/messages?fromId={id}`
