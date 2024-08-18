package domain.service;

import api.dto.SubmissionRequestDto;
import common.Const;
import domain.model.Submission;
import jakarta.enterprise.context.ApplicationScoped;
import api.dto.SubmissionResponseDto;
import org.jboss.logging.Logger;
import api.rest.SubmissionController;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@ApplicationScoped
public class SubmissionService {

    private static final Logger LOGGER = Logger.getLogger(SubmissionController.class);

    private SubmissionService() {
    }

    public static SubmissionService createSubmissionService() {
        return new SubmissionService();
    }

    public SubmissionResponseDto processSubmission(SubmissionRequestDto dto) throws Exception {

        LOGGER.info("Iniciando processamento da submissão para o desafio com ID: " + dto.challengeId());

        String className = "UserSolution";
        String file = "src/main/java/" + className + ".java";
        String compiled = file.replace(".java", ".class");
        Path path = Paths.get(file);
        Path pathCompiled = Paths.get(compiled);
        Files.deleteIfExists(path);
        Files.deleteIfExists(pathCompiled);
        Files.write(path, dto.code().getBytes());

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int compilationResult = compiler.run(null, null, null, file);
        if (compilationResult != 0) {
            throw new Exception("Compilation failed");
        }

        String output = executeInSeparateProcess(className);

        Submission submission = new Submission(
                dto.challengeId(),
                dto.userId(),
                dto.code(),
                List.of(output.split("\n")),
                null,
                null,
                Const.SUBMISSION_EXECUTED,
                null,
                null,
                0
        );
        return new SubmissionResponseDto(true, output);
    }

    private String executeInSeparateProcess(String className) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "-cp", "src/main/java", className
        );

        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            process.waitFor();
            return output.toString().trim();
        }
    }
}
