import { Router } from 'express';
import { MutantRouter } from './mutant.routes';
import { StatsRouter } from './stats.routes';

const MainRouter = Router();

MainRouter.use('/mutant', MutantRouter);
MainRouter.use('/stats', StatsRouter);

export default MainRouter;
