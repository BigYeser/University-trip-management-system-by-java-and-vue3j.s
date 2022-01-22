// @@@START:"Network.js"
import axios from 'axios';

class NetworkService {

    httpClient = axios.create({
        headers: {
            "Accept": "application/json",
        }
    });

    getDispatcherState() {
        return this.httpClient.get("http://localhost:8080/exam03/api/");
    }

    getAllStudyTripsState(url) {
        return this.httpClient.get(url);
    }

// @@@END

    getSingleStudyTripState(url) {
        return this.httpClient.get(url);
    }
    getSingleStudentStudyTripState(url){
        return this.httpClient.get(url);
    }

    getAllStudentOfStudyTripState(url) {
        return this.httpClient.get(url);
    }

    getSingleStudent(url) {
        return this.httpClient.get(url);
    }

    linkStudent(url, student) {
        return this.httpClient.put(url, student);
    }

    unlinkStudent(url) {
        return this.httpClient.delete(url);
    }
}

export default new NetworkService();
