<p>Projeto consiste em um serviço que cria figurinhas, consumindo API do The Office, pública.</p>
<p>A API do The Office retorna, randomicamente, o nome de um personagem e uma "pérola" por ele proferida durante o programa. O serviço que criei busca, em uma coleção de imagens, aquela que corresponde ao nome do personagem. Em seguida, cria uma imagem anexando abaixo da foto original o texto do personagem. </p>
<p>Após criada a figurinha, postei uma seleção na internet, gerando uma URL para cada imagem. Essa URL pode ser salva no banco de dados do projeto, o MongoDB.</p>
<p>A segunda etapa do projeto é uma API, que faz um CRUD das figurinhas. O GET, especialmente, retorna uma imagem, e não um json. O POST e o PUT retornam um json e o DELETE não retorna nenhum body, apenas o status da requisição.</p>
<p>O endpoint padrão da API é "/stickers". Para o PUT e o DELETE, é necessário também fornecer como parâmetro na URL o "id" da figurinha. Ex: "{url_base}/stickers/{id}"</p>

