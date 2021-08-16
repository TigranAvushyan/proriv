import { Message, MessageAction, MessageActionTypes, MessageType } from "../../types/message";
import { Dispatch } from "redux";

interface AddMessage {
  type: Message,
  header?: string | null,
  body: string | null,
  footer?: string | null
}

export const addMassage = (m: AddMessage) => {
  return (dispatch: Dispatch<MessageAction>) => {
    const massage: MessageType = { ...m, id: Date.now() };
    dispatch({
      type: MessageActionTypes.ADD_MESSAGE,
      payload: massage
    });
  };
};
export const deleteMassage = (id: number) => {
  return (dispatch: Dispatch<MessageAction>) => {
    dispatch({
      type: MessageActionTypes.DELETE_MESSAGE,
      payload: id
    });

  };
};
