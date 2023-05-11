
## 2023-05-11

* Criação do arquivo CHANGELOG.md;
* Criação da classe RequestCashFlowReport;
* Criação da classe ResponseCashFlowReport;
* Criação da classe utilitária EncodeUtils e seu teste unitário, para transformação em string Base64;
* Criado Dockerfile como imagem base para utilizar a biblioteca com os micro services;
* Modificado scripts compile e compile.bat para além de compilar localmente, também criar a imagem;


## 2023-05-10

Início do desenvolvimento da biblioteca da plataforma

* Criação das classes utilitárias;
  * DateUtils;
  * IdUtils;
  * JsonUtils;
  * ValidationUtils;
* Criação das classes de exceções;
  * ApplicationException;
  * EntityFoundException;
  * EntityNotFoundException;
  * EntityInvalidException;
* Criação das classes model;
  * CashFlow;
  * ItemCashFlow;
  * ItemCashFlowType (enum);
  * ActionType (enum);
  * RequestAction;