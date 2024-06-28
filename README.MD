# JDBC

JDBC é um conjunto de classes e interfaces em Java (API) usado para enviar instruções SQL para qualquer banco de dados relacional.

O driver JDBC fornece a conexão com o banco de dados e implementa o protocolo para transferir consultas e resultados entre o cliente e o banco de dados.

# Pool de conexões

Para realizar uma conexão com o banco de dados, a conexão geralmente é feita usando o protocolo TCP/IP. E esse abre e fecha de conexões gera um custo para aplicações web, onde possa ter um fluxo de muitas requisições constantes e cada uma delas vai gerar uma abertura e fechamento de uma nova conexão com o banco de dados. E para evitar isso, é usado o pool de conexões, onde mantém um determinado número de conexões sempre abertas e reusá-las quando necessário.
