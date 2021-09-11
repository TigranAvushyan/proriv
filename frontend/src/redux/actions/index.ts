import * as AuthActionCreator from "./auth";
import * as OrderActionCreator from "./order";


export default {
  ...AuthActionCreator,
  ...OrderActionCreator
};
