package store.supplier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class MarkdownSupplier {
    protected final String filePath;

    protected MarkdownSupplier(final String filePath) {
        this.filePath = filePath;
    }

    protected List<String> readFileWithMarkdown() {
        try (final BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            readHeader(br);
            return readContent(br);
        } catch (final IOException e) {
            throw new IllegalArgumentException(String.format("Not File Support : %s", filePath));
        }
    }

    private void readHeader(final BufferedReader br) throws IOException {
        br.readLine();
    }

    private List<String> readContent(final BufferedReader br) throws IOException {
        String line;
        final List<String> lines = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }
}
