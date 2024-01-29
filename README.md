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
- [ ] docker image build 후, docker 실행으로 정상되는지 확인

# CI/CD 세팅
- [ ] Github Actions
  - [ ] 빌드
    - gradle 명령어 실행 (build 및 테스트)
    - docker image build
  - [ ] 배포
    - docker image push
    - ssh 접속 후, 서버 호스트에서 docker image pull & restart

# ECR 관련 세팅
- NCP access key, private key

```bash
docker login poten16.kr.ncr.ntruss.com
```

