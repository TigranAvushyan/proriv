export interface MessageType {
  id: number,
  type: Message,
  header?: string | null,
  body: string | null,
  footer?: string | null
}

export enum Message {
  ERROR,
  WARN,
  INFO,
  SUCCESS
}

export enum MessageActionTypes {
  ADD_MESSAGE = "ADD_MESSAGE",
  DELETE_MESSAGE = "DELETE_MESSAGE"
}

interface DeleteMessage {
  type: MessageActionTypes.DELETE_MESSAGE,
  payload: number
}

interface AddMessage {
  type: MessageActionTypes.ADD_MESSAGE,
  payload: MessageType
}

export type MessageAction = DeleteMessage | AddMessage
