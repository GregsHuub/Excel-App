import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface CsvReaderInterface {

    // METHOD TO MULTIPLY
    default List<String> findFilesInFolder(String directoryFolder, String excelFileFormat) throws IOException {

        List<String> allFiles = new ArrayList<>();
        Stream<Path> walk = Files.walk(Paths.get(directoryFolder));
        //            IF INPUT FORMAT IS CSV
        if (excelFileFormat.equalsIgnoreCase("csv")) {
            allFiles = walk.filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(x -> x.endsWith(".csv"))
                    .collect(Collectors.toList());

//            IF INPUT FORMAT IS TSV
        } else if (excelFileFormat.equalsIgnoreCase("tsv")) {

            allFiles = walk.filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(x -> x.endsWith(".tsv") || x.endsWith(".txt"))
                    .collect(Collectors.toList());

//            IF INPUT FORMAT IS EMPTY, RESULT: FIND CSV AND TSV(TXT)
        } else if (excelFileFormat.equals("")) {
            allFiles = walk.filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(x -> x.endsWith(".tsv") || x.endsWith(".csv") || x.endsWith(".txt"))
                    .collect(Collectors.toList());

        } else if (!excelFileFormat.equals("") && !excelFileFormat.equalsIgnoreCase("csv") && !excelFileFormat.equalsIgnoreCase("tsv")) {
            allFiles = walk.filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(x -> x.endsWith("." + excelFileFormat))
                    .collect(Collectors.toList());

        }
        return allFiles;
    }
}
