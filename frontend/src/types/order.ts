import { Message } from "./message";
import { CustomerType } from "./customer";

export interface Product {
  name: string,
  price: number
}

export interface OrderItemType {
  product: Product,
  quantity: number,
  exchange: number,
  returnProduct: number,
}

export enum OrderStatus {
  STARTED, ACCEPTED, NOT_ACCEPTED
}

export interface OrderType {
  id: number,
  orderItems: OrderItemType[],
  customer: CustomerType,
  orderDate: Date | null,
  deliveryDate: Date | null,
  status: OrderStatus,
  loading: boolean,
  error: Message | null
}


export enum OrderActionTypes {
  ORDER_LOADING = "ORDER_LOADING",
  ORDER_ERROR = "ORDER_ERROR",
  LOAD_ORDER_ITEMS = "LOAD_ORDER_ITEMS",
  CHANGE_ORDER_STATUS = "CHANGE_ORDER_STATUS"
}

interface OrderLoading {
  type: OrderActionTypes.ORDER_LOADING,
}

interface ChangeOrderStatus {
  type: OrderActionTypes.CHANGE_ORDER_STATUS,
  payload: {
    orderId: number,
    status: OrderStatus
  }
}

interface OrderError {
  type: OrderActionTypes.ORDER_ERROR,
  payload: Message
}

interface LoadOrderItems {
  type: OrderActionTypes.LOAD_ORDER_ITEMS,
  payload: OrderItemType
}

export type OrderAction = OrderLoading
    | OrderError
    | LoadOrderItems
    | ChangeOrderStatus
