import { MessageAction, MessageActionTypes, MessageType } from "../../types/message";


const initState: MessageType[] = [];

export default (state: MessageType[] = initState, action: MessageAction): MessageType[] => {
  switch (action.type) {
    case MessageActionTypes.ADD_MESSAGE:
      state.push(action.payload);
      return state;
    case MessageActionTypes.DELETE_MESSAGE:
      return state.filter(massage => massage.id !== action.payload);
  }
  return state;
}
