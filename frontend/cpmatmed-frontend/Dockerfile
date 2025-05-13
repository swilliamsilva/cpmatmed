FROM node:14.21.3-alpine  

# Instala dependências necessárias para compilação (Python, build tools)
RUN apk add --no-cache \
    python3 \
    make \
    g++

# Configura o diretório de trabalho
WORKDIR /app

# Copia os arquivos de dependência e instala
COPY package.json package-lock.json ./
RUN npm install --legacy-peer-deps  # Ignora conflitos de peer dependencies

# Copia o código fonte e faz o build
COPY . .
RUN npm run build --configuration production  # Build do Angular

# Configuração do servidor estático (serve)
ENV PORT=3000
EXPOSE $PORT
CMD ["npm", "start"]  # Executa o comando "npm start"