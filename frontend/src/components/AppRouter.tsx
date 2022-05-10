import React, { FC } from "react";
import { useAppSelector } from "../hools/hooks";
import { Redirect, Route, Switch } from "react-router-dom";
import { privateRoutes, publicRoutes, RouteNames } from "../router";

interface AppRouterPropsType {

}

const AppRouter: FC<AppRouterPropsType> = () => {
  const { isAuth } = useAppSelector(state => state.auth);
  return (
      <>
        { isAuth ?
            <Switch>
              { privateRoutes.map(r => (
                  <Route exact={ r.exact } path={ r.path } component={ r.component } />
              )) }
              <Redirect to={RouteNames.DASHBOARD}/>
            </Switch>
            :
            <Switch>
              { publicRoutes.map(r => (
                  <Route exact={ r.exact } path={ r.path } component={ r.component } />
              )) }
              <Redirect to={RouteNames.LOGIN}/>
            </Switch>
        }
      </>
  );
};

export default AppRouter;
