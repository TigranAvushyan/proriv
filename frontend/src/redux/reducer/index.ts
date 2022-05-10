import { combineReducers } from "redux";
import auth from "./auth";
import order from "./order";
import customer from "./customer";

export const rootReducer = combineReducers({
  auth, order, customer
});

export type RootState = ReturnType<typeof rootReducer>;
