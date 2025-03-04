# 🎮 MovieApp

Um aplicativo para explorar e salvar filmes favoritos, desenvolvido com as melhores práticas e tecnologias modernas para Android.

## 🚀 Tecnologias

- [x] **Kotlin**
- [x] **Jetpack Compose**
- [x] **MVVM e Clean Architecture**
- [x] **Frameworks**: Coroutines, Retrofit, OkHttp, Coil e Dagger Hilt
- [x] **Jetpack Components**: Paging 3, Lifecycle, Navigation, Room, ViewModel
- [x] **Testes unitários** JUnit e Mockito

## 📚 API

Para obter informações sobre filmes, este aplicativo utiliza a **TMDB API**.

### Configuração da API
1. Crie uma conta no [The Movie Database (TMDB)](https://www.themoviedb.org/).
2. Gere uma API Key acessando "Configuração da Conta" > "API".
3. Adicione a chave no arquivo `local.properties`:
   ```properties
   API_KEY=SUA_CHAVE_AQUI
   BASE_URL="https://api.themoviedb.org/3/"
   BASE_URL_IMAGE="https://image.tmdb.org/t/p/w500/"
   ```

### Endpoints Utilizados
- **Filmes populares**: `GET /movie/popular`
- **Busca de filmes**: `GET /search/movie`
- **Detalhes do filme**: `GET /movie/{movie_id}`
- **Filmes similares**: `GET /movie/{movie_id}/similar`

## 📌 Funcionalidades

✔️ Listagem de filmes populares  
✔️ Busca por filmes  
✔️ Exibição de detalhes do filme (sinopse, elenco, avaliação)  
✔️ Adicionar/remover filmes dos favoritos  
✔️ Paginação para melhor desempenho na listagem


### Screenshot

<img src="imagens\tela1.jpeg" width="200"> <img src="imagens\tela2.jpeg" width="200"> <img src="imagens\tela3.jpeg" width="200">