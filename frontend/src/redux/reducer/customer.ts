import { CustomerAction, CustomerActionTypes, CustomerType } from "../../types/customer";
import { Message } from "../../types/message";

interface CustomerReducerType {
  loading: boolean,
  error: Message | null,
  customers: CustomerType[]
}

const initState: CustomerReducerType = {
  customers: [],
  loading: false,
  error: null
};


export default (state: CustomerReducerType = initState, action: CustomerAction): CustomerReducerType => {
  switch (action.type) {
    case CustomerActionTypes.CUSTOMER_ERROR:
      return { ...state, loading: false, error: action.payload };
    case CustomerActionTypes.CUSTOMER_LOADING:
      return { ...state, loading: true };
    case CustomerActionTypes.LOAD_CUSTOMERS:
      return { ...state, customers: action.payload };
  }
  return state;
}
