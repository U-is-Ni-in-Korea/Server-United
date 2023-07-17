## Contributors
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-3-orange.svg?style=flat-square)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->
<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tbody>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="http://antilog.tistory.com/"><img src="https://avatars.githubusercontent.com/u/45380072?v=4?s=100" width="100px;" alt="Jinsu Park"/><br /><sub><b>Jinsu Park</b></sub></a><br /><a href="https://github.com/U-is-Ni-in-Korea/Server-United/commits?author=jinsu4755" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/2zerozu"><img src="https://avatars.githubusercontent.com/u/84129098?v=4?s=100" width="100px;" alt="YeongJu Lee"/><br /><sub><b>YeongJu Lee</b></sub></a><br /><a href="https://github.com/U-is-Ni-in-Korea/Server-United/commits?author=2zerozu" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/jiyeoon00"><img src="https://avatars.githubusercontent.com/u/77728683?v=4?s=100" width="100px;" alt="Jiyeon"/><br /><sub><b>Jiyeon</b></sub></a><br /><a href="https://github.com/U-is-Ni-in-Korea/Server-United/commits?author=jiyeoon00" title="Code">💻</a></td>
    </tr>
  </tbody>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

# Server-United
![image](https://github.com/U-is-Ni-in-Korea/Server-United/assets/45380072/a4a4d35f-3980-45e7-8469-b9774bc5150b)
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

<div >
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" alt="Java" title="Java"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/183891303-41f257f8-6b3d-487c-aa56-c497b880d0fb.png" alt="Spring Boot" title="Spring Boot"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/190229463-87fa862f-ccf0-48da-8023-940d287df610.png" alt="Lombok" title="Lombok"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/183892787-bca94a0e-ffcb-4eeb-8137-e0fc4e446c25.png" alt="Groovy" title="Groovy"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/183896128-ec99105a-ec1a-4d85-b08b-1aa1620b2046.png" alt="MySQL" title="MySQL"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/183896132-54262f2e-6d98-41e3-8888-e40ab5a17326.png" alt="AWS" title="AWS"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192108372-f71d70ac-7ae6-4c0d-8395-51d8870c2ef0.png" alt="Git" title="Git"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192108374-8da61ba1-99ec-41d7-80b8-fb2f7c0a4948.png" alt="GitHub" title="GitHub"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192108890-200809d1-439c-4e23-90d3-b090cf9a4eea.png" alt="InteliJ" title="InteliJ"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192109061-e138ca71-337c-4019-8d42-4792fdaa7128.png" alt="Postman" title="Postman"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/189715289-df3ee512-6eca-463f-a0f4-c10d94a06b2f.png" alt="Figma" title="Figma"/></code>
</div>
</br>

![Alt](https://repobeats.axiom.co/api/embed/a1def2ffce467927e0e745a6f8ddcff24b0b3f0a.svg "Repobeats analytics image")

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

### 역할 분담
```
박진수
DB 설계, AWS 인프라, CD 구성, User 도메인 및 소셜 로그인 api, 잡일
```
```
이영주
DB 설계, CI구성, 소원권 및 미션 카테고리 api
```
```
신지연
DB 설계, 한판 승부 및 승부 히스토리 api
```

### Architecture

<img width="672" alt="image" src="https://github.com/TeamPophory/pophory-server/assets/81692211/4e1ac301-9bc5-4fb9-b877-d58c18c0ca8b">
