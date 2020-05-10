import express, { json, urlencoded } from 'express';
import morgan from 'morgan';
import MainRouter from './routes';

import { loggerClass, initMessage, notFoundEndpoint } from './utils';
import { initDataBase } from './database';

const server = express();

// eslint-disable-next-line no-unused-vars
const logger = loggerClass('server.js');

server.use(urlencoded({ extended: false }));
server.use(json());
server.use(
  morgan('combined', { skip: (req, res) => process.env.NODE_ENV === 'test' }),
);

server.get('/', initMessage);

server.use('/', MainRouter);

server.use('*', notFoundEndpoint);

export const App = server;
export const Database = { initDataBase };
