import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    vus: 10, // Number of virtual users
    duration: '30s', // Duration of the test
};

export default () => {
    const url = 'http://localhost:8090/members/1';
    const response = http.get(url);

    check(response, {
        'status is 200': (r) => r.status === 200,
        'response is correct': (r) => {
            const json = r.json();
            return json.id === 1
                // && json.email === 'user123@example.com'
                // && json.identification === 'ID123456789'
                // && json.phoneNumber === '010-1234-5678';
        },
    });

    sleep(1);
};
