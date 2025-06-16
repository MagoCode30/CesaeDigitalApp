# CesaeApp 📱

A **CesaeApp** é uma aplicação Android nativa desenvolvida para promover e facilitar o acesso à oferta formativa, projetos, serviços e contactos do **CESAE Digital**, um centro de referência em competências digitais em Portugal.

Com uma interface intuitiva e moderna, a aplicação permite a qualquer utilizador:
- Explorar e consultar todos os cursos disponíveis, acedendo a detalhes relevantes de forma rápida e prática.
- Adicionar, editar e gerir cursos localmente, demonstrando o ciclo completo de um CRUD robusto em ambiente mobile.
- Consultar informação institucional sobre projetos, serviços e contactos de diferentes polos do CESAE Digital, centralizando o acesso a dados essenciais para formandos e parceiros.
- Navegar de forma fluida entre módulos, com design responsivo baseado em Material Design e foco na experiência do utilizador.

Projetada com arquitetura **MVVM** e persistência local via **Room**, esta app demonstra boas práticas de desenvolvimento Android, com especial atenção à organização de código, validação de dados e feedback ao utilizador.

---

## 🚀 Funcionalidades

- **Gestão de cursos:** Adicionar, editar, remover e visualizar cursos com todos os detalhes.
- **Ordenação flexível:** Listagem de cursos ordenada por nome (A-Z/Z-A) ou por data (mais recente/mais antigo).
- **Seleção de imagem personalizada** para cada curso.
- **Visualização institucional:** Cards informativos sobre projetos, serviços e contactos do CESAE Digital.
- **Formulários validados:** Não permite submissão de dados incompletos ou inválidos.
- **Feedback ao utilizador:** Mensagens de sucesso ou erro para todas as operações.
- **Design consistente:** Interface moderna e alinhada com o ecossistema Android.

---

## 🛠️ Tecnologias & Arquitetura

- **Kotlin (100%)**
- **Room** (persistência de dados local)
- **MVVM:** ViewModel + LiveData + Repository + DAO
- **ViewBinding**
- **RecyclerView**
- **Material Components**
- **Estrutura modular e escalável**

> **O projeto adota a arquitetura recomendada pela Google, separando claramente UI, lógica de apresentação, acesso a dados e persistência, garantindo manutenibilidade, testabilidade e robustez.**

---

## 📦 Como correr o projeto

1. **Pré-requisitos:**
   - Android Studio Iguana ou superior
   - JDK 11+
2. **Clonar o repositório:**
   ```bash
   git clone https://github.com/MagoCode30/CesaeDigitalApp.git
3. Abrir no Android Studio:
  - Sync Gradle, confirmar dependências.
4. Executar em emulador ou dispositivo (API 24+).

--- 

## 💡 Possíveis melhorias futuras

- Tornar os módulos de projetos e serviços dinâmicos (CRUD completo).
- Internacionalização e extração total dos textos para strings.xml.
- Suporte a dark mode.
- Testes unitários e instrumentados.
- Integração futura com backend/API.

## 🏅 Licença

MIT License — livre para fins educativos e portfólio.

---

## 👨‍💻 Desenvolvido por

[**Luis Mago**](https://www.linkedin.com/in/luismago-dev/)

