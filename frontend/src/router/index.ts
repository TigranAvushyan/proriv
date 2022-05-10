import React from "react";
import LoginForm from "../components/LoginForm";
import Registration from "../components/Registration";
import NotFound from "../components/NotFound";
import Dashboard from "../components/Dashboard";


export interface IRoute {
  path: string,
  component: React.ComponentType<any>;
  exact?: boolean
}

export enum RouteNames {
  LOGIN = "/login",
  REGISTRATION = "/registration",
  DASHBOARD = "/",
  NOT_FOUND = "*"
}

export const publicRoutes: IRoute[] = [
  { component: LoginForm, path: RouteNames.LOGIN },
  { component: Registration, path: RouteNames.REGISTRATION },
];

export const privateRoutes: IRoute[] = [
  { component: Dashboard, path: RouteNames.DASHBOARD, exact: true },
  { component: NotFound, path: RouteNames.NOT_FOUND, exact: true }
];
