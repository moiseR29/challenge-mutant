import log4js from 'log4js';

log4js.configure({
  appenders: {
    file: {
      type: 'file',
      filename: 'logs/file.log',
      maxLogSize: 10 * 1024 * 1024, // = 10Mb
      backups: 5, // keep five backup files
      compress: true, // compress the backups
      encoding: 'utf-8',
      mode: 0o0640,
      flags: 'w+',
    },
    errorsLogs: {
      type: 'file',
      filename: 'logs/errors.log',
      maxLogSize: 10 * 1024 * 1024,
      backups: 5,
      compress: true,
      encoding: 'utf-8',
      mode: 0o0640,
      flags: 'w+',
    },
    justErrors: {
      type: 'logLevelFilter',
      appender: 'errorsLogs',
      level: 'error',
    },
    out: {
      type: 'stdout',
      layout: {
        type: 'colored',
      },
    },
  },
  categories: {
    default: { appenders: ['file', 'justErrors', 'out'], level: 'all' },
  },
});

export const loggerClass = (className) => {
  return log4js.getLogger(className);
};
