package com.elbaih.fileinputandconvertion;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Convertion {
    public static final String outputPath = "src/main/resources/";
    public static final String inputPath = "src/main/resources/";
    public static final String courseDataDb = "coursedata";
    public static final String studentDataDb = "student-data";

    public static void initDataBase(){
        courseData2Csv(courseDataDb);
        studentData2Csv(studentDataDb);
    }

    public static void studentData2Csv(String studentDataTextFileName) {
        String path = inputPath + studentDataTextFileName + ".txt";
        try {
            String fileString = Files.readString(Paths.get(path));
            fileString = "id," + fileString.replace("#Ap #", "#Ap ").replace("#", ",");
            String[] split = fileString.split("\\$");
            for (int i = 1; i < split.length; i++) {
                split[i] = i + "," + split[i];
            }
            String result = String.join("\n", split);
            saveToFileCsv("student-data", result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void courseData2Csv(String courseDataXmlFile) {
        String path = inputPath + courseDataXmlFile + ".xml";
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();

            String fileString = Files.readString(Paths.get(path))
                    .replaceAll("Course Name>", "CourseName>")
                    .replaceAll("Course duration>", "CourseDuration>")
                    .replaceAll("Course time>", "CourseTime>");


            InputStream stream = new ByteArrayInputStream(fileString.getBytes(StandardCharsets.UTF_8));

            Document doc = db.parse(stream);

   
            NodeList list = doc.getElementsByTagName("row");

            String newFileContent = "id,Course Name,Instructor,Course duration,Course time,Location";

            for (int i = 0; i < list.getLength(); i++) {

                Element child = (Element) list.item(i);

                String id = child.getElementsByTagName("id").item(0).getTextContent();
                String CourseName = child.getElementsByTagName("CourseName").item(0).getTextContent();
                String Instructor = child.getElementsByTagName("Instructor").item(0).getTextContent();
                String CourseDuration = child.getElementsByTagName("CourseDuration").item(0).getTextContent();
                String CourseTime = child.getElementsByTagName("CourseTime").item(0).getTextContent();
                String Location = child.getElementsByTagName("Location").item(0).getTextContent();

                newFileContent += "\n" + id + "," + CourseName + "," + '"' + Instructor + '"' + "," + CourseDuration
                        + "," + CourseTime + "," + Location;

            }

            saveToFileCsv("coursedata", newFileContent);

        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public static void saveToFileCsv(String fileName, String data) throws IOException {
        File csvFile = new File(outputPath + fileName + ".csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
        writer.write(data);
        writer.close();
    }


}

// // <row>
// Node child1 = list.item(i);

// NodeList d = child1.getChildNodes();

// String line = "";
// for (int j = 0; j < d.getLength(); j++) {
// if(d.item(j).getTextContent().trim().isEmpty())
// continue;
// line+='"'+d.item(j).getTextContent().trim()+'"'+",";
// }
// newFileContent+="\n"+line;
// System.out.println(line);
