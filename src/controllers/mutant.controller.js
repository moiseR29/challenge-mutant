import { mutantCheck, HUMAN, MUTANT, loggerClass } from '../utils';
import { Stats } from '../database';

const logger = loggerClass('mutant.controller');

export const isMutant = async (req, res) => {
  try {
    const { dna } = req.body;
    mutantCheck.isMutant(dna) ? await mutant(res, dna) : await human(res, dna);
  } catch (error) {
    res.status(400).send(error.message);
  }
};

const mutant = async (response, dna) => {
  try {
    const exists = await isExists(dna);
    if (!exists) {
      await Stats.create({ dna, type: MUTANT });
    }
    return response.send(`is ${MUTANT}`);
  } catch (error) {
    logger.error('Error: ', error);
    throw Error('dna exists');
  }
};

const human = async (response, dna) => {
  try {
    const exists = await isExists(dna);
    if (!exists) await Stats.create({ dna });

    return response.status(403).send(`is ${HUMAN}`);
  } catch (error) {
    logger.error('Error: ', error.message);
    throw Error('dna exists');
  }
};

const isExists = async (dna) => {
  const exist = await Stats.findOne({ dna });
  return !!exist;
};
