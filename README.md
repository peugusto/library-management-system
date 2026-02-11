  # Sistema de Empréstimo de Biblioteca

  ## Descrição
  Projeto de estudo para praticar **Java com JDBC**, **DAO clássico** e **orientação a objetos**, com foco em **modelagem de domínio e regras de negócio**, sem uso de frameworks ou ORM.

  ## Fluxo Básico do Sistema
1. Gerenciar usuários (inserir, atualizar, excluir e desativar)
2. Gerenciar livros (inserir, excluir)
3. Gerenciar empréstimos (renovar e encerrar)


  # Regras de Negócio Principais

### Usuário
- Usuário inativo não pode realizar empréstimos
- Usuário não pode ser excluído se estiver com empréstimo em ativo
- Usuário não pode ser excluído se possuir histórico

---

### Livro
- Livro pode estar disponível ou fora de circulação
- Livro não pode ser emprestado se estiver indisponível
- Livro não pode ser excluído se estiver em empréstimo ativo

---

### Empréstimo
- Depende de um usuário e um livro existentes
- Possui data de início e fim real
- Trabalha apenas com datas (sem horário)
- Controla seu próprio ciclo de vida
- Não permite estados inválidos

---


  ## Estrutura do Projeto (MVC)
  ```
└── 📁src
    └── 📁db
        ├── DB.java
        ├── DBException.java
    └── 📁model
        └── 📁dao
            └── 📁impl
                ├── EmprestimoDaoJdbc.java
                ├── LivroDaoJdbc.java
                ├── UsuarioDaoJdbc.java
            ├── DaoFactory.java
            ├── EmprestimoDAO.java
            ├── LivroDAO.java
            ├── UsuarioDAO.java
        └── 📁entities
            └── 📁enums
                ├── StatusEmprestimo.java
                ├── StatusLivro.java
                ├── StatusUsuario.java
            ├── Emprestimo.java
            ├── Livro.java
            ├── Usuario.java
        └── 📁services
            ├── BusinessException.java
            ├── EmprestimoService.java
            ├── LivroService.java
            ├── UsuarioService.java
    └── 📁view
        └── Program.java
├── LivroService.java
└── UsuarioService.java
└── view
└── Program.java
```

  ## Tecnologias utilizadas: 
  Java, JDBC e MySQL.
