package com.spring.ia.rag;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class HRPolicyLoader {

    private final VectorStore vectorStore;

    @Value("classpath:HR_Policies.pdf")
    Resource policyFile;

    public HRPolicyLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadPDF() {
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(policyFile);
        List<Document> docs = tikaDocumentReader.get();
        TextSplitter textSplitter =
                TokenTextSplitter.builder().withChunkSize(200).withMaxNumChunks(400).build();
        log.info("Loading HR policies into vector store...");
        vectorStore.add(textSplitter.split(docs));
    }
}
