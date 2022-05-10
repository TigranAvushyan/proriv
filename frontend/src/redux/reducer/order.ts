import { OrderAction, OrderActionTypes, OrderType } from "../../types/order";
import { Message } from "../../types/message";

interface OrderReducerType {
  orders: OrderType[],
  loading: boolean,
  error: Message | null
}

const initState: OrderReducerType = {
  orders: [],
  loading: false,
  error: null
};


export default (state = initState, action: OrderAction): OrderReducerType => {
  switch (action.type) {
    case OrderActionTypes.ORDER_LOADING:
      return { ...state, loading: true };
    case OrderActionTypes.ORDER_ERROR:
      const error: Message = {
        message: action.payload.message,
        type: action.payload.type
      };
      return { ...state, loading: false, error };
  }
  return state;
}
