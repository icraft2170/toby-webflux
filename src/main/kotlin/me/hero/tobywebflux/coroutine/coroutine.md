

# 코루틴
- 리액티브 프로그래밍의 경우 러닝커브가 큰 편이다. 코루틴을 사용하게 되면 리액티브 프로그래밍을 명령형 형태로 사용할 수 있다.
- 코루틴을 사용하게 되면 `suspend function(일시 중단 가능 함수)`를 통해 스레드가 실행을 잠시 중단했다가 그 지점부터 다시 실행이 가능하다
  - 리액티브의 Flux 와 Mono를 아래와 같이 변경할 수 있다.
    ```kotlin
      //Mono -> suspend 
      fun handle(): Mono<T> => suspend fun handle()
      //Flux -> Flow
      fun handle(): Flux<T> => fun handle(): Flow<T>
    ```
    - Mono는 `suspend function` 으로 변경
    - Flux 는 Flow로 변경

- WebClient에서 코루틴으로 사용하려면 `bodyToFlux` or `bodyToMono` 에서 `awaitBody<T>` 로 변경사용
- Spring Data R2DB 에서 코루틴 사용 시 레포지토리 상속을 CoroutineCrudRepository 를 상속받고(구현하고) 반환타입을 Mono, Flux -> suspend function , Flow 로 변경 