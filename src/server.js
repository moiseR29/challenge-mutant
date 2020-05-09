import express, { json, urlencoded } from 'express';
import morgan from 'morgan';
import MainRouter from './Routes';
import { loggerClass, initMessage, notFoundEndpoint } from './utils';

const app = express();

// eslint-disable-next-line no-unused-vars
const logger = loggerClass('server.js');

app.use(urlencoded({ extended: false }));
app.use(json());
// TODO resolve => morgan deprecated default format: use combined format
app.use(morgan('combined'));

app.get('/', initMessage);

app.use('/', MainRouter);

app.use('*', notFoundEndpoint);

export default app;
