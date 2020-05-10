import mongoose from 'mongoose';
import config from 'config';
import { loggerClass } from '../utils';

const logger = loggerClass('connection.js');

export const initDataBase = async () => {
  try {
    await mongoose.connect(config.get('DB_URL'), {
      useNewUrlParser: true,
      useUnifiedTopology: true,
      useFindAndModify: false,
    });
    logger.info('Database on');
    logger.info(`Database name: ${mongoose.connection.db.databaseName}`);
  } catch (error) {
    logger.error(error.message);
    process.exit();
  }
};
