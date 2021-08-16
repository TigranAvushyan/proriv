import React, { FC } from 'react';
import { Redirect, Route, Switch } from "react-router-dom";
import Registration from "./components/view/Registration";
import LoginForm from "./components/view/LoginForm";
import Dashboard from "./components/view/Dashboard";
import NotFound from "./components/view/NotFound";
import { useAppSelector } from "./hooks";

const App: FC = () => {

  const isAuth = useAppSelector(state => state.auth.isAuth);


  function redirectHandler() {
    return isAuth ?
        <Dashboard /> :
        <Redirect to="/login" />;
  }

  return (
      <Switch>
        <Route exact path="/login" component={ LoginForm } />
        <Route exact path="/registration" component={ Registration } />
        <Route exact path="/" render={ redirectHandler } />
        <Route path="*" component={ NotFound } />
      </Switch>
  );


};
export default App;
