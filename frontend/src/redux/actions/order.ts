import { Dispatch } from "redux";
import { OrderAction, OrderActionTypes } from "../../types/order";
import { CustomerAction, CustomerActionTypes } from "../../types/customer";
import http from "../../axios/http";
import { MessageType } from "../../types/message";

export const fetchTable = () => {
  return async (dispatch: Dispatch<OrderAction | CustomerAction>) => {
    dispatch({
      type: OrderActionTypes.ORDER_LOADING
    });
    try {
      const res = await http.get("/order/table");

      dispatch({
        type: CustomerActionTypes.LOAD_CUSTOMERS,
        payload: res.data.customers
      })
      console.log(res.data.customers);
    } catch (e) {
      dispatch({
        type: OrderActionTypes.ORDER_ERROR,
        payload: {
          type: MessageType.ERROR,
          message: "Error"
        }
      });
    }

  };
};
