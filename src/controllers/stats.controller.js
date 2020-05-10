import { Stats } from '../database';
import { loggerClass } from '../utils';

const logger = loggerClass('stats.controller');

export const getStats = async (req, res) => {
  try {
    const response = await formatResponse();
    res.send(response);
  } catch (error) {
    logger.error(error.message);
    res.status(500).send('Server Error');
  }
};

const formatResponse = async () => {
  const statsResponse = await Stats.find({});

  const format = {
    count_mutant_dna: 0,
    count_human_dna: 0,
  };

  statsResponse.forEach((item) => {
    switch (item.type) {
      case 'human':
        format.count_human_dna += 1;
        break;
      case 'mutant':
        format.count_mutant_dna += 1;
        break;
      default:
        logger.info('salt');
        break;
    }
  });

  format.ratio = format.count_human_dna / format.count_mutant_dna || 0;

  return { ...format };
};
