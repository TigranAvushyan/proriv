import { Message } from "./message";

export interface CustomerType {
  id: number,
  name: string,
  address: {
    region: string,
    city: string,
    street: string,
    house: string
  },
  phone: string[]
}


export enum CustomerActionTypes {
  CUSTOMER_LOADING = "CUSTOMER_LOADING",
  CUSTOMER_ERROR = "CUSTOMER_ERROR",
  LOAD_CUSTOMERS = "LOAD_CUSTOMERS"
}

interface CustomerLoading {
  type: CustomerActionTypes.CUSTOMER_LOADING;
}

interface CustomerError {
  type: CustomerActionTypes.CUSTOMER_ERROR,
  payload: Message
}
interface LoadCustomers {
  type: CustomerActionTypes.LOAD_CUSTOMERS,
  payload: CustomerType[]
}

export type CustomerAction = CustomerLoading | CustomerError | LoadCustomers
