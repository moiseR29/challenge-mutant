import config from 'config';
import { App, Database } from './src/server';
import { loggerClass } from './src/utils';

const logger = loggerClass('index.js');

const initServer = async () => {
  try {
    await Database.initDataBase();
    App.listen(config.get('PORT'), () => logger.info('Server is running'));
  } catch (error) {
    logger.error(error.message);
    process.exit();
  }
};

initServer();
