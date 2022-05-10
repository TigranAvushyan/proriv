import { AuthAction, AuthActionTypes, AuthState } from "../../types/auth";
import { isAuth } from "../../utils";

const token = localStorage.getItem("token") || null;

const initState: AuthState = {
  isAuth: isAuth(token),
  isLoading: false,
  token
};


export default (state: AuthState = initState, action: AuthAction): AuthState => {
  switch (action.type) {
    case AuthActionTypes.AUTH_LOADING:
      return { ...state, isLoading: action.payload };
    case AuthActionTypes.AUTH_SUCCESS:
      return { ...state, isAuth: true, token: action.payload };
    case AuthActionTypes.TOKEN_NOT_FOUND:
      return { ...state, token: null, isAuth: false };
    case AuthActionTypes.AUTH_LOGOUT:
      return { ...state, token: null, isAuth: false };
  }
  return state;
}
