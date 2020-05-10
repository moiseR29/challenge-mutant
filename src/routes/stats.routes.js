import { Router } from 'express';
import { getStats } from '../controllers';

const router = Router();

router.get('/', (...args) => getStats(...args));

export const StatsRouter = router;
