import http from 'k6/http';
import { check } from 'k6';

export const options = {
    stages: [
        { duration: '1s', target: 1000 },  // 1초 동안 500명 생성 (스파이크 발생)
        { duration: '30s', target: 1000 }, // 30초 동안 500명 유지
        { duration: '10s', target: 0 },   // 10초 동안 사용자 수 0으로 감소
    ],
};

export default function () {
    const baseUrl = 'http://localhost:8080'; // API의 기본 URL
    const orderId = Math.floor(Math.random() * 3) + 1;; // 무작위로 orderId 생성 (1 ~ 10000)
    const url = `${baseUrl}/api/orders/${orderId}`; // API 경로

    // HTTP GET 요청
    const response = http.get(url);

    // 응답 검증
    check(response, {
        'is status 200': (r) => r.status === 200,
        'is response valid': (r) => r.body && r.body.length > 0,
    });
}

// k6 run --out influxdb=http://localhost:8086/k6 k6/stress-test.js
