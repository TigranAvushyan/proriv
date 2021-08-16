import { combineReducers } from "redux";
import auth from "./auth";
import massage from "./message";

export const rootReducer = combineReducers({
  auth, massage
});

export type RootState = ReturnType<typeof rootReducer>;
