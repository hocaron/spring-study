# 🚀 Spring Boot 성능 테스트 가이드

## 개요

이 프로젝트는 k6, InfluxDB, Prometheus, Grafana를 활용하여 Spring Boot 애플리케이션의 성능 테스트 및 최적화를 수행합니다.

## 🛠 사전 준비 사항

- Docker
- Docker Compose
- k6

## 📋 설정 단계

### 1. 모니터링 환경 구성

Docker Compose를 사용하여 모니터링 인프라를 시작합니다.

```bash
docker-compose up -d
```

다음 서비스가 실행됩니다.
- k6 테스트 데이터 저장을 위한 InfluxDB (버전 1.8)
- 메트릭 수집을 위한 Prometheus
- 테스트 결과 시각화를 위한 Grafana

### 2. 애플리케이션 빌드 및 실행

#### 애플리케이션 빌드
```bash
docker build -t warm-up:1 .
```

#### 컨테이너 실행
```bash
docker run -d \
  --name warm-up \
  --memory=2g \
  --cpus=2 \
  -p 8090:8080 \
  -e RUN_JAVA_ENV="-Dspring.profiles.active=docker" \
  warm-up:1
```

### 🔥 3. 성능 테스트 실행(k6)

```bash
k6 run --out influxdb=http://localhost:8086/k6 k6/stress-test.js
```

- `--out influxdb=http://localhost:8086/k6`: k6 테스트 결과를 InfluxDB에 저장
- `k6/stress-test.js`: 부하 테스트 스크립트

### 📊 4. 결과 확인

- 브라우저에서 http://localhost:3000 열기
- 자격 증명: admin / admin
- Grafana 성능 대시보드로 이동
