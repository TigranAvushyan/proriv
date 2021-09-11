import { Dispatch } from "redux";
import { AuthAction, AuthActionTypes, LoginRequest } from "../../types/auth";
import http from "../../axios/http";
import { message } from "antd";
import { History, LocationState } from "history";


export const login = (request: LoginRequest, history: History<LocationState>) => {
  return async (dispatch: Dispatch<AuthAction>) => {
    try {
      dispatch({
        type: AuthActionTypes.AUTH_LOADING,
        payload: true
      });

      const res = await http.post("/auth/login", request);

      const token = res.headers.authorization;
      dispatch({
        type: AuthActionTypes.AUTH_SUCCESS,
        payload: token
      });

      localStorage.setItem("token", token);
      history.push("/");
    } catch (e) {
      localStorage.removeItem("token");
      http.defaults.headers.common["Authorization"] = "";
      message.error("Неправильный логин или пароль");

      dispatch({
        type: AuthActionTypes.AUTH_LOADING,
        payload: false
      });
    }
  };
};

export const logout = () => {
  return async (dispatch: Dispatch<AuthAction>) => {
    try {
      await http.get("/auth/logout");
      dispatch({
        type: AuthActionTypes.AUTH_LOGOUT
      });
    } catch (e) {
      // ERROR
    }
  };
};

