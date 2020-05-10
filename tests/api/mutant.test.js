import request from 'supertest';
import { expect } from 'chai';
import { App } from '../../src/server';
import {
  connect,
  clearDatabase,
  closeDatabase,
} from '../helpers/mocks/database.mock';
import {
  DNAHUMANO_1,
  DNAHUMANO_2,
  DNAMUTANTE_1,
  DNAMUTANTE_2,
  DNAINVALID_1,
  DNAINVALID_2,
  DNAINVALID_3,
} from '../helpers/constan';

describe('Api Mutants', () => {
  before(async () => {
    await connect();
  });
  describe('Test Endpoint Mutant', () => {
    it('Insert Mutant-1', (done) => {
      request(App)
        .post('/mutant')
        .send({ dna: DNAMUTANTE_1 })
        // eslint-disable-next-line handle-callback-err
        .end((err, res) => {
          expect(res.statusCode).to.equal(200);
          done();
        });
    });

    it('Insert Mutant-2', (done) => {
      request(App)
        .post('/mutant')
        .send({ dna: DNAMUTANTE_2 })
        // eslint-disable-next-line handle-callback-err
        .end((err, res) => {
          expect(res.statusCode).to.equal(200);
          done();
        });
    });

    it('Insert Human-1', (done) => {
      request(App)
        .post('/mutant')
        .send({ dna: DNAHUMANO_1 })
        // eslint-disable-next-line handle-callback-err
        .end((err, res) => {
          expect(res.statusCode).to.equal(403);
          done();
        });
    });

    it('Insert Human-2', (done) => {
      request(App)
        .post('/mutant')
        .send({ dna: DNAHUMANO_2 })
        // eslint-disable-next-line handle-callback-err
        .end((err, res) => {
          expect(res.statusCode).to.equal(403);
          done();
        });
    });

    it('Insert Invalid-1', (done) => {
      request(App)
        .post('/mutant')
        .send({ dna: DNAINVALID_1 })
        // eslint-disable-next-line handle-callback-err
        .end((err, res) => {
          expect(res.statusCode).to.equal(400);
          done();
        });
    });

    it('Insert Invalid-2', (done) => {
      request(App)
        .post('/mutant')
        .send({ dna: DNAINVALID_2 })
        // eslint-disable-next-line handle-callback-err
        .end((err, res) => {
          expect(res.statusCode).to.equal(400);
          done();
        });
    });

    it('Insert Invalid-2', (done) => {
      request(App)
        .post('/mutant')
        .send({ dna: DNAINVALID_3 })
        // eslint-disable-next-line handle-callback-err
        .end((err, res) => {
          expect(res.statusCode).to.equal(400);
          done();
        });
    });
  });

  describe('Test endpoint Stats', () => {
    it('Stats Info', (done) => {
      request(App)
        .get('/stats')
        // eslint-disable-next-line handle-callback-err
        .end((err, res) => {
          expect(res.body).to.have.property('count_mutant_dna', 2);
          expect(res.body).to.have.property('count_human_dna', 2);
          expect(res.body).to.have.property('ratio', 1);
          done();
        });
    });
  });

  after(async () => {
    await clearDatabase();
    await closeDatabase();
  });
});
