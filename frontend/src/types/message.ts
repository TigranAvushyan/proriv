export interface Message {
  message: string,
  title?: string,
  type: MessageType
}

export enum MessageType {
  ERROR,
  WARN,
  INFO
}
