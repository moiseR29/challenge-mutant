import app from './src/server';
import config from 'config';
import { loggerClass } from './src/utils';

const logger = loggerClass('index.js');

const initServer = () => {
  try {
    app.listen(config.get('PORT'), () => logger.info('Server is running'));
  } catch (error) {
    logger.error(error.message);
    process.exit();
  }
};

initServer();
