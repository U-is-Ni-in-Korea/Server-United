# Server-United
해당 프로젝트는 SOPT 32th APPJAM 프로젝트입니다.

### Build
```text
$ ./gradlew clean build
```
### Run
```text
$ java -jar build/libs/uni-${version}-SNAPSHOT-plain.jar
```

### Project Info

### Coding Convention
[naver hackday convetions](https://naver.github.io/hackday-conventions-java/)

### Commit Convention
[https://www.conventionalcommits.org/en/v1.0.0/](https://www.conventionalcommits.org/en/v1.0.0/)

### Branching Strategy
main : 제품으로 출시될 수 있는 브랜치
develop :  다음 출시 버전을 개발하는 브랜치
feature/{feature_name} : 기능을 개발하는 브랜치
release_{version} : 이번 출시 버전을 준비하는 브랜치
hotfix : 출시 버전에서 발생한 버그를 수정 하는 브랜치

### 프로젝트 폴더링

```                 
.
├── src
    ├── main
        ├── common
        ├── config
        ├── controller
        ├── domain
            ├── entity
        ├── dto
        ├── exception
        ├── external
        ├── mapper
        ├── repository
        ├── service
        
    └──  test
```