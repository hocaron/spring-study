import grpc from 'k6/x/grpc';
import { check, sleep } from 'k6';

export let options = {
    vus: 10, // Number of virtual users
    duration: '15s', // Duration of the test
};

const client = new grpc.Client();
client.load(['proto'], 'helloworld.proto');

export default () => {
    client.connect('localhost:9090', {
        plaintext: true
    });

    const request = { id: 1 };
    const params = {
        metadata: {
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
        }
    };
    const response = client.invoke('Greeter/GetMemberInfo', request, params);

    check(response, {
        'status is OK': (r) => r && r.status === grpc.StatusOK
    });

    client.close();
    sleep(1);
};
