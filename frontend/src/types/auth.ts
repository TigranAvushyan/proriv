export interface LoginRequest {
  username: string,
  password: string
}

export interface RegistrationRequest {
  username: string,
  password: string,
  phone: string,
  firstName: string,
  lastName: string,
}


export enum AuthActionTypes {
  AUTH_SUCCESS = "AUTH_SUCCESS",
  AUTH_LOADING = "AUTH_LOADING",
  TOKEN_NOT_FOUND = "TOKEN_NOT_FOUND",
  AUTH_LOGOUT = "AUTH_LOGOUT"
}

export interface AuthState {
  isAuth: boolean,
  isLoading: boolean,
  token: string | null
}

interface AuthSuccessAction {
  type: AuthActionTypes.AUTH_SUCCESS,
  payload: string
}


interface AuthLoadingAction {
  type: AuthActionTypes.AUTH_LOADING,
  payload: boolean,
}

interface TokenNotFoundAction {
  type: AuthActionTypes.TOKEN_NOT_FOUND,
}

interface AuthLogout {
  type: AuthActionTypes.AUTH_LOGOUT,
}


export type AuthAction = AuthSuccessAction | AuthLoadingAction | TokenNotFoundAction | AuthLogout
