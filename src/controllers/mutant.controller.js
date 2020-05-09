import { mutantCheck } from '../utils';

export const isMutant = (req, res) => {
  try {
    const { dna } = req.body;
    mutantCheck.isMutant(dna)
      ? res.send('is Mutant')
      : res.status(403).send('is not mutant');
  } catch (error) {
    res.status(400).send(error.message);
  }
};
