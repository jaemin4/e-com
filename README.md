
### 1. 요구사항

- `e-커머스 상품 주문 서비스`를 구현해 봅니다.
- 상품 주문에 필요한 메뉴 정보들을 구성하고 조회가 가능해야 합니다.
- 사용자는 상품을 여러개 선택해 주문할 수 있고, 미리 충전한 잔액을 이용합니다.
- 상품 주문 내역을 통해 판매량이 가장 높은 상품을 추천합니다.

### 2. API Specs

### 기본과제

1️⃣ **`주요`** **잔액 충전 / 조회 API**

- 결제에 사용될 금액을 충전하는 API 를 작성합니다.
- 사용자 식별자 및 충전할 금액을 받아 잔액을 충전합니다.
- 사용자 식별자를 통해 해당 사용자의 잔액을 조회합니다.

2️⃣ **`기본` 상품 조회 API**

- 상품 정보 ( ID, 이름, 가격, 잔여수량 ) 을 조회하는 API 를 작성합니다.
- 조회시점의 상품별 잔여수량이 정확하면 좋습니다.

3️⃣  **`주요` 선착순 쿠폰 기능**

- 선착순 쿠폰 발급 API 및 보유 쿠폰 목록 조회 API 를 작성합니다.
- 사용자는 선착순으로 할인 쿠폰을 발급받을 수 있습니다.
- 주문 시에 유효한 할인 쿠폰을 함께 제출하면, 전체 주문금액에 대해 할인 혜택을 부여받을 수 있습니다.

4️⃣ **`주요`** **주문 / 결제 API**

- 사용자 식별자와 (상품 ID, 수량) 목록을 입력받아 주문하고 결제를 수행하는 API 를 작성합니다.
- 결제는 기 충전된 잔액을 기반으로 수행하며 성공할 시 잔액을 차감해야 합니다.
- 데이터 분석을 위해 결제 성공 시에 실시간으로 주문 정보를 데이터 플랫폼에 전송해야 합니다. ( 데이터 플랫폼이 어플리케이션 `외부` 라는 가정만 지켜 작업해 주시면 됩니다 )

> 데이터 플랫폼으로의 전송 기능은 Mock API, Fake Module 등 다양한 방법으로 접근해 봅니다.
> 

5️⃣  **`기본` 상위 상품 조회 API**

- 최근 3일간 가장 많이 팔린 상위 5개 상품 정보를 제공하는 API 를 작성합니다.
    - 통계 정보를 다루기 위한 기술적 고민을 충분히 해보도록 합니다.

### 심화과제

- **(심화)** 재고 관리에 문제 없도록 구현합니다.
- **(심화)** 동시성 이슈를 고려하여 구현합니다.
- **(심화)** 다수의 인스턴스로 어플리케이션이 동작하더라도 기능에 문제가 없도록 작성하도록 합니다.

## 3. 설계 시작

## ERD

이 ERD(Entity-Relationship Diagram)는 **포인트, 쿠폰, 결제 관리 시스템**의 구조를 표현한 것

![image](https://github.com/user-attachments/assets/9b8f84be-5a54-432a-b813-543f9503058c)



| **Entity 관계** | **설명** |
| --- | --- |
| UserEntity ↔ PointEntity | 1:1 관계 - 사용자마다 하나의 포인트 계정 보유 |
| UserEntity ↔ PointHistoryEntity | 1:N 관계 - 사용자의 포인트 충전/사용 이력 관리 |
| UserEntity ↔ UserCouponEntity | 1:N 관계 - 사용자가 여러 개의 쿠폰 보유 가능 |
| CouponEntity ↔ UserCouponEntity | 1:N 관계 - 동일한 쿠폰을 여러 사용자가 보유 가능 |
| UserEntity ↔ PaymentHistoryEntity | 1:N 관계 - 사용자별 결제 내역 관리 |
| ProductEntity ↔ PaymentHistoryEntity | 1:N 관계 - 상품별 판매 이력 관리 |

### 클래스 레이어

| **Controller** | REST API 요청 처리 | 각 기능별로 컨트롤러가 잘 분리되어 있음 (`PointController`, `CouponController`, 등) |
| --- | --- | --- |
| **FrontService** | 여러 서비스 조합 + 트랜잭션 처리 | 복잡한 로직을 FrontService로 분리한 건 좋음 (트랜잭션 단위 유지에 유리) |
| **Service** | 비즈니스 로직 처리 | `PointService`, `CouponService` 등 도메인 단위로 잘 나눠져 있음 |
| **Repository** | DB 접근 계층 | `JpaRepository`를 기반으로 필터링 메서드도 잘 작성되어 있음 |


![image](https://github.com/user-attachments/assets/215e72b5-5a15-491c-b75b-5057b393d1a9)
### layer흐름
### 1. 💳 포인트 충전/사용/조회 흐름

- `PointController` → `PointFrontService`
    
    → `PointService` + `PointHistoryService` + `UserService`
    
- `PointService`
    - 유저별 포인트 조회, 포인트 충전/사용
    - `PointJpaRepository` 통해 포인트 엔티티 관리
- `PointHistoryService`
    - 포인트 변동 이력 저장
- 트랜잭션 예시: `chargeUserPoint`, `useUserPoint`

### 2. 🎟 쿠폰 발급 및 사용

- `CouponController` → `CouponFrontService`
    
    → `CouponService` + `UserCouponService` + `UserService`
    
- `CouponService`: 쿠폰 엔티티 조회, 수량 감소
- `UserCouponService`: 발급/사용한 쿠폰 저장
- `findByCouponIdForLock()`으로 발급 동시성 제어

### 3. 🛒 결제 처리

- `PaymentController` → `PaymentFrontService`
    
    → `PaymentHistoryService` + `ProductService` + `PointService`
    
- `PaymentFrontService`: 포인트 차감 + 결제 이력 저장
- `ProductService`: 상품 재고 감소

### 4. 📦 상품 조회

- `ProductController` → `ProductFrontService` → `ProductService`
- 단순한 조회
  
API 명세서
https://022d-2406-5900-112b-dc48-319d-bb57-1de6-c7c9.ngrok-free.app/swagger-ui/index.html#/%EC%BF%A0%ED%8F%B0/issueCoupon
