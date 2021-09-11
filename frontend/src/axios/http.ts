import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:8080/api/"
});

http.interceptors.request.use(function (config) {
  const token: string | null = localStorage.getItem("token");
  config.headers.Authorization = token || "";
  return config;
});


export default http;
