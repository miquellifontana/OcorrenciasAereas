# Ocorrências Aéreas na Aviação Civil Brasileira

#### URL GitHub:
 - (https://github.com/miquellifontana/OcorrenciasAereas)
 
#### Membros da equipe 
 - Guilherme Nunes Ferreira | email: guiferreira1996@gmail.com  | GitHub: https://github.com/gnunesferreira
 - Gustavo Campos Silva     | email: gucamposcs@gmail.com       | GitHub: https://github.com/gucamposcs
 - Miquelli Rigo Fontana    | email: miquellirfontana@gmail.com | GitHub: https://github.com/miquellifontana
 
#### Motivação
 - Exibir informações sobre os acidentes e incidentes graves ocorridos na Aviação civil brasileira.

#### Fontes de dados
 - Cenipa (Centro de Investigação e Prevenção de Acidentes Aeronáuticos)
   - Tabela de Ocorrências: (http://www.cenipa.aer.mil.br/cenipa/Anexos/article/1451/ocorrencia.csv)
   - Tabela de Aeronaves envolvidadas: (http://www.cenipa.aer.mil.br/cenipa/Anexos/article/1451/aeronave.csv)

#### Descrição dos dados
##### Tabela de Ocorrências
Nome dos Campos | Tipo de dados | Significado
--------------- | ------------- | -----------
codigo_ocorrencia | INT(11) | Código de identificação da ocorrência
classificacao | VARCHAR(30) | Classificação da ocorrência
tipo | VARCHAR(80) | Tipo da ocorrência
localidade | VARCHAR(100) | Cidade / município onde houve a ocorrência
uf | VARCHAR(3) | Estado / província onde houve a ocorrência
pais | VARCHAR(80) | País onde houve a ocorrência
aerodromo | VARCHAR(4) | Código ICAO do aeródromo onde houve a ocorrência
dia_ocorrencia | DATE | Data da ocorrência
horario_utc | TIME | Horário da ocorrência no padrão UTC
sera_investigada | VARCHAR(5) | Informação se a ocorrência será ou não investigada
comando_investigador | VARCHAR(15) | Comando investigador responsável pela ocorrência
status_investigacao | VARCHAR(10) | Informação se a investigação está ativa ou finalizada
numero_relatorio | VARCHAR(50) | Número de identificação do relatório final de investigação
Relatorio_publicado | VARCHAR(5) | Informação se o relatório final de investigação foi ou não publicado
dia_publicacao | DATE | Dia da publicação do relatório final de investigação
quantidade_recomendacoes | INT(11) | Quantidade de recomendações de segurança emitidas
aeronaves_envolvidas | INT(11) | Quantidade de aeronaves envolvidas na ocorrência
saida_pista | INT(11) | Informação se houve ou não saída de pista na ocorrência
dia_extracao | DATE | Dia da extração dos dados na base de dados do CENIPA

##### Tabela de Aeronaves
Nome dos Campos | Tipo de dados | Significado
--------------- | ------------- | -----------
codigo_aeronave | INT(11) | Código de identificação de aeronave
codigo_ocorrencia | INT(11) | Código de identificação de ocorrência
matricula | VARCHAR(10) | Matrícula da aeronave
codigo_operador | INT(11) | Código de identificação do operador
equipamento | VARCHAR(45) | Tipo da aeronave
fabricante | VARCHAR(45) | Fabricante da aeronave
modelo | VARCHAR(45) | Modelo da aeronave
tipo_motor | VARCHAR(45) | Tipo de motor da aeronave
quantidade_motores | INT(11) | Quantidade de motores da aeronave
peso_maximo_decolagem | FLOAT | Peso máximo para decolagem
quantidade_assentos | INT(11) | Quantidade de assentos na aeronave
ano_fabricacao | INT(11) | Ano de fabricação da aeronave
pais_registro | VARCHAR(80) | País de registro da aeronave
categoria_registro | VARCHAR(4) | Categoria de registro da aeronave no momento da ocorrência
categoria_aviacao | VARCHAR(15) | Categoria de aviação da aeronave no momento da ocorrência
origem_voo | VARCHAR(4) | Origem de voo da aeronave
destino_voo | VARCHAR(4) | Destino de voo da aeronave
fase_operacao | VARCHAR(15) | Fase de operação da aeronave no momento da ocorrência
tipo_operacao | VARCHAR(20) | Tipo de operação da aeronave no momento da ocorrência
nivel_dano | VARCHAR(10) | Nível do dano da aeronave
quantidade_fatalidades | INT(11) | Quantidade de fatalidades (mortos) na aeronave
dia_extracao | DATE | Dia da extração dos dados na base de dados do CENIPA

#### Volume de dados
 - No arquivo disponibilizado pelo Cenipa em 30/07/2016 haviam 2027 ocorrências registradas

#### Formato de disponibilização dos dados
 - Os dados disponibilizados estão no formato CSV com o separados ','
 
#### Exemplo de consulta
 - Consultar ocorrências com número de fatalidades maior ou igual a 199.
codigo_ocorrencia | classificacao | tipo | localidade | uf | pais | datahora | quantidade_assentos | quantidade_fatalidades
----------------- | ------------- | ---- | ---------- | -- | ---- | -------- | ------------------- | ----------------------
29859 | ACIDENTE | PERDA DE CONTROLE NO SOLO | SÃO PAULO | SP | BRASIL | 2007-07-17 21:54:00 | 185 | 199