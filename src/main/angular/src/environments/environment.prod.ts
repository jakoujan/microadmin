import { InjectableRxStompConfig } from '@stomp/ng2-stompjs';

export const environment = {
  production: false,
  API_URL: '',
  user: '',
  password: '',
  websocket: {
    uri: 'ws://' + window.location.host + '/ws',
    topicPrefix: "/web/action",
    destination: '/updater/order',
    process: {
      topic: '/web/updater',
      prefix: '/updater'
    }
  }
};

export const constants = {
  SESSION: 'session',
  MODULE: 'module',
  TYPE_FILL: 1,
  TYPE_DOSIFICATION: 2,
  ORDER: 'order'
}

export const stompConfig: InjectableRxStompConfig = {
  brokerURL: environment.websocket.uri,
  connectHeaders: {
    login: 'edgar',
    passcode: 'p'
  },
  heartbeatIncoming: 0,
  heartbeatOutgoing: 20000,
  reconnectDelay: 200,
}