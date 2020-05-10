import config from 'config';

import { App } from '../src/server';

describe('Check app On', () => {
  it('app running', (done) => {
    try {
      App.listen(config.get('PORT'));
      done();
    } catch (error) {
      process.exit();
    }
  });
});
