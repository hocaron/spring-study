import http from 'k6/http';
import { check } from 'k6';

export const options = {
    stages: [
        { duration: '0s', target: 100 },
        { duration: '3m', target: 100 },
        { duration: '10s', target: 0 }
    ]
};

export default function () {
    const baseUrl = 'http://localhost:8090';
    const orderId = Math.floor(Math.random() * 3) + 1; // 1~3 사이의 랜덤 Order ID
    const url = `${baseUrl}/api/orders/${orderId}`;

    const response = http.get(url);

    const success = check(response, {
        'status is 200': (r) => r.status === 200,
        'response body is not empty': (r) => r.body && r.body.length > 0,
    });
}
