package com.andreigravonski.adotepet.service.impl;

import com.andreigravonski.adotepet.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    // Define o caminho da pasta onde as imagens serão salvas.
    private final Path rootLocation = Paths.get("upload-dir");

    public FileStorageServiceImpl() {
        // Cria o diretório de upload na inicialização, se ele não existir.
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar o diretório de upload", e);
        }
    }

    @Override
    public String store(MultipartFile file) {
        // Validação inicial do arquivo
        if (file.isEmpty()) {
            throw new RuntimeException("Falha ao armazenar arquivo vazio.");
        }

        String originalFilename = file.getOriginalFilename();

        // Validação defensiva do nome do arquivo
        if (originalFilename == null || originalFilename.isBlank()) {
            throw new RuntimeException("Nome de arquivo inválido.");
        }

        try {
            // Lógica para extrair a extensão de forma segura
            String extension = "";
            int i = originalFilename.lastIndexOf('.');
            if (i > 0) {
                extension = originalFilename.substring(i);
            }

            // Geração do nome único (sem o .toString() desnecessário)
            String uniqueFilename = UUID.randomUUID() + extension;

            // Monta o caminho completo do destino do arquivo
            Path destinationFile = this.rootLocation.resolve(Paths.get(uniqueFilename))
                    .normalize().toAbsolutePath();

            // Copia o conteúdo do arquivo enviado para o arquivo de destino no servidor
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            // Retorna o nome único do arquivo para ser salvo no banco de dados
            return uniqueFilename;

        } catch (IOException e) {
            throw new RuntimeException("Falha ao armazenar o arquivo.", e);
        }
    }
}