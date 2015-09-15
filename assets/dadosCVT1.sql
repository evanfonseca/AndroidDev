BEGIN TRANSACTION;
DROP TABLE if exists android_metadata;
DROP TABLE if exists categorias_lugares;
DROP TABLE if exists cidades;
DROP TABLE if exists lugares;
DROP TABLE if exists categorias;
DROP TABLE if exists subcategorias;
DROP TABLE if exists fotoscapacidade;

CREATE TABLE android_metadata (locale TEXT);
INSERT INTO android_metadata VALUES('pt_PT');

CREATE TABLE subcategorias (id INTEGER, categoria_id INTEGER, descriacao TEXT,link_icon, Criado_em TEXT, Actualisado_em TEXT);
INSERT INTO subcategorias VALUES(1,3,'Cafés',1,NULL,NULL);
INSERT INTO subcategorias VALUES(2,3,'Lanches',2,NULL,NULL);
INSERT INTO subcategorias VALUES(3,3,'Pizzarias',3,NULL,NULL);
INSERT INTO subcategorias VALUES(4,3,'Restaurantes',4,NULL,NULL);
INSERT INTO subcategorias VALUES(5,3,'Bares',5,NULL,NULL);
INSERT INTO subcategorias VALUES(6,2,'Hotéis',6,NULL,NULL);
INSERT INTO subcategorias VALUES(7,2,'Pensões',7,NULL,NULL);
INSERT INTO subcategorias VALUES(8,2,'Pousadas',8,NULL,NULL);


CREATE TABLE categorias_lugares (id INTEGER, lugar_id INTEGER, Categoria_id INTEGER,SubCategoria_id INTEGER,Criado_em TEXT,Actualisado_em TEXT);
INSERT INTO categorias_lugares VALUES(1,1,2,6,NULL,NULL);
INSERT INTO categorias_lugares VALUES(2,5,2,6,NULL,NULL);
INSERT INTO categorias_lugares VALUES(3,7,2,7,NULL,NULL);
INSERT INTO categorias_lugares VALUES(4,7,3,4,NULL,NULL);
INSERT INTO categorias_lugares VALUES(5,8,3,5,NULL,NULL);
INSERT INTO categorias_lugares VALUES(6,9,3,4,NULL,NULL);
INSERT INTO categorias_lugares VALUES(7,2,3,1,NULL,NULL);
INSERT INTO categorias_lugares VALUES(8,3,3,5,NULL,NULL);
INSERT INTO categorias_lugares VALUES(9,6,2,0,NULL,NULL);
INSERT INTO categorias_lugares VALUES(10,10,1,6,NULL,NULL);
INSERT INTO categorias_lugares VALUES(11,11,1,0,NULL,NULL);
INSERT INTO categorias_lugares VALUES(12,12,2,6,NULL,NULL);
INSERT INTO categorias_lugares VALUES(13,13,3,5,NULL,NULL);
INSERT INTO categorias_lugares VALUES(14,4,3,1,NULL,NULL);
INSERT INTO categorias_lugares VALUES(15,13,3,4,NULL,NULL);

CREATE TABLE categorias (id INTEGER, descriacao TEXT,link_icon TEXT,Criado_em TEXT,Actualisado_em TEXT);
INSERT INTO categorias VALUES(1,'Monumentos e Sitios','monument','1','1');
INSERT INTO categorias VALUES(2,'Hoteis e Pensões','hotels1','1','1');
INSERT INTO categorias VALUES(3,'Restaurantes e Bares','restaurant','1','1');
INSERT INTO categorias VALUES(4,'Centro Comerciais','comerce','1','1');
INSERT INTO categorias VALUES(5,'Pontos de Interresse','pois','1','1');
INSERT INTO categorias VALUES(6,'Serviços','services','1','1');
INSERT INTO categorias VALUES(7,'Lugares de Lazer','lazer','1','1');



CREATE TABLE cidades (Id INTEGER,Nome TEXT, Descricao TEXT,Ilha TEXT,Latitude_centro TEXT,Longitude_centro TEXT,Historia TEXT,Clima TEXT,Geografia TEXT,Criado_em TEXT,Actualisado_em TEXT,GestorId INTEGER,Gestor TEXT,LugarQuantidade TEXT,FotoCapa TEXT);
INSERT INTO cidades VALUES(1,'Praia','A cidade da Praia é a capital de Cabo Verde, país-arquipélago no Oceano Atlântico, a oeste do Senegal. Está localizada a sul da ilha de Santiago. É também sede do Município do mesmo nome.

A Praia é a maior cidade de Cabo Verde. Tem um porto comercial, por onde é exportado café, cana de açúcar e frutas tropicais. Possui igualmente uma importante indústria pesqueira.

Como cidade capital, abriga no bairro chamado Plateau, promontório à beira-mar, edifícios públicos e outras construções de importância, como o Palácio Presidencial, construído no fim do século XIX para ser a residência do governador português. Contam-se ainda a antiga Câmara Municipal, prédio com fachada clássica e uma torre central quadrada, a Igreja Nossa Senhora da Graça, também no estilo classicista, o Museu Etnográfico e o Monumento de Diogo Gomes, navegador português e descobridor da Ilha de Santiago em 1460. Ao longo da História de cabo Verde houve sucessivas propostas de transferências da capital de Praia para outros sítios, sendo a última a proposta da mudança para Mindelo durante o séc. XIX. As sucessivas administrações portuguesas nunca mostraram interesse (económico ou político?) em mudar a capital de Cabo Verde. Através de um decreto de 1858, com a elevação do estatuto de vila para cidade, Praia ficou definitivamente a capital de Cabo Verde, concentrando as funções de centro político, religioso e económico.

Durante a administração portuguesa, só o planalto central (chamado Plateau) é que era considerado como parte integrante da cidade, digno de ser urbanizado e concentrando os serviços. Foi só depois da independência que se aceitou a realidade que a cidade da Praia já englobava também todos os bairros circundantes. Depois da independência a cidade sofreu um boom demográfico, e em trinta anos quadruplicou a sua população, recebendo movimentos migratórios de todas as ilhas, e contribuindo para que a ilha de Santiago passasse a ter metade da população de Cabo Verde, para que o Município da Praia passasse a ter um quarto, e para que a cidade da Praia passasse a ter um quinto.','Santiago',14.93305,-23.51333,'A vila da Praia de Santa Maria surgiu em 1615, quando se deu o início do povoamento de um planalto situado perto de uma praia (praia de Santa Maria) que oferecia boas condições para navios. Inicialmente utilizada como porto clandestino (para que não se pagassem as taxas aduaneiras na então capital, Ribeira Grande) a localidade foi progressivamente adquirindo características de uma vila com a gradual fuga das populações da Ribeira Grande, aquando do declínio desta última. A passagem oficial da capital de Ribeira Grande para Praia de Santa Maria deu-se em 1770.','Tropical Humido','Montanhoso',NULL,NULL,NULL,NULL,1,1);
INSERT INTO cidades VALUES(2,'Mindelo','Mindelo é uma cidade que se localiza na ilha de São Vicente, é sede do concelho homónimo e é a segunda maior cidade de Cabo Verde. Ocupa uma área total de 67 km² a noroeste da ilha, na Baía do Porto Grande, porto natural formado pela cratera submarina de um vulcão com cerca de 4 km de diâmetro. O Ilhéu dos Pássaros, com 82 metros de altitude e que hospeda um pequeno farol, sinaliza a outra extremidade da cratera.','S. Vicente',16.87655,-24.98135,'Na verdade, mesmo os viajantes da época que demandavam a cidade confirmam que a população que vivia em torno do cais tinha uma vida modesta, se não mesmo miserável, pelo que são compreensíveis os expedientes de sobrevivência a que recorriam. Os navios que chegavam eram rapidamente rodeados por pequenos botes conduzidos por um magote de remadores que tentavam sobreviver vendendo frutas, doces e curiosidades da terra. Fugindo dos guardas da Alfândega subiam para o convés dos navios, não só para tentar vender os seus produtos como também para comprar cigarros, tabacos e bebidas alcoólicas para serem revendidos em terra.

Mas essa existência miserável não era exclusiva do cais, estendia-se também à cidade que, por sinal, nunca viria a gozar de boa fama nas demais ilhas do arquipélago que a consideravam uma terra de perdição.

O carregamento do carvão, impondo aos trabalhadores a respiração quotidiana de poeiras, revela-se um foco poderoso de tuberculização da classe operária. Por sua vez as promíscuas condições de habitação encarregam-se de expandir a doença pelos bairros pobres da cidade.

Acresce que, como em todas as cidades com elevado movimento de navios nos portos, mas particularmente nesta que sobrevive exclusivamente a partir dele, Mindelo é uma cidade onde a prostituição atinge índices elevados e com ela as doenças venéreas. E a mais grave de todas, a na época mortífera sífilis, acaba por entrar e propagar-se por todo o arquipélago devido às ligações constantes que Mindelo tem com as restantes ilhas.','Tropical Seco','Semi-motanhoso',NULL,NULL,NULL,NULL,1,2);
INSERT INTO cidades VALUES(3,'Santa Maria','Sal é uma ilha do grupo do Barlavento de Cabo Verde e conselho do mesmo nome. É uma das menores ilhas habitadas, estendendo-se por 30 km de comprimento e 12 km de largura, no sentido leste-oeste e distante cerca de 50 km em linha reta da Boa Vista, sua mais próxima vizinha. O conselho do Sal é constituído apenas por uma freguesia: Nossa Senhora das Dores.','Sal',16.60008,-22.91035,'Por não possuir água potável a ilha conheceu o abandono até ao século XIX quando, a partir de 1833 se iniciou a exploração de sal na localidade de Pedra de Lume. Essa atividade deu início ao povoamento.

Administrativamente, a ilha pertenceu ao conselho da Boavista até 1935.

A exploração das salinas dinamizou a economia da ilha até meados da década de 1980.

Com o objectivo de constituir um ponto de escala para os voos com destino à América do Sul, em 1939 foi construído, por iniciativa italiana, o "Aeroporto Internacional da Ilha do Sal", com projeto do Engenheiro Raul Pires Ferreira Chaves.

A este mesmo profissional, à época, deve-se a implantação de sistemas de captação de água pluvial, possibilitando o incremento do povoamento, sobretudo através da migração interna no arquipélago, nomeadamente a partir da ilha de São Nicolau. Desse modo, em curto espaço de tempo, a população teve um incremento de cinco vezes.','Tropical Seco','Plana',NULL,NULL,NULL,NULL,1,3);
INSERT INTO cidades VALUES(4,'S. Filipe','Fogo (ou Djarfogo que significa Ilha do Fogo[2] ) é uma das 10 ilhas que constituem o arquipélago de Cabo Verde, foi uma das primeiras Ilhas a ser povoada. São Filipe é a terceira cidade mais antiga do arquipélago. A ilha é vulcânica e é a mais saliente do grupo, devido à altitude do vulcão Pico do Fogo. O vulcão tem estado historicamente ativo e sua última erupção teve início em 23 de novembro de 2014[3] . A sua característica mais espetacular é uma cratera com 9 km de largura, com uma bordeira de 1 km de altura. A cratera tem uma fenda em sua parede oriental, e um grande pico eleva-se em seu centro. O cone central Pico constitui o ponto mais elevado da ilha (2829 m) e seu cume é cerca de 100 m mais alto do que a bordeira da cratera que o circunda. Lava do vulcão tem alcançado a costa oriental da ilha em tempos históricos.','Fogo',14.89517,-24.49289,'A ilha é vulcânica e é a mais saliente do grupo, devido à altitude do vulcão Pico do Fogo. O vulcão tem estado historicamente ativo e sua última erupção teve início em 23 de novembro de 2014[3] . A sua característica mais espetacular é uma cratera com 9 km de largura, com uma bordeira de 1 km de altura. A cratera tem uma fenda em sua parede oriental, e um grande pico eleva-se em seu centro. O cone central Pico constitui o ponto mais elevado da ilha (2829 m) e seu cume é cerca de 100 m mais alto do que a bordeira da cratera que o circunda. Lava do vulcão tem alcançado a costa oriental da ilha em tempos históricos.','Tropical Humido','Vulcanica',NULL,NULL,NULL,NULL,1,4);

CREATE TABLE lugares (Id INTEGER,Nome TEXT, Latitude TEXT,Longitude TEXT,Descricao TEXT,UserId INTEGER,User TEXT,CidadeId INTEGER,Cidade TEXT ,Criado_em TEXT,Actualisado_em TEXT);
INSERT INTO lugares VALUES(1,'hotel mindelo',16.889452,-24.987431,'d',NULL,NULL,2,NULL,NULL,NULL);
INSERT INTO lugares VALUES(2,'cafe praia',14.93305,-23.513327,'d',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO lugares VALUES(3,'bar s filipe',14.895168,-24.494564,'d',NULL,NULL,4,NULL,NULL,NULL);
INSERT INTO lugares VALUES(4,'cafe s maria',16.600081,-22.910349,'d',NULL,NULL,3,NULL,NULL,NULL);
INSERT INTO lugares VALUES(5,'hotel porto grande',16.889452,-24.987431,'d',NULL,NULL,2,NULL,NULL,NULL);
INSERT INTO lugares VALUES(6,'ola mar hotel',14.93305,-23.513327,'d',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO lugares VALUES(7,'Archote',16.889452,-24.987431,'d',NULL,NULL,2,NULL,NULL,NULL);
INSERT INTO lugares VALUES(8,'bar cely',16.889452,-24.987431,'d',NULL,NULL,2,NULL,NULL,NULL);
INSERT INTO lugares VALUES(9,'preta ku branco',14.93305,-23.513327,'d',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO lugares VALUES(10,'Palacio do Povo',16.886555,-24.986716,'d',NULL,NULL,2,NULL,NULL,NULL);
INSERT INTO lugares VALUES(11,'Praça Amilcar Cabral',16.889452,-24.987431,'d',NULL,NULL,2,NULL,NULL,NULL);
INSERT INTO lugares VALUES(12,'Hotel Vip',14.93305,-23.513327,'d',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO lugares VALUES(13,'fund d mar',16.886555,-24.986716,'d',NULL,NULL,2,NULL,NULL,NULL);

CREATE TABLE fotoscapacidade (id INTEGER, nome TEXT, CidadeId INTEGER, Criado_em TEXT, Actualisado_em TEXT);
INSERT INTO fotoscapacidade VALUES(1,'praia1',1,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(2,'praia2',1,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(3,'praia3',1,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(4,'praia4',1,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(5,'praia5',1,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(6,'praia6',1,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(7,'praia7',1,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(8,'praia8',1,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(9,'praia9',1,NULL,NULL);

INSERT INTO fotoscapacidade VALUES(10,'fogo1',4,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(11,'fogo2',4,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(12,'fogo3',4,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(13,'fogo4',4,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(14,'fogo5',4,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(15,'fogo6',4,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(16,'fogo7',4,NULL,NULL);

INSERT INTO fotoscapacidade VALUES(17,'santamaria1',3,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(18,'santamaria2',3,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(19,'santamaria3',3,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(20,'santamaria4',3,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(21,'santamaria5',3,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(22,'santamaria6',3,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(23,'santamaria7',3,NULL,NULL);

INSERT INTO fotoscapacidade VALUES(24,'mindelo1',2,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(25,'mindelo2',2,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(26,'mindelo3',2,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(27,'mindelo4',2,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(28,'mindelo5',2,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(29,'mindelo6',2,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(30,'mindelo7',2,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(31,'mindelo8',2,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(32,'mindelo9',2,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(33,'mindelo10',2,NULL,NULL);
INSERT INTO fotoscapacidade VALUES(34,'mindelo11',2,NULL,NULL);

COMMIT;
