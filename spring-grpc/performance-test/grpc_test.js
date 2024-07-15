import grpc from 'k6/x/grpc';
import { check, sleep } from 'k6';

export let options = {
    vus: 10, // Number of virtual users
    duration: '30s', // Duration of the test
};

const client = new grpc.Client();
client.load(['proto'], 'helloworld.proto');

export default () => {
    client.connect('localhost:9090', {
        plaintext: true
    });

    const data = { id: 1 };
    const response = client.invoke('Greeter/GetMemberInfo', data);

    check(response, {
        'status is OK': (r) => r && r.status === grpc.StatusOK,
        'id is correct': (r) => r.message && r.message.id === "1",
        'email is correct': (r) => r.message && r.message.email === 'user123@example.com',
        'identification is correct': (r) => r.message && r.message.identification === 'ID123456789',
        'phoneNumber is correct': (r) => r.message && r.message.phoneNumber === '010-1234-5678',
    });

    client.close();
    sleep(1);
};
