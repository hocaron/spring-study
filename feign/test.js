import http from "k6/http";
import { sleep } from "k6";

export const options = {
    vus: 200,        // 동시 사용자 수
    duration: '10s',  // 테스트 지속 시간
    timeout: '60s',  // 예: 60초 타임아웃
};

export default function () {
    http.get("http://localhost:8080/date");
    sleep(1);  // 1초 대기
}
