import { Router } from 'express';
import { MutantRouter } from './mutant.routes';

const MainRouter = Router();

MainRouter.use('/mutant', MutantRouter);

export default MainRouter;
