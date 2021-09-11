import React, { FC } from "react";
import { LoginRequest } from "../types/auth";
import { History } from "history";
import { useAppActions, useAppSelector } from "../hools/hooks";
import { Button, Col, Form, Input, Row } from "antd";
import { Link } from "react-router-dom";


interface PropsType {
  history: History;
}


const LoginForm: FC<PropsType> = ({ history }) => {

  const { login } = useAppActions();

  const loading = useAppSelector(state => state.auth.isLoading);

  const onSubmit = (request: LoginRequest) => {
    login(request, history);
  };

  return (
      <div className="login">
        <h1>Вход</h1>

        <Row justify="center" align="middle">
          <Col xs={ { span: 18 } } sm={ { span: 16 } }>

            <Form
                name="login"
                labelCol={ { span: 6 } }
                wrapperCol={ { span: 10 } }
                onFinish={ onSubmit }
            >
              <Form.Item
                  label="Логин"
                  name="username"
                  rules={ [{ required: true, message: "Логин не может быть пустым!" }] }
              >
                <Input />
              </Form.Item>

              <Form.Item
                  label="Пароль"
                  name="password"
                  rules={ [{ required: true, message: "Пароль не может быть пустым!" }] }
              >
                <Input.Password />
              </Form.Item>

              <Form.Item wrapperCol={ { offset: 8, span: 16 } }>
                <Button type="primary" htmlType="submit" loading={ loading }>
                  Войти
                </Button>
              </Form.Item>
              <Link to="/registration">Регистрация</Link>
            </Form>
          </Col>
        </Row>
      </div>
  );
};

export default LoginForm;
