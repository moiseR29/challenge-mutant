import { Router } from 'express';
import { isMutant } from '../controllers';

const router = Router();

router.post('/', (...arg) => isMutant(...arg));

export const MutantRouter = router;
