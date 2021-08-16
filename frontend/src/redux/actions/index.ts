import * as AuthActionCreator from "./auth"
import * as MassageActionCreator from "./message"


export default {
  ...AuthActionCreator,
  ...MassageActionCreator
}
