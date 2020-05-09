export const initMessage = (req, res) =>
  res.send('Welcome to mutant Api, please sended sequence');

export const notFoundEndpoint = (req, res) =>
  res.send('EndPoint not found, use /mutant');
