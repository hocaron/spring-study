import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    vus: 10, // Number of virtual users
    duration: '15s', // Duration of the test
};

export default () => {
    const url = 'http://localhost:8090/members/1';
    const headers = {
        'Header1': 'Value1',
        'Header2': 'Value2',
        'Header3': 'Value3',
        'Header4': 'Value4',
        'Header5': 'Value5',
        'Header6': 'Value6',
        'Header7': 'Value7',
        'Header8': 'Value8',
        'Header9': 'Value9',
        'Header10': 'Value10'
    };
    const response = http.get(url, { headers: headers });

    check(response, {
        'status is 200': (r) => r.status === 200
    });

    sleep(1);
};
