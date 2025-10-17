import axios from "axios";

const api = axios.create({
    baseURL: 'http://localhost:8081/AuthenticationSystem',
    withCredentials: true,
    headers: {
        "Content-Type": "application/x-www-form-urlencoded"
    }
});

export default api;