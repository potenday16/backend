# 개발환경
| 분류 |  버전   |
|:---:|:-----:|
| Java |  21   |
| Spring Boot | 3.2.2 |
| Gradle |  8.4  |
| MySQL |  8.2  |

# 인프라
- [ ] NCP 서버 1대
  - 서버 기동 (Docker Runtime)
- [ ] NCP DB 1대
  - MySQL 8.x 기동
- docker runtime 세팅
  - [ ] docker 설치
  - [ ] docker-compose 설치
  
# 접속 가이드
- [ ] ssh 접속 가이드 작성

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

```

