# Copia os arquivos
COPY backend /cpmatmed/backend
COPY start.sh /cpmatmed/start.sh

# Define diretório de trabalho
WORKDIR /backend

# Dá permissão de execução
RUN chmod +x start.sh

# Executa o script
CMD ["./start.sh"]
