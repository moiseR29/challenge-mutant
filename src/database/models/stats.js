import { Schema, model } from 'mongoose';

const statsSchema = new Schema({
  dna: {
    type: [String],
  },
  type: {
    type: String,
    default: 'human',
  },
});

export const Stats = model('Stats', statsSchema);
