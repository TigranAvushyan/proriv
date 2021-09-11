import React, { FC } from "react";
import { Button, Col, Form, Input, message, Row } from "antd";
import { RegistrationRequest } from "../types/auth";
import http from "../axios/http";
import { History } from "history";

const Registration: FC<{ history: History }> = ({ history }) => {


  const [loading, setLoading] = React.useState<boolean>(false);


  const onSubmit = async (value: RegistrationRequest) => {
    setLoading(true);
    try {
      await http.post("/auth/registration", value);
      history.push("/login");
    } catch (e) {
      message.error("Логин уже занят!");
    } finally {
      setLoading(false);

    }
  };

  return (
      <div className="login">

        <h1>Регистрация</h1>

        <Row justify="center" align="middle">
          <Col xs={ { span: 18 } } sm={ { span: 16 } }>

            <Form
                name="registration"
                labelCol={ { span: 6 } }
                wrapperCol={ { span: 10 } }
                onFinish={ onSubmit }
            >


              <Form.Item
                  label="Имя"
                  name="firstName"
                  rules={ [{ required: true, message: "Пишите ваше имя!" }] }
              >
                <Input />
              </Form.Item>

              <Form.Item
                  label="Фамилия"
                  name="lastName"
                  rules={ [{ required: true, message: "Пишите ваше Фамилия!" }] }
              >
                <Input />
              </Form.Item>


              <Form.Item
                  label="Телефон"
                  name="phone"
                  rules={ [
                    { required: true, message: "Пишите ваш номер телефона" },
                    { pattern: /^(\+7|8)\d{10}$/, message: "Указан неправильный номер телефона" }] }

              >
                <Input placeholder="8 xxx xxx xx xx" />
              </Form.Item>

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
                  Зарегистрироваться
                </Button>
              </Form.Item>
            </Form>
          </Col>
        </Row>

      </div>
  );
};

export default Registration;
