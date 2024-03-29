# 개발환경
| 분류 |  버전   |
|:---:|:-----:|
| Java |  21   |
| Spring Boot | 3.2.2 |
| Gradle |  8.4  |
| MySQL |  8.2  |

# 인프라
- [x] NCP 서버 1대
  - 서버 기동 (Docker Runtime)
- [x] NCP DB 1대
  - MySQL 8.x 기동
- docker runtime 세팅
  - [x] docker 설치

# ssh 접속 가이드
- `poten16-server.pem` 파일을 다운로드
  - **해당 파일은 오픈채팅방에 요청주시면 전달드리겠습니다.**
- `~/.ssh/config` 파일에 아래 내용 추가
  ```bash
  cat <<EOF >> ~/.ssh/authorized_keys
  Host poten16-server
    IdentityFile ~/.ssh/poten16-server.pem
    HostName 106.10.51.10
    Port 2200
    User root
  EOF
  ```
- `ssh poten16-server` 명령어로 접속
# DB 접속 방법
- **host** : `db-lbpvf.pub-cdb.ntruss.com`
- **user** : `poten16`
- **port** : `3306`
- **password** : 문의주시면 전달드리겠습니다.
```bash
mysql -h db-lbpvf.pub-cdb.ntruss.com -u poten16 -p
```

# Docker Image
- [x] docker image build 후, docker 실행으로 정상되는지 확인

# Docker Build 및 run 가이드
- Gradle의 bootJar 실행
- Gradle의 bootBuildImage 실행
```bash
# in local
docker login poten16.kr.ncr.ntruss.com
docker push poten16.kr.ncr.ntruss.com/poten16-server:{version}
# in server
docker pull poten16.kr.ncr.ntruss.com/poten16-server:{version}
./scripts/run_docker.sh
```
```bash
# run_docker.sh
#!/bin/bash

# Docker 실행 (환경변수는 ~/.bash_profile 에 지정함)
docker run -d --name poten16-server -p 8080:8080 -e MYSQL_URL=${MYSQL_URL} -e MYSQL_USERNAME=${MYSQL_USERNAME} -e MYSQL_PASSWORD=${MYSQL_PASSWORD} poten16.kr.ncr.ntruss.com/poten16-server sleep infinity
```

# CI/CD 세팅
- [x] Github Actions
  - [x] 빌드
    - gradle 명령어 실행 (build 및 테스트)
    - docker image build
  - [x] 배포
    - docker image push
    - ssh 접속 후, 서버 호스트에서 docker image pull & restart

# ECR 관련 세팅
- NCP access key, private key
```bash
docker login poten16.kr.ncr.ntruss.com
```

# 환경변수
| 이름                | 설명                          |
|-------------------|-----------------------------|
| MYSQL_URL         | MYSQL 주소입니다 (JDBC 형태여야 합니다) |
| MYSQL_USERNAME    | MYSQL 사용자 명 입니다.            |
| MYSQL_PASSWORD    | MYSQL 비밀번호 입니다.             |
| GPT_TOKEN         | GPT API-KEY 입니다.            |
| W3W_API_KEY       | W3W API-KEY 입니다.            |
| GPT_REQUEST_COUNT | GPT 요청 제한 횟수 입니다.           |
| W3W_REQUEST_COUNT | W3W 요청 제한 횟수 입니다.           |

# Domain 모델링
- Member
  - id
  - deviceId : mobile device 고유값 (unique key) --> 
  - deviceOs : (iOS/Android)
  - nickname
  - maxNumber
- Card (Root Aggregate)
  - id
  - memberId
  - poemId
  - font
  - fontColor
  - backGround
  - coordinates
    - latitude
    - longitude
  - number -> 유저별 카드 생성 번호 : Member.maxNumber + 1
- Poem (= ChatGptResult)
  - id
  - gptRequestHash (4단어 해쉬 처리) : unique key
  - title
  - words
  - content : VARCHAR(255)

- W3wResult
  - id
  - country
  - nearestPlace
  - latitude
  - longitude
  - words
  - language
  - locale
  - map

---
# API 설계
## HTTP Header
> `-H POEM-DEVICE-ID: xxxxxxxxx`

## API 목록
### Member
- [x] GET /api/v1/members/check : 등록된 맴버인지 조회
- [x] POST /api/v1/members : 맴버 저장

### Card
- [x] GET /api/v1/cards : 특정 요청자의 카드 목록
- [x] GET /api/v1/cards/{id} : 특정 카드 조회
- [x] GET /api/v1/cards/readiness : Open Api(gpt,w3w) 유효성 검증 & 사용 제한 횟수 HttpStatus: 500
- [x] POST /api/v1/cards : 카드 생성
  - 카드 넘버링도 저장?
  - RequestBody
    - userId
    - coordinate (위도, 경도) --> w3w API 요청 파라미터로 사용
    - additionalWord (추가 단어) - 위치 관련 단어
    - backGroundImageId
- [x] POST /api/v1/cards/poem : 특정 카드의 시 생성
- ~~PUT /api/v1/cards/{id} : 카드 수정~~
- ~~DELETE /api/v1/cards/{id} : 카드 삭제~~ (확인 필요)
---
# TODO
- [x] W3W 캐시 처리
