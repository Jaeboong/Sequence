## API

### Login
POST
http://localhost:8080/api/login

```json
{
    "id": "testuser123",
    "password": "password123"
}
```

### Register
POST
http://localhost:8080/api/register

```json
{
    "id": "testuser123",
    "password": "password123",
    "name": "홍길동2",
    "birthDate": "1995-01-01",
    "gender": "남성",
    "address": "서울시 강남구",
    "phoneNumber": "010-1234-5678",
    "email": "test@example.com",
    "schoolName": "테스트대학교",
    "majorName": "컴퓨터공학과",
    "entranceYear": "2015",
    "graduationYear": "2019",
    "academicStatus": "졸업",
    "skills": ["Java", "Spring", "React"],
    "desiredPositions": ["백엔드 개발자", "풀스택 개발자"],
    "portfolioUrl": "https://github.com/testuser123",
    "introduction": "안녕하세요. 백엔드 개발자를 희망하는 홍길동입니다."
}
```

### Article
POST
http://localhost:8081/api/articles

**Header**
- Key: Authorization
- Value: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0dXNlcjEyMyIsInVzZXJuYW1lIjoi7ZmN6ri464-ZMiIsImlhdCI6MTczMjQ0NTYxOCwiZXhwIjoxNzMyNTMyMDE4fQ.ONNMdF6gQykRAT_VnPhW6uF-_IIkrUwdGDk0StWa0y8X1Ei7qh69e5JrDfs7yFlzAKWC9tWHclaLMPxZjCiB4w // 로그인해서 받은 token 값

```json
{
    "title": "테스트 제목2",
    "content": "테스트 내용2",
    "username": "홍길동2" 
}
```

## 실행방법

- Docker Desktop에서 Sequence와 SequenceBoard 이름의 mariadb 서버 실행

- WSL 에서 Redis 서버 실행
  * sudo service redis-server start
  * sudo service redis-server status
  * redis-cli ping

- 위 API 요청들로 API 요청 테스트

- 종료 시 Redis 서버 종료
  * sudo service redis-server stop
  * redis-cli shutdown
  * sudo killall redis-server

